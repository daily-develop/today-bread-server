package com.github.org.todaybread.todaybread.review.infra.http.request;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class CreateReviewRequest {

    @NotBlank
    String productId;

    List<MultipartFile> images;

    String content;

    Float score;

    @Builder
    public CreateReviewRequest(
        String productId,
        List<MultipartFile> images,
        String content,
        Float score
    ) {
        this.productId = productId;
        this.images = images;
        this.content = content;
        this.score = score;
    }
}
