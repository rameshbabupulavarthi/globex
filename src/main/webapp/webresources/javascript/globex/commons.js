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


                  '<div class="filter-wrapper">'+
                     '<div class="filter-section"><div class="popup-header" >Filter</div>'+
                        '<div class="filter-data"></div>'+
                        '<div class="form-column">'+
                            '<div>'+
                                '<div class="save-filter form-button">Save</div>'+
                                '<div class="cancel-filter form-button">Cancel</div>'+
                            '</div>'+
                        '</div>'+
                     '</div>'+
                  '</div>'+

              '</div>',
    events:{
        "click .paging-wrapper .page":'renderPage',
        "click .paging-wrapper .nav-first-pages":'renderFirstPages',
        "click .paging-wrapper .nav-prev-page":'renderPrev',
        "click .paging-wrapper .nav-next-page":'renderNext',
        "click .paging-wrapper .nav-last-pages":'renderLastPages',

        'click .save-filter':'saveFilters',
        'click .cancel-filter':'hideFilters',
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
    },
    saveFilters:function(){
        var $filterFields=$(".filter-field");
        var filter={};
        for(var i=0;i<$filterFields.length;i++){
           var $filterField= $($filterFields[i]);
           var filterFieldName=$filterField.attr("name");
           var filterFieldValue;
           if($filterField.hasClass("type-int")){
               filterFieldValue=parseInt($filterField.val());
           }else{
                filterFieldValue=$filterField.val();
           }
           if(filterFieldValue){
                filter[filterFieldName]=filterFieldValue;
           }
        }
        this.pageContext.filterData=filter;
        this.pageContext.render();
    },
    hideFilters:function(){
        this.$el.find(".filter-wrapper").hide();
    }
});

FilterView=Backbone.View.extend({
    el:$('<div/>',{class:'filter-container'}),
    events:{
        'click .filter-list':'showFilters',
        'mouseout .filter-wrapper':'hideFilters',
    },
    template:'<div class="">'+
                '<div class="popup-content">'+
                   '<div class="">Filter</div>'+
                   '<div class="popup-body">'+

                   '</div>'+
                '</div>'+
             '</div>',
    render: function() {
        var template = this.template;
        this.$el.html($(template));
        this.$pageContextEl.prepend(this.$el);
        /*$("body").addClass("blur-background");
        this.$el.find("#popupContainer").show("slow",function(){ });*/

    },
    showFilters:function(){
        var template = this.template;
        this.$el.html($(template));
    },
    hideFilters:function(){

    }
});


function getPhoneNumber(user){
    var phoneNo="";
    if(user && user.phoneCountryCode){
       phoneNo=user.phoneCountryCode+"-";
    }
    if(user && user.phoneAreaCode){
        phoneNo=phoneNo+user.phoneAreaCode+"-";
    }
    if(user && user.phone){
        phoneNo=phoneNo+user.phone;
    }
    return phoneNo;
}

function getUserName(user){
    if(user){
        var fullName=user.firstName+","+user.lastName;
        return fullName;
    }
}


function setValues(){

    var $selectBoxes=$("select");
    for(var i=0;i<$selectBoxes.length;i++){
        var $selectBox=$($selectBoxes[i]);
        var value=$selectBox.attr("value");
        if(value){
            $selectBox.val(value);
        }
    }
}

function getAddress(address){
    if(user){
        var fullAddress=address.address1+","+address.address1;
        return fullAddress;
    }
}


function countrySelect($el){

        function formatCountryDisplay(country){
            if(country.loading){
                return country.text;
            }
            var countrySearchTemplate= '<div class="search-user-card">'+
                                           '<div class="search-user-card">'+
                                                '<div class="">'+
                                                    '<span class="comment-detail-time"><%=country%></span>'+
                                               '</div>'+
                                           '</div>'+
                                        '</div>';
            var countryData=country.country;
            var variables = {country:countryData};
            var template = _.template(countrySearchTemplate, variables );
            return template;
        }
        function formatSelection(country){
           return country.country;
        }

        $el.select2({
           /* initSelection : function (element, callback) {
                 var val=$el.val();
                 var data = {id: val, text: val};
                 callback(data);
             },
*/

             ajax: {
               url: "/secure/viewCountries",
               dataType: 'json',
               delay: 250,
               data: function (params) {
                 return {
                       country: params.term, // search term
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

                 if(data.countries){
                     var countryList=data.countries;
                     for(var i=0;i<countryList.length;i++){
                        var count=countryList[i];
                        //count.id=countryList[i].countryId;
                        count.id=countryList[i].country
                     }
                 }
                 return {
                   results: data.countries,
                   pagination: {
                     more: (params.pageNo * 10) < data.totalRecords
                   }
                 };
               },
               cache: true
             },
             escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
             minimumInputLength: 1,
             templateResult: formatCountryDisplay, // omitted for brevity, see the source of this page
             templateSelection: formatSelection // omitted for brevity, see the source of this page
           });
   }