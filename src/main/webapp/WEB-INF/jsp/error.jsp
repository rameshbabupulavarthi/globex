<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="/webresources/javascript/jquery.js"></script>
<script>
	var ctx = "${ctx}";
	/* if student is rendered to the Whoopsie due to non-availability of assignment, he should be
	 * again rendered to the Dashboard on clicking on the "[Please Click to proceed ]"
	 */
	$(document).ready(function(){
		$('.render-user-dash-board').click(function(e){
			var redirectUrl = $(e.currentTarget).attr('url');
			if (!redirectUrl || redirectUrl == 'DEFAULT') {
				redirectUrl = ctx+"/";
			}
			window.location.href = redirectUrl;
		});
	});
</script>

<style>
	* {
		margin: 0;
		padding: 0;
	}
	
</style>

<title>Whoopsie!</title>

<body style="background:transparent url('/webresources/images/app/bg-5px.png') repeat scroll 0 0;font-family: 'HelveticaNeue-BoldCondensed', 'Helvetica Neue Bold Condensed', 'Helvetica Neue', 'Helvetica', 'Arial Narrow', 'Arial', sans-serif;">

	<div align="left" show="hidden">

	</div>

	<div
		style="margin:0px auto;background:#ffffff;height: 530px;width: 1068px; border: solid 1px #ccc; margin-top: 20px;">
		
		<div style="text-align: center;">
			<div style="margin-top: 95px; margin-bottom: 20px;">
				<img src="/webresources/images/app/whoopsie.png" />
			</div>
			<div style="font:'Open Sans',sans-serif;">
			<c:choose>
				<c:when test="${not empty model.errorCode}">
					<c:choose>
					<c:when test="${model.errorCode eq 7}">
						<p style="line-height: 28px; font-weight: normal; font-size: 26px; padding-bottom: 18px;">The assignment you are trying to access is no longer available.
						</p>
						<p style="font-weight: normal; font-size:22px;"> Please contact your system administrator.</p>
					</c:when>
                 </c:choose>
				</c:when>
				<c:otherwise>
					<p style="line-height: 28px; font-weight: normal; font-size: 26px; padding-bottom: 18px;">We encountered a problem while processing your request.
					</p>
					<p style="font-weight: normal; font-size:16px;">If you are seeing this frequently, please contact your system administrator for help.</p>
				</c:otherwise>
			</c:choose>
				<b style="font-size: 16px; margin-top: 60px; display: block;">
					<a style="color: #000000; text-decoration: none; cursor : pointer;" class = "render-user-dash-board" url = "${model.redirectUrl}">[Please Click to proceed ]</a>
				</b>
			</div>
			
		</div>
	</div>

</body>

