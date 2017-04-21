package com.globex.service;

import com.globex.model.entity.user.User;
import com.globex.model.vo.PageModel;
import com.globex.model.vo.UserDO;
import com.globex.security.CurrentUserDO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by Ramesh on 17-12-2016.
 */
public interface UserService {

    public User getCurrentUser();

    public CurrentUserDO getCurrentUserDO();

    public Long saveUser(UserDO userDO);

    public Map<String,Object> list(Integer pageNumber,Integer pageSize);

    public PageModel<UserDO> list(PageModel<UserDO> pageModel);

    public UserDO getUser(Long userId);

}
