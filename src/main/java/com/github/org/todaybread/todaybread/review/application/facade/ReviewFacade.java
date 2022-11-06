package com.github.org.todaybread.todaybread.review.application.facade;

import com.github.org.todaybread.todaybread.review.infra.http.request.CreateReviewRequest;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewListResponse;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewResponse;

public interface ReviewFacade {

    ReviewResponse create(String memberId, CreateReviewRequest request);

    ReviewListResponse getList(String productId, int page, int take);

    Boolean delete(String memberId, String reviewId);
}
