package com.github.org.todaybread.todaybread.steppay.product.infra.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayUpdateProductRequest {

    String type;
    String status;
    String name;
    String featuredImageUrl;
    String description;

    @Builder
    public SteppayUpdateProductRequest(
        String name,
        String featuredImageUrl,
        String description
    ) {
        this.type = "BOX";
        this.status = "SALE";
        this.name = name;
        this.featuredImageUrl = featuredImageUrl;
        this.description = description;
    }
}
