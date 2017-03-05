package com.globex.service;

import com.globex.model.entity.user.User;
import com.globex.model.entity.user.UserRole;
import com.globex.model.vo.UserDO;
import com.globex.repository.rdbms.UserRepository;
import com.globex.security.LoggedInUserDetails;
import com.globex.security.CurrentUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Ramesh on 17-12-2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StandardPasswordEncoder encoder;

    @Deprecated
    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoggedInUserDetails userDetails = (LoggedInUserDetails) authentication.getPrincipal();
        User user = userRepository.findOne(userDetails.getUserDO().getUserId());
        return user;
    }

    @Override
    public CurrentUserDO getCurrentUserDO() {
        CurrentUserDO user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof LoggedInUserDetails) {
            LoggedInUserDetails userDetails = (LoggedInUserDetails) authentication.getPrincipal();
            if (userDetails != null) {
                user = userDetails.getUserDO();
            }
        }
        return user;
    }

    @Override
    public Long saveUser(UserDO userDO){
        User user=new User();
        user.setId(userDO.getId());
        user.setFirstname(userDO.getFirstName());
        user.setLastname(userDO.getLastName());
        user.setUsername(userDO.getUserName());
        user.setEmail(userDO.getEmail());
        user.setTelephone(userDO.getTelephone());
        user.setPassword(encoder.encode(userDO.getPassword()));
        user.setStatus(UserDO.Status.ACTIVE.getValue());
        user.setThumbnail(userDO.getThumbnail());
        user.setCreatedDate(new Date());
        user.setModifiedDate(new Date());

        UserRole userRole=new UserRole();
        userRole.setUser(user);
        userRole.setDefaultRole(Boolean.TRUE);
        userRole.setPermission("1");
        userRole.setType(userDO.getRole());
        userRole.setCreatedBy(getCurrentUserDO().getUserId());
        userRole.setCreatedDate(new Date());
        Set<UserRole> userRoles=new HashSet<UserRole>();
        userRoles.add(userRole);
        user.setUserRole(userRoles);

        User persistedUser=userRepository.save(user);
        return persistedUser.getId();
    }

    public Map<String,Object> list(Integer pageNumber,Integer pageSize){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable limit = (Pageable) new PageRequest(pageNumber, pageSize,sort);
        Page<User> userList =userRepository.findAll(limit);
        List<UserDO> userDOs=new ArrayList<UserDO>();
        for(User user:userList){
            UserDO userDO=new UserDO(user);
            userDOs.add(userDO);
        }
        Map<String,Object> model=new HashMap<String,Object>();
        model.put("totalRecords",userList.getTotalElements());
        model.put("users",userDOs);
        return model;
    }

    public UserDO getUser(Long userId){
        User user= userRepository.findOne(userId);
        UserDO userDO=new UserDO(user);
        return userDO;
    }

}
