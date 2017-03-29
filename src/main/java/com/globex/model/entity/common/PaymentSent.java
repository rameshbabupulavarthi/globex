package com.globex.model.entity.common;

import com.globex.model.entity.user.User;
import lombok.Data;

import java.util.Date;
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
@Table(name = "payment_sent")
public class PaymentSent implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "PAYMENT_SENT_ID", unique = true, nullable = false)
	private Integer paymentSentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPDATED_BY", nullable = false)
	private User userByUpdatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY", nullable = false)
	private User userByCreatedBy;

    /*@Column(name = "PAYMENT_DATE")
	private Date paymentDate;*/

    @Column(name = "PAYMENT_MODE", nullable = false)
	private int paymentMode;

    @Column(name = "PAYMENT_MODE_OTHER", length = 30)
	private String paymentModeOther;

    @Column(name = "AMOUNT", precision = 11)
	private Double amount;

    @Column(name = "DETAILS")
	private String details;

    /*@Column(name = "DATE_CREATED")
	private Date dateCreated;

    @Column(name = "DATE_UPDATED")
	private Date dateUpdated;*/

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentSent")
	private Set<PaidPmlmInvoice> paidPmlmInvoices;
}
