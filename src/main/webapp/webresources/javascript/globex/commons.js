/**paging view*/

PagingModel=Backbone.Model.extend({
    defaults: {
        currentPage:1,
        totalRecords:100,
        pagingLimit:10,
        pageSize:10
    }
});

PagingView=Backbone.View.extend({
    el:$('<div/>',{class:'page-container'}),
    model:PagingModel,
    template:'<div class="paging-section">'+
                  '<div class="paging-wrapper">'+
                      '<span class="nav-first-pages nav-page-options"> << </span>'+
                      '<span class="nav-prev-page nav-page-options"> < </span>'+
                      '<% for(var i=startPage;i<endPage;i++) {%>'+
                          '<% if(i==currentPage){ %>'+
                          '<span class="currentPage" pageNo="<%= i %>"><%= i+1 %></span>'+
                          '<% }else{ %>'+
                          '<span class="page" pageNo="<%= i %>"><%= i+1 %></span>'+
                          '<% } %>'+
                      '<%}%>'+
                      '<span class="nav-next-page nav-page-options"> > </span>'+
                      '<span class="nav-last-pages nav-page-options"> >> </span>'+
                  '</div>'+
              '</div>',
    events:{
        "click .paging-wrapper .page":'renderPage',
        "click .paging-wrapper .nav-first-pages":'renderFirstPages',
        "click .paging-wrapper .nav-prev-page":'renderPrev',
        "click .paging-wrapper .nav-next-page":'renderNext',
        "click .paging-wrapper .nav-last-pages":'renderLastPages'
    },
    render: function() {
        this.$el.empty();
        var pagingLimit=this.model.get("pagingLimit");
        var currentPage=this.model.get("currentPage");
        var totalRecords=this.model.get("totalRecords");
        var totalPages=totalRecords/pagingLimit;
        var startPage=0;
        var remainder=(currentPage%pagingLimit);
        if(remainder>0){
            startPage=(currentPage-remainder);
        }else if(remainder==0){
            startPage=currentPage;
        }else{
            startPage=currentPage>pagingLimit?(currentPage-pagingLimit):0;
        }
        endPage=startPage+pagingLimit;
        endPage=(endPage>totalPages)?totalPages:endPage;

        var variables={startPage:startPage,endPage:endPage,totalPages:totalPages,currentPage:currentPage};
        var template = _.template(this.template, variables);
        this.$el.append(template);
        this.$pageContextEl.prepend(this.$el);
    },
    renderPage:function(e){
        var pageNo=$(e.currentTarget).attr("pageNo");
        this.navigateToPage(pageNo);
    },
    navigateToPage:function(pageNo){
        var pageContext=this.pageContext;
        this.destroy();
        pageContext.pageNo=pageNo;
        pageContext.render();
    },
    renderPrev:function(){
        var pagingLimit=this.model.get("pagingLimit");
        var currentPage=this.model.get("currentPage");
        var prevPage=(currentPage-(currentPage%pagingLimit))-pagingLimit;
        if(prevPage>=0){
            this.navigateToPage(prevPage);
        }
    },
    renderNext:function(){
        var pagingLimit=this.model.get("pagingLimit");
        var currentPage=this.model.get("currentPage");
        var nextPage=(currentPage-(currentPage%pagingLimit))+pagingLimit;
        var totalRecords=this.model.get("totalRecords");
        var pageSize=this.model.get("pageSize");
        var totalPages=parseInt(totalRecords/pageSize);
        if(nextPage<totalPages){
            this.navigateToPage(nextPage);
        }
    },
    renderFirstPages:function(){
        var currentPage=0;
        this.navigateToPage(currentPage);
    },
    renderLastPages:function(){
        var totalRecords=this.model.get("totalRecords");
        var pagingLimit=this.model.get("pagingLimit");
        var pageSize=this.model.get("pageSize");
        var totalPages=parseInt(totalRecords/pageSize);
        var remainder=(totalPages%pagingLimit);
        remainder=remainder>0?remainder:10;
        var currentPage=totalPages-remainder;
        this.navigateToPage(currentPage);
    },
    destroy:function(){
        this.$el.empty();
    }
});