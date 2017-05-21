package com.globex.model.entity.pm;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(exclude = {"organization"})
@Table(name = "contact_info")
public class ContactInfo implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CONTACT_INFO_ID")
	private Integer contactInfoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
	private Organization organization;

    @Column(name = "FIRST_NAME")
	private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "OFFICE_LOCATION")
	private String officeLocation;

    @Column(name = "EMAIL")
    private String email;

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

    @Column(name = "USER_TYPE")
	private int userType;

    @Column(name = "COMMENTS")
	private String comments;

	public ContactInfo() {
	}

	public ContactInfo(Organization organization, String firstName, String lastName, int userType) {
		this.organization = organization;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
	}

	public ContactInfo(Organization organization, String firstName, String lastName, String officeLocation,
			String email, Integer phoneCountryCode, Integer phoneAreaCode, Long phone, Integer phoneExtension,
			Integer faxCountryCode, Integer faxAreaCode, Long fax, Integer mobileCountryCode, Long mobile, int userType,
			String comments) {
		this.organization = organization;
		this.firstName = firstName;
		this.lastName = lastName;
		this.officeLocation = officeLocation;
		this.email = email;
		this.phoneCountryCode = phoneCountryCode;
		this.phoneAreaCode = phoneAreaCode;
		this.phone = phone;
		this.phoneExtension = phoneExtension;
		this.faxCountryCode = faxCountryCode;
		this.faxAreaCode = faxAreaCode;
		this.fax = fax;
		this.mobileCountryCode = mobileCountryCode;
		this.mobile = mobile;
		this.userType = userType;
		this.comments = comments;
	}



}
