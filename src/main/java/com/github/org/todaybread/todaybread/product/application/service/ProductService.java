package com.github.org.todaybread.todaybread.product.application.service;

import com.github.org.todaybread.todaybread.product.domain.BreadType;
import com.github.org.todaybread.todaybread.product.domain.Product;
import java.util.List;

public interface ProductService {

    Product getById(String productId);

    List<Product> getList(String storeId, BreadType type, int page, int take);

    List<Product> getRecommended(int take);

    Product save(Product product);
}
