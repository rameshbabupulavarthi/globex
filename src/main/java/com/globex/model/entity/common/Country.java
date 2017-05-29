package com.globex.model.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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
@EqualsAndHashCode(of = {"id"})
@ToString(exclude={"taxes","rateRequirements","clauses"})
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
    private Short locCurOnLocPol;

    @Column(name="LOC_CUR_ON_LOC_POL_COM")
    private String locCurOnLocPolComments;

    @Column(name="FOR_LAW_ON_LOC_POL")
    private Short foreignLawOnLocalPolicy;

    @Column(name="FOR_LAW_ON_LOC_POL_COM")
    private String foreignLawOnLocalPolicyComments;

    @Column(name="MAN_SCR")
    private Short useManuScript;

    @Column(name="MAN_SCR_LOB")
    private String manuScriptLOB;

    @Column(name="MAN_SCR_COM")
    private String manuScriptComments;

    @Column(name="RE_INS_SUP")
    private Short reInsuranceSupport;

    @Column(name="RE_INS_SUP_LOB")
    private String reInsuranceSupportLOB;

    @Column(name="RE_INS_SUP_COM")
    private String reInsuranceSupportComments;

    @Column(name="FOR_RE_INS_REG_")
    private Short foreignReinsurerRegistered;

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
    private Short nonAdmittedAllowed;

    @Column(name="NON_ADMITTED_COMMENTS")
    private String nonAdmittedComments;

    @Column(name="MANDATORY_REINSURANCE_CESSION")
    private Short mandatoryReInsuranceCession;

    @Column(name="MANDATORY_REINSURANCE_COMMENTS")
    private String mandatoryReInsuranceComments;

    @Column(name="TACIT_RENEWAL")
    private Short tacitRenewal;

    @Column(name="TACIT_RENEWAL_REASON")
    private String tacitRenewalReason;

    @Column(name="TACIT_RENEWAL_COMMENTS")
    private String tacticalRenewalComments;

    @Column(name="CASH_BEFORE_COVER")
    private Short cashBeforeCoverReq;

    @Column(name="CASH_BEFORE_COVER_COMMENTS")
    private String cashBeforeCoverReqComments;

    @Column(name="LOCAL_CURRENCY_REQ")
    private Short localCurrencyReq;

    @Column(name="LOCAL_CURRENCY_REQ_COMMENTS")
    private String localCurrencyReqComments;

    @Column(name="STA_RE_INS_REQ_LOB")
    private String stateReinsurerReqLOB;

    @Column(name="STA_RE_INS_REQ")
    private String stateReinsurerReq;

    @Column(name="STA_RE_INS_REQ_COMMENTS")
    private String stateReinsurerReqComments;

    @Column(name="OTHER_REQUIREMENTS")
    private String otherRequirements;

    @Column(name="GENERAL_COMMENTS")
    private String generalComments;

    @Column(name="DATE_CREATED")
    private Date createdDate;

    @Column(name="DATE_UPDATED")
    private Date updatedDate;

    @Column(name="CREATED_BY")
    private Long createdBy;

    @Column(name="UPDATED_BY")
    private Long updatedBy;

    @Column(name="INS_REQ_DOC")
    private String insuRequiredDoc;

    @Column(name="GENERAL_ATTACH")
    private String generalAttachment;

    /*@Column(name="GENERAL_COMMENTS")
    private Set<Attachment> generalAttachments;*/
}
