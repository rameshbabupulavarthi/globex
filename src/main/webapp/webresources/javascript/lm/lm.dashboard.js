var DashboardModel=Backbone.Model.extend({
    defaults: {

    },
     events:{
            "click .user-nav-options":"renderUserOptions",
            "click .layout-body-content":"closeUserOptions",
            'click .navigate-user-profile':'viewProfile'
        },
    initialize: function(opts){

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

var DashboardView=Backbone.View.extend({
     model: DashboardModel,
     initialize:function(){
        this.render();
     },
     render:function(){
        var $dashboard = $("<div/>",{class: "dashboard-wrapper"});
     },
     events: {
        "click .section": "toggleSection"
     },
     toggleSection: function(ev){
        $(ev.currentTarget).find(".section-wrapper").toggle();
     }
});


$(".section").click(function(ev){
    $(ev.currentTarget).find(".section-content").toggle();
});