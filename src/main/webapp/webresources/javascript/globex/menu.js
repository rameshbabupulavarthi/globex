MenuNavigatorView=Backbone.View.extend({
     el:".layout-body-menu",
     events:{
         'mouseover .menu-container':'showMenu',
         'mouseleave .menu-container':'hideMenu',
         'mouseover .menu-item-navigator':'showSubMenu',
         'mouseleave .menu-item-navigator':'hideSubMenu',
         'click .navigate-manage-user':'renderManageUser',
         'click .navigate-dashboard':'renderDashboard',
         'click .navigate-manage-country':'renderManageCountry',
         'click .navigate-applications':'renderApplication',
         'click .navigate-manage-pm':'renderPMList',
         'click .navigate-manage-lm':'renderLMList',
     },
     initialize:function(){
     },
     render:function(){
     },
     showMenu:function(e){
         this.$el.find(".prd-logo-text").show();
         this.$el.find(".menu-toggle-content").show("slow", function() {
          });
     },
     hideMenu:function(){
         this.$el.find(".prd-logo-text").hide();
         this.$el.find(".menu-toggle-content").hide("slow", function() {

         });
     },
     showSubMenu:function(e){
         $(e.currentTarget).find(".sub-menu").show();
     },
     hideSubMenu:function(e){
         $(e.currentTarget).find(".sub-menu").hide();
     },
     renderManageUser:function(){
        require(['user'], function() {
           $("#layout-body-content").empty();
           $("#layout-body-content").unbind();
           var userListView=new UserListView();
           userListView.render();

        });
     },
     renderDashboard:function(){
        var dashboardView=new DashboardView({});
     },
     renderManageCountry:function(){
         require(['globex/admin/country'], function() {
            $("#layout-body-content").empty();
            $("#layout-body-content").unbind();
            var countryListView=new CountryListView();
            countryListView.render();

         });
     },
     renderApplication:function(){
        require(['globex/pm/pm.application'], function() {
            $("#layout-body-content").empty();
            $("#layout-body-content").unbind();
            var appSubmissionListView=new AppSubmissionListView({el:"#layout-body-content"});
            appSubmissionListView.render();
        });
     },
     renderPMList:function(){
         require(['globex/pm/pm.registration'], function() {
              $("#layout-body-content").empty();
              $("#layout-body-content").unbind();
              var pmListView=new PMListView({el:"#layout-body-content"});
              pmListView.render();
         });
     },
     renderLMList:function(){
        require(['lm/lm.registration'], function() {
          $("#layout-body-content").empty();
              $("#layout-body-content").unbind();
              var lmListView=new LMListView({el:"#layout-body-content"});
              lmListView.render();
          });
     }
});