package com.globex.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth.provider.OAuthProcessingFilterEntryPoint;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomOAuthProcessingFilterEntryPoint extends OAuthProcessingFilterEntryPoint {
	
	final Logger logger = LoggerFactory.getLogger(CustomOAuthProcessingFilterEntryPoint.class);

	private static final String INVALID_SIGNATURE = "Invalid signature";
	private static final String MISSING_SIGNATURE_METHOD = "Missing signature method";
	private static final String EXPIRED_TIMESTAMP = "Expired timestamp";
	private static final String MISSING_TIMESTAMP = "Missing timestamp";
	private static final String MISSING_NONCE = "Missing nonce";
	private static final String MISSING_SIGNATURE = "Missing signature";
	private static final String UNSUPPORTED_SIGNATURE_METHOD = "Unsupported signature method";

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            String message =  authException.getMessage();
	    	logger.error("OAuth request Failed : "+message);
	    	request.setAttribute("error_codes", message);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/provisiningError");
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST);
	    	dispatcher.forward(request, response);
	}
}
