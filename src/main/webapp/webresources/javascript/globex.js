requirejs.config({
	baseUrl: "/webresources/javascript/",
	urlArgs: "_=0.00102", //+  (new Date()).getTime(),
	waitSeconds: 0
});


require.config({
        paths: {
                jQuery: "jquery", // v2.0.3
    			jQueryUI: "jquery-ui/jquery-ui", // v1.10.4
    			lm:"lm",
    			dashboard:"globex/dashboard/dashboard",
    			underScore: "underscore-min", // v1.5.2
    			backBone: "backbone-min" ,// v1.0.0
    			fcbkcomplete:"lib/fcbkcomplete/fcbkcomplete",
    			user:"globex/user/user",
    			menu:"globex/menu"
        },
        map: {
                "*": {
                    // Uncaught Error: Load timeout for modules: swutils,jquery-ui/jquery-ui
                    "jquery-ui/jquery-ui": 'jQueryUI'
                }
            },
        shim: {
                'jQuery': {
                     exports: '$'
                   },
                'underScore': {
                      exports: '_'
                   }
            },
        waitSeconds: 0
    });


define([
    'text!'+'templates/lm/lmRegistrationForm.html'

], function(lmRegistrationForm){

    require(['jQuery'], function() {
        require(['jQueryUI'], function() {
          require(['underScore'], function() {
            require(['backBone'], function() {
                require(['lm','dashboard','menu'], function() {
                   require(['fcbkcomplete'], function() {
                        initApp(lmRegistrationForm)
                    });
                });
            });
          });
        });
    });

});


function initApp(lmRegistrationForm){

    require(['user'], function() {
        //var userView=new UserView({el:"#dashboardContainer"});
    });

    var dashboardView=new DashboardView({});


/*    var lm= new LMUserRegistrationView({ });
    lm.render();

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

    var dashboardView=new DashboardView({ });
    $(".section").click(function(ev){
        $(ev.currentTarget).find(".section-content").toggle();
    });*/

}
