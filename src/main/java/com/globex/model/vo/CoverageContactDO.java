package com.globex.model.vo;

import com.globex.model.entity.pm.CoverageContact;
import com.globex.model.vo.pm.ContactInfoDO;
import com.globex.model.vo.pm.CoverageAreaDO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 5/20/2017.
 */
@Data
public class CoverageContactDO implements Serializable {

    private Long coverageContactId;
    private String coverageArea;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private String contactPosition;

    public CoverageContactDO(){

    }

    public CoverageContactDO(CoverageContact coverageContact){
        this.coverageContactId=coverageContact.getCoverageContactId();
        this.coverageArea=coverageContact.getCoverageArea();
        this.contactName=coverageContact.getContactName();
        this.contactPhone=coverageContact.getContactPhone();
        this.contactEmail=coverageContact.getContactEmail();
        this.contactPosition=coverageContact.getContactPosition();
    }

    public CoverageContact value(){
        CoverageContact coverageContact=new CoverageContact();
        coverageContact.setCoverageContactId(coverageContactId);
        coverageContact.setCoverageArea(coverageArea);
        coverageContact.setContactName(contactName);
        coverageContact.setContactPhone(contactPhone);
        coverageContact.setContactEmail(contactEmail);
        coverageContact.setContactPosition(contactPosition);
        return coverageContact;
    }

}
