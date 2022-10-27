package com.github.org.todaybread.todaybread.product.application.service;

import com.github.org.todaybread.todaybread.product.domain.Product;
import java.util.List;

public interface ProductService {

    Product getById(String productId);

    List<Product> getList(String storeId, int page, int take, String search);

    Product save(Product product);
}
