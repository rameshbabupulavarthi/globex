MenuNavigatorView=Backbone.View.extend({
     el:".layout-body-menu",
     events:{
         'mouseover .menu-container':'showMenu',
         'mouseleave .menu-container':'hideMenu',
         'click .navigate-manage-user':'renderManageUser',
         'click .navigate-dashboard':'renderDashboard',
         'click .navigate-applications':'renderPMRegistration',
         'click .navigate-pm-apps':'renderPMApplications'
     },
     initialize:function(){
     },
     render:function(){
     },
     showMenu:function(){
         this.$el.find(".prd-logo-text").show();
         this.$el.find(".menu-toggle-content").show("slow", function() {
          });
     },
     hideMenu:function(){
         this.$el.find(".prd-logo-text").hide();
         this.$el.find(".menu-toggle-content").hide("slow", function() {

          });
     },
     renderManageUser:function(){
        require(['user'], function() {
           var userListView=new UserListView();
           userListView.render();

        });
     },
     renderDashboard:function(){
        var dashboardView=new DashboardView({});
     },
     renderPMRegistration:function(){
       require(['globex/pm/pmRegistration'], function() {
            var applicationView=new ApplicationView();
            applicationView.render();
        });
     },
     renderPMApplications:function(){
        require(['globex/pm/pm.application'], function() {
             var appSubmissionListView=new AppSubmissionListView({el:"#layout-body-content"});
             appSubmissionListView.render();
         });
     }
});