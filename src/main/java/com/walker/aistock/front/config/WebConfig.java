package com.walker.aistock.front.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // TODO 추후 배포 서버의 파일 경로로 변경
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 리소스 핸들러
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        // 생성 이미지 파일 리소스 핸들러
        registry.addResourceHandler("/ai/image/**")
                .addResourceLocations("file:/Users/minwoojeon/J/AI-STOCK/IMAGE/");

        // 생성 뉴스 파일 리소스 핸들러
        registry.addResourceHandler("/ai/speech/**")
                .addResourceLocations("file:/Users/minwoojeon/J/AI-STOCK/SPEECH/");

    }

}
