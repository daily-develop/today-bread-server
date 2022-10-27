package com.github.org.todaybread.todaybread.product.application.facade;

import com.github.org.todaybread.todaybread.product.infra.http.request.CreateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.request.UpdateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import java.util.List;

public interface ProductFacade {

    ProductResponse getById(String productId);

    List<ProductResponse> getList(String storeId, int page, int take, String search);

    ProductResponse create(String memberId, CreateProductRequest request);

    ProductResponse update(String memberId, UpdateProductRequest request);
}
