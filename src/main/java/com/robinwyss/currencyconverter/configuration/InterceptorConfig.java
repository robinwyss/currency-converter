package com.robinwyss.currencyconverter.configuration;

import com.robinwyss.currencyconverter.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // add it to every request /**
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
    }
}