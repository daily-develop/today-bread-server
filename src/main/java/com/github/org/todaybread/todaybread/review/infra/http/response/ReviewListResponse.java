package com.github.org.todaybread.todaybread.review.infra.http.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewListResponse {

    List<ReviewResponse> content;

    Long totalElement;

    @Builder
    public ReviewListResponse(
        List<ReviewResponse> content,
        Long totalElement
    ) {
        this.content = content;
        this.totalElement = totalElement;
    }
}
