package com.github.org.todaybread.todaybread.order.application.service;

import com.github.org.todaybread.todaybread.order.domain.Order;
import com.github.org.todaybread.todaybread.order.exception.NotFoundOrderException;
import com.github.org.todaybread.todaybread.order.infra.http.response.OrderResponse;
import com.github.org.todaybread.todaybread.order.infra.persistence.OrderRepositoryImpl;
import com.github.org.todaybread.todaybread.store.domain.Store;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepositoryImpl orderRepository;

    @Value("${steppay.secret}")
    private String secretKey;

    @Override
    public String getOrderUrl(String orderCode) {
        return "https://api.steppay.kr/api/v1/orders/" + orderCode + "/pay";
    }

    @Override
    public String getOrderSecret() {
        return secretKey;
    }

    @Override
    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getById(String orderId) {
        return orderRepository.getById(orderId).orElseThrow(NotFoundOrderException::new);
    }

    @Override
    public Order getByMemberIdAndProductId(String memberId, String productId) {
        return orderRepository.getByMemberIdAndProductId(memberId, productId).orElse(null);
    }

    @Override
    public List<Order> getList(String memberId, int page, int take) {
        return orderRepository.getByMemberId(
            memberId,
            PageRequest.of(page - 1, take)
        );
    }

    @Override
    public List<OrderResponse> getListByStore(Store store, int page, int take) {
        return orderRepository.getByStore(
            store,
            PageRequest.of(page - 1, take)
        );
    }
}
