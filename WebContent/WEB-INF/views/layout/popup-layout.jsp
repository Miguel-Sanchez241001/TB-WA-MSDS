<%@page import="pe.com.bn.msds.common.Constant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", " no-store ");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>



<html>
<head>

<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Cache-Control" content="max-age=0" />
<meta http-equiv="Expires" content="0" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=Constant.VAR_GLB_COD_APLICATIVO%></title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/fontTableroPic1.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/fontTableroPic2.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/stylesTable.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/styles.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/displaytag.css" type="text/css"></link>

<script src="<%=request.getContextPath()%>/assets/js/jquery-1.11.1.js"></script>

</head>

<body>

	<table style="width: 100%; height: 100%;" class="rxsview">
		<tr>
			<td valign="top" class="rxborderwhite">
<!-- App container --> 
<%
String htmlfilename = "../template/" 
	+(request.getAttribute("page") == null ? "bienvenida/welcome":request.getAttribute("page").toString()) 
	+".jsp";
 %><jsp:include page="<%=htmlfilename%>" /> 
<!-- End App container -->
			</td>
		</tr>
	</table>

</body>
</html>