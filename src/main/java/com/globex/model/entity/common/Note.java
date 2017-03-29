package com.globex.model.entity.common;

import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
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
@Table(name = "note")
public class Note implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "NOTE_ID", unique = true, nullable = false)
	private Integer noteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_ID", nullable = false)
	private File file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID", nullable = false)
    private Organization organization;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CREATED_BY", nullable = false)
    private User user;

    @Column(name = "INFORMATION", nullable = false, length = 50)
    private String information;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;
}
