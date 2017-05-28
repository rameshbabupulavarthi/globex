package com.globex.model.vo.common;

import com.globex.model.entity.common.RateRequirement;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 5/27/2017.
 */
@Data
public class RateRequirementDO implements Serializable{

    private Long requirementId;

    private String requirementName;

    private String requirementLob;

    private String requirementRate;

    private String requirementAppliedTo;

    private String requirementMinPremium;

    private String requirementCurrency;

    private String requirementType;

    public RateRequirementDO(){

    }

    public RateRequirementDO(RateRequirement rateRequirement){

        this.requirementId=rateRequirement.getRequirementId();
        this.requirementName=rateRequirement.getRequirementName();
        this.requirementLob=rateRequirement.getLob();
        this.requirementRate =rateRequirement.getRate();
        this.requirementAppliedTo=rateRequirement.getAppliedTo();
        this.requirementMinPremium=rateRequirement.getMinPremium();
        this.requirementCurrency=rateRequirement.getCurrency();
        this.requirementType=rateRequirement.getReqType();
    }

    public RateRequirement value(){
        RateRequirement rateRequirement=new RateRequirement();
        rateRequirement.setRequirementId(this.getRequirementId());
        rateRequirement.setRequirementName(requirementName);
        rateRequirement.setLob(requirementLob);
        rateRequirement.setRate(requirementRate);
        rateRequirement.setAppliedTo(requirementAppliedTo);
        rateRequirement.setMinPremium(requirementMinPremium);
        rateRequirement.setCurrency(requirementCurrency);
        rateRequirement.setReqType(requirementType);
        return rateRequirement;
    }
}
