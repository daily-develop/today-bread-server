package com.github.org.todaybread.todaybread.steppay.plan.application;

import com.github.org.todaybread.todaybread.steppay.plan.infra.request.SteppayCreatePlanRequest;
import com.github.org.todaybread.todaybread.steppay.plan.infra.request.SteppayUpdatePlanRequest;
import com.github.org.todaybread.todaybread.steppay.plan.infra.response.SteppayPlanResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SteppayPlanServiceMock implements SteppayPlanService {

    private final Map<Long, SteppayPlanResponse> cache = new HashMap<>();

    @Override
    public SteppayPlanResponse create(Long productId, SteppayCreatePlanRequest request) {
        SteppayPlanResponse response = SteppayPlanResponse.builder()
            .id((long) (Math.random() * 10_000))
            .createdAt(LocalDateTime.now())
            .modifiedAt(LocalDateTime.now())
            .code(UUID.randomUUID().toString())
            .price(request.getPrice())
            .unit(request.getUnit())
            .type(request.getType())
            .plan(request.getPlan())
            .build();

        cache.put(productId, response);

        return cache.get(productId);
    }

    @Override
    public SteppayPlanResponse update(
        Long productId,
        Long planId,
        SteppayUpdatePlanRequest request
    ) {
        SteppayPlanResponse cached = cache.get(productId);

        cache.put(
            productId,
            SteppayPlanResponse.builder()
                .id(cached.getId())
                .createdAt(cached.getCreatedAt())
                .modifiedAt(LocalDateTime.now())
                .code(cached.getCode())
                .price(request.getPrice())
                .unit(cached.getUnit())
                .type(cached.getType())
                .plan(request.getPlan())
                .build()
        );

        return cache.get(productId);
    }
}
