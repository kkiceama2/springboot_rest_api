package com.example.restApiExample.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

@Service
public class Interceptor implements HandlerInterceptor {
	
	@Override
    public boolean preHandle(HttpServletRequest request
				           , HttpServletResponse response
				           , Object handler ) throws Exception {
        
		
		
		
        return true;
    }
	
	@Override
	public void postHandle(HttpServletRequest request
						 , HttpServletResponse response
						 , Object handler
						 , @Nullable ModelAndView modelAndView) throws Exception {
		
		
		
	}
	
	
}
