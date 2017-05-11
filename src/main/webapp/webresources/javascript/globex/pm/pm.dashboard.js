DashboardView=Backbone.View.extend({
    el:".layout-body",
    events:{
        "click .user-nav-options":"renderUserOptions",
        "click .layout-body-content":"closeUserOptions",
        'click .navigate-user-profile':'viewProfile'
    },
    initialize:function(){
        this.render();
    },
    render:function(){

        var _self=this;
        _self.$el.find("#layout-body-content").html("");
        require(['text!'+'templates/dashboard.html'], function(dashboard) {
            _self.$el.find("#layout-body-content").append(dashboard)
            var dashboardTilesView=new DashboardTilesView();
            dashboardTilesView.render();

            var menuNavigatorView=new MenuNavigatorView();
            menuNavigatorView.render();
        });


    },
    renderUserOptions:function(){
        this.$el.find('.profile-drop-down').show();
    },
    closeUserOptions:function(e){
        var $target = $(e.target);
        if(!$target.hasClass("profile-drop-down")){
           this.$el.find('.profile-drop-down').hide();
        }
    },
    viewProfile:function(){
      var _self=this;
      require(['user'], function() {
         var currentUserId=$("#currentUserId").val();
         var userModel = new UserModel({userId:currentUserId});
         var userProfileView=new UserProfileView({model:userModel});
         userProfileView.render();
         _self.$el.find("#layout-body-content").trigger("click");

      });
    }
});

DashboardTilesView=Backbone.View.extend({
    el:"#dashboardTilesContainer",
    events:{
        'click .dashboard-tile-customize':'renderOptions',
        'click .dashboard-tile-jumpout':'jumpOut'
    },
    render:function(){
      for(var i=0;i<10;i++){
            var messageModel=new MessageModel();
            var messageTileView=new MessageTileView({
                model:messageModel
            });
            messageTileView.render();
            this.$el.find("#messageContainer").append(messageTileView.$el);
      }

      for(var i=0;i<5;i++){
          var renewalModel=new RenewalModel();
          var renewalTileView=new RenewalTileView({
              model:renewalModel
          });
          this.$el.find("#renewalsContainer").append(renewalTileView.$el);

          var reminderModel=new ReminderModel();
          var reminderTileView=new ReminderTileView({
                model:reminderModel
          });
          this.$el.find("#remindersContainer").append(reminderTileView.$el);

          var appSubmissionModel=new AppSubmissionModel();
          var appSubmissionTileView=new AppSubmissionTileView({
                  model:appSubmissionModel
          });
          this.$el.find("#appSubmissionContainer").append(appSubmissionTileView.$el);


          var lookupModel=new LookupModel();
          var lookupTileView=new LookupTileView({
                model:lookupModel
          });
          this.$el.find("#lookupContainer").append(lookupTileView.$el);
      }
    },
    renderOptions:function(){
       var popupView=new PopupView({el:"#popupWrapper"});
       popupView.render();
    },
    jumpOut:function(){
        require(['globex/pm/pm.application'], function() {
            var appSubmissionListView=new AppSubmissionListView({el:"#layout-body-content"});
            appSubmissionListView.render();
        });

       /* require(['globex/pm/pm.dashboardDetailView'], function() {
            var messageDetailView=new MessageDetailView({el:"#layout-body-content"});
            messageDetailView.render();
        });*/
    }
});

var MessageModel=Backbone.Model.extend({
    defaults: {
        senderName:"Charlie",
        messageTime: " 02:25 AM",
        senderImage:"https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg" ,
        messageContent:"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
    },
    initialize: function(opts){

    }
});

var MessageTileView=Backbone.View.extend({
    //el:".message-holder",
    events:{

    },
    model : MessageModel,
    messageTemplate:'<div class="message-item"><span class="message-title-content"><span class="message-sender-name"><%= senderName %></span>'+
                    '<span class="message-time"><%= messageTime %></span></span>'+
                    '<span class="message-sender-img"><img src="<%= senderImage %>" ></span><span class="message-content"><%= messageContent %></span></div>',

    initialize: function(opts) {

    },
    render: function() {
        var variables = {senderName:this.model.get("senderName"),messageTime:this.model.get("messageTime"),
                senderImage:this.model.get("senderImage"),messageContent:this.model.get("messageContent")};
        var template = _.template( this.messageTemplate, variables );
        this.$el.append($(template));
    }
});


/**Renewals*/
var RenewalModel=Backbone.Model.extend({
    defaults: {
        senderName:"Charlie",
        messageTime: " 02:25 AM",
        senderImage:"https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg" ,
        messageContent:"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
    },
    initialize: function(opts){

    }
});

var RenewalTileView=Backbone.View.extend({
    //el:".message-holder",
    model : RenewalModel,
    messageTemplate:'<div class="message-item"><span class="message-title-content"><span class="message-sender-name"><%= senderName %></span>'+
                    '<span class="message-time"><%= messageTime %></span></span>'+
                    '<span class="message-sender-img pic-standard-small"><img src="<%= senderImage %>" ></span><span class="message-content"><%= messageContent %></span></div>',

    initialize: function(opts) {
        this.render();
    },
    render: function() {
        var variables = {senderName:this.model.get("senderName"),messageTime:this.model.get("messageTime"),
                senderImage:this.model.get("senderImage"),messageContent:this.model.get("messageContent")};
        var template = _.template( this.messageTemplate, variables );
        this.$el.append($(template));
    }
});


/**Reminders*/
var ReminderModel=Backbone.Model.extend({
    defaults: {
        senderName:"Charlie",
        messageTime: " 02:25 AM",
        senderImage:"https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg" ,
        messageContent:"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
    },
    initialize: function(opts){

    }
});

var ReminderTileView=Backbone.View.extend({
    //el:".message-holder",
    model : ReminderModel,
    messageTemplate:'<div class="message-item"><span class="message-title-content"><span class="message-sender-name"><%= senderName %></span>'+
                    '<span class="message-time"><%= messageTime %></span></span>'+
                    '<span class="message-sender-img pic-standard-small"><img src="<%= senderImage %>" ></span><span class="message-content"><%= messageContent %></span></div>',

    initialize: function(opts) {
        this.render();
    },
    render: function() {
        var variables = {senderName:this.model.get("senderName"),messageTime:this.model.get("messageTime"),
                senderImage:this.model.get("senderImage"),messageContent:this.model.get("messageContent")};
        var template = _.template( this.messageTemplate, variables );
        this.$el.append($(template));
    }
});


/**Application submissions view*/
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

var AppSubmissionTileView=Backbone.View.extend({
    //el:".message-holder",
    model : AppSubmissionModel,
    messageTemplate:'<div class="message-item"><span class="message-title-content"><span class="message-sender-name"><%= senderName %></span>'+
                    '<span class="message-time"><%= messageTime %></span></span>'+
                    '<span class="message-sender-img pic-standard-small"><img src="<%= senderImage %>" ></span><span class="message-content"><%= messageContent %></span></div>',

    initialize: function(opts) {
        this.render();
    },
    render: function() {
        var variables = {senderName:this.model.get("senderName"),messageTime:this.model.get("messageTime"),
                senderImage:this.model.get("senderImage"),messageContent:this.model.get("messageContent")};
        var template = _.template( this.messageTemplate, variables );
        this.$el.append($(template));
    }
});


/**Application Look up*/
var LookupModel=Backbone.Model.extend({
    defaults: {
        senderName:"Charlie",
        messageTime: " 02:25 AM",
        senderImage:"https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg" ,
        messageContent:"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
    },
    initialize: function(opts){

    }
});

var LookupTileView=Backbone.View.extend({
    //el:".message-holder",
    model : LookupModel,
    messageTemplate:'<div class="message-item"><span class="message-title-content"><span class="message-sender-name"><%= senderName %></span>'+
                    '<span class="message-time"><%= messageTime %></span></span>'+
                    '<span class="message-sender-img pic-standard-small"><img src="<%= senderImage %>" ></span><span class="message-content"><%= messageContent %></span></div>',

    initialize: function(opts) {
        this.render();
    },
    render: function() {
        var variables = {senderName:this.model.get("senderName"),messageTime:this.model.get("messageTime"),
                senderImage:this.model.get("senderImage"),messageContent:this.model.get("messageContent")};
        var template = _.template( this.messageTemplate, variables );
        this.$el.append($(template));
    }
});


PopupView=Backbone.View.extend({
    events:{
        'click .popup-close':'closePopup'
    },
    template:'<div class="popup-container" id="popupContainer"><div class="popup-content">'+
                  '<div class="popup-header"><span class="popup-header-text" id="popup-title"></span><span class="popup-close">X</span></div>'+
                 '<div class="popup-body"><div id="popup-content">  </div></div>'+
                '</div>'+
             '</div>',
    initialize: function() {
    },
    render: function() {
        var template = this.template;
        this.$el.append($(template));
        $("body").addClass("blur-background");
        this.$el.find("#popupContainer").show("slow",function(){ });
    },
    closePopup:function(){
        this.$el.find("#popupContainer").hide("slow",function(){ });
        $("body").removeClass("blur-background");
        this.$el.find("#popupContainer").remove();
    }
});