<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String htmlfilename = "../template/"
	+(request.getAttribute("page") == null ? "bienvenida/welcome":request.getAttribute("page").toString()) 
	+".jsp";
%>
<jsp:include page="<%=htmlfilename%>" />
