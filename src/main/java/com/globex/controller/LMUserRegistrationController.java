package com.globex.controller;

import com.globex.model.vo.*;
import com.globex.repository.rdbms.PartnerMarketRepository;
import com.globex.repository.rdbms.UserRepository;
import com.globex.service.LMUserRegistrationService;
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
    public OrganizationDetailsDO saveOrgInfo(@ModelAttribute("userData") OrganizationDetailsDO organizationDetailsDO) {

        System.out.println(organizationDetailsDO);
        organizationDetailsDO=lmUserRegistrationService.saveOrgInfo(organizationDetailsDO);
        return organizationDetailsDO;
    }


    @RequestMapping("/secure/saveFinancialInfo")
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
    }

}
