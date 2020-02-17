package com.example.restApiExample.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.restApiExample.interceptor.Interceptor;


@Configuration
public class InterceptorConf implements WebMvcConfigurer {
    @Autowired Interceptor interceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {

    	registry.addMapping("/**/*")
    			.allowedOrigins("*")
			    .allowedMethods("GET", "POST", "OPTIONS")
			    .allowedHeaders("content-type", "customaccessid", "customaccesskey", "accept")
			    .allowCredentials(true);
    
    }
}