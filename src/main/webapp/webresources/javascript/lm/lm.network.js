LMNetworkView = Backbone.View.extend({
    el: '.lm-registration-step3',
    render: function(){
        this.$el.find( "#vettingDate" ).datepicker({dateFormat:'dd/mm/yy'});
        this.$el.find( "#vettingReminder" ).datepicker({dateFormat:'dd/mm/yy'});
        var lmUserRegistrationJSONData= $("#lmUserRegistrationJSONData script").html();
        if(lmUserRegistrationJSONData){
            var lmUserRegistrationJSON = eval('(' + lmUserRegistrationJSONData +')');
            var networkJSON=lmUserRegistrationJSON.network;
            if(networkJSON){
                this.$el.find("#networkId").val(networkJSON.id);
                this.$el.find("[name='networkPartnerQualifier']").val(networkJSON.networkPartnerQualifier);
                this.$el.find("[name='networkPartnerQuaLob']").val(networkJSON.networkPartnerQuaLob);
                this.$el.find("#generalComments").val(networkJSON.generalComments);
                this.$el.find("#networkPartnerScore").val(networkJSON.networkPartnerScore);
                this.$el.find("#averageResponseTime").val(networkJSON.averageResponseTime);
                this.$el.find("#primaryContactComments").val(networkJSON.primaryContactComments);
                this.$el.find("[name='vettingStatus']").val(networkJSON.vettingStatus);
                this.$el.find("#vettingStatusComments").val(networkJSON.vettingStatusComments);
                var vettingDate=new Date(networkJSON.vettingDate);
                this.$el.find("#vettingDate").datepicker("setDate",vettingDate);
                var vettingReminder=new Date(networkJSON.vettingReminder);
                this.$el.find("#vettingReminder").datepicker("setDate" ,vettingReminder);
                this.$el.find("#vettingReminderComments").val(networkJSON.vettingReminderComments);
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
            _self.$el.find("#saveNetworkInfo").ready(function() {
                if (_self.$el.find("#saveNetworkInfo").length > 0){
                    _self.$el.find("#saveNetworkInfo").validate({
                        invalidHandler: function(e, validator) {},
                        rules: {
                            "networkPartnerQualifier":"required",
                            "networkPartnerQuaLob":"required",
                            "generalComments":"required",
                            "networkPartnerScore":"required",
                            "averageResponseTime":"required",
                            "primaryContactComments":"required",
                            "vettingStatus":"required",
                            "vettingStatusComments":"required",
                            "vettingDate":"required",
                            "vettingReminder":"required",
                            "vettingReminderComments":"required"
                        },
                        submitHandler: function(form) {
                            $(".loading-icon-wrapper").show();
                            $("body").css({opacity:0.5});
                            $.ajax({
                                type: 'POST',
                                url: form.action,
                                data: _self.$el.find('#saveNetworkInfo').serialize(),
                                error: function() {
                                    setTimeout(function(){
                                        $(".loading-icon-wrapper").hide();
                                        $("body").css({opacity:1})
                                      }, 3000);
                                },
                                success: function(networkJSON) {
                                        if(networkJSON){
                                           _self.$el.find("#orgId").val(networkJSON.id);
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
        this.$el.find("#saveNetworkInfo").submit();
    }
});