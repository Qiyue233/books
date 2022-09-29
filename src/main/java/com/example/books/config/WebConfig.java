package com.example.books.config;

import com.example.books.interceptor.WebInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("login");
//    }
    //拦截器
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new WebInterceptor())
               .addPathPatterns("/**");
    }
}
