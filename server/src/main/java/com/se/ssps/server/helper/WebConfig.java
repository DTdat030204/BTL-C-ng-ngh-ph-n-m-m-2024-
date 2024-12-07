package com.se.ssps.server.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Áp dụng cho tất cả endpoint
                        .allowedOrigins("http://localhost:3000") // Cho phép từ localhost:3000
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Phương thức được phép
                        .allowedHeaders("*"); // Chấp nhận tất cả header
            }
        };
    }
}
