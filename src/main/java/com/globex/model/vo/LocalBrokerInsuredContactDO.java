package com.globex.model.vo;

import com.globex.model.entity.common.LocalBrokerInsuredContact;
import com.globex.model.vo.pm.ApplicationDO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 4/11/2017.
 */
@Data
public class LocalBrokerInsuredContactDO implements Serializable{

    private Integer localBrokerInsuredContactId;

    private ApplicationDO application;

    private String country;

    private String contactName;

    private String address;

    private Integer phoneCountryCode;

    private Integer phoneAreaCode;

    private Long phone;

    private Integer phoneExtension;

    private Integer faxCountryCode;

    private Integer faxAreaCode;

    private Long fax;

    private String email;

    private int contactType;

    public LocalBrokerInsuredContactDO(LocalBrokerInsuredContact localBrokerInsuredContact){
        this.country=localBrokerInsuredContact.getCountry();
        this.contactName=localBrokerInsuredContact.getContactName();
        this.address=localBrokerInsuredContact.getAddress();
        this.phoneCountryCode=localBrokerInsuredContact.getPhoneCountryCode();
        this.phoneAreaCode=localBrokerInsuredContact.getPhoneAreaCode();
        this.phone=localBrokerInsuredContact.getPhone();
        this.phoneExtension=localBrokerInsuredContact.getPhoneExtension();
        this.faxCountryCode=localBrokerInsuredContact.getFaxCountryCode();
        this.faxAreaCode=localBrokerInsuredContact.getFaxAreaCode();
        this.fax=localBrokerInsuredContact.getFax();
        this.email=localBrokerInsuredContact.getEmail();
        this.contactType=localBrokerInsuredContact.getContactType();
    }

    public LocalBrokerInsuredContact getValue(){
        LocalBrokerInsuredContact localBrokerInsuredContact=new LocalBrokerInsuredContact();
        localBrokerInsuredContact.setCountry(this.getCountry());
        localBrokerInsuredContact.setContactName(this.getContactName());
        localBrokerInsuredContact.setAddress(this.getAddress());
        localBrokerInsuredContact.setPhoneCountryCode(this.getPhoneCountryCode());
        localBrokerInsuredContact.setPhoneAreaCode(this.getPhoneAreaCode());
        localBrokerInsuredContact.setPhone(this.getPhone());
        localBrokerInsuredContact.setPhoneExtension(this.getPhoneExtension());
        localBrokerInsuredContact.setFaxCountryCode(this.getFaxCountryCode());
        localBrokerInsuredContact.setFaxAreaCode(this.getFaxAreaCode());
        localBrokerInsuredContact.setFax(this.getFax());
        localBrokerInsuredContact.setEmail(this.getEmail());
        localBrokerInsuredContact.setContactType(this.getContactType());
        return localBrokerInsuredContact;
    }
}
