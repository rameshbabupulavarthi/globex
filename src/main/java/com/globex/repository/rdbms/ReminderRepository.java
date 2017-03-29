package com.globex.repository.rdbms;

import com.globex.model.entity.common.Reminder;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Sunil Golla on 3/13/2017.
 */
public interface ReminderRepository extends PagingAndSortingRepository<Reminder, Long> {

}
