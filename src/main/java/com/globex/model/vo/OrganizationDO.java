package com.globex.model.vo;

import com.globex.model.entity.pm.AccountInfo;
import com.globex.model.entity.pm.CoverageArea;
import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
import com.globex.model.vo.pm.AccountInfoDO;
import com.globex.model.vo.pm.CoverageAreaDO;
import com.utils.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
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

    private Integer orgType;

    private Integer parentOrgId;

    private Integer approved;

    private String comment;

    private String licenceState;

    private Set<UserDO> users;

    private Set<AccountInfoDO> accountInfos;

    private Set<CoverageAreaDO> coverageAreas;


    private String userJsonStr;
    private String accountInfoJsonStr;
    private String coverageAreaJsonStr;


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
        this.orgType=organization.getOrgType();
        this.parentOrgId=organization.getParentOrgId();
        this.approved=organization.getApproved();
        this.comment=organization.getComment();
        this.licenceState=organization.getLicenceState();
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
        organization.setOrgType(this.getOrgType());
        organization.setParentOrgId(this.getParentOrgId());
        organization.setApproved(this.getApproved());
        organization.setComment(this.getComment());
        organization.setLicenceState(this.getLicenceState());

        organization.setUsers(getUsers(this.getUsers(),organization));
        organization.setAccountInfos(getAccountInfos(this.getAccountInfos(),organization));
        organization.setCoverageAreas(getCoverageAreas(this.getCoverageAreas(),organization));
        return organization;
    }

    public void loadChildren(Organization organization){

        Set<User> users= organization.getUsers();
        Set<AccountInfo> accountInfos=organization.getAccountInfos();
        Set<CoverageArea> coverageAreas=organization.getCoverageAreas();

        Set<UserDO> userDOs= new HashSet<UserDO>();
        Set<AccountInfoDO> accountInfoDOs=new HashSet<AccountInfoDO>();
        Set<CoverageAreaDO> coverageAreaDOs=new HashSet<CoverageAreaDO>();

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

        this.users=userDOs;
        this.accountInfos=accountInfoDOs;
        this.coverageAreas=coverageAreaDOs;
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
}
