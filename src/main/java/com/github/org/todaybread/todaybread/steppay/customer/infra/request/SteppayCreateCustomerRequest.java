package com.github.org.todaybread.todaybread.steppay.customer.infra.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SteppayCreateCustomerRequest {

    String name;
    String email;
    String phone;

    SteppayShippingRequest shipping;

    @Builder
    public SteppayCreateCustomerRequest(
        String name,
        String email,
        String phone,
        SteppayShippingRequest shipping
    ) {
        this.name = name;
        this.email = email;
        this.phone = phone.replaceAll("-", "").trim();
        this.shipping = shipping;
    }
}
