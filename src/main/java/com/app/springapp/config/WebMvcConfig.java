package com.app.springapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private String uploadDir = System.getProperty("user.dir") + "/uploads/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 클라이언트에서 /uploads/** 로 요청이 오면 D 드라이브의 폴더에서 파일을 찾음
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///" + uploadDir);
    }
}
