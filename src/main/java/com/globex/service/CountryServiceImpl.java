package com.globex.service;

import com.globex.model.entity.common.Country;
import com.globex.model.vo.CountryDO;
import com.globex.model.vo.PageModel;
import com.globex.model.vo.UserDO;
import com.globex.repository.rdbms.CountryRepository;
import com.globex.security.CurrentUserDO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Sunil Golla on 2/22/2017.
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    UserService userService;

    @Autowired
    SessionFactory sessionFactory;

    public PageModel<CountryDO> list(PageModel<CountryDO> pageModel){
        Session session=sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Country.class);
        Criteria criteriaForCount=session.createCriteria(Country.class);

        if(pageModel.getFilters()!=null){
            Map<String,Object> filters=pageModel.getFilters();
            for(Map.Entry<String,Object> entry: filters.entrySet()){
                criteria.add(Restrictions.ilike(entry.getKey(), "%" + entry.getValue() + "%"));
                criteriaForCount.add(Restrictions.ilike(entry.getKey(), "%"+entry.getValue()+"%"));
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
        List<Country> list= criteria.list();
        List<CountryDO> countryDOs=new ArrayList<CountryDO>();
        for(Country country:list){
            CountryDO countryDO=new CountryDO(country);
            countryDOs.add(countryDO);
        }
        pageModel.setContent(countryDOs);
        pageModel.setTotalRecords(totalCount);
        return pageModel;
    }

    public Country getCountryDetails(Long countryId){
        return countryRepository.findOne(countryId);
    }

    public Long saveCountry(Country country){
        CurrentUserDO userDO=userService.getCurrentUserDO();
        country.setCreatedBy(userDO.getUserId());
        country.setUpdatedBy(userDO.getUserId());
        /*country.setCreatedDate(new Date());
        country.setUpdatedDate(new Date());*/
        Country countrySaved=countryRepository.save(country);
        return countrySaved.getId();
    }

}
