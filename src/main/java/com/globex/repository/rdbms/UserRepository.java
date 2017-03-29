package com.globex.repository.rdbms;

import com.globex.model.entity.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends  PagingAndSortingRepository<User, Long>{

	public User findUserById(Long id);

	@Deprecated
	public User findUserByUserName(String username);

}