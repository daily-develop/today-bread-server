package com.github.org.todaybread.todaybread.steppay.plan.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayRecurringInfo {

    Long intervalCount;
    String interval;
    String aggregateUsageType;
    String usageType;

    @Builder
    public SteppayRecurringInfo() {
        this.intervalCount = 1L;
        this.interval = "MONTH";
        this.aggregateUsageType = "SUM";
        this.usageType = "LICENSED";
    }
}
