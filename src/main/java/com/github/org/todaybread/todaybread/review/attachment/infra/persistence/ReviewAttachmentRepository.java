package com.github.org.todaybread.todaybread.review.attachment.infra.persistence;

import com.github.org.todaybread.todaybread.review.attachment.domain.ReviewAttachment;

public interface ReviewAttachmentRepository {

    ReviewAttachment save(ReviewAttachment reviewAttachment);
}
