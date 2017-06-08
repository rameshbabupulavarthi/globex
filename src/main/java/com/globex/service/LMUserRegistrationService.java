package com.globex.service;

import com.globex.model.entity.partner.AccountDetails;
import com.globex.model.entity.partner.NetworkDetails;
import com.globex.model.vo.*;

/**
 * Created by Ramesh on 25-12-2016.
 */
public interface LMUserRegistrationService {

    public PartnerDO getPartnerDetails();

    public OrganizationDO saveOrgInfo(OrganizationDO organizationDO);

    public FinancialDetailsDO saveFinancialInfo(FinancialDetailsDO financialDetailsDO);

    public NetworkDetailsDO saveNetworkInfo(NetworkDetailsDO networkDetailsDO);

    public AccountDetailsDO saveAccountInfo(AccountDetailsDO accountDetailsDO);

    public BankingDetailsDO saveBankingInfo(BankingDetailsDO bankingDetailsDO);
}
