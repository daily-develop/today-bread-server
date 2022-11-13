package com.github.org.todaybread.todaybread.review.application.service;

import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.review.domain.Review;
import org.springframework.data.domain.Page;

public interface ReviewService {

    Review save(Review review);

    Review getById(String reviewId);

    Review getByMemberIdAndProductId(String memberId, String productId);

    Page<Review> getByProduct(Product product, int page, int take);

    Float getScoreByProduct(Product product);

    void delete(Review review);
}
