<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<title>CARGA ARCHIVOS</title>

<link href='<c:url value="/assets/css/datepicker.css"/>'
	rel="stylesheet" />
<script src='<c:url value="/assets/js/datepicker.js"/>'></script>
<script src='<c:url value="/assets/js/datepicker-es.js"/>'></script>


<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
}

.container-box {
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

input[type="text"],input[type="file"],input[type="email"],textarea {
	width: 40%;
	padding: 5px;
	font-size: 13px;
	border-radius: 5px;
	border: 1px solid #ccc;
}

textarea {
	height: 100px;
}

button {
	padding: 10px;
	background-color: #C51416;
	color: #fff;
	border: none;
	border-radius: 5px;
	font-size: 14px;
	cursor: pointer;
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

.titulo {
	font-weight: bold;
	text-decoration: underline;
	font-size: 16px;
}

.box-archivo {
	display: flex;
	flex-direction: column;
	align-items: center;
	padding: 20px;
	gap: 20px;
}

.box {
	width: 100%;
	max-width: 600px;
	display: flex;
	flex-direction: row;
	align-items: center;
	gap: 10px; /* Space between elements */
}

.message {
	display: inline-block;
	padding: 10px 20px;
	border-radius: 5px;
	font-weight: bold;
	margin: 10px 0;
	font-size: 16px;
}

.message-success {
	background-color: #d4edda;
	color: #155724;
	border: 1px solid #c3e6cb;
}

.message-error {
	background-color: #f8d7da;
	color: #721c24;
	border: 1px solid #f5c6cb;
}
</style>


<script>
	document.addEventListener('DOMContentLoaded', function() {
		var boxFileDiv = document.getElementById("box-file");
		boxFileDiv.style.display = "none";

	});
	function mostrarDiv() {
		var selectValor = document.getElementById("selectType").value;
		var boxFileDiv = document.getElementById("box-file");

		if (selectValor !== "opcion1") {
			boxFileDiv.style.display = "block";
		} else {
			boxFileDiv.style.display = "none";
		}
		var boxMsjDiv = document.getElementById("caja-msj");
		boxMsjDiv.style.display = "none";
	 
	}
</script>


</head>
<body>

	<c:url var="url" value="/" />

	<div class="container-box ">

		<h2>CARGAR ARCHIVO EXCEL</h2>

		 
			<form class="box-archivo  "
				action="<%=request.getContextPath()%>/cargaxlspost" method="post"
				enctype="multipart/form-data">
				<div class="box">
					<span class="titulo">Seleccionar Parametro: </span> <select
						name="opcion" id="selectType" style="font-size: 14px"
						onchange="mostrarDiv()">
						<option value="0">Elige</option>
						<option value="1">Sistema</option>
						<option value="2">Area Usuaria</option>
						<option value="3">Area Responsable</option>
						<option value="4">Proceso Negocio</option>
						<option value="5">Tipo Aplicacion</option>
						<option value="6">Tiempo Atencion</option>
						<option value="7">Version</option>
						<option value="8">Lenguaje</option>
						<option value="9">Base de datos</option>
						<option value="10">Responsable Desa</option>
						<option value="11">Fabrica</option>
					</select>
				</div>

				<div id="box-file" class="box">

					<input type="file" id="archivo" name="archivo" accept=".xlsx, .xls" />
					<button type="submit" class="btn-red">Cargar archivo</button>

				</div>
			
			<div id="caja-msj" class="box-botom">
				<c:if test="${not empty mensaje}">
					<span class="message message-success"> ${mensaje}</span>
					<span class="message message-success">    Registros cargados: ${RC} </span>
				</c:if>
				<c:if test="${not empty error}">
					<span class="message message-error"> ${error}</span>
					
				</c:if>
			</div>
</form>
		 

	</div>

</body>
</html>