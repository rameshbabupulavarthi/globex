package com.globex.model.vo;

import com.globex.model.entity.user.PartnerMarket;
import com.globex.security.CurrentUserDO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by Ramesh on 25-12-2016.
 */
@Data
public class PartnerMarketDO implements Serializable{

    private Integer id;

    private String orgName;

    private String addrLine1;

    private String addrLine2;

    private String city;

    private String state;

    private String zipcode;

    private String country;

    private String website;

    //primary contact
    private CurrentUserDO userDO;

    private String comments;

    // bank information
    private String firstName;

    private String lastName;

    private String email;

    private String phoneCountryCode;

    private String phoneAreaCode;

    private String phone;

    private String faxCountryCode;

    private String faxAreaCode;

    private String fax;

    private String mobileCountryCode;

    private String mobile;

    private String bankDetails;

    private String otherDetails;

    private String createdDate;

    private String createdBy;

    private String modifiedDate;

    private String modifiedBy;



    //step2
    private int companytype;
    private String parentcompany;
    private String licenseno;
    private String licenseauthority;
    private String licenseauthwebsite;
    //private int comestablishmentyear;
    private String ambestrating;
    private int ambestlook;
    /*private Date ambestratingdate;*/
    private String amratingattachment;//file
    private Map<Integer,Object> grossPremium;
    private Map<Integer,String> combinedRatio;
    private Map<Integer,Object> totalAssets;
    private Map<Integer,Object> financialReports;
    private Map<Integer,String> ranking;
    private String otherbrokers;


    private int sandprating;
    private String sandpoutlook;
    private String sandpratingoutlookdate;
    private String sandpattachment;//file
    private String misccompname;
    private String misccompcountry;
    private String misccomprating;
    private String misccompoutlook;
    private String ratedbyotheragency;
    private String misccompattachment;//file
    private int businesstowritel;
    private int businessnottowrite;
    private String lobcomments;

    //Account Handling Details:
   /* private String countrylist;
    private String selectedcountry;
    private String territorycomment;*/


    //step3
    private int networkpartnerqua;
    private int networkpartnerqualob;
    private String generalcomments;
    private int networkpartnerscore;
    private String averageresponsetime;
    private String primarycontactcomments;

    //
    private int vettingstatus;
    private String vettingstatuscomments;
    /*private Date vettingdate;
    private Date vettingreminder;*/
    private String vettingremindercomments;



    //step4
    private String countrylist;
    private String territorycomment;
    private String selectedcountry;
    private String authloblimit;
    private String minimumpremium;
    private String authlobcurrency;
    private String authlobpremiumreverse;
    private String comestablishmentyear;
    private String authlobexacttaxes;
    private String authlobexacttaxes1text1;
    private String authlobexacttaxes1text2;
    private int taxresponsible;
    private int flatpercentage;
    private int taxapplied;
    private String lobtariffcomment;
    private String lobtariffcommentattach;//file
    private String reinsurancecommission;
    private String lobmandatoryclauses;
    private String lobmandatoryclausesattach;
    private int loc_curr_ref_loc_policy;
    private String loc_curr_ref_loc_policy_text;
    private int for_juri_aval_loc_policy;
    private String for_juri_aval_loc_policy_text;
    private int manuscript_aval;
    private String manuscript_aval_text;
    private int for_reinsur_support;
    private String for_reinsur_support_text;
    private String insu_required_doc;//file
    private String insu_required_doc_attach;
    private int taxid;
    private String taxid_text;
    private int serviceoption;
    private String serviceoption_text;
    private int reinsur_broker;
    private String reinsur_broker_text;
    private int reg_regulator;
    private String reg_regulator_text;
    private String registration_procedure;
    private String registration_procedure_attach;//file

    //
    //private String selectedcountry;
    private String required_doc_reinsur_place;
    private String required_doc_reinsur_place_attach;
    private String spec_req_doc_reinsur_place;
    private String spec_req_doc_reinsur_place_attach;
    private int comp_invol_claims;
    private String comp_invol_claims_text;
    private int claim_pay_master_local;
    private String claim_pay_master_local_text;
    private String claim_handling_wording_attach;//file
    private int premium_pay_option;
    private int premium_with_tax;
    private int non_admitted_policy;
    private String non_admitted_policy_text;
    private String non_admitted_comments;
    private int mandatory_reinsur_cession;
    private String mandatory_reinsur_cession_text;
    private String mandatory_reinsur_comments;
    private String policy_lang;
    private int tacit_renewal;
    private String tacit_renewal_text;
    private String tacit_renewal_comments;
    private String network;
    private int cash_before_cover_req;
    private String cash_before_cover_req_text;
    private int premium_payment_warranty;
    private String premium_payment_warranty_text;
    private int premium_payment_warranty_days;
    private int foreign_currency_accept;
    private String foreign_currency_accept_text;
    private String acceptable_currency;
    private String premium_remittance_time;
    private int backdating_possible;
    private String backdating_possible_text;
    private int local_currency_requirement;
    private String local_currency_requirement_text;
    private int state_reinsur_involv;
    private String state_reinsur_involv_text;
    private String accounting;
    private String general_comments;
    private String general_attachment;//file
    private String policy_forms_wording_attach;


    //STEP5 :BANKING INFORMATION
    private String dept_name;
    private String dept_role;
    private String dept_comments;
    private String dept_office;
    private String dept_gsm;
    private String dept_email;
    private String bank_details;
    private String bank_address;
    private String bank_name;
    private String bank_iban;
    private String bank_swift_code;


    public PartnerMarketDO(){

    }
    public PartnerMarketDO(PartnerMarket partnerMarket,CurrentUserDO userDO){
        this.id=partnerMarket.getId();
        this.orgName=partnerMarket.getOrgName();
        this.addrLine1=partnerMarket.getAddrLine1();
        this.addrLine2=partnerMarket.getAddrLine2();
        this.city=partnerMarket.getCity();
        this.state=partnerMarket.getState();
        this.zipcode=partnerMarket.getZipCode();
        this.country=partnerMarket.getCountry();
        this.website=partnerMarket.getWebsite();
        this.userDO=userDO;
        this.comments=partnerMarket.getComments();
        this.firstName=partnerMarket.getFirstName();
        this.lastName=partnerMarket.getLastName();
        this.email=partnerMarket.getEmail();
        this.phoneCountryCode=partnerMarket.getPhoneCountryCode();
        this.phoneAreaCode=partnerMarket.getPhoneAreaCode();
        this.phone=partnerMarket.getPhone();
        this.faxCountryCode=partnerMarket.getFaxCountryCode();
        this.faxAreaCode=partnerMarket.getFaxAreaCode();
        this.fax=partnerMarket.getFax();
        this.mobileCountryCode=partnerMarket.getMobileCountryCode();
        this.mobile=partnerMarket.getMobile();
        this.bankDetails=partnerMarket.getBankDetails();
        this.otherDetails=partnerMarket.getOtherDetails();
        this.createdDate=partnerMarket.getCreatedDate();
        this.createdBy=partnerMarket.getCreatedBy();
        this.modifiedDate=partnerMarket.getModifiedDate();
        this.modifiedBy=partnerMarket.getModifiedBy();

    }
}
