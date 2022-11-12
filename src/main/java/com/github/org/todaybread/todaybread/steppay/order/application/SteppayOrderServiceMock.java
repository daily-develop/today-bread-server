package com.github.org.todaybread.todaybread.steppay.order.application;

import com.github.org.todaybread.todaybread.steppay.customer.application.SteppayCustomerServiceMock;
import com.github.org.todaybread.todaybread.steppay.customer.infra.response.SteppayCustomerResponse;
import com.github.org.todaybread.todaybread.steppay.order.infra.request.SteppayCreateOrderRequest;
import com.github.org.todaybread.todaybread.steppay.order.infra.response.SteppayItemResponse;
import com.github.org.todaybread.todaybread.steppay.order.infra.response.SteppayOrderResponse;
import com.github.org.todaybread.todaybread.steppay.order.infra.response.SteppayShippingResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SteppayOrderServiceMock implements SteppayOrderService {

    private final Map<Long, SteppayOrderResponse> cache = new HashMap<>();
    private final SteppayCustomerServiceMock steppayCustomerServiceMock;

    @Override
    public SteppayOrderResponse create(SteppayCreateOrderRequest request) {
        SteppayCustomerResponse customer = steppayCustomerServiceMock.get();

        SteppayOrderResponse response = SteppayOrderResponse.builder()
            .orderId((long) (Math.random() * 10_000))
            .orderCode(UUID.randomUUID().toString())
            .type("RECURRING")
            .items(
                List.of(
                    SteppayItemResponse.builder()
                        .id((long) (Math.random() * 10_000))
                        .orderItemCode(UUID.randomUUID().toString())
                        .paidAmount((long) (Math.random() * 10_000))
                        .orderedProductName("product")
                        .status("status")
                        .build()
                )
            )
            .customer(customer)
            .shipping(
                SteppayShippingResponse.builder()
                    .name(customer.getName())
                    .phone(customer.getPhone())
                    .postcode("12345")
                    .address1("서울시 강남구")
                    .address2("삼성동 245번지 999")
                    .build()
            )
            .createdAt(LocalDateTime.now())
            .modifiedAt(LocalDateTime.now())
            .build();

        cache.put(response.getOrderId(), response);

        return cache.get(response.getOrderId());
    }

}
