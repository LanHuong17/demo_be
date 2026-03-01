package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("https://demo-be-march1-dnbrh9dxh7e8fnhv.southeastasia-01.azurewebsites.net") // URL Frontend sau khi deploy
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
    }
} 
