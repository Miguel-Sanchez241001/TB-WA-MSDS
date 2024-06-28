<%@page import="pe.com.bn.msds.common.Constant"%>
<% 
	response.setHeader("Cache-Control","no-cache"); 
	response.setHeader("Cache-Control"," no-store ");
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader ("Expires", 0); 
%>

<div id="logo-multired">
    <img src="<%=request.getContextPath()%>/assets/img/header/logo_REPR.jpg" alt="Logotipo de Factibilidad de Prestamo Multired">
</div>
<div id="logo-bn">
    <img src="<%=request.getContextPath()%>/assets/img/header/logo-bn.jpg" alt="Logotipo del Banco de la Nación">
</div>
