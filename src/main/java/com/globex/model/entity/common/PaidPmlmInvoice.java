package com.globex.model.entity.common;

import lombok.Data;

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
@Table(name = "paid_pmlm_invoice")
public class PaidPmlmInvoice implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "PAID_PMLM_INVOICE_ID", unique = true, nullable = false)
	private Integer paidPmlmInvoiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_SENT_ID", nullable = false)
	private PaymentSent paymentSent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PMLM_INVOICE_ID", nullable = false)
	private PmlmInvoice pmlmInvoice;
}
