package com.github.org.todaybread.todaybread.steppay.order.infra.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SteppayItemResponse {

    Long id;

    Long paidAmount;

    String currency;

    Long quantity;

    String status;

    String orderItemCode;

    String orderedProductName;


    @Builder
    public SteppayItemResponse(
        Long id,
        Long paidAmount,
        String status,
        String orderItemCode,
        String orderedProductName
    ) {
        this.id = id;
        this.paidAmount = paidAmount;
        this.currency = "KRW";
        this.quantity = 1L;
        this.status = status;
        this.orderItemCode = orderItemCode;
        this.orderedProductName = orderedProductName;
    }
}
