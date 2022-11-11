package com.github.org.todaybread.todaybread.steppay.product.infra.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayCreateProductRequest {

    String type;
    String status;
    String name;
    String featuredImageUrl;
    Long quantity;

    @Builder
    public SteppayCreateProductRequest(
        String name,
        String featuredImageUrl,
        Long quantity
    ) {
        this.type = "BOX";
        this.status = "SALE";
        this.name = name;
        this.featuredImageUrl = featuredImageUrl;
        this.quantity = quantity;
    }
}
