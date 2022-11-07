package com.github.org.todaybread.todaybread.steppay.customer.application;

import com.github.org.todaybread.todaybread.steppay.customer.infra.request.SteppayCreateCustomerRequest;
import com.github.org.todaybread.todaybread.steppay.customer.infra.response.SteppayCustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class SteppayCustomerServiceImpl implements SteppayCustomerService {

    private final RestTemplate template;

    @Override
    public SteppayCustomerResponse createCustomer(SteppayCreateCustomerRequest request) {
        return template.postForObject(
            "/customers",
            request,
            SteppayCustomerResponse.class
        );
    }

}
