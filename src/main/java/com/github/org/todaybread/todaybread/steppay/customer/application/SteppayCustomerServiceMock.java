package com.github.org.todaybread.todaybread.steppay.customer.application;

import com.github.org.todaybread.todaybread.steppay.customer.infra.request.SteppayCreateCustomerRequest;
import com.github.org.todaybread.todaybread.steppay.customer.infra.response.SteppayCustomerResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SteppayCustomerServiceMock implements SteppayCustomerService {

    private final List<SteppayCustomerResponse> cache = new ArrayList<>();

    @Override
    public SteppayCustomerResponse createCustomer(SteppayCreateCustomerRequest request) {
        SteppayCustomerResponse response = SteppayCustomerResponse.builder()
            .id((long) (Math.random() * 10_000))
            .createdAt(LocalDateTime.now())
            .modifiedAt(LocalDateTime.now())
            .name(request.getName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .build();

        cache.add(response);

        return cache.get(0);
    }

    public SteppayCustomerResponse get() {
        return cache.get(0);
    }
}
