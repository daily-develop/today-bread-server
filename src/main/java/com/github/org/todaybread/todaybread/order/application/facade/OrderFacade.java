package com.github.org.todaybread.todaybread.order.application.facade;

import com.github.org.todaybread.todaybread.order.infra.http.response.OrderResponse;
import java.util.List;

public interface OrderFacade {

    String getOrderUrl(String orderCode);

    String getOrderSecret();

    Boolean hasOrder(String memberId, String productId);

    OrderResponse create(String memberId, String productId);

    OrderResponse getById(String memberId, String orderId);

    List<OrderResponse> getListByMemberId(String memberId, int page, int take);

    List<OrderResponse> getListByStoreId(String memberId, String storeId, int page, int take);

    OrderResponse success(String memberId, String orderId);

    OrderResponse cancel(String memberId, String orderId);
}
