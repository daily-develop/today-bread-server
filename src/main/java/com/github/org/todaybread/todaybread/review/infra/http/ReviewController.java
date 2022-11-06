package com.github.org.todaybread.todaybread.review.infra.http;

import com.github.org.todaybread.todaybread.file.infra.http.response.FileResponse;
import com.github.org.todaybread.todaybread.review.application.facade.ReviewFacadeImpl;
import com.github.org.todaybread.todaybread.review.infra.http.request.CreateReviewRequest;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewListResponse;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewResponse;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@PreAuthorize(value = "hasAuthority('GENERAL')")
public class ReviewController {

    private final ReviewFacadeImpl reviewFacade;

    @QueryMapping
    public ReviewListResponse reviews(
        @Argument String productId,
        @Argument int page,
        @Argument int take
    ) {
        return reviewFacade.getList(productId, page, take);
    }

    @MutationMapping
    public ReviewResponse createReview(
        Authentication authentication,
        @Valid @Argument CreateReviewRequest request
    ) {
        return reviewFacade.create(authentication.getName(), request);
    }

    @MutationMapping
    public Boolean deleteReview(
        Authentication authentication,
        @Argument String reviewId
    ) {
        return reviewFacade.delete(authentication.getName(), reviewId);
    }

    @BatchMapping(field = "attachment", typeName = "Review")
    public Map<ReviewResponse, List<FileResponse>> attachment(
        List<ReviewResponse> reviews
    ) {
        return reviewFacade.attachmentsForReview(reviews);
    }
}
