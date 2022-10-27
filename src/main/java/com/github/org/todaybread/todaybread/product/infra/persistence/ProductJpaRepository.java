package com.github.org.todaybread.todaybread.product.infra.persistence;

import com.github.org.todaybread.todaybread.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<Product, UUID> {
}
