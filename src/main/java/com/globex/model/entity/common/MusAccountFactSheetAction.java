package com.globex.model.entity.common;

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
@Table(name = "mus_account_fact_sheet_action")
public class MusAccountFactSheetAction implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "FACT_SHEET_ACTION_ID", unique = true, nullable = false)
	private Integer factSheetActionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FACT_SHEET_ID", nullable = false)
	private MusAccountFactSheet musAccountFactSheet;

    /*@Column(name = "ACTION_DATE")
	private Date actionDate;*/

    @Column(name = "ACTION_ISSUE", nullable = false, length = 50)
	private String actionIssue;

    @Column(name = "FOLLOW_UP_STATUS", nullable = false, length = 50)
	private String followUpStatus;
}
