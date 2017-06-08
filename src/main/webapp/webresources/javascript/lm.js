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
        companyName:"",
        companyCountry:"",
        companyWebsite:"",
        companyRating:"",
        companyOutlook:"",
        companyAttachment:"",
       }];

       var lobs=[
            {lob:"Marine",authorizedToWrite:"",willingnessToWrite:""},
            {lob:"Property",authorizedToWrite:"",willingnessToWrite:""},
            {lob:"General Liability",authorizedToWrite:"",willingnessToWrite:""},
            {lob:"Professional Liability",authorizedToWrite:"",willingnessToWrite:""},
            {lob:"Package Policy",authorizedToWrite:"",willingnessToWrite:""},
            {lob:"Accident & Health",authorizedToWrite:"",willingnessToWrite:""},
            {lob:"Other Coverages",authorizedToWrite:"",willingnessToWrite:""}
       ];

       var rateRequirements=[{
           requirementName:"",
           requirementLob:"",
           requirementRate:"",
           appliedTo:"",
           reqMinPremium:"",
           reqCurrency:"",
           reqType:""
       }];

       var commissionRequirements=[{
           commissionLob:"",
           commissionRate:"",
           commissionAppliedTo:"",
           commissionFlatAmount:"",
           commissionCurrency:"",
       }];

       var organizationHistories=[
            {year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:""},
            {year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:""},
            {year:"",premium:"",premiumCurrency:"",combinedRatio:"",totalAssets:"",totalAssetsCurrency:"",attachment:"",comments:"",ranking:"",type:""}
       ];

       var uwDepDetails=[{
            uwLob:"",
            uwName:"",
            uwRole:"",
            uwComments:"",
            uwOffice:"",
            uwGsm:"",
            uwEmail:"",
       }];

       var bankingDetails=[{
            details:"",
            address:"",
            name:"",
            iBan:"",
            swiftCode:"",
            email:"",
       }];

       var variables ={
           orgId:"",orgName:"",regDate:"",address1:"",address2:"",city:"",state:"",country:"",zip:"",
           website:"",orgUserType:"",telePhone:"",approved:"",comment:"",
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
                _self.validateDetails();
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
        //this.stepView.saveDetails();
        this.$el.find("#saveOrgInfo").submit();
    },
    showRegistrationStep:function(e){
        this.$el.find(".lm-registration-step").removeClass("lm-registration-step-selected");
        var group=this.$el.find(e.currentTarget).attr("group");
        this.$el.find("[group='"+group+"']").addClass("lm-registration-step-selected");
        //this.$el.find(e.currentTarget).addClass("lm-registration-step-selected");
        var target=$(e.currentTarget).attr("target");
        this.$el.find(".lm-registration-step-section").hide();
        this.$el.find("."+target).show();

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
                        /*orgDetails["regDate"]=$("[name='regDate']").val();
                        orgDetails["attachment"]=$("[name='orgDetails.attachment']").val();
                        orgDetails["attachment"]=$("[name='orgDetails.amRatingAttachment']").val();
                        orgDetails["attachment"]=$("[name='orgDetails.misCompanyAttachment']").val();*/

                        orgDetails["parentCompany"]=$("[name='orgDetails.parentCompany']").val();
                        orgDetails["orgType"]=$("[name='orgDetails.orgType']").val();
                        orgDetails["licenseAuthorityName"]=$("[name='orgDetails.licenseAuthorityName']").val();
                        orgDetails["ratedByOtherAgency"]=$("[name='orgDetails.ratedByOtherAgency']").val();
                        orgDetails["licenseAuthWebsite"]=$("[name='orgDetails.licenseAuthWebsite']").val();
                        orgDetails["licenseNo"]=$("[name='orgDetails.licenseNo']").val();
                        orgDetails["amBestRating"]=$("[name='orgDetails.amBestRating']").val();
                        orgDetails["amBestLook"]=$("[name='orgDetails.amBestLook']").val();
                        orgDetails["amOutlookDate"]=$("[name='orgDetails.amOutlookDate']").val();
                        /*orgDetails["sAndPRating"]=$("[name='orgDetails.sAndPRating']").val();
                        orgDetails["sAndPOutlook"]=$("[name='orgDetails.sAndPOutlook']").val();
                        orgDetails["sAndPrRatingOutlookDate"]=$("[name='orgDetails.sAndPrRatingOutlookDate']").val();
                        orgDetails["sAndPAttachment"]=$("[name='orgDetails.sAndPAttachment']").val();*/
                        orgDetails["misCompanyName"]=$("[name='misCompanyName']").val();
                        orgDetails["misCompanyWebsite"]=$("[name='misCompanyWebsite']").val();
                        orgDetails["misCompanyCountry"]=$("[name='misCompanyCountry']").val();
                        orgDetails["misCompanyOutlook"]=$("[name='misCompanyOutlook']").val();
                        orgDetails["misCompanyRating"]=$("[name='misCompanyRating']").val();

                        var grossPremiumArr=[];
                        var $grossPremiums= $(".gross-premium-wrapper");
                        for(var i=0;i<$grossPremiums.length;i++){
                            var grossPremiumJson={};
                            var $grossPremium=$($grossPremiums[i]);
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
                            combinedRatioJson["year"]=$combinedRatio.find("[name='year']").val();
                            combinedRatioJson["combinedRatio"]==$combinedRatio.find("[name='combinedRatio']").val();
                            combinedRatioArr.push(combinedRatioJson);
                        }

                        var totalAssetsArr=[];
                        var $totalAssets= $(".total-assets");
                        for(var i=0;i<$totalAssets.length;i++){
                            var totalAssetsJson={};
                            var $totalAsset=$($totalAssets[i]);
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
                            compRankingsJson["year"]=$compRanking.find("[name='year']").val();
                            compRankingsJson["ranking"]=$compRanking.find("[name='ranking']").val();
                            compRankingsArr.push(compRankingsJson);
                        }

                        var finReportsArr=[];
                        var $finReports= $(".fin-report");
                        for(var i=0;i<$finReports.length;i++){
                            var finReportsJson={};
                            var $finReport=$($finReports[i]);
                            finReportsJson["year"]=$finReport.find("[name='year']").val();
                            //finReportsJson["finComments"]=$finReport.find("[name='finComments']").val();
                            finReportsArr.push(finReportsJson);
                        }

                        orgDetails["alphaBrokers"]=$("[name='orgDetails.alphaBrokers']").val();

                        var lobsArr=[];
                        var $lobs= $(".lob");
                        for(var i=0;i<$lobs.length;i++){
                            var lobsJson={};
                            var $lob=$($lobs[i]);
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
                            //rateRequirementJson["requirementId"]=$rateRequirement.find("[name='requirementId']").val();
                            rateRequirementJson["requirementLob"]=$rateRequirement.find("[name='requirementLob']").val();
                            rateRequirementJson["requirementRate"]=$rateRequirement.find("[name='requirementRate']").val();
                            rateRequirementJson["reqMinPremium"]=$rateRequirement.find("[name='reqMinPremium']").val();
                            rateRequirementJson["reqCurrency"]=$rateRequirement.find("[name='reqCurrency']").val();
                            rateRequirementJson["reqType"]=$rateRequirement.find("[name='reqType']").val();
                            rateRequirementsArr.push(rateRequirementJson);
                        }

                        /**************************************** STEP2 *************************************/

                        var commRequirementsArr=[];
                        var $commRequirements= $(".re-ins-com-req");
                        for(var i=0;i<$commRequirements.length;i++){
                            var commRequirementJson={};
                            var $commRequirement=$($commRequirements[i]);
                            //commRequirementJson["requirementId"]=$commRequirement.find("[name='requirementId']").val();
                            commRequirementJson["commissionLob"]=$commRequirement.find("[name='commissionLob']").val();
                            commRequirementJson["commissionRate"]=$commRequirement.find("[name='commissionRate']").val();
                            commRequirementJson["commissionAppliedTo"]=$commRequirement.find("[name='commissionAppliedTo']").val();
                            commRequirementJson["commissionFlatAmount"]=$commRequirement.find("[name='commissionFlatAmount']").val();
                            commRequirementJson["commissionCurrency"]=$commRequirement.find("[name='commissionCurrency']").val();
                            commRequirementsArr.push(commRequirementJson);
                        }

                        var uwContactsArr=[];
                        var $uwContacts= $(".uw-contact");
                        for(var i=0;i<$uwContacts.length;i++){
                            var uwContactJson={};
                            var $uwContact=$($uwContacts[i]);
                            //uwContactJson["uwId"]=$uwContact.find("[name='uwId']").val();
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
                            //accContactJson["bankId"]=$accContact.find("[name='bankId']").val();
                            accContactJson["bankDetails"]=$accContact.find("[name='bankDetails']").val();
                            accContactJson["bankAddress"]=$accContact.find("[name='bankAddress']").val();
                            accContactJson["bankName"]=$accContact.find("[name='bankName']").val();
                            accContactJson["bankIban"]=$accContact.find("[name='bankIban']").val();
                            accContactJson["bankSwiftCode"]=$accContact.find("[name='bankSwiftCode']").val();
                            accContactJson["bankAttachment"]=$accContact.find("[name='bankAttachment']").val();
                            accContactsArr.push(accContactJson);
                        }

                        var orgHist=[];
                        orgHist.concat(grossPremiumArr);
                        orgHist.concat(combinedRatioArr);
                        orgHist.concat(totalAssetsArr);
                        orgHist.concat(compRankingsArr);
                        orgHist.concat(finReportsArr);

                         var orgParametersJson={};
                         orgParametersJson['organizationHistories']=orgHist;
                         orgParametersJson['lobs']=lobsArr;
                         orgParametersJson['rateRequirements']=rateRequirementsArr;
                         orgParametersJson['commissionRequirements']=commRequirementsArr;
                         orgParametersJson['uwDepDetails']=uwContactsArr;
                         orgParametersJson['bankingDetails']=accContactsArr;

                         var orgParametersJsonStr=JSON.stringify(orgParametersJson);
                         var orgDetailsJsonStr=JSON.stringify(orgDetails);
                         formData.append('orgParametersJson',orgParametersJsonStr);
                         formData.append('orgDetailsJson',orgDetailsJsonStr);
                        /*formData.append('taxTypesJsonStr',countryJson.taxTypesJsonStr);
                        formData.append('taxRequirementsJsonStr',countryJson.taxRequirementsJsonStr);
                        formData.append('clausesJsonStr',countryJson.clausesJsonStr);*/

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
                                       _self.$el.find("#bankingId").val(orgJSON.id);
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
/*
			}

		});
	});
 }
});*/
