package com.globex.service;

import com.globex.model.entity.user.User;
import com.globex.model.vo.UserDO;
import com.globex.security.CurrentUserDO;

import java.util.List;

/**
 * Created by Ramesh on 17-12-2016.
 */
public interface UserService {

    public User getCurrentUser();

    public CurrentUserDO getCurrentUserDO();

    public Long saveUser(UserDO userDO);

    public List<UserDO> list(Integer pageNumber,Integer pageSize);

    public UserDO getUser(Long userId);

}
