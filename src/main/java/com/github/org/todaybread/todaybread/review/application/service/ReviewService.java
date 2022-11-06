package com.github.org.todaybread.todaybread.review.application.service;

import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.review.domain.Review;
import org.springframework.data.domain.Page;

public interface ReviewService {

    Review save(Review review);

    Review getById(String reviewId);


    Page<Review> getByProduct(Product product, int page, int take);

    void delete(Review review);
}
