package com.globex.oauth;

import com.globex.security.PreAuthenticatedUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth.provider.ConsumerAuthentication;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.token.OAuthAccessProviderToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OAuthRequestHandler implements org.springframework.security.oauth.provider.OAuthAuthenticationHandler {
	
	final Logger logger = LoggerFactory.getLogger(OAuthRequestHandler.class);
	
	@Autowired
    PreAuthenticatedUserDetailsService userDetailsService;

	@Override
	public Authentication createAuthentication(HttpServletRequest request, ConsumerAuthentication authentication, OAuthAccessProviderToken authToken) {
		AbstractAuthenticationToken userAuthentication = null;

        ConsumerDetails consumerDetails = authentication.getConsumerDetails();
        Map<String, Object> aPrincipal = new HashMap<String, Object>();
        userAuthentication = new PreAuthenticatedAuthenticationToken(aPrincipal, "");

		WebAuthenticationDetails webRequest = new WebAuthenticationDetails(request);
		userAuthentication.setDetails(webRequest);
		ProviderManager pm = (ProviderManager) WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()).getBean("authenticationManager");
		return pm.authenticate(userAuthentication);

	}

    private String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }







	public List<GrantedAuthority> getAnonymousAuthorities() {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
		return authList;
	}

}
