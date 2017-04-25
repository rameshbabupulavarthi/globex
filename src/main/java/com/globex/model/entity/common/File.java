package com.globex.model.entity.common;


import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

@Data
@ToString(exclude={"applications","communications","musAccountFactSheets","attachments","notes","pmlmInvoices","fileAttachments"})
@EqualsAndHashCode(of = {"fileId"})
@Entity
@Table(name = "file")
public class File implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "FILE_ID")
	private Long fileId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARTNER_MARKET_ID", nullable = false)
	private Organization organization;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FORWARD_TO")
    private User forwardTo;

    @Column(name = "FILE_STATUS", nullable = false)
    private int fileStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UPDATED_BY")
    private User updatedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;

    @Column(name = "PROSPECT")
    private Integer prospect;

    @Column(name = "DATE_CREATED")
	private Timestamp dateCreated;

    @Column(name = "DATE_UPDATED")
    private Timestamp dateUpdated;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "file")
    @Fetch(FetchMode.SELECT)
    private Set<Application> applications;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	private Set<Communication> communications;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
	private Set<MusAccountFactSheet> musAccountFactSheets;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
    private Set<Attachment> attachments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
    private Set<Note> notes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
    private Set<PmlmInvoice> pmlmInvoices;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
    private Set<FileAttachment> fileAttachments;
}
