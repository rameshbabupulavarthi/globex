package com.globex.security;


import com.globex.model.entity.user.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class CurrentUserDO implements Serializable{

	private static final long serialVersionUID = -8887782515039151079L;
	
	public CurrentUserDO(User user){
		this.userId = user.getId();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userName = user.getUserName();
		this.thumbnail = user.getThumbnail();
        this.fullName=firstName+","+lastName;
	}
	
	public CurrentUserDO(){
		
	}
	
	private Long userId;
	
	private String currentUserRole;
	
	private String userName;
	
	private String title;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String externalUserId;
	
	private String thumbnail;
	
	private String password;
	
	private String fullName;

	public String toErrorString() {
		StringBuffer sb = new StringBuffer("UserDO [");
		sb.append("userId=").append(userId);
		sb.append(", userName=").append(userName);
		sb.append(", firstName=").append(firstName);
		sb.append(", lastName=").append(lastName);
		sb.append(", email=").append(email);

		sb.append("]");
		return sb.toString();
	}
}