package com.globex.model.vo;

import com.globex.model.entity.common.Clause;
import com.globex.model.entity.common.Country;
import com.globex.model.entity.common.RateRequirement;
import com.globex.model.entity.common.Tax;
import com.globex.model.vo.common.ClauseDO;
import com.globex.model.vo.common.RateRequirementDO;
import com.globex.model.vo.common.TaxDO;
import com.utils.StringUtils;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    private String nonAdmittedLob;

    private Short mandatoryReInsuranceCession;

    private String mandatoryReInsuranceComments;

    private String mandatoryReInsuranceLob;

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

    private String insuRequiredDoc;

    private String generalAttachment;

    private List<TaxDO> taxes;

    private List<RateRequirementDO> rateRequirements;

    private List<ClauseDO> clauses;


    private String taxTypesJsonStr;

    private String taxRequirementsJsonStr;

    private String clausesJsonStr;

    private CommonsMultipartFile clauseAttachment;

    private CommonsMultipartFile insuRequiredDocFile;

    private CommonsMultipartFile generalAttachmentFile;

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
        this.nonAdmittedLob=country.getNonAdmittedLob();
        this.mandatoryReInsuranceCession=country.getMandatoryReInsuranceCession();
        this.mandatoryReInsuranceComments=country.getMandatoryReInsuranceComments();
        this.mandatoryReInsuranceLob=country.getMandatoryReInsuranceLob();
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

        this.setInsuRequiredDoc(country.getInsuRequiredDoc());
        this.setGeneralAttachment(country.getGeneralAttachment());
    }

    public void loadFullDetails(Country country){
        List<Tax> taxes=country.getTaxes();
        List<RateRequirement> rateRequirements= country.getRateRequirements();
        List<Clause> clauses=country.getClauses();

        List<TaxDO> taxDOs=new ArrayList<TaxDO>();
        List<RateRequirementDO> rateRequirementDOs=new ArrayList<RateRequirementDO>();
        List<ClauseDO> clauseDOs=new ArrayList<ClauseDO>();
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
        country.setCountry(StringUtils.getValue(this.getCountry()));
        country.setTerritoryComments(StringUtils.getValue(this.getTerritoryComments()));
        country.setLocCurOnLocPol(this.getLocCurOnLocPol());
        country.setLocCurOnLocPolComments(StringUtils.getValue(this.getLocCurOnLocPolComments()));
        country.setForeignLawOnLocalPolicy(this.getForeignLawOnLocalPolicy());
        country.setForeignLawOnLocalPolicyComments(StringUtils.getValue(this.getForeignLawOnLocalPolicyComments()));
        country.setUseManuScript(this.getUseManuScript());
        country.setManuScriptLOB(StringUtils.getValue(this.getManuScriptLOB()));
        country.setManuScriptComments(StringUtils.getValue(this.getManuScriptComments()));
        country.setReInsuranceSupport(this.getReInsuranceSupport());
        country.setReInsuranceSupportLOB(StringUtils.getValue(getReInsuranceSupportLOB()));
        country.setReInsuranceSupportComments(StringUtils.getValue(getReInsuranceSupportComments()));
        country.setForeignReinsurerRegistered(getForeignReinsurerRegistered());
        country.setForeignReinsurerRegisteredComments(StringUtils.getValue(getForeignReinsurerRegisteredComments()));
        country.setForeignReinsurerRegisteredAdvice(StringUtils.getValue(getForeignReinsurerRegisteredAdvice()));
        country.setInfoReqdForPolicyInsurance(StringUtils.getValue(getInfoReqdForPolicyInsurance()));
        country.setPremiumCollectionType(StringUtils.getValue(getPremiumCollectionType()));
        country.setNonAdmittedAllowed(getNonAdmittedAllowed());
        country.setNonAdmittedComments(StringUtils.getValue(getNonAdmittedComments()));
        country.setNonAdmittedLob(getNonAdmittedLob());
        country.setMandatoryReInsuranceCession(getMandatoryReInsuranceCession());
        country.setMandatoryReInsuranceComments(StringUtils.getValue(getMandatoryReInsuranceComments()));
        country.setMandatoryReInsuranceLob(StringUtils.getValue(getMandatoryReInsuranceLob()));
        country.setTacitRenewal(getTacitRenewal());
        country.setTacitRenewalReason(StringUtils.getValue(getTacitRenewalReasons()));
        country.setTacticalRenewalComments(StringUtils.getValue(getTacticalRenewalComments()));
        country.setCashBeforeCoverReq(getCashBeforeCoverReq());
        country.setCashBeforeCoverReqComments(StringUtils.getValue(getCashBeforeCoverReqComments()));
        country.setLocalCurrencyReq(getLocalCurrencyReq());
        country.setLocalCurrencyReqComments(StringUtils.getValue(getLocalCurrencyReqComments()));
        country.setStateReinsurerReqLOB(StringUtils.getValue(getStateReinsurerReqLOB()));
        country.setStateReinsurerReq(StringUtils.getValue(getStateReinsurerReq()));
        country.setStateReinsurerReqComments(StringUtils.getValue(getStateReinsurerReqComments()));
        country.setOtherRequirements(StringUtils.getValue(getOtherRequirements()));
        country.setGeneralComments(StringUtils.getValue(getGeneralComments()));
        country.setCreatedBy(getCreatedBy());
        country.setUpdatedBy(getUpdatedBy());

        country.setInsuRequiredDoc(StringUtils.getValue(getInsuRequiredDoc()));
        country.setGeneralAttachment(StringUtils.getValue(getGeneralAttachment()));

        List<Tax> taxes=getTaxes(this.getTaxes(),country);
        List<RateRequirement> rateRequirements=getRateRequirements(this.getRateRequirements(),country);
        List<Clause> clauses=getClauses(this.getClauses(),country);

        country.setTaxes(taxes);
        country.setRateRequirements(rateRequirements);
        country.setClauses(clauses);
        return country;
    }

    private List<Tax> getTaxes(List<TaxDO> taxDOs,Country country){
        if(taxDOs!=null && !taxDOs.isEmpty()){
            List<Tax> taxes=new ArrayList<Tax>();
            for(TaxDO taxDO:taxDOs){
                Tax tax=taxDO.value();
                tax.setCountry(country);
                taxes.add(tax);
            }
            return taxes;
        }
        return null;
    }

    private List<RateRequirement> getRateRequirements(List<RateRequirementDO> rateRequirementDOs,Country country){
        if(rateRequirementDOs!=null && !rateRequirementDOs.isEmpty()){
            List<RateRequirement> rateRequirements=new ArrayList<RateRequirement>();
            for(RateRequirementDO rateRequirementDO:rateRequirementDOs){
                RateRequirement rateRequirement=rateRequirementDO.value();
                rateRequirement.setCountry(country);
                rateRequirements.add(rateRequirement);
            }
            return rateRequirements;
        }
        return null;
    }

    private List<Clause> getClauses(List<ClauseDO> clauseDOs,Country country){
        if(clauseDOs!=null && !clauseDOs.isEmpty()){
            List<Clause> clauses=new ArrayList<Clause>();
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
