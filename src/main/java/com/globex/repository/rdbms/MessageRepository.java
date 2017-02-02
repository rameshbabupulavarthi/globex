package com.globex.repository.rdbms;

import com.globex.model.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Sunil Golla on 1/29/2017.
 */
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

}
