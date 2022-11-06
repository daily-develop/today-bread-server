package com.github.org.todaybread.todaybread.review.attachment.infra.persistence;

import com.github.org.todaybread.todaybread.review.attachment.domain.ReviewAttachment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewAttachmentRepositoryImpl implements ReviewAttachmentRepository {

    private final ReviewAttachmentJpaRepository reviewAttachmentRepository;

    @Override
    public ReviewAttachment save(ReviewAttachment reviewAttachment) {
        return reviewAttachmentRepository.save(reviewAttachment);
    }
}
