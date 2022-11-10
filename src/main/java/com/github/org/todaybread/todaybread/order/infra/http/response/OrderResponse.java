package com.github.org.todaybread.todaybread.order.infra.http.response;

import com.github.org.todaybread.todaybread.member.infra.http.response.MemberResponse;
import com.github.org.todaybread.todaybread.order.domain.Order;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderResponse {

    String id;
    Long paidAmount;
    ProductResponse product;
    MemberResponse member;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @Builder
    public OrderResponse(Order order) {
        this.id = order.getId().toString();
        this.product = order.getProduct().toResponse();
        this.member = order.getMember().toResponse();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
    }
}
