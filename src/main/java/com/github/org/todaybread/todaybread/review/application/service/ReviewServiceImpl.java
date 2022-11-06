package com.github.org.todaybread.todaybread.review.application.service;

import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.review.domain.Review;
import com.github.org.todaybread.todaybread.review.exception.NotFoundReviewException;
import com.github.org.todaybread.todaybread.review.infra.persistence.ReviewRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepositoryImpl reviewRepository;

    @Override
    @Transactional
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review getById(String reviewId) {
        return reviewRepository.getById(reviewId).orElseThrow(NotFoundReviewException::new);
    }

    @Override
    public Page<Review> getByProduct(Product product, int page, int take) {
        Pageable pageable = PageRequest.of(page - 1, take);
        return reviewRepository.getByProduct(product, pageable);
    }

    @Override
    @Transactional
    public void delete(Review review) {
        reviewRepository.delete(review);
    }
}
