package com.globex.model.vo.common;

import com.globex.model.entity.common.Tax;
import com.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 5/27/2017.
 */
@Data
public class TaxDO implements Serializable {

    private Long taxId;

    private String taxType;

    private String taxLob;

    private String taxRate;

    private String taxAppliedTo;

    private String taxAmount;

    private String taxCurrency;

    private String taxResponsiblility;

    public TaxDO(){

    }

    public TaxDO(Tax tax){
        this.taxId=tax.getTaxId();
        this.taxType=tax.getTaxType();
        this.taxLob=tax.getLob();
        this.taxRate=tax.getPercent();
        this.taxAppliedTo=tax.getAppliedTo();
        this.taxAmount=tax.getAmount();
        this.taxCurrency=tax.getCurrency();
        this.taxResponsiblility=tax.getResponsiblility();
    }
    public Tax value(){
        Tax tax=new Tax();
        tax.setTaxId(taxId);
        tax.setTaxType(StringUtils.getValue(taxType));
        tax.setLob(StringUtils.getValue(taxLob));
        tax.setPercent(StringUtils.getValue(taxRate));
        tax.setAppliedTo(StringUtils.getValue(taxAppliedTo));
        tax.setAmount(StringUtils.getValue(taxAmount));
        tax.setCurrency(StringUtils.getValue(taxCurrency));
        tax.setResponsiblility(StringUtils.getValue(taxResponsiblility));
        return tax;
    }


}
