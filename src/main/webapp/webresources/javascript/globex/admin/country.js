var CountryDetailView =Backbone.View.extend({
    model:CountryModel,
    events: {
        "click .cancel-button":"cancel"
    },
    render: function(){
        var _self=this;
        require(['text!'+'templates/common/country_details.html'], function(country_details) {
            var variables ={
                    id:"",country:"",nonAdmittedAllowed:"",nonAdmittedComments:"",
                    retailBorkerRequired:"",retailBrokerComments:"",reInsuranceBrokerRequired:"",
                    reInsuranceBrokerComments:"",mandatoryReInsuranceCession:"",mandatoryReInsuranceComments:"",stateSidePremiumAllowed:"",stateSidePremiumComments:"",
                    otherAccRequirements:"",premiumReserve:"",taxes:"",vat:"",reInsuranceTax:"",
                    otherRequirements:"",policyLanguage:"",tacitRenewal:"",tacticalRenewalComments:"",generalComments:"",createdBy:""
               };
            if(_self.model){
                variables = {
                            id:_self.model.get("id"),country:_self.model.get("country"),nonAdmittedAllowed:_self.model.get("nonAdmittedAllowed"),nonAdmittedComments:_self.model.get("nonAdmittedComments"),
                            retailBorkerRequired:_self.model.get("retailBorkerRequired"),retailBrokerComments:_self.model.get("retailBrokerComments"),reInsuranceBrokerRequired:_self.model.get("reInsuranceBrokerRequired"),
                            reInsuranceBrokerComments:_self.model.get("reInsuranceBrokerComments"),mandatoryReInsuranceCession:_self.model.get("mandatoryReInsuranceCession"),
                            mandatoryReInsuranceComments:_self.model.get("mandatoryReInsuranceComments"),
                            stateSidePremiumAllowed:_self.model.get("stateSidePremiumAllowed"),stateSidePremiumComments:_self.model.get("stateSidePremiumComments"),
                            otherAccRequirements:_self.model.get("otherAccRequirements"),premiumReserve:_self.model.get("premiumReserve"),
                            taxes:_self.model.get("taxes"),vat:_self.model.get("vat"),reInsuranceTax:_self.model.get("reInsuranceTax"),
                            otherRequirements:_self.model.get("otherRequirements"),policyLanguage:_self.model.get("policyLanguage"),tacitRenewal:_self.model.get("tacitRenewal"),
                            tacticalRenewalComments:_self.model.get("tacticalRenewalComments"),generalComments:_self.model.get("generalComments"),
                            createdBy:_self.model.get("createdBy")
                       };
            }
             country_details = _.template( country_details, variables );
            _self.$el.html(country_details);
            _self.validateDetails();
        });
    },
    validateDetails:function(){
        var _self=this;
        require(["jquery.validate"],function(){
            _self.$el.find("#saveCountryDetails").validate({
                invalidHandler: function(e, validator) {},
                submitHandler: function(form) {
                    $(".loading-icon-wrapper").show();
                    $("body").css({opacity:0.5});

                    var countryJson={};
                    countryJson['countryName=']_self.$el.find("[name='country']").val();
                    countryJson['territoryComments']=_self.$el.find("[name='territoryComments']").val();

                    var $taxRows= _self.$el.find("#taxWrapper .tax-row");
                    var taxJsonArray=[];
                    for(var i=0;i<$taxRows.length;i++){
                        var taxRow=$($taxRows[i]);
                        var taxTypeJson={};
                        taxTypeJson['taxType']=taxRow.find("[name='taxType']").val();
                        taxTypeJson['lob']=taxRow.find("[name='lob']").val();
                        taxTypeJson['percent']=taxRow.find("[name='percent']").val();
                        taxTypeJson['appliedTo']=taxRow.find("[name='appliedTo']").val();
                        taxTypeJson['amount']=taxRow.find("[name='amount']").val();
                        taxTypeJson['currency']=taxRow.find("[name='currency']").val();
                        taxTypeJson['resposiblility']=taxRow.find("[name='resposiblility']").val();
                        taxJsonArray.push(taxTypeJson);
                    }

                    var $taxRequirements=_self.$el.find("#taxRequirements .taxRequirement");
                    var taxRequirementJsonArray=[];
                    for(var i=0;i<$taxRequirements.length;i++){
                        var $taxRequirement=$($taxRequirements[i]);
                        var taxRequirementJson={};
                        taxRequirementJson['requirementName']=taxRow.find("[name='requirementName']").val();
                        taxRequirementJson['lob']=taxRow.find("[name='lob']").val();
                        taxRequirementJson['rate']=taxRow.find("[name='rate']").val();
                        taxRequirementJson['appliedTo']=taxRow.find("[name='appliedTo']").val();
                        taxRequirementJson['minPremium']=taxRow.find("[name='minPremium']").val();
                        taxRequirementJson['currency']=taxRow.find("[name='currency']").val();
                        taxRequirementJson['reqType']=taxRow.find("[name='reqType']").val();
                        taxRequirementJsonArray.push(taxRequirementJson);
                    }

                    var $clauses=_self.$el.find("#clauses .clause");
                    var clausesJsonArray=[];
                    for(var i=0;i<$taxRequirements.length;i++){
                        var $clause=$($clauses[i]);
                        var clauseJson={};
                        clauseJson['name']=$clause.find("[name='name']").val();
                        clauseJson['lob']=$clause.find("[name='lob']").val();
                        clauseJson['comments']=$clause.find("[name='comments']").val();
                        clausesJsonArray.push(clauseJson);
                    }

                    countryJson['locCurOnLocPol']=_self.$el.find("[name='locCurOnLocPol']").val();
                    countryJson['locCurOnLocPolComments']=_self.$el.find("[name='locCurOnLocPolComments']").val();
                    countryJson['foreignLawOnLocalPolicy']=_self.$el.find("[name='foreignLawOnLocalPolicy']").val();
                    countryJson['foreignLawOnLocalPolicyComments']=_self.$el.find("[name='foreignLawOnLocalPolicyComments']").val();
                    countryJson['useManuScript']=_self.$el.find("[name='useManuScript']").val();
                    countryJson['manuScriptLOB']=_self.$el.find("[name='manuScriptLOB']").val();
                    countryJson['manuScriptComments']=_self.$el.find("[name='manuScriptComments']").val();
                    countryJson['reInsuranceSupport']=_self.$el.find("[name='reInsuranceSupport']").val();
                    countryJson['reInsuranceSupportLOB']=_self.$el.find("[name='reInsuranceSupportLOB']").val();
                    countryJson['reInsuranceSupportComments']=_self.$el.find("[name='reInsuranceSupportComments']").val();
                    countryJson['foreignReinsurerRegistered']=_self.$el.find("[name='foreignReinsurerRegistered']").val();
                    countryJson['foreignReinsurerRegisteredComments']=_self.$el.find("[name='foreignReinsurerRegisteredComments']").val();
                    countryJson['foreignReinsurerRegisteredAdvice']=_self.$el.find("[name='foreignReinsurerRegisteredAdvice']").val();
                    countryJson['infoReqdForPolicyInsurance']=_self.$el.find("[name='infoReqdForPolicyInsurance']").val();
                    countryJson['premiumCollectionType']=_self.$el.find("[name='premiumCollectionType']").val();
                    countryJson['nonAdmittedAllowed']=_self.$el.find("[name='nonAdmittedAllowed']").val();
                    countryJson['nonAdmittedComments']=_self.$el.find("[name='nonAdmittedComments']").val();
                    countryJson['mandatoryReInsuranceCession']=_self.$el.find("[name='mandatoryReInsuranceCession']").val();
                    countryJson['mandatoryReInsuranceComments']=_self.$el.find("[name='mandatoryReInsuranceComments']").val();
                    countryJson['tacitRenewal']=_self.$el.find("[name='tacitRenewal']").val();
                    countryJson['tacticalRenewalComments']=_self.$el.find("[name='tacticalRenewalComments']").val();
                    countryJson['cashBeforeCoverReq']=_self.$el.find("[name='cashBeforeCoverReq']").val();
                    countryJson['cashBeforeCoverReqComments']=_self.$el.find("[name='cashBeforeCoverReqComments']").val();
                    countryJson['localCurrencyReq']=_self.$el.find("[name='localCurrencyReq']").val();
                    countryJson['localCurrencyReqComments']=_self.$el.find("[name='localCurrencyReqComments']").val();
                    countryJson['stateReinsurerReqLOB']=_self.$el.find("[name='stateReinsurerReqLOB']").val();
                    countryJson['stateReinsurerReq']=_self.$el.find("[name='stateReinsurerReq']").val();
                    countryJson['stateReinsurerReqComments']=_self.$el.find("[name='stateReinsurerReqComments']").val();
                    countryJson['otherRequirements']=_self.$el.find("[name='otherRequirements']").val();
                    countryJson['generalComments']=_self.$el.find("[name='generalComments']").val();



                    /*var form=document.getElementById("saveCountryDetails");
                    var formData=new FormData(form);*/
                    var formData=$('#saveCountryDetails').serialize();
                    $.ajax({
                        type: 'POST',
                        url: form.action,
                        data: formData,
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
    cancel:function(e){
         e.preventDefault();
         $(".navigate-manage-country").trigger("click");
    }
});


var CountryCollection=Backbone.Collection.extend({
	url: "/secure/viewCountries",
});

CountryListView =Backbone.View.extend({
    el:"#layout-body-content",
    tagName:'div',
    className:'table-container',
    headerTemplate:'<thead><tr class="header-row"> <th class="header-column">Country<div class="header-column-sort"></div></th>  <th class="header-column">Reinsurance Comments<div class="header-column-sort"></div></th> <th class="header-column"><div class="header-column-text">Non-Admitted Comments</div><div class="header-column-sort"></div></th>'+
                    '<th class="header-column">Taxes<div class="header-column-sort"></div></th> <th class="header-column">VAT<div class="header-column-sort"></div></th> <th class="header-column">Reinsurance Tax<div class="header-column-sort"></div></th> <th class="header-column">View</th> <th class="header-column">Delete</th> </tr></thead>',
    events: {
        "click #createCountry":"addCountry"
    },
    render: function(){
        var $self=this;
        var pageNo=$self.pageNo;
        var pageData={pageNo:pageNo};
        var countryCollection=new CountryCollection();
        countryCollection.fetch({
            data: pageData,
            type: 'POST',
            success: function(collection, response){
                $self.pageNo=response.pageNo;
                $self.$el.empty();

                var $country_list_container=$("<div/>" , {
                     "id":"country_list_container",
                     html:'<div class=""><div class="btn-wrapper"> <div id="createCountry" class="add-user-button"><span class="add-button"></span> <span class="add-country-text" >Add Country </span></div></div></div>'
                 });

                var $country_table=$("<table />" , {
                    "class": "table-container",
                    "id": "table-container",
                    html:$self.headerTemplate
                });
                $self.$el.html($country_list_container);
                $country_list_container.append($country_table);
                var countries=response.countries;
                var index=0;
                _.each(countries,function(country){
                   index++;
                   var rowClass=(index%2)==0?"table-column-even":"table-column-odd";
                    var countryModel = new CountryModel({
                        rowClass:rowClass,
                        countryId:country.id,
                        country:country.country,
                        mandatoryReInsuranceComments:country.mandatoryReInsuranceComments,
                        nonAdmittedComments:country.nonAdmittedComments,
                        taxes:country.taxes,
                        vat:country.vat,
                        reInsuranceTax:country.reInsuranceTax
                    });
                    var countryView = new CountryView({model: countryModel});
                    countryView.render();
                    $country_table.append(countryView.$el);

                },this);
             var pagingModel=new PagingModel({currentPage:response.pageNo,totalRecords:response.totalRecords});
             var pagingView=new PagingView({model:pagingModel});
             pagingView.pageContext=$self;
             pagingView.$pageContextEl=$self.$el.find("#country_list_container");
             pagingView.render();
            }
        });
    },
     addCountry:function(){
         var countryDetailView=new CountryDetailView({el:"#layout-body-content"});
         this.$el.empty();
         countryDetailView.render();
     }
});

var CountryModel=Backbone.Model.extend({
    defaults: {
        status:'Active'
    },
    url:"/secure/editCountry"
});

var CountryView =Backbone.View.extend({
    tagName:"tr",
    className:"table-row",
    model : CountryModel,
    events:{
        "click .edit-country":"editCountry",
        "click .delete-country":"deleteCountry"
    },
    countryTemplate:'<td class="table-column"><p class="table-column-text"><%=country%></p></td>  <td class="table-column"><p class="table-column-text"><%=mandatoryReInsuranceComments%></p></td> <td class="table-column"><p class="table-column-text"><%=nonAdmittedComments%></p></td>'+
                 '<td class="table-column"><p class="table-column-text"><%=taxes%></p></td> <td class="table-column"><p class="table-column-text"><%=vat%></p></td> <td class="table-column"><p class="table-column-text"><%=reInsuranceTax%></p></td>'+
                 '<td class="table-column"><div class="edit-icon edit-country"></div></td> <td class="table-column"><div class="delete-icon delete-country"></div></td>',
    initialize: function(){},
    render: function(){
        var variables = {country:this.model.get("country"),mandatoryReInsuranceComments:this.model.get("mandatoryReInsuranceComments"),nonAdmittedComments:this.model.get("nonAdmittedComments"),
                        taxes:this.model.get("taxes"),vat:this.model.get("vat"),reInsuranceTax:this.model.get("reInsuranceTax")};
        var template = _.template( this.countryTemplate, variables );
        var rowClass=this.model.get("rowClass");
        this.$el.addClass(rowClass);
        this.$el.append($(template));
    },
    editCountry:function(){
        var countryId=this.model.get("countryId");
        $.ajax({
            type: 'POST',
            url: '/secure/getCountryDetails',
            data: {
                countryId : countryId
            },
            context: this,
            error: function() {},
            success: function(country) {
               var countryModel = new CountryModel({
                   id:country.id,country:country.country,nonAdmittedAllowed:country.nonAdmittedAllowed,nonAdmittedComments:country.nonAdmittedComments,
                   retailBorkerRequired:country.retailBorkerRequired,retailBrokerComments:country.retailBrokerComments,reInsuranceBrokerRequired:country.reInsuranceBrokerRequired,
                   reInsuranceBrokerComments:country.reInsuranceBrokerComments,mandatoryReInsuranceCession:country.mandatoryReInsuranceCession,
                   mandatoryReInsuranceComments:country.mandatoryReInsuranceComments,
                   stateSidePremiumAllowed:country.stateSidePremiumAllowed,stateSidePremiumComments:country.stateSidePremiumComments,
                   otherAccRequirements:country.otherAccRequirements,premiumReserve:country.premiumReserve,
                   taxes:country.taxes,vat:country.vat,reInsuranceTax:country.reInsuranceTax,
                   otherRequirements:country.otherRequirements,policyLanguage:country.policyLanguage,tacitRenewal:country.tacitRenewal,
                   tacticalRenewalComments:country.tacticalRenewalComments,generalComments:country.generalComments,
                   createdBy:country.createdBy
               });

                var popupView=new PopupView({el:"#popupWrapper"});
                popupView.render();
                popupView.$el.find("#popup-title").html("Country Details");
                var countryPopupView = new CountryPopupView({el:"#popup-content",model: countryModel});
                countryPopupView.render();
            }
        });
    },
    deleteCountry:function(){
          var _self=this;
          var countryId=this.model.get("countryId");

          $(".loading-icon-wrapper").show();
          $("body").css({opacity:0.5});
          $.ajax({
              type: 'POST',
              url: '/secure/deleteCountry',
              data: {
                  countryId : countryId
              },
              context: this,
              error: function() {},
              success: function(htmlData) {
                  $(".navigate-manage-country").trigger("click");
                  _self.$el.empty();
                  $(".loading-icon-wrapper").hide();
                  $("body").css({opacity:1});
              }
          });
    }
});


/**popup view to display Country**/
var CountryPopupView =Backbone.View.extend({
    model:CountryModel,
    events: {
        "click .cancel-button":"cancel"
    },
    render: function(){

        var _self=this;
        require(['text!'+'templates/common/country_edit_view.html'], function(country_details) {

            var variables =
            {
                id:_self.model.get("id"),country:_self.model.get("country"),nonAdmittedAllowed:_self.model.get("nonAdmittedAllowed"),nonAdmittedComments:_self.model.get("nonAdmittedComments"),
                retailBorkerRequired:_self.model.get("retailBorkerRequired"),retailBrokerComments:_self.model.get("retailBrokerComments"),reInsuranceBrokerRequired:_self.model.get("reInsuranceBrokerRequired"),
                reInsuranceBrokerComments:_self.model.get("reInsuranceBrokerComments"),mandatoryReInsuranceCession:_self.model.get("mandatoryReInsuranceCession"),
                mandatoryReInsuranceComments:_self.model.get("mandatoryReInsuranceComments"),
                stateSidePremiumAllowed:_self.model.get("stateSidePremiumAllowed"),stateSidePremiumComments:_self.model.get("stateSidePremiumComments"),
                otherAccRequirements:_self.model.get("otherAccRequirements"),premiumReserve:_self.model.get("premiumReserve"),
                taxes:_self.model.get("taxes"),vat:_self.model.get("vat"),reInsuranceTax:_self.model.get("reInsuranceTax"),
                otherRequirements:_self.model.get("otherRequirements"),policyLanguage:_self.model.get("policyLanguage"),tacitRenewal:_self.model.get("tacitRenewal"),
                tacticalRenewalComments:_self.model.get("tacticalRenewalComments"),generalComments:_self.model.get("generalComments"),
                createdBy:_self.model.get("createdBy")
           };
            var template = _.template( country_details, variables );

            _self.$el.html(template);
            /*popupView.$el.find("#popup-content").append(template);*/
            _self.validateDetails();
        });
    },
    validateDetails:function(){
            var _self=this;
            require(["jquery.validate"],function(){
                _self.$el.find("#saveCountryDetails").validate({
                    invalidHandler: function(e, validator) {},
                    submitHandler: function(form) {
                        $(".loading-icon-wrapper").show();
                        $("body").css({opacity:0.5});

                        var formData=$('#saveCountryDetails').serialize();
                        $.ajax({
                            type: 'POST',
                            url: form.action,
                            data: formData,
                            error: function() {
                                setTimeout(function(){
                                 $(".loading-icon-wrapper").hide();
                                 $("body").css({opacity:1})
                               }, 3000);
                            },
                            success: function(userJSON) {
                                $(".navigate-manage-country").trigger("click");
                                $(".popup-close").trigger("click");
                                _self.$el.empty();
                                $(".loading-icon-wrapper").hide();
                                $("body").css({opacity:1});
                            }
                        });
                    },
                });
            });
    },
    cancel:function(e){
          e.preventDefault();
          $(".popup-close").trigger("click");
          $(".navigate-manage-country").trigger("click");
      }
});
/**popup view to display Country**/
