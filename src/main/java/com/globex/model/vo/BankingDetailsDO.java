package com.globex.model.vo;

import com.globex.model.entity.partner.BankingDetails;
import com.globex.model.entity.partner.ContactDetails;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 12/31/2016.
 */
@Data
public class BankingDetailsDO implements Serializable {

    private Long bankingId;

    private String details;

    private String address;

    private String name;

    private String iban;

    private String swiftCode;

    private String email;

   /* private ContactDetailsDO contact;*/

    public BankingDetailsDO(){}

    public BankingDetailsDO (BankingDetails bankingDetails){
        bankingId=bankingDetails.getId();
        details=bankingDetails.getDetails();
        address=bankingDetails.getAddress();
        name=bankingDetails.getName();
        iban=bankingDetails.getIBan();
        swiftCode=bankingDetails.getSwiftCode();
        email=bankingDetails.getEmail();
        //contact=bankingDetails.getContact()!=null?new ContactDetailsDO(bankingDetails.getContact()):null;
    }

    public BankingDetails value(){
        BankingDetails bankingDetails=new BankingDetails();
        bankingDetails.setId(bankingId);
        bankingDetails.setDetails(details);
        bankingDetails.setAddress(address);
        bankingDetails.setName(name);
        bankingDetails.setIBan(iban);
        bankingDetails.setSwiftCode(swiftCode);
        bankingDetails.setEmail(email);
        //bankingDetails.setContact(contact!=null?contact.getValue():null);
        return bankingDetails;
    }
}
