package com.github.org.todaybread.todaybread.review.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.review.attachment.domain.ReviewAttachment;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewResponse;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Core {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ReviewAttachment> attachments;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Float score;


    @Builder
    public Review(
        Member member,
        Product product,
        List<ReviewAttachment> attachments,
        String content,
        Float score
    ) {
        this.member = member;
        this.product = product;
        this.attachments = attachments;
        this.content = content;
        this.score = score;
    }

    public void uploadAttachment(List<ReviewAttachment> attachments) {
        this.attachments = attachments;
    }

    public ReviewResponse toResponse() {
        return ReviewResponse.builder()
            .review(this)
            .build();
    }
}
