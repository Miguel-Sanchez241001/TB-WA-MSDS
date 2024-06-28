<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@page import="java.util.List"%>
<%@page import="pe.com.bn.msds.model.Auditoria"%>
<%@ page import="org.apache.poi.xssf.usermodel.XSSFWorkbook"%>
<%@ page import="org.apache.poi.ss.usermodel.*"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
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

<title>Auditoria</title>

<link href='<c:url value="/assets/css/datepicker.css"/>'
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/displaytag.css"
	type="text/css"></link>
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
	width: 800px;
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

h3 {
	text-align: center;
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
.export-links a img {
    width: 24px;
    height: 24px;
    transition: transform 0.3s;
}

.export-links a img:hover {
    transform: scale(1.1);
}

textarea {
	height: 100px;
}

button,input[type="submit"] {
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
	font-size: 14px;
}

.parrafo {
	font-weight: bold;
	width: 110px;
	font-size: 14px;
}

.box-archivo {
	width: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 20px;
}

.box {
	width: 100%;
	max-width: 1000px;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.box-botom {
	width: 100%;
	max-width: 600px;
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: center;
	gap: 20px; /* Space between elements */
}

.message {
	display: block;
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

.box-table {
	width: 100%;
}

.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgb(0, 0, 0);
	background-color: rgba(0, 0, 0, 0.4);
	justify-content: center;
	align-items: center;
	display: flex;
}

.modal-content {
	background-color: white;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
	max-width: 500px;
	border-radius: 5px;
	display: flex;
	flex-direction: column;
}

.modal-content-top {
	display: flex;
	width: 100%;
	flex-direction: row;
	justify-content: space-between;
}

.modal-content-body {
	display: flex;
	width: 100%;
	gap: 15px;
	flex-direction: column;
	justify-content: space-between;
	flex-direction: column;
}

.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.box-form {
	display: flex;
	width: 100%;
	flex-direction: row;
	margin: 10px 0px;
}

.close:hover,.close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

label {
	font-size: 15px;
	font-weight: bold;
	width: 100px;
}

.form-item {
	display: flex;
	flex-direction: row;
	gap: 15px;
	margin: 5px 15px
}

.form-items {
	display: flex;
	flex-direction: column;
}
</style>

<script type="text/javascript">
	document.addEventListener('DOMContentLoaded', function() {

	});

	window.onclick = function(event) {

	}
</script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#box-btn').hide();
						$('#box-dias').hide();
						$('#box-fechas').hide();

						$('#opcionSelectTipoBusqueda').change(function() {
							var selectedValue = $(this).val();
							if (selectedValue === "0") {
								$('#box-btn').hide();
								$('#box-dias').hide();
								$('#box-fechas').hide();
							}
							if (selectedValue === "3") {
								$('#box-btn').show();
								$('#box-dias').hide();
								$('#box-fechas').hide();
							}
							if (selectedValue === "2") {
								$('#box-btn').show();
								$('#box-dias').show();
								$('#box-fechas').hide();
							}
							if (selectedValue === "1") {
								$('#box-btn').show();
								$('#box-dias').hide();
								$('#box-fechas').show();
							}

						});

						$("#formulario_reporte")
								.submit(
										function(event) {
											var fechaInicio = new Date($(
													"#forFechaFin").val());
											var fechaFin = new Date($(
													"#forFechaFin").val());
											var fechaDia = new Date($(
													"#forDiaDate").val());
											var fechaActual = new Date();
											var selectedV = $(
													"#opcionSelectTipoBusqueda")
													.val();

											if (selectedV === "1") {
												if (fechaInicio > fechaActual) {
													alert("La fecha de inicio no puede ser mayor que la fecha actual.");
													event.preventDefault(); // Evitar envío del formulario
												}
												if (fechaFin > fechaActual) {
													alert("La fecha de fin no puede ser mayor que la fecha actual.");
													event.preventDefault(); // Evitar envío del formulario
												}
												if (fechaFin < fechaInicio) {
													alert("La fecha de fin no puede ser anterior a la fecha de inicio.");
													event.preventDefault(); // Evitar envío del formulario
												}
											}

											if (selectedV === "2") {
												if (fechaDia > fechaActual) {
													alert("La fecha día no puede ser mayor que la fecha actual.");
													event.preventDefault(); // Evitar envío del formulario
												}
											}

										});

					});
</script>
</head>
<body>

	<c:url var="url" value="/" />

	<div class="container-box">
		<h2>Reporte de Sistemas y Aplicativos</h2>
		<div class="box-archivo  ">

			<form class="box" action="<%=request.getContextPath()%>/resulreport"
				method="post" id="formulario_reporte" >
				<div class="form-item">
					<span class="titulo">Seleccionar : </span> <select
						id="opcionSelectTipoBusqueda" name="opcion"
						style="font-size: 12px; width: 100px">
						<option value="0">Elija</option>
						<option value="1">Entre fechas</option>
						<option value="2">Por dia</option>
						<option value="3">Ver todo</option>
					</select>
				</div>


				<div id="box-fechas" class="form-items">
					<div class="form-item">
						<span class="parrafo">Fecha Inicio: </span> <input
							name="forFechaInicio" id="forFechaInicio" type="date" />
					</div>
					<div class="form-item">
						<span class="parrafo">Fecha fin: </span> <input name="forFechaFin"
							id="forFechaFin" type="date" />
					</div>
				</div>
				<div id="box-dias" class="form-item">

					<span class="parrafo">Seleccione dia: </span> <input
						name="forDiaDate" id="forDiaDate" type="date" />
				</div>
				<div id="box-btn" class="form-item">
					<button type="submit" class="btn-red" >Generar Reporte</button>
				</div>
			</form>

		 

		</div>


	</div>

</body>
</html>