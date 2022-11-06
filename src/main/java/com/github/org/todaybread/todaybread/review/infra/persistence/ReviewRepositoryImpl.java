package com.github.org.todaybread.todaybread.review.infra.persistence;

import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.review.domain.QReview;
import com.github.org.todaybread.todaybread.review.domain.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {

    private final ReviewJpaRepository reviewRepository;
    private final JPAQueryFactory queryFactory;
    private final QReview review = QReview.review;

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Optional<Review> getById(String reviewId) {
        return reviewRepository.findById(UUID.fromString(reviewId));
    }

    @Override
    public Page<Review> getByProduct(Product product, Pageable pageable) {
        return reviewRepository.findByProduct(product, pageable);
    }

    @Override
    public Float getScoreByProduct(Product product) {
        return queryFactory
            .select(review.score.avg().floatValue())
            .from(review)
            .where(
                review.product.eq(product)
            )
            .fetchOne();
    }

    @Override
    public void delete(Review review) {
        reviewRepository.delete(review);
    }
}
