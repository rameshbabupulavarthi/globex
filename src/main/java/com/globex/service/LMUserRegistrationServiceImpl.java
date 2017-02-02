package com.globex.service;

import com.globex.model.entity.partner.*;
import com.globex.model.entity.user.User;
import com.globex.model.vo.*;
import com.globex.repository.rdbms.lm.*;
import com.globex.security.CurrentUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ramesh on 25-12-2016.
 */
@Service
public class LMUserRegistrationServiceImpl implements LMUserRegistrationService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FinancialDetailsRepository financialDetailsRepository;

    @Autowired
    BankDetailsRepository bankDetailsRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    NetworkDetailsRepository networkDetailsRepository;

    @Autowired
    OrganizationDetailsRepository organizationDetailsRepository;

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    UserService userService;


    public PartnerDO getPartnerDetails(){
        CurrentUserDO userDO=userService.getCurrentUserDO();
        Partner partner=partnerRepository.findByUserId(userDO.getUserId());
        PartnerDO partnerDO=partner!=null?new PartnerDO(partner):null;
        return partnerDO;
    }


    public Partner savePartnerInfo(Partner partner){
        return partnerRepository.save(partner);
    }

    public Partner getPartnerInfo(){
       Long userId=userService.getCurrentUserDO().getUserId();
        User user=userService.getCurrentUser();
       Partner partner= partnerRepository.findByUserId(userId);
        if(partner==null){
            partner=new Partner();
            partner.setUser(user);
        }
       return partner;
    }

    public OrganizationDetailsDO saveOrgInfo(OrganizationDetailsDO organizationDetailsDO){

        Partner partner=getPartnerInfo();
        OrganizationDetails organizationDetails=organizationDetailsDO.getValue();
        if(partner.getOrganizationDetails()!=null){
            organizationDetails.setId(partner.getOrganizationDetails().getId());
        }
        ContactDetails contactDetails=organizationDetails.getContact();
        contactDetails=contactRepository.save(contactDetails);
        organizationDetails.setContact(contactDetails);
        organizationDetails=organizationDetailsRepository.save(organizationDetails);
        partner.setOrganizationDetails(organizationDetails);
        savePartnerInfo(partner);
        return new OrganizationDetailsDO(organizationDetails);
    }

    public void saveContactInfo(ContactDetailsDO contactDetailsDO){
        ContactDetails contactDetails=contactDetailsDO.getValue();
        contactDetails=contactRepository.save(contactDetails);

        Partner partner=getPartnerInfo();
        partner.setContactDetails(contactDetails);
        savePartnerInfo(partner);
    }


    public FinancialDetailsDO saveFinancialInfo(FinancialDetailsDO financialDetailsDO){
        FinancialDetails financialDetails=financialDetailsDO.getValue();
        financialDetails=financialDetailsRepository.save(financialDetails);

        Partner partner=getPartnerInfo();
        partner.setFinancialDetails(financialDetails);
        savePartnerInfo(partner);
        return new FinancialDetailsDO(financialDetails);
    }

    public NetworkDetailsDO saveNetworkInfo(NetworkDetailsDO networkDetailsDO){
        NetworkDetails networkDetails=networkDetailsDO.getValue();
        networkDetails=networkDetailsRepository.save(networkDetails);

        Partner partner=getPartnerInfo();
        partner.setNetworkDetails(networkDetails);
        savePartnerInfo(partner);
        return new NetworkDetailsDO(networkDetails) ;
    }

    public AccountDetailsDO saveAccountInfo(AccountDetailsDO accountDetailsDO){
        AccountDetails accountDetails=accountDetailsDO.getValue();
        accountDetails=accountRepository.save(accountDetails);

        Partner partner=getPartnerInfo();
        partner.setAccountDetails(accountDetails);
        savePartnerInfo(partner);
        return new AccountDetailsDO(accountDetails);
    }

    public BankingDetailsDO saveBankingInfo(BankingDetailsDO bankingDetailsDO){
        BankingDetails bankingDetails=bankingDetailsDO.getValue();

        ContactDetails contactDetails=bankingDetails.getContact();
        contactDetails=contactRepository.save(contactDetails);
        bankingDetails.setContact(contactDetails);

        bankingDetails=bankDetailsRepository.save(bankingDetails);

        Partner partner=getPartnerInfo();
        partner.setBankingDetails(bankingDetails);
        savePartnerInfo(partner);
        return new BankingDetailsDO(bankingDetails);
    }
}