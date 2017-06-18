var CountryDetailView =Backbone.View.extend({
    model:CountryModel,
    events: {
        "click .cancel-button":"cancel",
        "click #addTaxType":"addTaxType",
        "click #addRateRequirement":"addRateRequirement"
    },
    render: function(){
        var _self=this;
        require(['text!'+'templates/common/country_details.html'], function(country_details) {

            var taxes=[
                {"taxType":"Value Added Tax","taxLob":"","taxRate":"","taxAppliedTo":"","taxAmount":"","taxCurrency":"","taxResponsiblility":""},
                {"taxType":"Reinsurance Tax","taxLob":"","taxRate":"","taxAppliedTo":"","taxAmount":"","taxCurrency":"","taxResponsiblility":""},
                {"taxType":"Policy Issuance Fee","taxLob":"","taxRate":"","taxAppliedTo":"","taxAmount":"","taxCurrency":"","taxResponsiblility":""},
                {"taxType":"Stamp Duty","taxLob":"","taxRate":"","taxAppliedTo":"","taxAmount":"","taxCurrency":"","taxResponsiblility":""},
                {"taxType":"Solvency Tax","taxLob":"","taxRate":"","taxAppliedTo":"","taxAmount":"","taxCurrency":"","taxResponsiblility":""},
            ];

            var rateRequirements=[
                {"requirementLob":"","requirementRate":"","requirementAppliedTo":"","requirementMinPremium":"","requirementCurrency":"","requirementType":""},
            ];

            var clauses=[
                {"clauseName":"","clauseLob":"","clauseComments":""},
            ];

            var variables ={
                    countryId:"",country:"",territoryComments:"",locCurOnLocPol:"",
                    locCurOnLocPolComments:"",foreignLawOnLocalPolicy:"",foreignLawOnLocalPolicyComments:"",
                    useManuScript:"",manuScriptLOB:"",manuScriptComments:"",
                    reInsuranceSupport:"",reInsuranceSupportLOB:"",reInsuranceSupportComments:"",
                    foreignReinsurerRegistered:"",foreignReinsurerRegisteredComments:"",foreignReinsurerRegisteredAdvice:"",
                    infoReqdForPolicyInsurance:"",premiumCollectionType:"",nonAdmittedAllowed:"",nonAdmittedComments:"",
                    mandatoryReInsuranceCession:"",mandatoryReInsuranceComments:"",tacitRenewal:"",tacitRenewalReasons:"",
                    tacticalRenewalComments:"",cashBeforeCoverReq:"",cashBeforeCoverReqComments:"",localCurrencyReq:"",
                    localCurrencyReqComments:"",stateReinsurerReqLOB:"",stateReinsurerReq:"",stateReinsurerReqComments:"",
                    otherRequirements:"",generalComments:"",generalAttachment:"",insuRequiredDoc:"",
                    taxes:taxes,rateRequirements:rateRequirements,clauses:clauses,
                    stateReinsurerReqLOB:"",nonAdmittedLob:"",mandatoryReInsuranceLob:"",
            };
            if(_self.model){
               variables = {
                countryId:_self.model.get("countryId"),country:_self.model.get("country"),territoryComments:_self.model.get("territoryComments"),
                locCurOnLocPol:_self.model.get("locCurOnLocPol"),locCurOnLocPolComments:_self.model.get("locCurOnLocPolComments"),
                foreignLawOnLocalPolicy:_self.model.get("foreignLawOnLocalPolicy"),foreignLawOnLocalPolicyComments:_self.model.get("foreignLawOnLocalPolicyComments"),
                useManuScript:_self.model.get("useManuScript"),manuScriptLOB:_self.model.get("manuScriptLOB"),
                manuScriptComments:_self.model.get("manuScriptComments"),reInsuranceSupport:_self.model.get("reInsuranceSupport"),
                reInsuranceSupportLOB:_self.model.get("reInsuranceSupportLOB"),reInsuranceSupportComments:_self.model.get("reInsuranceSupportComments"),
                foreignReinsurerRegistered:_self.model.get("foreignReinsurerRegistered"),foreignReinsurerRegisteredComments:_self.model.get("foreignReinsurerRegisteredComments"),
                foreignReinsurerRegisteredAdvice:_self.model.get("foreignReinsurerRegisteredAdvice"),infoReqdForPolicyInsurance:_self.model.get("infoReqdForPolicyInsurance"),
                premiumCollectionType:_self.model.get("premiumCollectionType"),nonAdmittedAllowed:_self.model.get("nonAdmittedAllowed"),
                nonAdmittedComments:_self.model.get("nonAdmittedComments"),mandatoryReInsuranceCession:_self.model.get("mandatoryReInsuranceCession"),
                mandatoryReInsuranceComments:_self.model.get("mandatoryReInsuranceComments"),tacitRenewal:_self.model.get("tacitRenewal"),
                tacitRenewalReasons:_self.model.get("tacitRenewalReasons"),tacticalRenewalComments:_self.model.get("tacticalRenewalComments"),
                cashBeforeCoverReq:_self.model.get("cashBeforeCoverReq"),cashBeforeCoverReqComments:_self.model.get("cashBeforeCoverReqComments"),
                localCurrencyReq:_self.model.get("localCurrencyReq"),localCurrencyReqComments:_self.model.get("localCurrencyReqComments"),
                stateReinsurerReqLOB:_self.model.get("stateReinsurerReqLOB"),stateReinsurerReq:_self.model.get("stateReinsurerReq"),
                stateReinsurerReqComments:_self.model.get("stateReinsurerReqComments"),otherRequirements:_self.model.get("otherRequirements"),
                generalComments:_self.model.get("generalComments"),
                generalAttachment:_self.model.get("generalAttachment"),insuRequiredDoc:_self.model.get("insuRequiredDoc"),
                taxes:_self.model.get("taxes"),rateRequirements:_self.model.get("rateRequirements"),clauses:_self.model.get("clauses"),
                stateReinsurerReqLOB:_self.model.get("stateReinsurerReqLOB"),nonAdmittedLob:_self.model.get("nonAdmittedLob"),
                mandatoryReInsuranceLob:_self.model.get("mandatoryReInsuranceLob"),
              };
            }
             country_details = _.template( country_details, variables );
            _self.$el.html(country_details);

            var $selectBoxes=_self.$el.find("select");
            for(var i=0;i<$selectBoxes.length;i++){
                var $selectBox=$($selectBoxes[i]);
                var value=$selectBox.attr("value");
                if(value){
                    $selectBox.val(value);
                }
            }

            _self.validateDetails();
        });
    },
    validateDetails:function(){
        var _self=this;
        require(["jquery.validate"],function(){
                _self.$el.find("#saveCountryDetails").validate({
                invalidHandler: function(e, validator) {},
                rules: {
                   country:{required: true,maxlength: 255},
                   territoryComments:{required: true,maxlength: 255},
                   locCurOnLocPolComments:{maxlength: 255},
                   foreignLawOnLocalPolicyComments:{maxlength: 255},
                   manuScriptComments:{maxlength: 255},
                   reInsuranceSupportComments:{maxlength: 255},
                   foreignReinsurerRegisteredComments:{maxlength: 255},
                   foreignReinsurerRegisteredAdvice:{maxlength: 255},
                   infoReqdForPolicyInsurance:{maxlength: 255},
                   mandatoryReInsuranceComments:{maxlength: 255},
                   nonAdmittedComments:{maxlength: 255},
                   tacitRenewalReasons:{maxlength: 255},
                   tacticalRenewalComments:{maxlength: 255},
                   cashBeforeCoverReqComments:{maxlength: 255},
                   localCurrencyReqComments:{maxlength: 255},
                   stateReinsurerReqComments:{maxlength: 255},
                   otherRequirements:{maxlength: 255},
                   generalComments:{maxlength: 255},
                },
                submitHandler: function(form) {
                    $(".loading-icon-wrapper").show();
                    $("body").css({opacity:0.5});

                    var countryJson={};

                    var $taxRows= _self.$el.find("#taxWrapper .tax-row");
                    var taxJsonArray=[];
                    for(var i=0;i<$taxRows.length;i++){
                        var $taxRow=$($taxRows[i]);
                        var taxTypeJson={};
                        taxTypeJson['taxId']=$taxRow.find("[name='taxId']").val();
                        taxTypeJson['taxType']=$taxRow.find("[name='taxType']").val();
                        taxTypeJson['taxLob']=$taxRow.find("[name='taxLob']").val();
                        taxTypeJson['taxRate']=$taxRow.find("[name='taxRate']").val();
                        taxTypeJson['taxAppliedTo']=$taxRow.find("[name='taxAppliedTo']").val();
                        taxTypeJson['taxAmount']=$taxRow.find("[name='taxAmount']").val();
                        taxTypeJson['taxCurrency']=$taxRow.find("[name='taxCurrency']").val();
                        taxTypeJson['taxResponsiblility']=$taxRow.find("[name='taxResponsiblility']").val();
                        taxJsonArray.push(taxTypeJson);
                    }

                    var $taxRequirements=_self.$el.find("#taxRequirements .taxRequirement");
                    var taxRequirementJsonArray=[];
                    for(var i=0;i<$taxRequirements.length;i++){
                        var $taxRequirement=$($taxRequirements[i]);
                        var taxRequirementJson={};
                        taxRequirementJson['requirementId']=$taxRequirement.find("[name='requirementId']").val();
                        taxRequirementJson['requirementName']=$taxRequirement.find("[name='requirementName']").val();
                        taxRequirementJson['requirementLob']=$taxRequirement.find("[name='requirementLob']").val();
                        taxRequirementJson['requirementRate']=$taxRequirement.find("[name='requirementRate']").val();
                        taxRequirementJson['requirementAppliedTo']=$taxRequirement.find("[name='requirementAppliedTo']").val();
                        taxRequirementJson['requirementMinPremium']=$taxRequirement.find("[name='requirementMinPremium']").val();
                        taxRequirementJson['requirementCurrency']=$taxRequirement.find("[name='requirementCurrency']").val();
                        taxRequirementJson['requirementType']=$taxRequirement.find("[name='requirementType']").val();
                        taxRequirementJsonArray.push(taxRequirementJson);
                    }

                    var $clauses=_self.$el.find("#clauses .clause");
                    var clausesJsonArray=[];
                    for(var i=0;i<$clauses.length;i++){
                        var $clause=$($clauses[i]);
                        var clauseJson={};
                        clauseJson['clauseId']=$clause.find("[name='clauseId']").val();
                        clauseJson['clauseName']=$clause.find("[name='clauseName']").val();
                        clauseJson['clauseLob']=$clause.find("[name='clauseLob']").val();
                        clauseJson['clauseComments']=$clause.find("[name='clauseComments']").val();
                        clausesJsonArray.push(clauseJson);
                    }
                    /*
                    countryJson['countryId']=_self.$el.find("[name='countryId']").val();
                    countryJson['country']=_self.$el.find("[name='country']").val();
                    countryJson['territoryComments']=_self.$el.find("[name='territoryComments']").val();
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
                    countryJson['generalComments']=_self.$el.find("[name='generalComments']").val();*/

                    countryJson.taxTypesJsonStr=JSON.stringify(taxJsonArray);
                    countryJson.taxRequirementsJsonStr= JSON.stringify(taxRequirementJsonArray);
                    countryJson.clausesJsonStr=JSON.stringify(clausesJsonArray);
                    //var formData=countryJson;

                    var formData=new FormData(form);
                    formData.append('taxTypesJsonStr',countryJson.taxTypesJsonStr);
                    formData.append('taxRequirementsJsonStr',countryJson.taxRequirementsJsonStr);
                    formData.append('clausesJsonStr',countryJson.clausesJsonStr);
                    /*var form=document.getElementById("saveCountryDetails");
                    */
                    //var formData=$('#saveCountryDetails').serialize();
                    $.ajax({
                        type: 'POST',
                        /*dataType : "json",*/
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
    cancel:function(e){
         e.preventDefault();
         $(".navigate-manage-country").trigger("click");
    },
    addTaxType:function(){
        var _self=this;
        require(['text!'+'templates/common/tax_type.html'], function(tax_type) {
            _self.$el.find("#taxTypeBody").append(tax_type);
        });
    },
    addRateRequirement:function(){
        var _self=this;
        require(['text!'+'templates/common/rate_requirement.html'], function(rate_requirement) {
            _self.$el.find("#taxRequirementBody").append(rate_requirement);
        });
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
                    '<th class="header-column">Accounting or Other Requirements<div class="header-column-sort"></div></th> <th class="header-column">Cash Before Cover requirement<div class="header-column-sort"></div></th> <th class="header-column">Tacit Renewal<div class="header-column-sort"></div></th> <th class="header-column">Edit</th> <th class="header-column">Delete</th> </tr></thead>',
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
                        countryId:country.countryId,
                        country:country.country,
                        mandatoryReInsuranceComments:country.mandatoryReInsuranceComments,
                        nonAdmittedComments:country.nonAdmittedComments,
                        otherRequirements:country.otherRequirements,
                        cashBeforeCoverReqComments:country.cashBeforeCoverReqComments,
                        vat:country.vat,
                        tacitRenewalReasons:country.tacitRenewalReasons
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
         this.$el.empty();
         this.$el.unbind();
         var countryDetailView=new CountryDetailView({el:"#layout-body-content"});
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
                 '<td class="table-column"><p class="table-column-text"><%=otherRequirements%></p></td> <td class="table-column"><p class="table-column-text"><%=mandatoryReInsuranceComments%></p></td> <td class="table-column"><p class="table-column-text"><%=tacitRenewalReasons%></p></td>'+
                 '<td class="table-column"><div class="edit-icon edit-country"></div></td> <td class="table-column"><div class="delete-icon delete-country"></div></td>',
    initialize: function(){},
    render: function(){
        var variables = {country:this.model.get("country"),mandatoryReInsuranceComments:this.model.get("mandatoryReInsuranceComments"),nonAdmittedComments:this.model.get("nonAdmittedComments"),
                        otherRequirements:this.model.get("otherRequirements"),mandatoryReInsuranceComments:this.model.get("mandatoryReInsuranceComments"),tacitRenewalReasons:this.model.get("tacitRenewalReasons")};
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
                   countryId:country.countryId,country:country.country,territoryComments:country.territoryComments,
                   locCurOnLocPol:country.locCurOnLocPol,locCurOnLocPolComments:country.locCurOnLocPolComments,
                   foreignLawOnLocalPolicy:country.foreignLawOnLocalPolicy,foreignLawOnLocalPolicyComments:country.foreignLawOnLocalPolicyComments,
                   useManuScript:country.useManuScript,manuScriptLOB:country.manuScriptLOB,
                   manuScriptComments:country.manuScriptComments,reInsuranceSupport:country.reInsuranceSupport,
                   reInsuranceSupportLOB:country.reInsuranceSupportLOB,reInsuranceSupportComments:country.reInsuranceSupportComments,
                   foreignReinsurerRegistered:country.foreignReinsurerRegistered,foreignReinsurerRegisteredComments:country.foreignReinsurerRegisteredComments,
                   foreignReinsurerRegisteredAdvice:country.foreignReinsurerRegisteredAdvice,infoReqdForPolicyInsurance:country.infoReqdForPolicyInsurance,
                   premiumCollectionType:country.premiumCollectionType,nonAdmittedAllowed:country.nonAdmittedAllowed,
                   nonAdmittedComments:country.nonAdmittedComments,mandatoryReInsuranceCession:country.mandatoryReInsuranceCession,
                   mandatoryReInsuranceComments:country.mandatoryReInsuranceComments,tacitRenewal:country.tacitRenewal,
                   tacitRenewalReasons:country.tacitRenewalReasons,tacticalRenewalComments:country.tacticalRenewalComments,
                   cashBeforeCoverReq:country.cashBeforeCoverReq,cashBeforeCoverReqComments:country.cashBeforeCoverReqComments,
                   localCurrencyReq:country.localCurrencyReq,localCurrencyReqComments:country.localCurrencyReqComments,
                   stateReinsurerReqLOB:country.stateReinsurerReqLOB,stateReinsurerReq:country.stateReinsurerReq,
                   stateReinsurerReqComments:country.stateReinsurerReqComments,otherRequirements:country.otherRequirements,
                   generalComments:country.generalComments,
                   generalAttachment:country.generalAttachment,insuRequiredDoc:country.insuRequiredDoc,
                   taxes:country.taxes,rateRequirements:country.rateRequirements,clauses:country.clauses,
                   stateReinsurerReqLOB:country.stateReinsurerReqLOB,nonAdmittedLob:country.nonAdmittedLob,mandatoryReInsuranceLob:country.mandatoryReInsuranceLob,
               });

                /*var popupView=new PopupView({el:"#popupWrapper"});
                popupView.render();
                popupView.$el.find("#popup-title").html("Country Details");
                var countryPopupView = new CountryPopupView({el:"#popup-content",model: countryModel});
                countryPopupView.render();*/
                $("#layout-body-content").empty();
                $("#layout-body-content").unbind();
                var countryDetailView = new CountryDetailView({el:"#layout-body-content",model: countryModel});
                countryDetailView.render();
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
        require(['text!'+'templates/common/country_details.html'], function(country_details) {

            var variables =
            {
                countryId:_self.model.get("countryId"),country:_self.model.get("country"),territoryComments:_self.model.get("territoryComments"),
                locCurOnLocPol:_self.model.get("locCurOnLocPol"),locCurOnLocPolComments:_self.model.get("locCurOnLocPolComments"),
                foreignLawOnLocalPolicy:_self.model.get("foreignLawOnLocalPolicy"),foreignLawOnLocalPolicyComments:_self.model.get("foreignLawOnLocalPolicyComments"),
                useManuScript:_self.model.get("useManuScript"),manuScriptLOB:_self.model.get("manuScriptLOB"),
                manuScriptComments:_self.model.get("manuScriptComments"),reInsuranceSupport:_self.model.get("reInsuranceSupport"),
                reInsuranceSupportLOB:_self.model.get("reInsuranceSupportLOB"),reInsuranceSupportComments:_self.model.get("reInsuranceSupportComments"),
                foreignReinsurerRegistered:_self.model.get("foreignReinsurerRegistered"),foreignReinsurerRegisteredComments:_self.model.get("foreignReinsurerRegisteredComments"),
                foreignReinsurerRegisteredAdvice:_self.model.get("foreignReinsurerRegisteredAdvice"),infoReqdForPolicyInsurance:_self.model.get("infoReqdForPolicyInsurance"),
                premiumCollectionType:_self.model.get("premiumCollectionType"),nonAdmittedAllowed:_self.model.get("nonAdmittedAllowed"),
                nonAdmittedComments:_self.model.get("nonAdmittedComments"),mandatoryReInsuranceCession:_self.model.get("mandatoryReInsuranceCession"),
                mandatoryReInsuranceComments:_self.model.get("mandatoryReInsuranceComments"),tacitRenewal:_self.model.get("tacitRenewal"),
                tacitRenewalReasons:_self.model.get("tacitRenewalReasons"),tacticalRenewalComments:_self.model.get("tacticalRenewalComments"),
                cashBeforeCoverReq:_self.model.get("cashBeforeCoverReq"),cashBeforeCoverReqComments:_self.model.get("cashBeforeCoverReqComments"),
                localCurrencyReq:_self.model.get("localCurrencyReq"),localCurrencyReqComments:_self.model.get("localCurrencyReqComments"),
                stateReinsurerReqLOB:_self.model.get("stateReinsurerReqLOB"),stateReinsurerReq:_self.model.get("stateReinsurerReq"),
                stateReinsurerReqComments:_self.model.get("stateReinsurerReqComments"),otherRequirements:_self.model.get("otherRequirements"),
                generalComments:_self.model.get("generalComments"),
                taxes:_self.model.get("taxes"),rateRequirements:_self.model.get("rateRequirements"),clauses:_self.model.get("clauses"),
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
