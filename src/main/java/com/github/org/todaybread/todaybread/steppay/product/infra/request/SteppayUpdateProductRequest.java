package com.github.org.todaybread.todaybread.steppay.product.infra.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayUpdateProductRequest {

    String name;
    String featuredImageUrl;
    Long quantity;

    @Builder
    public SteppayUpdateProductRequest(
        String name,
        String featuredImageUrl,
        Long quantity
    ) {
        this.name = name;
        this.featuredImageUrl = featuredImageUrl;
        this.quantity = quantity;
    }
}
