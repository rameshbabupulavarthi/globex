package com.globex.model.vo;

import com.globex.model.entity.partner.AccountDetails;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 12/31/2016.
 */
@Data
public class AccountDetailsDO implements Serializable {

    private Integer id;

    private String countryList;

    private String territoryComment;

    private String selectedCountry;

    private String authLobLimit;

    private String minimumPremium;

    private String authLobCurrency;

    private String authLobpPremiumReverse;

    private String comEstablishmentYear;

    private String authLobeExactTaxes;

    private String authLobExactTaxes1Text1;

    private Integer taxResponsible;

    private Integer flatPercentage;

    private Integer taxApplied;

    private String lobTariffComment;

    private String lobTariffCommentAttach;//file

    private String reInsurancecCommission;

    private String lobMandatoryClauses;

    private String lobMandatoryClausesAttach;

    private Integer locCurrRefLocPolicy;

    private String locCurrRefLocPolicyText;

    private Integer forJuriAvalLocPolicy;

    private String forJuriAvalLocPolicyText;

    private Integer manuscriptAval;

    private String manuscriptAvalText;

    private Integer forReinsurSupport;

    private String forReinsurSupportText;

    private String insuRequiredDoc;//file

    private String insuRequiredDocAttach;

    private Integer taxId;

    private String taxIdText;

    private Integer serviceOption;

    private String serviceOptionText;

    private Integer reinsurBroker;

    private String reinsurBrokerText;

    private Integer regRegulator;

    private String regRegulatorText;

    private String registrationProcedure;

    private String registrationProcedureAttach;//file

    //
    private String selectedcountry;

    private String requiredDocReinsurPlace;

    private String requiredDocReinsurPlaceAttach;

    private String specReqDocReinsurPlace;

    private String specReqDocReinsurPlaceAttach;

    private Integer compInvolClaims;

    private String compInvolClaimsText;

    private Integer claimPayMasterLocal;

    private String claimPayMasterLocalText;

    private String claimHandlingWordingAttach;//file

    private Integer premiumPayOption;

    private Integer premiumWithTax;

    private Integer nonAdmittedPolicy;

    private String nonAdmittedPolicyText;

    private String nonAdmittedComments;

    private Integer mandatoryReinsurCession;

    private String mandatoryReinsurCessionText;

    private String mandatoryReinsurComments;

    private String policyLang;

    private Integer tacitRenewal;

    private String tacitRenewalText;

    private String tacitRenewalComments;

    private String network;

    private Integer cashBeforeCoverReq;

    private String cashBeforeCoverReqText;

    private Integer premiumPaymentWarranty;

    private String premiumPaymentWarrantyText;

    private Integer premiumPaymentWarrantyDays;

    private Integer foreignCurrencyAccept;

    private String foreignCurrencyAcceptText;

    private String acceptableCurrency;

    private String premiumRemittanceTime;

    private Integer backDatingPossible;

    private String backDatingPossibleText;

    private Integer localCurrencyRequirement;

    private String localCurrencyRequirementText;

    private Integer stateReinsurInvolv;

    private String stateReinsurInvolvText;

    private String accounting;

    private String generalComments;

    private String generalAttachment;//file

    private String policyFormsWordingAttach;



   private String authlobexacttaxes1text1;
   private String authlobexacttaxes2text1;
   private String authlobexacttaxes3text1;
   private String authlobexacttaxes4text1;
   private String authlobexacttaxes5text1;
   private String authlobexacttaxes6text1;
   private String authLobExactTaxes1Text2;
   private String authlobexacttaxes2text2;
   private String authlobexacttaxes3text2;
   private String authlobexacttaxes4text2;
   private String authlobexacttaxes5text2;
   private String authlobexacttaxes6text2;


    public AccountDetailsDO(){}

    public AccountDetailsDO(AccountDetails accountDetails){

       id=accountDetails.getId();
       countryList=accountDetails.getCountryList();
       territoryComment=accountDetails.getTerritoryComment();
       selectedCountry=accountDetails.getSelectedCountry();
       authLobLimit=accountDetails.getAuthLobLimit();
       minimumPremium=accountDetails.getMinimumPremium();
       authLobCurrency=accountDetails.getAuthLobCurrency();
       authLobpPremiumReverse=accountDetails.getAuthLobpPremiumReverse();
       comEstablishmentYear=accountDetails.getComEstablishmentYear();
       authLobeExactTaxes=accountDetails.getAuthLobeExactTaxes();
       authLobExactTaxes1Text1=accountDetails.getAuthLobExactTaxes1Text1();
       authLobExactTaxes1Text2=accountDetails.getAuthLobExactTaxes1Text2();
       taxResponsible=accountDetails.getTaxResponsible();
       flatPercentage=accountDetails.getFlatPercentage();
       taxApplied=accountDetails.getTaxApplied();
       lobTariffComment=accountDetails.getLobTariffComment();
       lobTariffCommentAttach=accountDetails.getLobTariffCommentAttach();//file
       reInsurancecCommission=accountDetails.getReInsurancecCommission();
       lobMandatoryClauses=accountDetails.getLobMandatoryClauses();
       lobMandatoryClausesAttach=accountDetails.getLobMandatoryClausesAttach();
       locCurrRefLocPolicy=accountDetails.getLocCurrRefLocPolicy();
       locCurrRefLocPolicyText=accountDetails.getLocCurrRefLocPolicyText();
       forJuriAvalLocPolicy=accountDetails.getForJuriAvalLocPolicy();
       forJuriAvalLocPolicyText=accountDetails.getForJuriAvalLocPolicyText();
       manuscriptAval=accountDetails.getManuscriptAval();
       manuscriptAvalText=accountDetails.getManuscriptAvalText();
       forReinsurSupport=accountDetails.getForReinsurSupport();
       forReinsurSupportText=accountDetails.getForReinsurSupportText();
       insuRequiredDoc=accountDetails.getInsuRequiredDoc();//file
       insuRequiredDocAttach=accountDetails.getInsuRequiredDocAttach();
       taxId=accountDetails.getTaxId();
       taxIdText=accountDetails.getTaxIdText();
       serviceOption=accountDetails.getServiceOption();
       serviceOptionText=accountDetails.getServiceOptionText();
       reinsurBroker=accountDetails.getReinsurBroker();
       reinsurBrokerText=accountDetails.getReinsurBrokerText();
       regRegulator=accountDetails.getRegRegulator();
       regRegulatorText=accountDetails.getRegRegulatorText();
       registrationProcedure=accountDetails.getRegistrationProcedure();
       registrationProcedureAttach=accountDetails.getRegistrationProcedureAttach();
       requiredDocReinsurPlace=accountDetails.getRequiredDocReinsurPlace();
       requiredDocReinsurPlaceAttach=accountDetails.getRequiredDocReinsurPlace();
       specReqDocReinsurPlace=accountDetails.getSpecReqDocReinsurPlace();
       specReqDocReinsurPlaceAttach=accountDetails.getSpecReqDocReinsurPlaceAttach();
       compInvolClaims=accountDetails.getCompInvolClaims();
       compInvolClaimsText=accountDetails.getCompInvolClaimsText();
       claimPayMasterLocal=accountDetails.getClaimPayMasterLocal();
       claimPayMasterLocalText=accountDetails.getClaimPayMasterLocalText();
       claimHandlingWordingAttach=accountDetails.getClaimHandlingWordingAttach();//
       premiumPayOption=accountDetails.getPremiumPayOption();
       premiumWithTax=accountDetails.getPremiumWithTax();
       nonAdmittedPolicy=accountDetails.getNonAdmittedPolicy();
       nonAdmittedPolicyText=accountDetails.getNonAdmittedPolicyText();
       nonAdmittedComments=accountDetails.getNonAdmittedComments();
       mandatoryReinsurCession=accountDetails.getMandatoryReinsurCession();
       mandatoryReinsurCessionText=accountDetails.getMandatoryReinsurCessionText();
       mandatoryReinsurComments=accountDetails.getMandatoryReinsurComments();
       policyLang=accountDetails.getPolicyLang();
        tacitRenewal=accountDetails.getTacitRenewal();
       tacitRenewalText=accountDetails.getTacitRenewalText();
       tacitRenewalComments=accountDetails.getTacitRenewalComments();
       network=accountDetails.getNetwork();
       cashBeforeCoverReq=accountDetails.getCashBeforeCoverReq();
       cashBeforeCoverReqText=accountDetails.getCashBeforeCoverReqText();
       premiumPaymentWarranty=accountDetails.getPremiumPaymentWarranty();
       premiumPaymentWarrantyText=accountDetails.getPremiumPaymentWarrantyText();
       premiumPaymentWarrantyDays=accountDetails.getPremiumPaymentWarrantyDays();
       foreignCurrencyAccept=accountDetails.getForeignCurrencyAccept();
       foreignCurrencyAcceptText=accountDetails.getForeignCurrencyAcceptText();
       acceptableCurrency=accountDetails.getAcceptableCurrency();
       premiumRemittanceTime=accountDetails.getPremiumRemittanceTime();
       backDatingPossible=accountDetails.getBackDatingPossible();
       backDatingPossibleText=accountDetails.getBackDatingPossibleText();
       localCurrencyRequirement=accountDetails.getLocalCurrencyRequirement();
       localCurrencyRequirementText=accountDetails.getLocalCurrencyRequirementText();
       stateReinsurInvolv=accountDetails.getStateReinsurInvolv();
       stateReinsurInvolvText=accountDetails.getStateReinsurInvolvText();
       accounting=accountDetails.getAccounting();
       generalComments=accountDetails.getGeneralComments();
       generalAttachment=accountDetails.getGeneralAttachment();//file
       policyFormsWordingAttach=accountDetails.getPolicyFormsWordingAttach();
    }

   public AccountDetails getValue(){

       AccountDetails accountDetails=new AccountDetails();
       accountDetails.setId(id);
       accountDetails.setCountryList(countryList);
       accountDetails.setTerritoryComment(territoryComment);
       accountDetails.setSelectedCountry(selectedCountry);
       accountDetails.setAuthLobLimit(authLobLimit);
       accountDetails.setMinimumPremium(minimumPremium);
       accountDetails.setAuthLobCurrency(authLobCurrency);
       accountDetails.setAuthLobpPremiumReverse(authLobpPremiumReverse);
       accountDetails.setComEstablishmentYear(comEstablishmentYear);
       accountDetails.setAuthLobeExactTaxes(authLobeExactTaxes);
       accountDetails.setAuthLobExactTaxes1Text1(authLobExactTaxes1Text1);
       accountDetails.setAuthLobExactTaxes1Text2(authLobExactTaxes1Text2);
       accountDetails.setTaxResponsible(taxResponsible);
       accountDetails.setFlatPercentage(flatPercentage);
       accountDetails.setTaxApplied(taxApplied);
       accountDetails.setLobTariffComment(lobTariffComment);
       accountDetails.setLobTariffCommentAttach(lobTariffCommentAttach);//file
       accountDetails.setReInsurancecCommission(reInsurancecCommission);
       accountDetails.setLobMandatoryClauses(lobMandatoryClauses);
       accountDetails.setLobMandatoryClausesAttach(lobMandatoryClausesAttach);
       accountDetails.setLocCurrRefLocPolicy(locCurrRefLocPolicy);
       accountDetails.setLocCurrRefLocPolicyText(locCurrRefLocPolicyText);
       accountDetails.setForJuriAvalLocPolicy(forJuriAvalLocPolicy);
       accountDetails.setForJuriAvalLocPolicyText(forJuriAvalLocPolicyText);
       accountDetails.setManuscriptAval(manuscriptAval);
       accountDetails.setManuscriptAvalText(manuscriptAvalText);
       accountDetails.setForReinsurSupport(forReinsurSupport);
       accountDetails.setForReinsurSupportText(forReinsurSupportText);
       accountDetails.setInsuRequiredDoc(insuRequiredDoc);//file
       accountDetails.setInsuRequiredDocAttach(insuRequiredDocAttach);
       accountDetails.setTaxId(taxId);
       accountDetails.setTaxIdText(taxIdText);
       accountDetails.setServiceOption(serviceOption);
       accountDetails.setServiceOptionText(serviceOptionText);
       accountDetails.setReinsurBroker(reinsurBroker);
       accountDetails.setReinsurBrokerText(reinsurBrokerText);
       accountDetails.setRegRegulator(regRegulator);
       accountDetails.setRegRegulatorText(regRegulatorText);
       accountDetails.setRegistrationProcedure(registrationProcedure);
       accountDetails.setRegistrationProcedureAttach(registrationProcedureAttach);
       accountDetails.setRequiredDocReinsurPlace(requiredDocReinsurPlace);
       accountDetails.setRequiredDocReinsurPlace(requiredDocReinsurPlaceAttach);
       accountDetails.setSpecReqDocReinsurPlace(specReqDocReinsurPlace);
       accountDetails.setSpecReqDocReinsurPlaceAttach(specReqDocReinsurPlaceAttach);
       accountDetails.setCompInvolClaims(compInvolClaims);
       accountDetails.setCompInvolClaimsText(compInvolClaimsText);
       accountDetails.setClaimPayMasterLocal(claimPayMasterLocal);
       accountDetails.setClaimPayMasterLocalText(claimPayMasterLocalText);
       accountDetails.setClaimHandlingWordingAttach(claimHandlingWordingAttach);//
       accountDetails.setPremiumPayOption(premiumPayOption);
       accountDetails.setPremiumWithTax(premiumWithTax);
       accountDetails.setNonAdmittedPolicy(nonAdmittedPolicy);
       accountDetails.setNonAdmittedPolicyText(nonAdmittedPolicyText);
       accountDetails.setNonAdmittedComments(nonAdmittedComments);
       accountDetails.setMandatoryReinsurCession(mandatoryReinsurCession);
       accountDetails.setMandatoryReinsurCessionText(mandatoryReinsurCessionText);
       accountDetails.setMandatoryReinsurComments(mandatoryReinsurComments);
       accountDetails.setPolicyLang(policyLang);
       accountDetails.setTacitRenewal(tacitRenewal);
       accountDetails.setTacitRenewalText(tacitRenewalText);
       accountDetails.setTacitRenewalComments(tacitRenewalComments);
       accountDetails.setNetwork(network);
       accountDetails.setCashBeforeCoverReq(cashBeforeCoverReq);
       accountDetails.setCashBeforeCoverReqText(cashBeforeCoverReqText);
       accountDetails.setPremiumPaymentWarranty(premiumPaymentWarranty);
       accountDetails.setPremiumPaymentWarrantyText(premiumPaymentWarrantyText);
       accountDetails.setPremiumPaymentWarrantyDays(premiumPaymentWarrantyDays);
       accountDetails.setForeignCurrencyAccept(foreignCurrencyAccept);
       accountDetails.setForeignCurrencyAcceptText(foreignCurrencyAcceptText);
       accountDetails.setAcceptableCurrency(acceptableCurrency);
       accountDetails.setPremiumRemittanceTime(premiumRemittanceTime);
       accountDetails.setBackDatingPossible(backDatingPossible);
       accountDetails.setBackDatingPossibleText(backDatingPossibleText);
       accountDetails.setLocalCurrencyRequirement(localCurrencyRequirement);
       accountDetails.setLocalCurrencyRequirementText(localCurrencyRequirementText);
       accountDetails.setStateReinsurInvolv(stateReinsurInvolv);
       accountDetails.setStateReinsurInvolvText(stateReinsurInvolvText);
       accountDetails.setAccounting(accounting);
       accountDetails.setGeneralComments(generalComments);
       accountDetails.setGeneralAttachment(generalAttachment);//file
       accountDetails.setPolicyFormsWordingAttach(policyFormsWordingAttach);

       return accountDetails;
   }
}
