package com.globex.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/** Interceptor for URL
 */
public class URLInterceptor implements HandlerInterceptor {
	final Logger logger = LoggerFactory.getLogger(URLInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        generateCookie(request, response);
		return true;
	}

    private void generateCookie(HttpServletRequest request, HttpServletResponse response) {
        String cookieName = "App_cookie";
        //String url = "";
        Cookie[] cookies = null;
        HttpServletRequest req = request;
        HttpServletResponse res = response;
        Boolean hasCookie = Boolean.FALSE;


        if (null != req) {
            //url = req.getServletPath().toString();
            cookies = req.getCookies();
        }

        if ((null != cookies) && (cookies.length > 0)){
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    hasCookie = Boolean.TRUE;
                    break;
                }
            }
        }

        // if TEST_COOKIE is not present
        if(!hasCookie) {
            Integer maxAge;



            maxAge = 14400; // 4hrs

            //List<String> cmsURLS = Arrays.asList("/", "/index.jsp", "/login", "/secure/questionEditView", "/secure/cmsHome", "/secure/manageContent", "/secure/renderCollectionsByCategory");
            String cookieValue = UUID.randomUUID().toString();
            Cookie newCookie = new Cookie(cookieName, cookieValue);
            newCookie.setPath("/");
            newCookie.setMaxAge(maxAge);
            newCookie.setHttpOnly(true);

            if(null != res)
                res.addCookie(newCookie);
        }
    }


    /** Handles Post processing
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	

	
}