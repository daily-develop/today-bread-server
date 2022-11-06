package com.github.org.todaybread.todaybread.review.attachment.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.review.attachment.infra.http.response.ReviewAttachmentResponse;
import com.github.org.todaybread.todaybread.review.domain.Review;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewAttachment extends Core {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;

    @Builder
    public ReviewAttachment(
        Review review,
        File file
    ) {
        this.review = review;
        this.file = file;
    }

    public ReviewAttachmentResponse toResponse() {
        return ReviewAttachmentResponse.builder()
            .reviewAttachment(this)
            .build();
    }
}
