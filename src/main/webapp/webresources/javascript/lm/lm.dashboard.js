var DashboardModel=Backbone.Model.extend({
    defaults: {

    },
    initialize: function(opts){

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