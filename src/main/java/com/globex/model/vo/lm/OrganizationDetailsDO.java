package com.globex.model.vo.lm;

import com.globex.model.entity.common.RateRequirement;
import com.globex.model.entity.partner.CommissionRequirement;
import com.globex.model.entity.partner.LOB;
import com.globex.model.entity.pm.OrganizationDetail;
import com.utils.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Sunil Golla on 6/4/2017.
 */
@Data
public class OrganizationDetailsDO implements Serializable {

    private Integer parentCompany;

    private Integer orgType;

    private String licenseNo;

    private String licenseAuthorityName;

    private String ratedByOtherAgency;

    private String licenseAuthWebsite;

    private String establishedDate;

    private String attachment;

    private String amBestRating;

    private Integer amBestLook;

    private String amOutlookDate;

    private String amRatingAttachment;

    private Integer sAndPRating;

    private String sAndPOutlook;

    private String sAndPrRatingOutlookDate;

    private String sAndPAttachment;

    private String misCompanyName;

    private String misCompanyCountry;

    private String misCompanyWebsite;

    private String misCompanyRating;

    private String misCompanyOutlook;

    private String misCompanyAttachment;

    private String alphaBrokers;

    private String reInsuranceLob;

    private Integer reInsuranceSupport;

    private String reInsuranceComments;

    /******* step 2********/


    private String reInsurancePlacementLob;

    private Integer reInsurancePlacement;

    private String reInsurancePlacementComments;



    private String insuRequiredDoc;

    private Integer reinsurBroker;

    private String reinsurBrokerText;

    private String insuRequiredDocAttach;

    private Integer regRegulator;

    private String regRegulatorText;

    private Integer serviceOption;

    private String serviceOptionText;

    private String registrationProcedure;

    private String requiredDocReinsurPlace;

    private String registrationProcedureAttach;

    private String specReqDocReinsurPlace;

    private String requiredDocReinsurPlaceAttach;

    private String compInvolClaims;

    private String premiumPayOption;

    private String premiumWithTax;

    private String claimHandlingWordingAttach;


    /******  bank details   ******/
    private String bankDetails;

    private String bankAddress;

    private String bankName;

    private String bankIban;

    private String bankSwiftCode;

    private String bankAttachment;


/*    private Set<RateRequirement> rateRequirements;

    private Set<CommissionRequirement> commissionRequirements;

    private Set<LOB> lobs;*/


    public OrganizationDetailsDO (OrganizationDetail organizationDetail){

        parentCompany=organizationDetail.getParentCompany();
        orgType=organizationDetail.getOrgType();
        licenseNo=organizationDetail.getLicenseNo();
        licenseAuthorityName=organizationDetail.getLicenseAuthorityName();
        ratedByOtherAgency=organizationDetail.getRatedByOtherAgency();
        licenseAuthWebsite=organizationDetail.getLicenseAuthWebsite();
        establishedDate=DateUtil.formatDate(organizationDetail.getEstablishedDate());
        attachment=organizationDetail.getAttachment();
        amBestRating=organizationDetail.getAmBestRating();
        amBestLook=organizationDetail.getAmBestLook();
        amOutlookDate=DateUtil.formatDate(organizationDetail.getAmOutlookDate());
        amRatingAttachment=organizationDetail.getAmRatingAttachment();
        sAndPRating=organizationDetail.getSAndPRating();
        sAndPOutlook=organizationDetail.getSAndPOutlook();
        sAndPrRatingOutlookDate=DateUtil.formatDate(organizationDetail.getSAndPrRatingOutlookDate());
        sAndPAttachment=organizationDetail.getSAndPAttachment();
        misCompanyName=organizationDetail.getMisCompanyName();
        misCompanyCountry=organizationDetail.getMisCompanyCountry();
        misCompanyWebsite=organizationDetail.getMisCompanyWebsite();
        misCompanyRating=organizationDetail.getMisCompanyRating();
        misCompanyOutlook=organizationDetail.getMisCompanyOutlook();
        misCompanyAttachment=organizationDetail.getMisCompanyAttachment();
        alphaBrokers=organizationDetail.getAlphaBrokers();
        reInsuranceLob=organizationDetail.getReInsuranceLob();
        reInsuranceSupport=organizationDetail.getReInsuranceSupport();
        reInsuranceComments=organizationDetail.getReInsuranceComments();
        reInsurancePlacementLob=organizationDetail.getReInsurancePlacementLob();
        reinsurBroker=organizationDetail.getReinsurBroker();
        reinsurBrokerText=organizationDetail.getReinsurBrokerText();
        insuRequiredDocAttach=organizationDetail.getInsuRequiredDocAttach();
        regRegulator=organizationDetail.getRegRegulator();
        regRegulatorText =organizationDetail.getRegRegulatorText();
        serviceOption=organizationDetail.getServiceOption();
        serviceOptionText=organizationDetail.getServiceOptionText();
        registrationProcedure=organizationDetail.getRegistrationProcedure();
        bankDetails=organizationDetail.getBankDetails();
        bankAddress=organizationDetail.getBankAddress();
        bankName=organizationDetail.getBankName();
        bankIban=organizationDetail.getBankIban();
        bankSwiftCode=organizationDetail.getBankSwiftCode();
        bankAttachment=organizationDetail.getBankAttachment();
    }

    public OrganizationDetail value(){
        OrganizationDetail organizationDetail=new OrganizationDetail();
        organizationDetail.setParentCompany(parentCompany);
        organizationDetail.setOrgType(orgType);
        organizationDetail.setLicenseNo(licenseNo);
        organizationDetail.setLicenseAuthorityName(licenseAuthorityName);
        organizationDetail.setRatedByOtherAgency(ratedByOtherAgency);
        organizationDetail.setLicenseAuthWebsite(licenseAuthWebsite);
        organizationDetail.setEstablishedDate(DateUtil.getTimestamp(establishedDate));
        organizationDetail.setAttachment(attachment);
        organizationDetail.setAmBestRating(amBestRating);
        organizationDetail.setAmBestLook(amBestLook);
        organizationDetail.setAmOutlookDate(DateUtil.getTimestamp(amOutlookDate));
        organizationDetail.setAmRatingAttachment(amRatingAttachment);
        organizationDetail.setSAndPRating(sAndPRating);
        organizationDetail.setSAndPOutlook(sAndPOutlook);
        organizationDetail.setSAndPrRatingOutlookDate(DateUtil.getTimestamp(sAndPrRatingOutlookDate));
        organizationDetail.setSAndPAttachment(sAndPAttachment);
        organizationDetail.setMisCompanyName(misCompanyName);
        organizationDetail.setMisCompanyCountry(misCompanyCountry);
        organizationDetail.setMisCompanyWebsite(misCompanyWebsite);
        organizationDetail.setMisCompanyRating(misCompanyRating);
        organizationDetail.setMisCompanyOutlook(misCompanyOutlook);
        organizationDetail.setMisCompanyAttachment(misCompanyAttachment);
        organizationDetail.setAlphaBrokers(alphaBrokers);
        organizationDetail.setReInsuranceLob(reInsuranceLob);
        organizationDetail.setReInsuranceSupport(reInsuranceSupport);
        organizationDetail.setReInsuranceComments(reInsuranceComments);

        organizationDetail.setReInsurancePlacementLob(reInsurancePlacementLob);
        organizationDetail.setReinsurBroker(reinsurBroker);
        organizationDetail.setReinsurBrokerText(reinsurBrokerText);
        organizationDetail.setInsuRequiredDocAttach(insuRequiredDocAttach);
        organizationDetail.setRegRegulator(regRegulator);
        organizationDetail.setRegRegulatorText(regRegulatorText);
        organizationDetail.setServiceOption(serviceOption);
        organizationDetail.setServiceOptionText(serviceOptionText);
        organizationDetail.setRegistrationProcedure(registrationProcedure);

        organizationDetail.setBankDetails(bankDetails);
        organizationDetail.setBankAddress(bankAddress);
        organizationDetail.setBankName(bankName);
        organizationDetail.setBankIban(bankIban);
        organizationDetail.setBankSwiftCode(bankSwiftCode);
        organizationDetail.setBankAttachment(bankAttachment);
        return organizationDetail;
    }

}
