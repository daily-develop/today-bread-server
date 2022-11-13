package com.github.org.todaybread.todaybread.review.infra.http.response;

import com.github.org.todaybread.todaybread.file.infra.http.response.FileResponse;
import com.github.org.todaybread.todaybread.member.infra.http.response.MemberResponse;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import com.github.org.todaybread.todaybread.review.domain.Review;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewResponse {

    String id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    MemberResponse member;
    ProductResponse product;
    Float score;
    String content;
    List<FileResponse> attachment;

    @Builder
    public ReviewResponse(Review review) {
        this.id = review.getId().toString();
        this.createdAt = review.getCreatedAt();
        this.updatedAt = review.getUpdatedAt();
        this.member = review.getMember().toResponse();
        this.product = review.getProduct().toResponse();
        this.score = review.getScore();
        this.content = review.getContent();
        this.attachment = review.getAttachments().stream()
            .map(it -> it.getFile().toResponse())
            .collect(Collectors.toList());
    }
}
