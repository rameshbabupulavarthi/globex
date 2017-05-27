package com.globex.service;

import com.globex.constants.Role;
import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
import com.globex.model.vo.OrganizationDO;
import com.globex.model.vo.PageModel;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Sunil Golla on 5/13/2017.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserService userService;

    @Autowired
    StandardPasswordEncoder encoder;

    public PageModel<OrganizationDO> list(PageModel<OrganizationDO> pageModel){

        Session session=sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Organization.class);
        Criteria criteriaForCount=session.createCriteria(Organization.class);

        if(pageModel.getFilters()!=null && !pageModel.getFilters().isEmpty()){
            Map<String,Object> filters=pageModel.getFilters();
            //criteria.createAlias("users","u");
            //criteriaForCount.createAlias("users","u");
            for(Map.Entry<String,Object> entry: filters.entrySet()){
                if(entry.getValue() instanceof String){
                    criteria.add(Restrictions.ilike(entry.getKey(), "%" + entry.getValue() + "%"));
                    criteriaForCount.add(Restrictions.ilike(entry.getKey(), "%" + entry.getValue() + "%"));
                }else{
                    criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
                    criteriaForCount.add(Restrictions.eq(entry.getKey(),entry.getValue()));
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
        List<Organization> list= criteria.list();

        List<OrganizationDO> orgDOs=new ArrayList<OrganizationDO>();
        for(Organization organization:list){
            OrganizationDO organizationDO=new OrganizationDO(organization);
            organizationDO.loadUsers(organization);
            orgDOs.add(organizationDO);
        }
        session.close();
        pageModel.setContent(orgDOs);
        pageModel.setTotalRecords(totalCount);
        return pageModel;
    }

    public OrganizationDO getPMDetails(Long orgId){
        Session session=sessionFactory.openSession();
        Organization organization=(Organization)session.get(Organization.class, orgId);
        OrganizationDO organizationDO=new OrganizationDO(organization);
        organizationDO.loadFullDetails(organization);
        session.close();
        return organizationDO;
    }

    public void deletePM(Long orgId){
        Session session=sessionFactory.openSession();
        Organization organization=(Organization)session.get(Organization.class, orgId);
        session.delete(organization);
        session.close();
    }

    @Transactional
    public void save(OrganizationDO organizationDO){

        Organization organization= organizationDO.value();
        organization.setRegDate(new Timestamp(new Date().getTime()));
        organization.setOrgType(Role.ROLE_PM_ADMIN.getRoleValue());

        Set<User> users= organization.getUsers();
        for(User user:users){
            user.setPassword(encoder.encode(user.getPassword()));
        }
        Session session=sessionFactory.openSession();
        if(organization.getId()!=null){
            session.update(organization);
        }else {
            session.save(organization);
        }
        session.flush();;
        session.close();
    }
}
