package com.github.org.todaybread.todaybread.steppay.order.application;

import com.github.org.todaybread.todaybread.steppay.order.infra.request.SteppayCreateOrderRequest;
import com.github.org.todaybread.todaybread.steppay.order.infra.response.SteppayOrderResponse;

public interface SteppayOrderService {

    SteppayOrderResponse create(SteppayCreateOrderRequest request);
}
