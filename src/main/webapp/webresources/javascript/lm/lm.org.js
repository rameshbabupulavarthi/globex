LMOrganizationView = Backbone.View.extend({
    el: '.lm-registration-step1',
    initialize: function(){
            //this.render();
     },
    events: {

     },
    render: function(){
        var lmUserRegistrationJSONData= $("#lmUserRegistrationJSONData script").html();
        if(lmUserRegistrationJSONData){
            var lmUserRegistrationJSON = eval('(' + lmUserRegistrationJSONData +')');
            var organizationJSON=lmUserRegistrationJSON.organization;
            if(organizationJSON){
                this.$el.find("#orgId").val(organizationJSON.id);
                this.$el.find("#orgName").val(organizationJSON.name);
                this.$el.find("#phone").val(organizationJSON.phone);
                this.$el.find("#website").val(organizationJSON.website);
                this.$el.find("#orgEmail").val(organizationJSON.email);
                this.$el.find("#addrLine1").val(organizationJSON.address);
                this.$el.find("#city").val(organizationJSON.city);
                this.$el.find("#state").val(organizationJSON.state);
                this.$el.find("#zipcode").val(organizationJSON.zipcode);
                this.$el.find("#country").val(organizationJSON.country);

                var contactJSON=organizationJSON.contact;
                this.$el.find("#orgContactId").val(contactJSON.id);
                this.$el.find("#firstName").val(contactJSON.firstName);
                this.$el.find("#email").val(contactJSON.email);
                this.$el.find("#lastName").val(contactJSON.lastName);
                this.$el.find("#mobileNumber").val(contactJSON.phone);
            }
        }
        this.registerClickEvents();
        this.validateDetails();
      },
    registerClickEvents:function(){
         this.$el.find("#country").autocomplete({
           source: function( request, response ) {
                   $.ajax( {
                     url: "/secure/getCountries",
                     dataType: "json",
                     data: {
                       mode: request.term
                     },
                     success: function( data ) {
                       response( data );
                     }
                   } );
                 },
             minLength: 2,
             select: function( event, ui ) {
               console.log( "Selected: " + ui.item.value + " aka " + ui.item.id );
             }
         });
      },
    validateDetails:function(){
        var _self=this;
        	 require(["jquery.validate"],function(){
        		_self.$el.find("#saveOrgInfo").ready(function() {
        			if (_self.$el.find("#saveOrgInfo").length > 0){
        				_self.$el.find("#saveOrgInfo").validate({
        					invalidHandler: function(e, validator) {},
        					rules: {
        					    name:"required",
        					    phone:"required",
        					    website:"required",
        					    address:"required",
        					    city:"required",
        					    state:"required",
        					    zipcode:"required",
        					    country:"required",
        					    "contact.firstName":"required",
        					    "contact.email":"required",
        					    "contact.lastName":"required",
        					    "contact.mobileNumber":"required",
        						orgName: {
        							required: true,
        							maxlength: 100,
        							noSpecialChars: "^([a-zA-Z\\s])+$"
        						},
        						/*email: {
        							required: true,
        							email: true,
        							noSpecialChars: "^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$",
        							maxlength: 255
        						},*/
        					},
        					messages: {
        						orgName:{
        							required: "<div class='validation-mes'>Please provide your Organization Name.</div>",
        							maxlength: jQuery.format("The maximum allowed length is {0} characters."),
        							noSpecialChars: "<div class='validation-mes'>No special characters allowed.</div>"
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
        						email: {
        							required: "<div class='validation-mes' id='email-validation-mes'>Please enter a valid email address.</div>",
        							email: "<div class='validation-mes' id='email-validation-mes'>Please enter a valid email address.</div>",
        							noSpecialChars: "<div class='validation-mes'>Enter a valid email address.</div>",
        							maxlength: jQuery.format("<div class='validation-mes'>The maximum allowed length is {0} characters.</div>")
        						},
        					},
        					submitHandler: function(form) {
                                $(".loading-icon-wrapper").show();
                                $("body").css({opacity:0.5});
        						$.ajax({
        							type: 'POST',
        							url: form.action,
        							data: _self.$el.find('#saveOrgInfo').serialize(),
        							error: function() {
                                        setTimeout(function(){
                                         $(".loading-icon-wrapper").hide();
                                         $("body").css({opacity:1})
                                       }, 3000);
        							},
        							success: function(organizationJSON) {
        								if(organizationJSON != undefined && organizationJSON != null && organizationJSON != ''){
                                            if(organizationJSON){
                                               _self.$el.find("#orgId").val(organizationJSON.id);
                                               _self.$el.find("#orgContactId").val(organizationJSON.contact.id);
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
        this.$el.find("#saveOrgInfo").submit();
    }

});