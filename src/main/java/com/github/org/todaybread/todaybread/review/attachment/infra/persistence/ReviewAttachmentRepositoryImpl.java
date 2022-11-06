package com.github.org.todaybread.todaybread.review.attachment.infra.persistence;

import com.github.org.todaybread.todaybread.review.attachment.domain.QReviewAttachment;
import com.github.org.todaybread.todaybread.review.attachment.domain.ReviewAttachment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewAttachmentRepositoryImpl implements ReviewAttachmentRepository {

    private final ReviewAttachmentJpaRepository reviewAttachmentRepository;
    private final JPAQueryFactory queryFactory;
    private final QReviewAttachment reviewAttachment = QReviewAttachment.reviewAttachment;


    @Override
    public ReviewAttachment save(ReviewAttachment reviewAttachment) {
        return reviewAttachmentRepository.save(reviewAttachment);
    }

    @Override
    public List<ReviewAttachment> getByReviewsIds(List<String> reviewIds) {
        List<UUID> ids = reviewIds.stream()
            .map(UUID::fromString)
            .collect(Collectors.toList());

        return queryFactory
            .selectFrom(reviewAttachment)
            .where(reviewAttachment.review.id.in(ids))
            .fetch();
    }
}
