package com.globex.model.entity.common;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sunil Golla on 2/15/2017.
 */
@Data
@Entity
@Table(name = "country_data")
public class Country {

    @Id
    @Column(name = "COUNTRY_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="COUNTRY")
    private String country;

    @Column(name="NON_ADMITTED_ALLOWED")
    private Boolean nonAdmittedAllowed;

    @Column(name="NON_ADMITTED_COMMENTS")
    private String nonAdmittedComments;

    @Column(name="RETAIL_BROKER_REQUIRED")
    private Boolean retailBrokerRequired;

    @Column(name="RETAIL_BROKER_COMMENTS")
    private String retailBrokerComments;

    @Column(name="REINSURANCE_BROKER_REQUIRED")
    private Boolean reInsuranceBrokerRequired;

    @Column(name="REINSURANCE_BROKER_COMMENTS")
    private String reInsuranceBrokerComments;

    @Column(name="MANDATORY_REINSURANCE_CESSION")
    private Boolean mandatoryReInsuranceCession;

    @Column(name="MANDATORY_REINSURANCE_COMMENTS")
    private String mandatoryReInsuranceComments;

    @Column(name="STATESIDE_PREMIUM_ALLOWED")
    private Boolean stateSidePremiumAllowed;

    @Column(name="STATESIDE_PREMIUM_COMMENTS")
    private String stateSidePremiumComments;

    @Column(name="OTHER_ACCOUNTING_REQUIREMENTS")
    private String otherAccRequirements;

    @Column(name="PREMIUM_RESERVE")
    private String premiumReserve;

    @Column(name="TAXES")
    private String taxes;

    @Column(name="VAT")
    private String vat;

    @Column(name="REINSURANCE_TAX")
    private String reInsuranceTax;

    @Column(name="OTHER_REQUIREMENTS")
    private String otherRequirements;

    @Column(name="POLICY_LANGUAGE")
    private String policyLanguage;

    @Column(name="TACIT_RENEWAL")
    private Boolean tacitRenewal;

    @Column(name="TACIT_RENEWAL_COMMENTS")
    private String tacticalRenewalComments;

    @Column(name="GENERAL_COMMENTS")
    private String generalComments;

    @Column(name="CREATED_BY")
    private Long createdBy;

    @Column(name="DATE_CREATED")
    private Date createdDate;

    @Column(name="UPDATED_BY")
    private Long updatedBy;

    @Column(name="DATE_UPDATED")
    private Date updatedDate;

}
