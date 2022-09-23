package com.github.org.todaybread.todaybread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TodayBreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodayBreadApplication.class, args);
    }

}
