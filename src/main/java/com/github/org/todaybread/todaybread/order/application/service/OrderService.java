package com.github.org.todaybread.todaybread.order.application.service;

import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.order.domain.Order;
import com.github.org.todaybread.todaybread.order.domain.OrderType;
import com.github.org.todaybread.todaybread.order.infra.http.response.OrderResponse;
import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.store.domain.Store;
import java.util.List;

public interface OrderService {

    String getOrderUrl(String orderCode);

    String getOrderSecret();

    Order save(Order order);

    Order getById(String orderId);

    Order getByMemberAndProduct(Member member, Product product);

    Order getByMemberIdAndProductIdAndSuccess(String memberId, String productId);

    List<Order> getListByMemberId(String memberId, int page, int take);

    List<Order> getListByProductId(String productId);

    List<OrderResponse> getListByStore(Store store, int page, int take);

    Order updateStatus(Order order, OrderType status);
}
