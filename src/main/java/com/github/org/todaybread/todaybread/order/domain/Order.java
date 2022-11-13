package com.github.org.todaybread.todaybread.order.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.order.infra.http.response.OrderResponse;
import com.github.org.todaybread.todaybread.product.domain.Product;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(columnDefinition = "varchar(255) default 'READY'")
    @Enumerated(EnumType.STRING)
    private OrderType status = OrderType.READY;

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

    public Order updateStatus(OrderType status) {
        this.status = status;
        return this;
    }

    public OrderResponse toResponse() {
        return OrderResponse.builder()
            .order(this)
            .build();
    }
}
