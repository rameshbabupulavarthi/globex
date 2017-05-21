package com.globex.model.vo.pm;

import com.globex.model.entity.common.Attachment;
import com.globex.model.entity.pm.RegisteredCountry;
import com.globex.model.vo.AttachmentDO;
import com.globex.model.vo.OrganizationDO;
import com.utils.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sunil Golla on 5/18/2017.
 */
@Data
public class RegisteredCountryDO implements Serializable {

    private Long registeredCountryId;

    private String countryName;

    private String registrationNumber;

    private String registrationDate;

    private Set<AttachmentDO> regAttachments;

    private OrganizationDO organization;


    public RegisteredCountryDO(){

    }

    public RegisteredCountryDO(RegisteredCountry registeredCountry){
        this.registeredCountryId=registeredCountry.getRegisteredCountryId();
        this.countryName=registeredCountry.getCountryName();
        this.registrationNumber=registeredCountry.getRegistrationNumber();
        this.registrationDate=DateUtil.formatDate(registeredCountry.getRegistrationDate());
        this.regAttachments=getAttachmentDOs(registeredCountry.getRegAttachments());

    }

    public RegisteredCountry value(){
        RegisteredCountry registeredCountry=new RegisteredCountry();
        registeredCountry.setRegisteredCountryId(this.getRegisteredCountryId());
        registeredCountry.setCountryName(this.getCountryName());
        registeredCountry.setRegistrationNumber(this.getRegistrationNumber());
        registeredCountry.setRegistrationDate(DateUtil.getTimestamp(this.getRegistrationDate()));
        registeredCountry.setRegAttachments(getAttachments(regAttachments));
        registeredCountry.setOrganization(organization!=null?organization.value():null);
        return registeredCountry;
    }

    public Set<Attachment> getAttachments(Set<AttachmentDO> attachmentDOs){
        if(attachmentDOs!=null && !attachmentDOs.isEmpty()){
            Set<Attachment> attachments=new HashSet<Attachment>();
            for(AttachmentDO attachmentDO:attachmentDOs){
                attachments.add(attachmentDO.value());
            }
            return attachments;
        }
        return null;
    }

    public Set<AttachmentDO> getAttachmentDOs(Set<Attachment> fileAttachments){
        if(fileAttachments!=null && !fileAttachments.isEmpty()){
            Set<AttachmentDO> fileAttachmentDOs=new HashSet<AttachmentDO>();
            for(Attachment fileAttachment:fileAttachments){
                fileAttachmentDOs.add(new AttachmentDO(fileAttachment));
            }
            return fileAttachmentDOs;
        }
        return null;
    }
}
