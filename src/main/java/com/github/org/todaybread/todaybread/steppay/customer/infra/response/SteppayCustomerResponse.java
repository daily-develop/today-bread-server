package com.github.org.todaybread.todaybread.steppay.customer.infra.response;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SteppayCustomerResponse {

    Long id;
    String name;
    String email;
    String phone;
    String code;
    LocalDateTime createdAt;

    @Builder
    public SteppayCustomerResponse(
        Long id,
        String name,
        String email,
        String phone,
        String code,
        LocalDateTime createdAt
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.code = code;
        this.createdAt = createdAt;
    }
}