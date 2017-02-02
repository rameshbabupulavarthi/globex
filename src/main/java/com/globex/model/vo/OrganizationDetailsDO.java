package com.globex.model.vo;

import com.globex.model.entity.partner.ContactDetails;
import com.globex.model.entity.partner.OrganizationDetails;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 12/31/2016.
 */
@Data
public class OrganizationDetailsDO implements Serializable{

    private Long id;

    private String name;

    private String phone;

    private String website;

    private String email;

    private String address;

    private String city;

    private String state;

    private String zipcode;

    private String country;

    private ContactDetailsDO contact;

    public OrganizationDetailsDO(){

    }

    public OrganizationDetailsDO (OrganizationDetails organizationDetails){
        id=organizationDetails.getId();
        name=organizationDetails.getName();
        phone=organizationDetails.getPhone();
        website=organizationDetails.getWebsite();
        email=organizationDetails.getEmail();
        address=organizationDetails.getAddress();
        city=organizationDetails.getCity();
        state=organizationDetails.getState();
        zipcode=organizationDetails.getZipcode();
        country=organizationDetails.getCountry();
        contact= organizationDetails.getContact()!=null?new ContactDetailsDO(organizationDetails.getContact()):null;
    }

    public OrganizationDetails getValue(){
        OrganizationDetails organizationDetails=new OrganizationDetails();
        organizationDetails.setId(id);
        organizationDetails.setName(name);
        organizationDetails.setPhone(phone);
        organizationDetails.setWebsite(website);
        organizationDetails.setEmail(email);
        organizationDetails.setAddress(address);
        organizationDetails.setCity(city);
        organizationDetails.setState(state);
        organizationDetails.setZipcode(zipcode);
        organizationDetails.setCountry(country);

        ContactDetails contactDetails=contact!=null?contact.getValue():null;
        organizationDetails.setContact(contactDetails);
        return organizationDetails;
    }
}
