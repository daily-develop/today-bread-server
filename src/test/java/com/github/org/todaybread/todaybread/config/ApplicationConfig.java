package com.github.org.todaybread.todaybread.config;

import com.github.org.todaybread.todaybread.auth.application.oauth.OAuthService;
import com.github.org.todaybread.todaybread.auth.application.oauth.OAuthServiceMock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ApplicationConfig {

    @Bean
    public OAuthService oAuthService() {
        return new OAuthServiceMock();
    }
}
