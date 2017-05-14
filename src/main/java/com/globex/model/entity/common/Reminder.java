package com.globex.model.entity.common;

import com.globex.model.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "reminder")
public class Reminder implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "REMINDER_ID", unique = true, nullable = false)
	private Integer reminderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
	private User user;

    @Column(name = "COMMUNICATION_ID")
	private Integer communicationId;

    /*@Column(name = "REMINDER_DATE")
	private Timestamp reminderDate;*/

    @Column(name = "INFORMATION", nullable = false, length = 50)
	private String information;

    @Column(name = "DETAILS")
	private String details;

    @Column(name = "STATUS", nullable = false)
	private int status;

    /*@Column(name = "DATE_CREATED")
	private Timestamp dateCreated;*/
}
