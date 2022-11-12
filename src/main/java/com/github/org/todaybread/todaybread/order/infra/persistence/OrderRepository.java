package com.github.org.todaybread.todaybread.order.infra.persistence;

import com.github.org.todaybread.todaybread.order.domain.Order;
import com.github.org.todaybread.todaybread.order.infra.http.response.OrderResponse;
import com.github.org.todaybread.todaybread.store.domain.Store;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> getByMemberIdAndProductId(String memberId, String productId);

    Optional<Order> getById(String orderId);

    List<Order> getByMemberId(String memberId, Pageable pageable);

    List<OrderResponse> getByStore(Store findStore, Pageable pageable);
}
