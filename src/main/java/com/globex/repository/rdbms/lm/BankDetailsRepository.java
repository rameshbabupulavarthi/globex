package com.globex.repository.rdbms.lm;

import com.globex.model.entity.partner.BankingDetails;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Sunil Golla on 12/31/2016.
 */
public interface BankDetailsRepository extends PagingAndSortingRepository<BankingDetails, Long> {
}
