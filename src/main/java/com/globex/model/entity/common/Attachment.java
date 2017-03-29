package com.globex.model.entity.common;

import com.globex.model.entity.common.File;
import com.globex.model.entity.pm.Organization;
import lombok.Data;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "attachment")
public class Attachment implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ATTACHMENT_ID", unique = true, nullable = false)
    private Integer attachmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_ID", nullable = false)
	private File file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	private Organization organization;

    @Column(name = "FILE_NAME", nullable = false, length = 65535)
	private String fileName;

    @Column(name = "SUBJECT", length = 50)
	private String subject;

    @Column(name = "DESCRIPTION", length = 65535)
	private String description;

    /*@Column(name = "DATE_CREATED")
	private Date dateCreated;

    @Column(name = "DATE_SENT")
	private Date dateSent;*/
}
