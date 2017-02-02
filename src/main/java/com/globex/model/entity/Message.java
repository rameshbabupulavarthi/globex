package com.globex.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sunil Golla on 1/17/2017.
 */
@Data
@Entity
@Table(name="t_message")
public class Message {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="sender_id")
    private Long senderId;

    @Column(name="receiver_ids")
    private String receiver_ids;

    @Column(name="message")
    private String message;

    @Column(name="subject")
    private String subject;

    @Column(name="thread_id")
    private Long threadId;

    @Column(name="created_date")
    private Date createdDate;

}
