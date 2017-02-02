
LMUserRegistrationView = Backbone.View.extend({
    el: '.layout-body',
    initialize: function(){

    },
    events: {
        'click .ls-registration-step-save' :'saveUserDetails',
        'click .lm-registration-step':'showRegistrationStep',
        'click .lm-menu-item':'showRegistrationStep',
        'click .lm-registration-body-section-toggle':'toggleFormSection',
    },
    render: function(){
       var _self=this;
       require(["lm/lm.org","lm/lm.banking","lm/lm.network","lm/lm.finance","lm/lm.account"],function(){
            _self.$el.find(".lm-registration-step:first").trigger('click');
       });
    },
    toggleFormSection:function(e){
        $(e.currentTarget).parent().next().toggle();
    },
    saveUserDetails:function(){
        this.stepView.saveDetails();
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
           this.stepView=new LMOrganizationView({ });
        }else if(group=='lm-finance-details'){
            this.stepView= new LMFinanceView({ });
        }else if(group=='lm-network-details'){
            this.stepView= new LMNetworkView({ });
         }else if(group=='lm-acount-details'){
            this.stepView= new LMAccountView({ });
         }else if(group=='lm-dept-details'){
            this.stepView= new LMBankingView({ });
         }

        $(".loading-icon-wrapper").show();
        $("body").css({opacity:0.5});
        // setTimeout(function(){
            $(".loading-icon-wrapper").hide();
            $("body").css({opacity:1})
       //   }, 2000);


        this.stepView.render();
    },
    validateUserDetails:function(){
         var _self=this;
	 require(["jquery.validate"],function(){
		_self.$el.find("#saveOrgInfo").ready(function() {
			if (_self.$el.find("#saveOrgInfo").length > 0){
				_self.$el.find("#saveOrgInfo").validate({
					invalidHandler: function(e, validator) {},
					/*rules: {
						orgName: {
							required: true,
							maxlength: 100,
							noSpecialChars: "^([a-zA-Z\\s])+$"
						},
						email: {
							required: true,
							email: true,
							noSpecialChars: "^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$",
							maxlength: 255
						}
					},
					messages: {
						orgName:{
							required: "<div class='validation-mes'>Please provide your Organization Name.</div>",
							maxlength: jQuery.format("The maximum allowed length is {0} characters."),
							noSpecialChars: "<div class='validation-mes'>No special characters allowed.</div>"
						},
						email: {
							required: "<div class='validation-mes' id='email-validation-mes'>Please enter a valid email address.</div>",
							email: "<div class='validation-mes' id='email-validation-mes'>Please enter a valid email address.</div>",
							noSpecialChars: "<div class='validation-mes'>Enter a valid email address.</div>",
							maxlength: jQuery.format("<div class='validation-mes'>The maximum allowed length is {0} characters.</div>")
						},
						phone:{
                            required: "<div class='validation-mes'>Please provide your phone no.</div>",
                            maxlength: jQuery.format("The maximum allowed length is {0} characters."),
                            noSpecialChars: "<div class='validation-mes'>No special characters allowed.</div>"
                        },
                        website:{
                            required: "<div class='validation-mes'>Please provide your website info.</div>",
                            maxlength: jQuery.format("The maximum allowed length is {0} characters."),
                            noSpecialChars: "<div class='validation-mes'>No special characters allowed.</div>"
                        },
						password: {
							required: "<div class='validation-mes'>Please provide a password.</div>",
							minlength: "<div class='validation-mes'>Password must be at least 5 characters.</div>",
							maxlength: jQuery.format("<div class='validation-mes'>The maximum allowed length is {0} characters.</div>")

						},
						passwordConf: {
							required: "<div class='validation-mes'>Please confirm the password.</div>",
							equalTo: "<div class='validation-mes'>Password didn't match</div>",
							minlength:"",
							maxlength: jQuery.format("<div class='validation-mes'>The maximum allowed length is {0} characters.</div>")
						},
					},*/
					submitHandler: function(form) {
						_self.$el.find('.update-status').hide();
						if($.trim($('#fname').val())==$.trim($('#prevUserName').val()) && $.trim($('#email').val())==$.trim($('#prevEmailId').val())
								&& $('#password').val()=="" && $('#passwordConf').val()=="" ){
							return;
						}
						$.ajax({
							type: 'POST',
							url: form.action,
							data: $('#saveOrgInfo').serialize(),
							error: function() {
								$('#save').removeAttr("disabled");
								$('.update-status').show().css('color','red').html('Fail to update');
							},
							success: function(htmlData) {
								if(htmlData != undefined && htmlData != null && htmlData != ''){
									if(htmlData=="Successful"){
										$('.update-status').show();
										$('#passwordConf').val("");
										$('#password').val("");
										$('.change-pwd-link').removeClass('change-pwd-link-style');
										$('.password-edit-details').hide();
										fullNameHtml=$('.user-name-section .static-text').html();
										$('.user-nav__username').html(fullNameHtml);
										$('.nav--dropped').attr("title",fullNameHtml);
										$('.update-status').show().css('color','green').html('successfully updated');
										$('#prevUserName').val($.trim($('#fname').val()));
										$('#prevEmailId').val($.trim($('#email').val()));
									}else{
										$('.update-status').show().css('color','red').html('Fail to update');
									}
								}
							}
						});
					}
				});
			}

		});
	});
 }
});