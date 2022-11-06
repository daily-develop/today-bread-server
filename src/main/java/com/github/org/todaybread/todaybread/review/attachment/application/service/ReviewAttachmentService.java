package com.github.org.todaybread.todaybread.review.attachment.application.service;

import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.review.attachment.domain.ReviewAttachment;
import com.github.org.todaybread.todaybread.review.domain.Review;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewResponse;
import java.util.List;

public interface ReviewAttachmentService {

    ReviewAttachment save(Review review, File file);

    List<ReviewAttachment> getByReviews(List<ReviewResponse> reviews);
}
