
/*AppSubmission record view*/
AppSubmissionView=Backbone.View.extend({
    events:{
        "click .pm-app-file":"renderFileDetailsPopup",
        "click .file-detail-view":"renderFileDetails"
    },
    template:'<div class="pm-app-item">'+
              	'<div class="pm-app-prof-icon message-sender-img pic-standard-small">'+
              		'<img src="https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg" title="Charles" alt="">'+
              	'</div>'+
              	'<div class="pm-app-item-section">'+
              		'<div class="pm-app-post"><span class="app-post-user"><%= senderName %></span><span class="app-post-msg">submitted an Application</span>'+
              	'</div>'+

              		'<div class="pm-app-file" title="View File">'+
              		'	<span class="pm-app-item-file"></span><span class=""><%= fileId %></span>'+
              		'</div>'+

              		'<div class="pm-post-view-card">'+
              		'	<span class="">Actions:'+
              		'	 <span>'+
              		'		<span class="file-detail-view">'+
              			'		<span class="pm-post-view-link" title="View File">View File</span><span class="pm-post-view-link" title="View File"> | Comment</span>'+
              		'		</span>'+
              		'	  </span>'+
              		'	</span>'+
              		'</div>'+

              		'<div class="pm-app-post-detail">'+
              		'	<span class="pm-details"><span class="pm-post-highlight-text">PM:</span><%= orgName %></span>'+
              		'	<span class="pm-account-details"><span class="pm-post-highlight-text">Insured:</span><%= insuredCompany %></span>'+
              		'	<span class="pm-post-share"><span class="pm-post-share-text">Share</span></span>'+
              		'</div>'+
              	'</div>'+

              	'<div class="pm-app-item-side">'+
              	'	<div class="">'+
              	'		<div class="pm-app-side-item">'+
              	'			<span class="pm-app-status-text">Status : </span><span class="pm-app-status">New</span>'+
              	'		</div>'+
              	'		<div class="pm-app-side-item">'+
              	'			<span class="pm-app-status-text">Date : </span><span class="pm-app-status"><%= dateCreated %></span>'+
              	'		</div>'+
              	'	</div>'+
              	'</div>'+
             ' </div>',
        render: function(){
            var variables = {
               fileId:this.model.get("fileId"),
               senderName:this.model.get("senderName"),
               orgName:this.model.get("orgName"),
               insuredCompany:this.model.get("insuredCompany"),
               dateCreated:this.model.get("dateCreated"),
               senderImage:this.model.get("senderImage") ,
               messageContent:this.model.get("messageContent"),
               insuredCompany:this.model.get("insuredCompany")
            };
            var template = _.template( this.template, variables );
            this.$el.append($(template));
        },
        renderFileDetails:function(){
            var _self=this;
            require(['globex/pm/pm.application',], function() {
                $("#layout-body-content").empty();
                var fileApplicationDetailView = new FileApplicationDetailView({el:"#layout-body-content",model:_self.model});
                fileApplicationDetailView.render();
            });
        },
        renderFileDetailsPopup:function(){
            var _self=this;
            require(['globex/pm/pm.application',], function() {
                var popupView=new PopupView({el:"#popupWrapper"});
                popupView.render();
                popupView.$el.find("#popup-title").html("File Details");
                var fileApplicationPopupView = new FileApplicationPopupView({el:"#popup-content",model:_self.model});
                fileApplicationPopupView.render();
            });
        }
})

var AppSubmissionCollection=Backbone.Collection.extend({
	url: "/secure/viewApplications"
});

var AppSubmissionModel=Backbone.Model.extend({
    defaults: {
        senderName:"Charlie",
        messageTime: " 02:25 AM",
        senderImage:"https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg" ,
        messageContent:"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
    },
    initialize: function(opts){

    }
});


/*AppSubmission list view*/
AppSubmissionListView=Backbone.View.extend({
    layoutTemplate:'<div class="tab-wrapper"><div class="tab-header-wrapper"> <div class="tab-list">'+
                   ' <div class="tab"><span class="tab-header-text">Current Applications</span></div></div>'+
                   ' <div class=""><div id="createPMApplication" class="add-user-button"><span class="add-button"></span>'+
                   ' <span class="add-user-text">Create Application</span> </div> </div> </div>'+
                   ' <div class="tab-container"><div class="pm-app-container"> <div class="pm-app-list">'+
                   ' <div class="pm-app-item-wrapper">  </div>'+
                   ' </div></div></div></div>',

    render: function() {
        var $self=this;
        $self.renderPage();
    },
    renderPage:function(){
        var $self=this;
        var pageNo=$self.pageNo?$self.pageNo:0;
        var pageData={pageNo:pageNo};

        var appSubmissionCollection=new AppSubmissionCollection();
        appSubmissionCollection.fetch({
            data: pageData,
            type: 'POST',
            success: function(collection, response){
                $self.pageNo=response.pageNo;
                $self.$el.html("");

                $self.$el.html($self.layoutTemplate);
                var $file_list_wrapper=$self.$el.find(".pm-app-item-wrapper");

                var files=[];
                for(var i=0; i< response.files.length; i++){
                    var file=response.files[i];
                    var application=file.application;
                    var user=file.createdBy;
                    var organization=file.organization;

                    var dateCreated=$.datepicker.formatDate("M d, yy",new Date(file.dateCreated));
                    var appSubmissionModel=new AppSubmissionModel({
                           fileId:file.id,
                           fileStatus:file.fileStatus,
                           senderName:user.firstName+""+user.lastName,
                           orgName:organization.orgName,
                           insuredCompany:application.insuredCompany,
                           dateCreated:dateCreated,
                           senderImage:"https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg" ,
                           messageContent:application.comment,
                           insuredCompany:application.insuredCompany,
                           prospect:file.prospect,
                           organization:organization,
                           application:application,
                    });

                    var appSubmissionView=new AppSubmissionView({
                        model: appSubmissionModel
                    });
                    appSubmissionView.render();
                    $file_list_wrapper.append(appSubmissionView.$el);
                }

             var pagingModel=new PagingModel({currentPage:response.pageNo,totalRecords:response.totalRecords});
             var pagingView=new PagingView({model:pagingModel});
             pagingView.pageContext=$self;
             pagingView.$pageContextEl=$self.$el.find(".pm-app-list");
             pagingView.render();
            }
        });
    }
});



FileApplicationDetailView=Backbone.View.extend({
  //el:".message-holder",
  layoutTemplate:'<div class="pm-file-form">'+
                     '<form method="post" autocomplete="off" action="/secure/saveCountry" id="saveCountryDetails" enctype="multipart/form-data">'+
                        ' <div class="accordian-header">'+
                            ' <div class="accordian-header-text">File Details'+
                            ' </div>'+
                            ' <div class="accordian-toggle">'+
                             '    <span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>'+
                             '</div>'+
                         '</div>'+
                        ' <div class="accordian-body">'+
                         '    <div class="edit-country-form" id="application-form">'+
                 		'	</div>'+
                        ' </div>'+
                     '</form>'+
                 '</div>',
  events:{
    "change #pmCoverage":"renderCoverage"
  },
  model : AppSubmissionModel,
  initialize: function(opts) {

  },
  render: function() {

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

        if(this.model){
            var fileId =this.model.get("fileId");
            var fileStatus =this.model.get("fileStatus");
            var senderName =this.model.get("senderName");
            var orgName =this.model.get("orgName");
            var insuredCompany =this.model.get("insuredCompany");
            var dateCreated =this.model.get("dateCreated");
            var senderImage =this.model.get("senderImage");
            var messageContent = this.model.get("messageContent");
            var insuredCompany =this.model.get("insuredCompany");
            var prospect =this.model.get("prospect");
            var organization =this.model.get("organization");
            var application =this.model.get("application");

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

      var _self=this;
      require(['text!'+'templates/pm/coverage/marine_coverage.html'], function(fileApplicationForm) {

           _self.$el.html(_self.layoutTemplate);
           var template=_.template(fileApplicationForm,variables);
          _self.$el.find("#application-form").html(template);
          /*popupView.$el.find("#popup-content").append(template);*/
          _self.validateDetails();
      });
  },
  renderCoverage:function(e){



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

        if(this.model){

            var fileId =this.model.get("fileId");
            var fileStatus =this.model.get("fileStatus");
            var senderName =this.model.get("senderName");
            var orgName =this.model.get("orgName");
            var insuredCompany =this.model.get("insuredCompany");
            var dateCreated =this.model.get("dateCreated");
            var senderImage =this.model.get("senderImage");
            var messageContent = this.model.get("messageContent");
            var insuredCompany =this.model.get("insuredCompany");
            var prospect =this.model.get("prospect");
            var organization =this.model.get("organization");
            var application =this.model.get("application");

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

        var coverage=parseInt($(e.currentTarget).val());
        var coverageTemplate=null;
        switch(coverage){

            case 2 : coverageTemplate="marine_coverage";
                     break;
            case 3 : coverageTemplate="property_coverage";
                      break;
            case 4 : coverageTemplate="general_liability_coverage";
                     break;
            case 5 : coverageTemplate="profession_liability_coverage";
                     break;

            case 6 : coverageTemplate="package_policy_coverage";
                     break;
            case 7 : coverageTemplate="health_coverage";
                      break;
            case 8 : coverageTemplate="other_coverage";
                     break;
        }

       var _self=this;
       require(['text!'+'templates/pm/coverage/'+coverageTemplate+'.html'], function(fileApplicationForm) {
           var template=_.template(fileApplicationForm,variables);
           _self.$el.append(fileApplicationForm);
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
      }
});



FileApplicationPopupView=Backbone.View.extend({
  //el:".message-holder",
  events:{
    "change .pmCoverage":"renderCoverage"
  },
  model : AppSubmissionModel,
  initialize: function(opts) {
  },
  render: function() {



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

        if(this.model){

            var fileId =this.model.get("fileId");
            var fileStatus =this.model.get("fileStatus");
            var senderName =this.model.get("senderName");
            var orgName =this.model.get("orgName");
            var insuredCompany =this.model.get("insuredCompany");
            var dateCreated =this.model.get("dateCreated");
            var senderImage =this.model.get("senderImage");
            var messageContent = this.model.get("messageContent");
            var insuredCompany =this.model.get("insuredCompany");
            var prospect =this.model.get("prospect");
            var organization =this.model.get("organization");
            var application =this.model.get("application");

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
      var _self=this;
      require(['text!'+'templates/pm/coverage/marine_coverage.html'], function(fileApplicationForm) {

           var template=_.template(fileApplicationForm,variables);
          _self.$el.append(template);
          /*popupView.$el.find("#popup-content").append(template);*/
          _self.validateDetails();
      });
  },
  renderCoverage:function(){

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
      }
});
