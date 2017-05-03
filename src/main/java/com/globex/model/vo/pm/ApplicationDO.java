package com.globex.model.vo.pm;

import com.globex.model.entity.common.Application;
import com.globex.model.entity.common.ExposureData;
import com.globex.model.entity.common.LocalBrokerInsuredContact;
import com.globex.model.vo.ExposureDataDO;
import com.globex.model.vo.LocalBrokerInsuredContactDO;
import com.utils.DateUtil;
import lombok.Data;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
public class ApplicationDO implements Serializable {

    private Integer applicationId;

    private Long fileId;

    private FileInfoDO fileInfo;

    private String appNo;

    private String policyStartDate;

    private String policyEndDate;

    private String insuredCompany;

    private String reinsuringCompany;

    private String coverages ;

    private Integer currency;

    private String otherCurrency;

    private String interest ;

    private String perils ;

    private Double totalWorldWideValue;

    private Double totalUSAValue ;

    private String termsAndConditions ;

    private String noticeOfCancellation ;

    private String limitsOfLiability ;

    private String deductibles ;

    private String valuation ;


    private String lossHistory ;

    private String claimsHandling ;

    private Double reinsurance;

    private String extAccOrCompt ;

    private String comment ;

    private String otherServices ;

    private Integer collectionType ;

    private String masterPolicyNo ;

    private String underWriterName  ;

    private Integer phoneCountryCode;

    private Integer phoneAreaCode;

    private Long phone;

    private String email;

    private String branchOffice;

    private Integer appStatus;

    /*private Date dateEmailedLocalMarkets;*/

    private Boolean glOccurence ;

    private String glOccuranceLimit ;

    private String glAggregateLimit ;

    private String glDeductible ;

    private Boolean plOccurance ;

    private String plOccuranceLimit ;

    private String plAggregateLimit ;

    private String plDeductible ;

    private Boolean elOccurence;

    private String elPayroll ;

    private String elNoOfEmployees ;

    private String elLimits ;

    private String elDeductibles ;

    private String elTargetRate ;

    private String elLossHistory ;

    private String annualGrossIncome ;

    private String estimatedExtraExpense ;

    private String totValShpInTwlMnths ;

    private String avgShpVal ;

    private String noOfAnnualShps ;

    private String maxValPerShp ;

    private String maxValPerExb ;

    private String propOnExb ;

    private String noOfExbs ;

    private String perilsInsAgnst ;

    private String limits ;

    private String comTargetRate ;

    private String commDeductibles ;

    private String commValuation ;

    private String commLossHistory ;

    private String commTermsConditions ;

    private String lineOfCoverage ;

    private Boolean glClaimsMade;

    private Boolean plClaimsMade;

    private Boolean elClaimsMade;

   private String daysSpent ;

   private String accDeathDisMem ;

   private String locAggLimit ;

   private String addBenefit ;

   private String addExpoInfo ;

   private String assProvider ;

   private String foreignLossHist ;

   private String allReinsurance ;

   private String otherAllReinsurance ;


    private CommonsMultipartFile attachment;

    /***childs**/
    private Set<ExposureDataDO> exposureDatas;

    private Set<LocalBrokerInsuredContactDO> localBrokerInsuredContacts;

    private String exposureJson;

    private String localBrokerInsuredContactsJson;

    public ApplicationDO(){

    }

    public ApplicationDO(Application application){

        this.applicationId=application.getApplicationId();

        this.appNo=application.getApplicationNo();

        this.policyStartDate= DateUtil.formatDate(application.getPolicyStartDate()) ;

        this.policyEndDate=DateUtil.formatDate(application.getPolicyEndDate());

        this.insuredCompany=application.getInsuredCompany() ;

        this.reinsuringCompany=application.getReinsuringCompany() ;

        this.coverages =application.getCoverages() ;

        this.currency=application.getCurrency() ;

        this.otherCurrency=application.getOtherCurrency() ;

        this.interest =application.getInterest() ;

        this.perils =application.getPerils() ;

        this.totalWorldWideValue=application.getTotalWorldWideValue() ;

        this.totalUSAValue =application.getTotalUsaValue();

        this.termsAndConditions =application.getTermsAndConditions();

        this.noticeOfCancellation =application.getNoticeOfCancellation();

        this.limitsOfLiability =application.getLimitsOfLiability();

        this.deductibles =application.getDeductibles();

        this.valuation =application.getValuation();

        this.lossHistory =application.getLossHistory();

        this.claimsHandling =application.getClaimsHandling();

        this.reinsurance=application.getReinsurance();

        this.extAccOrCompt =application.getExtAccOrCompt() ;

        this.comment =application.getComment() ;

        this.otherServices =application.getOtherServices() ;

        this.collectionType =application.getCollectionType() ;

        this.masterPolicyNo =application.getMasterPolicyNo() ;

        this.underWriterName  =application.getUnderWriterName() ;

        this.phoneCountryCode=application.getPhoneCountryCode() ;

        this.phoneAreaCode=application.getPhoneAreaCode() ;

        this.phone=application.getPhone() ;

        this.email=application.getEmail() ;

        this.branchOffice=application.getBranchOffice() ;

        this.appStatus=application.getApplicationStatus() ;

        //this.dateEmailedLocalMarkets=application.getDateEmailedLocalMarkets() ;

        this.glOccurence =application.getGlOccurence() ;

        this.glOccuranceLimit =application.getGlOccuranceLimit() ;

        this.glAggregateLimit =application.getGlAggregateLimit() ;

        this.glDeductible =application.getGlDeductible() ;

        this.plOccurance =application.getPlOccurence() ;

        this.plOccuranceLimit =application.getPlOccuranceLimit() ;

        this.plAggregateLimit =application.getPlAggregateLimit() ;

        this.plDeductible =application.getPlDeductible();

        this.elOccurence=application.getElOccurence() ;

        this.elPayroll =application.getElPayroll() ;

        this.elNoOfEmployees =application.getElNoOfEmployees() ;

        this.elLimits =application.getElLimits() ;

        this.elDeductibles =application.getElDeductibles() ;

        this.elTargetRate =application.getElTargetRate() ;

        this.elLossHistory =application.getElLossHistory() ;

        this.annualGrossIncome =application.getAnnualGrossIncome() ;

        this.estimatedExtraExpense =application.getEstimatedExtraExpense() ;

        this.totValShpInTwlMnths =application.getTotValShpngInTwelveMths();

        this.avgShpVal =application.getAvgShpngValue() ;

        this.noOfAnnualShps =application.getNoOfAnnualShpngs() ;

        this.maxValPerShp =application.getMaxValuePerShpmnt() ;

        this.maxValPerExb =application.getMaxValuePerExbtn() ;

        this.propOnExb =application.getPropertyOnExbtn() ;

        this.noOfExbs =application.getNoOfExbtns() ;

        this.perilsInsAgnst =application.getPerilsInsuredAgnst() ;

        this.limits =application.getLimits() ;

        this.comTargetRate =application.getCommTargetRate() ;

        this.commDeductibles =application.getCommDeductibles() ;

        this.commValuation =application.getCommValuation() ;

        this.commLossHistory =application.getCommLossHistry();

        this.commTermsConditions =application.getCommTermsConditions() ;

        this.lineOfCoverage =application.getLineOfCoverage() ;

        this.glClaimsMade=application.getGlClaimsMade() ;

        this.plClaimsMade=application.getPlClaimsMade() ;

        this.elClaimsMade=application.getElClaimsMade() ;

        this.daysSpent =application.getDaysSpent() ;

        this.accDeathDisMem =application.getAccDeathDismem() ;

        this.locAggLimit =application.getLocAggLimit() ;

        this.addBenefit =application.getAddBenefit() ;

        this.addExpoInfo =application.getAddExpoInfo() ;

        this.assProvider =application.getAssProvider() ;

        this.foreignLossHist =application.getForeignLossHist() ;

        this.allReinsurance =application.getAllReinsurance() ;

        this.otherAllReinsurance =application.getOtherAllReinsurance() ;

        this.exposureDatas=exposureData(application.getExposureDatas());

        this.localBrokerInsuredContacts=getlocalBrokerContacts(application.getLocalBrokerInsuredContacts());
    }

    public Application value(){

        Application application=new Application();
        application.setApplicationId(applicationId);
         application.setApplicationNo(appNo);
         /*policyStartDate=application.getPolicyStartDate() ;
         application.setApplicationId(policyEndDate=application.getPolicyEndDate() ;*/
         application.setInsuredCompany(insuredCompany);
         application.setReinsuringCompany(reinsuringCompany) ;
         application.setCoverages(coverages) ;
         application.setCurrency(currency);
         application.setOtherCurrency(otherCurrency) ;
         application.setInterest(interest) ;
         application.setPerils(perils) ;
         application.setTotalWorldWideValue(totalWorldWideValue) ;
         application.setTotalUsaValue(totalUSAValue);
         application.setTermsAndConditions(termsAndConditions);
         application.setNoticeOfCancellation(noticeOfCancellation);
         application.setLimitsOfLiability(limitsOfLiability);
         application.setDeductibles(deductibles);
         application.setValuation(valuation);
         application.setLossHistory(lossHistory);
         application.setClaimsHandling(claimsHandling);
         application.setReinsurance(reinsurance);
         application.setExtAccOrCompt(extAccOrCompt);
         application.setComment(comment);
         application.setOtherServices(otherServices) ;
         application.setCollectionType(collectionType);
         application.setMasterPolicyNo(masterPolicyNo) ;
         application.setUnderWriterName(underWriterName);
         application.setPhoneCountryCode(phoneCountryCode);
         application.setPhoneAreaCode(phoneAreaCode) ;
         application.setPhone(phone) ;
         application.setEmail(email);
         application.setBranchOffice(branchOffice) ;
         application.setApplicationStatus(appStatus) ;
         //dateEmailedLocalMarkets=application.getDateEmailedLocalMarkets() ;
         application.setGlOccurence(glOccurence);
         application.setGlOccuranceLimit(glOccuranceLimit) ;
         application.setGlAggregateLimit(glAggregateLimit) ;
         application.setGlAggregateLimit(glDeductible) ;
         application.setPlOccurence(plOccurance) ;
         application.setPlOccuranceLimit(plOccuranceLimit);
         application.setPlAggregateLimit(plAggregateLimit);
         application.setPlDeductible(plDeductible);
         application.setElOccurence(elOccurence);
         application.setElPayroll(elPayroll);
         application.setElNoOfEmployees(elNoOfEmployees);
         application.setElLimits(elLimits);
         application.setElDeductibles(elDeductibles);
         application.setElTargetRate(elTargetRate);
         application.setElLossHistory(elLossHistory);
         application.setAnnualGrossIncome(annualGrossIncome) ;
         application.setEstimatedExtraExpense(estimatedExtraExpense);
         application.setTotValShpngInTwelveMths(totValShpInTwlMnths);
         application.setAvgShpngValue(avgShpVal);
         application.setNoOfAnnualShpngs(noOfAnnualShps);
         application.setMaxValuePerShpmnt(maxValPerShp);
         application.setMaxValuePerExbtn(maxValPerExb);
         application.setPropertyOnExbtn(propOnExb);
         application.setNoOfExbtns(noOfExbs);
         application.setPerilsInsuredAgnst(perilsInsAgnst);
         application.setLimits(limits =application.getLimits());
         application.setCommTargetRate(comTargetRate);
         application.setCommDeductibles(commDeductibles);
         application.setCommValuation(commValuation);
         application.setCommLossHistry(commLossHistory);
         application.setCommTermsConditions(commTermsConditions) ;
         application.setLineOfCoverage(lineOfCoverage) ;
         application.setGlClaimsMade(glClaimsMade) ;
         application.setPlClaimsMade(plClaimsMade) ;
         application.setElClaimsMade(elClaimsMade) ;
         application.setDaysSpent(daysSpent) ;
         application.setAccDeathDismem(accDeathDisMem) ;
         application.setLocAggLimit(locAggLimit) ;
         application.setAddBenefit(addBenefit) ;
         application.setAddExpoInfo(addExpoInfo) ;
         application.setAssProvider(assProvider) ;
         application.setForeignLossHist(foreignLossHist) ;
         application.setAllReinsurance(allReinsurance) ;
         application.setOtherAllReinsurance(otherAllReinsurance);

        return application;
    }

    private Set<ExposureDataDO> exposureData(Set<ExposureData> exposureList){
        if(exposureList!=null && !exposureList.isEmpty()){
            Set<ExposureDataDO> exposureDOs=new HashSet<ExposureDataDO>();
            for(ExposureData exposureData:exposureList){
                exposureDOs.add(new ExposureDataDO(exposureData));
            }
            return exposureDOs;
        }
        return null;
    }

    public Set<ExposureData> getExposureDataValues(Application application){
        if(exposureDatas!=null && !exposureDatas.isEmpty()){
            Set<ExposureData> exposures=new HashSet<ExposureData>();
            for(ExposureDataDO exposureData:exposureDatas){
                ExposureData exposure=exposureData.value();
                exposure.setApplication(application);
                exposures.add(exposure);
            }
            application.setExposureDatas(exposures);
            return exposures;
        }
        return null;
    }

    private Set<LocalBrokerInsuredContactDO> getlocalBrokerContacts(Set<LocalBrokerInsuredContact> localBrokerInsuredContacts){
        if(localBrokerInsuredContacts!=null && !localBrokerInsuredContacts.isEmpty()){
            Set<LocalBrokerInsuredContactDO> localBrokerInsuredContactList=new HashSet<LocalBrokerInsuredContactDO>();
            for(LocalBrokerInsuredContact localBrokerInsuredContact:localBrokerInsuredContacts){
                localBrokerInsuredContactList.add(new LocalBrokerInsuredContactDO(localBrokerInsuredContact));
            }
            return localBrokerInsuredContactList;
        }
        return null;
    }

    public Set<LocalBrokerInsuredContact> getlocalBrokerContactValues(Application application){
        if(localBrokerInsuredContacts!=null && !localBrokerInsuredContacts.isEmpty()){
            Set<LocalBrokerInsuredContact> localBrokerInsuredContactList=new HashSet<LocalBrokerInsuredContact>();
            for(LocalBrokerInsuredContactDO localBrokerInsuredContact:localBrokerInsuredContacts){
                LocalBrokerInsuredContact localBrokerContact=localBrokerInsuredContact.value();
                localBrokerContact.setApplication(application);
                localBrokerInsuredContactList.add(localBrokerContact);
            }
            application.setLocalBrokerInsuredContacts(localBrokerInsuredContactList);
            return localBrokerInsuredContactList;
        }
        return null;
    }
}
