package com.github.org.todaybread.todaybread.steppay.product.infra.response;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class SteppayPricePlanResponse {

    Long id;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    String code;
    Long price;
    String type;
    String claimMethodType;
}
