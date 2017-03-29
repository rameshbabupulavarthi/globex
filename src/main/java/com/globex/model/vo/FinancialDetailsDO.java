package com.globex.model.vo;

import com.globex.model.entity.partner.FinancialDetails;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ramesh on 12/31/2016.
 */
@Data
public class FinancialDetailsDO implements Serializable {

    private Integer id;

    private Integer companyType;

    private String parentCompany;

    private String licenseNo;

    private String licenseAuthority;

    private String licenseAuthWebsite;

    private Integer companyEstablishmentYear;

    private String amBestRating;

    private Integer amBestLook;

    /*private Date amBestRatingDate;*/

    private String amRatingAttachment;//file

    private String otherBrokers;

    private Integer sAndPRating;

    private String sAndPOutlook;

    /*private Date sAndPrRatingOutlookDate;*/

    private String sAndPAttachment;//file

    private String miscCompName;

    private String miscCompWebsite;

    private String miscCompCountry;

    private String miscCompRating;

    private String miscCompOutlook;

    private String ratedByOtherAgency;

    private String miscCompAttachment;//file

    private Integer businessToWrite;

    private Integer businessNotToWrite;

    private String lobComments;

    private String countryList;

    private String selectedCountry;

    private String territoryComment;


    private String grosspremiumpremium1;
    private String grosspremiumpremium2;
    private String grosspremiumpremium3;
    private String grosspremiumcurrency1;
    private String grosspremiumcurrency2;
    private String grosspremiumcurrency3;
    private String currentcombratio1;
    private String currentcombratio2;
    private String currentcombratio3;
    private String totalassets1;
    private String totalassets2;
    private String totalassets3;
    private String totalassetscurrency1;
    private String totalassetscurrency2;
    private String totalassetscurrency3;
    private String financialreportcomments1;
    private String financialreportcomments2;
    private String financialreportcomments3;
    private String advisecompranking1;
    private String advisecompranking2;
    private String advisecompranking3;

    public FinancialDetailsDO(){}

    public FinancialDetailsDO(FinancialDetails financialDetails){
        id=financialDetails.getId();
        companyType=financialDetails.getCompanyType();
        parentCompany=financialDetails.getParentCompany();
        licenseNo=financialDetails.getLicenseNo();
        licenseAuthority=financialDetails.getLicenseAuthority();
        licenseAuthWebsite=financialDetails.getLicenseAuthwebsite();
        companyEstablishmentYear=financialDetails.getCompanyEstablishmentYear();
        amBestRating=financialDetails.getAmBestRating();
        amBestLook=financialDetails.getAmBestLook();
        //amBestRatingDate=financialDetails.getAmBestRatingDate();
        amRatingAttachment=financialDetails.getAmRatingAttachment();//file
        otherBrokers=financialDetails.getOtherBrokers();
        sAndPRating=financialDetails.getSAndPRating();
        sAndPOutlook=financialDetails.getSAndPOutlook();
        //sAndPrRatingOutlookDate=financialDetails.getSAndPrRatingOutlookDate();
        sAndPAttachment=financialDetails.getSAndPAttachment();//file
        miscCompName=financialDetails.getMiscCompName();
        miscCompWebsite=financialDetails.getMiscCompWebsite();
        miscCompCountry=financialDetails.getMiscCompCountry();
        miscCompRating=financialDetails.getMiscCompRating();
        miscCompOutlook=financialDetails.getMiscCompOutlook();
        ratedByOtherAgency=financialDetails.getRatedByOtherAgency();
        miscCompAttachment=financialDetails.getMiscCompAttachment();//file
        businessToWrite=financialDetails.getBusinessToWrite();
        businessNotToWrite=financialDetails.getBusinessNotToWrite();
        lobComments=financialDetails.getLobComments();
        countryList=financialDetails.getCountryList();
        selectedCountry=financialDetails.getSelectedCountry();
        territoryComment=financialDetails.getTerritoryComment();
    }

    public FinancialDetails getValue(){
        FinancialDetails financialDetails=new FinancialDetails();
        financialDetails.setId(id);
        financialDetails.setCompanyType(companyType);
        financialDetails.setParentCompany(parentCompany);
        financialDetails.setLicenseNo(licenseNo);
        financialDetails.setLicenseAuthority(licenseAuthority);
        financialDetails.setLicenseAuthwebsite(licenseAuthWebsite);
        financialDetails.setCompanyEstablishmentYear(companyEstablishmentYear);
        financialDetails.setAmBestRating(amBestRating);
        financialDetails.setAmBestLook(amBestLook);
        //financialDetails.setAmBestRatingDate(amBestRatingDate);
        financialDetails.setAmRatingAttachment(amRatingAttachment);//file
        financialDetails.setOtherBrokers(otherBrokers);
        financialDetails.setSAndPRating(sAndPRating);
        financialDetails.setSAndPOutlook(sAndPOutlook);
        //financialDetails.setSAndPrRatingOutlookDate(sAndPrRatingOutlookDate);
        financialDetails.setSAndPAttachment(sAndPAttachment);//file
        financialDetails.setMiscCompName(miscCompName);
        financialDetails.setMiscCompWebsite(miscCompWebsite);
        financialDetails.setMiscCompCountry(miscCompCountry);
        financialDetails.setMiscCompRating(miscCompRating);
        financialDetails.setMiscCompOutlook(miscCompOutlook);
        financialDetails.setRatedByOtherAgency(ratedByOtherAgency);
        financialDetails.setMiscCompAttachment(miscCompAttachment);//file
        financialDetails.setBusinessToWrite(businessToWrite);
        financialDetails.setBusinessNotToWrite(businessNotToWrite);
        financialDetails.setLobComments(lobComments);
        financialDetails.setCountryList(countryList);
        financialDetails.setSelectedCountry(selectedCountry);
        financialDetails.setTerritoryComment(territoryComment);
        return financialDetails;
    }
}