package com.globex.service;

import com.globex.model.entity.common.Country;
import org.springframework.data.domain.Page;

/**
 * Created by Sunil Golla on 2/22/2017.
 */
public interface CountryService {

    public Page<Country> list(Integer pageNumber,Integer pageSize);

    public Country getCountryDetails(Long countryId);
}
