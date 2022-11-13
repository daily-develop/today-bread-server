package com.github.org.todaybread.todaybread.review.infra.persistence;

import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.review.domain.Review;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepository {

    Review save(Review review);

    Optional<Review> getById(String reviewId);

    Optional<Review> getByMemberIdAndProductId(String memberId, String productId);

    Page<Review> getByProduct(Product product, Pageable pageable);

    Float getScoreByProduct(Product product);

    void delete(Review review);
}
