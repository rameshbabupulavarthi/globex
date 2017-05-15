var UserDetailView =Backbone.View.extend({
    model:UserModel,
    events: {
        //'click #saveUser' :'saveUserDetails'
        "click .cancel-button":"cancel",
    },
    render: function(){
        var _self=this;
        require(['text!'+'templates/user/user_details.html'], function(user_details) {
            var variables ={id:"",userName:"",firstName:"",lastName:"",email:"",status:"",
                    phoneCountryCode:"",phoneAreaCode:"",phone:"",phoneExtension:"",faxCountryCode:"",faxAreaCode:"",fax:"",
                    mobileCountryCode:"",mobile:"",userType:"",comments:"",status:"",address:"",city:"",state:"",country:"",zip:"",
                    branchOffice:"",thumbnail:"",userType:"",loggedInUserRole:$("#currentUserRole").val()
            };
            if(_self.model){
                variables ={  id:_self.model.get("userId"),firstName:_self.model.get("firstName"),lastName:_self.model.get("lastName"),email:_self.model.get("email"),
                              userName:_self.model.get("userName"),userType:_self.model.get("userType"),status:_self.model.get("status"),
                              phoneCountryCode:_self.model.get("phoneCountryCode"),phoneAreaCode:_self.model.get("phoneAreaCode"),phone:_self.model.get("phone"),
                              phoneExtension:_self.model.get("phoneExtension"),faxCountryCode:_self.model.get("faxCountryCode"),faxAreaCode:_self.model.get("faxAreaCode"),
                              fax:_self.model.get("fax"),mobileCountryCode:_self.model.get("mobileCountryCode"),mobile:_self.model.get("mobile"),
                              userType:_self.model.get("userType"),comments:_self.model.get("comments"),address:_self.model.get("address"),city:_self.model.get("city"),
                              state:_self.model.get("state"),country:_self.model.get("country"),zip:_self.model.get("zip"),branchOffice:_self.model.get("branchOffice"),
                              thumbnail:_self.model.get("thumbnail"),loggedInUserRole:$("#currentUserRole").val()
                           };
            }
             user_details = _.template( user_details, variables );
            _self.$el.html(user_details);
            _self.$el.find("[name='userType']").val(variables.userType);
            _self.validateDetails();
        });
    },
    validateDetails:function(){
        var _self=this;
        require(["jquery.validate"],function(){
            _self.$el.find("#saveUserDetails").validate({
                invalidHandler: function(e, validator) {},
                rules: {
                    firstName:"required",
                    lastName:"required",
                    email:"required",
                    phone:"required",
                    fax:"required",
                    mobileNumber:"required",
                    userName:"required",
                    password:"required",
                    userType:"required",
                    phone: {
                        required: true,
                        number: true,
                        minlength: 5,
                        maxlength: 10,
                    },
                    mobileNumber: {
                        required: true,
                        number: true,
                        minlength: 5,
                        maxlength: 10,
                    },
                    email: {
                        required: true,
                        email: true,
                        maxlength: 255
                    }
                },
                messages: {
                    phone:{
                        required: "<div class='validation-mes'>Please provide your phone no.</div>",
                        maxlength: "The maximum allowed length is {0} characters.",
                        noSpecialChars: "<div class='validation-mes'>No special characters allowed.</div>"
                    },
                    email: {
                        required: "<div class='validation-mes' id='email-validation-mes'>Please enter a valid email address.</div>",
                        email: "<div class='validation-mes' id='email-validation-mes'>Please enter a valid email address.</div>",
                        noSpecialChars: "<div class='validation-mes'>Enter a valid email address.</div>",
                        maxlength: "<div class='validation-mes'>The maximum allowed length is {0} characters.</div>"
                    }
                },
                submitHandler: function(form) {
                    $(".loading-icon-wrapper").show();
                    $("body").css({opacity:0.5});

                    var form=document.getElementById("saveUserDetails");
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
                            setTimeout(function(){
                             $(".loading-icon-wrapper").hide();
                             $("body").css({opacity:1})
                           }, 3000);
                        },
                        success: function(userJSON) {
                         $(".loading-icon-wrapper").hide();
                         $("body").css({opacity:1});
                         $(".navigate-manage-user").trigger("click");
                         console.log("success"+userJSON);
                        }
                    });
                }
            });
        });
    },
    cancel:function(e){
        e.preventDefault();
        $(".navigate-manage-user").trigger("click");
    }
});



/**popup view to display user**/
var UserProfileView =Backbone.View.extend({
    el:"#layout-body-content",
    model:UserModel,
    events: {

    },
    render: function(){
        var _self=this;
        var userId=this.model.get("userId");
        this.model.fetch({
            data: {userId:userId},
            type: 'POST',
            success: function(collection, response){
                var user=response;
                var fullName=user.firstName+","+user.lastName;
                var userModel = new UserModel({
                    userId:user.id,
                    userName:user.userName,
                    firstName:user.firstName,
                    lastName:user.lastName,
                    name:fullName,
                    userType:user.userType,
                    email:user.email,
                    status:user.status,
                    phoneCountryCode:user.phoneCountryCode,
                    phoneAreaCode:user.phoneAreaCode,
                    phone:user.phone,
                    phoneExtension:user.phoneExtension,
                    faxCountryCode:user.faxCountryCode,
                    faxAreaCode:user.faxAreaCode,
                    fax:user.fax,
                    mobileCountryCode:user.mobileCountryCode,
                    mobile:user.mobile,
                    userType:user.userType,
                    comments:user.comments,
                    address:user.address,
                    city:user.city,
                    state:user.state,
                    country:user.country,
                    zip:user.zip,
                    branchOffice:user.branchOffice,
                    thumbnail:user.thumbnail,
                    loggedInUserRole:$("#currentUserRole").val()
                });
                var userDetailView =new UserDetailView({el:"#layout-body-content",model:userModel});
                _self.$el.empty();
                userDetailView.render();
            }
        });
    }
});
/**popup view to display user**/


/**popup view to display user**/
var UserPopupView =Backbone.View.extend({
    model:UserModel,
    events: {
        "click .cancel-button":"cancel"
    },
    render: function(){
        var _self=this;
        require(['text!'+'templates/user/user_edit_view.html'], function(user_details) {
            var popupView=new PopupView({el:"#popupWrapper"});
            popupView.render();
            var variables ={ id:_self.model.get("userId"),firstName:_self.model.get("firstName"),lastName:_self.model.get("lastName"),email:_self.model.get("email"),
                             userName:_self.model.get("userName"),userType:_self.model.get("userType"),status:_self.model.get("status"),
                             phoneCountryCode:_self.model.get("phoneCountryCode"),phoneAreaCode:_self.model.get("phoneAreaCode"),phone:_self.model.get("phone"),
                             phoneExtension:_self.model.get("phoneExtension"),faxCountryCode:_self.model.get("faxCountryCode"),faxAreaCode:_self.model.get("faxAreaCode"),
                             fax:_self.model.get("fax"),mobileCountryCode:_self.model.get("mobileCountryCode"),mobile:_self.model.get("mobile"),
                             userType:_self.model.get("userType"),comments:_self.model.get("comments"),address:_self.model.get("address"),city:_self.model.get("city"),
                             state:_self.model.get("state"),country:_self.model.get("country"),zip:_self.model.get("zip"),branchOffice:_self.model.get("branchOffice"),
                             thumbnail:_self.model.get("thumbnail"),loggedInUserRole:$("#currentUserRole").val()
                          };
            var template = _.template( user_details, variables );
            popupView.$el.find("#popupContainer").addClass("user-popup");
            popupView.$el.find("#popup-content").html(template);
            popupView.$el.find("[name='userType']").val(variables.userType);
            popupView.$el.find("#popup-title").html("User Details");

            popupView.$el.find("#popupContainer .cancel-button").click(function(e){
                e.preventDefault();
                $(".popup-close").trigger("click");
                $(".navigate-manage-user").trigger("click");
            });

            _self.validateDetails();
        });
    },
    validateDetails:function(){
            var _self=this;
            require(["jquery.validate"],function(){
                $("#popupWrapper #saveUserDetails").validate({
                    invalidHandler: function(e, validator) {},
                    rules: {
                        firstName:"required",
                        lastName:"required",
                        email:"required",
                        phone:"required",
                        fax:"required",
                        mobileNumber:"required",
                        userName:"required",
                        password:"required",
                        userType:"required",
                        phone: {
                            required: true,
                            number: true,
                            minlength: 5,
                            maxlength: 10,
                        },
                        mobileNumber: {
                            required: true,
                            number: true,
                            minlength: 5,
                            maxlength: 10,
                        },
                        email: {
                            required: true,
                            email: true,
                            maxlength: 255
                        }
                    },
                    messages: {
                        phone:{
                            required: "<div class='validation-mes'>Please provide your phone no.</div>",
                            maxlength: "The maximum allowed length is {0} characters.",
                            noSpecialChars: "<div class='validation-mes'>No special characters allowed.</div>"
                        },
                        email: {
                            required: "<div class='validation-mes' id='email-validation-mes'>Please enter a valid email address.</div>",
                            email: "<div class='validation-mes' id='email-validation-mes'>Please enter a valid email address.</div>",
                            noSpecialChars: "<div class='validation-mes'>Enter a valid email address.</div>",
                            maxlength: "<div class='validation-mes'>The maximum allowed length is {0} characters.</div>"
                        }
                    },
                    submitHandler: function(form) {
                        $(".loading-icon-wrapper").show();
                        $("body").css({opacity:0.5});

                        var form=document.getElementById("saveUserDetails");
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
                                setTimeout(function(){
                                 $(".loading-icon-wrapper").hide();
                                 $("body").css({opacity:1})
                               }, 3000);
                            },
                            success: function(userJSON) {
                                $(".loading-icon-wrapper").hide();
                                $("body").css({opacity:1});
                                $(".popup-close").trigger("click");
                                $(".navigate-manage-user").trigger("click");
                            }
                        });
                    },
                });
            });
    },
    cancel:function(e){
        e.preventDefault();
        $(".popup-close").trigger("click");
        $(".navigate-manage-user").trigger("click");
    }
});
/**popup view to display user**/


var UserCollection=Backbone.Collection.extend({
	url: "/secure/getUsers",
});

UserListView =Backbone.View.extend({
    el:"#layout-body-content",
    tagName:'div',
    className:'table-container',
    headerTemplate:'<thead><tr class="header-row"> <th class="header-column">Name<div class="header-column-sort"></div></th>  <th class="header-column">User Role<div class="header-column-sort"></div></th> <th class="header-column">User Status<div class="header-column-sort"></div></th>'+
                    '<th class="header-column">Email<div class="header-column-sort"></div></th> <th class="header-column">Phone<div class="header-column-sort"></div></th>  <th class="header-column">View</th> <th class="header-column">Delete</th> </tr></thead>',
    events: {
        "click #createUser":"addUser"
    },
    render: function(){
        var $self=this;
        var pageNo=$self.pageNo;
        var pageData={pageNo:pageNo};
        var userCollection=new UserCollection();
        userCollection.fetch({
            data: pageData,
            type: 'POST',
            success: function(collection, response){
                $self.pageNo=response.pageNo;
                $self.$el.empty();

                var $user_list_container=$("<div/>" , {
                     "id":"user_list_container",
                     html:'<div class=""><div class="btn-wrapper"> <div id="createUser" class="add-user-button"><span class="add-button"></span> <span class="add-user-text" >Add User </span></div></div></div>'
                 });

                var $user_table=$("<table/>" , {
                    "class": "table-container",
                    "id": "table-container",
                    html:$self.headerTemplate
                });
                $self.$el.append($user_list_container);
                $user_list_container.append($user_table);
                var users=response.users;

                var index=0;
                _.each(users,function(user){
                    index++;
                    var rowClass=(index%2)==0?"table-column-even":"table-column-odd";
                    var fullName=user.firstName+","+user.lastName;
                    var roleStr="";
                    switch(user.userType){
                        case 1: roleStr="ROLE_SUPER_ADMIN";
                                break;
                        case 2: roleStr="ROLE_ADMIN";
                                break;
                        case 3: roleStr="ROLE_GLOBEX_ADMIN";
                                break;
                        case 4: roleStr="ROLE_GLOBEX_USER";
                                break;
                        case 5: roleStr="ROLE_PM_ADMIN";
                                break;
                        case 6: roleStr="ROLE_PM_USER";
                                break;
                        case 7: roleStr="ROLE_LM_ADMIN";
                                break;
                        case 8: roleStr="ROLE_LM_USER";
                                break;
                    }
                    var phoneNo="";
                    if(user.phoneCountryCode){
                       phoneNo=user.phoneCountryCode+"-";
                    }
                    if(user.phoneAreaCode){
                        phoneNo=phoneNo+user.phoneAreaCode+"-";
                    }
                    if(user.phone){
                        phoneNo=phoneNo+user.phone;
                    }
                    var userModel = new UserModel({
                        rowClass:rowClass,
                        userId:user.id,
                        userName:user.userName,
                        firstName:user.firstName,
                        lastName:user.lastName,
                        name:fullName,
                        userType:user.userType,
                        email:user.email,
                        status:user.status,
                        phoneCountryCode:user.phoneCountryCode,
                        phoneAreaCode:user.phoneAreaCode,
                        phone:user.phone,
                        phoneExtension:user.phoneExtension,
                        faxCountryCode:user.faxCountryCode,
                        faxAreaCode:user.faxAreaCode,
                        fax:user.fax,
                        mobileCountryCode:user.mobileCountryCode,
                        mobile:user.mobile,
                        userType:user.userType,
                        comments:user.comments,
                        address:user.address,
                        city:user.city,
                        state:user.state,
                        country:user.country,
                        zip:user.zip,
                        branchOffice:user.branchOffice,
                        thumbnail:user.thumbnail,

                        roleStr:roleStr,
                        phoneNo:phoneNo,
                        loggedInUserRole:$("#currentUserRole").val()
                       });
                    var userView = new UserView({model: userModel});
                    userView.render();
                    $user_table.append(userView.$el);
                },this);

                var pagingModel=new PagingModel({currentPage:response.pageNo,totalRecords:response.totalRecords});
                var pagingView=new PagingView({model:pagingModel});
                pagingView.pageContext=$self;
                pagingView.$pageContextEl=$self.$el.find("#user_list_container");
                pagingView.render();
            }
        });
    },
    addUser:function(){
        var userDetailView=new UserDetailView({el:"#layout-body-content"});
        this.$el.empty();
        userDetailView.render();
    }
});

var UserModel=Backbone.Model.extend({
    defaults: {
        status:'Active'
    },
    url:"/secure/editUser"
});

var UserView =Backbone.View.extend({
    tagName:"tr",
    className:"table-row",
    model : UserModel,
    events:{
        "click .edit-user":"editUser",
        "click .delete-user":"deleteUser"
    },
    userTemplate:'<td class="table-column"><%=name%></td>  <td class="table-column"><%=roleStr%></td> <td class="table-column"><%=status%></td>'+
                 '<td class="table-column"><%=email%></td> <td class="table-column"><%=phoneNo%></td>  <td class="table-column"><div class="edit-icon edit-user"></div></td> '+
                 '<td class="table-column"><div class="delete-icon delete-user"></div></td>',
    initialize: function(){},
    render: function(){
        var _self=this;
        var variables ={  id:_self.model.get("userId"),firstName:_self.model.get("firstName"),lastName:_self.model.get("lastName"),email:_self.model.get("email"),
                      name:this.model.get("name"),
                      userName:_self.model.get("userName"),userType:_self.model.get("userType"),status:_self.model.get("status"),
                      phoneCountryCode:_self.model.get("phoneCountryCode"),phoneAreaCode:_self.model.get("phoneAreaCode"),phone:_self.model.get("phone"),
                      phoneExtension:_self.model.get("phoneExtension"),faxCountryCode:_self.model.get("faxCountryCode"),faxAreaCode:_self.model.get("faxAreaCode"),
                      fax:_self.model.get("fax"),mobileCountryCode:_self.model.get("mobileCountryCode"),mobile:_self.model.get("mobile"),
                      userType:_self.model.get("userType"),comments:_self.model.get("comments"),address:_self.model.get("address"),city:_self.model.get("city"),
                      state:_self.model.get("state"),country:_self.model.get("country"),zip:_self.model.get("zip"),branchOffice:_self.model.get("branchOffice"),
                      thumbnail:_self.model.get("thumbnail"),loggedInUserRole:$("#currentUserRole").val(),
                      roleStr:_self.model.get("roleStr"),phoneNo:_self.model.get("phoneNo")
                   };
        var template = _.template( this.userTemplate, variables );
        var rowClass=this.model.get("rowClass");
        this.$el.addClass(rowClass);
        this.$el.append($(template));
        this.$el.find("[name='userType']").val(variables.userType);
    },
    editUser:function(){
        var userId=this.model.get("userId");
        $.ajax({
            type: 'POST',
            url: '/secure/editUser',
            data: {
                userId : userId
            },
            context: this,
            error: function() {},
            success: function(user) {

                var fullName=user.firstName+","+user.lastName;
                var userModel = new UserModel({
                    userId:user.id,
                    name:fullName,
                    userName:user.userName,
                    firstName:user.firstName,
                    lastName:user.lastName,
                    userType:user.userType,
                    email:user.email,
                    status:user.status,
                    phoneCountryCode:user.phoneCountryCode,
                    phoneAreaCode:user.phoneAreaCode,
                    phone:user.phone,
                    phoneExtension:user.phoneExtension,
                    faxCountryCode:user.faxCountryCode,
                    faxAreaCode:user.faxAreaCode,
                    fax:user.fax,
                    mobileCountryCode:user.mobileCountryCode,
                    mobile:user.mobile,
                    userType:user.userType,
                    comments:user.comments,
                    address:user.address,
                    city:user.city,
                    state:user.state,
                    country:user.country,
                    zip:user.zip,
                    branchOffice:user.branchOffice,
                    thumbnail:user.thumbnail,
                   });
                var userPopupView = new UserPopupView({model: userModel});
                userPopupView.render();
            }
        });
    },
    deleteUser:function(){
          var userId=this.model.get("userId");
          $.ajax({
              type: 'POST',
              url: '/secure/deleteUser',
              data: {
                  userId : userId
              },
              context: this,
              error: function() {},
              success: function(htmlData) {
                  this.remove();
              }
          });
    }
});


