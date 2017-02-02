package com.globex.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAwareAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {

	public AjaxAwareAuthenticationEntryPoint(final String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	public void commence(final HttpServletRequest request,
			final HttpServletResponse response,
			final AuthenticationException authException) throws IOException,
			ServletException {

        SavedRequest savedRequest = (SavedRequest)request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if (savedRequest != null) {
            request.getSession().removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
        }
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			response.sendError(403, "Forbidden");
		} else {
			super.commence(request, response, authException);
		}
	}
}