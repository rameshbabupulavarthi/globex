/**************************************************************** LM ***********************************************/
LMListView =Backbone.View.extend({

    el:"#layout-body-content",
    tagName:'div',
    className:'table-container',
    headerTemplate:'<thead><tr class="header-row">'+
                            '<th class="header-column">Organization<div class="header-column-sort"></div></th>'+
                            '<th class="header-column">Address<div class="header-column-sort"></div></th>'+
                            '<th class="header-column">Contact Name<div class="header-column-sort"></div></th>'+
                            '<th class="header-column">Phone Number<div class="header-column-sort"></div></th>'+
                            '<th class="header-column">Edit<div class="header-column-sort"></div></th>'+
                            '<th class="header-column">Delete</th>'+
                    '</tr></thead>',
    filterTemplate:'<div>'+
                        '<div class="form-row">'+
                            '<div class="form-column"><div class="field-label">Organization</div><input class="filter-field" type="text" name="orgName" value="<%=orgName%>"></div>'+
                            '<div class="form-column"><div class="field-label">Address</div><input class="filter-field" type="text" name="address1" value="<%=address1%>"></div>'+
                            '<div class="form-column"><div class="field-label">Contact Name</div><input class="filter-field" type="text" name="users.firstName" value="<%=contactName%>"></div>'+
                        '</div>'+
                    '</div>',
    events: {
        "click #createLM":"addLM",
        'click .show-filter':'showFilters',
    },
    render: function(){

        var $self=this;
        var pageNo=$self.pageNo;
        var filterData={};
        if($self.filterData){
          filterData= $self.filterData;
        }
        filterData['orgUserType']=1;
        var filters=JSON.stringify(filterData);
        var pageData={pageNo:pageNo,filterJson:filters};
        var lmCollection=new LMCollection();
        lmCollection.fetch({
            data: pageData,
            type: 'POST',
            success: function(collection, response){
                $self.pageNo=response.pageNo;
                $self.$el.empty();

                var $pm_list_container=$("<div/>" , {
                     "id":"pm_list_container",
                     html:'<div class="">'+
                            '<div class="btn-wrapper">'+
                                '<div class="show-filter filter-list filter-icon"></div>'+
                                ' <a href="/secure/downloadPMs"><div class="filter-list download-icon"></div> </a>'+
                                '<div id="createLM" class="add-user-button"><span class="add-button"></span> <span class="add-country-text" >Add Local Market </span></div>'+
                            '</div>'+
                           '</div>'
                 });

                var $pm_table=$("<table />" , {
                    "class": "table-container",
                    "id": "table-container",
                    html:$self.headerTemplate
                });
                $self.$el.html($pm_list_container);
                $pm_list_container.append($pm_table);
                var organizations=response.organizations;
                var index=0;
                _.each(organizations,function(organization){
                   index++;
                   var rowClass=(index%2)==0?"table-column-even":"table-column-odd";

                   var users=organization.users;
                   var accountInfos=organization.accountInfos;
                   var coverageAreas=organization.coverageAreas;

                   var user=users[0];
                   var phoneNumber=getPhoneNumber(user);
                   var address=organization.address1+","+organization.address1;
                   var fullName=getUserName(user);

                   var partnerMarketModel = new PartnerMarketModel({
                        rowClass:rowClass,
                        address:address,
                        phoneNumber:phoneNumber,
                        contactName:fullName,

                        orgId:organization.orgId,
                        orgName:organization.orgName,
                        address1:organization.address1,
                        address2:organization.address2,
                        city:organization.city,
                        state:organization.state,
                        country:organization.country,
                        zip:organization.zip,
                        website:organization.website,
                        orgType:organization.orgType,
                        parentOrgId:organization.parentOrgId,
                        approved:organization.approved,
                        comment:organization.comment,
                        licenceState:organization.licenceState,

                        users:users,
                        /*accountInfos:accountInfos,
                        coverageAreas:coverageAreas*/
                    });
                    var lmRecordView = new LMRecordView({model: partnerMarketModel});
                    lmRecordView.render();
                    $pm_table.append(lmRecordView.$el);

                },this);
             var pagingModel=new PagingModel({currentPage:response.pageNo,totalRecords:response.totalRecords});
             var pagingView=new PagingView({model:pagingModel});
             pagingView.pageContext=$self;
             pagingView.$pageContextEl=$self.$el.find("#pm_list_container");
             pagingView.render();
            }
        });
    },
    addFilters:function(){

    },
    showFilters:function(){
        this.$el.find(".filter-wrapper").show();
        var filterData=this.filterData;
        var variables={orgName:"",address1:"",contactName:"",phone:""};
        if(filterData){
            variables={orgName:filterData.orgName,address1:filterData.address1,contactName:filterData.contactName,phone:filterData.phone};
        }
        var template = _.template( this.filterTemplate, variables );
        $(".filter-data").html(template);
    },
    addLM:function(){
        this.$el.empty();
        this.$el.unbind();
        require(['lm'], function() {
             var lm= new LMUserRegistrationView({el:".layout-body",model:null});
             lm.render();
        });
    }
});


var LMCollection=Backbone.Collection.extend({
	url: "/secure/listPMs",
});

/**popup view to display user**/
var PartnerMarketPopupView =Backbone.View.extend({
    model:PartnerMarketModel,
    events: {
        "click .cancel-button":"cancel"
    },
    render: function(){
        var _self=this;
        require(['text!'+'templates/pm/registration/partner_registration.html'], function(partner_registration) {
            var popupView=new PopupView({el:"#popupWrapper"});
            popupView.render();
            var variables ={
                    orgId:"",orgName:"",regDate:"",address1:"",address2:"",
                    city:"",state:"",country:"",
                    zip:"",website:"",orgType:"",
                    parentOrgId:"",approved:"",comment:"",
                    licenceState:"",
               };
            if(_self.model){
                variables = {
                    orgId:_self.model.get("orgId"),orgName:_self.model.get("orgName"),regDate:_self.model.get("regDate"),address1:_self.model.get("address1"),
                    address2:_self.model.get("address2"),city:_self.model.get("city"),state:_self.model.get("state"),country:_self.model.get("country"),
                    zip:_self.model.get("zip"),website:_self.model.get("website"),orgType:_self.model.get("orgType"),
                    parentOrgId:_self.model.get("parentOrgId"),approved:_self.model.get("approved"),comment:_self.model.get("comment"),
                    licenceState:_self.model.get("licenceState"),
                    users:_self.model.get("users"),accountInfos:_self.model.get("accountInfos"),coverageAreas:_self.model.get("coverageAreas"),
                    registeredCountries:_self.model.get("registeredCountries"),coverageContacts:_self.model.get("coverageContacts"),
                    branchOffices:_self.model.get("branchOffices")
                };
            }
            var template = _.template( partner_registration, variables );
            popupView.$el.find("#popup-content").html(template);
            popupView.$el.find("#popup-title").html("Partner Market Details");
            //_self.validateDetails();
        });
    },
    cancel:function(e){
          e.preventDefault();
          $(".navigate-manage-pm").trigger("click");
      }
});

var LMRegistrationView=Backbone.View.extend({
        model:PartnerMarketModel,
        events: {
            "click #addContact":"addContact",
            "click #addBankDetails":"addBankDetails",
            "click #addRegistrationDetails":"addRegistrationDetails",
            "click #addCoverageContacts":"addCoverageContacts",
            "click #addBranchOffices":"addBranchOffices",
            "click .cancel-button":"cancel",
        },
        render: function(){
            var _self=this;
            var partner_registration="partner_registration.html";
            var variables ={
                    orgId:"",orgName:"",regDate:"",address1:"",address2:"",
                    city:"",state:"",country:"",
                    zip:"",website:"",orgType:"",
                    parentOrgId:"",approved:"",comment:"",
                    licenceState:"",
                    users:[],accountInfos:[],coverageAreas:[]
            };
            if(_self.model){
                variables = {
                    orgId:_self.model.get("orgId"),orgName:_self.model.get("orgName"),regDate:_self.model.get("regDate"),address1:_self.model.get("address1"),
                    address2:_self.model.get("address2"),city:_self.model.get("city"),state:_self.model.get("state"),country:_self.model.get("country"),
                    zip:_self.model.get("zip"),website:_self.model.get("website"),orgType:_self.model.get("orgType"),
                    parentOrgId:_self.model.get("parentOrgId"),approved:_self.model.get("approved"),comment:_self.model.get("comment"),
                    licenceState:_self.model.get("licenceState"),
                    users:_self.model.get("users"),accountInfos:_self.model.get("accountInfos"),coverageAreas:_self.model.get("coverageAreas"),
                    accountInfos:_self.model.get("accountInfos"),coverageAreas:_self.model.get("coverageAreas"),
                    registeredCountries:_self.model.get("registeredCountries"),coverageContacts:_self.model.get("coverageContacts"),
                    branchOffices:_self.model.get("branchOffices")
                };
                partner_registration="partner_edit_view.html";
            }

            require(['text!'+'templates/pm/registration/'+partner_registration], function(partner_registration) {
                 partner_registration = _.template( partner_registration, variables );
                _self.$el.html(partner_registration);
                _self.validateDetails();
                $(".date-picker").datepicker({ dateFormat: 'dd-mm-yy'});
            });

        },
        validateDetails:function(){
            var _self=this;
            require(["jquery.validate"],function(){
                _self.$el.find("#savePMDetails").validate({
                    invalidHandler: function(e, validator) {},
                    rules: {
                        orgName:"required",
                        address1:"required",
                        country:"required",
                        state:"required",
                        city:"required",
                        zip:"required",
                        website:"required",

                        firstName:"required",
                        lastName:"required",
                        email:"required",
                        phone:"required",
                        fax:"required",
                        mobile:"required",
                        userName:"required",
                        password:"required",
                        confirmPassword:"required",
                        userType:"required",
                        email: {
                            required: true,
                            email: true,
                            maxlength: 255
                        },
                    },
                    submitHandler: function(form) {
                        $(".loading-icon-wrapper").show();
                        $("body").css({opacity:0.5});
                        //var formData=$('#savePMDetails').serialize();

                        //organization
                        var $organizationSection=_self.$el.find("#organizationSection");
                        var organizationJson={
                           orgId:$organizationSection.find("[name='orgId']").val(),
                           orgType:$organizationSection.find("[name='orgType']").val(),
                           approved:$organizationSection.find("[name='approved']").val(),
                           orgName:$organizationSection.find("[name='orgName']").val(),
                           address1:$organizationSection.find("[name='address1']").val(),
                           address2:$organizationSection.find("[name='address2']").val(),
                           country:$organizationSection.find("[name='country']").val(),
                           state:$organizationSection.find("[name='state']").val(),
                           city:$organizationSection.find("[name='city']").val(),
                           zip:$organizationSection.find("[name='zip']").val(),
                           website:$organizationSection.find("[name='website']").val(),
                        }

                        //contact details
                        var $contactSection=_self.$el.find("#contactSection");
                        var contacts=$contactSection.find(".pm-contact");
                        var contactsJsonArr=[];
                        for(var i=0;i<contacts.length;i++){
                            var $contact=$(contacts[i]);
                            var contactJson={
                                id:$contact.find("[name='id']").val(),
                                userType:$contact.find("[name='userType']").val(),
                                userName:$contact.find("[name='userName']").val(),
                                password:$contact.find("[name='password']").val(),
                                firstName:$contact.find("[name='firstName']").val(),
                                lastName:$contact.find("[name='lastName']").val(),
                                email:$contact.find("[name='email']").val(),
                                phoneCountryCode:$contact.find("[name='phoneCountryCode']").val(),
                                phoneAreaCode:$contact.find("[name='phoneAreaCode']").val(),
                                phone:$contact.find("[name='phone']").val(),
                                phoneExtension:$contact.find("[name='phoneExtension']").val(),
                                faxCountryCode:$contact.find("[name='faxCountryCode']").val(),
                                faxAreaCode:$contact.find("[name='faxAreaCode']").val(),
                                fax:$contact.find("[name='fax']").val(),
                                mobileCountryCode:$contact.find("[name='mobileCountryCode']").val(),
                                mobile:$contact.find("[name='mobile']").val(),
                                userType:$contact.find("[name='userType']").val(),
                                comments:$contact.find("[name='comments']").val(),
                                status:$contact.find("[name='status']").val(),
                                address:$contact.find("[name='address']").val(),
                                city:$contact.find("[name='city']").val(),
                                state:$contact.find("[name='state']").val(),
                                country:$contact.find("[name='country']").val(),
                                zip:$contact.find("[name='zip']").val(),
                                branchOffice:$contact.find("[name='branchOffice']").val()
                            }
                            contactsJsonArr.push(contactJson);
                        }
                        organizationJson.userJsonStr=JSON.stringify(contactsJsonArr);
                        //bank details
                        var $bankSection=_self.$el.find("#bankSection");
                        var bankAccounts=$bankSection.find(".pm-bank");
                        var bankAccountJsonArr=[];
                        for(var i=0;i<bankAccounts.length;i++){
                            var $bankAccount=$(bankAccounts[i]);
                            var bankAccountJson={
                                accountInfoId:$bankAccount.find("[name='accountInfoId']").val(),
                                contactFirstName:$bankAccount.find("[name='contactFirstName']").val(),
                                contactLastName:$bankAccount.find("[name='contactLastName']").val(),
                                contactEmail:$bankAccount.find("[name='contactEmail']").val(),
                                contactPhoneCountryCode:$bankAccount.find("[name='contactPhoneCountryCode']").val(),
                                contactPhoneAreaCode:$bankAccount.find("[name='contactPhoneAreaCode']").val(),
                                contactPhone:$bankAccount.find("[name='contactPhone']").val(),
                                contactFaxCountryCode:$bankAccount.find("[name='contactFaxCountryCode']").val(),
                                contactFaxAreaCode:$bankAccount.find("[name='contactFaxAreaCode']").val(),
                                contactFax:$bankAccount.find("[name='contactFax']").val(),
                                contactMobileCountryCode:$bankAccount.find("[name='contactMobileCountryCode']").val(),
                                contactMobile:$bankAccount.find("[name='contactMobile']").val(),
                                bankInfo:$bankAccount.find("[name='bankInfo']").val(),
                                otherInfo:$bankAccount.find("[name='otherInfo']").val()
                            }
                            bankAccountJsonArr.push(bankAccountJson);
                        }
                        organizationJson.accountInfoJsonStr=JSON.stringify(bankAccountJsonArr);

                        var serviceSection=_self.$el.find("#serviceSection");
                        var $serviceSection=$(serviceSection);
                        var coverageAreaJsonArray=[];
                        var coverageAreaJson={
                            coverageAreaId:$serviceSection.find("[name='coverageAreaId']").val(),
                            marine:$serviceSection.find("[name='marine']").val(),
                            property:$serviceSection.find("[name='property']").val(),
                            healthAndBenefits:$serviceSection.find("[name='healthAndBenefits']").val(),
                            liabilities:$serviceSection.find("[name='liabilities']").val(),
                            directorsAndOfficers:$serviceSection.find("[name='directorsAndOfficers']").val(),
                            aviation:$serviceSection.find("[name='aviation']").val()
                        }
                        coverageAreaJsonArray.push(coverageAreaJson);
                        organizationJson.coverageAreaJsonStr=JSON.stringify(coverageAreaJsonArray);

                        var $registeredCountriesSection=_self.$el.find("#registeredCountriesSection");
                        var registeredCountries=$registeredCountriesSection.find(".registered-country");
                        var registeredCountriesJsonArray=[];
                        for(var i=0;i<registeredCountries.length;i++){
                            var $registeredCountry=$(registeredCountries[i]);
                            var registeredCountryJson={
                              registeredCountryId:$registeredCountry.find("[name='registeredCountryId']").val(),
                              countryName:$registeredCountry.find("[name='countryName']").val(),
                              registrationNumber:$registeredCountry.find("[name='registrationNumber']").val(),
                              registrationDate:$registeredCountry.find("[name='registrationDate']").val(),
                              //regAttachments:$registeredCountry.find("[name='regAttachments']").val(),
                            }
                            registeredCountriesJsonArray.push(registeredCountryJson);
                        }
                        organizationJson.registeredCountriesJsonStr=JSON.stringify(registeredCountriesJsonArray);

                        var $coverageContactsSection=_self.$el.find("#coverageContactsSection");
                        var coverageContacts=$coverageContactsSection.find(".coverage-contact");
                        var coverageContactsJsonArray=[];
                        for(var i=0;i<coverageContacts.length;i++){
                            var $coverageContact=$(coverageContacts[i]);
                            var coverageContactJson={
                              coverageContactId:$coverageContact.find("[name='coverageContactId']").val(),
                              coverageArea:$coverageContact.find("[name='contactDepartment']").val(),
                              contactName:$coverageContact.find("[name='contactPersonName']").val(),
                              contactPhone:$coverageContact.find("[name='contactPhoneNumber']").val(),
                              contactEmail:$coverageContact.find("[name='contactEmail']").val(),
                              contactPosition:$coverageContact.find("[name='contactPosition']").val()
                            };
                            coverageContactsJsonArray.push(coverageContactJson);
                        }
                        organizationJson.coverageContactsJsonStr=JSON.stringify(coverageContactsJsonArray);

                        var branchOfficesSection=_self.$el.find("#branchOfficesSection");
                        var branchOffices=branchOfficesSection.find(".branch-office");
                        var branchOfficesJsonArray=[];
                        for(var i=0;i<branchOffices.length;i++){
                            var $branchOffice=$(branchOffices[i]);
                            var branchOfficeJson={
                              branchOfficeId:$branchOffice.find("[name='branchOfficeId']").val(),
                              branchCountry:$branchOffice.find("[name='branchCountry']").val(),
                              branchAddress:$branchOffice.find("[name='branchAddress']").val(),
                            }
                            branchOfficesJsonArray.push(branchOfficeJson);
                        }
                        organizationJson.branchOfficeJsonStr=JSON.stringify(branchOfficesJsonArray);

                        var organizationJsonStr=JSON.stringify(organizationJson);
                        var formData=organizationJson;

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
                            success: function(orgJSON) {
                                _self.$el.empty();
                                $(".navigate-manage-pm").trigger("click");
                                $(".loading-icon-wrapper").hide();
                                $("body").css({opacity:1});
                            }
                        });
                    }
                });
            });
        },
        addContact:function(){
            var _self=this;
            require(['text!'+'templates/pm/registration/pmContact.html'], function(partner_contact) {
                _self.$el.find("#contactDetails").append(partner_contact);
            });
        },
        addBankDetails:function(){
            var _self=this;
            require(['text!'+'templates/pm/registration/banking_details.html'], function(banking_details) {
                _self.$el.find("#bankDetails").append(banking_details);
            });
        },
        addRegistrationDetails:function(){
            var _self=this;
            require(['text!'+'templates/pm/registration/partner_registered_country.html'], function(partner_registered_country) {
                _self.$el.find("#registeredCountrySection").append(partner_registered_country);
            });
        },
        addCoverageContacts:function(){
            var _self=this;
            require(['text!'+'templates/pm/registration/partner_coverage_contact.html'], function(partner_coverage_contact) {
                _self.$el.find("#coverageCountrySection").append(partner_coverage_contact);
            });
        },
        addBranchOffices:function(){
            var _self=this;
            require(['text!'+'templates/pm/registration/partner_branch_office.html'], function(partner_branch_office) {
                _self.$el.find("#branchOfficeSection").append(partner_branch_office);
            });
        },
        cancel:function(e){
            e.preventDefault();
             $(".navigate-manage-pm").trigger("click");
        }
});

var LMRecordView=Backbone.View.extend({
    tagName:"tr",
    className:"table-row",
    model : PartnerMarketModel,
    events:{
        "click .view-lm":"viewLM",
        "click .edit-lm":"editLM",
        "click .delete-lm":"deleteLM"
    },
    PMTemplate:'<td class="table-column"><p class="table-column-text"><%=orgName%></p></td>'+
                '<td class="table-column"><p class="table-column-text"><%=address%></p></td>'+
                '<td class="table-column"><p class="table-column-text"><%=contactName%></p></td>'+
                '<td class="table-column"><p class="table-column-text"><%=phoneNumber%></p></td>'+
                '<td class="table-column"><div class="edit-icon edit-lm"></div></td>'+
                '<td class="table-column"><div class="delete-icon delete-lm"></div></td>',
    render: function(){
        var variables = { orgName:this.model.get("orgName"),address:this.model.get("address"),
                          phoneNumber:this.model.get("phoneNumber"),contactName:this.model.get("contactName"),
                        };
        var template = _.template( this.PMTemplate, variables );
        var rowClass=this.model.get("rowClass");
        this.$el.addClass(rowClass);
        this.$el.append($(template));
    },
    viewLM:function(){
        var orgId=this.model.get("orgId");
        $.ajax({
            type: 'POST',
            url: '/secure/getPMDetails',
            data: {
                orgId : orgId
            },
            context: this,
            error: function() {},
            success: function(organization) {
               var partnerMarketModel = new PartnerMarketModel({
                   orgId:organization.orgId,
                   orgName:organization.orgName,
                   address1:organization.address1,
                   address2:organization.address2,
                   city:organization.city,
                   state:organization.state,
                   country:organization.country,
                   zip:organization.zip,
                   website:organization.website,
                   orgType:organization.orgType,
                   parentOrgId:organization.parentOrgId,
                   approved:organization.approved,
                   comment:organization.comment,
                   licenceState:organization.licenceState,
                   users:organization.users,
                   accountInfos:organization.accountInfoDOs,
                   coverageAreas:organization.coverageAreaDOs,
                   registeredCountries:organization.registeredCountryDOs,
                   coverageContacts:organization.coverageContactDOs,
                   branchOffices:organization.branchOfficeDOs
               });

               var popupView=new PopupView({el:"#popupWrapper"});
               popupView.render();
               popupView.$el.find("#popup-title").html("Partner Market Details");
               var partnerMarketPopupView = new PartnerMarketPopupView({el:"#popup-content",model: partnerMarketModel});
               partnerMarketPopupView.render();
            }
        });
    },
    editLM:function(){
        var orgId=this.model.get("orgId");
        $.ajax({
            type: 'POST',
            url: '/secure/getPMDetails',
            data: {
                orgId : orgId
            },
            context: this,
            error: function() {},
            success: function(organization) {
               require(["lm"],function(){
                    var lmUserRegistrationModel=new LMUserRegistrationModel({
                       orgId:organization.orgId,
                       orgName:organization.orgName,
                       address1:organization.address1,
                       address2:organization.address2,
                       city:organization.city,
                       state:organization.state,
                       country:organization.country,
                       zip:organization.zip,
                       website:organization.website,
                       orgType:organization.orgType,
                       telePhone:organization.telePhone,
                       parentOrgId:organization.parentOrgId,
                       approved:organization.approved,
                       comment:organization.comment,
                       organizationDetails:organization.organizationDetails,
                       miscRatings:organization.miscRatings,
                       lobs:organization.lobs,
                       rateRequirements:organization.rateRequirements,
                       commissionRequirements:organization.commissionRequirements,
                       organizationHistories:organization.organizationHistories,
                       uwDepDetails:organization.uwDepDetails,
                       bankingDetails:organization.bankingDetails,
                   });
                    var lmUserRegistrationView = new LMUserRegistrationView({el:"#layout-body-content",model: lmUserRegistrationModel});
                    lmUserRegistrationView.render();
               });
            }
        });
    },
    deleteLM:function(){
          var _self=this;
          var orgId=this.model.get("orgId");

          $(".loading-icon-wrapper").show();
          $("body").css({opacity:0.5});
          $.ajax({
              type: 'POST',
              url: '/secure/deleteOrg',
              data: {
                  orgId : orgId
              },
              context: this,
              error: function() {},
              success: function(htmlData) {
                  $(".navigate-manage-pm").trigger("click");
                  _self.$el.empty();
                  $(".loading-icon-wrapper").hide();
                  $("body").css({opacity:1});
              }
          });
    }
});

var PartnerMarketModel=Backbone.Model.extend({

});
