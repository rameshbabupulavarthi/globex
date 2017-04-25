package com.globex.model.vo;

import com.globex.model.entity.common.Country;
import lombok.Data;

/**
 * Created by Sunil Golla on 4/23/2017.
 */
@Data
public class CountryDO {

    private Long id;

    private String country;

    private Boolean nonAdmittedAllowed;

    private String nonAdmittedComments;

    private Boolean retailBrokerRequired;

    private String retailBrokerComments;

    private Boolean reInsuranceBrokerRequired;

    private String reInsuranceBrokerComments;

    private Boolean mandatoryReInsuranceCession;

    private String mandatoryReInsuranceComments;

    private Boolean stateSidePremiumAllowed;

    private String stateSidePremiumComments;

    private String otherAccRequirements;

    private String premiumReserve;

    private String taxes;

    private String vat;

    private String reInsuranceTax;

    private String otherRequirements;

    private String policyLanguage;

    private Boolean tacitRenewal;

    private String tacticalRenewalComments;

    private String generalComments;

    private Long createdBy;

    private Long updatedBy;

    public CountryDO(){

    }

    public CountryDO(Country country){

        id=country.getId();

        this.country=country.getCountry();

        nonAdmittedAllowed=country.getNonAdmittedAllowed();

        nonAdmittedComments=country.getNonAdmittedComments();

        retailBrokerRequired=country.getRetailBrokerRequired();

        retailBrokerComments=country.getRetailBrokerComments();

        reInsuranceBrokerRequired=country.getReInsuranceBrokerRequired();

        reInsuranceBrokerComments=country.getReInsuranceBrokerComments();

        mandatoryReInsuranceCession=country.getMandatoryReInsuranceCession();

        mandatoryReInsuranceComments=country.getMandatoryReInsuranceComments();

        stateSidePremiumAllowed=country.getStateSidePremiumAllowed();

        stateSidePremiumComments=country.getStateSidePremiumComments();

        otherAccRequirements=country.getOtherAccRequirements();

        premiumReserve=country.getPremiumReserve();

        taxes=country.getTaxes();

        vat=country.getVat();

        reInsuranceTax=country.getReInsuranceTax();

        otherRequirements=country.getOtherRequirements();

        policyLanguage=country.getPolicyLanguage();

        tacitRenewal=country.getTacitRenewal();

        tacticalRenewalComments=country.getTacticalRenewalComments();

        generalComments=country.getGeneralComments();

       createdBy=country.getCreatedBy();

       updatedBy=country.getUpdatedBy();

    }
}
