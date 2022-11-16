package com.github.org.todaybread.todaybread.order.infra.http;

import com.github.org.todaybread.todaybread.order.application.facade.OrderFacadeImpl;
import com.github.org.todaybread.todaybread.order.infra.http.response.OrderResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@PreAuthorize(value = "hasAuthority('GENERAL')")
public class OrderController {

    private final OrderFacadeImpl orderFacade;

    @QueryMapping
    public Boolean hasOrder(
        Authentication authentication,
        @Argument String productId
    ) {
        return orderFacade.hasOrder(authentication.getName(), productId);
    }

    @QueryMapping
    public OrderResponse order(
        Authentication authentication,
        @Argument String orderId
    ) {
        return orderFacade.getById(authentication.getName(), orderId);
    }

    @QueryMapping
    public List<OrderResponse> ordersWithMember(
        Authentication authentication,
        @Argument int page,
        @Argument int take
    ) {
        return orderFacade.getListByMemberId(authentication.getName(), page, take);
    }

    @QueryMapping
    public List<OrderResponse> ordersWithStore(
        Authentication authentication,
        @Argument String storeId,
        @Argument int page,
        @Argument int take
    ) {
        return orderFacade.getListByStoreId(
            authentication.getName(),
            storeId,
            page,
            take
        );
    }

    @MutationMapping
    public OrderResponse createOrder(
        Authentication authentication,
        @Argument String productId
    ) {
        return orderFacade.create(authentication.getName(), productId);
    }

    @MutationMapping
    public OrderResponse success(
        Authentication authentication,
        @Argument String orderId
    ) {
        return orderFacade.success(authentication.getName(), orderId);
    }

    @MutationMapping
    public OrderResponse cancel(
        Authentication authentication,
        @Argument String orderId
    ) {
        return orderFacade.cancel(authentication.getName(), orderId);
    }

    @SchemaMapping(typeName = "Order", field = "orderUrl")
    public String orderUrl(OrderResponse order) {
        return orderFacade.getOrderUrl(order.getSteppayOrderCode());
    }

    @SchemaMapping(typeName = "Order", field = "orderSecret")
    public String orderSecret() {
        return orderFacade.getOrderSecret();
    }
}
