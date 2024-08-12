package com.walker.aistock.front.config;

import com.walker.aistock.backend.common.enums.FilePath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.walker.aistock.backend.common.enums.FilePath.IMAGE_SRC_PATH;
import static com.walker.aistock.backend.common.enums.FilePath.SPEECH_SRC_PATH;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.realpath.image}")
    private String IMAGE_REAL_PATH;

    @Value("${file.realpath.speech}")
    private String SPEECH_REAL_PATH;

    // TODO 추후 배포 서버의 파일 경로로 변경
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 리소스 핸들러
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        // 생성 이미지 파일 리소스 핸들러
        registry.addResourceHandler(IMAGE_SRC_PATH.getValue() + "**")
                .addResourceLocations("file:" + IMAGE_REAL_PATH);

        // 생성 뉴스 파일 리소스 핸들러
        registry.addResourceHandler(SPEECH_SRC_PATH.getValue() + "**")
                .addResourceLocations("file:" + SPEECH_REAL_PATH);

    }

}
