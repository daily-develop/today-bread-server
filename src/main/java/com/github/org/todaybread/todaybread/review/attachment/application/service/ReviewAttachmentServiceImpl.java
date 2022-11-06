package com.github.org.todaybread.todaybread.review.attachment.application.service;

import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.review.attachment.domain.ReviewAttachment;
import com.github.org.todaybread.todaybread.review.attachment.infra.persistence.ReviewAttachmentRepositoryImpl;
import com.github.org.todaybread.todaybread.review.domain.Review;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewAttachmentServiceImpl implements ReviewAttachmentService {

    private final ReviewAttachmentRepositoryImpl reviewAttachmentRepository;

    @Override
    @Transactional
    public ReviewAttachment save(Review review, File file) {
        return reviewAttachmentRepository.save(
            ReviewAttachment.builder()
                .review(review)
                .file(file)
                .build()
        );
    }

    @Override
    public List<ReviewAttachment> getByReviewIds(List<UUID> reviewIds) {
        return reviewAttachmentRepository.getByReviewIds(reviewIds);
    }
}
