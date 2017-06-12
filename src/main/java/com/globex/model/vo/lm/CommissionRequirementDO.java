package com.globex.model.vo.lm;

import com.globex.model.entity.partner.CommissionRequirement;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 6/5/2017.
 */
@Data
public class CommissionRequirementDO implements Serializable {

    private Long commReqId;

    private String commissionLob;

    private Double commissionRate;

    private String commissionAppliedTo;

    private Double commissionFlatAmount;

    private String commissionCurrency;

    public CommissionRequirementDO(){

    }

    public CommissionRequirementDO(CommissionRequirement commissionRequirement){
        commReqId=commissionRequirement.getCommReqId();
        commissionLob=commissionRequirement.getCommissionLob();
        commissionRate=commissionRequirement.getCommissionRate();
        commissionAppliedTo=commissionRequirement.getCommissionAppliedTo();
        commissionFlatAmount=commissionRequirement.getCommissionFlatAmount();
        commissionCurrency=commissionRequirement.getCommissionCurrency();
    }

    public CommissionRequirement value(){
        CommissionRequirement commissionRequirement=new CommissionRequirement();
        commissionRequirement.setCommReqId(commReqId);
        commissionRequirement.setCommissionLob(commissionLob);
        commissionRequirement.setCommissionRate(commissionRate);
        commissionRequirement.setCommissionAppliedTo(commissionAppliedTo);
        commissionRequirement.setCommissionFlatAmount(commissionFlatAmount);
        commissionRequirement.setCommissionCurrency(commissionCurrency);
        return commissionRequirement;
    }
}
