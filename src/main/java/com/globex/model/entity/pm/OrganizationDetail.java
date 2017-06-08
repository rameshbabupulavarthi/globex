package com.globex.model.entity.pm;

import com.globex.model.entity.common.RateRequirement;
import com.globex.model.entity.partner.CommissionRequirement;
import com.globex.model.entity.partner.LOB;
import com.globex.model.entity.partner.OrgRateRequirement;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Sunil Golla on 5/31/2017.
 */
@Data
@Entity
@Table(name="organization_detail")
public class OrganizationDetail {


    @Id
    @Column(name="ORG_DET_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long orgDetailsId;

    @OneToOne
    @JoinColumn(name="org_id")
    private Organization organization;

    @Column(name="PARENT_ORG_NAME")
    private Integer parentCompany;

    @Column(name="ORG_TYPE")
    private Integer orgType;

    @Column(name="LIC_NO")
    private String licenseNo;

    @Column(name="LIC_AUTH_NAME")
    private String licenseAuthorityName;

    @Column(name="RAT_BY_OTH")
    private String ratedByOtherAgency;

    @Column(name="LIC_AUTH_WEB")
    private String licenseAuthWebsite;

    @Column(name="EST_DATE")
    private Timestamp establishedDate;

    @Column(name="ATTACHMENT")
    private String attachment;

    @Column(name="AM_BEST_RATING")
    private String amBestRating;

    @Column(name="AM_BEST_LOOK")
    private Integer amBestLook;

    @Column(name="AM_OUTLOOK_DATE")
    private Timestamp amOutlookDate;

    @Column(name="AM_RAT_ATTACH")
    private String amRatingAttachment;

    @Column(name="S_P_RAT")
    private Integer sAndPRating;

    @Column(name="S_P_OTL")
    private String sAndPOutlook;

    @Column(name="S_P_OTL_DATE")
    private Timestamp sAndPrRatingOutlookDate;

    @Column(name="S_P_ATTACH")
    private String sAndPAttachment;

    @Column(name="MISC_COMPANY_NAME")
    private String misCompanyName;

    @Column(name="MISC_COMPANY_COUNTRY")
    private String misCompanyCountry;

    @Column(name="MISC_COMPANY_WEBSITE")
    private String misCompanyWebsite;

    @Column(name="MISC_COMPANY_RATING")
    private String misCompanyRating;

    @Column(name="MISC_COMPANY_OUTLOOK")
    private String misCompanyOutlook;

    @Column(name="MISC_COMPANY_ATTACHMENT")
    private String misCompanyAttachment;

    @Column(name="alphaBrokers")
    private String alphaBrokers;


    @Column(name="reInsuranceLob")
    private String reInsuranceLob;

    @Column(name="reInsuranceSupport")
    private Integer reInsuranceSupport;

    @Column(name="reInsuranceComments")
    private String reInsuranceComments;

    @Column(name="reInsurancePlacementLOB")
    private String reInsurancePlacementLob;

    @Column(name="reinsurBroker")
    private Integer reinsurBroker;

    @Column(name="reinsurBrokerText")
    private String reinsurBrokerText;

    @Column(name="insuRequiredDoc")
    private String insuRequiredDoc;

    @Column(name="insuRequiredDocAttach")
    private String insuRequiredDocAttach;

    @Column(name="serviceOption")
    private Integer serviceOption;

    @Column(name="serviceOptionText")
    private String serviceOptionText;

    @Column(name="regRegulator")
    private Integer regRegulator;

    @Column(name="regRegulatorText")
    private String regRegulatorText;

    @Column(name="adviceRegistration")
    private String adviceRegistration;

    @Column(name="adviceRegistrationAttach")
    private String adviceRegistrationAttach;

    @Column(name="requiredDocReinsurPlace")
    private String requiredDocReinsurPlace;

    @Column(name="registrationProcedure")
    private String registrationProcedure;

    @Column(name="registrationProcedureAttach")
    private String registrationProcedureAttach;

    @Column(name="specReqDocReinsurPlace")
    private String specReqDocReinsurPlace;

    @Column(name="requiredDocReinsurPlaceAttach")
    private String requiredDocReinsurPlaceAttach;

    @Column(name="compInvolClaims")
    private Integer compInvolClaims;

    @Column(name="compInvolClaimsText")
    private String compInvolClaimsText;

    @Column(name="premiumPayOption")
    private String premiumPayOption;

    @Column(name="premiumWithTax")
    private String premiumWithTax;

    @Column(name="claimHandlingWordingAttach")
    private String claimHandlingWordingAttach;

    @Column(name="bankDetails")
    private String bankDetails;

    @Column(name="bankAddress")
    private String bankAddress;

    @Column(name="bankName")
    private String bankName;

    @Column(name="bankIban")
    private String bankIban;

    @Column(name="bankSwiftCode")
    private String bankSwiftCode;

    @Column(name="bankAttachment")
    private String bankAttachment;

}
