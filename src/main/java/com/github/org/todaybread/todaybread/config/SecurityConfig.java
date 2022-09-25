package com.github.org.todaybread.todaybread.config;

import com.github.org.todaybread.todaybread.auth.application.token.TokenServiceImpl;
import com.github.org.todaybread.todaybread.auth.infra.filter.JwtAccessDeniedHandler;
import com.github.org.todaybread.todaybread.auth.infra.filter.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenServiceImpl tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()

            .cors().configurationSource(corsConfigurationSource())
            .and()

            .apply(new JwtSecurityConfig(tokenProvider))
            .and()

            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)
            .and()

            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/graphql").permitAll()
            .antMatchers("/playground").permitAll()
            .antMatchers("/vendor/playground/**").permitAll()
            .anyRequest().authenticated()
            .and()

            .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
