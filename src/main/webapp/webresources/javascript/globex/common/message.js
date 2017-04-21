var MessageView=Backbone.View.extend({
    events:{
       'click .cancel-button':'cancelMessage'
    },
    model : MessageModel,
    initialize: function(opts) {

    },
    render: function() {
        var fileId=this.options.fileId;
        var variables={fileId:fileId};
        var _self=this;
        _self.$el.empty();
        require(['text!'+'templates/common/message_form.html'], function(message_form) {
               var template=_.template(message_form,variables);
              _self.$el.html(template);
              _self.validateDetails();
          });
    },
    cancelMessage:function(e){
       $(".popup-close").trigger("click");
       e.preventDefault();
    },
    validateDetails:function(){

        var _self=this;
        $('.js-example-matcher-start').select2({
             ajax: {
               url: "/secure/findUsers",
               dataType: 'json',
               delay: 250,
               data: function (params) {
                 return {
                       email: params.term, // search term
                       pageNo: params.page,
                       pageSize:10
                  };
               },
               processResults: function (data, params) {
                 // parse the results into the format expected by Select2
                 // since we are using custom formatting functions we do not need to
                 // alter the remote JSON data, except to indicate that infinite
                 // scrolling can be used
                 params.pageNo = params.pageNo || 1;

                 return {
                   results: data.users,
                   pagination: {
                     more: (params.pageNo * 10) < data.totalRecords
                   }
                 };
               },
               cache: true
             },
             escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
             minimumInputLength: 1,
             templateResult: _self.formatUserDisplay, // omitted for brevity, see the source of this page
             templateSelection: _self.formatUserSelection // omitted for brevity, see the source of this page
           });

        require(["jquery.validate"],function(){
            _self.$el.find("#sendMessage").validate({
                invalidHandler: function(e, validator) {},
                submitHandler: function(form) {
                    $(".loading-icon-wrapper").show();
                    $("body").css({opacity:0.5});

                    var form=document.getElementById("sendMessage");
                    var formData=new FormData(form);
                    $.ajax({
                        type: 'POST',
                        url: form.action,
                        data: formData,
                        async: false,
                        cache: false,
                        contentType: false,
                        processData: false,
                        error: function() {
                            $(".loading-icon-wrapper").hide();
                            $("body").css({opacity:1});
                            $(".popup-close").trigger("click");
                            _self.context.render();
                        },
                        success: function() {
                            $(".loading-icon-wrapper").hide();
                            $("body").css({opacity:1});
                            $(".popup-close").trigger("click");
                            _self.options.context.render();
                        }
                    });
                }
            });
        });
    },
    formatUserDisplay:function(user){
        if(user.loading){
            return user.text;
        }
        var userSearchTemplate= '<div class="search-user-card">'+
                                   '<span class="pic-standard-small"> <img src="<%= thumbnail %>" > </span>'+
                                   '<div class="search-user-card">'+
                                        '<div class="">'+
                                            '<span class="comment-by-name"><%=userName%></span> <span class="comment-by-role">PM</span>'+
                                        '</div>'+
                                        '<div class="">'+
                                            '<span class="comment-detail-time"><%=email%></span>'+
                                           '<span class="comment-by-role"><%=orgName%></span>'+
                                       '</div>'+
                                   '</div>'+
                                '</div>';

        var userName=user.firstName+","+user.lastName;
        var variables = {userName:userName,email:user.email,thumbnail:user.thumbnail,orgName:user.organization.orgName};
        var template = _.template(userSearchTemplate, variables );
        return template;
    },
    formatUserSelection:function(user){
       return user.email;
    },
});