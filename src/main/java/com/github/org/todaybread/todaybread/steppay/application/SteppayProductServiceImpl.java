package com.github.org.todaybread.todaybread.steppay.application;

import com.github.org.todaybread.todaybread.steppay.infra.request.SteppayCreateProductRequest;
import com.github.org.todaybread.todaybread.steppay.infra.response.SteppayProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SteppayProductServiceImpl implements SteppayProductService {

    private final RestTemplate template;

    public SteppayProductResponse create(SteppayCreateProductRequest request) {
        return template.postForObject(
            "/products",
            request,
            SteppayProductResponse.class
        );
    }
}