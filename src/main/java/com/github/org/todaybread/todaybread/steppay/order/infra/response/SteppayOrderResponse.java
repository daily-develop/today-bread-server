package com.github.org.todaybread.todaybread.steppay.order.infra.response;

import com.github.org.todaybread.todaybread.steppay.customer.infra.response.SteppayCustomerResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SteppayOrderResponse {

    Long orderId;

    String orderCode;

    String type;

    Long paidAmount;

    List<SteppayItemResponse> items;

    SteppayCustomerResponse customer;

    SteppayShippingResponse shipping;

    LocalDateTime createdAt;

    LocalDateTime modifiedAt;


    @Builder
    public SteppayOrderResponse(
        Long orderId,
        String orderCode,
        String type,
        Long paidAmount,
        List<SteppayItemResponse> items,
        SteppayCustomerResponse customer,
        SteppayShippingResponse shipping,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
    ) {
        this.orderId = orderId;
        this.orderCode = orderCode;
        this.type = type;
        this.paidAmount = paidAmount;
        this.items = items;
        this.customer = customer;
        this.shipping = shipping;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
