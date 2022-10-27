package com.github.org.todaybread.todaybread.steppay.product.application;

import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayCreateProductRequest;
import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayUpdateProductRequest;
import com.github.org.todaybread.todaybread.steppay.product.infra.response.SteppayProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

    @Override
    public SteppayProductResponse update(Integer productId, SteppayUpdateProductRequest request) {
        return template.exchange(
            "/products/" + productId,
            HttpMethod.PUT,
            new HttpEntity<>(request),
            SteppayProductResponse.class
        ).getBody();
    }
}
