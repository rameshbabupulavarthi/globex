package com.globex.model.entity.common;

import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sunil Golla on 2/15/2017.
 */
@Data
@ToString
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "communication")
public class Communication {

    @Id
    @GeneratedValue
    @Column(name = "COMMUNICATION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FILE_ID")
    private File file;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FROM_ORGANIZATION_ID")
    private Organization organizationByFromOrganizationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TO_ORGANIZATION_ID")
    private Organization organizationByToOrganizationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FROM_USER_ID")
    private User user;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "FILE_ATTACHMENT")
    private String fileAttachment;

    @Column(name = "SHOW_TO_GLOBEX")
    private boolean showToGlobex;

    @Column(name = "SHOW_TO_PM")
    private boolean showToPm;

    @Column(name = "SHOW_TO_LM")
    private boolean showToLm;

    /*@Column(name = "REPLY_EXPECTED_BY")
    private Date replyExpectedBy;*/

    @Column(name = "REPLY_SENT")
    private boolean replySent;

    @Column(name = "COUNTRY")
    private String country;
}
