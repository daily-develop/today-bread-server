package com.github.org.todaybread.todaybread.steppay.plan.application;

import com.github.org.todaybread.todaybread.steppay.plan.infra.request.SteppayCreatePlanRequest;
import com.github.org.todaybread.todaybread.steppay.plan.infra.request.SteppayUpdatePlanRequest;
import com.github.org.todaybread.todaybread.steppay.plan.infra.response.SteppayPlanResponse;

public interface SteppayPlanService {

    SteppayPlanResponse create(Long productId, SteppayCreatePlanRequest request);

    SteppayPlanResponse update(Long productId, Long planId, SteppayUpdatePlanRequest request);
}
