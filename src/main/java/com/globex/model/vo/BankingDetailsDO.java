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

    private Long id;

    private String details;

    private String address;

    private String name;

    private String iBan;

    private String swiftCode;

    private ContactDetailsDO contact;

    public BankingDetailsDO(){}

    public BankingDetailsDO (BankingDetails bankingDetails){
        id=bankingDetails.getId();
        details=bankingDetails.getDetails();
        address=bankingDetails.getAddress();
        name=bankingDetails.getName();
        iBan=bankingDetails.getIBan();
        swiftCode=bankingDetails.getSwiftCode();
        contact=bankingDetails.getContact()!=null?new ContactDetailsDO(bankingDetails.getContact()):null;
    }

    public BankingDetails getValue(){
        BankingDetails bankingDetails=new BankingDetails();
        bankingDetails.setId(id);
        bankingDetails.setDetails(details);
        bankingDetails.setAddress(address);
        bankingDetails.setName(name);
        bankingDetails.setIBan(iBan);
        bankingDetails.setSwiftCode(swiftCode);
        bankingDetails.setContact(contact!=null?contact.getValue():null);
        return bankingDetails;
    }
}
