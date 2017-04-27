package com.globex.security;

import com.globex.constants.Role;
import com.globex.model.entity.user.User;
import com.globex.model.entity.user.UserRole;
import com.globex.repository.rdbms.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class PreAuthenticatedUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

	@Autowired
    UserRepository userRepository;
	

	@Override
	public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
		Map<String, Object> aPrincipal = (Map<String, Object>) token.getPrincipal();
		User user = null;//userRepository.findUserByExternalIdAndCustomerId((String)aPrincipal.get(ProvisioningUtils.USER_EXTERNAL_ID), (Long)aPrincipal.get(ProvisioningUtils.CUSTOMER_ID));
		LoggedInUserDetails userDetails = new LoggedInUserDetails(user.getUserName(), "", getAuthorities(user));
		CurrentUserDO userDO = new CurrentUserDO(user);


		

		userDetails.setUserDO(userDO);
		
		return userDetails;
	}

	private List<GrantedAuthority> getAuthorities(User user) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        String role=Role.getValue(user.getUserType()).getRoleName();
        authList.add(new SimpleGrantedAuthority(role));
		/*if(userRoles!=null && !userRoles.isEmpty()){
			for(UserRole userRole: userRoles){
				authList.add(new SimpleGrantedAuthority(userRole.getType()));
			}
		}*/
		return authList;
	}

}
