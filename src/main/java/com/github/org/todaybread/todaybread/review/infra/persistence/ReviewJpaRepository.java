package com.github.org.todaybread.todaybread.review.infra.persistence;

import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.review.domain.Review;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepository extends JpaRepository<Review, UUID> {

    Optional<Review> findByMemberIdAndProductId(UUID memberId, UUID productId);

    Page<Review> findByProductOrderByCreatedAtDesc(Product product, Pageable pageable);
}
