LMUserRegistrationModel=Backbone.Model.extend({
    defaults: {

    }
});

LMUserRegistrationView = Backbone.View.extend({
    el: '.layout-body',
    model : LMUserRegistrationModel,
    events: {
        'click .ls-registration-step-save' :'saveUserDetails',
        'click .lm-registration-step':'showRegistrationStep',
        'click .lm-menu-item':'showRegistrationStep',
        'click .lm-registration-body-section-toggle':'toggleFormSection',
         'click .user-nav-options':'renderUserOptions',
         'click .lm-layout-body-content':'closeUserOptions',
         'click .navigate-user-profile':'viewProfile'
    },
    render: function(){
       var _self=this;

       var organizationDetails={
            parentCompany:"",orgType:"",
            licenseNo:"",
            licenseAuthorityName:"",
            ratedByOtherAgency:"",
            licenseAuthWebsite:"",
            establishedDate:"",
            attachment:"",
            amBestRating:"",
            amBestLook:"",
            amOutlookDate:"",
            amRatingAttachment:"",
            sAndPRating:"",
            sAndPOutlook:"",
            sAndPrRatingOutlookDate:"",
            sAndPAttachment:"",
            misCompanyName:"",
            misCompanyCountry:"",
            misCompanyWebsite:"",
            misCompanyRating:"",
            misCompanyOutlook:"",
            misCompanyAttachment:"",
            alphaBrokers:"",
            reInsuranceLob:"",
            reInsuranceSupport:"",
            reInsuranceComments:"",
            reInsurancePlacementLob:"",
            reInsurancePlacement:"",
            reInsurancePlacementComments:"",
            insuRequiredDoc:"",
            reinsurBroker:"",
            reinsurBrokerText:"",
            insuRequiredDocAttach:"",
            regRegulator:"",
            regRegulatorText:"",
            serviceOption:"",
            serviceOptionText:"",
            registrationProcedure:"",
            requiredDocReinsurPlace:"",
            registrationProcedureAttach:"",
            specReqDocReinsurPlace:"",
            requiredDocReinsurPlaceAttach:"",
            compInvolClaims:"",
            premiumPayOption:"",
            premiumWithTax:"",
            claimHandlingWordingAttach:"",
            bankDetails:"",
            bankAddress:"",
            bankName:"",
            bankIban:"",
            bankSwiftCode:"",
            bankAttachment:""
       };

       var miscRatings=[{
        miscId:"",
        companyName:"",
        companyCountry:"",
        companyWebsite:"",
        companyRating:"",
        companyOutlook:"",
        companyAttachment:"",
       }];

       var lobs=[
            {orgLobId:"",lob:"Marine",authorizedToWrite:"",willingnessToWrite:""},
            {orgLobId:"",lob:"Property",authorizedToWrite:"",willingnessToWrite:""},
            {orgLobId:"",lob:"General Liability",authorizedToWrite:"",willingnessToWrite:""},
            {orgLobId:"",lob:"Professional Liability",authorizedToWrite:"",willingnessToWrite:""},
            {orgLobId:"",lob:"Package Policy",authorizedToWrite:"",willingnessToWrite:""},
            {orgLobId:"",lob:"Accident & Health",authorizedToWrite:"",willingnessToWrite:""},
            {orgLobId:"",lob:"Other Coverages",authorizedToWrite:"",willingnessToWrite:""}
       ];

       var rateRequirements=[{
           requirementId:"",
           requirementName:"",
           requirementLob:"",
           requirementRate:"",
           appliedTo:"",
           reqMinPremium:"",
           reqCurrency:"",
           reqType:""
       }];

       var commissionRequirements=[{
           commReqId:"",
           commissionLob:"",
           commissionRate:"",
           commissionAppliedTo:"",
           commissionFlatAmount:"",
           commissionCurrency:"",
       }];

       var organizationHistories=[
            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:1},
            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:1},
            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:1},

            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:2},
            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:2},
            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:2},

            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:3},
            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:3},
            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:3},

            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:4},
            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:4},
            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:4},

            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:5},
            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:5},
            {historyId:"",year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:5}

       ];

       var uwDepDetails=[{
            uwId:"",
            uwLob:"",
            uwName:"",
            uwRole:"",
            uwComments:"",
            uwOffice:"",
            uwGsm:"",
            uwEmail:"",
       }];

       var bankingDetails=[{
            bankingId:"",
            details:"",
            address:"",
            name:"",
            iban:"",
            swiftCode:"",
            email:"",
       }];

       var variables ={
           orgId:"",orgName:"",regDate:"",address1:"",address2:"",city:"",state:"",country:"",zip:"",
           website:"",orgUserType:"",telePhone:"",approved:"",comment:"",miscId:"",
           organizationDetails:organizationDetails,
           miscRatings:miscRatings,
           lobs:lobs,
           rateRequirements:rateRequirements,
           commissionRequirements:commissionRequirements,
           organizationHistories:organizationHistories,
           uwDepDetails:uwDepDetails,
           bankingDetails:bankingDetails
       };
       if(_self.model){
           variables = {
               orgId:_self.model.get("orgId"),orgName:_self.model.get("orgName"),regDate:_self.model.get("regDate"),address1:_self.model.get("address1"),
               address2:_self.model.get("address2"),city:_self.model.get("city"),state:_self.model.get("state"),country:_self.model.get("country"),
               zip:_self.model.get("zip"),website:_self.model.get("website"),orgUserType:_self.model.get("orgUserType"),telePhone:_self.model.get("telePhone"),
               approved:_self.model.get("approved"),comment:_self.model.get("comment"),
               organizationDetails:_self.model.get("organizationDetails"),miscRatings:_self.model.get("miscRatings"),
               lobs:_self.model.get("lobs"),rateRequirements:_self.model.get("rateRequirements"),
               commissionRequirements:_self.model.get("commissionRequirements"),organizationHistories:_self.model.get("organizationHistories"),
               uwDepDetails:_self.model.get("uwDepDetails"),bankingDetails:_self.model.get("bankingDetails")
           };
       }

       require(["lm/lm.org","lm/lm.banking","lm/lm.network","lm/lm.finance","lm/lm.account"],function(){
            require(['text!'+'templates/lm/lmRegistrationForm.html'], function(lmRegistrationForm) {
                var lm_registration_form = _.template( lmRegistrationForm, variables );
                $("#layout-body-content").html(lm_registration_form);
                _self.$el.find(".lm-registration-step:first").trigger('click');
                setValues();
                _self.validateDetails();
                _self.validateAccountInfo();
            });
       });
    },

    renderUserOptions:function(){
        this.$el.find('.profile-drop-down').show();
    },
    closeUserOptions:function(e){
        var $target = $(e.target);
        if(!$target.hasClass("profile-drop-down")){
           this.$el.find('.profile-drop-down').hide();
        }
    },
    viewProfile:function(){
      var _self=this;
      require(['user'], function() {
         var currentUserId=$("#currentUserId").val();
         var userModel = new UserModel({userId:currentUserId});
         var userProfileView=new UserProfileView({model:userModel});
         userProfileView.render();
         _self.$el.find("#layout-body-content").trigger("click");

      });
    },
    toggleFormSection:function(e){
        $(e.currentTarget).parent().next().toggle();
    },
    saveUserDetails:function(){
        /*this.stepView.saveDetails();*/
        //this.$el.find("#saveOrgInfo").submit();
        if(this.stepNo==1){
           this.$el.find("#saveOrgInfo").submit();
        }else{
            this.$el.find("#saveAccountInfo").submit();
        }
    },
    showRegistrationStep:function(e){
        this.$el.find(".lm-registration-step").removeClass("lm-registration-step-selected");
        var group=this.$el.find(e.currentTarget).attr("group");
        this.$el.find("[group='"+group+"']").addClass("lm-registration-step-selected");
        //this.$el.find(e.currentTarget).addClass("lm-registration-step-selected");
        var target=$(e.currentTarget).attr("target");
        this.$el.find(".lm-registration-step-section").hide();
        this.$el.find("."+target).show();

        if(group=='lm-org-details'){
           this.stepNo=1;
        }else if(group=='lm-finance-details'){
            this.stepNo= 2;
        }
        /*if(group=='lm-org-details'){
           this.stepView=new LMOrganizationView({ });
        }else if(group=='lm-finance-details'){
            this.stepView= new LMFinanceView({ });
        }else if(group=='lm-network-details'){
            this.stepView= new LMNetworkView({ });
         }else if(group=='lm-acount-details'){
            this.stepView= new LMAccountView({ });
         }else if(group=='lm-dept-details'){
            this.stepView= new LMBankingView({ });
         }*/

        $(".loading-icon-wrapper").show();
        $("body").css({opacity:0.5});
        // setTimeout(function(){
            $(".loading-icon-wrapper").hide();
            $("body").css({opacity:1})
       //   }, 2000);

        $(".date-picker").datepicker({ dateFormat: 'dd-mm-yy'});
        //this.stepView.render();
    },
    validateDetails:function(){
            var _self=this;
            require(["jquery.validate"],function(){
                _self.$el.find("#saveOrgInfo").ready(function() {
                    if (_self.$el.find("#saveOrgInfo").length > 0){
                        _self.$el.find("#saveOrgInfo").validate({
                            invalidHandler: function(e, validator) {},
                            /*rules: {

                                },*/
                        submitHandler: function(form) {
                        $(".loading-icon-wrapper").show();
                        $("body").css({opacity:0.5});

                        var formData=new FormData(form);

                        var orgDetails={};
                        orgDetails["establishedDate"]=$("[name='orgDetails.establishedDate']").val();
                        orgDetails["orgDetailsId"]=$("[name='orgDetails.orgDetailsId']").val();
                        orgDetails["parentCompany"]=$("[name='orgDetails.parentCompany']").val();
                        orgDetails["orgType"]=$("[name='orgDetails.orgType']").val();
                        orgDetails["licenseAuthorityName"]=$("[name='orgDetails.licenseAuthorityName']").val();
                        orgDetails["ratedByOtherAgency"]=$("[name='orgDetails.ratedByOtherAgency']").val();
                        orgDetails["licenseAuthWebsite"]=$("[name='orgDetails.licenseAuthWebsite']").val();
                        orgDetails["licenseNo"]=$("[name='orgDetails.licenseNo']").val();
                        orgDetails["amBestRating"]=$("[name='orgDetails.amBestRating']").val();
                        orgDetails["amBestLook"]=$("[name='orgDetails.amBestLook']").val();
                        orgDetails["amOutlookDate"]=$("[name='orgDetails.amOutlookDate']").val();
                        orgDetails["snpRating"]=$("[name='orgDetails.snpRating']").val();
                        orgDetails["snpOutlook"]=$("[name='orgDetails.snpOutlook']").val();
                        orgDetails["snpRatingOutlookDate"]=$("[name='orgDetails.snpRatingOutlookDate']").val();
                        orgDetails["snpAttachment"]=$("[name='orgDetails.snpAttachment']").val();

                        var miscRatingArr=[];
                        var $miscRatings= $(".misc-rating");
                        for(var i=0;i<$miscRatings.length;i++){
                            var miscRatingJson={};
                            var $miscRating=$($miscRatings[i]);
                            miscRatingJson["miscId"]=$miscRating.find("[name='miscId']").val();
                            miscRatingJson["companyName"]=$miscRating.find("[name='companyName']").val();
                            miscRatingJson["companyWebsite"]=$miscRating.find("[name='companyWebsite']").val();
                            miscRatingJson["companyCountry"]=$miscRating.find("[name='companyCountry']").val();
                            miscRatingJson["companyOutlook"]=$miscRating.find("[name='companyOutlook']").val();
                            miscRatingJson["companyRating"]=$miscRating.find("[name='companyRating']").val();
                            miscRatingArr.push(miscRatingJson);
                        }

                        var grossPremiumArr=[];
                        var $grossPremiums= $(".gross-premium-wrapper");
                        for(var i=0;i<$grossPremiums.length;i++){
                            var grossPremiumJson={};
                            var $grossPremium=$($grossPremiums[i]);
                            grossPremiumJson["type"]=$grossPremium.find("[name='type']").val();
                            grossPremiumJson["historyId"]=$grossPremium.find("[name='historyId']").val();
                            grossPremiumJson["year"]=$grossPremium.find("[name='year']").val();
                            grossPremiumJson["premium"]=$grossPremium.find("[name='premium']").val();
                            grossPremiumJson["premiumCurrency"]=$grossPremium.find("[name='premiumCurrency']").val();
                            grossPremiumArr.push(grossPremiumJson);
                        }

                        var combinedRatioArr=[];
                        var $combinedRatios= $(".combined-ratio");
                        for(var i=0;i<$combinedRatios.length;i++){
                            var combinedRatioJson={};
                            var $combinedRatio=$($combinedRatios[i]);
                            combinedRatioJson["type"]=$combinedRatio.find("[name='type']").val();
                            combinedRatioJson["historyId"]=$combinedRatio.find("[name='historyId']").val();
                            combinedRatioJson["year"]=$combinedRatio.find("[name='year']").val();
                            combinedRatioJson["combinedRatio"]=$combinedRatio.find("[name='combinedRatio']").val();
                            combinedRatioArr.push(combinedRatioJson);
                        }

                        var totalAssetsArr=[];
                        var $totalAssets= $(".total-assets");
                        for(var i=0;i<$totalAssets.length;i++){
                            var totalAssetsJson={};
                            var $totalAsset=$($totalAssets[i]);
                            totalAssetsJson["historyId"]=$totalAsset.find("[name='historyId']").val();
                            totalAssetsJson["type"]=$totalAsset.find("[name='type']").val();
                            totalAssetsJson["year"]=$totalAsset.find("[name='year']").val();
                            totalAssetsJson["totalAssets"]=$totalAsset.find("[name='totalAssets']").val();
                            totalAssetsJson["totalAssetsCurrency"]=$totalAsset.find("[name='totalAssetsCurrency']").val();
                            totalAssetsArr.push(totalAssetsJson);
                        }

                        var compRankingsArr=[];
                        var $compRankings= $(".comp-rank");
                        for(var i=0;i<$compRankings.length;i++){
                            var compRankingsJson={};
                            var $compRanking=$($compRankings[i]);
                            compRankingsJson["historyId"]=$compRanking.find("[name='historyId']").val();
                            compRankingsJson["type"]=$compRanking.find("[name='type']").val();
                            compRankingsJson["year"]=$compRanking.find("[name='year']").val();
                            compRankingsJson["ranking"]=$compRanking.find("[name='ranking']").val();
                            compRankingsArr.push(compRankingsJson);
                        }

                        var finReportsArr=[];
                        var $finReports= $(".fin-report");
                        for(var i=0;i<$finReports.length;i++){
                            var finReportsJson={};
                            var $finReport=$($finReports[i]);
                            finReportsJson["type"]=$finReport.find("[name='type']").val();
                            finReportsJson["historyId"]=$finReport.find("[name='historyId']").val();
                            finReportsJson["year"]=$finReport.find("[name='year']").val();
                            finReportsJson["finComments"]=$finReport.find("[name='finComments']").val();
                            finReportsArr.push(finReportsJson);
                        }
                        orgDetails["alphaBrokers"]=$("[name='orgDetails.alphaBrokers']").val();

                        var lobsArr=[];
                        var $lobs= $(".lob");
                        for(var i=0;i<$lobs.length;i++){
                            var lobsJson={};
                            var $lob=$($lobs[i]);
                            lobsJson["orgLobId"]=$lob.find("[name='orgLobId']").val();
                            lobsJson["lob"]=$lob.find("[name='lob']").val();
                            lobsJson["authorizedToWrite"]=$lob.find("[name='authorizedToWrite']").val();
                            lobsJson["willingnessToWrite"]=$lob.find("[name='willingnessToWrite']").val();
                            lobsArr.push(lobsJson);
                        }
                        orgDetails["reInsuranceLob"]=$("[name='orgDetails.reInsuranceLob']").val();
                        orgDetails["reInsuranceSupport"]=$("[name='orgDetails.reInsuranceSupport']").val();
                        orgDetails["reInsuranceComments"]=$("[name='orgDetails.reInsuranceComments']").val();

                        var rateRequirementsArr=[];
                        var $rateRequirements= $(".rate-req");
                        for(var i=0;i<$rateRequirements.length;i++){
                            var rateRequirementJson={};
                            var $rateRequirement=$($rateRequirements[i]);
                            rateRequirementJson["requirementId"]=$rateRequirement.find("[name='requirementId']").val();
                            rateRequirementJson["requirementLob"]=$rateRequirement.find("[name='requirementLob']").val();
                            rateRequirementJson["requirementRate"]=$rateRequirement.find("[name='requirementRate']").val();
                            rateRequirementJson["reqMinPremium"]=$rateRequirement.find("[name='reqMinPremium']").val();
                            rateRequirementJson["reqCurrency"]=$rateRequirement.find("[name='reqCurrency']").val();
                            rateRequirementJson["reqType"]=$rateRequirement.find("[name='reqType']").val();
                            rateRequirementsArr.push(rateRequirementJson);
                        }

                        var orgHist=[];
                        orgHist=orgHist.concat(grossPremiumArr);
                        orgHist=orgHist.concat(combinedRatioArr);
                        orgHist=orgHist.concat(totalAssetsArr);
                        orgHist=orgHist.concat(compRankingsArr);
                        orgHist=orgHist.concat(finReportsArr);

                         var orgParametersJson={};
                         orgParametersJson['organizationHistories']=orgHist;
                         orgParametersJson['lobs']=lobsArr;
                         orgParametersJson['rateRequirements']=rateRequirementsArr;
                         orgParametersJson['miscRatings']=miscRatingArr;

                         var orgParametersJsonStr=JSON.stringify(orgParametersJson);
                         var orgDetailsJsonStr=JSON.stringify(orgDetails);
                         formData.append('orgParametersJson',orgParametersJsonStr);
                         formData.append('orgDetailsJson',orgDetailsJsonStr);

                        $.ajax({
                            type: 'POST',
                            url: form.action,
                            data: formData,
                            async: false,
                            cache: false,
                            contentType: false,
                            processData: false,
                            error: function() {
                                 setTimeout(function(){
                                     $(".loading-icon-wrapper").hide();
                                     $("body").css({opacity:1})
                                   }, 3000);
                            },
                            success: function(orgJSON) {
                                if(orgJSON != undefined && orgJSON != null && orgJSON != ''){
                                    if(orgJSON){
                                       $("[name='orgId']").val(orgJSON.orgId);
                                       $("[name='orgDetails.orgDetailsId']").val(orgJSON.orgDetailsId);
                                      // _self.$el.find("#bankingContactId").val(bankingJSON.contact.id);
                                    }
                                }
                                 setTimeout(function(){
                                     $(".loading-icon-wrapper").hide();
                                     $("body").css({opacity:1});
                                     var nextStep=$(".lm-registration-step.lm-registration-step-selected").attr("nextStep");
                                     $(".lm-registration-step:eq("+nextStep+")").trigger("click");
                                   }, 3000);

                            }
                        });
                       }
                      });
                    }
                });
            });
    },
    validateAccountInfo:function(){
        var _self=this;
        require(["jquery.validate"],function(){
            _self.$el.find("#saveAccountInfo").ready(function() {
                if (_self.$el.find("#saveAccountInfo").length > 0){
                    _self.$el.find("#saveAccountInfo").validate({
                        invalidHandler: function(e, validator) {},
                        /*rules: {

                            },*/
                    submitHandler: function(form) {
                    $(".loading-icon-wrapper").show();
                    $("body").css({opacity:0.5});

                    var formData=new FormData(form);

                    /**************************************** STEP2 *************************************/

                    var commRequirementsArr=[];
                    var $commRequirements= $(".re-ins-com-req");
                    for(var i=0;i<$commRequirements.length;i++){
                        var commRequirementJson={};
                        var $commRequirement=$($commRequirements[i]);
                        commRequirementJson["commReqId"]=$commRequirement.find("[name='commReqId']").val();
                        commRequirementJson["commissionLob"]=$commRequirement.find("[name='commissionLob']").val();
                        commRequirementJson["commissionRate"]=$commRequirement.find("[name='commissionRate']").val();
                        commRequirementJson["commissionAppliedTo"]=$commRequirement.find("[name='commissionAppliedTo']").val();
                        commRequirementJson["commissionFlatAmount"]=$commRequirement.find("[name='commissionFlatAmount']").val();
                        commRequirementJson["commissionCurrency"]=$commRequirement.find("[name='commissionCurrency']").val();
                        commRequirementsArr.push(commRequirementJson);
                    }
                    var orgDetails={};
                    orgDetails["orgDetailsId"]=$("[name='orgDetails.orgDetailsId']").val();
                    orgDetails["reInsurancePlacementLob"]=$("[name='orgDetails.reInsurancePlacementLob']").val();
                    orgDetails["reInsurancePlacementComments"]=$("[name='orgDetails.reInsurancePlacementComments']").val();
                    orgDetails["insuRequiredDoc"]=$("[name='orgDetails.insuRequiredDoc']").val();
                    orgDetails["serviceOption"]=$("[name='orgDetails.serviceOption']").val();
                    orgDetails["serviceOptionText"]=$("[name='orgDetails.serviceOptionText']").val();
                    orgDetails["regRegulator"]=$("[name='orgDetails.regRegulator']").val();
                    orgDetails["regRegulatorText"]=$("[name='orgDetails.regRegulatorText']").val();
                    orgDetails["adviceRegistration"]=$("[name='orgDetails.adviceRegistration']").val();
                    orgDetails["requiredDocReinsurPlace"]=$("[name='orgDetails.requiredDocReinsurPlace']").val();
                    orgDetails["specReqDocReinsurPlace"]=$("[name='orgDetails.specReqDocReinsurPlace']").val();
                    orgDetails["compInvolClaims"]=$("[name='orgDetails.compInvolClaims']").val();
                    orgDetails["compInvolClaimsText"]=$("[name='orgDetails.compInvolClaimsText']").val();
                    orgDetails["premiumPayOption"]=$("[name='orgDetails.premiumPayOption']").val();
                    orgDetails["premiumWithTax"]=$("[name='orgDetails.premiumWithTax']").val();

                    var uwContactsArr=[];
                    var $uwContacts= $(".uw-contact");
                    for(var i=0;i<$uwContacts.length;i++){
                        var uwContactJson={};
                        var $uwContact=$($uwContacts[i]);
                        uwContactJson["uwId"]=$uwContact.find("[name='uwId']").val();
                        uwContactJson["uwName"]=$uwContact.find("[name='uwName']").val();
                        uwContactJson["uwRole"]=$uwContact.find("[name='uwRole']").val();
                        uwContactJson["uwComments"]=$uwContact.find("[name='uwComments']").val();
                        uwContactJson["uwOffice"]=$uwContact.find("[name='uwOffice']").val();
                        uwContactJson["uwGsm"]=$uwContact.find("[name='uwGsm']").val();
                        uwContactJson["uwEmail"]=$uwContact.find("[name='uwEmail']").val();
                        uwContactsArr.push(uwContactJson);
                    }

                    var accContactsArr=[];
                    var $accContacts= $(".acc-contact");
                    for(var i=0;i<$accContacts.length;i++){
                        var accContactJson={};
                        var $accContact=$($accContacts[i]);
                        accContactJson["bankingId"]=$accContact.find("[name='bankingId']").val();
                        accContactJson["details"]=$accContact.find("[name='details']").val();
                        accContactJson["address"]=$accContact.find("[name='address']").val();
                        accContactJson["name"]=$accContact.find("[name='name']").val();
                        accContactJson["iban"]=$accContact.find("[name='iban']").val();
                        accContactJson["swiftCode"]=$accContact.find("[name='swiftCode']").val();
                        accContactJson["email"]=$accContact.find("[name='email']").val();
                        accContactsArr.push(accContactJson);
                    }

                      var orgParametersJson={};
                      orgParametersJson['commissionRequirements']=commRequirementsArr;
                      orgParametersJson['uwDepDetails']=uwContactsArr;
                      orgParametersJson['bankingDetails']=accContactsArr;

                      var orgParametersJsonStr=JSON.stringify(orgParametersJson);
                      var orgDetailsJsonStr=JSON.stringify(orgDetails);
                      formData.append('orgParametersJson',orgParametersJsonStr);
                      formData.append('orgDetailsJson',orgDetailsJsonStr);

                    $.ajax({
                        type: 'POST',
                        url: form.action,
                        data: formData,
                        async: false,
                        cache: false,
                        contentType: false,
                        processData: false,
                        error: function() {
                             setTimeout(function(){
                                 $(".loading-icon-wrapper").hide();
                                 $("body").css({opacity:1})
                               }, 3000);
                        },
                        success: function(orgJSON) {
                            if(orgJSON != undefined && orgJSON != null && orgJSON != ''){
                                if(orgJSON){
                                   _self.$el.find("#orgId").val(orgJSON.orgId);
                                   _self.$el.find("#orgDetailsId").val(orgJSON.orgDetailsId);
                                  // _self.$el.find("#bankingContactId").val(bankingJSON.contact.id);
                                }
                            }
                             setTimeout(function(){
                                 $(".loading-icon-wrapper").hide();
                                 $("body").css({opacity:1});
                                 var nextStep=$(".lm-registration-step.lm-registration-step-selected").attr("nextStep");
                                 $(".lm-registration-step:eq("+nextStep+")").trigger("click");
                               }, 3000);

                        }
                    });
                   }
                  });
                }
            });
        });
        },
});
