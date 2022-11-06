package com.github.org.todaybread.todaybread.review.infra.http.response;

import com.github.org.todaybread.todaybread.file.infra.http.response.FileResponse;
import com.github.org.todaybread.todaybread.review.domain.Review;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewResponse {

    String id;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    List<FileResponse> attachment;
    Float score;

    String content;

    @Builder
    public ReviewResponse(Review review) {
        this.id = review.getId().toString();
        this.createdAt = review.getCreatedAt();
        this.updatedAt = review.getUpdatedAt();
        this.attachment = review.getAttachments() != null ?
            review.getAttachments().stream()
                .map(it -> it.getFile().toResponse())
                .toList()
            : List.of();
        this.score = review.getScore();
        this.content = review.getContent();
    }
}
