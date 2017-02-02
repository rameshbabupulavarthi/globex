package com.globex.repository.rdbms.lm;

import com.globex.model.entity.partner.Partner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Sunil Golla on 12/31/2016.
 */
public interface PartnerRepository extends PagingAndSortingRepository<Partner, Long> {

    @Query("select p from Partner p where p.user.id =:userId")
    public Partner findByUserId(@Param("userId") Long userId);
}
