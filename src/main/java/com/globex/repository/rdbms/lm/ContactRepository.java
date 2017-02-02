package com.globex.repository.rdbms.lm;

import com.globex.model.entity.partner.ContactDetails;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Sunil Golla on 12/31/2016.
 */
public interface ContactRepository extends PagingAndSortingRepository<ContactDetails, Long> {
}
