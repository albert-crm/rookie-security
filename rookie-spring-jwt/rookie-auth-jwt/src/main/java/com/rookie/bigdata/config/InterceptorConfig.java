package com.rookie.bigdata.config;

import com.rookie.bigdata.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Class InterceptorConfig
 * @Description
 * @Author rookie
 * @Date 2023/12/25 15:50
 * @Version 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");
    }


}
