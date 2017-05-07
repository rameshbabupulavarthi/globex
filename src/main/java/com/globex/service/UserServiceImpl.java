package com.globex.service;

import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
import com.globex.model.entity.user.UserRole;
import com.globex.model.vo.PageModel;
import com.globex.model.vo.UserDO;
import com.globex.repository.rdbms.UserRepository;
import com.globex.security.LoggedInUserDetails;
import com.globex.security.CurrentUserDO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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

    @Autowired
    SessionFactory sessionFactory;

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
        user.setFirstName(userDO.getFirstName());
        user.setLastName(userDO.getLastName());
        user.setUserName(userDO.getUserName());
        user.setEmail(userDO.getEmail());
        user.setPhoneCountryCode(userDO.getPhoneCountryCode());
        user.setPhoneAreaCode(userDO.getPhoneAreaCode());
        user.setPhone(userDO.getPhone());
        user.setPhoneExtension(userDO.getPhoneExtension());
        user.setFaxCountryCode(userDO.getFaxCountryCode());
        user.setFaxAreaCode(userDO.getFaxAreaCode());
        user.setFax(userDO.getFax());
        user.setMobileCountryCode(userDO.getMobileCountryCode());
        user.setMobile(userDO.getMobile());
        user.setUserType(userDO.getUserType());
        user.setComments(userDO.getComments());
        user.setStatus(userDO.getStatus());
        user.setAddress(userDO.getAddress());
        user.setCity(userDO.getCity());
        user.setState(userDO.getState());
        user.setCountry(userDO.getCountry());
        user.setZip(userDO.getZip());
        user.setBranchOffice(userDO.getBranchOffice());
        user.setStatus(UserDO.Status.ACTIVE.getValue());
        user.setCreatedDate(new Date());
        user.setModifiedDate(new Date());

        user.setThumbnail(userDO.getThumbnail());
        user.setPassword(encoder.encode(userDO.getPassword()));
        User currentUser=getCurrentUser();
        Organization organization=currentUser.getOrganization();
        user.setOrganization(organization);
        User persistedUser=userRepository.save(user);
        return persistedUser.getId();
    }

    @Override
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

    @Override
    public PageModel<UserDO> list(PageModel<UserDO> pageModel){
        Session session=sessionFactory.openSession();
        Criteria criteria=session.createCriteria(User.class);
        Criteria criteriaForCount=session.createCriteria(User.class);

        if(pageModel.getFilters()!=null){
            Map<String,Object> filters=pageModel.getFilters();
            for(Map.Entry<String,Object> entry: filters.entrySet()){
                if(entry.getKey().equals("orgId")){
                    criteria.createAlias("organization","organization");
                    criteria.add(Restrictions.eq("organization.id",entry.getValue()));
                    criteriaForCount.createAlias("organization","organization");
                    criteriaForCount.add(Restrictions.eq("organization.id",entry.getValue()));
                }else {
                    criteria.add(Restrictions.ilike(entry.getKey(), "%" + entry.getValue() + "%"));
                    criteriaForCount.add(Restrictions.ilike(entry.getKey(), "%" + entry.getValue() + "%"));
                }
            }
        }

        if(pageModel.getSortFields()!=null){
            Map<String,PageModel.SortOrder> sortFields=pageModel.getSortFields();
            for(Map.Entry<String,PageModel.SortOrder> entry:sortFields.entrySet()){
                if(PageModel.SortOrder.ASC.equals(entry.getValue())){
                    criteria.addOrder(Order.asc(entry.getKey()));
                }else{
                    criteria.addOrder(Order.desc(entry.getKey()));
                }
            }
        }else{
            criteria.addOrder(Order.desc("id"));
        }
        criteria.setFirstResult(pageModel.getPageNo()*pageModel.getPageSize());
        criteria.setMaxResults(pageModel.getPageSize());

        criteria.setFirstResult(pageModel.getPageNo()*pageModel.getPageSize());
        criteria.setMaxResults(pageModel.getPageSize());
        criteriaForCount.setProjection(Projections.rowCount());
        Long totalCount = (Long) criteriaForCount.uniqueResult();
        List<User> list= criteria.list();

        session.close();
        List<UserDO> userDOs=new ArrayList<UserDO>();
        for(User user:list){
            UserDO userDO=new UserDO(user);
            userDOs.add(userDO);
        }
        pageModel.setContent(userDOs);
        pageModel.setTotalRecords(totalCount);
        return pageModel;
    }

    public UserDO getUser(Long userId){
        User user= userRepository.findOne(userId);
        UserDO userDO=new UserDO(user);
        return userDO;
    }

}
