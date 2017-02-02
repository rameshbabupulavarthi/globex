LMFinanceView = Backbone.View.extend({
    el: '.lm-registration-step2',
    render: function(){
        this.$el.find("#amBestRatingDate").datepicker({dateFormat:'dd/mm/yy'});
        this.$el.find("#sAndPrRatingOutlookDate").datepicker({dateFormat:'dd/mm/yy'});
        var lmUserRegistrationJSONData= $("#lmUserRegistrationJSONData script").html();
        if(lmUserRegistrationJSONData){
            var lmUserRegistrationJSON = eval('(' + lmUserRegistrationJSONData +')');
            var financeJSON=lmUserRegistrationJSON.finance;
            if(financeJSON){
                this.$el.find("#financeId").val(financeJSON.id);
                this.$el.find("[name='networkPartnerQualifier']").val(financeJSON.companyType);
                this.$el.find("#licenseNo").val(financeJSON.licenseNo);
                this.$el.find("#licenseAuthority").val(financeJSON.licenseAuthority);
                this.$el.find("#licenseAuthWebsite").val(financeJSON.licenseAuthWebsite);
                this.$el.find("#companyEstablishmentYear").val(financeJSON.companyEstablishmentYear);
                this.$el.find("[name='amBestRating']").val(financeJSON.amBestRating);
                this.$el.find("[name='amBestLook']").val(financeJSON.amBestLook);

                var amBestRatingDate=new Date(financeJSON.amBestRatingDate);
                this.$el.find("#amBestRatingDate").datepicker("setDate",amBestRatingDate);

                var sAndPrRatingOutlookDate=new Date(financeJSON.sAndPrRatingOutlookDate);
                this.$el.find("#sAndPrRatingOutlookDate").datepicker("setDate",sAndPrRatingOutlookDate);

                //this.$el.find("#amRatingAttachment").val(financeJSON.amRatingAttachment);
                this.$el.find("#otherBrokers").val(financeJSON.otherBrokers);
                this.$el.find("#sAndPRating").val(financeJSON.sAndPRating);
                this.$el.find("[name='sAndPOutlook']").val(financeJSON.sAndPOutlook);
                //this.$el.find("#sAndPAttachment").val(financeJSON.sAndPAttachment);
                this.$el.find("#miscCompName").val(financeJSON.miscCompName);
                this.$el.find("#miscCompWebsite").val(financeJSON.miscCompWebsite);
                this.$el.find("#miscCompCountry").val(financeJSON.miscCompCountry);
                this.$el.find("#miscCompRating").val(financeJSON.miscCompRating);
                this.$el.find("#miscCompOutlook").val(financeJSON.miscCompOutlook);
                this.$el.find("#ratedByOtherAgency").val(financeJSON.ratedByOtherAgency);
                //this.$el.find("#miscCompAttachment").val(financeJSON.miscCompAttachment);
                this.$el.find("[name='businessToWrite']").val(financeJSON.businessToWrite);
                this.$el.find("[name='businessNotToWrite']").val(financeJSON.businessNotToWrite);
                this.$el.find("#lobComments").val(financeJSON.lobComments);
                this.$el.find("#countryList").val(financeJSON.countryList);
                this.$el.find("#selectedCountry").val(financeJSON.selectedCountry);
                this.$el.find("#territoryComment").val(financeJSON.territoryComment);
            }
        }
        this.registerClickEvents();
        this.validateDetails();
    },
    registerClickEvents:function(){

    },
    validateDetails:function(){
        var _self=this;
        require(["jquery.validate"],function(){
            _self.$el.find("#saveFinancialInfo").ready(function() {
                if (_self.$el.find("#saveFinancialInfo").length > 0){
                    _self.$el.find("#saveFinancialInfo").validate({
                        invalidHandler: function(e, validator) {},
                        rules: {
                                companyType:"required",
                                parentCompany:"required",
                                licenseNo:"required",
                                licenseAuthority:"required",
                                licenseAuthWebsite:"required",
                                companyEstablishmentYear:{required: true,number: true},
                                amBestLook:"required",
                                amBestRatingDate:"required",
                                sAndPrRatingOutlookDate:"required"
                         },
                        submitHandler: function(form) {
                            $(".loading-icon-wrapper").show();
                            $("body").css({opacity:0.5});

                            $.ajax({
                                type: 'POST',
                                url: form.action,
                                data: _self.$el.find('#saveFinancialInfo').serialize(),
                                error: function() {
                                     setTimeout(function(){
                                         $(".loading-icon-wrapper").hide();
                                         $("body").css({opacity:1})
                                         }, 3000);
                                },
                                success: function(financeJSON) {
                                    if(financeJSON){
                                       _self.$el.find("#financeId").val(financeJSON.id);
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
    saveDetails:function(){
        this.$el.find("#saveFinancialInfo").submit();
    }
});