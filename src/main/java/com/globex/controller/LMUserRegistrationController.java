package com.globex.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globex.constants.AppConstants;
import com.globex.model.vo.OrganizationDO;
import com.globex.model.vo.PartnerDO;

import com.globex.model.vo.lm.OrganizationDetailsDO;
import com.globex.repository.rdbms.PartnerMarketRepository;
import com.globex.repository.rdbms.UserRepository;
import com.globex.service.LMUserRegistrationService;
import com.globex.service.OrganizationService;
import com.globex.service.UserService;
import com.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
        PartnerDO partnerDO=lmUserRegistrationService.getPartnerDetails();
        Map<String, Object> model = new HashMap<String, Object>();
        String view ="globex/partner_market";
        if(partnerDO!=null){
            model.put("partnerMarket", AppUtils.convertObjectTOJSONString(partnerDO));
        }
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
    public OrganizationDO saveOrgInfo(@ModelAttribute("userData") OrganizationDO organizationDO) throws Exception {

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
        organizationService.save(organizationDO);
        return organizationDO;
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
