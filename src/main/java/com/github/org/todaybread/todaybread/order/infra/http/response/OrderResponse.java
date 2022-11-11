package com.github.org.todaybread.todaybread.order.infra.http.response;

import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.member.infra.http.response.MemberResponse;
import com.github.org.todaybread.todaybread.order.domain.Order;
import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderResponse {

    String id;
    String steppayOrderCode;
    Long paidAmount;
    ProductResponse product;
    MemberResponse member;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public OrderResponse(
        UUID id,
        String steppayOrderCode,
        Long paidAmount,
        Product product,
        Member member,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id.toString();
        this.steppayOrderCode = steppayOrderCode;
        this.paidAmount = paidAmount;
        this.product = product.toResponse();
        this.member = member.toResponse();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Builder
    public OrderResponse(Order order) {
        this.id = order.getId().toString();
        this.steppayOrderCode = order.getSteepayOrderCode();
        this.paidAmount = order.getPaidAmount();
        this.product = order.getProduct().toResponse();
        this.member = order.getMember().toResponse();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
    }
}
