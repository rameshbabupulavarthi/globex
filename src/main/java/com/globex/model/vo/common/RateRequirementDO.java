package com.globex.model.vo.common;

import com.globex.model.entity.common.RateRequirement;
import com.utils.StringUtils;
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
        rateRequirement.setRequirementName(StringUtils.getValue(requirementName));
        rateRequirement.setLob(StringUtils.getValue(requirementLob));
        rateRequirement.setRate(StringUtils.getValue(requirementRate));
        rateRequirement.setAppliedTo(StringUtils.getValue(requirementAppliedTo));
        rateRequirement.setMinPremium(StringUtils.getValue(requirementMinPremium));
        rateRequirement.setCurrency(StringUtils.getValue(requirementCurrency));
        rateRequirement.setReqType(StringUtils.getValue(requirementType));
        return rateRequirement;
    }
}
