package com.github.org.todaybread.todaybread.product.infra.persistence;

import com.github.org.todaybread.todaybread.product.domain.Product;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, UUID> {

    List<Product> findByStatusOrderByScoreDesc(Boolean status, Pageable pageable);
}
