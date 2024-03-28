package com.example.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){

        log.info("WebMvcConfigurer 호출");

        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5000", "null")
                .allowedMethods("POST", "GET")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
