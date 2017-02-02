package com.globex.model.vo;

import com.globex.model.entity.partner.ContactDetails;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 12/31/2016.
 */
@Data
public class ContactDetailsDO implements Serializable {

    private Long id;

    private String fullName;

    private String firstName;

    private String lastName;

    private String role;

    private String officeName;

    private String phone;

    private String email;

    private String comments;

    public ContactDetailsDO(){

    }

    public ContactDetailsDO (ContactDetails contactDetails){
        id=contactDetails.getId();
        fullName=contactDetails.getFullName();
        firstName= contactDetails.getFirstName();
        lastName= contactDetails.getLastName();
        role=contactDetails.getRole();
        officeName= contactDetails.getOfficeName();
        phone=contactDetails.getPhone();
        email=contactDetails.getEmail();
        comments=contactDetails.getComments();
    }

    public ContactDetails getValue(){

        ContactDetails contactDetails=new ContactDetails();
        contactDetails.setId(id);
        contactDetails.setFullName(fullName);
        contactDetails.setFirstName(firstName);
        contactDetails.setLastName(lastName);
        contactDetails.setRole(role);
        contactDetails.setOfficeName(officeName);
        contactDetails.setPhone(phone);
        contactDetails.setEmail(email);
        contactDetails.setComments(comments);
        return contactDetails;
    }
}
