package com.globex.security;

import com.globex.model.entity.user.User;
import com.globex.model.entity.user.UserRole;
import com.globex.model.vo.UserDO;
import com.globex.repository.rdbms.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthenticationUserDetailsService implements UserDetailsService {
	final Logger logger = LoggerFactory.getLogger(AuthenticationUserDetailsService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	StandardPasswordEncoder encoder;

	@SuppressWarnings("deprecation")
	@Override	
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		try{
            String proxyPassword = null;
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication!=null){
				Object userWebAuthenticationDetails = authentication.getDetails();				 
				if (userWebAuthenticationDetails != null && userWebAuthenticationDetails instanceof UserWebAuthenticationDetails){
                    proxyPassword = ((UserWebAuthenticationDetails)userWebAuthenticationDetails).getProxyPassword();
				}
			}
			User user = null;
			if(user==null) {
				try {
					user = userRepository.findUserByUserName(userName);
				} catch (Exception e) {
					logger.debug("Exception during login, possibly getting multiple users with same userName: {}",e);
				}				
			}

			if (user == null) {
				throw new UsernameNotFoundException("User with name "+userName+" not found");
			} else if (user.getStatus() != null && (user.getStatus() == UserDO.Status.DISABLED.getValue() ||
                    user.getStatus() == UserDO.Status.EXPIRED.getValue() || user.getStatus() == UserDO.Status.DELETED.getValue())){
				throw new UsernameNotFoundException("User with name "+userName+" is deactivated, please contact support team");
			}
            String password = user.getPassword();

            if(StringUtils.isNotEmpty(proxyPassword)){
                password = encoder.encode(proxyPassword);
            }
        	LoggedInUserDetails userDetails = new LoggedInUserDetails(user.getUserName(), password, getAuthorities(user.getUserRole()));
        	CurrentUserDO userDO = new CurrentUserDO(user);

            String role=null;
            for (UserRole userRole : user.getUserRole()) {
                if (Boolean.TRUE == userRole.getDefaultRole()) {
                    role = userRole.getType();
                    break;
                }
            }
            if (role==null) {
                for (UserRole userRole : user.getUserRole()) {
                    role = userRole.getType();
                    break;
                }
            }
            userDO.setCurrentUserRole(role);
			userDetails.setUserDO(userDO);
			return userDetails;
		}catch(Exception ex){
			if(logger.isErrorEnabled()){
				logger.error("Error in finding user {} ",userName, ex);
			}
			throw new UsernameNotFoundException("User with name "+userName+" not found");
		}
	}

    public List<GrantedAuthority> getAuthorities(Set<UserRole> userRoles) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        if(userRoles!=null && !userRoles.isEmpty()){
            for(UserRole userRole: userRoles){
                authList.add(new SimpleGrantedAuthority(userRole.getType()));
                // If Permission is not null create a granted authorities for it
                if(userRole.getPermission() != null) {
                    authList.add(new SimpleGrantedAuthority(userRole.getPermission()));
                }
            }
        }
        return authList;
    }
}
