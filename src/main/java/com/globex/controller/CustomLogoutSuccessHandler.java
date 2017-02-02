package com.globex.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component 
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
 
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,  
            Authentication authentication) throws IOException, ServletException {  

    	SecurityContextHolder.clearContext();
		Cookie terminate = new Cookie(TokenBasedRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, null);
		terminate.setMaxAge(0);
		terminate.setPath("/");;
		response.addCookie(terminate);

		HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        Cookie jsessionterminate = new Cookie("JSESSIONID",null);
		jsessionterminate.setMaxAge(0);
		jsessionterminate.setPath("/");
		response.addCookie(jsessionterminate);
        
        //below does the 'standard' spring logout handling  
        super.onLogoutSuccess(request, response, authentication);         
    }  
}  