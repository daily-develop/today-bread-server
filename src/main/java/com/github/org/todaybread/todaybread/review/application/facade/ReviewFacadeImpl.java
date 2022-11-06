package com.github.org.todaybread.todaybread.review.application.facade;

import com.github.org.todaybread.todaybread.file.application.facade.FileFacadeImpl;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import com.github.org.todaybread.todaybread.file.infra.http.response.FileResponse;
import com.github.org.todaybread.todaybread.member.application.service.MemberServiceImpl;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.product.application.service.ProductServiceImpl;
import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.review.application.service.ReviewServiceImpl;
import com.github.org.todaybread.todaybread.review.attachment.application.service.ReviewAttachmentServiceImpl;
import com.github.org.todaybread.todaybread.review.attachment.domain.ReviewAttachment;
import com.github.org.todaybread.todaybread.review.domain.Review;
import com.github.org.todaybread.todaybread.review.exception.NotWriterException;
import com.github.org.todaybread.todaybread.review.infra.http.request.CreateReviewRequest;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewListResponse;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewResponse;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewFacadeImpl implements ReviewFacade {

    private final ReviewServiceImpl reviewService;
    private final MemberServiceImpl memberService;
    private final ProductServiceImpl productService;
    private final FileFacadeImpl fileFacade;
    private final ReviewAttachmentServiceImpl reviewAttachmentService;

    @Override
    @Transactional
    public ReviewResponse create(String memberId, CreateReviewRequest request) {
        Member member = memberService.getMember(memberId);
        Product product = productService.getById(request.getProductId());

        Review review = reviewService.save(
            Review.builder()
                .member(member)
                .product(product)
                .content(request.getContent())
                .score(request.getScore())
                .build()
        );
        product.updateScore(
            reviewService.getScoreByProduct(product)
        );

        if (request.getImages() != null) {
            List<File> files = fileFacade.uploads(
                memberId,
                FileType.REVIEW,
                request.getImages()
            );
            review.uploadAttachment(
                files.stream()
                    .map(it -> reviewAttachmentService.save(review, it))
                    .collect(Collectors.toList())
            );
        }

        return review.toResponse();
    }

    @Override
    public ReviewListResponse getList(String productId, int page, int take) {
        Product product = productService.getById(productId);
        Page<Review> reviews = reviewService.getByProduct(product, page, take);

        return ReviewListResponse.builder()
            .content(
                reviews.getContent().stream()
                    .map(Review::toResponse)
                    .collect(Collectors.toList())
            )
            .totalElement(reviews.getTotalElements())
            .build();
    }

    @Override
    @Transactional
    public Boolean delete(String memberId, String reviewId) {
        Member member = memberService.getMember(memberId);
        Review review = reviewService.getById(reviewId);

        if (!member.getId().equals(review.getMember().getId())) {
            throw new NotWriterException();
        }
        reviewService.delete(review);
        review.getProduct().updateScore(
            reviewService.getScoreByProduct(review.getProduct())
        );
        return true;
    }

    public Map<ReviewResponse, List<FileResponse>> attachmentsForReview(
        List<ReviewResponse> reviews
    ) {
        List<ReviewAttachment> attachments = reviewAttachmentService.getByReviews(reviews);

        return reviews.stream()
            .collect(
                Collectors.toMap(
                    Function.identity(),
                    review -> attachments.stream()
                        .filter(it -> it.getReview().getId().toString().equals(review.getId()))
                        .map(it -> it.getFile().toResponse())
                        .collect(Collectors.toList())
                )
            );
    }

}
