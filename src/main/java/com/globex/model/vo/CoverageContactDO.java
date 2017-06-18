package com.globex.model.vo;

import com.globex.model.entity.pm.CoverageContact;
import com.globex.model.vo.pm.ContactInfoDO;
import com.globex.model.vo.pm.CoverageAreaDO;
import com.utils.StringUtils;
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
        coverageContact.setCoverageArea(StringUtils.getValue(coverageArea));
        coverageContact.setContactName(StringUtils.getValue(contactName));
        coverageContact.setContactPhone(StringUtils.getValue(contactPhone));
        coverageContact.setContactEmail(StringUtils.getValue(contactEmail));
        coverageContact.setContactPosition(StringUtils.getValue(contactPosition));
        return coverageContact;
    }

}
