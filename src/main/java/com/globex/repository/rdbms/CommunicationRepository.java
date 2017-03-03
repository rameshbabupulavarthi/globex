package com.globex.repository.rdbms;

import com.globex.model.entity.common.Communication;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Sunil Golla on 2/20/2017.
 */
public interface CommunicationRepository extends PagingAndSortingRepository<Communication,Long> {

}
