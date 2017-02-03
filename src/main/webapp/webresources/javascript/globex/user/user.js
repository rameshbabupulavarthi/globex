var UserDetailView =Backbone.View.extend({
    events: {
        //'click #saveUser' :'saveUserDetails'
    },
    render: function(){
        var _self=this;
        require(['text!'+'templates/user/user_details.html'], function(user_details) {
            _self.$el.append(user_details);
            _self.validateDetails();
        });
    },
    validateDetails:function(){
        var _self=this;
        require(["jquery.validate"],function(){
            _self.$el.find("#saveUserDetails").validate({
                invalidHandler: function(e, validator) {},
                submitHandler: function(form) {
                    $(".loading-icon-wrapper").show();
                    $("body").css({opacity:0.5});

                    var form=document.getElementById("saveUserDetails");
                    var formData=new FormData(form);
                    $.ajax({
                        type: 'POST',
                        url: form.action,
                        data: formData,
                        error: function() {
                            setTimeout(function(){
                             $(".loading-icon-wrapper").hide();
                             $("body").css({opacity:1})
                           }, 3000);
                        },
                        success: function(userJSON) {
                            console.log("success"+userJSON);
                        }
                    });
                },
            });
        });
    }
});



var UserPopupView =Backbone.View.extend({
    model:UserModel,
    events: {

    },
    render: function(){

        var _self=this;
        require(['text!'+'templates/user/user_edit_view.html'], function(user_details) {
            var popupView=new PopupView({el:"#popupWrapper"});
            popupView.render();
            var variables ={id:_self.model.get("userId"),firstName:_self.model.get("firstName"),lastName:_self.model.get("lastName"),email:_self.model.get("email"),phone:_self.model.get("phone"),
                    userName:_self.model.get("userName"),role:_self.model.get("role"),status:_self.model.get("status")};
            var template = _.template( user_details, variables );

            popupView.$el.find("#popup-content").append(template);
            popupView.$el.find("#popup-title").html("User Details");
            _self.validateDetails();
        });


    },
    validateDetails:function(){
            var _self=this;
            require(["jquery.validate"],function(){
                _self.$el.find("#saveUserDetails").validate({
                    invalidHandler: function(e, validator) {},
                    submitHandler: function(form) {
                        $(".loading-icon-wrapper").show();
                        $("body").css({opacity:0.5});

                        var form=document.getElementById("saveUserDetails");
                        var formData=new FormData(form);
                        $.ajax({
                            type: 'POST',
                            url: form.action,
                            data: formData,
                            error: function() {
                                setTimeout(function(){
                                 $(".loading-icon-wrapper").hide();
                                 $("body").css({opacity:1})
                               }, 3000);
                            },
                            success: function(userJSON) {
                                console.log("success"+userJSON);
                            }
                        });
                    },
                });
            });
    }
});


var UserCollection=Backbone.Collection.extend({
	url: "/secure/getUsers",
});

UserListView =Backbone.View.extend({
    el:"#layout-body-content",
    tagName:'div',
    className:'table-container',
    headerTemplate:'<thead><tr class="header-row"> <th class="header-column">Name</th>  <th class="header-column">User Role</th> <th class="header-column">User Status</th>'+
                    '<th class="header-column">Email</th> <th class="header-column">Phone</th>  <th class="header-column">View</th> <th class="header-column">Delete</th> </tr></thead>',
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
                $self.$el.html("");

                var $user_list_container=$("<div/>" , {
                     "id":"user_list_container",
                     html:'<div class="btn-wrapper"> <div id="createUser" class="add-user-button"><span class="add-button"></span> <span class="add-user-text" >Add User </span></div></div>'
                 });

                var $user_table=$("<table/>" , {
                    "class": "table-container",
                    "id": "table-container",
                    html:$self.headerTemplate
                });
                $self.$el.append($user_list_container);
                $user_list_container.append($user_table);
                var users=response.users;

                _.each(users,function(user){
                    var fullName=user.firstName+","+user.lastName;
                    var userModel = new UserModel({
                        userId:user.id,
                        userName:user.userName,
                        firstName:user.firstName,
                        lastName:user.lastName,
                        name:fullName,
                        role:user.role,
                        //status:user.status,
                        email:user.email,
                        phone:user.telephone,
                       });
                    var userView = new UserView({model: userModel});
                    userView.render();
                    $user_table.append(userView.$el);

                },this);
            }
        });
    },
    addUser:function(){
        var userView=new UserDetailView({el:"#layout-body-content"});
        this.$el.html("");
        userView.render();
    }
});

var UserModel=Backbone.Model.extend({
    defaults: {
        status:'Active'
    }
});

var UserView =Backbone.View.extend({
    tagName:"tr",
    className:"table-row",
    model : UserModel,
    events:{
        "click .edit-user":"editUser",
        "click .delete-user":"deleteUser"
    },
    userTemplate:'<td class="table-column"><%=name%></td>  <td class="table-column"><%=role%></td> <td class="table-column"><%=status%></td>'+
                 '<td class="table-column"><%=email%></td> <td class="table-column"><%=phone%></td>  <td class="table-column"><div class="edit-icon edit-user"></div></td> '+
                 '<td class="table-column"><div class="delete-icon delete-user"></div></td>',
    initialize: function(){},
    render: function(){
        var variables = {name:this.model.get("name"),role:this.model.get("role"),status:this.model.get("status"),email:this.model.get("email"),
                            phone:this.model.get("phone")};
        var template = _.template( this.userTemplate, variables );
        this.$el.append($(template));
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
                    role:user.role,
                    //status:user.status,
                    email:user.email,
                    phone:user.telephone,
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


