package com.globex.service;

import com.globex.model.entity.common.Country;
import com.globex.model.vo.UserDO;
import com.globex.repository.rdbms.CountryRepository;
import com.globex.security.CurrentUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Sunil Golla on 2/22/2017.
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    UserService userService;

    public Page<Country> list(Integer pageNumber,Integer pageSize){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable limit = (Pageable) new PageRequest(pageNumber, pageSize,sort);
        Page<Country> communicationPage =countryRepository.findAll(limit);
        return communicationPage;
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
