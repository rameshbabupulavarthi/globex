var PMDetailView =Backbone.View.extend({
    model:PMModel,
    events: {
        //'click #saveUser' :'saveUserDetails'
    },
    render: function(){
        var _self=this;
        require(['text!'+'templates/common/pm_details.html'], function(pm_details) {
            var variables ={id:"",pm:"",nonAdmittedAllowed:"",nonAdmittedComments:"",taxes:"",vat:"",reInsuranceTax:""};
            if(_self.model){
                variables = {pm:_self.model.get("pm"),nonAdmittedAllowed:_self.model.get("nonAdmittedAllowed"),nonAdmittedComments:_self.model.get("nonAdmittedComments"),
                taxes:_self.model.get("taxes"),vat:_self.model.get("vat"),reInsuranceTax:_self.model.get("reInsuranceTax")};
            }
             pm_details = _.template( pm_details, variables );
            _self.$el.append(pm_details);
            _self.validateDetails();
        });
    },
    validateDetails:function(){
        var _self=this;
        require(["jquery.validate"],function(){
            _self.$el.find("#savePMDetails").validate({
                invalidHandler: function(e, validator) {},
                submitHandler: function(form) {
                    $(".loading-icon-wrapper").show();
                    $("body").css({opacity:0.5});

                    var form=document.getElementById("savePMDetails");
                    var formData=new FormData(form);
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
                            console.log("success"+userJSON);
                        }
                    });
                }
            });
        });
    }
});


var PMCollection=Backbone.Collection.extend({
	url: "/secure/viewCountries",
});

PMListView =Backbone.View.extend({
    el:"#layout-body-content",
    tagName:'div',
    className:'table-container',
    headerTemplate:'<thead><tr class="header-row"> <th class="header-column">PM</th>  <th class="header-column">Mandatory Reinsurance Comments</th> <th class="header-column">Non-Admitted Comments</th>'+
                    '<th class="header-column">Taxes</th> <th class="header-column">VAT</th> <th class="header-column">Reinsurance Tax</th> <th class="header-column">View</th> <th class="header-column">Delete</th> </tr></thead>',
    events: {
        "click #createPM":"addPM"
    },
    render: function(){
        var $self=this;
        var pageNo=$self.pageNo;
        var pageData={pageNo:pageNo};
        var pmCollection=new PMCollection();
        pmCollection.fetch({
            data: pageData,
            type: 'POST',
            success: function(collection, response){
                $self.pageNo=response.pageNo;
                $self.$el.html("");

                var $pm_list_container=$("<div/>" , {
                     "id":"pm_list_container",
                     html:'<div class=""><div class="btn-wrapper"> <div id="createPM" class="add-user-button"><span class="add-button"></span> <span class="add-pm-text" >Add PM </span></div></div></div>'
                 });

                var $pm_table=$("<table/>" , {
                    "class": "table-container",
                    "id": "table-container",
                    html:$self.headerTemplate
                });
                $self.$el.append($pm_list_container);
                $pm_list_container.append($pm_table);
                var countries=response.countries;

                _.each(countries,function(pm){
                    var pmModel = new PMModel({
                        pmId:pm.id,
                        pm:pm.pm,
                        mandatoryReInsuranceComments:pm.mandatoryReInsuranceComments,
                        nonAdmittedComments:pm.nonAdmittedComments,
                        taxes:pm.taxes,
                        vat:pm.vat,
                        reInsuranceTax:pm.reInsuranceTax
                    });
                    var pmView = new PMView({model: pmModel});
                    pmView.render();
                    $pm_table.append(pmView.$el);

                },this);
             var pagingModel=new PagingModel({currentPage:response.pageNo,totalRecords:response.totalRecords});
             var pagingView=new PagingView({model:pagingModel});
             pagingView.pageContext=$self;
             pagingView.$pageContextEl=$self.$el.find("#pm_list_container");
             pagingView.render();
            }
        });
    },
     addPM:function(){
         var pmDetailView=new PMDetailView({el:"#layout-body-content"});
         this.$el.html("");
         pmDetailView.render();
     }
});

var PMModel=Backbone.Model.extend({
    defaults: {
        status:'Active'
    },
    url:"/secure/editPM"
});

var PMView =Backbone.View.extend({
    tagName:"tr",
    className:"table-row",
    model : PMModel,
    events:{
        "click .edit-pm":"editPM",
        "click .delete-pm":"deletePM"
    },
    pmTemplate:'<td class="table-column"><%=pm%></td>  <td class="table-column"><%=mandatoryReInsuranceComments%></td> <td class="table-column"><%=nonAdmittedComments%></td>'+
                 '<td class="table-column"><%=taxes%></td> <td class="table-column"><%=vat%></td> <td class="table-column"><%=reInsuranceTax%></td>'+
                 '<td class="table-column"><div class="edit-icon edit-pm"></div></td> <td class="table-column"><div class="delete-icon delete-pm"></div></td>',
    initialize: function(){},
    render: function(){
        var variables = {pm:this.model.get("pm"),mandatoryReInsuranceComments:this.model.get("mandatoryReInsuranceComments"),nonAdmittedComments:this.model.get("nonAdmittedComments"),
                        taxes:this.model.get("taxes"),vat:this.model.get("vat"),reInsuranceTax:this.model.get("reInsuranceTax")};
        var template = _.template( this.pmTemplate, variables );
        this.$el.append($(template));
    },
    editPM:function(){
        var pmId=this.model.get("pmId");
        $.ajax({
            type: 'POST',
            url: '/secure/getPMDetails',
            data: {
                pmId : pmId
            },
            context: this,
            error: function() {},
            success: function(pm) {
               var pmModel = new PMModel({
                   id:pm.id,pm:pm.pm,nonAdmittedAllowed:pm.nonAdmittedAllowed,nonAdmittedComments:pm.nonAdmittedComments,
                   retailBorkerRequired:pm.retailBorkerRequired,retailBrokerComments:pm.retailBrokerComments,reInsuranceBrokerRequired:pm.reInsuranceBrokerRequired,
                   reInsuranceBrokerComments:pm.reInsuranceBrokerComments,mandatoryReInsuranceCession:pm.mandatoryReInsuranceCession,
                   mandatoryReInsuranceComments:pm.mandatoryReInsuranceComments,
                   stateSidePremiumAllowed:pm.stateSidePremiumAllowed,stateSidePremiumComments:pm.stateSidePremiumComments,
                   otherAccRequirements:pm.otherAccRequirements,premiumReserve:pm.premiumReserve,
                   taxes:pm.taxes,vat:pm.vat,reInsuranceTax:pm.reInsuranceTax,
                   otherRequirements:pm.otherRequirements,policyLanguage:pm.policyLanguage,tacitRenewal:pm.tacitRenewal,
                   tacticalRenewalComments:pm.tacticalRenewalComments,generalComments:pm.generalComments,
                   createdBy:pm.createdBy
               });

                var pmPopupView = new PMPopupView({model: pmModel});
                pmPopupView.render();
            }
        });
    },
    deletePM:function(){
          var pmId=this.model.get("pmId");
          $.ajax({
              type: 'POST',
              url: '/secure/deletePM',
              data: {
                  pmId : pmId
              },
              context: this,
              error: function() {},
              success: function(htmlData) {
                  this.remove();
              }
          });
    }
});


/**popup view to display PM**/
var PMPopupView =Backbone.View.extend({
    model:PMModel,
    events: {

    },
    render: function(){

        var _self=this;
        require(['text!'+'templates/common/pm_edit_view.html'], function(pm_details) {
            var popupView=new PopupView({el:"#popupWrapper"});
            popupView.render();
            var variables =
            {
                id:_self.model.get("id"),pm:_self.model.get("pm"),nonAdmittedAllowed:_self.model.get("nonAdmittedAllowed"),nonAdmittedComments:_self.model.get("nonAdmittedComments"),
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
            var template = _.template( pm_details, variables );

            popupView.$el.find("#popup-content").append(template);
            popupView.$el.find("#popup-title").html("PM Details");
            _self.validateDetails();
        });


    },
    validateDetails:function(){
            var _self=this;
            require(["jquery.validate"],function(){
                _self.$el.find("#savePMDetails").validate({
                    invalidHandler: function(e, validator) {},
                    submitHandler: function(form) {
                        $(".loading-icon-wrapper").show();
                        $("body").css({opacity:0.5});

                        var form=document.getElementById("savePMDetails");
                        var formData=new FormData(form);
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
                                console.log("success"+userJSON);
                            }
                        });
                    },
                });
            });
    }
});
/**popup view to display PM**/
