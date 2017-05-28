package com.globex.model.vo.common;

import com.globex.model.entity.common.Tax;
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
        tax.setTaxType(taxType);
        tax.setLob(taxLob);
        tax.setPercent(taxRate);
        tax.setAppliedTo(taxAppliedTo);
        tax.setAmount(taxAmount);
        tax.setCurrency(taxCurrency);
        tax.setResponsiblility(taxResponsiblility);
        return tax;
    }


}
