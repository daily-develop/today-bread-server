package com.github.org.todaybread.todaybread.steppay.customer.infra.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayShippingRequest {

    String postcode;

    String address1;

    String address2;

    @Builder
    public SteppayShippingRequest(
        String postcode,
        String address1,
        String address2
    ) {
        this.postcode = postcode;
        this.address1 = address1;
        this.address2 = address2;
    }
}
