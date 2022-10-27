package com.github.org.todaybread.todaybread.product.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface ProductRepository {

    Optional<Product> getById(String productId);

    List<Product> getList(String storeId, String search, Pageable pageable);

    Product save(Product product);
}
