package com.globex.model.entity.common;

import com.globex.model.entity.pm.FileInfo;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sunil Golla on 2/15/2017.
 */
@Data
@Entity
@Table(name = "communication")
public class Communication {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="FILE_ID")
    private Integer fileId;

    @Column(name="FROM_ORGANIZATION_ID")
    private String fromOrgId;

    @Column(name="FROM_USER_ID")
    private Integer fromUserId;

    @Column(name="TO_ORGANIZATION_ID")
    private Integer toOrgId;

    @Column(name="DATE_CREATED")
    private Timestamp createdDate;

    @Column(name="SUBJECT")
    private String subject;

    @Column(name="CONTENT")
    private String content;

    @Column(name="FILE_ATTACHMENT")
    private String fileAttached;

    @Column(name="SHOW_TO_GLOBEX")
    private Integer showToGlobex;

    @Column(name="SHOW_TO_PM")
    private Integer showToPM;

    @Column(name="SHOW_TO_LM")
    private Integer showToLM;

    @Column(name="REPLY_EXPECTED_BY")
    private Timestamp replyExpectedBy;

    @Column(name="REPLY_SENT")
    private Integer replySent;

    @Column(name="COUNTRY")
    private String country;

}
