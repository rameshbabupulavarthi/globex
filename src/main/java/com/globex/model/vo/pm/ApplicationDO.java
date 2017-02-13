package com.globex.model.vo.pm;

import com.globex.model.entity.pm.Application;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
public class ApplicationDO {

    private Integer id;

    private FileInfoDO fileInfo;

    private String appNo;

    private Timestamp policyStartDate;

    private Timestamp policyEndDate;

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

    private String reinsurance;

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

    private Timestamp dateEmailedLocalMarkets;

    private Integer glOccurence ;

    private String glOccuranceLimit ;

    private String glAggregateLimit ;

    private String glDeductible ;

    private Integer plOccurance ;

    private String plOccuranceLimit ;

    private String plAggregateLimit ;

    private String plDeductible ;

    private Integer elOccurence;

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

    private Integer glClaimsMade;

    private Integer plClaimsMade;

    private Integer elClaimsMade;

   private String daysSpent ;

   private String accDeathDisMem ;

   private String locAggLimit ;

   private String addBenefit ;

   private String addExpoInfo ;

   private String assProvider ;

   private String foreignLossHist ;

   private String allReinsurance ;

   private String otherAllReinsurance ;


    public ApplicationDO(){

    }

    public ApplicationDO(Application application){

        this.id=application.getId();

        //this.fileInfo=new FileInfoDO(application.getFileInfo());

        this.appNo=application.getAppNo() ;

        this.policyStartDate=application.getPolicyStartDate() ;

        this.policyEndDate=application.getPolicyEndDate() ;

        this.insuredCompany=application.getInsuredCompany() ;

        this.reinsuringCompany=application.getReinsuringCompany() ;

        this.coverages =application.getCoverages() ;

        this.currency=application.getCurrency() ;

        this.otherCurrency=application.getOtherCurrency() ;

        this.interest =application.getInterest() ;

        this.perils =application.getPerils() ;

        this.totalWorldWideValue=application.getTotalWorldWideValue() ;

        this.totalUSAValue =application.getTotalUSAValue() ;

        this.termsAndConditions =application.getTermsAndConditions() ;

        this.noticeOfCancellation =application.getNoticeOfCancellation() ;

        this.limitsOfLiabilty =application.getLimitsOfLiabilty() ;

        this.deductibles =application.getDeductibles() ;

        this.valuation =application.getValuation() ;


        this.lossHistory =application.getLossHistory() ;

        this.claimsHandling =application.getClaimsHandling() ;

        this.reinsurance=application.getReinsurance() ;

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

        this.appStatus=application.getAppStatus() ;

        this.dateEmailedLocalMarkets=application.getDateEmailedLocalMarkets() ;

        this.glOccurence =application.getGlOccurence() ;

        this.glOccuranceLimit =application.getGlOccuranceLimit() ;

        this.glAggregateLimit =application.getGlAggregateLimit() ;

        this.glDeductible =application.getGlDeductible() ;

        this.plOccurance =application.getPlOccurance() ;

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

        this.totValShpInTwlMnths =application.getTotValShpInTwlMnths() ;

        this.avgShpVal =application.getAvgShpVal() ;

        this.noOfAnnualShps =application.getNoOfAnnualShps() ;

        this.maxValPerShp =application.getMaxValPerShp() ;

        this.maxValPerExb =application.getMaxValPerExb() ;

        this.propOnExb =application.getPropOnExb() ;

        this.noOfExbs =application.getNoOfExbs() ;

        this.perilsInsAgnst =application.getPerilsInsAgnst() ;

        this.limits =application.getLimits() ;

        this.comTargetRate =application.getComTargetRate() ;

        this.commDeductibles =application.getCommDeductibles() ;

        this.commValuation =application.getCommValuation() ;

        this.commLossHistory =application.getCommLossHistory() ;

        this.commTermsConditions =application.getCommTermsConditions() ;

        this.lineOfCoverage =application.getLineOfCoverage() ;

        this.glClaimsMade=application.getGlClaimsMade() ;

        this.plClaimsMade=application.getPlClaimsMade() ;

        this.elClaimsMade=application.getElClaimsMade() ;

        this.daysSpent =application.getDaysSpent() ;

        this.accDeathDisMem =application.getAccDeathDisMem() ;

        this.locAggLimit =application.getLocAggLimit() ;

        this.addBenefit =application.getAddBenefit() ;

        this.addExpoInfo =application.getAddExpoInfo() ;

        this.assProvider =application.getAssProvider() ;

        this.foreignLossHist =application.getForeignLossHist() ;

        this.allReinsurance =application.getAllReinsurance() ;

        this.otherAllReinsurance =application.getOtherAllReinsurance() ;

    }

}
