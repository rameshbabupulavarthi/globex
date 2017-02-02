package com.globex.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

public class GlobalExceptionHandler implements HandlerExceptionResolver {
	final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception) {

        return null;
    }
    
    private static String getFullURL(HttpServletRequest request) {
    	if(request!=null){
	        StringBuffer requestURL = request.getRequestURL();
	        String queryString = request.getQueryString();
	
	        if (queryString == null) {
	            return requestURL.toString();
	        } else {
	            return requestURL.append('?').append(queryString).toString();
	        }
    	}
    	return null;
    }

}