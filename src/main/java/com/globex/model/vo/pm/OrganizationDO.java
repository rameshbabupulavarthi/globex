package com.globex.model.vo.pm;

import com.globex.model.entity.pm.Organization;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sunil Golla on 2/10/2017.
 */
@Data
public class OrganizationDO {

    private Integer id;

    private String orgName;

    private Timestamp regDate;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String country;

    private String zip;

    private String website;

    private Integer orgType;

    private Integer parentOrgId;

    private Integer approved;

    private String comment;

    private String licenceState;


    public OrganizationDO(){


    }


    public OrganizationDO(Organization organization){
        this.id=organization.getId();
        this.orgName=organization.getOrgName();
        this.regDate=organization.getRegDate();
        this.address1=organization.getAddress1();
        this.address2=organization.getAddress2();
        this.city=organization.getCity();
        this.state=organization.getState();
        this.country=organization.getCountry();
        this.zip=organization.getZip();
        this.website=organization.getWebsite();
        this.orgType=organization.getOrgType();
        this.parentOrgId=organization.getParentOrgId();
        this.approved=organization.getApproved();
        this.comment=organization.getComment();
        this.licenceState=organization.getLicenceState();
    }

}
