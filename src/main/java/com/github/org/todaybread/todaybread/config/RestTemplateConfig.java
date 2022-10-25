package com.github.org.todaybread.todaybread.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${steppay.end-point}")
    String baseUri;
    
    @Value("${steppay.secret}")
    String secretKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
            .rootUri(baseUri)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.ALL_VALUE)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("Secret-Token", secretKey)
            .build();
    }
}
