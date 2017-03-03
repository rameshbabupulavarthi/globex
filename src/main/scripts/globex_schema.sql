
CREATE DATABASE IF NOT EXISTS globex;

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






   /*

   */

   CREATE TABLE `organization` (
     `ORGANIZATION_ID` int(11) NOT NULL AUTO_INCREMENT,
     `ORGANIZATION_NAME` varchar(100) NOT NULL,
     `REGISTRATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     `ADDRESS_LINE_1` varchar(100) NOT NULL,
     `ADDRESS_LINE_2` varchar(100) DEFAULT NULL,
     `CITY` varchar(30) NOT NULL,
     `STATE` varchar(30) NOT NULL,
     `COUNTRY` varchar(50) NOT NULL,
     `ZIP` varchar(15) NOT NULL,
     `WEB_SITE` varchar(50) DEFAULT NULL,
     `ORGANIZATION_TYPE` int(1) NOT NULL,
     `PARENT_ORGANIZATION_ID` int(11) DEFAULT NULL,
     `APPROVED` tinyint(1) NOT NULL DEFAULT '0',
     `COMMENT` varchar(255) DEFAULT NULL,
     `LICENSED_STATE` varchar(150) DEFAULT NULL,
     PRIMARY KEY (`ORGANIZATION_ID`)
   ) ENGINE=InnoDB AUTO_INCREMENT=690 DEFAULT CHARSET=latin1;

   CREATE TABLE `file` (
     `FILE_ID` int(11) NOT NULL AUTO_INCREMENT,
     `PARTNER_MARKET_ID` int(11) NOT NULL,
     `FORWARD_TO` int(11) DEFAULT NULL,
     `FILE_STATUS` int(11) NOT NULL DEFAULT '1',
     `PROSPECT` int(11) DEFAULT NULL,
     `CREATED_BY` int(11) DEFAULT NULL,
     `DATE_CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     `UPDATED_BY` int(11) DEFAULT NULL,
     `DATE_UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
     PRIMARY KEY (`FILE_ID`),
     KEY `FILE_PARTNER_MARKET_ID_FK` (`PARTNER_MARKET_ID`),
     KEY `FILE_FORWARD_TO_FK` (`FORWARD_TO`),
     KEY `FILE_CREATED_BY_FK` (`CREATED_BY`),
     KEY `FILE_UPDATED_BY_FK` (`UPDATED_BY`),
     CONSTRAINT `FILE_CREATED_BY_FK` FOREIGN KEY (`CREATED_BY`) REFERENCES `user` (`USER_ID`),
     CONSTRAINT `FILE_FORWARD_TO_FK` FOREIGN KEY (`FORWARD_TO`) REFERENCES `user` (`USER_ID`),
     CONSTRAINT `FILE_PARTNER_MARKET_ID_FK` FOREIGN KEY (`PARTNER_MARKET_ID`) REFERENCES `organization` (`ORGANIZATION_ID`),
     CONSTRAINT `FILE_UPDATED_BY_FK` FOREIGN KEY (`UPDATED_BY`) REFERENCES `user` (`USER_ID`)
   ) ENGINE=InnoDB AUTO_INCREMENT=1817 DEFAULT CHARSET=latin1;

   CREATE TABLE `application` (
     `APPLICATION_ID` int(11) NOT NULL AUTO_INCREMENT,
     `FILE_ID` int(11) NOT NULL,
     `APPLICATION_NO` varchar(20) DEFAULT NULL,
     `POLICY_START_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     `POLICY_END_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
     `INSURED_COMPANY` varchar(100) NOT NULL,
     `REINSURING_COMPANY` varchar(100) NOT NULL,
     `COVERAGES` text,
     `CURRENCY` int(11) DEFAULT NULL,
     `OTHER_CURRENCY` varchar(10) DEFAULT NULL,
     `INTEREST` text,
     `PERILS` text,
     `TOTAL_WORLD_WIDE_VALUE` double(18,2) DEFAULT '0.00',
     `TOTAL_USA_VALUE` double(18,2) DEFAULT '0.00',
     `TERMS_AND_CONDITIONS` text,
     `NOTICE_OF_CANCELLATION` text,
     `LIMITS_OF_LIABILITY` text,
     `DEDUCTIBLES` text,
     `VALUATION` text,
     `loss_history` text,
     `CLAIMS_HANDLING` text,
     `REINSURANCE` double(7,4) DEFAULT NULL,
     `EXT_ACC_OR_COMPT` text,
     `COMMENT` text,
     `OTHER_SERVICES` text,
     `COLLECTION_TYPE` int(1) NOT NULL,
     `MASTER_POLICY_NO` varchar(20) DEFAULT NULL,
     `UNDER_WRITER_NAME` varchar(30) DEFAULT NULL,
     `PHONE_COUNTRY_CODE` int(3) DEFAULT NULL,
     `PHONE_AREA_CODE` int(5) DEFAULT NULL,
     `PHONE` bigint(20) DEFAULT NULL,
     `EMAIL` varchar(50) DEFAULT NULL,
     `BRANCH_OFFICE` varchar(150) DEFAULT NULL,
     `APPLICATION_STATUS` int(1) NOT NULL,
     `DATE_EMAILED_LOCAL_MARKETS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
     `GL_OCCURENCE` tinyint(1) NOT NULL DEFAULT '0',
     `GL_OCCURANCE_LIMIT` text,
     `GL_AGGREGATE_LIMIT` text,
     `GL_DEDUCTIBLE` text,
     `PL_OCCURENCE` tinyint(1) NOT NULL DEFAULT '0',
     `PL_OCCURANCE_LIMIT` text,
     `PL_AGGREGATE_LIMIT` text,
     `PL_DEDUCTIBLE` text,
     `EL_OCCURENCE` tinyint(1) NOT NULL DEFAULT '0',
     `EL_PAYROLL` text,
     `EL_NO_OF_EMPLOYEES` text,
     `EL_LIMITS` text,
     `EL_DEDUCTIBLES` text,
     `EL_TARGET_RATE` text,
     `EL_LOSS_HISTORY` text,
     `ANNUAL_GROSS_INCOME` text,
     `ESTIMATED_EXTRA_EXPENSE` text,
     `TOT_VAL_SHPNG_IN_TWELVE_MTHS` text,
     `AVG_SHPNG_VALUE` text,
     `NO_OF_ANNUAL_SHPNGS` text,
     `MAX_VALUE_PER_SHPMNT` text,
     `MAX_VALUE_PER_EXBTN` text,
     `PROPERTY_ON_EXBTN` text,
     `NO_OF_EXBTNS` text,
     `PERILS_INSURED_AGNST` text,
     `LIMITS` text,
     `COMM_TARGET_RATE` text,
     `COMM_DEDUCTIBLES` text,
     `COMM_VALUATION` text,
     `COMM_LOSS_HISTRY` text,
     `COMM_TERMS_CONDITIONS` text,
     `LINE_OF_COVERAGE` text,
     `GL_CLAIMS_MADE` tinyint(1) NOT NULL DEFAULT '0',
     `PL_CLAIMS_MADE` tinyint(1) NOT NULL DEFAULT '0',
     `EL_CLAIMS_MADE` tinyint(1) NOT NULL DEFAULT '0',
     `DAYS_SPENT` text,
     `ACC_DEATH_DISMEM` text,
     `LOC_AGG_LIMIT` text,
     `ADD_BENEFIT` text,
     `ADD_EXPO_INFO` text,
     `ASS_PROVIDER` text,
     `FOREIGN_LOSS_HIST` text,
     `ALL_REINSURANCE` text,
     `OTHER_ALL_REINSURANCE` text,
     PRIMARY KEY (`APPLICATION_ID`),
     KEY `APPLICATION_FILE_ID_FK` (`FILE_ID`),
     CONSTRAINT `APPLICATION_FILE_ID_FK` FOREIGN KEY (`FILE_ID`) REFERENCES `file` (`FILE_ID`)
   ) ENGINE=InnoDB AUTO_INCREMENT=1721 DEFAULT CHARSET=latin1;

   CREATE TABLE `assigned_local_market` (
     `ASSIGNED_LOCAL_MARKET_ID` int(11) NOT NULL AUTO_INCREMENT,
     `APPLICATION_ID` int(11) NOT NULL,
     `LOCAL_MARKET_ID` int(11) DEFAULT NULL,
     `COUNTRY` varchar(50) NOT NULL,
     PRIMARY KEY (`ASSIGNED_LOCAL_MARKET_ID`),
     KEY `ASSIGNED_LOCAL_MARKET_APPLICATION_ID` (`APPLICATION_ID`),
     KEY `ASSIGNED_LOCAL_MARKET_LM_ID` (`LOCAL_MARKET_ID`),
     CONSTRAINT `ASSIGNED_LOCAL_MARKET_APPLICATION_ID` FOREIGN KEY (`APPLICATION_ID`) REFERENCES `application` (`APPLICATION_ID`),
     CONSTRAINT `ASSIGNED_LOCAL_MARKET_LM_ID` FOREIGN KEY (`LOCAL_MARKET_ID`) REFERENCES `organization` (`ORGANIZATION_ID`)
   ) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=latin1;

   CREATE TABLE `communication` (
     `COMMUNICATION_ID` int(11) NOT NULL AUTO_INCREMENT,
     `FILE_ID` int(11) NOT NULL,
     `FROM_ORGANIZATION_ID` int(11) NOT NULL,
     `FROM_USER_ID` int(11) NOT NULL,
     `TO_ORGANIZATION_ID` int(11) NOT NULL,
     `DATE_CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     `SUBJECT` varchar(255) DEFAULT NULL,
     `CONTENT` text,
     `FILE_ATTACHMENT` text,
     `SHOW_TO_GLOBEX` tinyint(1) NOT NULL DEFAULT '0',
     `SHOW_TO_PM` tinyint(1) NOT NULL DEFAULT '0',
     `SHOW_TO_LM` tinyint(1) NOT NULL DEFAULT '0',
     `REPLY_EXPECTED_BY` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
     `REPLY_SENT` tinyint(1) NOT NULL DEFAULT '0',
     `COUNTRY` varchar(50) DEFAULT NULL,
     PRIMARY KEY (`COMMUNICATION_ID`),
     KEY `COMMUNICATION_FILE_ID_FK` (`FILE_ID`),
     KEY `COMMUNICATION_FROM_ORGAN_ID_FK` (`FROM_ORGANIZATION_ID`),
     KEY `COMMUNICATION_FROM_USER_ID_FK` (`FROM_USER_ID`),
     KEY `COMMUNICATION_TO_ORGANI_ID_FK` (`TO_ORGANIZATION_ID`) /*,
     CONSTRAINT `COMMUNICATION_FILE_ID_FK` FOREIGN KEY (`FILE_ID`) REFERENCES `file` (`FILE_ID`),
     CONSTRAINT `COMMUNICATION_FROM_ORGAN_ID_FK` FOREIGN KEY (`FROM_ORGANIZATION_ID`) REFERENCES `organization` (`ORGANIZATION_ID`),
     CONSTRAINT `COMMUNICATION_FROM_USER_ID_FK` FOREIGN KEY (`FROM_USER_ID`) REFERENCES `user` (`USER_ID`),
     CONSTRAINT `COMMUNICATION_TO_ORGANI_ID_FK` FOREIGN KEY (`TO_ORGANIZATION_ID`) REFERENCES `organization` (`ORGANIZATION_ID`)*/
   ) ENGINE=InnoDB AUTO_INCREMENT=12558 DEFAULT CHARSET=latin1;


   CREATE TABLE `country_data` (
     `COUNTRY_ID` int(11) NOT NULL AUTO_INCREMENT,
     `COUNTRY` varchar(50) NOT NULL,
     `NON_ADMITTED_ALLOWED` tinyint(1) NOT NULL DEFAULT '0',
     `NON_ADMITTED_COMMENTS` varchar(255) DEFAULT NULL,
     `RETAIL_BROKER_REQUIRED` tinyint(1) NOT NULL DEFAULT '0',
     `RETAIL_BROKER_COMMENTS` varchar(255) DEFAULT NULL,
     `REINSURANCE_BROKER_REQUIRED` tinyint(1) NOT NULL DEFAULT '0',
     `REINSURANCE_BROKER_COMMENTS` varchar(255) DEFAULT NULL,
     `MANDATORY_REINSURANCE_CESSION` tinyint(1) NOT NULL DEFAULT '0',
     `MANDATORY_REINSURANCE_COMMENTS` varchar(255) DEFAULT NULL,
     `STATESIDE_PREMIUM_ALLOWED` tinyint(1) NOT NULL DEFAULT '0',
     `STATESIDE_PREMIUM_COMMENTS` varchar(255) DEFAULT NULL,
     `OTHER_ACCOUNTING_REQUIREMENTS` varchar(255) DEFAULT NULL,
     `PREMIUM_RESERVE` varchar(255) DEFAULT NULL,
     `TAXES` varchar(255) DEFAULT NULL,
     `VAT` varchar(255) DEFAULT NULL,
     `REINSURANCE_TAX` varchar(255) DEFAULT NULL,
     `OTHER_REQUIREMENTS` varchar(2000) DEFAULT NULL,
     `POLICY_LANGUAGE` varchar(150) DEFAULT NULL,
     `TACIT_RENEWAL` tinyint(1) DEFAULT '0',
     `TACIT_RENEWAL_COMMENTS` varchar(255) DEFAULT NULL,
     `GENERAL_COMMENTS` varchar(2000) DEFAULT NULL,
     `CREATED_BY` int(11) NOT NULL,
     `DATE_CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     `UPDATED_BY` int(11) NOT NULL,
     `DATE_UPDATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
     PRIMARY KEY (`COUNTRY_ID`),
     UNIQUE KEY `COUNTRY_UK` (`COUNTRY`),
     KEY `COUNTRY_UPDATED_BY_USER_ID_FK` (`CREATED_BY`),
     CONSTRAINT `COUNTRY_CREATED_BY_USER_ID_FK` FOREIGN KEY (`CREATED_BY`) REFERENCES `user` (`USER_ID`),
     CONSTRAINT `COUNTRY_UPDATED_BY_USER_ID_FK` FOREIGN KEY (`CREATED_BY`) REFERENCES `user` (`USER_ID`)
   ) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=latin1;


insert into `t_user` values(1, 'ramesh@globex.com', 'ramesh', 'babu','9824ade57d45e6024e4faa629fc9fcee11b1bd5bfd9778ef6cc486d527294e037a6303ce8bfee35c', '', 'ramesh@globex.com', 'ramesh', '',NULL , '2012-09-27 20:28:41', '2012-09-27 20:28:41');
insert into `t_user_role` values(1, '2012-09-27 20:28:41', 0, '2012-09-27 20:28:41', 0, 0, 'ROLE_SUPER_ADMIN', 1, 1, 1);

insert into `t_user` values(2, 'admin@globex.com', 'admin', 'admin','9824ade57d45e6024e4faa629fc9fcee11b1bd5bfd9778ef6cc486d527294e037a6303ce8bfee35c', '', 'admin@globex.com', 'admin', '',NULL , '2012-09-27 20:28:41', '2012-09-27 20:28:41');
insert into `t_user_role` values(2, '2012-09-27 20:28:41', 0, '2012-09-27 20:28:41', 0, 0, 'ROLE_SUPER_ADMIN', 1, 1, 2);


CREATE USER 'dbuser'@'%' IDENTIFIED BY 'dbpassword';
grant all privileges on *.* to 'dbuser'@'%';