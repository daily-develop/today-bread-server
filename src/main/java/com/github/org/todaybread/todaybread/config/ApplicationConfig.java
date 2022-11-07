package com.github.org.todaybread.todaybread.config;

import com.github.org.todaybread.todaybread.auth.application.oauth.OAuthService;
import com.github.org.todaybread.todaybread.auth.application.oauth.OAuthServiceImpl;
import com.github.org.todaybread.todaybread.auth.application.oauth.OAuthServiceMock;
import com.github.org.todaybread.todaybread.steppay.customer.application.SteppayCustomerService;
import com.github.org.todaybread.todaybread.steppay.customer.application.SteppayCustomerServiceImpl;
import com.github.org.todaybread.todaybread.steppay.customer.application.SteppayCustomerServiceMock;
import com.github.org.todaybread.todaybread.steppay.product.application.SteppayProductService;
import com.github.org.todaybread.todaybread.steppay.product.application.SteppayProductServiceImpl;
import com.github.org.todaybread.todaybread.steppay.product.application.SteppayProductServiceMock;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final RestTemplate restTemplate;

    @Profile({"local"})
    @Bean(name = "OAuthService")
    public OAuthService oAuthServiceMock() {
        return new OAuthServiceMock();
    }

    @Profile({"dev"})
    @Bean(name = "OAuthService")
    public OAuthService oAuthServiceImpl() {
        return new OAuthServiceImpl();
    }

    @Profile({"local"})
    @Bean(name = "SteppayCustomerService")
    public SteppayCustomerService steppayCustomerServiceMock() {
        return new SteppayCustomerServiceMock();
    }

    @Profile({"dev"})
    @Bean(name = "SteppayCustomerService")
    public SteppayCustomerService steppayCustomerServiceImpl() {
        return new SteppayCustomerServiceImpl(restTemplate);
    }

    @Profile({"local"})
    @Bean(name = "SteppayProductService")
    public SteppayProductService steppayProductServiceMock() {
        return new SteppayProductServiceMock();
    }

    @Profile({"dev"})
    @Bean(name = "SteppayProductService")
    public SteppayProductService steppayProductServiceImpl() {
        return new SteppayProductServiceImpl(restTemplate);
    }
}
