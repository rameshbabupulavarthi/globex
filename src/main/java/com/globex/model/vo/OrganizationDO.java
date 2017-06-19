package com.globex.model.vo;

import com.globex.model.entity.common.RateRequirement;
import com.globex.model.entity.lm.UWDepDetails;
import com.globex.model.entity.partner.*;
import com.globex.model.vo.lm.OrganizationDetailsDO;
import com.globex.model.entity.pm.*;
import com.globex.model.entity.user.User;
import com.globex.model.vo.lm.*;
import com.globex.model.vo.pm.AccountInfoDO;
import com.globex.model.vo.pm.BranchOfficeDO;
import com.globex.model.vo.pm.CoverageAreaDO;
import com.globex.model.vo.pm.RegisteredCountryDO;
import com.utils.DateUtil;
import com.utils.StringUtils;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
@ToString(exclude = {"users","accountInfoDOs","coverageAreaDOs","registeredCountryDOs","coverageContactDOs","branchOfficeDOs",
                     "orgFileAttachment","amRatingFileAttachment","snpFileAttachment",
                     /*"organizationHistories","lobs", "rateRequirements", "commissionRequirements", "uwDepDetails","bankingDetails","miscRatings"*/})
public class OrganizationDO implements Serializable {

    private Long orgId;

    private String orgName;

    private String regDate;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String country;

    private String zip;

    private String website;

    private String telePhone;

    private Integer orgUserType;

    private Integer approved;

    private String comment;

    private List<UserDO> users;

    private List<AccountInfoDO> accountInfoDOs;

    private List<CoverageAreaDO> coverageAreaDOs;

    private List<RegisteredCountryDO> registeredCountryDOs;

    private List<CoverageContactDO> coverageContactDOs;

    private List<BranchOfficeDO> branchOfficeDOs;

    private String userJsonStr;
    private String accountInfoJsonStr;
    private String coverageAreaJsonStr;
    private String registeredCountriesJsonStr;
    private String coverageContactsJsonStr;
    private String branchOfficeJsonStr;

    private OrganizationDetailsDO organizationDetails;

    private List<MiscRatingDO> miscRatings;

    private List<LobDO> lobs;

    private List<OrgRateRequirementDO> rateRequirements;

    private List<CommissionRequirementDO> commissionRequirements;

    private List<OrganizationHistoryDO> organizationHistories;

    private List<UWDepDetailsDO> uwDepDetails;

    private List<BankingDetailsDO> bankingDetails;

    private String orgParametersJson;

    private String orgDetailsJson;

    private CommonsMultipartFile orgFileAttachment;

    private CommonsMultipartFile amRatingFileAttachment;

    private CommonsMultipartFile snpFileAttachment;


    private CommonsMultipartFile insuRequiredDocAttach;

    private CommonsMultipartFile adviceRegistrationAttach;

    private CommonsMultipartFile registrationProcedureAttach;

    private CommonsMultipartFile requiredDocReinsurPlaceAttach;

    private CommonsMultipartFile claimHandlingWordingAttach;

    public OrganizationDO(){

    }

    public OrganizationDO(Organization organization){
        this.orgId=organization.getId();
        this.orgName=organization.getOrgName();
        //this.regDate=organization.getRegDate();
        this.address1=organization.getAddress1();
        this.address2=organization.getAddress2();
        this.city=organization.getCity();
        this.state=organization.getState();
        this.country=organization.getCountry();
        this.zip=organization.getZip();
        this.website=organization.getWebsite();
        this.telePhone=organization.getTelePhone();
        // this.orgType=organization.getOrgType();
        //this.parentOrgId=organization.getParentOrgId();
        /*this.approved=organization.getApproved();
        this.comment=organization.getComment();
        this.licenceState=organization.getLicenceState();*/
    }


    public Organization value(){
        Organization organization=new Organization();
        organization.setId(this.getOrgId());
        organization.setOrgName(StringUtils.getValue(this.getOrgName()));
        organization.setRegDate(DateUtil.getTimestamp(this.getRegDate()));
        organization.setAddress1(StringUtils.getValue(this.getAddress1()));
        organization.setAddress2(StringUtils.getValue(this.getAddress2()));
        organization.setCity(StringUtils.getValue(this.getCity()));
        organization.setState(StringUtils.getValue(this.getState()));
        organization.setCountry(StringUtils.getValue(this.getCountry()));
        organization.setZip(StringUtils.getValue(this.getZip()));
        organization.setWebsite(StringUtils.getValue(this.getWebsite()));
        organization.setTelePhone(StringUtils.getValue(telePhone));
        organization.setApproved(this.getApproved());
        organization.setComment(StringUtils.getValue(this.getComment()));
        organization.setOrgUserType(this.getOrgUserType());

        organization.setUsers(getUsers(this.getUsers(),organization));
        organization.setAccountInfos(getAccountInfos(this.getAccountInfoDOs(),organization));
        organization.setCoverageAreas(getCoverageAreas(this.getCoverageAreaDOs(),organization));

        organization.setRegisteredCountries(getRegisteredCountries(this.getRegisteredCountryDOs(), organization));
        organization.setCoverageContacts(getCoverageContacts(this.getCoverageContactDOs(), organization));
        organization.setBranchOffices(getBranchOffices(this.getBranchOfficeDOs(), organization));
        getLMDetails(organization);
        return organization;
    }

    public void getLMDetails(Organization organization){

        if(organizationDetails!=null){
            OrganizationDetail organizationDet=organizationDetails.value();
            organizationDet.setOrganization(organization);
            organization.setOrganizationDetails(organizationDet);
        }

        if(miscRatings!=null){
            List<MiscRating> miscRatingList=new ArrayList<MiscRating>();
            for(MiscRatingDO miscRatingDO:miscRatings){
                MiscRating miscRating=miscRatingDO.value();
                miscRating.setOrganization(organization);
                miscRatingList.add(miscRating);
            }
            organization.setMiscRatings(miscRatingList);
        }

        if(lobs!=null && !lobs.isEmpty()){
            List<LOB> lobList=new ArrayList<LOB>();
            for(LobDO lobDO:lobs){
                LOB lob=lobDO.value();
                lob.setOrganization(organization);
                lobList.add(lob);
            }
            organization.setLobs(lobList);
        }

        if(rateRequirements!=null && !rateRequirements.isEmpty()){
            List<OrgRateRequirement> orgRateRequirements=new ArrayList<OrgRateRequirement>();
            for(OrgRateRequirementDO rateRequirementDO:rateRequirements){
                OrgRateRequirement orgRateRequirement=rateRequirementDO.value();
                orgRateRequirement.setOrganization(organization);
                orgRateRequirements.add(orgRateRequirement);
            }
            organization.setRateRequirements(orgRateRequirements);
        }

        if(commissionRequirements!=null && !commissionRequirements.isEmpty()){
            List<CommissionRequirement> commissionRequirementList=new ArrayList<CommissionRequirement>();
            for(CommissionRequirementDO commissionRequirementDO:commissionRequirements){
                CommissionRequirement commissionRequirement=commissionRequirementDO.value();
                commissionRequirement.setOrganization(organization);
                commissionRequirementList.add(commissionRequirement);
            }
            organization.setCommissionRequirements(commissionRequirementList);
        }

        if(organizationHistories!=null && !organizationHistories.isEmpty()){
            List<OrganizationHistory> organizationHistoryList=new ArrayList<OrganizationHistory>();
            for(OrganizationHistoryDO organizationHistoryDO:organizationHistories){
                OrganizationHistory organizationHistory=organizationHistoryDO.value();
                organizationHistory.setOrganization(organization);
                organizationHistoryList.add(organizationHistory);
            }
            organization.setOrganizationHistories(organizationHistoryList);
        }

        if(uwDepDetails!=null && !uwDepDetails.isEmpty()){
            List<UWDepDetails> uwDepDetailsList=new ArrayList<UWDepDetails>();
            for(UWDepDetailsDO uwDepDetailsDO:uwDepDetails){
                UWDepDetails uwDepDetails=uwDepDetailsDO.value();
                uwDepDetails.setOrganization(organization);
                uwDepDetailsList.add(uwDepDetails);
            }
            organization.setUwDepDetails(uwDepDetailsList);
        }

        if(bankingDetails!=null && !bankingDetails.isEmpty()){
            List<BankingDetails> bankingDetailList=new ArrayList<BankingDetails>();
            for(BankingDetailsDO bankingDetailsDO:bankingDetails){
                BankingDetails bankingDetails=bankingDetailsDO.value();
                bankingDetails.setOrganization(organization);
                bankingDetailList.add(bankingDetails);
            }
            organization.setBankingDetails(bankingDetailList);
        }
    }

    public void loadFullDetails(Organization organization){

        List<User> users= organization.getUsers();
        List<AccountInfo> accountInfos=organization.getAccountInfos();
        List<CoverageArea> coverageAreas=organization.getCoverageAreas();
        List<RegisteredCountry> registeredCountries=organization.getRegisteredCountries();
        List<CoverageContact> coverageContacts=organization.getCoverageContacts();
        List<BranchOffice> branchOffices=organization.getBranchOffices();

        List<UserDO> userDOs= new ArrayList<UserDO>();
        List<AccountInfoDO> accountInfoDOs=new ArrayList<AccountInfoDO>();
        List<CoverageAreaDO> coverageAreaDOs=new ArrayList<CoverageAreaDO>();
        List<RegisteredCountryDO> registeredCountryDOs=new ArrayList<RegisteredCountryDO>();
        List<CoverageContactDO> coverageContactDOs=new ArrayList<CoverageContactDO>();
        List<BranchOfficeDO> branchOfficeDOs=new ArrayList<BranchOfficeDO>();

        for(User user: users){
            UserDO userDO=new UserDO(user);
            userDOs.add(userDO);
        }

        for(AccountInfo accountInfo: accountInfos){
            AccountInfoDO accountInfoDO=new AccountInfoDO(accountInfo);
            accountInfoDOs.add(accountInfoDO);
        }

        for(CoverageArea coverageArea: coverageAreas){
            CoverageAreaDO coverageAreaDO=new CoverageAreaDO(coverageArea);
            coverageAreaDOs.add(coverageAreaDO);
        }

        for(RegisteredCountry registeredCountry:registeredCountries){
            RegisteredCountryDO registeredCountryDO=new RegisteredCountryDO(registeredCountry);
            registeredCountryDOs.add(registeredCountryDO);
        }

        for(CoverageContact coverageContact:coverageContacts){
            CoverageContactDO coverageContactDO=new CoverageContactDO(coverageContact);
            coverageContactDOs.add(coverageContactDO);
        }

        for(BranchOffice branchOffice:branchOffices){
            BranchOfficeDO branchOfficeDO=new BranchOfficeDO(branchOffice);
            branchOfficeDOs.add(branchOfficeDO);
        }

        OrganizationDetail organizationDetail=organization.getOrganizationDetails();
        List<MiscRatingDO> miscRatingDOs=new ArrayList<MiscRatingDO>();
        List<LobDO> lobDOs=new ArrayList<LobDO>();
        List<OrgRateRequirementDO> orgRateRequirementDOs=new ArrayList<OrgRateRequirementDO>();
        List<CommissionRequirementDO> commissionRequirementDOs=new ArrayList<CommissionRequirementDO>();
        List<OrganizationHistoryDO> organizationHistoryDOs=new ArrayList<OrganizationHistoryDO>();
        List<UWDepDetailsDO> uwDepDetailsDOs=new ArrayList<UWDepDetailsDO>();
        List<BankingDetailsDO> bankingDetailsDOs=new ArrayList<BankingDetailsDO>();


        OrganizationDetailsDO organizationDetailsDO=null;
        if(organizationDetail!=null){
            organizationDetailsDO=new OrganizationDetailsDO(organizationDetail);
        }

        for(MiscRating miscRating:organization.getMiscRatings()){
            MiscRatingDO miscRatingDO=new MiscRatingDO(miscRating);
            miscRatingDOs.add(miscRatingDO);
        }

        for(LOB lob: organization.getLobs()){
            LobDO lobDO=new LobDO(lob);
            lobDOs.add(lobDO);
        }

        for(OrgRateRequirement orgRateRequirement: organization.getRateRequirements()){
            OrgRateRequirementDO orgRateRequirementDO=new OrgRateRequirementDO(orgRateRequirement);
            orgRateRequirementDOs.add(orgRateRequirementDO);
        }

        for (CommissionRequirement commissionRequirement:organization.getCommissionRequirements()){
            CommissionRequirementDO commissionRequirementDO=new CommissionRequirementDO(commissionRequirement);
            commissionRequirementDOs.add(commissionRequirementDO);
        }

        for (OrganizationHistory organizationHistory: organization.getOrganizationHistories()){
            OrganizationHistoryDO organizationHistoryDO=new OrganizationHistoryDO(organizationHistory);
            organizationHistoryDOs.add(organizationHistoryDO);
        }
        
        for(UWDepDetails uwDepDetails:organization.getUwDepDetails()){
            UWDepDetailsDO uwDepDetailsDO=new UWDepDetailsDO(uwDepDetails);
            uwDepDetailsDOs.add(uwDepDetailsDO);
        }

        for(BankingDetails bankingDetails:organization.getBankingDetails()){
            BankingDetailsDO bankingDetailsDO=new BankingDetailsDO(bankingDetails);
            bankingDetailsDOs.add(bankingDetailsDO);
        }

        this.users=userDOs;
        this.accountInfoDOs=accountInfoDOs;
        this.coverageAreaDOs=coverageAreaDOs;
        this.registeredCountryDOs=registeredCountryDOs;
        this.coverageContactDOs=coverageContactDOs;
        this.branchOfficeDOs=branchOfficeDOs;

        this.organizationDetails=organizationDetailsDO;
        this.lobs=lobDOs;
        this.miscRatings=miscRatingDOs;
        this.rateRequirements=orgRateRequirementDOs;
        this.commissionRequirements=commissionRequirementDOs;
        this.organizationHistories= organizationHistoryDOs;
        this.uwDepDetails=uwDepDetailsDOs;
        this.bankingDetails=bankingDetailsDOs;
    }

    public void loadUsers(Organization organization){
        List<UserDO> userDOs= new ArrayList<UserDO>();
        List<User> users= organization.getUsers();
        for(User user: users){
            UserDO userDO=new UserDO(user);
            userDOs.add(userDO);
        }

        this.users=userDOs;
    }

    private List<User> getUsers(List<UserDO> userDOs,Organization organization){
        if(userDOs!=null && !userDOs.isEmpty()){
            List<User> users=new ArrayList<User>();
            for(UserDO userDO:userDOs){
                User user=userDO.value();
                user.setOrganization(organization);
                users.add(user);
            }
            return users;
        }
        return null;
    }

    private List<AccountInfo> getAccountInfos(List<AccountInfoDO> accountInfoDOs,Organization organization){
        if(accountInfoDOs!=null && !accountInfoDOs.isEmpty()){
            List<AccountInfo> accountInfos=new ArrayList<AccountInfo>();
            for(AccountInfoDO accountInfoDO:accountInfoDOs){
                AccountInfo accountInfo=accountInfoDO.value();
                accountInfo.setOrganization(organization);
                accountInfos.add(accountInfo);
            }
            return accountInfos;
        }
        return null;
    }

    private List<CoverageArea> getCoverageAreas(List<CoverageAreaDO> coverageAreaDOs,Organization organization){
        if(coverageAreaDOs!=null && !coverageAreaDOs.isEmpty()){
            List<CoverageArea> coverageAreas=new ArrayList<CoverageArea>();
            for(CoverageAreaDO coverageAreaDO:coverageAreaDOs){
                CoverageArea coverageArea=coverageAreaDO.value();
                coverageArea.setOrganization(organization);
                coverageAreas.add(coverageArea);
            }
            return coverageAreas;
        }
        return null;
    }


    private List<RegisteredCountry> getRegisteredCountries(List<RegisteredCountryDO> registeredCountryDOs,Organization organization){
        if(registeredCountryDOs!=null && !registeredCountryDOs.isEmpty()){
            List<RegisteredCountry> registeredCountries=new ArrayList<RegisteredCountry>();
            for(RegisteredCountryDO registeredCountryDO:registeredCountryDOs){
                RegisteredCountry registeredCountry=registeredCountryDO.value();
                registeredCountry.setOrganization(organization);
                registeredCountries.add(registeredCountry);
            }
            return registeredCountries;
        }
        return null;
    }

    private List<CoverageContact> getCoverageContacts(List<CoverageContactDO> coverageContactDOs,Organization organization){
        if(coverageContactDOs!=null && !coverageContactDOs.isEmpty()){
            List<CoverageContact> coverageContacts=new ArrayList<CoverageContact>();
            for(CoverageContactDO coverageContactDO:coverageContactDOs){
                CoverageContact coverageContact=coverageContactDO.value();
                coverageContact.setOrganization(organization);
                coverageContacts.add(coverageContact);
            }
            return coverageContacts;
        }
        return null;
    }

    private List<BranchOffice> getBranchOffices(List<BranchOfficeDO> branchOfficeDOs,Organization organization){
        if(branchOfficeDOs!=null && !branchOfficeDOs.isEmpty()){
            List<BranchOffice> branchOffices=new ArrayList<BranchOffice>();
            for(BranchOfficeDO branchOfficeDO:branchOfficeDOs){
                BranchOffice branchOffice=branchOfficeDO.value();
                branchOffice.setOrganization(organization);
                branchOffices.add(branchOffice);
            }
            return branchOffices;
        }
        return null;
    }
}
