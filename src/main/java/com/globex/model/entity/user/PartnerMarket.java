package com.globex.model.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(exclude={"user"})
@Entity
@Table(name="t_partner_market")
public class PartnerMarket implements Serializable {
	@Id
	@Column
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;

    @Column(name="org_name")
	private String orgName;

    @Column(name="address_1")
	private String addrLine1;

    @Column(name="address_2")
	private String addrLine2;

    @Column(name="city")
	private String city;

    @Column(name="state")
	private String state;

    @Column(name="zipcode")
	private String zipCode;

    @Column(name="country")
	private String country;

    @Column(name="website")
	private String website;
	
	//primary contact
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;

    @Column(name="comments")
	private String comments;
	
	// bank information
    @Column(name="first_name")
	private String firstName;

    @Column(name="last_name")
	private String lastName;

    @Column(name="email")
	private String email;

    @Column(name="phone_country_code")
    private String phoneCountryCode;

    @Column(name="phone_area_code")
	private String phoneAreaCode;

    @Column(name="phone")
	private String phone;

    @Column(name="fax_country_code")
	private String faxCountryCode;

    @Column(name="fax_area_code")
	private String faxAreaCode;

    @Column(name="fax")
	private String fax;

    @Column(name="mobile_country_code")
	private String mobileCountryCode;

    @Column(name="mobile")
	private String mobile;

    @Column(name="bank_details")
	private String bankDetails;

    @Column(name="other_details")
	private String otherDetails;

    @Column(name="created_date")
    private String createdDate;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="modified_date")
    private String modifiedDate;

    @Column(name="modified_by")
    private String modifiedBy;

}
