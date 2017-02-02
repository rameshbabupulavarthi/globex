package com.globex.model.entity.partner;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Ramesh on 29-12-2016.
 */
@Data
@Entity
@Table(name="t_account")
public class AccountDetails implements Serializable {


    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="countrylist")
    private String countryList;

    @Column(name="territoryComment")
    private String territoryComment;

    @Column(name="selectedCountry")
    private String selectedCountry;

    @Column(name="authLobLimit")
    private String authLobLimit;

    @Column(name="minimumPremium")
    private String minimumPremium;

    @Column(name="authLobCurrency")
    private String authLobCurrency;

    @Column(name="authLobpPremiumReverse")
    private String authLobpPremiumReverse;

    @Column(name="comEstablishmentYear")
    private String comEstablishmentYear;

    @Column(name="authLobeExactTaxes")
    private String authLobeExactTaxes;

    @Column(name="authLobExactTaxes1Text1")
    private String authLobExactTaxes1Text1;

    @Column(name="authLobExactTaxes1Text2")
    private String authLobExactTaxes1Text2;

    @Column(name="taxResponsible")
    private Integer taxResponsible;

    @Column(name="flatPercentage")
    private Integer flatPercentage;

    @Column(name="taxApplied")
    private Integer taxApplied;

    @Column(name="lobTariffComment")
    private String lobTariffComment;

    @Column(name="lobTariffCommentAttach")
    private String lobTariffCommentAttach;//file

    @Column(name="reInsurancecCommission")
    private String reInsurancecCommission;

    @Column(name="lobMandatoryClauses")
    private String lobMandatoryClauses;

    @Column(name="lobMandatoryClausesAttach")
    private String lobMandatoryClausesAttach;

    @Column(name="locCurrRefLocPolicy")
    private Integer locCurrRefLocPolicy;

    @Column(name="locCurrRefLocPolicyText")
    private String locCurrRefLocPolicyText;

    @Column(name="forJuriAvalLocPolicy")
    private Integer forJuriAvalLocPolicy;

    @Column(name="forJuriAvalLocPolicyText")
    private String forJuriAvalLocPolicyText;

    @Column(name="manuscriptAval")
    private Integer manuscriptAval;

    @Column(name="manuscriptAvalText")
    private String manuscriptAvalText;

    @Column(name="forReinsurSupport")
    private Integer forReinsurSupport;

    @Column(name="forReinsurSupportText")
    private String forReinsurSupportText;

    @Column(name="insuRequiredDoc")
    private String insuRequiredDoc;//file

    @Column(name="insuRequiredDocAttach")
    private String insuRequiredDocAttach;

    @Column(name="taxId")
    private Integer taxId;

    @Column(name="taxIdText")
    private String taxIdText;

    @Column(name="serviceOption")
    private Integer serviceOption;

    @Column(name="serviceOptionText")
    private String serviceOptionText;

    @Column(name="reinsurBroker")
    private Integer reinsurBroker;

    @Column(name="reinsurBrokerText")
    private String reinsurBrokerText;

    @Column(name="regRegulator")
    private Integer regRegulator;

    @Column(name="regRegulatorText")
    private String regRegulatorText;

    @Column(name="registrationProcedure")
    private String registrationProcedure;

    @Column(name="registrationProcedureAttach")
    private String registrationProcedureAttach;//file

    //
    //private String selectedcountry;

    @Column(name="requiredDocReinsurPlace")
    private String requiredDocReinsurPlace;

    @Column(name="requiredDocReinsurPlaceAttach")
    private String requiredDocReinsurPlaceAttach;

    @Column(name="specReqDocReinsurPlace")
    private String specReqDocReinsurPlace;

    @Column(name="specReqDocReinsurPlaceAttach")
    private String specReqDocReinsurPlaceAttach;

    @Column(name="compInvolClaims")
    private Integer compInvolClaims;

    @Column(name="compInvolClaimsText")
    private String compInvolClaimsText;

    @Column(name="claimPayMasterLocal")
    private Integer claimPayMasterLocal;

    @Column(name="claimPayMasterLocalText")
    private String claimPayMasterLocalText;

    @Column(name="claimHandlingWordingAttach")
    private String claimHandlingWordingAttach;//file

    @Column(name="premiumPayOption")
    private Integer premiumPayOption;

    @Column(name="premiumWithTax")
    private Integer premiumWithTax;

    @Column(name="nonAdmittedPolicy")
    private Integer nonAdmittedPolicy;

    @Column(name="nonAdmittedPolicyText")
    private String nonAdmittedPolicyText;

    @Column(name="nonAdmittedComments")
    private String nonAdmittedComments;

    @Column(name="mandatoryReinsurCession")
    private Integer mandatoryReinsurCession;

    @Column(name="mandatoryReinsurCessionText")
    private String mandatoryReinsurCessionText;

    @Column(name="mandatoryReinsurComments")
    private String mandatoryReinsurComments;

    @Column(name="policyLang")
    private String policyLang;

    @Column(name="tacitRenewal")
    private Integer tacitRenewal;

    @Column(name="tacitRenewalText")
    private String tacitRenewalText;

    @Column(name="tacitRenewalComments")
    private String tacitRenewalComments;

    @Column(name="network")
    private String network;

    @Column(name="cashBeforeCoverReq")
    private Integer cashBeforeCoverReq;

    @Column(name="cashBeforeCoverReqText")
    private String cashBeforeCoverReqText;

    @Column(name="premiumPaymentWarranty")
    private Integer premiumPaymentWarranty;

    @Column(name="premiumPaymentWarrantyText")
    private String premiumPaymentWarrantyText;

    @Column(name="premiumPaymentWarrantyDays")
    private Integer premiumPaymentWarrantyDays;

    @Column(name="foreignCurrencyAccept")
    private Integer foreignCurrencyAccept;

    @Column(name="foreignCurrencyAcceptText")
    private String foreignCurrencyAcceptText;

    @Column(name="acceptableCurrency")
    private String acceptableCurrency;

    @Column(name="premiumRemittanceTime")
    private String premiumRemittanceTime;

    @Column(name="backDatingPossible")
    private Integer backDatingPossible;

    @Column(name="backDatingPossibleText")
    private String backDatingPossibleText;

    @Column(name="localCurrencyRequirement")
    private Integer localCurrencyRequirement;

    @Column(name="localCurrencyRequirementText")
    private String localCurrencyRequirementText;

    @Column(name="stateReinsurInvolv")
    private Integer stateReinsurInvolv;

    @Column(name="stateReinsurInvolvText")
    private String stateReinsurInvolvText;

    @Column(name="accounting")
    private String accounting;

    @Column(name="generalComments")
    private String generalComments;

    @Column(name="generalAttachment")
    private String generalAttachment;//file

    @Column(name="policyFormsWordingAttach")
    private String policyFormsWordingAttach;

}