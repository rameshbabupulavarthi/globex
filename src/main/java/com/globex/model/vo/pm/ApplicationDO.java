package com.globex.model.vo.pm;

import com.globex.model.entity.common.Application;
import com.globex.model.entity.common.ExposureData;
import com.globex.model.vo.ExposureDataDO;
import com.globex.model.vo.LocalBrokerInsuredContactDO;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
public class ApplicationDO {

    private Integer applicationId;

    private FileInfoDO fileInfo;

    private String appNo;

    private Date policyStartDate;

    private Date policyEndDate;

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

    private String limitsOfLiabilty ;

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

    /***childs**/
    private Set<ExposureDataDO> exposureDatas;

    private Set<LocalBrokerInsuredContactDO> localBrokerInsuredContacts;

    public ApplicationDO(){

    }

    public ApplicationDO(Application application){

        this.applicationId=application.getApplicationId();

        this.appNo=application.getApplicationNo();

        /*this.policyStartDate=application.getPolicyStartDate() ;

        this.policyEndDate=application.getPolicyEndDate() ;*/

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

        this.limitsOfLiabilty =application.getLimitsOfLiability();

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

        this.glOccurence =application.isGlOccurence() ;

        this.glOccuranceLimit =application.getGlOccuranceLimit() ;

        this.glAggregateLimit =application.getGlAggregateLimit() ;

        this.glDeductible =application.getGlDeductible() ;

        this.plOccurance =application.isPlOccurence() ;

        this.plOccuranceLimit =application.getPlOccuranceLimit() ;

        this.plAggregateLimit =application.getPlAggregateLimit() ;

        this.plDeductible =application.getPlDeductible();

        this.elOccurence=application.isElOccurence() ;

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

        this.glClaimsMade=application.isGlClaimsMade() ;

        this.plClaimsMade=application.isPlClaimsMade() ;

        this.elClaimsMade=application.isElClaimsMade() ;

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
    }

    public Application getValue(){

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
         application.setLimitsOfLiability(limitsOfLiabilty);
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

}
