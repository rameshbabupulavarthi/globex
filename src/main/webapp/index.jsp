<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%

ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()); 
RequestDispatcher rd = null;
String principal = null;
Boolean isAuthenticated = Boolean.FALSE;
if(SecurityContextHolder.getContext()!=null
		&& SecurityContextHolder.getContext().getAuthentication() !=null){
	isAuthenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
	if(SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null){
		principal  = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
	}
}

rd = request.getRequestDispatcher("/index");
rd.forward(request, response);

%>