package com.github.org.todaybread.todaybread.review.attachment.infra.persistence;

import com.github.org.todaybread.todaybread.review.attachment.domain.ReviewAttachment;
import java.util.List;

public interface ReviewAttachmentRepository {

    ReviewAttachment save(ReviewAttachment reviewAttachment);

    List<ReviewAttachment> getByReviewsIds(List<String> reviewIds);
}
