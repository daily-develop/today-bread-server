package com.github.org.todaybread.todaybread.order.infra.http;

import com.github.org.todaybread.todaybread.order.application.facade.OrderFacadeImpl;
import com.github.org.todaybread.todaybread.order.infra.http.response.OrderResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@PreAuthorize(value = "hasAuthority('GENERAL')")
public class OrderController {

    private final OrderFacadeImpl orderFacade;

    @QueryMapping
    public OrderResponse order(
        Authentication authentication,
        @Argument String orderId
    ) {
        return orderFacade.getById(authentication.getName(), orderId);
    }

    @QueryMapping
    public List<OrderResponse> orders(
        Authentication authentication,
        @Argument int page,
        @Argument int take
    ) {
        return orderFacade.getList(authentication.getName(), page, take);
    }

    @MutationMapping
    public OrderResponse createOrder(
        Authentication authentication,
        @Argument String productId
    ) {
        return orderFacade.create(authentication.getName(), productId);
    }
}
