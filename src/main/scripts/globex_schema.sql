CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `external_id` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username_idx` (`username`),
  KEY `email_idx` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;



CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `default_role` tinyint(1) DEFAULT '1',
  `permission` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK331DEE5F2E968434` (`user_id`),
  CONSTRAINT `t_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=776104 DEFAULT CHARSET=latin1 ;


CREATE TABLE `t_contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fullName` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `officeName` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;


CREATE TABLE `t_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `website` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;


CREATE TABLE `t_banking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `details` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `iban` varchar(255) NOT NULL,
  `swift_code` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;


CREATE TABLE `t_network` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qualifier` varchar(255) NOT NULL,
  `qualifier_lob` varchar(255) NOT NULL,
  `comments` varchar(255) NOT NULL,
  `score` varchar(255) NOT NULL,
  `avg_response_time` varchar(255) DEFAULT NULL,
  `contact_comments` varchar(255) DEFAULT NULL,
  `vetting_status` varchar(255) DEFAULT NULL,
  `vetting_status_comments` varchar(255) DEFAULT NULL,
  `vetting_date` datetime DEFAULT NULL,
  `vetting_reminder` varchar(255) DEFAULT NULL,
  `vetting_reminder_comments` varchar(255) DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;


CREATE TABLE `t_partner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  `bank_id` bigint(20) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `contact_id` bigint(20) DEFAULT NULL,
  `finance_id` bigint(20) DEFAULT NULL,
  `network_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;


CREATE TABLE `t_account` (
    id  bigint(20) NOT NULL AUTO_INCREMENT,
    countryList varchar(255) DEFAULT NULL,
    territoryComment varchar(255) DEFAULT NULL,
    selectedCountry varchar(255) DEFAULT NULL,
    authLobLimit varchar(255) DEFAULT NULL,
    minimumPremium varchar(255) DEFAULT NULL,
    authLobCurrency varchar(255) DEFAULT NULL,
    authLobpPremiumReverse varchar(255) DEFAULT NULL,
    comEstablishmentYear varchar(255) DEFAULT NULL,
    authLobeExactTaxes  varchar(255) DEFAULT NULL,
    authLobExactTaxes1Text1 varchar(255) DEFAULT NULL,
    authLobExactTaxes1Text2 varchar(255) DEFAULT NULL,
    taxResponsible varchar(255) DEFAULT NULL,
    flatPercentage varchar(255) DEFAULT NULL,
    taxApplied varchar(255) DEFAULT NULL,
    lobTariffComment varchar(255) DEFAULT NULL,
    lobTariffCommentAttach varchar(255) DEFAULT NULL,
    reInsurancecCommission varchar(255) DEFAULT NULL,
    lobMandatoryClauses  varchar(255) DEFAULT NULL,
    lobMandatoryClausesAttach varchar(255) DEFAULT NULL,
    locCurrRefLocPolicy varchar(255) DEFAULT NULL,
    locCurrRefLocPolicyText varchar(255) DEFAULT NULL,
    forJuriAvalLocPolicy varchar(255) DEFAULT NULL,
    forJuriAvalLocPolicyText varchar(255) DEFAULT NULL,
    manuscriptAval  varchar(255) DEFAULT NULL,
    manuscriptAvalText varchar(255) DEFAULT NULL,
    forReinsurSupport  varchar(255) DEFAULT NULL,
    forReinsurSupportText varchar(255) DEFAULT NULL,
    insuRequiredDoc varchar(255) DEFAULT NULL,
    insuRequiredDocAttach varchar(255) DEFAULT NULL,
    taxId  varchar(255) DEFAULT NULL,
    taxIdText varchar(255) DEFAULT NULL,
    serviceOption varchar(255) DEFAULT NULL,
    serviceOptionText varchar(255) DEFAULT NULL,
    reinsurBroker varchar(255) DEFAULT NULL,
    reinsurBrokerText varchar(255) DEFAULT NULL,
    regRegulator  varchar(255) DEFAULT NULL,
    regRegulatorText varchar(255) DEFAULT NULL,
    registrationProcedure varchar(255) DEFAULT NULL,
    registrationProcedureAttach varchar(255) DEFAULT NULL,
    requiredDocReinsurPlace varchar(255) DEFAULT NULL,
    requiredDocReinsurPlaceAttach varchar(255) DEFAULT NULL,
    specReqDocReinsurPlace varchar(255) DEFAULT NULL,
    specReqDocReinsurPlaceAttach varchar(255) DEFAULT NULL,
    compInvolClaims  varchar(255) DEFAULT NULL,
    compInvolClaimsText varchar(255) DEFAULT NULL,
    claimPayMasterLocal  varchar(255) DEFAULT NULL,
    claimPayMasterLocalText varchar(255) DEFAULT NULL,
    claimHandlingWordingAttach varchar(255) DEFAULT NULL,
    premiumPayOption varchar(255) DEFAULT NULL,
    premiumWithTax varchar(255) DEFAULT NULL,
    nonAdmittedPolicy  varchar(255) DEFAULT NULL,
    nonAdmittedPolicyText varchar(255) DEFAULT NULL,
    nonAdmittedComments varchar(255) DEFAULT NULL,
    mandatoryReinsurCession varchar(255) DEFAULT NULL,
    mandatoryReinsurCessionText varchar(255) DEFAULT NULL,
    mandatoryReinsurComments varchar(255) DEFAULT NULL,
    policyLang  varchar(255) DEFAULT NULL,
    tacitRenewal varchar(255) DEFAULT NULL,
    tacitRenewalText varchar(255) DEFAULT NULL,
    tacitRenewalComments  varchar(255) DEFAULT NULL,
    network  varchar(255) DEFAULT NULL,
    cashBeforeCoverReq  varchar(255) DEFAULT NULL,
    cashBeforeCoverReqText varchar(255) DEFAULT NULL,
    premiumPaymentWarranty  varchar(255) DEFAULT NULL,
    premiumPaymentWarrantyText varchar(255) DEFAULT NULL,
    premiumPaymentWarrantyDays varchar(255) DEFAULT NULL,
    foreignCurrencyAccept  varchar(255) DEFAULT NULL,
    foreignCurrencyAcceptText varchar(255) DEFAULT NULL,
    acceptableCurrency varchar(255) DEFAULT NULL,
    premiumRemittanceTime varchar(255) DEFAULT NULL,
    backDatingPossible varchar(255) DEFAULT NULL,
    backDatingPossibleText varchar(255) DEFAULT NULL,
    localCurrencyRequirement varchar(255) DEFAULT NULL,
    localCurrencyRequirementText varchar(255) DEFAULT NULL,
    stateReinsurInvolv varchar(255) DEFAULT NULL,
    stateReinsurInvolvText varchar(255) DEFAULT NULL,
    accounting varchar(255) DEFAULT NULL,
    generalComments varchar(255) DEFAULT NULL,
    generalAttachment varchar(255) DEFAULT NULL,
    policyFormsWordingAttach varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;


create table `t_finance`(
    id  					 bigint(20) NOT NULL AUTO_INCREMENT,
    companyType              varchar(255) DEFAULT NULL,
    parentCompany            varchar(255) DEFAULT NULL,
    licenseNo                varchar(255) DEFAULT NULL,
    licenseAuthority         varchar(255) DEFAULT NULL,
    licenseAuthWebsite       varchar(255) DEFAULT NULL,
    companyEstablishmentYear varchar(255) DEFAULT NULL,
    amBestRating             varchar(255) DEFAULT NULL,
    amBestLook               varchar(255) DEFAULT NULL,
    amBestRatingDate         varchar(255) DEFAULT NULL,
    amRatingAttachment       varchar(255) DEFAULT NULL,
    otherBrokers             varchar(255) DEFAULT NULL,
    sAndPRating              varchar(255) DEFAULT NULL,
    sAndPOutlook             varchar(255) DEFAULT NULL,
    sAndPrRatingOutlookDate  varchar(255) DEFAULT NULL,
    sAndPAttachment          varchar(255) DEFAULT NULL,
    miscCompName             varchar(255) DEFAULT NULL,
    miscCompWebsite          varchar(255) DEFAULT NULL,
    miscCompCountry          varchar(255) DEFAULT NULL,
    miscCompRating           varchar(255) DEFAULT NULL,
    miscCompOutlook          varchar(255) DEFAULT NULL,
    ratedByOtherAgency       varchar(255) DEFAULT NULL,
    miscCompAttachment       varchar(255) DEFAULT NULL,
    businessToWrite          varchar(255) DEFAULT NULL,
    businessNotToWrite       varchar(255) DEFAULT NULL,
    lobComments              varchar(255) DEFAULT NULL,
    countryList              varchar(255) DEFAULT NULL,
    selectedCountry          varchar(255) DEFAULT NULL,
    territoryComment         varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;


create table `t_message`(
    id  bigint(20) NOT NULL AUTO_INCREMENT,
    sender_id bigint(20) DEFAULT NULL,
    receiver_ids varchar(255) DEFAULT NULL,
    message text DEFAULT NULL,
    subject varchar(255) DEFAULT NULL,
    thread_id bigint(20) DEFAULT NULL,
    created_date datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;


insert into `t_user` values(1, 'ramesh@globex.com', 'ramesh', 'babu','9824ade57d45e6024e4faa629fc9fcee11b1bd5bfd9778ef6cc486d527294e037a6303ce8bfee35c', '', 'ramesh@globex.com', 'ramesh', '',NULL , '2012-09-27 20:28:41', '2012-09-27 20:28:41');
insert into `t_user_role` values(1, '2012-09-27 20:28:41', 0, '2012-09-27 20:28:41', 0, 0, 'ROLE_SUPER_ADMIN', 1, 1, 1);

insert into `t_user` values(2, 'admin@globex.com', 'admin', 'admin','9824ade57d45e6024e4faa629fc9fcee11b1bd5bfd9778ef6cc486d527294e037a6303ce8bfee35c', '', 'admin@globex.com', 'admin', '',NULL , '2012-09-27 20:28:41', '2012-09-27 20:28:41');
insert into `t_user_role` values(2, '2012-09-27 20:28:41', 0, '2012-09-27 20:28:41', 0, 0, 'ROLE_SUPER_ADMIN', 1, 1, 2);

insert into `t_user` values(3, 'admin@globex.com', 'admin', 'admin','9824ade57d45e6024e4faa629fc9fcee11b1bd5bfd9778ef6cc486d527294e037a6303ce8bfee35c', '', 'admin@globex.com', 'admin', '',NULL , '2012-09-27 20:28:41', '2012-09-27 20:28:41');
insert into `t_user_role` values(3, '2012-09-27 20:28:41', 0, '2012-09-27 20:28:41', 0, 0, 'ROLE_SUPER_ADMIN', 1, 1, 3);