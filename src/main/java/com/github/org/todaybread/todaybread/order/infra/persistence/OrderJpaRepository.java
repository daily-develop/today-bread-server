package com.github.org.todaybread.todaybread.order.infra.persistence;

import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.order.domain.Order;
import com.github.org.todaybread.todaybread.order.domain.OrderType;
import com.github.org.todaybread.todaybread.product.domain.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findByMemberAndProduct(Member member, Product product);

    Optional<Order> findByMemberIdAndProductIdAndStatus(
        UUID memberId,
        UUID productId,
        OrderType status
    );

    List<Order> findByProductId(UUID productId);

    List<Order> findByMemberIdOrderByCreatedAt(UUID memberId, Pageable pageable);

    List<Order> findByMemberIdAndStatusOrderByCreatedAtDesc(UUID memberId, OrderType status,
        Pageable pageable);

}
