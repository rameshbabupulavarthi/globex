package com.globex.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserWebAuthenticationDetails extends WebAuthenticationDetails {
	
	private static final long serialVersionUID = 3337693725322469090L;
	
	private Long customerId;
	
	private String proxyPassword;

	
	public UserWebAuthenticationDetails(HttpServletRequest context) {
		super(context);
	}
}
