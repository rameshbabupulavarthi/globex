package com.globex.repository.rdbms.lm;

import com.globex.model.entity.pm.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Sunil Golla on 6/12/2017.
 */
public interface OrganizationRepository  extends PagingAndSortingRepository<Organization, Long> {


    @Query("select o from Organization o where o.createdBy.id =:userId order by o.id desc ")
    public List<Organization> findOrganizationByUserId(@Param("userId") Long userId);
}
