package com.github.org.todaybread.todaybread.order.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.order.infra.http.response.OrderResponse;
import com.github.org.todaybread.todaybread.product.domain.Product;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends Core {

    @Column(unique = true, nullable = false)
    private Long steppayId;

    @Column(nullable = false)
    private String steepayOrderCode;

    @Column(nullable = false)
    private Long paidAmount;

    @OneToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    private Product product;

    @OneToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public Order(
        Long steppayId,
        String steepayOrderCode,
        Long paidAmount,
        Product product,
        Member member
    ) {
        this.steppayId = steppayId;
        this.steepayOrderCode = steepayOrderCode;
        this.paidAmount = paidAmount;
        this.product = product;
        this.member = member;
    }

    public OrderResponse toResponse() {
        return OrderResponse.builder()
            .order(this)
            .build();
    }
}
