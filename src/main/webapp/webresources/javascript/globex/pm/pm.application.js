
/*AppSubmission record view*/
AppSubmissionView=Backbone.View.extend({
    events:{
        "click .pm-app-file":"renderFileDetailsPopup",
        "click .pm-post-view-link":"renderFileDetails",
        "click .file-comments-option":"renderComments",
        "click .file-notes":"renderNotes"
    },
    template:'<div class="pm-app-item">'+
              	'<div class="pm-app-prof-icon message-sender-img pic-standard-small">'+
              		'<img src="<%= senderImage %>" title="Charles" alt="">'+
              	'</div>'+
              	'<div class="pm-app-item-section">'+
              		'<div class="pm-app-post"><span class="app-post-user"><%= senderName %></span><span class="app-post-msg">submitted an Application</span>'+
              	'</div>'+

              		'<div class="pm-app-file" title="View File">'+
              		'	<span class="pm-app-item-file"></span><span class=""><%= fileId %></span>'+
              		'</div>'+



              		'<div class="pm-app-post-detail">'+
              		'	<span class="pm-details"><span class="pm-post-highlight-text">PM:</span><%= orgName %></span>'+
              		'	<span class="pm-account-details"><span class="pm-post-highlight-text">Insured:</span><%= insuredCompany %></span>'+
              		'</div>'+

              		'<div class="pm-post-view-card">'+
                    '	<span class="">Actions:'+
                    '	 <span>'+
                    '		<span class="file-detail-view">'+
                        '		<span class="pm-post-view-link" title="View File">View File</span>'+
                        '       <span class="app-post-msg" title="View File"> </span>'+
                    '		</span>'+
                    '	  </span>'+
                    '	</span>'+
                    '</div>'+

                    '<div class="file-actions-wrapper">'+
                    '	<span class="file-actions-item">'+
                        '	<a class="file-comments-option"><em class="file-comments-option-text">Messages</em></a>'+
                        '	<a class="file-notes"><em class="file-notes-text">Notes</em></a>'+
                        '	<span class="pm-post-share"><span class="pm-post-share-text">Share</span></span>'+
                    '	</span>'+
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
              	'<div class="file-activity-section"></div>'+
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
            /*require(['globex/pm/pm.application'], function() {
                $("#layout-body-content").empty();
                var fileApplicationDetailView = new FileApplicationDetailView({el:"#layout-body-content",model:_self.model});
                fileApplicationDetailView.render();
            });*/

             require(['globex/pm/pmRegistration'], function() {
                $("#layout-body-content").empty();
                var registrationView = new RegistrationView({model:_self.model});
                registrationView.render();
             });



        },
        renderFileDetailsPopup:function(){
            var _self=this;
            require(['globex/pm/pm.application'], function() {
                var popupView=new PopupView({el:"#popupWrapper"});
                popupView.render();
                popupView.$el.find("#popup-title").html("File Details");
                var fileApplicationPopupView = new FileApplicationPopupView({el:"#popup-content",model:_self.model});
                fileApplicationPopupView.render();
            });
        },
        renderComments:function(){
            if(this.$el.find(".file-comments-section").length>0){
                this.$el.find(".file-comments-section").remove();
            }else{
                this.$el.find(".file-activity-section").empty();
                var container=this.$el.find(".file-activity-section");
                var fileId=this.model.get("fileId");
                var commentsListView=new CommentsListView({
                    el:container,
                    fileId:fileId
                });
                commentsListView.render();
            }
        },
        renderNotes:function(){
            if(this.$el.find(".file-notes-section").length>0){
                this.$el.find(".file-notes-section").remove();
            }else{
                this.$el.find(".file-activity-section").empty();
                var container=this.$el.find(".file-activity-section");
                var fileId=this.model.get("fileId");
                var notesListView=new NotesListView({
                    el:container,
                    fileId:fileId
                });
                notesListView.render();
            }
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
                   ' <span class="add-user-text add-application">Create Application</span> </div> </div> </div>'+
                   ' <div class="tab-container"><div class="pm-app-container"> <div class="pm-app-list">'+
                   ' <div class="pm-app-item-wrapper">  </div>'+
                   ' </div></div></div></div>',

    events:{
        'click .add-application':'renderPMRegistration',
    },
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
                    var application=file.applications[0];
                    var user=file.createdBy;
                    var organization=file.organization;

                    var dateCreated=$.datepicker.formatDate("M d, yy",new Date(file.dateCreated));
                    var appSubmissionModel=new AppSubmissionModel({
                           fileId:file.fileId,
                           fileStatus:file.fileStatus,
                           senderName:user.firstName+""+user.lastName,
                           orgName:organization.orgName,
                           insuredCompany:application.insuredCompany,
                           dateCreated:dateCreated,
                           senderImage:user.thumbnail ,
                           messageContent:application.comment,
                           insuredCompany:application.insuredCompany,
                           prospect:file.prospect,
                           organization:organization,
                           application:application,
                           file:file
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
    },
    renderPMRegistration:function(){
       require(['globex/pm/pmRegistration'], function() {
            var registrationView=new RegistrationView();
            registrationView.render();
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
            var file =this.model.get("file");
            var fileAttachments=file.fileAttachments;

            variables={
                fileId:fileId,
                fileStatus:fileStatus,
                senderName:senderName,
                orgName:orgName,
                insuredCompany:insuredCompany,
                messageContent:messageContent,
                prospect:prospect,
                organization:organization,
                application:application,
                fileAttachments:fileAttachments
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

/*** comments ****/
var CommentsCollection=Backbone.Collection.extend({
	url: "/secure/getFileComments"
});

var CommentsListView=Backbone.View.extend({
    events:{
        'click .comments-post':'postMessage',
        'click .comment-add':'renderMessagePopup'
    },
    commentsLayoutTemplate:'<div class="file-comments-section"> '+
                               '<div class="comments-add-section">'+
                                 '<span class="comments-item-img pic-standard-small"> <img src="<%= senderImage %>" > </span>'+
                                 '<span class="comment-add"> <textarea id="comment" name="comment" ></textarea> </span>'+
                                 '<span class="comments-post" >Post</span>'+
                               '</div>'+
                               '<ul class="comments-ul" id="comments-ul"></ul>'+
                            '</div>',
    commentsTemplate:'<div class="">'+


                    '</div>',
    render:function(){
        var $self=this;
        this.$el.find(".file-comments-section").remove();
        var commentsCollection=new CommentsCollection();
        var data={fileId:this.options.fileId};
        commentsCollection.fetch({
            data: data,
            type: 'POST',
            success: function(collection, response){
                 var dashboardJsonStr=$("#dashboardJson script").text();
                 var dashboardJson=eval("("+dashboardJsonStr+")");
                 var user=dashboardJson.currentUser;
                 var variables = {senderImage:user.thumbnail};
                 var template = _.template( $self.commentsLayoutTemplate, variables );
                 $self.$el.append(template);

                var $messageBody=$self.$el.find("#comments-ul");
                for(var i=0; i< response.communications.length; i++){
                    var communication=response.communications[i];
                    var user=communication.user;
                    var userName=user.firstName+","+user.lastName;
                    var messageModel = new MessageModel({
                        subject:communication.subject,
                        attachments:communication.fileAttachment,
                        messageContent:communication.content,
                        senderName:userName,
                        messageTime: " 02:25 AM",
                        senderImage:user.thumbnail ,
                    });
                    var commentsView = new CommentsView({el:$messageBody,model: messageModel});
                    commentsView.render();
                    $messageBody.append(commentsView.$el);
                }
            }
        });
    },
    renderMessagePopup:function(){
        var fileId=this.options.fileId;
        var _self=this;
        require(['globex/common/message'], function() {
            var popupView=new PopupView({el:"#popupWrapper"});
            popupView.render();
            popupView.$el.find("#popup-title").html("New Message");
            popupView.$el.find("#popup-content").empty();
            var messageView = new MessageView({el:"#popup-content",fileId:fileId,context:_self});
            messageView.render();
        });
    },
    postMessage:function(){
        var $self=this;
        var fileId=this.options.fileId;
        var comment=this.$el.find("#comment").val();
        var data={fileId:fileId,comment:comment};
        $.ajax({
            type: 'POST',
            url: '/secure/postComments',
            data: data,
            error: function() {
                $self.render();
            },
            success: function(accountJSON) {
                $self.render();
            }
        });
    }
});

var CommentsView=Backbone.View.extend({
    model:MessageModel,
    commentsTemplate:'<li class="comments-li"> '+
                        '<div class="comments-item">'+
                           '<span class="comments-item-img pic-standard-small"> <img src="<%= senderImage %>" > </span>'+
                           '<div class="comments-item-section">'+
                                '<div class="">'+
                                    '<span class="comment-by-name"><%=senderName%></span> <span class="comment-by-role">PM</span>'+
                                    '<span class="comment-detail-time"><%=messageTime%></span>'+
                                '</div>'+
                                '<div class="comment-details"> '+
                                    '<span class="comment-detail-text"><%=subject%></span>'+
                                    '<a class="mail-attachment" href="/secure/downloadFile?filePath=<%=attachments%>" title="Attachment"></a>'+
                                '</div>'+
                                '<div class="comment-details"> '+
                                    '<span class="comment-detail-text"><%=messageContent%></span>'+
                                '</div>'+
                           '</div>'+
                        '</div>'+
                     '</li>',
    render:function(){
        var variables = {senderName:this.model.get("senderName"),messageTime:this.model.get("messageTime"),
                senderImage:this.model.get("senderImage"),messageContent:this.model.get("messageContent"),
                subject:this.model.get("subject"),attachments:this.model.get("attachments")};
        var template = _.template( this.commentsTemplate, variables );
        this.$el.append($(template));
    }
});
/*** end of comments ****/


/*** notes ****/
var NotesCollection=Backbone.Collection.extend({
	url: "/secure/getFileNotes"
});

var NotesListView=Backbone.View.extend({
    events:{
        'click .notes-post':'postNote'
    },
    notesLayoutTemplate:'<div class="file-notes-section"> '+
                               '<div class="notes-add-section">'+
                                 '<span class="notes-item-img pic-standard-small"> <img src="<%= senderImage %>" > </span>'+
                                 '<span class="notes-add"> <textarea id="notes" name="notes" ></textarea> </span>'+
                                 '<span class="notes-post" >Post</span>'+
                               '</div>'+
                               '<ul class="notes-ul" id="notes-ul"></ul>'+
                            '</div>',
    render:function(){
        var $self=this;
        this.$el.find(".file-notes-section").remove();
        var notesCollection=new NotesCollection();
        var data={fileId:this.options.fileId};
        notesCollection.fetch({
            data: data,
            type: 'POST',
            success: function(collection, response){
                 var dashboardJsonStr=$("#dashboardJson script").text();
                 var dashboardJson=eval("("+dashboardJsonStr+")");
                 var user=dashboardJson.currentUser;
                 var variables = {senderImage:user.thumbnail};
                 var template = _.template( $self.notesLayoutTemplate, variables );
                 $self.$el.append(template);

                var $messageBody=$self.$el.find("#notes-ul");
                for(var i=0; i< response.notes.length; i++){
                    var note=response.notes[i];
                    var user=note.user;
                    var userName=user.firstName+","+user.lastName;
                    var messageModel = new MessageModel({
                        messageContent:note.notes,
                        senderName:userName,
                        messageTime: " 02:25 AM",
                        senderImage:user.thumbnail ,
                    });
                    var notesView = new NotesView({el:$messageBody,model: messageModel});
                    notesView.render();
                    $messageBody.append(notesView.$el);
                }
            }
        });
    },
    postNote:function(){
        var $self=this;
        var fileId=this.options.fileId;
        var notes=this.$el.find("#notes").val();
        var data={fileId:fileId,information:notes,note:notes};
        $.ajax({
            type: 'POST',
            url: '/secure/postNote',
            data: data,
            error: function() {
                $self.render();
            },
            success: function(noteJSON) {
                $self.render();
            }
        });
    }
});

var NotesView=Backbone.View.extend({
    model:MessageModel,
    notesTemplate:'<li class="note-li"> '+
                        '<div class="note-item">'+
                           '<span class="note-item-img pic-standard-small"> <img src="<%= senderImage %>" > </span>'+
                           '<div class="note-item-section">'+
                                '<div class="">'+
                                    '<span class="note-by-name"><%=senderName%></span> <span class="note-by-role">PM</span>'+
                                    '<span class="note-detail-time"><%=messageTime%></span>'+
                                '</div>'+
                                '<div class="note-details"> '+
                                    '<span class="note-detail-text"><%=messageContent%></span>'+
                                '</div>'+
                           '</div>'+
                        '</div>'+
                     '</li>',
    render:function(){
        var variables = {senderName:this.model.get("senderName"),messageTime:this.model.get("messageTime"),
                senderImage:this.model.get("senderImage"),messageContent:this.model.get("messageContent")};
        var template = _.template( this.notesTemplate, variables );
        this.$el.append($(template));
    }
});
/***end of notes ***/
