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

           var exposureListView=new ExposureListView({el:$el,collection:exposureCollection});
           exposureListView.render();
       }
    },
    renderLocalInsuranceDetails:function(e){

    }
  });

   var ExposureListView=Backbone.View.extend({
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
                        exposure.country=_self.$el.find("#country").val();
                        exposure.states=_self.$el.find("#states").val();
                        exposure.exposure=_self.$el.find("#exposure").val();
                        exposure.rate=_self.$el.find("#rate").val();

                        var exposureModel=new ExposureModel({
                           country:exposure.country,
                           states:exposure.states,
                           exposure:exposure.exposure,
                           rate:exposure.rate
                        });
                        collection.add(exposureModel);
                    }
               });
            });
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

     render:function(){
        var localInsuredContacts=this.options.localInsuredContacts;
        require(['text!'+'templates/pm/local_insured_contact_details.html'], function(local_insured_contact_details) {
            var template=_.template(local_insured_contact_details,localInsuredContacts);
            var popupView=new PopupView({el:"#popupWrapper"});
            popupView.render();
            popupView.$el.find("#popup-title").html("Local Insured Contact Details");
            popupView.$el.find("#popup-content").empty();
            popupView.$el.find("#popup-content").html(template);
        });
     }
});