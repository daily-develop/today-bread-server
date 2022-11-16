package com.github.org.todaybread.todaybread.product.application.facade;

import com.github.org.todaybread.todaybread.product.domain.BreadType;
import com.github.org.todaybread.todaybread.product.infra.http.request.CreateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.request.UpdateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import java.util.List;

public interface ProductFacade {

    ProductResponse getById(String productId);

    List<ProductResponse> getList(String storeId, BreadType breadType, Boolean saleOnly, int page,
        int take);

    List<ProductResponse> getRecommended(int take);

    List<ProductResponse> getRanking(int page, int take);

    ProductResponse create(String memberId, CreateProductRequest request);

    ProductResponse update(String memberId, UpdateProductRequest request);

    ProductResponse stop(String memberId, String productId);
}
