package com.github.org.todaybread.todaybread.order.application.facade;

import com.github.org.todaybread.todaybread.order.infra.http.response.OrderResponse;
import java.util.List;

public interface OrderFacade {

    OrderResponse create(String memberId, String productId);

    List<OrderResponse> getList(String memberId, int page, int take);

    OrderResponse getById(String memberId, String orderId);

    List<OrderResponse> getListByStoreId(
        String memberId,
        String storeId,
        int page,
        int take
    );
}
