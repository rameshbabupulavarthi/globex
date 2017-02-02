LMBankingView = Backbone.View.extend({
    el: '.lm-registration-step5',
    render: function(){
        var lmUserRegistrationJSONData= $("#lmUserRegistrationJSONData script").html();
        if(lmUserRegistrationJSONData){
            var lmUserRegistrationJSON = eval('(' + lmUserRegistrationJSONData +')');
            var bankingJSON=lmUserRegistrationJSON.banking;
            if(bankingJSON){
                this.$el.find("#bankingId").val(bankingJSON.id);
                this.$el.find("#details").val(bankingJSON.details);
                this.$el.find("#address").val(bankingJSON.address);
                this.$el.find("#bank_name").val(bankingJSON.name);
                this.$el.find("#bank_iban").val(bankingJSON.iBan);
                this.$el.find("#bank_swift_code").val(bankingJSON.swiftCode);

                var contactJSON=bankingJSON.contact;
                this.$el.find("#bankingContactId").val(contactJSON.id);
                this.$el.find("#dept_name").val(contactJSON.fullName);
                this.$el.find("#dept_role").val(contactJSON.role);
                this.$el.find("#dept_comments").val(contactJSON.comments);
                this.$el.find("#dept_office").val(contactJSON.officeName);
                this.$el.find("#dept_gsm").val(contactJSON.phone);
                this.$el.find("#dept_email").val(contactJSON.email);
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
                _self.$el.find("#saveBankingInfo").ready(function() {
                    if (_self.$el.find("#saveBankingInfo").length > 0){
                        _self.$el.find("#saveBankingInfo").validate({
                            invalidHandler: function(e, validator) {},
                            rules: {
                                    "contact.fullName":"required",
                                    "contact.role":"required",
                                    "contact.comments":"required",
                                    "contact.officeName":"required",
                                    "contact.phone":"required",
                                    "contact.email":"required",
                                    "details":"required",
                                    "address":"required",
                                    "name":"required",
                                    "iBan":"required",
                                    "swiftCode":"required"
                                },
                            submitHandler: function(form) {
                                $(".loading-icon-wrapper").show();
                                $("body").css({opacity:0.5});
                                $.ajax({
                                    type: 'POST',
                                    url: form.action,
                                    data: _self.$el.find('#saveBankingInfo').serialize(),
                                    error: function() {

                                         setTimeout(function(){
                                             $(".loading-icon-wrapper").hide();
                                             $("body").css({opacity:1})
                                           }, 3000);
                                    },
                                    success: function(bankingJSON) {
                                        if(bankingJSON != undefined && bankingJSON != null && bankingJSON != ''){
                                            if(bankingJSON){
                                               _self.$el.find("#bankingId").val(bankingJSON.id);
                                               _self.$el.find("#bankingContactId").val(bankingJSON.contact.id);
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
    saveDetails:function(){
        this.$el.find("#saveBankingInfo").submit();
    }
});