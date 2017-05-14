package com.globex.model.vo.pm;

import com.globex.model.entity.pm.CoverageArea;
import com.globex.model.vo.OrganizationDO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 5/13/2017.
 */
@Data
public class CoverageAreaDO implements Serializable {

    private Integer coverageAreaId;

    private OrganizationDO organization;

    private boolean marine;

    private boolean property;

    private boolean healthAndBenefits;

    private boolean liabilities;

    private boolean directorsAndOfficers;

    private boolean aviation;

    public CoverageAreaDO(){

    }

    public CoverageAreaDO(CoverageArea coverageArea){

        this.coverageAreaId=coverageArea.getCoverageAreaId();
        this.organization=new OrganizationDO(coverageArea.getOrganization());
        this.marine=coverageArea.isMarine();
        this.property=coverageArea.isProperty();
        this.healthAndBenefits=coverageArea.isHealthAndBenefits();
        this.liabilities=coverageArea.isLiabilities();
        this.directorsAndOfficers=coverageArea.isDirectorsAndOfficers();
        this.aviation=coverageArea.isAviation();
    }

    public CoverageArea value(){

        CoverageArea coverageArea=new CoverageArea();
        coverageArea.setCoverageAreaId(coverageAreaId);
        coverageArea.setOrganization(organization!=null?organization.value():null);
        coverageArea.setMarine(marine);
        coverageArea.setProperty(property);
        coverageArea.setHealthAndBenefits(healthAndBenefits);
        coverageArea.setLiabilities(liabilities);
        coverageArea.setDirectorsAndOfficers(directorsAndOfficers);
        coverageArea.setAviation(aviation);
        return coverageArea;
    }
}
