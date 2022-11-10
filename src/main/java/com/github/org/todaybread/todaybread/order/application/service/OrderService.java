package com.github.org.todaybread.todaybread.order.application.service;

import com.github.org.todaybread.todaybread.order.domain.Order;
import java.util.List;

public interface OrderService {

    Order save(Order order);

    List<Order> getList(String memberId, int page, int take);

    Order getById(String orderId);
}
