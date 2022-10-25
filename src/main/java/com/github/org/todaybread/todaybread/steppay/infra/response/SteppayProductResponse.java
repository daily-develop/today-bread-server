package com.github.org.todaybread.todaybread.steppay.infra.response;

import java.time.LocalDateTime;
import java.util.List;
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
}
