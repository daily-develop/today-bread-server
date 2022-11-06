package com.github.org.todaybread.todaybread.review.attachment.infra.http.response;

import com.github.org.todaybread.todaybread.file.infra.http.response.FileResponse;
import com.github.org.todaybread.todaybread.review.attachment.domain.ReviewAttachment;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewAttachmentResponse {

    String id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    FileResponse fileUrl;

    @Builder
    public ReviewAttachmentResponse(ReviewAttachment reviewAttachment) {
        this.id = reviewAttachment.getId().toString();
        this.createdAt = reviewAttachment.getCreatedAt();
        this.updatedAt = reviewAttachment.getUpdatedAt();
        this.fileUrl =
            reviewAttachment.getFile() != null ? reviewAttachment.getFile().toResponse() : null;
    }
}
