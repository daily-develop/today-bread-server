package com.github.org.todaybread.todaybread.steppay.plan.infra.response;

import com.github.org.todaybread.todaybread.steppay.plan.domain.SteppayPlanInfo;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SteppayPlanResponse {

    Long id;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    String code;
    Long price;
    String unit;
    String type;
    SteppayPlanInfo plan;

    @Builder
    public SteppayPlanResponse(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String code,
        Long price,
        String unit,
        String type,
        SteppayPlanInfo plan
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.code = code;
        this.price = price;
        this.unit = unit;
        this.type = type;
        this.plan = plan;
    }
}
