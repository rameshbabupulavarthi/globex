package com.globex.model.vo.pm;

import com.globex.model.entity.pm.AccountInfo;
import com.globex.model.vo.OrganizationDO;
import com.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 5/13/2017.
 */
@Data
public class AccountInfoDO implements Serializable {

    private Integer accountInfoId;

    private OrganizationDO organization;

    private String contactFirstName;

    private String contactLastName;

    private Integer contactPhoneCountryCode;

    private Integer contactPhoneAreaCode;

    private Long contactPhone;

    private Integer contactPhoneExtension;

    private Integer contactFaxCountryCode;

    private Integer contactFaxAreaCode;

    private Long contactFax;

    private Integer contactMobileCountryCode;

    private Long contactMobile;

    private String contactEmail;

    private String bankInfo;

    private String otherInfo;

    public AccountInfoDO(){

    }

    public AccountInfoDO(AccountInfo accountInfo){

        this.accountInfoId=accountInfo.getAccountInfoId();
        this.organization=new OrganizationDO(accountInfo.getOrganization());
        this.contactFirstName=accountInfo.getFirstName();
        this.contactLastName=accountInfo.getLastName();
        this.contactPhoneCountryCode=accountInfo.getPhoneCountryCode();
        this.contactPhoneAreaCode=accountInfo.getPhoneAreaCode();
        this.contactPhone=accountInfo.getPhone();
        this.contactPhoneExtension=accountInfo.getPhoneExtension();
        this.contactFaxCountryCode=accountInfo.getFaxCountryCode();
        this.contactFaxAreaCode=accountInfo.getFaxAreaCode();
        this.contactFax=accountInfo.getFax();
        this.contactMobileCountryCode=accountInfo.getMobileCountryCode();
        this.contactMobile=accountInfo.getMobile();
        this.contactEmail=accountInfo.getEmail();
        this.bankInfo=accountInfo.getBankInfo();
        this.otherInfo=accountInfo.getOtherInfo();
    }

    public AccountInfo value(){
        AccountInfo accountInfo=new AccountInfo();
        accountInfo.setAccountInfoId(this.getAccountInfoId());
        accountInfo.setOrganization(this.getOrganization()!=null ?this.getOrganization().value():null);
        accountInfo.setFirstName(StringUtils.getValue(this.getContactFirstName()));
        accountInfo.setLastName(StringUtils.getValue(this.getContactLastName()));
        accountInfo.setPhoneCountryCode(this.getContactPhoneCountryCode());
        accountInfo.setPhoneAreaCode(this.getContactPhoneAreaCode());
        accountInfo.setPhone(this.getContactPhone());
        accountInfo.setPhoneExtension(this.getContactPhoneExtension());
        accountInfo.setFaxCountryCode(this.getContactFaxCountryCode());
        accountInfo.setFaxAreaCode(this.getContactFaxAreaCode());
        accountInfo.setFax(this.getContactFax());
        accountInfo.setMobileCountryCode(this.getContactMobileCountryCode());
        accountInfo.setMobile(this.getContactMobile());
        accountInfo.setEmail(StringUtils.getValue(this.getContactEmail()));
        accountInfo.setBankInfo(this.getBankInfo());
        accountInfo.setOtherInfo(this.getOtherInfo());
        return accountInfo;
    }
}
