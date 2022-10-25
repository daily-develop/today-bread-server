package com.github.org.todaybread.todaybread.steppay.application;


import com.github.org.todaybread.todaybread.steppay.infra.request.SteppayCreateProductRequest;
import com.github.org.todaybread.todaybread.steppay.infra.response.SteppayProductResponse;

public interface SteppayProductService {

    SteppayProductResponse create(SteppayCreateProductRequest request);
}
