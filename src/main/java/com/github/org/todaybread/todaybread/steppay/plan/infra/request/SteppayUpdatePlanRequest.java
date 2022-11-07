package com.github.org.todaybread.todaybread.steppay.plan.infra.request;

import com.github.org.todaybread.todaybread.steppay.plan.domain.SteppayPlanInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayUpdatePlanRequest {

    Long price;
    SteppayPlanInfo plan;

    @Builder
    public SteppayUpdatePlanRequest(Long price, SteppayPlanInfo plan) {
        this.price = price;
        this.plan = plan;
    }
}
