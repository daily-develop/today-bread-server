package com.github.org.todaybread.todaybread.steppay.order.application;

import com.github.org.todaybread.todaybread.steppay.order.infra.request.SteppayCreateOrderRequest;
import com.github.org.todaybread.todaybread.steppay.order.infra.response.SteppayOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class SteppayOrderServiceImpl implements SteppayOrderService {

    private final RestTemplate template;

    @Override
    public SteppayOrderResponse create(SteppayCreateOrderRequest request) {
        return template.postForObject(
            "/orders",
            request,
            SteppayOrderResponse.class
        );
    }
}
