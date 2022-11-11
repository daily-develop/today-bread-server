package com.github.org.todaybread.todaybread.steppay.plan.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SteppayPlanInfo {

    String name;
    String description;

    @Builder
    public SteppayPlanInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
