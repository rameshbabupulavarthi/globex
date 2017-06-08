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
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
@ToString(exclude = {"users","accountInfoDOs","coverageAreaDOs","registeredCountryDOs","coverageContactDOs","branchOfficeDOs"})
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

    private Set<UserDO> users;

    private Set<AccountInfoDO> accountInfoDOs;

    private Set<CoverageAreaDO> coverageAreaDOs;

    private Set<RegisteredCountryDO> registeredCountryDOs;

    private Set<CoverageContactDO> coverageContactDOs;

    private Set<BranchOfficeDO> branchOfficeDOs;

    private String userJsonStr;
    private String accountInfoJsonStr;
    private String coverageAreaJsonStr;
    private String registeredCountriesJsonStr;
    private String coverageContactsJsonStr;
    private String branchOfficeJsonStr;

    private OrganizationDetailsDO organizationDetails;

    private Set<MiscRatingDO> miscRatings;

    private Set<LobDO> lobs;

    private Set<OrgRateRequirementDO> rateRequirements;

    private Set<CommissionRequirementDO> commissionRequirements;

    private Set<OrganizationHistoryDO> organizationHistories;

    private Set<UWDepDetailsDO> uwDepDetails;

    private Set<BankingDetailsDO> bankingDetails;

    private String orgParametersJson;

    private String orgDetailsJson;


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
        // this.orgType=organization.getOrgType();
        //this.parentOrgId=organization.getParentOrgId();
        /*this.approved=organization.getApproved();
        this.comment=organization.getComment();
        this.licenceState=organization.getLicenceState();*/
    }


    public Organization value(){
        Organization organization=new Organization();
        organization.setId(this.getOrgId());
        organization.setOrgName(this.getOrgName());
        organization.setRegDate(DateUtil.getTimestamp(this.getRegDate()));
        organization.setAddress1(this.getAddress1());
        organization.setAddress2(this.getAddress2());
        organization.setCity(this.getCity());
        organization.setState(this.getState());
        organization.setCountry(this.getCountry());
        organization.setZip(this.getZip());
        organization.setWebsite(this.getWebsite());
        organization.setApproved(this.getApproved());
        organization.setComment(this.getComment());
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

        if(lobs!=null && !lobs.isEmpty()){
            Set<LOB> lobList=new HashSet<LOB>();
            for(LobDO lobDO:lobs){
                LOB lob=lobDO.value();
                lob.setOrganization(organization);
                lobList.add(lob);
            }
            organization.setLobs(lobList);
        }

        if(rateRequirements!=null && !rateRequirements.isEmpty()){
            Set<OrgRateRequirement> orgRateRequirements=new HashSet<OrgRateRequirement>();
            for(OrgRateRequirementDO rateRequirementDO:rateRequirements){
                OrgRateRequirement orgRateRequirement=rateRequirementDO.value();
                orgRateRequirement.setOrganization(organization);
                orgRateRequirements.add(orgRateRequirement);
            }
            organization.setRateRequirements(orgRateRequirements);
        }

        if(commissionRequirements!=null && !commissionRequirements.isEmpty()){
            Set<CommissionRequirement> commissionRequirementList=new HashSet<CommissionRequirement>();
            for(CommissionRequirementDO commissionRequirementDO:commissionRequirements){
                CommissionRequirement commissionRequirement=commissionRequirementDO.value();
                commissionRequirement.setOrganization(organization);
                commissionRequirementList.add(commissionRequirement);
            }
            organization.setCommissionRequirements(commissionRequirementList);
        }

        if(organizationHistories!=null && !organizationHistories.isEmpty()){
            Set<OrganizationHistory> organizationHistoryList=new HashSet<OrganizationHistory>();
            for(OrganizationHistoryDO organizationHistoryDO:organizationHistories){
                OrganizationHistory organizationHistory=organizationHistoryDO.value();
                organizationHistory.setOrganization(organization);
                organizationHistoryList.add(organizationHistory);
            }
            organization.setOrganizationHistories(organizationHistoryList);
        }

        if(uwDepDetails!=null && !uwDepDetails.isEmpty()){
            Set<UWDepDetails> uwDepDetailsList=new HashSet<UWDepDetails>();
            for(UWDepDetailsDO uwDepDetailsDO:uwDepDetails){
                UWDepDetails uwDepDetails=uwDepDetailsDO.value();
                uwDepDetails.setOrganization(organization);
                uwDepDetailsList.add(uwDepDetails);
            }
            organization.setUwDepDetails(uwDepDetailsList);
        }

        if(bankingDetails!=null && !bankingDetails.isEmpty()){
            Set<BankingDetails> bankingDetailList=new HashSet<BankingDetails>();
            for(BankingDetailsDO bankingDetailsDO:bankingDetails){
                BankingDetails bankingDetails=bankingDetailsDO.value();
                bankingDetails.setOrganization(organization);
                bankingDetailList.add(bankingDetails);
            }
            organization.setBankingDetails(bankingDetailList);
        }
    }

    public void loadFullDetails(Organization organization){

        Set<User> users= organization.getUsers();
        Set<AccountInfo> accountInfos=organization.getAccountInfos();
        Set<CoverageArea> coverageAreas=organization.getCoverageAreas();
        Set<RegisteredCountry> registeredCountries=organization.getRegisteredCountries();
        Set<CoverageContact> coverageContacts=organization.getCoverageContacts();
        Set<BranchOffice> branchOffices=organization.getBranchOffices();

        Set<UserDO> userDOs= new HashSet<UserDO>();
        Set<AccountInfoDO> accountInfoDOs=new HashSet<AccountInfoDO>();
        Set<CoverageAreaDO> coverageAreaDOs=new HashSet<CoverageAreaDO>();
        Set<RegisteredCountryDO> registeredCountryDOs=new HashSet<RegisteredCountryDO>();
        Set<CoverageContactDO> coverageContactDOs=new HashSet<CoverageContactDO>();
        Set<BranchOfficeDO> branchOfficeDOs=new HashSet<BranchOfficeDO>();

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
        Set<MiscRatingDO> miscRatingDOs=new HashSet<MiscRatingDO>();
        Set<LobDO> lobDOs=new HashSet<LobDO>();
        Set<OrgRateRequirementDO> orgRateRequirementDOs=new HashSet<OrgRateRequirementDO>();
        Set<CommissionRequirementDO> commissionRequirementDOs=new HashSet<CommissionRequirementDO>();
        Set<OrganizationHistoryDO> organizationHistoryDOs=new HashSet<OrganizationHistoryDO>();
        Set<UWDepDetailsDO> uwDepDetailsDOs=new HashSet<UWDepDetailsDO>();
        Set<BankingDetailsDO> bankingDetailsDOs=new HashSet<BankingDetailsDO>();


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
        Set<UserDO> userDOs= new HashSet<UserDO>();
        Set<User> users= organization.getUsers();
        for(User user: users){
            UserDO userDO=new UserDO(user);
            userDOs.add(userDO);
        }

        this.users=userDOs;
    }

    private Set<User> getUsers(Set<UserDO> userDOs,Organization organization){
        if(userDOs!=null && !userDOs.isEmpty()){
            Set<User> users=new HashSet<User>();
            for(UserDO userDO:userDOs){
                User user=userDO.value();
                user.setOrganization(organization);
                users.add(user);
            }
            return users;
        }
        return null;
    }

    private Set<AccountInfo> getAccountInfos(Set<AccountInfoDO> accountInfoDOs,Organization organization){
        if(accountInfoDOs!=null && !accountInfoDOs.isEmpty()){
            Set<AccountInfo> accountInfos=new HashSet<AccountInfo>();
            for(AccountInfoDO accountInfoDO:accountInfoDOs){
                AccountInfo accountInfo=accountInfoDO.value();
                accountInfo.setOrganization(organization);
                accountInfos.add(accountInfo);
            }
            return accountInfos;
        }
        return null;
    }

    private Set<CoverageArea> getCoverageAreas(Set<CoverageAreaDO> coverageAreaDOs,Organization organization){
        if(coverageAreaDOs!=null && !coverageAreaDOs.isEmpty()){
            Set<CoverageArea> coverageAreas=new HashSet<CoverageArea>();
            for(CoverageAreaDO coverageAreaDO:coverageAreaDOs){
                CoverageArea coverageArea=coverageAreaDO.value();
                coverageArea.setOrganization(organization);
                coverageAreas.add(coverageArea);
            }
            return coverageAreas;
        }
        return null;
    }


    private Set<RegisteredCountry> getRegisteredCountries(Set<RegisteredCountryDO> registeredCountryDOs,Organization organization){
        if(registeredCountryDOs!=null && !registeredCountryDOs.isEmpty()){
            Set<RegisteredCountry> registeredCountries=new HashSet<RegisteredCountry>();
            for(RegisteredCountryDO registeredCountryDO:registeredCountryDOs){
                RegisteredCountry registeredCountry=registeredCountryDO.value();
                registeredCountry.setOrganization(organization);
                registeredCountries.add(registeredCountry);
            }
            return registeredCountries;
        }
        return null;
    }

    private Set<CoverageContact> getCoverageContacts(Set<CoverageContactDO> coverageContactDOs,Organization organization){
        if(coverageContactDOs!=null && !coverageContactDOs.isEmpty()){
            Set<CoverageContact> coverageContacts=new HashSet<CoverageContact>();
            for(CoverageContactDO coverageContactDO:coverageContactDOs){
                CoverageContact coverageContact=coverageContactDO.value();
                coverageContact.setOrganization(organization);
                coverageContacts.add(coverageContact);
            }
            return coverageContacts;
        }
        return null;
    }

    private Set<BranchOffice> getBranchOffices(Set<BranchOfficeDO> branchOfficeDOs,Organization organization){
        if(branchOfficeDOs!=null && !branchOfficeDOs.isEmpty()){
            Set<BranchOffice> branchOffices=new HashSet<BranchOffice>();
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
