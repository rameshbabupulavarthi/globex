package com.globex.model.entity.common;

import com.globex.model.entity.common.File;
import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
import lombok.Data;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "pmlm_invoice")
public class PmlmInvoice implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "PMLM_INVOICE_ID", unique = true, nullable = false)
	private Integer pmlmInvoiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_ID", nullable = false)
	private File file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY")
	private User user;

    @Column(name = "INVOICE_NUMBER", length = 20)
	private String invoiceNumber;

    @Column(name = "INVOICE_TYPE", nullable = false)
	private int invoiceType;

    @Column(name = "POLICY_YEAR", nullable = false)
    private int policyYear;

    @Column(name = "COUNTRY", length = 50)
	private String country;

    @Column(name = "AMOUNT", nullable = false, precision = 11)
	private double amount;

    @Column(name = "CURRENCY", length = 50)
	private String currency;

    @Column(name = "DUE_DATE")
	private Date dueDate;

    @Column(name = "COMMENTS")
	private String comments;

    @Column(name = "INVOICE_STATUS", nullable = false)
	private int invoiceStatus;

    @Column(name = "PMLM_INVOICE_DATE")
	private Date pmlmInvoiceDate;

    @Column(name = "PMLM_INVOICE_MODE", nullable = false)
	private int pmlmInvoiceMode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pmlmInvoice")
	private Set<PaidPmlmInvoice> paidPmlmInvoices;
}
