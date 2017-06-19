package com.globex.model.entity.pm;
// Generated Mar 11, 2017 11:22:32 AM by Hibernate Tools 5.1.0.Alpha1

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * AccountInfo generated by hbm2java
 */
@Data
@EqualsAndHashCode(exclude = {"organization"})
@Entity
@Table(name = "account_info")
public class AccountInfo implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ACCOUNT_INFO_ID")
	private Integer accountInfoId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
	private Organization organization;

    @Column(name = "FIRST_NAME")
	private String firstName;

    @Column(name = "LAST_NAME")
	private String lastName;

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

    @Column(name = "MOBILE_COUNTRY_CODE")
	private Integer mobileCountryCode;

    @Column(name = "MOBILE")
	private Long mobile;

    @Column(name = "EMAIL")
	private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "accountInfo",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private List<AccountBankDetails> accountBankDetails;

    /*@Column(name = "BANK_INFO")
    private String bankInfo;

    @Column(name = "OTHER_INFO")
    private String otherInfo;*/
}
