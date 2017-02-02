package com.globex.model.entity.partner;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by Ramesh on 29-12-2016.
 */
@Data
@Entity
@Table(name="t_finance")
public class FinancialDetails implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="companyType")
    private Integer companyType;

    @Column(name="parentCompany")
    private String parentCompany;

    @Column(name="licenseNo")
    private String licenseNo;

    @Column(name="licenseAuthority")
    private String licenseAuthority;

    @Column(name="licenseAuthwebsite")
    private String licenseAuthwebsite;

    @Column(name="companyEstablishmentYear")
    private Integer companyEstablishmentYear;

    @Column(name="amBestRating")
    private String amBestRating;

    @Column(name="amBestLook")
    private Integer amBestLook;

    @Column(name="amBestRatingDate")
    private Date amBestRatingDate;

    @Column(name="amRatingAttachment")
    private String amRatingAttachment;//file

    @Column(name="otherBrokers")
    private String otherBrokers;

    @Column(name="sAndPRating")
    private Integer sAndPRating;

    @Column(name="sAndPOutlook")
    private String sAndPOutlook;

    @Column(name="sAndPrRatingOutlookDate")
    private Date sAndPrRatingOutlookDate;

    @Column(name="sAndPAttachment")
    private String sAndPAttachment;//file

    @Column(name="miscCompName")
    private String miscCompName;

    @Column(name="miscCompWebsite")
    private String miscCompWebsite;

    @Column(name="miscCompCountry")
    private String miscCompCountry;

    @Column(name="miscCompRating")
    private String miscCompRating;

    @Column(name="miscCompOutlook")
    private String miscCompOutlook;

    @Column(name="ratedByOtherAgency")
    private String ratedByOtherAgency;

    @Column(name="miscCompAttachment")
    private String miscCompAttachment;//file

    @Column(name="businessToWrite")
    private Integer businessToWrite;

    @Column(name="businessNotToWrite")
    private Integer businessNotToWrite;

    @Column(name="lobComments")
    private String lobComments;

    @Column(name="countryList")
    private String countryList;

    @Column(name="selectedCountry")
    private String selectedCountry;

    @Column(name="territoryComment")
    private String territoryComment;


    /*private Map<Integer,Object> grossPremium;

    private Map<Integer,String> combinedRatio;

    private Map<Integer,Object> totalAssets;

    private Map<Integer,Object> financialReports;

    private Map<Integer,String> ranking;*/

}
