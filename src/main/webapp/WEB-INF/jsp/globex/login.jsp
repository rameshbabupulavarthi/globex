<%@ page session="false"%>
<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en" data-ng-app="gwApp">

<head>
    <title>Globex World</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale = 1.0" />
    <meta name="description" content="GlobexWorld" />
    <meta name="keywords" content="GlobexWorld" />
    
    <link href="/webresources/css/site.min.css" type="text/css" rel="stylesheet" />
</head>

<body>
        <div class="login-page-wrapper" data-ng-controller="signInCtrl as ctrl">
        <img src="/webresources/images/bg0.jpg" class="page-bg" />
        <div class="login-form-wrapper">
            <h2>
                <img src="/webresources/images/logo.jpg" alt="Globex" width="200" />
                <span class="top-txt">Multinational Underwriting Services</span>
                <span>Globex World</span>
            </h2>
            <p class="server-err-msg">${error}</p>
            <form class="form-list" autocomplete="off" method="POST" action="/j_spring_security_check">
                <ol>
                    <li class="login-border mLeft mbot10" data-ng-class="{'error-highlight': ctrl.signInForm.submitted && (ctrl.signInForm.username.$error.required || ctrl.signInForm.username.$error.pattern)}">
                        <span class="login-icons icon-user"></span>
                        <label for="email">Email</label>
                        <input class="mLeft noBorder" id="username" name="j_username" type="text" placeholder="Username" autocomplete="off"  />
                        <span class="form-error-msg-inline hide" data-ng-if="ctrl.signInForm.submitted && ctrl.signInForm.username.$error.required" data-ng-class="{'show': ctrl.signInForm.submitted && ctrl.signInForm.username.$error.required}">Required</span>
                    </li>
                    <li class="login-border mLeft mbot10" data-ng-class="{'error-highlight': ctrl.signInForm.submitted && ctrl.signInForm.password.$error.required}">
                        <span class="login-icons icon-lock"></span>
                        <label for="password">Password</label>
                        <input class="mLeft noBorder" type="password" id="password" name="j_password" placeholder="Password" autocomplete="off" />
                        <span class="form-error-msg-inline hide" data-ng-if="ctrl.signInForm.submitted && ctrl.signInForm.password.$error.required" data-ng-class="{'show':ctrl.signInForm.submitted && ctrl.signInForm.password.$error.required}">Required</span>
                    </li>
                    <li class="form-btns-wrapper">
                        <input type="submit" value="Login" name="submit" class="login-btn" />
                    </li>
                </ol>
            </form>
            <a href="forgotpwd" class="login-link">Forgot Password?</a>
        </div>
        
        <div class="footer-generic">
            <p>&copy; 2016 Globex International Group. All rights reserved.</p>
            <ul>
                <li>
                    <a href="#">Terms of service</a>
                </li>
                <li>
                    <a href="#">Privacy Policy</a>
                </li>
                <li>
                    <a href="#">Contact Us</a>
                </li>
            </ul>
        </div>
    </div>
</body>

</html>
