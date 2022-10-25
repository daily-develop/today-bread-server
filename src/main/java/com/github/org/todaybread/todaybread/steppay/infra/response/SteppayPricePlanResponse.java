package com.github.org.todaybread.todaybread.steppay.infra.response;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class SteppayPricePlanResponse {

    Integer id;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    String code;
    Integer price;
    String type;
    String claimMethodType;
}
