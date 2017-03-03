package com.globex.service;

import com.globex.model.entity.common.Country;
import com.globex.repository.rdbms.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Sunil Golla on 2/22/2017.
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    public Page<Country> list(Integer pageNumber,Integer pageSize){
        Pageable limit = (Pageable) new PageRequest(pageNumber, pageSize);
        Page<Country> communicationPage =countryRepository.findAll(limit);
        return communicationPage;
    }

    public Country getCountryDetails(Long countryId){
        return countryRepository.findOne(countryId);
    }

}
