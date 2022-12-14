package com.github.org.todaybread.todaybread.steppay.product.infra.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SteppayProductResponse {

    Long id;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    String code;
    String type;
    String status;
    String name;
    String featuredImageUrl;
    Long quantity;
    List<SteppayPricePlanResponse> prices;

    @Builder
    public SteppayProductResponse(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String code,
        String type,
        String status,
        String name,
        String featuredImageUrl,
        Long quantity,
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
        this.quantity = quantity;
        this.prices = prices;
    }
}
