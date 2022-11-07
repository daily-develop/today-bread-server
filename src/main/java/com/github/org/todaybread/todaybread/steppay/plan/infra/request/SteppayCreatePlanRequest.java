package com.github.org.todaybread.todaybread.steppay.plan.infra.request;

import com.github.org.todaybread.todaybread.steppay.plan.domain.SteppayPlanInfo;
import com.github.org.todaybread.todaybread.steppay.plan.domain.SteppayRecurringInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayCreatePlanRequest {

    Long price;
    String unit;
    SteppayPlanInfo plan;
    String type;
    SteppayRecurringInfo recurring;

    @Builder
    public SteppayCreatePlanRequest(Long price, SteppayPlanInfo plan) {
        this.price = price;
        this.unit = "개";
        this.plan = plan;
        this.type = "FLAT";
        this.recurring = SteppayRecurringInfo.builder().build();
    }
}
