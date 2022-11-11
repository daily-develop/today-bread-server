package com.github.org.todaybread.todaybread.config;

import com.github.org.todaybread.todaybread.auth.application.oauth.OAuthService;
import com.github.org.todaybread.todaybread.auth.application.oauth.OAuthServiceMock;
import com.github.org.todaybread.todaybread.steppay.customer.application.SteppayCustomerService;
import com.github.org.todaybread.todaybread.steppay.customer.application.SteppayCustomerServiceMock;
import com.github.org.todaybread.todaybread.steppay.order.application.SteppayOrderService;
import com.github.org.todaybread.todaybread.steppay.order.application.SteppayOrderServiceMock;
import com.github.org.todaybread.todaybread.steppay.plan.application.SteppayPlanService;
import com.github.org.todaybread.todaybread.steppay.plan.application.SteppayPlanServiceMock;
import com.github.org.todaybread.todaybread.steppay.product.application.SteppayProductService;
import com.github.org.todaybread.todaybread.steppay.product.application.SteppayProductServiceMock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ApplicationConfig {

    @Bean
    public OAuthService oAuthService() {
        return new OAuthServiceMock();
    }

    @Bean
    public SteppayCustomerService steppayCustomerService() {
        return new SteppayCustomerServiceMock();
    }

    @Bean
    public SteppayProductService steppayProductService() {
        return new SteppayProductServiceMock();
    }

    @Bean
    public SteppayPlanService steppayPlanService() {
        return new SteppayPlanServiceMock();
    }

    @Bean
    public SteppayOrderService steppayOrderService() {
        return new SteppayOrderServiceMock();
    }
}
