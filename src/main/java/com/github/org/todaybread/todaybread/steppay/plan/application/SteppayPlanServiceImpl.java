package com.github.org.todaybread.todaybread.steppay.plan.application;

import com.github.org.todaybread.todaybread.steppay.plan.infra.request.SteppayCreatePlanRequest;
import com.github.org.todaybread.todaybread.steppay.plan.infra.request.SteppayUpdatePlanRequest;
import com.github.org.todaybread.todaybread.steppay.plan.infra.response.SteppayPlanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class SteppayPlanServiceImpl implements SteppayPlanService {

    private final RestTemplate template;

    @Override
    public SteppayPlanResponse create(Long productId, SteppayCreatePlanRequest request) {
        return template.postForObject(
            "/products/" + productId + "/prices",
            request,
            SteppayPlanResponse.class
        );
    }

    @Override
    public SteppayPlanResponse update(
        Long productId,
        Long planId,
        SteppayUpdatePlanRequest request
    ) {
        return template.exchange(
            "/products/" + productId + "/prices/" + planId,
            HttpMethod.PUT,
            new HttpEntity<>(request),
            SteppayPlanResponse.class
        ).getBody();
    }
}
