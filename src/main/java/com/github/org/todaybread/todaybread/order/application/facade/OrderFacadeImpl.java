package com.github.org.todaybread.todaybread.order.application.facade;

import com.github.org.todaybread.todaybread.customer.application.service.CustomerServiceImpl;
import com.github.org.todaybread.todaybread.customer.domain.Customer;
import com.github.org.todaybread.todaybread.manager.exception.NotManagerException;
import com.github.org.todaybread.todaybread.member.application.service.MemberServiceImpl;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.order.application.service.OrderServiceImpl;
import com.github.org.todaybread.todaybread.order.domain.Order;
import com.github.org.todaybread.todaybread.order.domain.OrderType;
import com.github.org.todaybread.todaybread.order.exception.NotFoundOrderAuthorityException;
import com.github.org.todaybread.todaybread.order.exception.OrderNotAcceptedException;
import com.github.org.todaybread.todaybread.order.infra.http.response.OrderResponse;
import com.github.org.todaybread.todaybread.product.application.service.ProductServiceImpl;
import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.steppay.order.application.SteppayOrderService;
import com.github.org.todaybread.todaybread.steppay.order.infra.request.SteppayCreateOrderRequest;
import com.github.org.todaybread.todaybread.steppay.order.infra.request.SteppayItemRequest;
import com.github.org.todaybread.todaybread.steppay.order.infra.response.SteppayOrderResponse;
import com.github.org.todaybread.todaybread.store.application.service.StoreServiceImpl;
import com.github.org.todaybread.todaybread.store.domain.Store;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {

    private final OrderServiceImpl orderService;
    private final SteppayOrderService steppayOrderService;
    private final MemberServiceImpl memberService;
    private final CustomerServiceImpl customerService;
    private final StoreServiceImpl storeService;
    private final ProductServiceImpl productService;

    @Override
    public String getOrderUrl(String orderCode) {
        return orderService.getOrderUrl(orderCode);
    }

    @Override
    public String getOrderSecret() {
        return orderService.getOrderSecret();
    }

    @Override
    public Boolean hasOrder(String memberId, String productId) {
        return orderService.getByMemberIdAndProductIdAndSuccess(memberId, productId) != null;
    }

    @Override
    @Transactional
    public OrderResponse create(String memberId, String productId) {
        Member member = memberService.getMember(memberId);
        Customer customer = customerService.getByMember(member);
        Product product = productService.getById(productId);

        if (member.getId().equals(product.getStore().getManager().getMember().getId())) {
            throw new OrderNotAcceptedException();
        }

        Order order = orderService.getByMemberAndProduct(member, product);
        if (order != null) {
            return order.toResponse();
        }

        SteppayOrderResponse response = steppayOrderService.create(
            SteppayCreateOrderRequest.builder()
                .customerId(customer.getSteppayId())
                .items(
                    List.of(SteppayItemRequest.builder()
                        .productCode(product.getSteppayCode())
                        .priceCode(product.getSteppayPlanCode())
                        .build()
                    )
                )
                .build()
        );

        return orderService.save(
            Order.builder()
                .steppayId(response.getOrderId())
                .steepayOrderCode(response.getOrderCode())
                .paidAmount(response.getPaidAmount())
                .product(product)
                .member(member)
                .build()
        ).toResponse();
    }

    @Override
    public OrderResponse getById(String memberId, String orderId) {
        Member member = memberService.getMember(memberId);
        Order order = orderService.getById(orderId);

        if (!member.getId().equals(order.getMember().getId())) {
            throw new NotFoundOrderAuthorityException();
        }

        return order.toResponse();
    }

    @Override
    public List<OrderResponse> getListByMemberId(String memberId, int page, int take) {
        return orderService.getListByMemberId(memberId, page, take);
    }

    @Override
    public List<OrderResponse> getListByStoreId(
        String memberId,
        String storeId,
        int page,
        int take
    ) {
        Member member = memberService.getMember(memberId);
        Store store = storeService.getById(storeId);

        if (!member.getId().equals(store.getManager().getMember().getId())) {
            throw new NotManagerException();
        }

        return orderService.getListByStore(store, page, take);
    }

    @Override
    @Transactional
    public OrderResponse success(String memberId, String orderId) {
        Member member = memberService.getMember(memberId);
        Order order = orderService.getById(orderId);

        if (!member.getId().equals(order.getMember().getId())) {
            throw new NotFoundOrderAuthorityException();
        }

        return orderService.updateStatus(order, OrderType.SUCCESS).toResponse();
    }

    @Override
    @Transactional
    public OrderResponse cancel(String memberId, String orderId) {
        Member member = memberService.getMember(memberId);
        Order order = orderService.getById(orderId);

        if (!member.getId().equals(order.getMember().getId())) {
            throw new NotFoundOrderAuthorityException();
        }

        return orderService.updateStatus(order, OrderType.CANCEL).toResponse();
    }
}
