package com.globex.repository.rdbms;

import com.globex.model.entity.common.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Sunil Golla on 2/22/2017.
 */
public interface CountryRepository extends PagingAndSortingRepository<Country,Long> {

}
