package com.globex.model.entity.common;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Sunil Golla on 2/15/2017.
 */
@Data
@Entity
/*@Table(name = "country_data")*/
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "COUNTRY_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="COUNTRY")
    private String country;

    @Column(name="TERRITORY_COMMENTS")
    private String territoryComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private Set<Tax> taxes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private Set<RateRequirement> rateRequirements;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country",cascade = { CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private Set<Clause> clauses;

    @Column(name="LOC_CUR_ON_LOC_POL")
    private Boolean locCurOnLocPol;

    @Column(name="LOC_CUR_ON_LOC_POL_COM")
    private String locCurOnLocPolComments;

    @Column(name="FOR_LAW_ON_LOC_POL")
    private Boolean foreignLawOnLocalPolicy;

    @Column(name="FOR_LAW_ON_LOC_POL_COM")
    private String foreignLawOnLocalPolicyComments;

    @Column(name="MAN_SCR")
    private Boolean useManuScript;

    @Column(name="MAN_SCR_LOB")
    private String manuScriptLOB;

    @Column(name="MAN_SCR_COM")
    private String manuScriptComments;

    @Column(name="RE_INS_SUP")
    private Boolean reInsuranceSupport;

    @Column(name="RE_INS_SUP_LOB")
    private String reInsuranceSupportLOB;

    @Column(name="RE_INS_SUP_COM")
    private String reInsuranceSupportComments;

    @Column(name="FOR_RE_INS_REG_")
    private Boolean foreignReinsurerRegistered;

    @Column(name="FOR_RE_INS_REG_COM")
    private String foreignReinsurerRegisteredComments;

    @Column(name="FOR_RE_INS_REG_ADV")
    private String foreignReinsurerRegisteredAdvice;

    @Column(name="INF_REQ_POL_INS")
    private String infoReqdForPolicyInsurance;

    @Column(name="POL_INS_ATT")
    private Attachment policyInsuranceAttachment;

    @Column(name="PREMIUM_COLLECTION_TYPE")
    private String premiumCollectionType;

    @Column(name="NON_ADMITTED_ALLOWED")
    private Boolean nonAdmittedAllowed;

    @Column(name="NON_ADMITTED_COMMENTS")
    private String nonAdmittedComments;

    @Column(name="MANDATORY_REINSURANCE_CESSION")
    private Boolean mandatoryReInsuranceCession;

    @Column(name="MANDATORY_REINSURANCE_COMMENTS")
    private String mandatoryReInsuranceComments;

    @Column(name="TACIT_RENEWAL")
    private Boolean tacitRenewal;

    @Column(name="TACIT_RENEWAL_COMMENTS")
    private String tacticalRenewalComments;

    @Column(name="CASH_BEFORE_COVER")
    private Boolean cashBeforeCoverReq;

    @Column(name="CASH_BEFORE_COVER_COMMENTS")
    private Boolean cashBeforeCoverReqComments;

    @Column(name="LOCAL_CURRENCY_REQ")
    private Boolean localCurrencyReq;

    @Column(name="LOCAL_CURRENCY_REQ_COMMENTS")
    private Boolean localCurrencyReqComments;

    @Column(name="STA_RE_INS_REQ_LOB")
    private Boolean stateReinsurerReqLOB;

    @Column(name="STA_RE_INS_REQ")
    private String stateReinsurerReq;

    @Column(name="STA_RE_INS_REQ_COMMENTS")
    private String stateReinsurerReqComments;

    @Column(name="OTHER_REQUIREMENTS")
    private String otherRequirements;

    @Column(name="GENERAL_COMMENTS")
    private String generalComments;

/*    @Column(name="GENERAL_COMMENTS")
    private Set<Attachment> generalAttachments;*/


    private Long createdBy;

    private Long updatedBy;

   /* @Column(name="RETAIL_BROKER_REQUIRED")
    private Boolean retailBrokerRequired;

    @Column(name="RETAIL_BROKER_COMMENTS")
    private String retailBrokerComments;

    @Column(name="REINSURANCE_BROKER_REQUIRED")
    private Boolean reInsuranceBrokerRequired;

    @Column(name="REINSURANCE_BROKER_COMMENTS")
    private String reInsuranceBrokerComments;

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

    @Column(name="POLICY_LANGUAGE")
    private String policyLanguage;

    @Column(name="CREATED_BY")
    private Long createdBy;

    @Column(name="UPDATED_BY")
    private Long updatedBy;

    @Column(name="DATE_CREATED")
    private Date createdDate;

    @Column(name="DATE_UPDATED")
    private Date updatedDate;*/

}
