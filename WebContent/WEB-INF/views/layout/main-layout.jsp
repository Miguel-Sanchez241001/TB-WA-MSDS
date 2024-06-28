<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@page import="pe.com.bn.msds.common.Util"%>
<%@page import="pe.com.bn.msds.common.Constant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

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

<title><%=Constant.VAR_GLB_COD_APLICATIVO%></title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bienvenido1.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bn-principal2.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bn-tipografiasLocal.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/css.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/home.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/softline-site.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/softline-controls.css" type="text/css"></link>

<script src="<%=request.getContextPath()%>/assets/js/jquery-1.11.1.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/project_ajax.js"></script>

<script type="text/javascript">
	function endSesion() {
		document.frmAction.action = "login";
		document.frmAction.submit();
	}
</script>

<script type="text/javascript">

	document.onkeypress = stopKey;
</script>

<script type="text/javascript">
	window.history.forward();

	function cargaInitMain() {
		window.history.forward();
		if (typeof (window.execProcessMain) == 'function') {
			execProcessMain();
		}
	}
	
</script>


</head>
<body>
	
        <div id="contenedor">
            <div id="cabecera" class="NoImprimir">
            	
            	<jsp:include page="../partials/header.jsp"></jsp:include>
                
            </div>
            <div id="cuerpo">
                <div id="bienvenidos-contenido">
                    <div id="border-superior" class="NoImprimir">
                        <img src="<%=request.getContextPath()%>/assets/img/body/border-superior3.jpg" alt="" width="1100">
                    </div>
                    <div id="datos-personales" class="limpiar NoImprimir">
                        <div>
                            <table width="100%">
                                <tbody>
                                    <tr>
                                        <td id="usuario" width="75%">
                                        	<sec:authorize access="isAuthenticated()">
												<table width="100%">
	                                                <tbody>
	                                                    <tr>
	                                                        <td id="nombres" style="width: 80%">
	                                                            <span id="etiqueta1" style="font-size:14px">Usuario:</span>
	                                                            <span id="userName"
	                                                                style="font-size:14px;color:#343434"><sec:authentication property="principal.nombre" />
	                                                            </span>
	
	                                                        </td>
<!-- 	                                                        <td id="oficinaUsu" style="width: 45%"> -->
<!-- 	                                                            <span id="etiqueta2" style="font-size:14px">Oficina:</span> -->
<!-- 	                                                            <span id="Oficina" -->
<%-- 	                                                                style="font-size:14px;color:#343434"><sec:authentication property="principal.seccion" /> --%>
<!-- 	                                                            </span> -->
<!-- 	                                                        </td> -->
	                                                        <td id="perfil" style="width: 20%">
	                                                            &nbsp;</td>
	                                                    </tr>
	                                                </tbody>
                                            	</table>
											</sec:authorize>
                                        </td>
                                        <td id="botones" width="25%">
                                        	
                                        	<c:url var="url" value="/" />
                                        	
                                            <table width="100%">
                                                <tbody>
                                                    <tr>
                                                        <td align="center">
                                                            <a 	href='<c:out value="${url}"/>home' 
                                                            	class="myButton"
                                                                title="Volver a Inicio"
                                                                style="font-size:14px;color:#C51416">
                                                                <img src="<%=request.getContextPath()%>/assets/img/icon/inicio.png">
                                                            </a>
                                                            <a 	href='<c:out value="${url}"/>home'  
                                                            	class="myButton"
                                                                title="Volver a Inicio"
                                                                style="font-size:14px;color:#C51416">
                                                                [ Inicio ]
                                                            </a>

                                                        </td>
                                                        <td align="center">
                                                            <a 	href='/logout' 
                                                            	class="myButton"
                                                                title="Cerrar Sesión"
                                                                style="font-size:14px;color:#C51416">
                                                                [ Cerrar Sesión ]
                                                            </a>

                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                    <div id="bn-contenidos" class="limpiar ">
                        
                        <div id="menu-internas" class="NoImprimir">
                        	
                        	<jsp:include page="../partials/menu.jsp"></jsp:include>
							
                        </div>
                        
                        <div id="contenidos-informativos">
							<!-- App container --> 
							<%
							 String htmlfilename = "../template/"
							 +(request.getAttribute("page") == null ? "bienvenida/welcome": request.getAttribute("page").toString()) 
							 + ".jsp";
							
							 %>
							 <jsp:include page="<%=htmlfilename%>" /> 
							 <!-- End App container -->
                        </div>
                    </div>
                    <div id="border-inferiror" class="NoImprimir">
                        <img src="<%=request.getContextPath()%>/assets/img/body/border-inferior1.jpg" alt="" width="1100">
                    </div>
                </div>
            </div>
            <div id="pie-pagina" class="NoImprimir">
                <div id="titulo-pie-pagina">
                    | Banco de la Nación |
                </div>
                <div id="oficinas">
                    <p>
                        Oficina Principal: Av. Javier Prado Este 2499. San Borja. Central Telefónica: 519-2000.
                    </p>
                    <p>
                        Atención en Oficinas Administrativas: Lunes a Viernes de 8:30 a 17:30 horas.&nbsp;
                    </p>

        		</div>
        	</div>
    	</div>

<!-- 	<iframe width=196 height=189 -->
<!-- 		name="gToday:normal:agenda.js" -->
<!-- 		id="gToday:normal:agenda.js" -->
<%-- 		src="<%=request.getContextPath()%>/assets/calendar/normal.html" --%>
<!-- 		scrolling="no" frameborder="0" -->
<!-- 		style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"> -->
<!-- 	</iframe> -->

</body>
</html>