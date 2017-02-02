package com.globex.repository.rdbms;

import com.globex.model.entity.user.PartnerMarket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Ramesh on 25-12-2016.
 */
public interface PartnerMarketRepository extends PagingAndSortingRepository<PartnerMarket, Long> {

    @Query("select pm FROM PartnerMarket pm where pm.user.id =:userId")
    public PartnerMarket findPartnerMarketByUserId(@Param("userId") Long userId);
}
