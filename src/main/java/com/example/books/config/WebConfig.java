package com.example.books.config;

import com.example.books.interceptor.WebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public WebInterceptor getWebInterceptor() {
        return new WebInterceptor();
    }
    @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(getWebInterceptor()).addPathPatterns("/**");
    }
}
