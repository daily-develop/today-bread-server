package com.github.org.todaybread.todaybread.order.application.service;

import com.github.org.todaybread.todaybread.order.domain.Order;
import com.github.org.todaybread.todaybread.order.exception.NotFoundOrderException;
import com.github.org.todaybread.todaybread.order.infra.persistence.OrderRepositoryImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepositoryImpl orderRepository;

    @Override
    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getList(String memberId, int page, int take) {
        return orderRepository.getByMemberId(
            memberId,
            PageRequest.of(page - 1, take)
        );
    }

    @Override
    public Order getById(String orderId) {
        return orderRepository.getById(orderId).orElseThrow(NotFoundOrderException::new);
    }
}
