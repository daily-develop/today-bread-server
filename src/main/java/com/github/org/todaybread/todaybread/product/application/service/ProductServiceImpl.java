package com.github.org.todaybread.todaybread.product.application.service;

import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.product.infra.persistence.ProductRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepositoryImpl productRepository;

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
