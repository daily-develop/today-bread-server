package com.github.org.todaybread.todaybread.review.application.facade;

import com.github.org.todaybread.todaybread.file.infra.http.response.FileResponse;
import com.github.org.todaybread.todaybread.review.infra.http.request.CreateReviewRequest;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewListResponse;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewResponse;
import java.util.List;
import java.util.Map;

public interface ReviewFacade {

    ReviewResponse create(String memberId, CreateReviewRequest request);

    ReviewListResponse getList(String productId, int page, int take);

    Boolean delete(String memberId, String reviewId);

    Map<ReviewResponse, List<FileResponse>> attachmentsForReview(List<ReviewResponse> reviews);
}
