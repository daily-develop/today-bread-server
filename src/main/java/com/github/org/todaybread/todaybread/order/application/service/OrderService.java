package com.github.org.todaybread.todaybread.order.application.service;

import com.github.org.todaybread.todaybread.order.domain.Order;
import com.github.org.todaybread.todaybread.order.infra.http.response.OrderResponse;
import com.github.org.todaybread.todaybread.store.domain.Store;
import java.util.List;

public interface OrderService {

    String getOrderUrl(String orderCode);

    String getOrderSecret();

    Order save(Order order);

    Order getById(String orderId);

    Order getByMemberIdAndProductId(String memberId, String productId);

    List<Order> getList(String memberId, int page, int take);

    List<OrderResponse> getListByStore(Store store, int page, int take);
}
