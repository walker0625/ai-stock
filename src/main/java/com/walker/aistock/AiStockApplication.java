package com.walker.aistock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // TODO 추후 인증 추가시 제거
public class AiStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiStockApplication.class, args);
    }

}
