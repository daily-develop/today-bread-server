package com.github.org.todaybread.todaybread.steppay.product.infra.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayCreateProductRequest {

    String type;
    String status;
    String name;
    String featuredImageUrl;

    @Builder
    public SteppayCreateProductRequest(
        String name,
        String featuredImageUrl
    ) {
        this.type = "BOX";
        this.status = "SALE";
        this.name = name;
        this.featuredImageUrl = featuredImageUrl;
    }
}
