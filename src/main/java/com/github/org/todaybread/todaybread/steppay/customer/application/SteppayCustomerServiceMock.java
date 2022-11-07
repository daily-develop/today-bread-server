package com.github.org.todaybread.todaybread.steppay.customer.application;

import com.github.org.todaybread.todaybread.steppay.customer.infra.request.SteppayCreateCustomerRequest;
import com.github.org.todaybread.todaybread.steppay.customer.infra.response.SteppayCustomerResponse;
import java.time.LocalDateTime;

public class SteppayCustomerServiceMock implements SteppayCustomerService {

    @Override
    public SteppayCustomerResponse createCustomer(SteppayCreateCustomerRequest request) {
        return SteppayCustomerResponse.builder()
            .id((long) (Math.random() * 10_000))
            .createdAt(LocalDateTime.now())
            .modifiedAt(LocalDateTime.now())
            .name(request.getName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .build();
    }
}
