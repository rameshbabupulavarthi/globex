<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html lang="en" xmlns:m="http://www.w3.org/1998/Math/MathML">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=1024, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta name="description" content="">
	<title> Globex World </title>
	<link rel="shortcut icon" type="image/ico" href="../webresources/images/logo.png">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,600,700' rel='stylesheet' type='text/css'>
    <link href="/webresources/css/jquery-ui.css" type="text/css" rel="stylesheet"></link>
    <link href="/webresources/css/globex.css" type="text/css" rel="stylesheet"></link>
    <link href="/webresources/css/dashboard.css" type="text/css" rel="stylesheet"></link>
    <link href="/webresources/css/pm.css" type="text/css" rel="stylesheet"></link>
    <link href="/webresources/css/jquery.tinyscrollbar.css" rel="stylesheet" type="text/css" />
    <script data-main="/webresources/javascript/globex" src="/webresources/javascript/require.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>

<body>
<div class="globex-layout">

    <div class="layout-body">

      <header class="layout-header">
        <div class="header-wrapper">

            <a class="navbar-brand" href="#">
                <img src="../webresources/images/logo.jpg" width="40px" height="40px">
            </a>

            <div class="header-logo">
                <div class="header-log-text">Globex World</div>
            </div>
            <div class="header-options">
                <div class="user-nav-options">
                    <span class="user-img"><img src="${model.user.thumbnail}"></span>
                    <span class="user-name">${model.user.fullName}</span>
                    <i class="drop-down-img"></i>
                </div>
                <div class="profile-drop-down">
                    <ul class="user-nav-ul">
                        <li class="navigate-user-profile"><a href="javascript:void(0);">Profile</a><//li>
                        <li><a href="/logout">Logout</a></li>
                    </ul>
                </div>
            </div>
        </div>
       </header>

        <!-- menu -->
       <div class="layout-body-menu">
               <div class="menu-container">

                 <ul class="menu-ul">
                     <li class="menu-group-item-text menu-toggle-content">GENERAL</li>
                     <li class="menu-group-item">
                       <ul>
                          <li class="menu-item navigate-dashboard">
                             <span class="menu-item-wrapper"><span class="menu-item-img menu-item-dashboard"></span>
                             <span class="menu-item-text menu-toggle-content">Dashboard</span>
                          </li>
                           <li class="navigate-manage-user">
                            <span class="menu-item-wrapper"><span class="menu-item-img menu-item-user"></span></span>
                            <span class="menu-item-text menu-toggle-content">Users</span>
                           </li>
                           <li>
                            <span class="menu-item-img"><span class="menu-item-img menu-item-users"></span></span>
                            <span class="menu-item-text menu-toggle-content">Client</span>
                           </li>
                       </ul>
                     </li>

                     <li class="menu-group-item-text menu-toggle-content">BUSINESS ACTIVITIES</li>
                     <li class="menu-group-item">
                       <ul>
                           <li class="navigate-pm-apps">
                              <span class="menu-item-wrapper"><span class="menu-item-img menu-item-dashboard"></span>
                              <span class="menu-item-text menu-toggle-content">Current Applications</span>
                           </li>
                           <li class="navigate-applications">
                              <span class="menu-item-wrapper"><span class="menu-item-img menu-item-dashboard"></span>
                              <span class="menu-item-text menu-toggle-content">New Application</span>
                           </li>
                           <li>
                             <span class="menu-item-wrapper"><span class="menu-item-img menu-item-dashboard"></span>
                             <span class="menu-item-text menu-toggle-content">Reports</span>
                           </li>
                       </ul>
                     </li>

                   <li class="menu-group-item-text menu-toggle-content">ACCOUNTINGS</li>
                   <li class="menu-group-item">
                       <ul>
                           <li>
                             <span class="menu-item-wrapper"><span class="menu-item-img menu-item-dashboard"></span>
                             <span class="menu-item-text menu-toggle-content">Receivables</span>
                           </li>
                           <li>
                             <span class="menu-item-wrapper"><span class="menu-item-img menu-item-dashboard"></span>
                             <span class="menu-item-text menu-toggle-content">Payables</span>
                           </li>
                       </ul>
                    </li>
                </ul>
                </div>
               </div>


        <div id="layout-body-content" class="layout-body-content">
            <div class="loading-icon-wrapper" >
                <img class="loading-icon" src="../webresources/images/loader.gif" width="130" height="130" alt="Image result for ajax loading gif transparent background">
            </div>



        </div>
    </div>

    <div id="dashboardJson" style="display:none;">
        <script type="application/json">
            ${model.dashboardJson}
        </script>
    </div>

    <div id="userJson" style="display:none;">
        <script type="application/json">
            ${model.user}
        </script>
    </div>

    <input type="hidden" id="currentUserId" value="${model.user.userId}">
    <input type="hidden" id="currentUserRole" value="${model.user.currentUserRole}">
</div>

<div id="popupWrapper">


</div>



</body>
</html>