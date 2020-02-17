package com.example.restApiExample.conf;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.restApiExample.entity.Response;
import com.google.gson.JsonObject;

@Aspect
@Component
public class LogAspect {
	Logger logger =  LoggerFactory.getLogger(LogAspect.class);

	@Around("execution(* com.example.restApiExample.api.*.*(..))")
    public String logging(ProceedingJoinPoint pjp) throws Throwable {
        
        Object[] obj = pjp.getArgs();
        JsonObject response = new JsonObject();
        HttpServletRequest request = (HttpServletRequest) obj[0];
        
        long sTime = System.currentTimeMillis();
        String result = (String) pjp.proceed();
        long eTime = System.currentTimeMillis();
        makeLog(request , result , eTime - sTime);
        
        return result.toString();
    }
	
	
	public void makeLog(HttpServletRequest request , String result , long diffTime) {
		
		JsonObject log = new JsonObject();
		JsonObject param = new JsonObject();
		
		Enumeration enumeration = request.getParameterNames();

		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			param.addProperty(key, request.getParameter(key));
		}
		
		log.add("reqParam", param);
		log.addProperty("reqHost", request.getRemoteHost());
		log.addProperty("reqMethod", request.getMethod());
		log.addProperty("reqtUri", request.getRequestURI());
		log.addProperty("processTime", diffTime + " ms");
		log.addProperty("result", result);
		
		
		System.out.println(log.toString());
		
	}
}
