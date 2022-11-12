package com.github.org.todaybread.todaybread.order.infra.persistence;

import com.github.org.todaybread.todaybread.order.domain.Order;
import com.github.org.todaybread.todaybread.order.domain.QOrder;
import com.github.org.todaybread.todaybread.order.infra.http.response.OrderResponse;
import com.github.org.todaybread.todaybread.product.domain.QProduct;
import com.github.org.todaybread.todaybread.store.domain.QStore;
import com.github.org.todaybread.todaybread.store.domain.Store;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    private final JPAQueryFactory queryFactory;

    private final QStore store = QStore.store;
    private final QProduct product = QProduct.product;
    private final QOrder order = QOrder.order;

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

    @Override
    public List<OrderResponse> getByStore(Store findStore, Pageable pageable) {
        return queryFactory
            .select(Projections.constructor(OrderResponse.class,
                order.id,
                order.createdAt,
                order.updatedAt,
                order.steepayOrderCode,
                order.paidAmount,
                order.product,
                order.member
            ))
            .from(order)
            .leftJoin(order.product, product)
            .leftJoin(product.store, store)
            .where(order.product.store.eq(findStore))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(order.createdAt.desc())
            .fetch();
    }
}
