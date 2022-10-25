package com.github.org.todaybread.todaybread.product.application.facade;

import com.github.org.todaybread.todaybread.product.infra.http.request.CreateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;

public interface ProductFacade {

    ProductResponse create(String memberId, CreateProductRequest productRequest);
}
