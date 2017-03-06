
var MessageCollection=Backbone.Collection.extend({
	url: "/secure/viewCommunications"
});

MessageDetailView=Backbone.View.extend({
    render: function() {
        var $self=this;
        var pageNo=$self.pageNo;
        var pageData={pageNo:pageNo};
        var messageCollection=new MessageCollection();
        messageCollection.fetch({
            data: pageData,
            type: 'POST',
            success: function(collection, response){
                $self.pageNo=response.pageNo;
                $self.$el.html("");
                for(var i=0; i< response.messages.length; i++){
                    var messageModel = new MessageModel({
                        senderName:"Charlie",
                        messageTime: " 02:25 AM",
                        senderImage:"https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg" ,
                        messageContent:"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
                    });
                    var messageView = new MessageView({model: messageModel});
                    messageView.render();
                    $self.$el.append(messageView.$el);
                }
            }
        });

        $(window).off("scroll").on("scroll", function() {
            $self.renderPage();
        });
    },
    renderPage:function(){
        var $self=this;
        var pageNo=$self.pageNo;
        var pageData={pageNo:pageNo};
        var messageCollection=new MessageCollection();
        messageCollection.fetch({
            data: pageData,
            type: 'POST',
            success: function(collection, response){
                $self.pageNo=response.pageNo;
                $self.$el.html("");
                for(var i=0; i< response.messages.length; i++){
                    var messageModel = new MessageModel({
                        senderName:"Charlie",
                        messageTime: " 02:25 AM",
                        senderImage:"https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg" ,
                        messageContent:"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
                    });
                    var messageView = new MessageView({model: messageModel});
                    messageView.render();
                    $self.$el.append(messageView.$el);
                }
            }
        });
    }
});

var MessageModel=Backbone.Model.extend({
    defaults: {
        senderName:"Charlie",
        messageTime: " 02:25 AM",
        senderImage:"https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg" ,
        messageContent:"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
    },
    initialize: function(opts){

    }
});

var MessageView=Backbone.View.extend({
    //el:".message-holder",
    events:{

    },
    model : MessageModel,
    messageTemplate:'<div class="message-item"><span class="message-title-content"><span class="message-sender-name"><%= senderName %></span>'+
                    '<span class="message-time"><%= messageTime %></span></span>'+
                    '<span class="message-sender-img"><img src="<%= senderImage %>" ></span><span class="message-content"><%= messageContent %></span></div>',

    initialize: function(opts) {

    },
    render: function() {
        var variables = {senderName:this.model.get("senderName"),messageTime:this.model.get("messageTime"),
                senderImage:this.model.get("senderImage"),messageContent:this.model.get("messageContent")};
        var template = _.template( this.messageTemplate, variables );
        this.$el.append($(template));
    }
});




