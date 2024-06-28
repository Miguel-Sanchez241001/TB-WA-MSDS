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


input[type="text"],
input[type="email"],
textarea {
  width: 60%;
  padding: 5px;
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
  width: 100%; /* Ancho de la caja de texto */
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
<html>
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



<div class="container">
  <h2>REGISTRO DE APLICACIONES DEL BN</h2>
  
<form>
<div class="form-group">
<table  width="100%" class="form-group">
 <tr>
    <td width="35%" align="left" ><span class="titulo" style="font-weight: bold; text-decoration: underline" style="font-size: 16px">Software: </span>
	</td>

   <td width="55%" align="left">	</td>
 </tr>
 <tr>
   <td width="35%" align="left" > <label for="email" style="font-size: 14px">Cod. Sistema:</label>
   </td>
															
   <td width="55%" align="left">
	   <select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>MODC - PRESTAMO MULTIRED</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>MHUB</option>
	  </select>
  </td>
 </tr>
	
<tr>
  <td width="35%" align="left" > <label for="email" style="font-size: 14px">Nombre del Sistema:</label>
  </td>
															
  <td width="55%"  >
	<input type="text" id="mi-input" name="mi-input" class="mi-input">
  </td>
 </tr>
						<tr>
							<td></td>
							<td><input type="text" size="10"/></td>
						</tr>


 
 <tr>
   <td width="35%" align="left" >
	<label for="email" style="font-size: 14px">Nivel:</label>
   </td>
   <td width="55%" align="left" style="font-size: 14px">
	   <select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
			<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>Sistema</option>
			<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>Subsistema</option>
	   </select>
  </td>
 </tr>
	
 <tr>														
	<td width="35%" align="left">
		<label for="email" style="font-size: 14px">Area Usuaria:</label>
	</td>
    <td width="55%" align="left" style="font-size: 14px">
		<select name="cpConsulta" class="Editar Requerido"  style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>Banca Personal</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>Subsistema</option>
		</select>
	</td>
 </tr>	
 
 <tr>
    <td width="35%" align="left">
		 <label for="email" style="font-size: 14px">Area TI Responsable:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		<select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>Sistemas Bancarios</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>Subsistema</option>
		</select>
	</td>
 </tr>
															
 <tr>
   <td width="35%" align="left">
       <label for="email" style="font-size: 14px">Proceso de Negocio:</label>
   </td>
   <td width="55%" align="left" style="font-size:14px">
	   <select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>Colocaciones</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>Subsistema</option>
	   </select>
   </td>
 </tr>
															
 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Core:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>Negocio</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>Subsistema</option>
		</select>
	</td>
</tr>	
 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Tipo:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>Activo</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>Pasivo</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>Servicio</option>
		</select>
	</td>
</tr>



<tr>
	<td style="height: 10px"></td>
</tr>
														
 <tr>
 <td width="35%" align="left" ><span class="titulo" style="font-weight: bold; text-decoration: underline" style="font-size: 16px">Plataforma Base: </span>
 </td>
 <td width="55%" align="left">
 </td>
 </tr>
													
<tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Plataforma:</label>
	</td>
    <td width="20%" align="left">
		<select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>OPEN</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>HOST</option>
		</select>

	</td>
 </tr>
 
  <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">RTO:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>9 Horas</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>8 Horas</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>7 Horas</option>
		</select>
	</td>
</tr>

 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">MTDP:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>9 Horas</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>8 Horas</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>7 Horas</option>
		</select>
	</td>
</tr>

 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">RPO:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>9 Horas</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>8 Horas</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>7 Horas</option>
		</select>
	</td>
</tr>

 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">MTDL:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>9 Horas</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>8 Horas</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>7 Horas</option>
		</select>
	</td>
</tr>

 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">MBCO:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>9 Horas</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>8 Horas</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>7 Horas</option>
		</select>
	</td>
</tr>
 
 <tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Versión:</label>
	</td>
	<td width="55%"  >
		<select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>V1</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>V2</option>
		</select>
	</td>
</tr>

 <tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Versión desplegada Servidor Desarrollo:</label>
	</td>
	<td width="55%"  >
		<select name="cpConsulta" class="Editar Requerido" style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>V1</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>V2</option>
		</select>
	</td>
</tr>
															
<tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Url Desarrollo:</label>
	</td>
	<td width="55%"  >
		<input type="text" id="mi-input" name="mi-input" class="mi-input">
	</td>
</tr>	

<tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Url Certificación:</label>
	</td>
															
	<td width="55%"  >
		<input type="text" id="mi-input" name="mi-input" class="mi-input">
	</td>
</tr>
															
<tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Url Producción:</label>
	</td>
    <td width="55%"  >
		<input type="text" id="mi-input" name="mi-input" class="mi-input">
	</td>
</tr>
                                                            
 <tr>
	<td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Url Git:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		<input type="text" id="mi-input" name="mi-input" class="mi-input">
	</td>
 </tr>
 <tr>
	<td width="35%" align="left">
		<label for="email" style="font-size: 14px">Lenguaje:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		<select name="cpConsulta" class="Editar Requerido"  style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>Java</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>Cobol</option>
		</select>
	</td>
 </tr>
										
<tr>
	 <td width="35%" align="left">
		 <label for="email" style="font-size: 14px">Base de datos:</label>
     </td>
	 <td width="55%" align="left" style="font-size: 14px">
		<select name="cpConsulta" class="Editar Requerido"  style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>Oracle</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>SQL </option>
		</select>
	 </td>
</tr>
															
 <tr>
	 <td width="35%" align="left">
		 <label for="email" style="font-size: 14px">Persona Responsable BN:</label>
	 </td>
	 <td width="55%" align="left" style="font-size: 14px">
		 <select name="cpConsulta" class="Editar Requerido"  style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>062453 - PEDRO ENCALADA</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>SQL </option>
		 </select>
	 </td>
</tr>
 <tr>
	 <td width="35%" align="left">
		 <label for="email" style="font-size: 14px">Desarrollo realizado por:</label>
	 </td>
	 <td width="55%" align="left" style="font-size: 14px">
		 <select name="cpConsulta" class="Editar Requerido"  style="font-size: 14px">
				<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>INDRA</option>
				<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>OLSSA</option>
		 </select>
	 </td>
</tr>
															
 <tr>
	<td width="35%" align="left">
		<label for="email" style="font-size: 14px">Diagrama de Arquitectura:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		<form method="post" action="/send/" enctype="multipart/form-data">
              <input type="file" class="Editar Requerido" >
        </form>
	</td>
</tr>

 <tr>
	<td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Plataforma compatible:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		<input type="text" id="mi-input" name="mi-input" class="mi-input">
	</td>
 </tr>
 
  <tr>
	<td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Requisistos Hardware:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		<input type="text" id="mi-input" name="mi-input" class="mi-input">
	</td>
 </tr>
 
   <tr>
	<td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Fecha de última actualización:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		<input type="text" id="pin" name="pin" maxlength="4" size="4">
	</td>
 </tr>
 
 <tr>
   <td width="35%" align="left" rows="1">
	 <label for="email" style="font-size: 14px">Comentarios:</label>
  </td>
  <td width="55%" align="left" >
	<textarea class="comment-box" placeholder="Escribe tu comentario aquí"></textarea>
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