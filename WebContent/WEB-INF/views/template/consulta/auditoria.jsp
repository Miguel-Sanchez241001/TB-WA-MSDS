<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%-- <%@page import="pe.com.bn.repr.common.Util"%><title>Titulo</title> --%>
<%-- <%@page import="pe.com.bn.repr.common.DatosSesion"%> --%>
<%-- <%@page import="pe.com.bn.repr.common.Constant"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%> --%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", " no-store ");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>



<style>

body {
  font-family: Arial, sans-serif;
  background-color: #f2f2f2;
  margin: 0;
  padding: 0;
}

.container {
  max-width: 700px;
  margin: 0px auto;
  background-color: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
  text-align: center;
  margin-bottom: 10px;
   font-family: Arial, sans-serif;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  font-weight: bold;
}

input[type="text"],
input[type="email"],
textarea {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

textarea {
  height: 100px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

/* Estilos para la caja de texto */
.comment-box {
  width: 500px; /* Ancho de la caja de texto */
  height: 60px; /* Altura de la caja de texto */
  padding: 10px; /* Espacio interno */
  font-size: 12px; /* Tamaño de la fuente */
  border: 1px solid #ccc; /* Borde */
  border-radius: 5px; /* Esquinas redondeadas */
  resize: none; /* Evitar redimensionamiento */
  overflow-y: auto; /* Habilitar desplazamiento vertical */
}
</style>

<head>

<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Cache-Control" content="max-age=0" />
<meta http-equiv="Expires" content="0" />

<title>PRESTAMO MULTIRED</title>

<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/fontTableroPic1.css" type="text/css"></link> --%>
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/fontTableroPic2.css" type="text/css"></link> --%>
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/styles.css" type="text/css" /> --%>
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/displaytag.css" type="text/css"></link> --%>

<%-- <tag:scripts /> --%>

<script type="text/javascript">
	function MASK(form, n, mask, format) {
		if (format == "undefined")
			format = false;
		if (format || NUM(n)) {
			dec = 0, point = 0;
			x = mask.indexOf(".") + 1;
			if (x) {
				dec = mask.length - x;
			}

			if (dec) {
				n = NUM(n, dec) + "";
				x = n.indexOf(".") + 1;
				if (x) {
					point = n.length - x;
				} else {
					n += ".";
				}
			} else {
				n = NUM(n, 0) + "";
			}
			for (var x = point; x < dec; x++) {
				n += "0";
			}
			x = n.length, y = mask.length, XMASK = "";
			while (x || y) {
				if (x) {
					while (y && "#0.".indexOf(mask.charAt(y - 1)) == -1) {
						if (n.charAt(x - 1) != "-")
							XMASK = mask.charAt(y - 1) + XMASK;
						y--;
					}
					XMASK = n.charAt(x - 1) + XMASK, x--;
				} else if (y && "0".indexOf(mask.charAt(y - 1)) + 1) {
					XMASK = mask.charAt(y - 1) + XMASK;
				}
				if (y) {
					y--
				}
			}
		} else {
			XMASK = "";
		}
		if (form) {
			form.value = XMASK;
			if (NUM(n) < 0) {
				form.style.color = "#FF0000";
			} else {
				form.style.color = "#000000";
			}
		}
		return XMASK;
	}

	// Convierte una cadena alfanumérica a numérica (incluyendo formulas aritméticas)
	//
	// s   = cadena a ser convertida a numérica
	// dec = numero de decimales a redondear
	//
	// La función devuelve el numero redondeado

	function NUM(s, dec) {
		for (var s = s + "", num = "", x = 0; x < s.length; x++) {
			c = s.charAt(x);
			if (".-+/*".indexOf(c) + 1 || c != " " && !isNaN(c)) {
				num += c;
			}
		}
		if (isNaN(num)) {
			num = eval(num);
		}
		if (num == "") {
			num = 0;
		} else {
			num = parseFloat(num);
		}
		if (dec != undefined) {
			r = .5;
			if (num < 0)
				r = -r;
			e = Math.pow(10, (dec > 0) ? dec : 0);
			return parseInt(num * e + r) / e;
		} else {
			return num;
		}
	}

	function validar() {

		if (document.frmLogin.tipoDocConsulta.value == '') {

			alert('Seleccione el Tipo.');

			return false;
		}

		if (document.frmLogin.numeroDocConsulta.value == '') {

			alert('Ingrese su Documento.');

			return false;
		}

		if (document.frmLogin.numeroDocConsulta.value.length < 8) {
			alert("Documento m\u00EDnimo 8 digitos");
			return false;
		}

		if (document.frmLogin.plazoConsulta.value == '') {
			alert('Ingrese el Plazo.');

			return false;
		}

		if (document.frmLogin.cpConsulta.value == '') {
			alert('Ingrese CP.');

			return false;
		}

		return true;
	}

	function consultar() {

		if (validar()) {

			document.frmLogin.submit();
		}

	}

	function enviar() {

		if (validar1()) {
			document.frmLogin.submit();
		}
	}

	function validar1() {

		if (document.frmLogin.solicitado.value == '') {

			alert('Ingrese el monto solicitado.');

			return false;
		}

		return true;
	}

	function enviar2() {
		document.frmLogin.submit();
	}
</script>

</head>
<body>

	<c:url var="url" value="/" />

	<form id="consultaFactibilidad" name="frmLogin" method="post"
		action='<c:out value="${url}"/>resultadoConsultaFactibilidad/'
		runat="server">

		<input id="scp1" name="scp1"
			value="<c:out value="${factibilidad.scp}"   />" type="hidden" />
		<input id="tdocum1" name="tdocum1"
			value="<c:out value="${factibilidad.tdocum}"   />" type="hidden" />
		<input id="ndocum1" name="ndocum1"
			value="<c:out value="${factibilidad.ndocum}"   />" type="hidden" />
		<input id="pgracia" name="pgracia"
			value="<c:out value="${factibilidad.pgracia}"   />" type="hidden" />
		<input id="ncuotas" name="ncuotas"
			value="<c:out value="${factibilidad.ncuotas}"   />" type="hidden" />
		<input id="sgd" name="sgd"
			value="<c:out value="${factibilidad.sgd}"   />" type="hidden" />


		<input name="method" value="" type="hidden"> 
		<input type="hidden" name="id">

	

						
							
									
									<div class="container">
  <h2>REGISTRO DE AUDITORIA </h2>
  
  <form>
 <div class="form-group">
										<table  width="100%" class="form-group">
										
										
															<tr>
														

															<td width="20%" align="left" ><span class="titulo" style="font-weight: bold; text-decoration: underline" style="font-size: 16px">Software: </span>
															
															</td>
															
															<td width="70%" align="left">
																</td>
															
															</tr>
													
														<tr>
														

															<td width="30%" align="left" > <label for="email" style="font-size: 14px">Cod. Sistema:</label>
															
															</td>
															
															<td width="60%" align="left">
																<select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
																	<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>PRAH - PRESTAMO MULTIRED</option>
																	<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>MHUB</option>
																</select>
															</td>
															
															</tr>
															
			
                 
														<tr>
															<td style="height: 10px"></td>
														</tr>
														
															<tr>
														

															<td width="20%" align="left" ><span class="titulo" style="font-weight: bold; text-decoration: underline" style="font-size: 16px">Plataforma Base: </span>
															
															</td>
															
															<td width="70%" align="left">
																</td>
															
															</tr>
													
											
															
									
															

														<tr>
															<td colspan="2" align="left">
																
																	<button  onclick="consultar();">Registrar</button>
															</td>
														</tr>

										
												
										

										</table>
										</div>
										</form>
  
  
</div>
								
			
	</form>
</body>
</html>