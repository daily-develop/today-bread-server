package com.github.org.todaybread.todaybread.order.infra.persistence;

import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.order.domain.Order;
import com.github.org.todaybread.todaybread.order.domain.OrderType;
import com.github.org.todaybread.todaybread.order.infra.http.response.OrderResponse;
import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.store.domain.Store;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> getById(String orderId);

    List<Order> getByMemberId(String memberId, Pageable pageable);

    List<Order> getByProductId(String productId);

    List<OrderResponse> getByStore(Store findStore, Pageable pageable);

    Optional<Order> getByMemberAndProduct(Member member, Product product);

    List<OrderResponse> getByMemberIdAndStatus(
        String memberId,
        OrderType status,
        Boolean productStatus,
        Pageable pageable
    );

    Optional<Order> getByMemberIdAndProductIdAndStatus(String memberId, String productId, OrderType status);

}
