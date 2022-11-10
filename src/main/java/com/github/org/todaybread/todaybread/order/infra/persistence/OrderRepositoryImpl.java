package com.github.org.todaybread.todaybread.order.infra.persistence;

import com.github.org.todaybread.todaybread.order.domain.Order;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getByMemberId(String memberId, Pageable pageable) {
        return orderRepository.findByMemberIdOrderByCreatedAt(UUID.fromString(memberId), pageable);
    }

    @Override
    public Optional<Order> getById(String orderId) {
        return orderRepository.findById(UUID.fromString(orderId));
    }
}
