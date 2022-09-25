package com.github.org.todaybread.todaybread.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

@TestConfiguration
public class EmbeddedRedisConfig {

    @Value("${spring.redis.port}")
    private Long port;

    private RedisServer redisServer;

    @PostConstruct
    void postConstruct() {
        redisServer = new RedisServer(port.intValue());
        redisServer.start();
    }

    @PreDestroy
    void preDestroy() {
        redisServer.stop();
    }
}
