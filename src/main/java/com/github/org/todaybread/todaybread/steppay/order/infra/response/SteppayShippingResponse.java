package com.github.org.todaybread.todaybread.steppay.order.infra.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SteppayShippingResponse {

    String name;
    String phone;
    String postcode;
    String address1;
    String address2;

    @Builder
    public SteppayShippingResponse(
        String name,
        String phone,
        String postcode,
        String address1,
        String address2
    ) {
        this.name = name;
        this.phone = phone;
        this.postcode = postcode;
        this.address1 = address1;
        this.address2 = address2;
    }
}
