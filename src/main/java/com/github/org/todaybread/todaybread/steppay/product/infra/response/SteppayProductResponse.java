package com.github.org.todaybread.todaybread.steppay.product.infra.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayProductResponse {

    Integer id;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    String code;
    String type;
    String status;
    String name;
    String featuredImageUrl;
    String description;
    List<SteppayPricePlanResponse> prices;

    @Builder
    public SteppayProductResponse(
        Integer id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String code,
        String type,
        String status,
        String name,
        String featuredImageUrl,
        String description,
        List<SteppayPricePlanResponse> prices
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.code = code;
        this.type = type;
        this.status = status;
        this.name = name;
        this.featuredImageUrl = featuredImageUrl;
        this.description = description;
        this.prices = prices;
    }
}
