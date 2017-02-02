LMAccountView = Backbone.View.extend({
    el: '.lm-registration-step4',
    render: function(){
        var lmUserRegistrationJSONData= $("#lmUserRegistrationJSONData script").html();
        if(lmUserRegistrationJSONData){
            var lmUserRegistrationJSON = eval('(' + lmUserRegistrationJSONData +')');
            var accountJSON=lmUserRegistrationJSON.account;
            if(accountJSON){
                this.$el.find("#accountId").val(accountJSON.id);
                this.$el.find("#countryList").val(accountJSON.countryList);
                this.$el.find("#territoryComment").val(accountJSON.territoryComment);
                this.$el.find("#selectedCountry").val(accountJSON.selectedCountry);
                this.$el.find("#authLobLimit").val(accountJSON.authLobLimit);
                this.$el.find("#minimumPremium").val(accountJSON.minimumPremium);
                this.$el.find("#authLobCurrency").val(accountJSON.authLobCurrency);
                this.$el.find("#authLobpPremiumReverse").val(accountJSON.authLobpPremiumReverse);
                this.$el.find("#comEstablishmentYear").val(accountJSON.comEstablishmentYear);
                this.$el.find("#authLobeExactTaxes").val(accountJSON.authLobeExactTaxes);
                this.$el.find("#authLobExactTaxes1Text1").val(accountJSON.authLobExactTaxes1Text1);
                this.$el.find("#authLobExactTaxes1Text2").val(accountJSON.authLobExactTaxes1Text2);
                this.$el.find("#taxResponsible").val(accountJSON.taxResponsible);
                this.$el.find("#flatPercentage").val(accountJSON.flatPercentage);
                this.$el.find("#taxApplied").val(accountJSON.taxApplied);
                this.$el.find("#lobTariffComment").val(accountJSON.lobTariffComment);
                this.$el.find("#lobTariffCommentAttach").val(accountJSON.lobTariffCommentAttach);
                this.$el.find("#reInsurancecCommission").val(accountJSON.reInsurancecCommission);
                this.$el.find("#lobMandatoryClauses").val(accountJSON.lobMandatoryClauses);
                this.$el.find("#lobMandatoryClausesAttach").val(accountJSON.lobMandatoryClausesAttach);
                this.$el.find("#locCurrRefLocPolicy").val(accountJSON.locCurrRefLocPolicy);
                this.$el.find("#locCurrRefLocPolicyText").val(accountJSON.locCurrRefLocPolicyText);
                this.$el.find("#forJuriAvalLocPolicy").val(accountJSON.forJuriAvalLocPolicy);
                this.$el.find("#forJuriAvalLocPolicyText").val(accountJSON.forJuriAvalLocPolicyText);
                this.$el.find("#manuscriptAval").val(accountJSON.manuscriptAval);
                this.$el.find("#manuscriptAvalText").val(accountJSON.manuscriptAvalText);
                this.$el.find("#forReinsurSupport").val(accountJSON.forReinsurSupport);
                this.$el.find("#forReinsurSupportText").val(accountJSON.forReinsurSupportText);
                this.$el.find("#insuRequiredDoc").val(accountJSON.insuRequiredDoc);
                this.$el.find("#insuRequiredDocAttach").val(accountJSON.insuRequiredDocAttach);
                this.$el.find("#taxId").val(accountJSON.taxId);
                this.$el.find("#taxIdText").val(accountJSON.taxIdText);
                this.$el.find("#serviceOption").val(accountJSON.serviceOption);
                this.$el.find("#serviceOptionText").val(accountJSON.serviceOptionText);
                this.$el.find("#reinsurBroker").val(accountJSON.reinsurBroker);
                this.$el.find("#reinsurBrokerText").val(accountJSON.reinsurBrokerText);
                this.$el.find("#regRegulator").val(accountJSON.regRegulator);
                this.$el.find("#regRegulatorText").val(accountJSON.regRegulatorText);
                this.$el.find("#registrationProcedure").val(accountJSON.registrationProcedure);
                this.$el.find("#registrationProcedureAttach").val(accountJSON.registrationProcedureAttach);
                this.$el.find("#requiredDocReinsurPlace").val(accountJSON.requiredDocReinsurPlace);
                this.$el.find("#requiredDocReinsurPlaceAttach").val(accountJSON.requiredDocReinsurPlaceAttach);
                this.$el.find("#specReqDocReinsurPlace").val(accountJSON.specReqDocReinsurPlace);
                this.$el.find("#specReqDocReinsurPlaceAttach").val(accountJSON.specReqDocReinsurPlaceAttach);
                this.$el.find("#compInvolClaims").val(accountJSON.compInvolClaims);
                this.$el.find("#compInvolClaimsText").val(accountJSON.compInvolClaimsText);
                this.$el.find("#claimPayMasterLocal").val(accountJSON.claimPayMasterLocal);
                this.$el.find("#claimPayMasterLocalText").val(accountJSON.claimPayMasterLocalText);
                this.$el.find("#claimHandlingWordingAttach").val(accountJSON.claimHandlingWordingAttach);
                this.$el.find("#premiumPayOption").val(accountJSON.premiumPayOption);
                this.$el.find("#premiumWithTax").val(accountJSON.premiumWithTax);
                this.$el.find("#nonAdmittedPolicy").val(accountJSON.nonAdmittedPolicy);
                this.$el.find("#nonAdmittedPolicyText").val(accountJSON.nonAdmittedPolicyText);
                this.$el.find("#nonAdmittedComments").val(accountJSON.nonAdmittedComments);
                this.$el.find("#mandatoryReinsurCession").val(accountJSON.mandatoryReinsurCession);
                this.$el.find("#mandatoryReinsurCessionText").val(accountJSON.mandatoryReinsurCessionText);
                this.$el.find("#mandatoryReinsurComments").val(accountJSON.mandatoryReinsurComments);
                this.$el.find("#policyLang").val(accountJSON.policyLang);
                this.$el.find("#tacitRenewal").val(accountJSON.tacitRenewal);
                this.$el.find("#tacitRenewalText").val(accountJSON.tacitRenewalText);
                this.$el.find("#tacitRenewalComments").val(accountJSON.tacitRenewalComments);
                this.$el.find("#network").val(accountJSON.network);
                this.$el.find("#cashBeforeCoverReq").val(accountJSON.cashBeforeCoverReq);
                this.$el.find("#cashBeforeCoverReqText").val(accountJSON.cashBeforeCoverReqText);
                this.$el.find("#premiumPaymentWarranty").val(accountJSON.premiumPaymentWarranty);
                this.$el.find("#premiumPaymentWarrantyText").val(accountJSON.premiumPaymentWarrantyText);
                this.$el.find("#premiumPaymentWarrantyDays").val(accountJSON.premiumPaymentWarrantyDays);
                this.$el.find("#foreignCurrencyAccept").val(accountJSON.foreignCurrencyAccept);
                this.$el.find("#foreignCurrencyAcceptText").val(accountJSON.foreignCurrencyAcceptText);
                this.$el.find("#acceptableCurrency").val(accountJSON.acceptableCurrency);
                this.$el.find("#premiumRemittanceTime").val(accountJSON.premiumRemittanceTime);
                this.$el.find("#backDatingPossible").val(accountJSON.backDatingPossible);
                this.$el.find("#backDatingPossibleText").val(accountJSON.backDatingPossibleText);
                this.$el.find("#localCurrencyRequirement").val(accountJSON.localCurrencyRequirement);
                this.$el.find("#localCurrencyRequirementText").val(accountJSON.localCurrencyRequirementText);
                this.$el.find("#stateReinsurInvolv").val(accountJSON.stateReinsurInvolv);
                this.$el.find("#stateReinsurInvolvText").val(accountJSON.stateReinsurInvolvText);
                this.$el.find("#accounting").val(accountJSON.accounting);
                this.$el.find("#generalComments").val(accountJSON.generalComments);
                this.$el.find("#generalAttachment").val(accountJSON.generalAttachment);
                this.$el.find("#policyFormsWordingAttach").val(accountJSON.policyFormsWordingAttach);
            }
        }
        this.registerClickEvents();
        this.validateDetails();


    },
    registerClickEvents:function(){

      this.validateDetails();
      this.saveDetails();
    },
    validateDetails:function(){
        var _self=this;
        require(["jquery.validate"],function(){
            _self.$el.find("#saveAccountInfo").ready(function() {
                if (_self.$el.find("#saveAccountInfo").length > 0){
                    _self.$el.find("#saveAccountInfo").validate({
                        invalidHandler: function(e, validator) {},
                        rules: {
                                countryList:"required",
                                authLobLimit:"required",
                                minimumPremium:"required",
                                authLobCurrency:"required",
                                authLobpPremiumReverse:"required",
                                comEstablishmentYear:"required",
                                lobTariffComment:"required",
                                reInsurancecCommission:"required",
                                lobMandatoryClauses:"required",
                                requiredDocReinsurPlace:"required",
                                specReqDocReinsurPlace:"required",
                         },
                        submitHandler: function(form) {
                            $(".loading-icon-wrapper").show();
                            $("body").css({opacity:0.5});
                            $.ajax({
                                type: 'POST',
                                url: form.action,
                                data: _self.$el.find('#saveAccountInfo').serialize(),
                                error: function() {
                                    setTimeout(function(){
                                     $(".loading-icon-wrapper").hide();
                                     $("body").css({opacity:1})
                                   }, 3000);
                                },
                                success: function(accountJSON) {
                                    if(accountJSON){
                                       _self.$el.find("#accountId").val(accountJSON.id);
                                    }
                                    setTimeout(function(){
                                        $(".loading-icon-wrapper").hide();
                                        $("body").css({opacity:1});
                                        var nextStep=$(".lm-registration-step.lm-registration-step-selected").attr("nextStep");
                                        $(".lm-registration-step:eq("+nextStep+")").trigger("click");
                                      }, 2000);
                                }
                            });
                        }
                    });
                }
            });
        });
    },
    saveDetails:function(){
        this.$el.find("#saveAccountInfo").submit();
    }
});