var CountryDetailView =Backbone.View.extend({
    model:CountryModel,
    events: {
        //'click #saveUser' :'saveUserDetails'
    },
    render: function(){
        var _self=this;
        require(['text!'+'templates/common/country_details.html'], function(country_details) {
            var variables ={id:"",country:"",nonAdmittedAllowed:"",nonAdmittedComments:"",taxes:"",vat:"",reInsuranceTax:""};
            if(_self.model){
                variables = {country:_self.model.get("country"),nonAdmittedAllowed:_self.model.get("nonAdmittedAllowed"),nonAdmittedComments:_self.model.get("nonAdmittedComments"),
                taxes:_self.model.get("taxes"),vat:_self.model.get("vat"),reInsuranceTax:_self.model.get("reInsuranceTax")};
            }
             country_details = _.template( country_details, variables );
            _self.$el.append(country_details);
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

                    var form=document.getElementById("saveCountryDetails");
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


var CountryCollection=Backbone.Collection.extend({
	url: "/secure/viewCountries",
});

CountryListView =Backbone.View.extend({
    el:"#layout-body-content",
    tagName:'div',
    className:'table-container',
    headerTemplate:'<thead><tr class="header-row"> <th class="header-column">Country</th>  <th class="header-column">Non-Admitted Allowed</th> <th class="header-column">Non-Admitted Comments</th>'+
                    '<th class="header-column">Taxes</th> <th class="header-column">VAT</th> <th class="header-column">Reinsurance Tax</th> <th class="header-column">View</th> <th class="header-column">Delete</th> </tr></thead>',
    events: {
        "click #createCountry":"addCountry"
    },
    render: function(){
        var $self=this;
        var pageNo=$self.pageNo;
        var pageData={pageNo:pageNo};
        this.$el.empty();
        var countryCollection=new CountryCollection();
        countryCollection.fetch({
            data: pageData,
            type: 'POST',
            success: function(collection, response){
                $self.pageNo=response.pageNo;

                var $country_list_container=$("<div/>" , {
                     "id":"country_list_container",
                     html:'<div class="btn-wrapper"> <div id="createCountry" class="add-user-button"><span class="add-button"></span> <span class="add-country-text" >Add Country </span></div></div>'
                 });

                var $country_table=$("<table/>" , {
                    "class": "table-container",
                    "id": "table-container",
                    html:$self.headerTemplate
                });
                $self.$el.append($country_list_container);
                $country_list_container.append($country_table);
                var countries=response.countries;

                _.each(countries,function(country){
                    var countryModel = new CountryModel({
                        id:country.id,
                        country:country.country,
                        nonAdmittedAllowed:country.nonAdmittedAllowed,
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
         this.$el.html("");
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
    countryTemplate:'<td class="table-column"><%=country%></td>  <td class="table-column"><%=nonAdmittedAllowed%></td> <td class="table-column"><%=nonAdmittedComments%></td>'+
                 '<td class="table-column"><%=taxes%></td> <td class="table-column"><%=vat%></td> <td class="table-column"><%=reInsuranceTax%></td>'+
                 '<td class="table-column"><div class="edit-icon edit-country"></div></td> <td class="table-column"><div class="delete-icon delete-country"></div></td>',
    initialize: function(){},
    render: function(){
        var variables = {country:this.model.get("country"),nonAdmittedAllowed:this.model.get("nonAdmittedAllowed"),nonAdmittedComments:this.model.get("nonAdmittedComments"),
                        taxes:this.model.get("taxes"),vat:this.model.get("vat"),reInsuranceTax:this.model.get("reInsuranceTax")};
        var template = _.template( this.countryTemplate, variables );
        this.$el.append($(template));
    },
    editCountry:function(){
        var countryId=this.model.get("countryId");
        $.ajax({
            type: 'POST',
            url: '/secure/editCountry',
            data: {
                countryId : countryId
            },
            context: this,
            error: function() {},
            success: function(country) {
               var countryModel = new CountryModel({
                   id:country.id,
                   country:country.country,
                   nonAdmittedAllowed:country.nonAdmittedAllowed,
                   nonAdmittedComments:country.nonAdmittedComments,
                   taxes:country.taxes,
                   vat:country.vat,
                   reInsuranceTax:country.reInsuranceTax
               });
                var countryPopupView = new CountryPopupView({model: countryModel});
                countryPopupView.render();
            }
        });
    },
    deleteCountry:function(){
          var countryId=this.model.get("countryId");
          $.ajax({
              type: 'POST',
              url: '/secure/deleteCountry',
              data: {
                  countryId : countryId
              },
              context: this,
              error: function() {},
              success: function(htmlData) {
                  this.remove();
              }
          });
    }
});