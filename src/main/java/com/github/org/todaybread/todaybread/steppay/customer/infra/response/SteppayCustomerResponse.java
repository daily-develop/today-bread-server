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
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    String name;
    String email;
    String phone;

    @Builder
    public SteppayCustomerResponse(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String name,
        String email,
        String phone
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}