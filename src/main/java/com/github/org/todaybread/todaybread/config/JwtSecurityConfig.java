package com.github.org.todaybread.todaybread.config;

import com.github.org.todaybread.todaybread.auth.application.token.TokenServiceImpl;
import com.github.org.todaybread.todaybread.auth.infra.filter.AuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class JwtSecurityConfig extends
    SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenServiceImpl tokenProvider;

    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(
            new AuthFilter(tokenProvider),
            UsernamePasswordAuthenticationFilter.class
        );
    }
}
