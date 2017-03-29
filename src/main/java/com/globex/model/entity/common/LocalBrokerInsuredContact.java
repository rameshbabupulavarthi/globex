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
@Table(name = "local_broker_insured_contact")
public class LocalBrokerInsuredContact implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "LOCAL_BROKER_INSURED_CONTACT_ID", unique = true, nullable = false)
	private Integer localBrokerInsuredContactId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_ID", nullable = false)

	private Application application;

    @Column(name = "COUNTRY", nullable = false, length = 50)
	private String country;

    @Column(name = "CONTACT_NAME", nullable = false, length = 100)
    private String contactName;

    @Column(name = "ADDRESS", length = 65535)
    private String address;

    @Column(name = "PHONE_COUNTRY_CODE")
    private Integer phoneCountryCode;

    @Column(name = "PHONE_AREA_CODE")
    private Integer phoneAreaCode;

    @Column(name = "PHONE")
	private Long phone;

    @Column(name = "PHONE_EXTENSION")
	private Integer phoneExtension;

    @Column(name = "FAX_COUNTRY_CODE")
	private Integer faxCountryCode;

    @Column(name = "FAX_AREA_CODE")
	private Integer faxAreaCode;

    @Column(name = "FAX")
	private Long fax;

    @Column(name = "EMAIL", length = 50)
	private String email;

    @Column(name = "CONTACT_TYPE", nullable = false)
	private int contactType;
}
