package com.walker.aistock.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;

@Configuration
public class WebFluxConfig {

    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        ServerCodecConfigurer configurer = ServerCodecConfigurer.create();
        configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024); // 10MB
        return configurer;
    }

}
