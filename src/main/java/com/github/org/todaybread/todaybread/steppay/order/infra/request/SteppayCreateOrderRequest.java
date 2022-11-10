package com.github.org.todaybread.todaybread.steppay.order.infra.request;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayCreateOrderRequest {

    Long customerId;

    List<SteppayItemRequest> items;

    @Builder
    public SteppayCreateOrderRequest(
        Long customerId,
        List<SteppayItemRequest> items
    ){
        this.customerId = customerId;
        this.items = items;
    }
}
