package com.github.org.todaybread.todaybread.steppay.product.infra.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayUpdateProductRequest {

    String name;
    String featuredImageUrl;

    @Builder
    public SteppayUpdateProductRequest(
        String name,
        String featuredImageUrl
    ) {
        this.name = name;
        this.featuredImageUrl = featuredImageUrl;
    }
}
