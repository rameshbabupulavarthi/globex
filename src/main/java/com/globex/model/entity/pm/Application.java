package com.globex.model.entity.pm;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
@ToString(exclude={"fileInfo"})
@Entity
@Table(name="application")
public class Application {

    @Id
    @Column(name="APPLICATION_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="FILE_ID")
    private FileInfo fileInfo;
     
    @Column(name="APPLICATION_NO")
    private String appNo;

    @Column(name="POLICY_START_DATE")
    private Timestamp policyStartDate;

    @Column(name="POLICY_END_DATE")
    private Timestamp policyEndDate;

    @Column(name="INSURED_COMPANY")
    private String insuredCompany;

    @Column(name="REINSURING_COMPANY")
    private String reinsuringCompany;

    @Column(name="COVERAGES")
    private String coverages ;

    @Column(name="CURRENCY")
    private Integer currency;

    @Column(name="OTHER_CURRENCY")
    private String otherCurrency;

    @Column(name="INTEREST")
    private String interest ;

    @Column(name="PERILS")
    private String perils ;

    @Column(name="TOTAL_WORLD_WIDE_VALUE")
    private Double totalWorldWideValue;

    @Column(name="TOTAL_USA_VALUE")
    private Double totalUSAValue ;

    @Column(name="TERMS_AND_CONDITIONS")
    private String termsAndConditions ;

    @Column(name="NOTICE_OF_CANCELLATION")
    private String noticeOfCancellation ;

    @Column(name="LIMITS_OF_LIABILITY")
    private String limitsOfLiabilty ;

    @Column(name="DEDUCTIBLES")
    private String deductibles ;

    @Column(name="VALUATION")
    private String valuation ;

    @Column(name="loss_history")
    private String lossHistory ;

    @Column(name="CLAIMS_HANDLING")
    private String claimsHandling ;

    @Column(name="REINSURANCE")
    private String reinsurance;

    @Column(name="EXT_ACC_OR_COMPT")
    private String extAccOrCompt ;

    @Column(name="COMMENT")
    private String comment ;

    @Column(name="OTHER_SERVICES")
    private String otherServices ;

    @Column(name="COLLECTION_TYPE")
    private Integer collectionType ;

    @Column(name="MASTER_POLICY_NO")
    private String masterPolicyNo ;

    @Column(name="UNDER_WRITER_NAME")
    private String underWriterName  ;

    @Column(name="PHONE_COUNTRY_CODE")
    private Integer phoneCountryCode;

    @Column(name="PHONE_AREA_CODE")
    private Integer phoneAreaCode;

    @Column(name="PHONE")
    private Long phone;

    @Column(name="EMAIL")
    private String email;

    @Column(name="BRANCH_OFFICE")
    private String branchOffice;

    @Column(name="APPLICATION_STATUS")
    private Integer appStatus;

    @Column(name="DATE_EMAILED_LOCAL_MARKETS")
    private Timestamp dateEmailedLocalMarkets;

    @Column(name="GL_OCCURENCE")
    private Integer glOccurence ;

    @Column(name="GL_OCCURANCE_LIMIT")
    private String glOccuranceLimit ;

    @Column(name="GL_AGGREGATE_LIMIT")
    private String glAggregateLimit ;

    @Column(name="GL_DEDUCTIBLE")
    private String glDeductible ;

    @Column(name="PL_OCCURENCE")
    private Integer plOccurance ;

    @Column(name="PL_OCCURANCE_LIMIT")
    private String plOccuranceLimit ;

    @Column(name="PL_AGGREGATE_LIMIT")
    private String plAggregateLimit ;

    @Column(name="PL_DEDUCTIBLE")
    private String plDeductible ;

    @Column(name="EL_OCCURENCE")
    private Integer elOccurence;

    @Column(name="EL_PAYROLL")
    private String elPayroll ;

    @Column(name="EL_NO_OF_EMPLOYEES")
    private String elNoOfEmployees ;

    @Column(name="EL_LIMITS")
    private String elLimits ;

    @Column(name="EL_DEDUCTIBLES")
    private String elDeductibles ;

    @Column(name="EL_TARGET_RATE")
    private String elTargetRate ;

    @Column(name="EL_LOSS_HISTORY")
    private String elLossHistory ;

    @Column(name="ANNUAL_GROSS_INCOME")
    private String annualGrossIncome ;

    @Column(name="ESTIMATED_EXTRA_EXPENSE")
    private String estimatedExtraExpense ;

    @Column(name="TOT_VAL_SHPNG_IN_TWELVE_MTHS")
    private String totValShpInTwlMnths ;

    @Column(name="AVG_SHPNG_VALUE")
    private String avgShpVal ;

    @Column(name="NO_OF_ANNUAL_SHPNGS")
    private String noOfAnnualShps ;

    @Column(name="MAX_VALUE_PER_SHPMNT")
    private String maxValPerShp ;

    @Column(name="MAX_VALUE_PER_EXBTN")
    private String maxValPerExb ;

    @Column(name="PROPERTY_ON_EXBTN")
    private String propOnExb ;

    @Column(name="NO_OF_EXBTNS")
    private String noOfExbs ;

    @Column(name="PERILS_INSURED_AGNST")
    private String perilsInsAgnst ;

    @Column(name="LIMITS")
    private String limits ;

    @Column(name="COMM_TARGET_RATE")
    private String comTargetRate ;

    @Column(name="COMM_DEDUCTIBLES")
    private String commDeductibles ;

    @Column(name="COMM_VALUATION")
    private String commValuation ;

    @Column(name="COMM_LOSS_HISTRY")
    private String commLossHistory ;

    @Column(name="COMM_TERMS_CONDITIONS")
    private String commTermsConditions ;

    @Column(name="LINE_OF_COVERAGE")
    private String lineOfCoverage ;

    @Column(name="GL_CLAIMS_MADE")
    private Integer glClaimsMade;

    @Column(name="PL_CLAIMS_MADE")
    private Integer plClaimsMade;

    @Column(name="EL_CLAIMS_MADE")
    private Integer elClaimsMade;

   @Column(name="DAYS_SPENT")
   private String daysSpent ;

   @Column(name="ACC_DEATH_DISMEM")
   private String accDeathDisMem ;

   @Column(name="LOC_AGG_LIMIT")
   private String locAggLimit ;

   @Column(name="ADD_BENEFIT")
   private String addBenefit ;

   @Column(name="ADD_EXPO_INFO")
   private String addExpoInfo ;

   @Column(name="ASS_PROVIDER")
   private String assProvider ;

   @Column(name="FOREIGN_LOSS_HIST")
   private String foreignLossHist ;

   @Column(name="ALL_REINSURANCE")
   private String allReinsurance ;

   @Column(name="OTHER_ALL_REINSURANCE")
   private String otherAllReinsurance ;
}
