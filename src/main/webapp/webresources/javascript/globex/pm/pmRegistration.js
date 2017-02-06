RegistrationView=Backbone.View.extend({
    el:".layout-body",
    events:{
        'change #pmCoverage':'renderCoveragePage'
    },
    initialize:function(){
    },
    render:function(){
        var _self=this;
        _self.$el.find("#layout-body-content").html("");
        require(['text!'+'templates/pm/marineRegistrationForm.html'], function(marineRegistrationForm) {
            _self.$el.find("#layout-body-content").append(marineRegistrationForm);
        });
    },
    renderCoveragePage:function(e){
        var _self=this;
        _self.$el.find("#layout-body-content").html("");

        var coverageType=$(e.currentTarget).val();
        var coveragePage="marineRegistrationForm.html";
        if(coverageType==2){
           coveragePage="marineRegistrationForm.html";
        }else if(coverageType==3){
            coveragePage="propertyRegistrationForm.html";
        }


        require(['text!'+'templates/pm/'+coveragePage], function(marineRegistrationForm) {
            _self.$el.find("#layout-body-content").append(marineRegistrationForm);
        });
    }
  });