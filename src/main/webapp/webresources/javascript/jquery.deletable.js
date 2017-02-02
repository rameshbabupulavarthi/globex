(function( $ ){
  var settings = {};
  var methods = {
    init : function(options) {    
    	settings = $.extend({
    		minclasscount:0
          },options);
    	
    	if(this.length > settings.minclasscount)
    	{
	        return this.each(function(){
	
		        	$(this).mouseleave(function(event) {
		        		$(this).find(".delete").remove();
		        		event.stopPropagation();
		   		    	return false;        		
					});	
		        	
		        	$(this).mouseenter(function(event) {
		        		$(this).find(".delete").remove();
		        		$(this).append($("<span/>",{
		            	   "class":"delete",
			        	   id:"delete-"+$(this).attr("id"),
			        	   contentEditable:"false",
			               css : {
			            	    backgroundColor: "white",
			               		color: "gray",
			               		cursor: "pointer",
			               		fontSize: "18px",
			               	   	fontWeight: "bold",
			               	   	zIndex:1001
			               },
			               text:" x ",
			               mouseup:function(e){
								e.stopPropagation();
								return false;
			               },
			               click:function select(e) 
			   		       {
			   		    		$(this).parent().remove();
			   		    		e.stopPropagation();
			   		    		return false;
			   		       }			    
		        		}).disableTextSelect());
		        		event.stopPropagation();
		   		    	return false;
		        	});
	        });
    	}
    },
    close : function(save) {
      // IS
    	return this.each(function(){
    		$(this).find(".delete").remove();
    	});
    },
    cancel : function( ) { 
    }
  };

  $.fn.deletable = function( method ) {
    
    // Method calling logic
    if ( methods[method] ) {
      return methods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
    } else if ( typeof method === 'object' || ! method ) {
      return methods.init.apply( this, arguments );
    } else {
      $.error( 'Method ' +  method + ' does not exist on jQuery.htmlEditor' );
    }    
  
  };

})( jQuery );
