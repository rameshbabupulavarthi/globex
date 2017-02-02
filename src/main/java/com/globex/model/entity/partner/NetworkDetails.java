package com.globex.model.entity.partner;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ramesh on 29-12-2016.
 */
@Data
@ToString
@Entity
@Table(name="t_network")
public class NetworkDetails implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="qualifier")
    private int networkPartnerQualifier;

    @Column(name="qualifier_lob")
    private int networkPartnerQuaLob;

    @Column(name="comments")
    private String generalComments;

    @Column(name="score")
    private int networkPartnerScore;

    @Column(name="avg_response_time")
    private String averageResponseTime;

    @Column(name="contact_comments")
    private String primaryContactComments;

    @Column(name="vetting_status")
    private int vettingStatus;

    @Column(name="vetting_status_comments")
    private String vettingStatusComments;

    @Column(name="vetting_date")
    private Date vettingDate;

    @Column(name="vetting_reminder")
    private Date vettingReminder;

    @Column(name="vetting_reminder_comments")
    private String vettingReminderComments;

}
