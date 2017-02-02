package com.globex.oauth;

import com.globex.constants.Role;
import com.globex.model.entity.user.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth.common.OAuthException;
import org.springframework.security.oauth.common.signature.SharedConsumerSecret;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.ExtraTrustConsumerDetails;

import java.util.ArrayList;
import java.util.List;


public class OAuthConsumerDetailsService implements ConsumerDetailsService {

	@Override
	public ConsumerDetails loadConsumerByConsumerKey(String key) throws OAuthException {

        return null;
	}
	
	private ExtraTrustConsumerDetails createConsumerDetails(String consumerKey, String consumerName, String consumerSecret) {
		SharedConsumerSecret secret = new SharedConsumerSecret(consumerSecret);
		BaseConsumerDetails bcd = new BaseConsumerDetails();
		bcd.setConsumerKey(consumerKey);
		bcd.setConsumerName(consumerName);
		bcd.setSignatureSecret(secret);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.getRoleName()));
		bcd.setAuthorities(authorities);
		bcd.setRequiredToObtainAuthenticatedToken(false); // false for 2 legged OAuth
		return bcd;
	}
}
