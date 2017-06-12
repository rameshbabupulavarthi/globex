package com.globex.model.vo.lm;

import com.globex.model.entity.pm.OrganizationDetail;
import com.utils.DateUtil;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Sunil Golla on 6/4/2017.
 */
@Data
@ToString(exclude = {""})
public class OrganizationDetailsDO implements Serializable {

    private Long orgDetailsId;

    private String telePhone;

    private String parentCompany;

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

    private Integer snpRating;

    private String snpOutlook;

    private String snpRatingOutlookDate;

    private String snpAttachment;

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

    private String adviceRegistration;

    private String adviceRegistrationAttach;

    private Integer serviceOption;

    private String serviceOptionText;

    private String registrationProcedure;

    private String requiredDocReinsurPlace;

    private String registrationProcedureAttach;

    private String specReqDocReinsurPlace;

    private String requiredDocReinsurPlaceAttach;

    private String compInvolClaims;

    private String compInvolClaimsText;

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

    public OrganizationDetailsDO (){

    }

    public OrganizationDetailsDO (OrganizationDetail organizationDetail){
        orgDetailsId=organizationDetail.getOrgDetailsId();
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
        snpRating=organizationDetail.getSAndPRating();
        snpOutlook=organizationDetail.getSAndPOutlook();
        snpRatingOutlookDate=DateUtil.formatDate(organizationDetail.getSAndPrRatingOutlookDate());
        snpAttachment=organizationDetail.getSAndPAttachment();
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


        reInsurancePlacement=organizationDetail.getReInsurancePlacement();
        reInsurancePlacementComments=organizationDetail.getReInsurancePlacementComments();
        insuRequiredDoc=organizationDetail.getInsuRequiredDoc();
        adviceRegistration=organizationDetail.getAdviceRegistration();
        requiredDocReinsurPlace=organizationDetail.getRequiredDocReinsurPlace();
        specReqDocReinsurPlace=organizationDetail.getSpecReqDocReinsurPlace();
        compInvolClaims=organizationDetail.getCompInvolClaims();
        compInvolClaimsText=organizationDetail.getCompInvolClaimsText();
        premiumPayOption=organizationDetail.getPremiumPayOption();
        premiumWithTax=organizationDetail.getPremiumWithTax();

        adviceRegistrationAttach=organizationDetail.getAdviceRegistrationAttach();
        registrationProcedureAttach=organizationDetail.getRegistrationProcedureAttach();
        requiredDocReinsurPlaceAttach=organizationDetail.getRequiredDocReinsurPlaceAttach();
        claimHandlingWordingAttach=organizationDetail.getClaimHandlingWordingAttach();
    }

    public OrganizationDetail value(){
        OrganizationDetail organizationDetail=new OrganizationDetail();
        organizationDetail.setOrgDetailsId(orgDetailsId);
        organizationDetail.setParentCompany(parentCompany);
        organizationDetail.setOrgType(orgType);
        organizationDetail.setLicenseNo(licenseNo);
        organizationDetail.setLicenseAuthorityName(licenseAuthorityName);
        organizationDetail.setRatedByOtherAgency(ratedByOtherAgency);
        organizationDetail.setLicenseAuthWebsite(licenseAuthWebsite);
        organizationDetail.setEstablishedDate(DateUtil.getDate(establishedDate));
        organizationDetail.setAttachment(attachment);
        organizationDetail.setAmBestRating(amBestRating);
        organizationDetail.setAmBestLook(amBestLook);
        organizationDetail.setAmOutlookDate(DateUtil.getDate(amOutlookDate));
        organizationDetail.setAmRatingAttachment(amRatingAttachment);
        organizationDetail.setSAndPRating(snpRating);
        organizationDetail.setSAndPOutlook(snpOutlook);
        organizationDetail.setSAndPrRatingOutlookDate(DateUtil.getDate(snpRatingOutlookDate));
        organizationDetail.setSAndPAttachment(snpAttachment);
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

        organizationDetail.setReInsurancePlacement(reInsurancePlacement);
        organizationDetail.setReInsurancePlacementComments(reInsurancePlacementComments);
        organizationDetail.setInsuRequiredDoc(insuRequiredDoc);
        organizationDetail.setAdviceRegistration(adviceRegistration);
        organizationDetail.setRequiredDocReinsurPlace(requiredDocReinsurPlace);
        organizationDetail.setSpecReqDocReinsurPlace(specReqDocReinsurPlace);
        organizationDetail.setCompInvolClaims(compInvolClaims);
        organizationDetail.setCompInvolClaimsText(compInvolClaimsText);
        organizationDetail.setPremiumPayOption(premiumPayOption);
        organizationDetail.setPremiumWithTax(premiumWithTax);

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

        organizationDetail.setAdviceRegistrationAttach(adviceRegistrationAttach);
        organizationDetail.setRegistrationProcedureAttach(registrationProcedureAttach);
        organizationDetail.setRequiredDocReinsurPlaceAttach(requiredDocReinsurPlaceAttach);
        organizationDetail.setClaimHandlingWordingAttach(claimHandlingWordingAttach);
        return organizationDetail;
    }

}
