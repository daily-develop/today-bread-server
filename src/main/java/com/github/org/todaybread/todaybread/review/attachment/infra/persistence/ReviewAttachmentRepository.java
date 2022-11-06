package com.github.org.todaybread.todaybread.review.attachment.infra.persistence;

import com.github.org.todaybread.todaybread.review.attachment.domain.ReviewAttachment;
import java.util.List;
import java.util.UUID;

public interface ReviewAttachmentRepository {

    ReviewAttachment save(ReviewAttachment reviewAttachment);

    List<ReviewAttachment> getByReviewIds(List<UUID> reviewIds);
}
