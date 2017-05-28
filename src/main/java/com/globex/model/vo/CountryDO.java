package com.globex.model.vo;

import com.globex.model.entity.common.Clause;
import com.globex.model.entity.common.Country;
import com.globex.model.entity.common.RateRequirement;
import com.globex.model.entity.common.Tax;
import com.globex.model.vo.common.ClauseDO;
import com.globex.model.vo.common.RateRequirementDO;
import com.globex.model.vo.common.TaxDO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sunil Golla on 4/23/2017.
 */
@Data
@ToString(exclude = {"taxes","rateRequirements","clauses"})
public class CountryDO implements Serializable{

    private Long countryId;

    private String country;

    private String territoryComments;

    private Short locCurOnLocPol;

    private String locCurOnLocPolComments;

    private Short foreignLawOnLocalPolicy;

    private String foreignLawOnLocalPolicyComments;

    private Short useManuScript;

    private String manuScriptLOB;

    private String manuScriptComments;

    private Short reInsuranceSupport;

    private String reInsuranceSupportLOB;

    private String reInsuranceSupportComments;

    private Short foreignReinsurerRegistered;

    private String foreignReinsurerRegisteredComments;

    private String foreignReinsurerRegisteredAdvice;

    private String infoReqdForPolicyInsurance;

    private String premiumCollectionType;

    private Short nonAdmittedAllowed;

    private String nonAdmittedComments;

    private Short mandatoryReInsuranceCession;

    private String mandatoryReInsuranceComments;

    private Short tacitRenewal;

    private String tacitRenewalReasons;

    private String tacticalRenewalComments;

    private Short cashBeforeCoverReq;

    private String cashBeforeCoverReqComments;

    private Short localCurrencyReq;

    private String localCurrencyReqComments;

    private String stateReinsurerReqLOB;

    private String stateReinsurerReq;

    private String stateReinsurerReqComments;

    private String otherRequirements;

    private String generalComments;

    private Long createdBy;

    private Long updatedBy;

    private Set<TaxDO> taxes;

    private Set<RateRequirementDO> rateRequirements;

    private Set<ClauseDO> clauses;


    private String taxTypesJsonStr;

    private String taxRequirementsJsonStr;

    private String clausesJsonStr;

    public CountryDO(){

    }

    public CountryDO(Country country){

        countryId=country.getId();
        this.country=country.getCountry();
        this.territoryComments=country.getTerritoryComments();
        this.locCurOnLocPol=country.getLocCurOnLocPol();
        this.locCurOnLocPolComments=country.getLocCurOnLocPolComments();
        this.foreignLawOnLocalPolicy=country.getForeignLawOnLocalPolicy();
        this.foreignLawOnLocalPolicyComments=country.getForeignLawOnLocalPolicyComments();
        this.useManuScript=country.getUseManuScript();
        this.manuScriptLOB=country.getManuScriptLOB();
        this.manuScriptComments=country.getManuScriptComments();
        this.reInsuranceSupport=country.getReInsuranceSupport();
        this.reInsuranceSupportLOB=country.getReInsuranceSupportLOB();
        this.reInsuranceSupportComments=country.getReInsuranceSupportComments();
        this.foreignReinsurerRegistered=country.getForeignReinsurerRegistered();
        this.foreignReinsurerRegisteredComments=country.getForeignReinsurerRegisteredComments();
        this.foreignReinsurerRegisteredAdvice=country.getForeignReinsurerRegisteredAdvice();
        this.infoReqdForPolicyInsurance=country.getInfoReqdForPolicyInsurance();
        this.premiumCollectionType=country.getPremiumCollectionType();
        this.nonAdmittedAllowed=country.getNonAdmittedAllowed();
        this.nonAdmittedComments=country.getNonAdmittedComments();
        this.mandatoryReInsuranceCession=country.getMandatoryReInsuranceCession();
        this.mandatoryReInsuranceComments=country.getMandatoryReInsuranceComments();
        this.tacitRenewal=country.getTacitRenewal();
        this.tacitRenewalReasons=country.getTacitRenewalReason();
        this.tacticalRenewalComments=country.getTacticalRenewalComments();
        this.cashBeforeCoverReq=country.getCashBeforeCoverReq();
        this.cashBeforeCoverReqComments=country.getCashBeforeCoverReqComments();
        this.localCurrencyReq=country.getLocalCurrencyReq();
        this.localCurrencyReqComments=country.getLocalCurrencyReqComments();
        this.stateReinsurerReqLOB=country.getStateReinsurerReqLOB();
        this.stateReinsurerReq=country.getStateReinsurerReq();
        this.stateReinsurerReqComments=country.getStateReinsurerReqComments();
        this.otherRequirements=country.getOtherRequirements();
        this.generalComments=country.getGeneralComments();
        this.createdBy=country.getCreatedBy();
        this.updatedBy=country.getUpdatedBy();


    }

    public void loadFullDetails(Country country){
        Set<Tax> taxes=country.getTaxes();
        Set<RateRequirement> rateRequirements= country.getRateRequirements();
        Set<Clause> clauses=country.getClauses();

        Set<TaxDO> taxDOs=new HashSet<TaxDO>();
        Set<RateRequirementDO> rateRequirementDOs=new HashSet<RateRequirementDO>();
        Set<ClauseDO> clauseDOs=new HashSet<ClauseDO>();
        for(Tax tax:taxes){
            TaxDO taxDO=new TaxDO(tax);
            taxDOs.add(taxDO);
        }
        for(RateRequirement rateRequirement:rateRequirements){
            RateRequirementDO rateRequirementDO=new RateRequirementDO(rateRequirement);
            rateRequirementDOs.add(rateRequirementDO);
        }
        for(Clause clause:clauses){
            ClauseDO clauseDO=new ClauseDO(clause);
            clauseDOs.add(clauseDO);
        }
        this.taxes=taxDOs;
        this.rateRequirements=rateRequirementDOs;
        this.clauses=clauseDOs;
    }

    public Country value(){
        Country country=new Country();
        country.setId(this.getCountryId());
        country.setCountry(this.getCountry());
        country.setTerritoryComments(this.getTerritoryComments());
        country.setLocCurOnLocPol(this.getLocCurOnLocPol());
        country.setLocCurOnLocPolComments(this.getLocCurOnLocPolComments());
        country.setForeignLawOnLocalPolicy(this.getForeignLawOnLocalPolicy());
        country.setForeignLawOnLocalPolicyComments(this.getForeignLawOnLocalPolicyComments());
        country.setUseManuScript(this.getUseManuScript());
        country.setManuScriptLOB(this.getManuScriptLOB());
        country.setManuScriptComments(this.getManuScriptComments());
        country.setReInsuranceSupport(this.getReInsuranceSupport());
        country.setReInsuranceSupportLOB(this.getReInsuranceSupportLOB());
        country.setReInsuranceSupportComments(this.getReInsuranceSupportComments());
        country.setForeignReinsurerRegistered(this.getForeignReinsurerRegistered());
        country.setForeignReinsurerRegisteredComments(this.getForeignReinsurerRegisteredComments());
        country.setForeignReinsurerRegisteredAdvice(this.getForeignReinsurerRegisteredAdvice());
        country.setInfoReqdForPolicyInsurance(this.getInfoReqdForPolicyInsurance());
        country.setPremiumCollectionType(this.getPremiumCollectionType());
        country.setNonAdmittedAllowed(this.getNonAdmittedAllowed());
        country.setNonAdmittedComments(this.getNonAdmittedComments());
        country.setMandatoryReInsuranceCession(this.getMandatoryReInsuranceCession());
        country.setMandatoryReInsuranceComments(this.getMandatoryReInsuranceComments());
        country.setTacitRenewal(this.getTacitRenewal());
        country.setTacitRenewalReason(this.getTacitRenewalReasons());
        country.setTacticalRenewalComments(this.getTacticalRenewalComments());
        country.setCashBeforeCoverReq(this.getCashBeforeCoverReq());
        country.setCashBeforeCoverReqComments(this.getCashBeforeCoverReqComments());
        country.setLocalCurrencyReq(this.getLocalCurrencyReq());
        country.setLocalCurrencyReqComments(this.getLocalCurrencyReqComments());
        country.setStateReinsurerReqLOB(this.getStateReinsurerReqLOB());
        country.setStateReinsurerReq(this.getStateReinsurerReq());
        country.setStateReinsurerReqComments(this.getStateReinsurerReqComments());
        country.setOtherRequirements(this.getOtherRequirements());
        country.setGeneralComments(this.getGeneralComments());
        country.setCreatedBy(this.getCreatedBy());
        country.setUpdatedBy(this.getUpdatedBy());

        Set<Tax> taxes=getTaxes(this.getTaxes(),country);
        Set<RateRequirement> rateRequirements=getRateRequirements(this.getRateRequirements(),country);
        Set<Clause> clauses=getClauses(this.getClauses(),country);

        country.setTaxes(taxes);
        country.setRateRequirements(rateRequirements);
        country.setClauses(clauses);
        return country;
    }

    private Set<Tax> getTaxes(Set<TaxDO> taxDOs,Country country){
        if(taxDOs!=null && !taxDOs.isEmpty()){
            Set<Tax> taxes=new HashSet<Tax>();
            for(TaxDO taxDO:taxDOs){
                Tax tax=taxDO.value();
                tax.setCountry(country);
                taxes.add(tax);
            }
            return taxes;
        }
        return null;
    }

    private Set<RateRequirement> getRateRequirements(Set<RateRequirementDO> rateRequirementDOs,Country country){
        if(rateRequirementDOs!=null && !rateRequirementDOs.isEmpty()){
            Set<RateRequirement> rateRequirements=new HashSet<RateRequirement>();
            for(RateRequirementDO rateRequirementDO:rateRequirementDOs){
                RateRequirement rateRequirement=rateRequirementDO.value();
                rateRequirement.setCountry(country);
                rateRequirements.add(rateRequirement);
            }
            return rateRequirements;
        }
        return null;
    }

    private Set<Clause> getClauses(Set<ClauseDO> clauseDOs,Country country){
        if(clauseDOs!=null && !clauseDOs.isEmpty()){
            Set<Clause> clauses=new HashSet<Clause>();
            for(ClauseDO clauseDO:clauseDOs){
                Clause clause=clauseDO.value();
                clause.setCountry(country);
                clauses.add(clause);
            }
            return clauses;
        }
        return null;
    }
}
