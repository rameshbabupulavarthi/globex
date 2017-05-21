package com.globex.model.vo.pm;

import com.globex.model.entity.pm.ContactInfo;
import com.globex.model.vo.OrganizationDO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 5/18/2017.
 */
@Data
public class ContactInfoDO implements Serializable{

    private Integer contactInfoId;

/*    private OrganizationDO organization;*/

    private String firstName;

    private String lastName;

    private String officeLocation;

    private String email;

    private Integer phoneCountryCode;

    private Integer phoneAreaCode;

    private Long phone;

    private Integer phoneExtension;

    private Integer faxCountryCode;

    private Integer faxAreaCode;

    private Long fax;

    private Integer mobileCountryCode;

    private Long mobile;

    private int userType;

    private String comments;

    public ContactInfoDO(){

    }

    public ContactInfoDO(ContactInfo contactInfo){
        this.contactInfoId=contactInfo.getContactInfoId();
        this.firstName=contactInfo.getFirstName();;
        this.lastName=contactInfo.getLastName();
        this.officeLocation=contactInfo.getOfficeLocation();
        this.email=contactInfo.getEmail();
        this.phoneCountryCode=contactInfo.getPhoneCountryCode();
        this.phoneAreaCode=contactInfo.getPhoneAreaCode();
        this.phone=contactInfo.getPhone();
        this.phoneExtension=contactInfo.getPhoneExtension();
        this.faxCountryCode=contactInfo.getFaxCountryCode();
        this.faxAreaCode=contactInfo.getFaxAreaCode();
        this.fax=contactInfo.getFax();
        this.mobileCountryCode=contactInfo.getMobileCountryCode();
        this.mobile=contactInfo.getMobile();
        this.userType=contactInfo.getUserType();
        this.comments=contactInfo.getComments();
    }

    public ContactInfo value(){
        ContactInfo contactInfo=new ContactInfo();
        contactInfo.setContactInfoId(this.getContactInfoId());
        contactInfo.setFirstName(this.getFirstName());
        contactInfo.setLastName(this.getLastName()!=null?this.getLastName():this.getFirstName());
        contactInfo.setOfficeLocation(this.getOfficeLocation());
        contactInfo.setEmail(this.getEmail());
        contactInfo.setPhoneCountryCode(this.getPhoneCountryCode());
        contactInfo.setPhoneAreaCode(this.getPhoneAreaCode());
        contactInfo.setPhone(this.getPhone());
        contactInfo.setPhoneExtension(this.getPhoneExtension());
        contactInfo.setFaxCountryCode(this.getFaxCountryCode());
        contactInfo.setFaxAreaCode(this.getFaxAreaCode());
        contactInfo.setFax(this.getFax());
        contactInfo.setMobileCountryCode(this.getMobileCountryCode());
        contactInfo.setMobile(this.getMobile());
        contactInfo.setUserType(this.getUserType());
        contactInfo.setComments(this.getComments());
        return contactInfo;
    }
}
