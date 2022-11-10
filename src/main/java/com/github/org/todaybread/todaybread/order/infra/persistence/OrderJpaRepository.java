package com.github.org.todaybread.todaybread.order.infra.persistence;

import com.github.org.todaybread.todaybread.order.domain.Order;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, UUID> {

    List<Order> findByMemberIdOrderByCreatedAt(UUID memberId, Pageable pageable);
}
