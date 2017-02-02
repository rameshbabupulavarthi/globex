package com.globex.model.vo;

import com.globex.model.entity.partner.Partner;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Sunil Golla on 12/31/2016.
 */
@Data
public class PartnerDO implements Serializable {

    private Long id;

    private OrganizationDetailsDO organization;

    private ContactDetailsDO contact;

    private BankingDetailsDO banking;

    private NetworkDetailsDO network;

    private FinancialDetailsDO finance;

    private AccountDetailsDO account;

    public PartnerDO(){}

    public PartnerDO(Partner partner){
        this.id=partner.getId();
        this.organization=partner.getOrganizationDetails()!=null?new OrganizationDetailsDO(partner.getOrganizationDetails()):null;
        this.contact=partner.getContactDetails()!=null?new ContactDetailsDO(partner.getContactDetails()):null;
        this.banking=partner.getBankingDetails()!=null?new BankingDetailsDO(partner.getBankingDetails()):null;
        this.network=partner.getNetworkDetails()!=null?new NetworkDetailsDO(partner.getNetworkDetails()):null;
        this.finance=partner.getFinancialDetails()!=null?new FinancialDetailsDO(partner.getFinancialDetails()):null;
        this.account=partner.getAccountDetails()!=null?new AccountDetailsDO(partner.getAccountDetails()):null;
    }
}
