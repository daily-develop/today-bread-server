package com.github.org.todaybread.todaybread.review.attachment.infra.persistence;

import com.github.org.todaybread.todaybread.review.attachment.domain.ReviewAttachment;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewAttachmentJpaRepository extends JpaRepository<ReviewAttachment, UUID> {

    List<ReviewAttachment> findByReviewId(UUID reviewId);
}
