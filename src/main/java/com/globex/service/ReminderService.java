package com.globex.service;

import com.globex.model.entity.common.Reminder;
import org.springframework.data.domain.Page;

/**
 * Created by Sunil Golla on 3/13/2017.
 */
public interface ReminderService {
    public Page<Reminder> list(Integer pageNumber,Integer pageSize);
}
