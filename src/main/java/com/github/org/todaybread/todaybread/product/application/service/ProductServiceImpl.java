package com.github.org.todaybread.todaybread.product.application.service;

import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.product.domain.ProductRepository;
import com.github.org.todaybread.todaybread.product.exception.NotFoundProductException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getById(String productId) {
        return productRepository.getById(productId).orElseThrow(NotFoundProductException::new);
    }

    @Override
    public List<Product> getList(String storeId, int page, int take, String search) {
        return productRepository.getList(
            storeId,
            search,
            PageRequest.of(page - 1, take)
        );
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }
}