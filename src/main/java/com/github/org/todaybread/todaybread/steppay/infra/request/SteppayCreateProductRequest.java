package com.github.org.todaybread.todaybread.steppay.infra.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayCreateProductRequest {

    String type;
    String status;
    String name;
    String featuredImageUrl;
    String description;

    @Builder
    public SteppayCreateProductRequest(
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
