package com.globex.controller;

import com.utils.AppUtils;
import com.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ramesh on 17-12-2016.
 */
@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping("/index")

    public ModelAndView getIndexPage(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        String domainName = AppUtils.getDomainName(request.getRequestURL().toString());
        String redirectUrl = "/login";
        return new ModelAndView("forward:"+redirectUrl);
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        //on session time out redirect LTI user to LTI login page otherwise normal login page.
        String referrer = request.getHeader("referer");


        //If the logged in user's orgin is via OAuth then remove the OAuthCookie
        removeOAuthCookie(request,response);
        String redirectUrl = "/globex/login";
        return new ModelAndView(redirectUrl/*, "model", model*/);
    }

    @RequestMapping("/loginError")
    public ModelAndView loginError(@RequestParam(value="errorCode", required=false) String errorCode, HttpServletRequest request
            , HttpServletResponse response, @RequestParam(value="errorPage", required=false) String errorPage) {
        Map<String, Object> model = new HashMap<String, Object>();

        return new ModelAndView("/globex/login", "model", model);

    }




    private void removeOAuthCookie(HttpServletRequest request, HttpServletResponse response){
        boolean oAuthSrcCookieExists = false;

        // Check if AUTHSOURCE Cookie exists with value OAUTH
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("AUTHSOURCE".equals(cookie.getName()) && "OAUTH".equals(cookie.getValue())) {
                    oAuthSrcCookieExists = true;
                }
            }
        }

        if (oAuthSrcCookieExists) {
            // Remove the authSrcCookieExists if present. The life time of the cookie will
            // be until the browser is exited.
            // But in case, where the LTIUser also has Admin role, then the user can login
            // via the same browser instance without exiting it. In this case the cookie
            // should be removed, so that if the session expires after the user logged in
            // via Admin role, then the user will be redirected to login page.
            Cookie oAuthSrcCookie= new Cookie("AUTHSOURCE","OAUTH");
            oAuthSrcCookie.setMaxAge(0);
            oAuthSrcCookie.setPath("/");
            response.addCookie(oAuthSrcCookie);
        }
    }
}