<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@page import="pe.com.bn.msds.common.Util"%>
<%@page import="pe.com.bn.msds.common.DatosSesion"%>
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

<title><%=Constant.VAR_GLB_COD_APLICATIVO%></title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bienvenido1.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bn-principal2.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bn-tipografiasLocal.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/css.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/resetearcss.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/stil.css" type="text/css"></link>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/home.css" type="text/css"></link>

<script src="<%=request.getContextPath()%>/assets/js/jquery-1.11.1.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/project_ajax.js"></script>

<script type="text/javascript">

	function validar(){
		
		if(document.frmLogin.codUsuario.value==''){
			alert('Ingrese la cuenta de usuario.');
			return false;
		}
		if(document.frmLogin.clave.value==''){
			alert('Ingrese la clave.');
			return false;
		}
		return true;
	}
	
	function iniciarSesion(){
		
		if(validar()){
			document.frmLogin.submit();
		}
	}

</script>

</head>
<body>

<form  id="frmLogin" name="frmLogin" method="post" action="login" runat="server" >
  	
	<input name="method" value="" type="hidden">
	<input type="hidden" name="id" >
	
    <div id="contenedor">
<!--         <div id="divprogress" class="PopupContainerProgressBienvenida"> -->
<!--             <div id="div2" class="PopupWindowProgressBienvenida"> -->
<!--                 <img id="imgloading" src="images/ajax-loader.gif" style="width:110px;height:110px;display:none;" -->
<!--                     title="Cargando" /> -->
<!--             </div> -->
<!--         </div> -->
        <div id="cabecera">
            <div id="logo-multired">
                <img src="<%=request.getContextPath()%>/assets/img/header/logo_REPR.jpg" alt="Logotipo MGEC" />
            </div>
            <div id="logo-bn">
                <img src="<%=request.getContextPath()%>/assets/img/header/logo-bn.jpg" alt="Logotipo del Banco de la Nación" />
            </div>
        </div>
        <div id="cuerpo">
            <h1 class="dax"></h1>
            <div id="login">
                <div id="border-superior">
                    <img src="<%=request.getContextPath()%>/assets/img/body/border-arriba.png" alt="Border Login Superior" />
                </div>
                <div id="login-contenido">
                    <div id="border-inferior">
                    </div>
                    
  						<h3>
                            <span id="lblTitulo" style="color:#A61219;">INGRESE USUARIO Y CONTRASEÑA</span>
                        </h3>
                        
                        <br />
                        
                        <div>
                        	<table style="margin-left: auto; margin-right: auto;">
								<tr><td>&nbsp;</td></tr>
								<tr>
									<td style="width: 150px; text-align: right;">
										<label for="num">Código de Usuario:</label>
									</td>
									<td>
										<input type="text" id="codUsuario" name="username" class="cas1 Requerido" data-msg="Usuario" style="width:150px; text-align: left;" onkeypress="return imposeMaxLength(event, this,6,2);" autocomplete="off" />
									</td>
								</tr>
								<tr>
									<td style="width: 150px; text-align: right;">
										<label for="num">Contraseña:</label>
									</td>
									<td>
										<input type="password" id="clave" name="password" class="cas2 Requerido" data-msg="Clave" style="width:150px; text-align: left;" onkeypress="return imposeMaxLength(event, this,15,3);" autocomplete="off" />
									</td>
								</tr>
								<tr>
									<td style="height: 30px">
									</td>
								</tr>	
								<tr>
									<td colspan="2">
									
									</td>
								</tr>
							</table>
							
                            <div style="padding: 0px 0px 10px 0px">
                            
                            	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
									<div id="error" class="ErrorMsg">	
										<p style="text-align: center">
											<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
										</p>
									</div>
								</c:if>
                            </div>
                            
                            <center>
                                <div class="btn1">
                                    <input type="button" id="btnIngresar" class="cerrar2" value="Ingresar" style="width:132px" tabindex="6" onclick="iniciarSesion();" />
                                </div>
                            </center>
                        </div>
                        <div style="padding: 0px 0px 10px 0px">
                            <span id="Span1"></span>
                        </div>
                        <div style="padding: 0px 0px 10px 0px">
                            <span id="spnVersion" class="ErrorMsg">Versión : 3.0 - Abril 2019 </span>
                        </div>
                    
                </div>
                <div id="Div1">
                    <img src="<%=request.getContextPath()%>/assets/img/body/border-abajo.png" alt="Border Login Inferior" />
                </div>
                <div style="padding: 0px 0px 10px 0px">
                    <img src="<%=request.getContextPath()%>/assets/img/body/get_info.png" style="width:19px;height:19px;" alt="Información" />
                    <span id="spnInforme" class="MsgNavegador">Para un uso óptimo del aplicativo, debe utilizar el
                        navegador Chrome </span>
                    <img src="<%=request.getContextPath()%>/assets/img/body/Google_Chrome.png" style="width:19px;height:19px;" alt="Navegador Chrome" />
                </div>
            </div>
        </div>
        
        <div id="pie-pagina">
            <div id="titulo-pie-pagina">
                | Banco de la Naci&oacute;n |
            </div>
            <div id="oficinas">
                <p>
                    Oficina Principal: Av. Javier Prado Este 2499. San Borja. Central Telefónica: 519-2000.
                </p>
                <p>
                    Atenci&oacute;n en Oficinas Administrativas: Lunes a Viernes de 8:30 a 17:30 horas.
                    Refrigerio de: 13:00 a 14:00 horas
                </p>
                <p>
                    Atenci&oacute;n en
                    Oficina de Tr&aacute;mite Documentario: Lunes a Viernes de 8:30
                    a 17:30 horas (Horario corrido).
                </p>
            </div>
        </div>
        
    </div>
</form>
</body>
</html>