package com.github.org.todaybread.todaybread.order.infra.persistence;

import com.github.org.todaybread.todaybread.order.domain.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface OrderRepository {

    Order save(Order order);

    List<Order> getByMemberId(String memberId, Pageable pageable);

    Optional<Order> getById(String orderId);
}
