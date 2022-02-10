package com.example.chapter6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    HandlerInterceptor loginInterceptor;

    //  상단의 Code>Override methods..>addInterceptors 검색
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                //  모든 URL 형식에 대한 정보를 intercept 하여, 권한이 없으면 접근 못 하도록.
                .addPathPatterns("/**")
                //  로그인 페이지에는 권한이 없어도 접근 가능하도록 해당 URL 명시
                .excludePathPatterns(
                        "/member/**"
                );
    }
}
