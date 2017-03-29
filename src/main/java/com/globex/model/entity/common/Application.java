package com.globex.model.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@ToString(exclude={"file"})
@EqualsAndHashCode(of = {"applicationId"})
@Entity
@Table(name = "application")
public class Application implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "APPLICATION_ID")
	private Integer applicationId;

    @ManyToOne
    @JoinColumn(name = "FILE_ID")
	private File file;

    @Column(name = "APPLICATION_NO")
    private String applicationNo;

    /*@Column(name = "POLICY_START_DATE")
    private Date policyStartDate;

    @Column(name = "POLICY_END_DATE")
    private Date policyEndDate;*/

    @Column(name = "INSURED_COMPANY")
    private String insuredCompany;

    @Column(name = "REINSURING_COMPANY")
    private String reinsuringCompany;

    @Column(name = "COVERAGES")
    private String coverages;

    @Column(name = "CURRENCY")
    private Integer currency;

    @Column(name = "OTHER_CURRENCY")
	private String otherCurrency;

    @Column(name = "INTEREST")
    private String interest;

    @Column(name = "PERILS")
    private String perils;

    @Column(name = "TOTAL_WORLD_WIDE_VALUE")
    private Double totalWorldWideValue;

    @Column(name = "TOTAL_USA_VALUE")
    private Double totalUsaValue;

    @Column(name = "TERMS_AND_CONDITIONS")
    private String termsAndConditions;

    @Column(name = "NOTICE_OF_CANCELLATION")
    private String noticeOfCancellation;

    @Column(name = "LIMITS_OF_LIABILITY")
	private String limitsOfLiability;

    @Column(name = "DEDUCTIBLES")
    private String deductibles;

    @Column(name = "VALUATION")
    private String valuation;

    @Column(name = "loss_history")
    private String lossHistory;

    @Column(name = "CLAIMS_HANDLING")
    private String claimsHandling;

    @Column(name = "REINSURANCE", precision = 7, scale = 4)
    private Double reinsurance;

    @Column(name = "EXT_ACC_OR_COMPT")
    private String extAccOrCompt;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "OTHER_SERVICES")
    private String otherServices;

    @Column(name = "COLLECTION_TYPE", nullable = false)
    private int collectionType;

    @Column(name = "MASTER_POLICY_NO", length = 20)
    private String masterPolicyNo;

    @Column(name = "UNDER_WRITER_NAME", length = 30)
    private String underWriterName;

    @Column(name = "PHONE_COUNTRY_CODE")
    private Integer phoneCountryCode;

    @Column(name = "PHONE_AREA_CODE")
    private Integer phoneAreaCode;

    @Column(name = "PHONE")
    private Long phone;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "BRANCH_OFFICE", length = 150)
    private String branchOffice;

    @Column(name = "APPLICATION_STATUS", nullable = false)
    private int applicationStatus;

    /*@Column(name = "DATE_EMAILED_LOCAL_MARKETS")
    private Date dateEmailedLocalMarkets;*/

    @Column(name = "GL_OCCURENCE", nullable = false)
    private boolean glOccurence;

    @Column(name = "GL_OCCURANCE_LIMIT")
    private String glOccuranceLimit;

    @Column(name = "GL_AGGREGATE_LIMIT")
    private String glAggregateLimit;

    @Column(name = "GL_DEDUCTIBLE")
    private String glDeductible;

    @Column(name = "PL_OCCURENCE", nullable = false)
    private boolean plOccurence;

    @Column(name = "PL_OCCURANCE_LIMIT")
    private String plOccuranceLimit;

    @Column(name = "PL_AGGREGATE_LIMIT")
    private String plAggregateLimit;

    @Column(name = "PL_DEDUCTIBLE")
    private String plDeductible;

    @Column(name = "EL_OCCURENCE", nullable = false)
    private boolean elOccurence;

    @Column(name = "EL_PAYROLL")
    private String elPayroll;

    @Column(name = "EL_NO_OF_EMPLOYEES")
    private String elNoOfEmployees;

    @Column(name = "EL_LIMITS")
    private String elLimits;

    @Column(name = "EL_DEDUCTIBLES")
    private String elDeductibles;

    @Column(name = "EL_TARGET_RATE")
	private String elTargetRate;

    @Column(name = "EL_LOSS_HISTORY")
    private String elLossHistory;

    @Column(name = "ANNUAL_GROSS_INCOME")
    private String annualGrossIncome;

    @Column(name = "ESTIMATED_EXTRA_EXPENSE")
    private String estimatedExtraExpense;

    @Column(name = "TOT_VAL_SHPNG_IN_TWELVE_MTHS")
    private String totValShpngInTwelveMths;

    @Column(name = "AVG_SHPNG_VALUE")
    private String avgShpngValue;

    @Column(name = "NO_OF_ANNUAL_SHPNGS")
    private String noOfAnnualShpngs;

    @Column(name = "MAX_VALUE_PER_SHPMNT")
    private String maxValuePerShpmnt;

    @Column(name = "MAX_VALUE_PER_EXBTN")
    private String maxValuePerExbtn;

    @Column(name = "PROPERTY_ON_EXBTN")
    private String propertyOnExbtn;

    @Column(name = "NO_OF_EXBTNS")
    private String noOfExbtns;

    @Column(name = "PERILS_INSURED_AGNST")
	private String perilsInsuredAgnst;

    @Column(name = "LIMITS")
    private String limits;

    @Column(name = "COMM_TARGET_RATE")
    private String commTargetRate;

    @Column(name = "COMM_DEDUCTIBLES")
    private String commDeductibles;

    @Column(name = "COMM_VALUATION")
    private String commValuation;

    @Column(name = "COMM_LOSS_HISTRY")
    private String commLossHistry;

    @Column(name = "COMM_TERMS_CONDITIONS")
    private String commTermsConditions;

    @Column(name = "LINE_OF_COVERAGE")
    private String lineOfCoverage;

    @Column(name = "GL_CLAIMS_MADE", nullable = false)
    private boolean glClaimsMade;

    @Column(name = "PL_CLAIMS_MADE", nullable = false)
    private boolean plClaimsMade;

    @Column(name = "EL_CLAIMS_MADE", nullable = false)
    private boolean elClaimsMade;

    @Column(name = "DAYS_SPENT")
    private String daysSpent;

    @Column(name = "ACC_DEATH_DISMEM")
    private String accDeathDismem;

    @Column(name = "LOC_AGG_LIMIT")
    private String locAggLimit;

    @Column(name = "ADD_BENEFIT")
    private String addBenefit;

    @Column(name = "ADD_EXPO_INFO")
    private String addExpoInfo;

    @Column(name = "ASS_PROVIDER")
    private String assProvider;

    @Column(name = "FOREIGN_LOSS_HIST")
    private String foreignLossHist;

    @Column(name = "ALL_REINSURANCE")
    private String allReinsurance;

    @Column(name = "OTHER_ALL_REINSURANCE")
    private String otherAllReinsurance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
	private Set<LocationsValues> locationsValueses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
    private Set<ExposureData> exposureDatas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
    private Set<AhExposureData> ahExposureDatas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
    private Set<LocalBrokerInsuredContact> localBrokerInsuredContacts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "application")
    private Set<AssignedLocalMarket> assignedLocalMarkets;
}