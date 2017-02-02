package com.globex.repository.rdbms;

import com.globex.model.entity.user.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Long>{
	
	@Query("SELECT ur FROM UserRole ur where ur.user.id =:userId")
	public UserRole getUserRoleByUserId(@Param("userId") Long userId);

}
