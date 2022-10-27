package com.github.org.todaybread.todaybread.config;

import com.github.org.todaybread.todaybread.auth.application.oauth.OAuthService;
import com.github.org.todaybread.todaybread.auth.application.oauth.OAuthServiceImpl;
import com.github.org.todaybread.todaybread.auth.application.oauth.OAuthServiceMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ApplicationConfig {

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
}
