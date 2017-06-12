package com.globex.model.vo.lm;

import com.globex.model.entity.partner.OrgRateRequirement;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 6/4/2017.
 */
@Data
public class OrgRateRequirementDO implements Serializable {

    private Long requirementId;

    private String requirementName;

    private String requirementLob;

    private String requirementRate;

    private String appliedTo;

    private String reqMinPremium;

    private String reqCurrency;

    private String reqType;

    public OrgRateRequirementDO(){

    }

    public OrgRateRequirementDO(OrgRateRequirement orgRateRequirement){
        requirementId=orgRateRequirement.getRequirementId();
        requirementName=orgRateRequirement.getRequirementName();
        requirementLob=orgRateRequirement.getLob();
        requirementRate=orgRateRequirement.getRate();
        appliedTo =orgRateRequirement.getAppliedTo();
        reqMinPremium=orgRateRequirement.getMinPremium();
        reqCurrency=orgRateRequirement.getCurrency();
        reqType=orgRateRequirement.getReqType();
    }

    public OrgRateRequirement value(){
        OrgRateRequirement orgRateRequirement=new OrgRateRequirement();
        orgRateRequirement.setRequirementId(requirementId);
        orgRateRequirement.setRequirementName(requirementName);
        orgRateRequirement.setLob(requirementLob);
        orgRateRequirement.setRate(requirementRate);
        orgRateRequirement.setAppliedTo(appliedTo);
        orgRateRequirement.setMinPremium(reqMinPremium);
        orgRateRequirement.setCurrency(reqCurrency);
        orgRateRequirement.setReqType(reqType);
        return orgRateRequirement;
    }
}
