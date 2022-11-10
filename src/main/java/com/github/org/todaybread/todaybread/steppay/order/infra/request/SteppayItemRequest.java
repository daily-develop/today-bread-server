package com.github.org.todaybread.todaybread.steppay.order.infra.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayItemRequest {

    String productCode;

    String priceCode;

    String currency;

    Integer minimumQuantity;

    @Builder
    public SteppayItemRequest(
        String productCode,
        String priceCode
    ) {
        this.productCode = productCode;
        this.priceCode = priceCode;
        this.currency = "KRW";
        this.minimumQuantity = 1;
    }
}
