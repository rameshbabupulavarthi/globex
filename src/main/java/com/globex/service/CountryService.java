package com.globex.service;

import com.globex.model.entity.common.Country;
import com.globex.model.vo.CountryDO;
import com.globex.model.vo.PageModel;
import org.springframework.data.domain.Page;

/**
 * Created by Sunil Golla on 2/22/2017.
 */
public interface CountryService {

    public PageModel<CountryDO> list(PageModel<CountryDO> pageModel);

    public Country getCountryDetails(Long countryId);

    public Long saveCountry(Country country);
}
