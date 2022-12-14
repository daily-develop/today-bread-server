package com.github.org.todaybread.todaybread.steppay;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.github.org.todaybread.todaybread.steppay.customer.application.SteppayCustomerService;
import com.github.org.todaybread.todaybread.steppay.customer.infra.request.SteppayCreateCustomerRequest;
import com.github.org.todaybread.todaybread.steppay.customer.infra.request.SteppayShippingRequest;
import com.github.org.todaybread.todaybread.steppay.customer.infra.response.SteppayCustomerResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SteppayCustomerServiceImplTest {

    @Autowired
    private SteppayCustomerService steppayCustomerService;

    @Test
    @DisplayName("스텝페이 고객을 생성할 수 있어요.")
    public void create() {
        SteppayCustomerResponse response = steppayCustomerService.createCustomer(
            SteppayCreateCustomerRequest.builder()
                .name("이름")
                .email("email@test.com")
                .phone("010-0000-0000")
                .shipping(
                    SteppayShippingRequest.builder()
                        .postcode("12345")
                        .address1("경기도 성남시 분당구 판교역로")
                        .address2("OOOO동 OOO호")
                        .build()
                )
                .build()
        );

        assertThat(response).isNotNull().isInstanceOf(SteppayCustomerResponse.class);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo("이름");
        assertThat(response.getEmail()).isEqualTo("email@test.com");
        assertThat(response.getPhone()).isEqualTo("01000000000");

    }
}