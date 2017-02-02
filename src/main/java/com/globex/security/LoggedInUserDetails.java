package com.globex.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper=false)
public class LoggedInUserDetails extends User {

	private static final long serialVersionUID = 2149394973496625539L;
	
	CurrentUserDO userDO;
	
	public LoggedInUserDetails(String userName, String password , Collection<? extends GrantedAuthority> authorities) {
		super(userName, password, authorities);
	}

}
