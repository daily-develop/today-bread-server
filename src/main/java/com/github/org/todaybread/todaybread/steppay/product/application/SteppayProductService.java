package com.github.org.todaybread.todaybread.steppay.product.application;


import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayCreateProductRequest;
import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayUpdateProductRequest;
import com.github.org.todaybread.todaybread.steppay.product.infra.response.SteppayProductResponse;

public interface SteppayProductService {

    SteppayProductResponse create(SteppayCreateProductRequest request);

    SteppayProductResponse update(Long productId, SteppayUpdateProductRequest request);

    SteppayProductResponse stop(Long productId);
}
