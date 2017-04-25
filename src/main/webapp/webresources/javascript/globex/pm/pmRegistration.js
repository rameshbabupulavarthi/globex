  RegistrationView=Backbone.View.extend({
    el:".layout-body",
    layoutTemplate:'<div class="page-content-section">'+
                      '<div class="lm-layout-wrapper">'+
                        '<div class="lm-registration-body">'+
                            '<div class="lm-registration-body-wrapper">'+

                               '<div class="accordian-header">'+
                                    '<div class="accordian-header-text"> File Application'+
                                    '</div>'+
                                    '<div class="accordian-toggle">'+
                                        '<span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>'+
                                    '</div>'+
                                '</div>'+
                                '<div class="accordian-body">'+
                                    '<div class="application-form">'+

                                    '</div>'+
                                '</div>'+
                            '</div>'+
                        '</div>'+
                      '</div>'+
                   '</div>',
    events:{
        'change #pmCoverage':'renderCoveragePage',
        'click  .pm-exposure-details':'renderExposureDetails',
        'click  .pm-broker-details':'renderLocalInsuranceDetails'
    },
    initialize:function(){
    },
    render:function(){
        var _self=this;
        _self.$el.find("#layout-body-content").empty();
        _self.$el.find("#layout-body-content").html(this.layoutTemplate);
        require(['text!'+'templates/pm/coverage/marine_coverage.html'], function(registrationForm) {
            var variables={
                fileId:"",
                fileStatus:"",
                senderName:"",
                orgName:"",
                insuredCompany:{},
                messageContent:"",
                prospect:prospect,
                organization:{},
                application:{}
            };
            if(_self.model){
                var fileId =_self.model.get("fileId");
                var fileStatus =_self.model.get("fileStatus");
                var senderName =_self.model.get("senderName");
                var orgName =_self.model.get("orgName");
                var insuredCompany =_self.model.get("insuredCompany");
                var dateCreated =_self.model.get("dateCreated");
                var senderImage =_self.model.get("senderImage");
                var messageContent = _self.model.get("messageContent");
                var insuredCompany =_self.model.get("insuredCompany");
                var prospect =_self.model.get("prospect");
                var organization =_self.model.get("organization");
                var application =_self.model.get("application");

                variables={
                    fileId:fileId,
                    fileStatus:fileStatus,
                    senderName:senderName,
                    orgName:orgName,
                    insuredCompany:insuredCompany,
                    messageContent:messageContent,
                    prospect:prospect,
                    organization:organization,
                    application:application
                };
             }
             var template=_.template(registrationForm,variables);
            _self.$el.find("#layout-body-content .application-form").html(template);
            _self.validateDetails();
            $(".date-picker").datepicker({ dateFormat: 'dd-mm-yy'});
        });
    },
    validateDetails:function(){
        var _self=this;
        require(["jquery.validate"],function(){
            _self.$el.find("#applicationForm").validate({
                invalidHandler: function(e, validator) {},
                submitHandler: function(form) {
                    $(".loading-icon-wrapper").show();
                    $("body").css({opacity:0.5});

                    var form=document.getElementById("saveCountryDetails");
                    var formData=new FormData(form);
                    /*var formData=$('#applicationForm').serialize();*/
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
                        success: function(userJSON) {
                            $(".navigate-manage-country").trigger("click");
                            _self.$el.empty();
                            $(".loading-icon-wrapper").hide();
                            $("body").css({opacity:1});
                        }
                    });
                }
            });
        });










    },
    renderCoveragePage:function(e){
        var _self=this;
        var coverageType=$(e.currentTarget).val();
        var coveragePage="marineRegistrationForm.html";
        if(coverageType==2){
           coveragePage="marineRegistrationForm.html";
        }else if(coverageType==3){
            coveragePage="propertyRegistrationForm.html";
        }
        require(['text!'+'templates/pm/'+coveragePage], function(registrationForm) {

            var variables={
                fileId:"",
                fileStatus:"",
                senderName:"",
                orgName:"",
                insuredCompany:{},
                messageContent:"",
                prospect:prospect,
                organization:{},
                application:{}
            };
            if(_self.model){
                var fileId =_self.model.get("fileId");
                var fileStatus =_self.model.get("fileStatus");
                var senderName =_self.model.get("senderName");
                var orgName =_self.model.get("orgName");
                var insuredCompany =_self.model.get("insuredCompany");
                var dateCreated =_self.model.get("dateCreated");
                var senderImage =_self.model.get("senderImage");
                var messageContent = _self.model.get("messageContent");
                var insuredCompany =_self.model.get("insuredCompany");
                var prospect =_self.model.get("prospect");
                var organization =_self.model.get("organization");
                var application =_self.model.get("application");

                variables={
                    fileId:fileId,
                    fileStatus:fileStatus,
                    senderName:senderName,
                    orgName:orgName,
                    insuredCompany:insuredCompany,
                    messageContent:messageContent,
                    prospect:prospect,
                    organization:organization,
                    application:application
                };
             }
             var template=_.template(registrationForm,variables);
            _self.$el.find("#layout-body-content .application-form").html(template);
        });
    },
    renderExposureDetails:function(e){
        var exposureCollection=new ExposureCollection();
        var application=this.model.get("application");
        var exposureList=application.exposureDatas;
        if(exposureList){
            for (var i = 0; i < exposureList.length; ++i) {
                var exposure=exposureList[i];
                var exposureModel=new ExposureModel({
                   country:exposure.country,
                   states:exposure.states,
                   exposure:exposure.exposure,
                   rate:exposure.rate
                });
                exposureCollection.add(exposureModel);
            }
            var popupView=new PopupView({el:"#popupWrapper"});
            popupView.render();
            popupView.$el.find("#popup-title").html("Exposure Data");
            popupView.$el.find("#popup-content").empty();
            var $el=popupView.$el.find("#popup-content");

           var exposureListView=new ExposureListView({el:$el,collection:exposureCollection,application:application});
           exposureListView.render();
       }
    },
    renderLocalInsuranceDetails:function(e){
        var localInsuranceCollection=new LocalInsuranceCollection();
        var application=this.model.get("application");
        var localBrokerInsuredContacts=application.localBrokerInsuredContacts;
        if(localBrokerInsuredContacts){
            for (var i = 0; i < localBrokerInsuredContacts.length; ++i) {
                var localBrokerInsuredContact=localBrokerInsuredContacts[i];
                var localInsuranceModel=new LocalInsuranceModel({
                   country:localBrokerInsuredContact.country,
                   contactName:localBrokerInsuredContact.contactName,
                   address:localBrokerInsuredContact.address,
                   phone:localBrokerInsuredContact.phone,
                   fax:localBrokerInsuredContact.fax,
                   email:localBrokerInsuredContact.email,
                   contactType:localBrokerInsuredContact.contactType
                });
                localInsuranceCollection.add(localInsuranceModel);
            }
            var popupView=new PopupView({el:"#popupWrapper"});
            popupView.render();
            popupView.$el.find("#popup-title").html("Local Insurance Contact Details");
            popupView.$el.find("#popup-content").empty();
            var $el=popupView.$el.find("#popup-content");

           var localInsuranceListView=new LocalInsuranceListView({el:$el,collection:localInsuranceCollection,application:application});
           localInsuranceListView.render();
       }
    }
  });

   var ExposureListView=Backbone.View.extend({
         events:{
            'click #addExposureDetails':'addRecord',
            'click .cancel-button':'cancel'
         },
         collection:ExposureCollection,
         render:function(){
            var _self=this;
            require(['text!'+'templates/pm/exposure_data.html'], function(exposure_data) {
                _self.$el.html(exposure_data);
                var element =_self.$el.find("#exposureList");
                _self.collection.forEach(function(item) {
                   var exposureView = new ExposureView({
                     el:element,
                     model: item
                   });
                   exposureView.render();
                });
                _self.validateDetails();

            });
         },
         validateDetails:function(){
            var collection= this.collection;
            var _self=this;
            require(["jquery.validate"],function(){
               _self.$el.find("#exposureForm").validate({
                    invalidHandler: function(e, validator) {},
                    submitHandler: function(form) {
                        var exposure={};
                        exposure.country=_self.$el.find("[name='country']").val();
                        exposure.states=_self.$el.find("[name='states']").val();
                        exposure.exposure=_self.$el.find("[name='exposure']").val();
                        exposure.rate=_self.$el.find("[name='rate']").val();

                        var exposureModel=new ExposureModel({
                           country:exposure.country,
                           states:exposure.states,
                           exposure:exposure.exposure,
                           rate:exposure.rate
                        });
                        collection.add(exposureModel);
                        _self.options.application.exposureDatas=collection.toJSON();
                        _self.render();
                    }
               });
            });
            var $el=_self.$el.find('.country-dropdown');
            countrySelect($el);
         },
         addRecord:function(){
            this.$el.find(".form-wrapper").removeClass("hidden-form");
         },
         cancel:function(){
            this.$el.find(".form-wrapper").addClass("hidden-form");
         }
    });

   var ExposureCollection = Backbone.Collection.extend({
          model: ExposureModel,
          initialize: function () {
              // This will be called when an item is added. pushed or unshifted
              /*this.on('add', function(model) {
                  console.log('something got added');
              });
              // This will be called when an item is removed, popped or shifted
              this.on('remove',  function(model) {
                  console.log('something got removed');
              });
              // This will be called when an item is updated
              this.on('change', function(model) {
                  console.log('something got changed');
              });*/
          },
    });

   var ExposureModel=Backbone.Model.extend({
   });

   var ExposureView=Backbone.View.extend({
    model:ExposureModel,
    template:'<tr class="table-row table-column-odd">'+
                  '<td class="table-column"><%=country%></td>'+
                  '<td class="table-column"><%=states%></td>'+
                  '<td class="table-column"><%=exposure%></td>'+
                  '<td class="table-column"><%=rate%></td>'+
              '</tr>',
        render:function(){
            var country=this.model.get("country");
            var states=this.model.get("states");
            var exposure=this.model.get("exposure");
            var rate=this.model.get("rate");

            var variables = {country:country,states:states,exposure:exposure,rate:rate};
            var template = _.template(this.template, variables );
            this.$el.append(template);
        }
    });

   var LocalInsuranceListView=Backbone.View.extend({
         events:{
           'click #addInsuranceContact':'addRecord',
           'click .cancel-button':'cancel'
         },
         collection:LocalInsuranceCollection,
         render:function(){
            var _self=this;
            require(['text!'+'templates/pm/local_insured_contact_details.html'], function(local_insured_contact_details) {
                _self.$el.html(local_insured_contact_details);
                var element =_self.$el.find("#localInsuranceContactList");
                _self.collection.forEach(function(item) {
                   var localInsuranceView = new LocalInsuranceView({
                     el:element,
                     model: item
                   });
                   localInsuranceView.render();
                });
                _self.validateDetails();
            });
         },
         validateDetails:function(){
            var collection= this.collection;
            var _self=this;
            require(["jquery.validate"],function(){
               _self.$el.find("#localInsuranceContactForm").validate({
                    invalidHandler: function(e, validator) {},
                    submitHandler: function(form) {
                        var localInsuranceContact={};
                        localInsuranceContact.country=_self.$el.find("[name='country']").val();
                        localInsuranceContact.contactType=_self.$el.find("[name='contactType']").val();
                        localInsuranceContact.contactName=_self.$el.find("[name='contactName']").val();
                        localInsuranceContact.address=_self.$el.find("[name='address']").val();
                        localInsuranceContact.phoneCountryCode=_self.$el.find("[name='phoneCountryCode']").val();
                        localInsuranceContact.phoneAreaCode=_self.$el.find("[name='phoneAreaCode']").val();
                        localInsuranceContact.phone=_self.$el.find("[name='phone']").val();
                        localInsuranceContact.faxCountryCode=_self.$el.find("[name='faxCountryCode']").val();
                        localInsuranceContact.faxAreaCode=_self.$el.find("[name='faxAreaCode']").val();
                        localInsuranceContact.fax=_self.$el.find("[name='fax']").val();
                        localInsuranceContact.email=_self.$el.find("[name='email']").val();

                        var localInsuranceModel=new LocalInsuranceModel({
                           country:localInsuranceContact.country,
                           contactType:localInsuranceContact.contactType,
                           contactName:localInsuranceContact.contactName,
                           address:localInsuranceContact.address,
                           phoneCountryCode:localInsuranceContact.phoneCountryCode,
                           phoneAreaCode:localInsuranceContact.phoneAreaCode,
                           phone:localInsuranceContact.phone,
                           faxCountryCode:localInsuranceContact.faxCountryCode,
                           faxAreaCode:localInsuranceContact.faxAreaCode,
                           fax:localInsuranceContact.fax,
                           email:localInsuranceContact.email
                        });
                        collection.add(localInsuranceModel);
                        _self.options.application.localBrokerInsuredContacts=collection.toJSON();
                        _self.render();
                    }
               });
            });
            var $el=_self.$el.find('.country-dropdown');
            countrySelect($el);
         },
         addRecord:function(){
            this.$el.find(".form-wrapper").removeClass("hidden-form");
         },
        cancel:function(){
          this.$el.find(".form-wrapper").addClass("hidden-form");
        }
});

   var LocalInsuranceCollection = Backbone.Collection.extend({
          model: LocalInsuranceModel,
          initialize: function () {

          },
    });

   var LocalInsuranceModel=Backbone.Model.extend({
   });

   var LocalInsuranceView=Backbone.View.extend({
       model:ExposureModel,
       template:'<tr class="table-row table-column-odd">'+
                     '<td class="table-column"><%=country%></td>'+
                     '<td class="table-column"><%=contactName%></td>'+
                     '<td class="table-column"><%=address%></td>'+
                     '<td class="table-column"><%=phone%></td>'+
                     '<td class="table-column"><%=fax%></td>'+
                     '<td class="table-column"><%=email%></td>'+
                     '<td class="table-column"><%=contactType%></td>'+
                 '</tr>',
       render:function(){
           var country=this.model.get("country");
           var contactName=this.model.get("contactName");
           var address=this.model.get("address");
           var phone=this.model.get("phone");
           var fax=this.model.get("fax");
           var email=this.model.get("email");
           var contactType=this.model.get("contactType");

           var variables = {country:country,contactName:contactName,address:address,phone:phone,fax:fax,email:email,contactType:contactType};
           var template = _.template(this.template, variables );
           this.$el.append(template);
       }
   });


   function countrySelect($el){
        $el.select2({
             ajax: {
               url: "/secure/viewCountries",
               dataType: 'json',
               delay: 250,
               data: function (params) {
                 return {
                       country: params.term, // search term
                       pageNo: params.page,
                       pageSize:10
                  };
               },
               processResults: function (data, params) {
                 // parse the results into the format expected by Select2
                 // since we are using custom formatting functions we do not need to
                 // alter the remote JSON data, except to indicate that infinite
                 // scrolling can be used
                 params.pageNo = params.pageNo || 1;

                 return {
                   results: data.countries,
                   pagination: {
                     more: (params.pageNo * 10) < data.totalRecords
                   }
                 };
               },
               cache: true
             },
             escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
             minimumInputLength: 1,
             templateResult: formatCountryDisplay, // omitted for brevity, see the source of this page
             templateSelection: formatSelection // omitted for brevity, see the source of this page
           });

        function formatCountryDisplay(country){
            if(country.loading){
                return country.text;
            }
            var countrySearchTemplate= '<div class="search-user-card">'+
                                           '<div class="search-user-card">'+
                                                '<div class="">'+
                                                    '<span class="comment-detail-time"><%=country%></span>'+
                                               '</div>'+
                                           '</div>'+
                                        '</div>';
            var country=country.country;
            var variables = {country:country};
            var template = _.template(countrySearchTemplate, variables );
            return template;
        }
        function formatSelection(country){
           return country.country;
        }
   }