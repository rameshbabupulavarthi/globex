package com.globex.service;

import com.globex.model.entity.common.Reminder;
import com.globex.repository.rdbms.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by Sunil Golla on 3/13/2017.
 */
@Service
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    ReminderRepository reminderRepository;

    public Page<Reminder> list(Integer pageNumber,Integer pageSize){
        Sort sort=new Sort(Sort.Direction.DESC,"reminderId");
        Pageable limit = (Pageable) new PageRequest(pageNumber, pageSize,sort);
        Page<Reminder> reminderPage =reminderRepository.findAll(limit);
        return reminderPage;
    }
}
