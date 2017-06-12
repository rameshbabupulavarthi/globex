package com.globex.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globex.constants.AppConstants;
import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.pm.OrganizationDetail;
import com.globex.model.entity.user.User;
import com.globex.model.vo.OrganizationDO;
import com.globex.model.vo.PartnerDO;

import com.globex.model.vo.lm.OrganizationDetailsDO;
import com.globex.repository.rdbms.PartnerMarketRepository;
import com.globex.repository.rdbms.UserRepository;
import com.globex.security.CurrentUserDO;
import com.globex.service.LMUserRegistrationService;
import com.globex.service.OrganizationService;
import com.globex.service.UserService;
import com.utils.AppUtils;
import com.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Ramesh on 24-12-2016.
 */
@Controller
public class LMUserRegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    LMUserRegistrationService lmUserRegistrationService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PartnerMarketRepository partnerMarketRepository;

    @RequestMapping("/secure/partnerRegistrationForm")
    @ResponseBody
    public ModelAndView getPartnerDetails() {
        Map<String, Object> model = new HashMap<String, Object>();
        OrganizationDO organizationDO=organizationService.getLMDetails();
        model.put("organization",AppUtils.convertObjectTOJSONString(organizationDO));
        CurrentUserDO userDO=userService.getCurrentUserDO();
        model.put("user",userDO);
        String view ="globex/partner_market";
        return new ModelAndView(view, "model", model);
    }


    @RequestMapping("/secure/getCountries")
    @ResponseBody
    public Map<String,String> getCountries(@RequestParam(value="mode", required=false) String country) {
        String[] locales = Locale.getISOCountries();
        Map<String,String> countries=new HashMap<String,String>();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            if(country!=null && obj.getDisplayCountry().startsWith(country)) {
                countries.put(obj.getCountry(), obj.getDisplayCountry());
            }
        }
        return countries;
    }


    @RequestMapping("/secure/saveOrgInfo")
    @ResponseBody
    public Map<String,Object> saveOrgInfo(HttpServletRequest request,@ModelAttribute("userData") OrganizationDO organizationDO) throws Exception {

        String orgParametersJson=organizationDO.getOrgParametersJson();
        String orgDetailsJson=organizationDO.getOrgDetailsJson();
        ObjectMapper mapper = new ObjectMapper();
        OrganizationDO organizationJsonDO=null;
        OrganizationDetailsDO organizationDetailsDO=null;
        if(orgParametersJson!=null && !orgParametersJson.isEmpty()){
            organizationJsonDO=mapper.readValue(orgParametersJson, new TypeReference<OrganizationDO>() {});
        }
        if(orgDetailsJson!=null && !orgDetailsJson.isEmpty()){
            organizationDetailsDO=mapper.readValue(orgDetailsJson, new TypeReference<OrganizationDetailsDO>() {});
        }

        CommonsMultipartFile attachment=organizationDO.getOrgFileAttachment();
        if (attachment != null && attachment.getBytes().length > 0) {
            String rootPath = request.getServletContext().getRealPath("/");//System.getProperty("catalina.home");
            String relativePath = FileUtils.uploadFile(attachment, rootPath);
            organizationDetailsDO.setAttachment(relativePath);
        }
        CommonsMultipartFile snpFileAttachment=organizationDO.getSnpFileAttachment();
        if (snpFileAttachment != null && snpFileAttachment.getBytes().length > 0) {
            String rootPath = request.getServletContext().getRealPath("/");//System.getProperty("catalina.home");
            String relativePath = FileUtils.uploadFile(snpFileAttachment, rootPath);
            organizationDetailsDO.setSnpAttachment(relativePath);
        }

        CommonsMultipartFile amRatingFileAttachment=organizationDO.getAmRatingFileAttachment();
        if (amRatingFileAttachment != null && amRatingFileAttachment.getBytes().length > 0) {
            String rootPath = request.getServletContext().getRealPath("/");//System.getProperty("catalina.home");
            String relativePath = FileUtils.uploadFile(amRatingFileAttachment, rootPath);
            organizationDetailsDO.setAmRatingAttachment(relativePath);
        }

        organizationDO.setMiscRatings(organizationJsonDO.getMiscRatings());
        organizationDO.setLobs(organizationJsonDO.getLobs());
        organizationDO.setRateRequirements(organizationJsonDO.getRateRequirements());
        organizationDO.setCommissionRequirements(organizationJsonDO.getCommissionRequirements());
        organizationDO.setOrganizationHistories(organizationJsonDO.getOrganizationHistories());
        organizationDO.setUwDepDetails(organizationJsonDO.getUwDepDetails());
        organizationDO.setBankingDetails(organizationJsonDO.getBankingDetails());
        organizationDO.setOrganizationDetails(organizationDetailsDO);
        //OrganizationDO organizationDetailDO=lmUserRegistrationService.saveOrgInfo(organizationDO);
        organizationDO.setOrgUserType(AppConstants.OrgUserType.LM.getUserType());
        organizationDO.setApproved(1);

        Organization organization= organizationDO.value();
        User user=userService.getCurrentUser();
        organization.setCreatedBy(user);
        organizationService.save(organization);

        Map<String,Object> output=new HashMap<String,Object>();
        output.put("orgId", organization.getId());
        output.put("orgDetailsId", organization.getOrganizationDetails().getOrgDetailsId());
        return output;
    }


    @RequestMapping("/secure/saveAccountInfo")
    @ResponseBody
    public Map<String,Object> saveAccountInfo(HttpServletRequest request,@ModelAttribute("userData") OrganizationDO organizationDO) throws Exception {
        String orgParametersJson=organizationDO.getOrgParametersJson();
        String orgDetailsJson=organizationDO.getOrgDetailsJson();
        ObjectMapper mapper = new ObjectMapper();
        OrganizationDO organizationJsonDO=null;
        OrganizationDetailsDO organizationDetailsDO=null;
        if(orgParametersJson!=null && !orgParametersJson.isEmpty()){
            organizationJsonDO=mapper.readValue(orgParametersJson, new TypeReference<OrganizationDO>() {});
        }
        if(orgDetailsJson!=null && !orgDetailsJson.isEmpty()){
            organizationDetailsDO=mapper.readValue(orgDetailsJson, new TypeReference<OrganizationDetailsDO>() {});
        }
        organizationDO.setCommissionRequirements(organizationJsonDO.getCommissionRequirements());
        organizationDO.setUwDepDetails(organizationJsonDO.getUwDepDetails());
        organizationDO.setBankingDetails(organizationJsonDO.getBankingDetails());
        organizationDO.setOrganizationDetails(organizationDetailsDO);
        organizationDO.setOrgUserType(AppConstants.OrgUserType.LM.getUserType());
        organizationDO.setApproved(1);

        CommonsMultipartFile insuRequiredDocAttach=organizationDO.getInsuRequiredDocAttach();
        if (insuRequiredDocAttach != null && insuRequiredDocAttach.getBytes().length > 0) {
            String rootPath = request.getServletContext().getRealPath("/");//System.getProperty("catalina.home");
            String relativePath = FileUtils.uploadFile(insuRequiredDocAttach, rootPath);
            organizationDetailsDO.setInsuRequiredDocAttach(relativePath);
        }
        CommonsMultipartFile adviceRegistrationAttach=organizationDO.getAdviceRegistrationAttach();
        if (adviceRegistrationAttach != null && adviceRegistrationAttach.getBytes().length > 0) {
            String rootPath = request.getServletContext().getRealPath("/");//System.getProperty("catalina.home");
            String relativePath = FileUtils.uploadFile(adviceRegistrationAttach, rootPath);
            organizationDetailsDO.setAdviceRegistrationAttach(relativePath);
        }
        CommonsMultipartFile registrationProcedureAttach=organizationDO.getRegistrationProcedureAttach();
        if (registrationProcedureAttach != null && registrationProcedureAttach.getBytes().length > 0) {
            String rootPath = request.getServletContext().getRealPath("/");//System.getProperty("catalina.home");
            String relativePath = FileUtils.uploadFile(registrationProcedureAttach, rootPath);
            organizationDetailsDO.setRegistrationProcedureAttach(relativePath);
        }
        CommonsMultipartFile requiredDocReinsurPlaceAttach=organizationDO.getRequiredDocReinsurPlaceAttach();
        if (requiredDocReinsurPlaceAttach != null && requiredDocReinsurPlaceAttach.getBytes().length > 0) {
            String rootPath = request.getServletContext().getRealPath("/");//System.getProperty("catalina.home");
            String relativePath = FileUtils.uploadFile(requiredDocReinsurPlaceAttach, rootPath);
            organizationDetailsDO.setRequiredDocReinsurPlaceAttach(relativePath);
        }
        CommonsMultipartFile claimHandlingWordingAttach=organizationDO.getClaimHandlingWordingAttach();
        if (claimHandlingWordingAttach != null && claimHandlingWordingAttach.getBytes().length > 0) {
            String rootPath = request.getServletContext().getRealPath("/");//System.getProperty("catalina.home");
            String relativePath = FileUtils.uploadFile(claimHandlingWordingAttach, rootPath);
            organizationDetailsDO.setClaimHandlingWordingAttach(relativePath);
        }

        Organization org=organizationService.getOrganization(organizationDO.getOrgId());
        Organization organization= organizationDO.value();
        organization.setOrgName(org.getOrgName());
        organization.setCity(org.getCity());
        organization.setState(org.getState());
        organization.setCountry(org.getCountry());
        organization.setZip(org.getZip());
        organization.setWebsite(org.getWebsite());
        organization.setTelePhone(org.getTelePhone());
        organization.setOrgUserType(org.getOrgUserType());
        organization.setApproved(org.getApproved());
        organization.setComment(org.getComment());
        organization.setLicenceState(org.getLicenceState());
        organization.setAddress1(org.getAddress1());
        organization.setAddress2(org.getAddress2());

        OrganizationDetail organizationDetail=organization.getOrganizationDetails();
        OrganizationDetail organizationDetailOld=org.getOrganizationDetails();

        organizationDetail.setParentCompany(organizationDetailOld.getParentCompany());
        organizationDetail.setOrgType(organizationDetailOld.getOrgType());
        organizationDetail.setLicenseNo(organizationDetailOld.getLicenseNo());
        organizationDetail.setLicenseAuthorityName(organizationDetailOld.getLicenseAuthorityName());
        organizationDetail.setRatedByOtherAgency(organizationDetailOld.getRatedByOtherAgency());
        organizationDetail.setLicenseAuthWebsite(organizationDetailOld.getLicenseAuthWebsite());
        organizationDetail.setEstablishedDate(organizationDetailOld.getEstablishedDate());
        organizationDetail.setAttachment(organizationDetailOld.getAttachment());
        organizationDetail.setAmBestRating(organizationDetailOld.getAmBestRating());
        organizationDetail.setAmBestLook(organizationDetailOld.getAmBestLook());
        organizationDetail.setAmOutlookDate(organizationDetailOld.getAmOutlookDate());
        organizationDetail.setAmRatingAttachment(organizationDetailOld.getAmRatingAttachment());
        organizationDetail.setSAndPRating(organizationDetailOld.getSAndPRating());
        organizationDetail.setSAndPOutlook(organizationDetailOld.getSAndPOutlook());
        organizationDetail.setSAndPrRatingOutlookDate(organizationDetailOld.getSAndPrRatingOutlookDate());
        organizationDetail.setSAndPAttachment(organizationDetailOld.getSAndPAttachment());
        organizationDetail.setMisCompanyName(organizationDetailOld.getMisCompanyName());
        organizationDetail.setMisCompanyCountry(organizationDetailOld.getMisCompanyCountry());
        organizationDetail.setMisCompanyWebsite(organizationDetailOld.getMisCompanyWebsite());
        organizationDetail.setMisCompanyRating(organizationDetailOld.getMisCompanyRating());
        organizationDetail.setMisCompanyOutlook(organizationDetailOld.getMisCompanyOutlook());
        organizationDetail.setMisCompanyAttachment(organizationDetailOld.getMisCompanyAttachment());
        organizationDetail.setAlphaBrokers(organizationDetailOld.getAlphaBrokers());
        organizationDetail.setReInsuranceLob(organizationDetailOld.getReInsuranceLob());
        organizationDetail.setReInsuranceSupport(organizationDetailOld.getReInsuranceSupport());
        organizationDetail.setReInsuranceComments(organizationDetailOld.getReInsuranceComments());

        User user=userService.getCurrentUser();
        organization.setCreatedBy(user);
        organizationService.save(organization);
        Map<String,Object> output=new HashMap<String,Object>();
        output.put("orgId", organizationDO.getOrgId());
        output.put("orgDetailsId", organizationDO.getOrganizationDetails().getOrgDetailsId());
        return output;
    }


/*    @RequestMapping("/secure/saveFinancialInfo")
    @ResponseBody
    public FinancialDetailsDO saveFinancialInfo(@ModelAttribute("userData") FinancialDetailsDO financialDetailsDO) {

        financialDetailsDO=lmUserRegistrationService.saveFinancialInfo(financialDetailsDO);
        return financialDetailsDO;
    }

    @RequestMapping("/secure/saveNetworkInfo")
    @ResponseBody
    public NetworkDetailsDO saveNetworkInfo(@ModelAttribute("userData") NetworkDetailsDO networkDetailsDO) {

        networkDetailsDO=lmUserRegistrationService.saveNetworkInfo(networkDetailsDO);
        return networkDetailsDO;
    }

    @RequestMapping("/secure/saveAccountInfo")
    @ResponseBody
    public AccountDetailsDO saveAccountInfo(@ModelAttribute("userData") AccountDetailsDO accountDetailsDO) {

        accountDetailsDO=lmUserRegistrationService.saveAccountInfo(accountDetailsDO);
        return accountDetailsDO;
    }

    @RequestMapping("/secure/saveBankingInfo")
    @ResponseBody
    public BankingDetailsDO saveBankingInfo(@ModelAttribute("userData") BankingDetailsDO bankingDetailsDO) {

        bankingDetailsDO=lmUserRegistrationService.saveBankingInfo(bankingDetailsDO);
        return bankingDetailsDO;
    }*/

}
