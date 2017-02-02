<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html lang="en" xmlns:m="http://www.w3.org/1998/Math/MathML">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=1024, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta name="description" content="">
	<title> Globex World </title>
	<link rel="shortcut icon" type="image/ico" href="${model.titleIconUrl}">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,600,700' rel='stylesheet' type='text/css'>
    <link href="/webresources/css/jquery-ui.css" type="text/css" rel="stylesheet"></link>
    <link href="/webresources/css/lm.css" type="text/css" rel="stylesheet"></link>
    <link href="/webresources/css/globex.css" type="text/css" rel="stylesheet"></link>
    <link href="/webresources/css/jquery.tinyscrollbar.css" rel="stylesheet" type="text/css" />
    <script data-main="/webresources/javascript/globex" src="/webresources/javascript/require.js"></script>
    <link href="/webresources/javascript/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/webresources/javascript/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="data:text/css;charset=utf-8," rel="stylesheet" data-href="/webresources/javascript/bootstrap/css/bootstrap-theme.min.css" id="bs-theme-stylesheet">
</head>

<body>
<div class="globex-layout">

    <div class="layout-header lm-background-shadow">
        <span class="prd-logo">
            <img src="../webresources/images/logo.jpg" alt="Globex World"/>
        </span>
        <span class="header-logo">
            <div class="header-log-text">Globex World</div>
        </span>
        <span class="header-options">
            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
            <span class="glyphicon glyphicon-bell" aria-hidden="true"></span>
            <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
            <span class="glyphicon glyphicon-bookmark" aria-hidden="true"></span>
        </span>
    </div>

    <div class="layout-body">
        <div class="layout-body-menu lm-background-shadow">
            <ul class="menu-ul">
                <li><span class="glyphicon glyphicon-th-large lm-menu-item" group="lm-org-details" target="lm-registration-step1" aria-hidden="true"></span></li>
                <li><span class="glyphicon glyphicon-user lm-menu-item " group="lm-finance-details" target="lm-registration-step2" aria-hidden="true"></span></li>
                <li><span class="glyphicon glyphicon-object-align-top lm-menu-item " group="lm-network-details" target="lm-registration-step3" aria-hidden="true"></span></li>
                <li><span class="glyphicon glyphicon-education lm-menu-item " group="lm-acount-details" target="lm-registration-step4" aria-hidden="true"></span></li>
                <li><span class="glyphicon glyphicon-list-alt lm-menu-item " group="lm-dept-details" target="lm-registration-step5" aria-hidden="true"></span></li>
            </ul>
        </div>

        <div id="layout-body-content" class="layout-body-content lm-background-shadow">
            <div class="loading-icon-wrapper" >
                <img class="loading-icon" src="../webresources/images/loader.gif" width="130" height="130" alt="Image result for ajax loading gif transparent background">
            </div>

        <div id="lm-register-form">
            <%@include file="/webresources/javascript/templates/lm/lmRegistrationForm.html"%>
            <div id="lmUserRegistrationJSONData" style="display: none;">
                <script type="application/json">${model.partnerMarket}</script>
            </div>
        </div>

        </div>
    </div>
</div>



    <footer class="footer">
        <div class="footer-content">

        </div>
    </footer>
</body>
</html>