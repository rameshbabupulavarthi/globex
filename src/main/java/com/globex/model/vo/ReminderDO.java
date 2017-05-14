package com.globex.model.vo;

import com.globex.model.entity.common.Reminder;
import com.globex.model.entity.user.User;
import com.utils.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * Created by Sunil Golla on 3/13/2017.
 */
@Data
public class ReminderDO {


    private Integer reminderId;

    private UserDO user;

    private Integer communicationId;

    private String information;

    private String details;

    private int status;

    private String dateCreated;

    private String reminderDate;

    public ReminderDO(){

    }

    public ReminderDO(Reminder reminder){
        this.reminderId=reminder.getReminderId();
        this.user=reminder.getUser()!=null?new UserDO(reminder.getUser()):null;
        this.communicationId=reminder.getReminderId();
        this.information=reminder.getInformation();
        this.details=reminder.getDetails();
        this.status=reminder.getStatus();
        //this.dateCreated= DateUtil.formatDate(reminder.getDateCreated());
    }
}
