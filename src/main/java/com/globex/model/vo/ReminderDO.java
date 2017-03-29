package com.globex.model.vo;

import com.globex.model.entity.common.Reminder;
import com.globex.model.entity.user.User;
import lombok.Data;

/**
 * Created by Sunil Golla on 3/13/2017.
 */
@Data
public class ReminderDO {


    private Integer reminderId;

    private UserDO user;

    private Integer communicationId;

    /*private Date reminderDate;*/

    private String information;

    private String details;

    private int status;

    /*private Date dateCreated;*/

    public ReminderDO(){

    }

    public ReminderDO(Reminder reminder){
        this.reminderId=reminder.getReminderId();
        this.user=reminder.getUser()!=null?new UserDO(reminder.getUser()):null;
        this.communicationId=reminder.getReminderId();
        this.information=reminder.getInformation();
        this.details=reminder.getDetails();
        this.status=reminder.getStatus();
    }
}
