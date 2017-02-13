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

    require(['jQuery'], function() {
        require(['jQueryUI'], function() {
          require(['underScore'], function() {
            require(['backBone'], function() {
                initApp();
            });
          });
        });
    });


function initApp(){

    var currentUserRole=$("#currentUserRole").val();
    if(currentUserRole=='ROLE_SUPER_ADMIN' ||currentUserRole=='ROLE_ADMIN'|| currentUserRole=='ROLE_GLOBEX'){
         require(['dashboard','menu'], function() {
             var dashboardView=new DashboardView({});
         });
    }else if(currentUserRole=='ROLE_PM_ADMIN'|| currentUserRole=='ROLE_PM_USER'){
             require(['jQuery','globex/pm/pm.dashboard','globex/pm/pm.menu'], function() {
                 var dashboardView=new DashboardView({});
             });
    }else{
        require(['jQuery','lm'], function() {
            var lm= new LMUserRegistrationView({ });
            lm.render();
        });
    }
}
