package com.github.org.todaybread.todaybread.steppay.product.application;

import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayCloseProductRequest;
import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayCreateProductRequest;
import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayUpdateProductRequest;
import com.github.org.todaybread.todaybread.steppay.product.infra.response.SteppayProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
public class SteppayProductServiceImpl implements SteppayProductService {

    private final RestTemplate template;

    @Override
    public SteppayProductResponse create(SteppayCreateProductRequest request) {
        return template.postForObject(
            "/products",
            request,
            SteppayProductResponse.class
        );
    }

    @Override
    public SteppayProductResponse update(Long productId, SteppayUpdateProductRequest request) {
        return template.exchange(
            "/products/" + productId,
            HttpMethod.PUT,
            new HttpEntity<>(request),
            SteppayProductResponse.class
        ).getBody();
    }

    @Override
    public SteppayProductResponse stop(Long productId) {
        return template.exchange(
            "/products/" + productId,
            HttpMethod.PUT,
            new HttpEntity<>(new SteppayCloseProductRequest()),
            SteppayProductResponse.class
        ).getBody();
    }
}
