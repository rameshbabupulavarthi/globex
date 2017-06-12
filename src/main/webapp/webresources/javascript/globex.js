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
    			menu:"globex/menu",
    			commons:"globex/commons",
    			select2:"lib/select2/js/select2.min"
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
                require(['commons'], function() {
                   require(['select2'], function() {
                       initApp();
                   });
                });
            });
          });
        });
    });


function initApp(){

    var currentUserRole=$("#currentUserRole").val();
    if(currentUserRole=='ROLE_SUPER_ADMIN' ||currentUserRole=='ROLE_ADMIN'|| currentUserRole=='ROLE_GLOBEX_ADMIN'|| currentUserRole=='ROLE_GLOBEX_USER'){
         require(['dashboard','menu'], function() {
             var dashboardView=new DashboardView({});
         });
    }else if(currentUserRole=='ROLE_PM_ADMIN'|| currentUserRole=='ROLE_PM_USER'){
             require(['jQuery','globex/pm/pm.dashboard','globex/pm/pm.menu'], function() {
                 var dashboardView=new DashboardView({});
             });
    }else{
        require(['jQuery','lm'], function() {

            var lmUserRegistrationJSONData= $("#lmUserRegistrationJSONData script").html();
            var lmUserRegistrationModel=null;
            if(lmUserRegistrationJSONData){
                var lmUserRegistrationJSON = eval('(' + lmUserRegistrationJSONData +')');
                var organization=lmUserRegistrationJSON;

                lmUserRegistrationModel=new LMUserRegistrationModel({
                    orgId:organization.orgId,
                    orgName:organization.orgName,
                    address1:organization.address1,
                    address2:organization.address2,
                    city:organization.city,
                    state:organization.state,
                    country:organization.country,
                    zip:organization.zip,
                    website:organization.website,
                    orgType:organization.orgType,
                    telePhone:organization.telePhone,
                    parentOrgId:organization.parentOrgId,
                    approved:organization.approved,
                    comment:organization.comment,
                    organizationDetails:organization.organizationDetails,
                    miscRatings:organization.miscRatings,
                    lobs:organization.lobs,
                    rateRequirements:organization.rateRequirements,
                    commissionRequirements:organization.commissionRequirements,
                    organizationHistories:organization.organizationHistories,
                    uwDepDetails:organization.uwDepDetails,
                    bankingDetails:organization.bankingDetails,
                });

            }

           var lmUserRegistrationView = new LMUserRegistrationView({el:"#layout-body-content",model: lmUserRegistrationModel});
           lmUserRegistrationView.render();
        });
    }
}
