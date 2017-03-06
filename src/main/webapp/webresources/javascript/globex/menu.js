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
         'click .navigate-manage-pm':'renderPMList'
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
           var userListView=new UserListView();
           userListView.render();

        });
     },
     renderDashboard:function(){
        var dashboardView=new DashboardView({});
     },
     renderManageCountry:function(){
         require(['globex/admin/country'], function() {
            var countryListView=new CountryListView();
            countryListView.render();

         });
     },
     renderPMList:function(){
        require(['globex/pm/pm.application'], function() {
            var appSubmissionListView=new AppSubmissionListView({el:"#layout-body-content"});
            appSubmissionListView.render();
        });
     }
});