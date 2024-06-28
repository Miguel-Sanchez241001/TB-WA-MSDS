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

<title>CONSULTA GENERAR REPORTE</title>

<link href='<c:url value="/assets/css/datepicker.css"/>'
	rel="stylesheet" />

<script src='<c:url value="/assets/js/datepicker.js"/>'></script>
<script src='<c:url value="/assets/js/datepicker-es.js"/>'></script>

<script type="text/javascript">
	
	function entradaFecha(e) {
		
		var regex = new RegExp("^[0-9\/]+$");
		
	    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    
	    if (regex.test(str)) {
	        return true;
	    }
	    
	    e.preventDefault();
	    
	    return false;
	}

	function onlyNumberKey(evt) {
	    
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    
	    if (charCode > 31 && (charCode < 48 || charCode > 57)){
	        evt.preventDefault();
	        return false;
	    }
	    
    	return true;
	}
	
	function validarConsultaDescarga() {
		
		// LIMPIAR MENSAJE DE VALIDACION
		document.getElementById("spnMensaje").textContent = "";
				
		/** VALIDAR FECHA INICIO **/
		fechaInicio = document.getElementById("txtFechaInicio").value;
		
		if (fechaInicio.trim().length == 0) {
			document.getElementById("spnMensaje").textContent = "DEBE SELECCIONAR UNA FECHA INICIO DE CONSULTA.";
			document.getElementById("txtFechaInicio").focus();
			return false;	
		}
		/** FIN VALIDAR FECHA INICIO **/
		
		/** VALIDAR FECHA FINAL **/
		fechaFinal = document.getElementById("txtFechaFinal").value;
		
		if (fechaFinal.trim().length == 0) {
			document.getElementById("spnMensaje").textContent = "DEBE SELECCIONAR UNA FECHA FINAL DE CONSULTA.";
			document.getElementById("txtFechaFinal").focus();
			return false;	
		}
		/** FIN VALIDAR FECHA FINAL **/
		
		return true;
	}
	
	function descargarResultadosConsulta() {
	
		valido = validarConsultaDescarga();
		
		if (valido) {
   			document.descargaGenerarReporte.submit();		
		}
	}

	function validarFormatoFecha(strFecha) {
		
		var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
		
		if (	strFecha.match(RegExPattern)
			&& 	strFecha != '' ) {
			
			try {
				
				/** DD/MM/YYYY **/
				/** 0  1  2    **/
				var arrayFecha = strFecha.split("/");
				
				var day   = arrayFecha[0];
				var month = arrayFecha[1];
				var year  = arrayFecha[2];
				
				
				if ((day - 0) == 0) {
					return false;
				}
				
				if ((month - 0) == 0) {
					return false;
				}

				if ((year - 0) < 1971) {
					return false;
				}
				
				
				var date  = new Date(year, month, '0');
				
				if ((day - 0) > (date.getDate() - 0)) {
					return false;
				}
				
				return true;
				
			} catch (e) {
				console.log("try:", e.message());
				return false;
			}
			
		} else {
			return false;
		}
	}

</script>


<script type="text/javascript">
	window.addEventListener('load', function() {
		
		idExistenDatos = document.getElementById("idExistenDatos").value;
		
		if (idExistenDatos == "1") {
			document.getElementById("btnBuscar").scrollIntoView();
		}
		

		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
		var yyyy = today.getFullYear();

		today = dd + '/' + mm + '/' + yyyy;
		
		
		const fechaInicio = document.getElementById("txtFechaInicio");
		const dpInicio = new Datepicker(fechaInicio, {
			format: "dd/mm/yyyy",
			language: "es"
		});
		if (fechaInicio.value == "") {
			fechaInicio.value = today;
		}

		const fechaFinal = document.getElementById("txtFechaFinal");
		const dpFinal = new Datepicker(fechaFinal, {
			format: "dd/mm/yyyy",
			language: "es"
		});
		if (fechaFinal.value == "") {
			fechaFinal.value = today;
		}
	});
</script>


</head>
<body>


	<c:url var="url" value="/" />

	<div id="divPrincipal" style="display: inline">

		<input type="hidden" id="idExistenDatos" value="${existenDatos}" />

		<table style="width: 100%">
			<tbody>
				<tr>
					<td>
						<form id="descargaGenerarReporte" name="descargaGenerarReporte"
							method="post"
							action='<c:out value="${url}"/>resultadoGenerarReporte/'
							runat="server">

							<table style="width: 100%">
								<tbody>
									<tr>
										<td><span class="titulo">Consulta para Generar Reporte</span>&nbsp;</td>
									</tr>
									<tr>
										<td></td>
									</tr>
									<tr>
										<td>
											<table style="width: 100%">
												<tbody>
													<tr>
														<td><span class="Subtitulo"><strong>Criterios de B�squeda:</strong></span>&nbsp;</td>
													</tr>
													<tr>
														<td>&nbsp; &nbsp;</td>
													</tr>
													<tr>
														<td>&nbsp;
															<table cellpadding="0" cellspacing="0"
																style="z-index: 104; left: 16px; width: 712px; height: 168px">
																<tbody>
																	<tr>
																		<td
																			style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_sup_izq.gif); width: 8px; height: 30px"
																			class="NoImprimir"></td>
																		<td colspan="2"
																			style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_barra_sup.gif); width: 484px; height: 30px; text-align: left"
																			class="NoImprimir"><span id="spnTitulo"
																			style="width: 296px; background-color: transparent; color: #CC0000"
																			class="SubtituloPlantilla  NoImprimir">Ingresar los Datos de Busqueda</span></td>
																		<td
																			style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_sup_der.gif); width: 8px; height: 30px"
																			class="NoImprimir"></td>
																	</tr>
																	<tr>
																		<td
																			style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_izq.gif); width: 8px; height: 150px"
																			class="NoImprimir"></td>
																		<td colspan="2"
																			style="vertical-align: top; width: 700px; height: 150px; text-align: center;">

																			<table style="width: 100%">
																				<tbody>

																					<tr>
																						<td>&nbsp;</td>
																						<td style="text-align: right">&nbsp;</td>
																						<td style="text-align: center">&nbsp;</td>
																						<td colspan="12" style="text-align: left">
																							&nbsp;</td>
																						<td>&nbsp;</td>
																					</tr>

																					<tr>
																						<td>&nbsp;</td>
																						<td style="text-align: right">&nbsp;</td>
																						<td style="text-align: center">&nbsp;</td>
																						<td colspan="12" style="text-align: left">
																							&nbsp;</td>
																						<td>&nbsp;</td>
																					</tr>

																					<tr>
																						<td>&nbsp;</td>
																						<td style="text-align: right"><span
																							class="Subtitulo">Fecha Inicio</span></td>
																						<td style="text-align: center"><span
																							class="Subtitulo">:</span></td>
																						<td colspan="12" style="text-align: left"><input
																							name="fechaInicial" id="txtFechaInicio"
																							class="form-control text-center"
																							data-msg="Ingrese fecha de inicio"
																							onkeypress="return entradaFecha(event);"
																							value="${fechaInicial}" maxlength="10" />
																							
																						<td>&nbsp;</td>
																					</tr>

																					<tr>
																						<td>&nbsp;</td>
																						<td style="text-align: right">&nbsp;</td>
																						<td style="text-align: center">&nbsp;</td>
																						<td colspan="12" style="text-align: left">
																							&nbsp;</td>
																						<td>&nbsp;</td>
																					</tr>

																					<tr>
																						<td>&nbsp;</td>
																						<td style="text-align: right"><span
																							class="Subtitulo">Fecha Fin</span></td>
																						<td style="text-align: center"><span
																							class="Subtitulo">:</span></td>
																						<td colspan="12" style="text-align: left"><input
																							name="fechaFinal" id="txtFechaFinal"
																							class="form-control text-center entradaFecha"
																							data-msg="Ingrese fecha de final"
																							onkeypress="return entradaFecha(event);"
																							value="${fechaFinal}" maxlength="10" /> 
																						<td>&nbsp;</td>
																					</tr>

																					<tr>
																						<td>&nbsp;</td>
																						<td style="text-align: right">&nbsp;</td>
																						<td style="text-align: center">&nbsp;</td>
																						<td colspan="12" style="text-align: left">
																							&nbsp;</td>
																						<td>&nbsp;</td>
																					</tr>

<!-- 																					<tr> -->
<!-- 																						<td>&nbsp;</td> -->
<!-- 																						<td style="text-align: right"><span -->
<!-- 																							class="Subtitulo">Generar reporte</span></td> -->
<!-- 																						<td style="text-align: center"><span -->
<!-- 																							class="Subtitulo">:</span></td> -->
<!-- 																						<td colspan="12" style="text-align: left"><input -->
<!-- 																							id="rbOrden2" name="reporteDescargar" -->
<!-- 																							type="radio" -->
<%-- 																							${reporteDescargar=='02' ? 'checked="checked"' : ''} --%>
<!-- 																							value="02"> <span class="Subtitulo">Txt</span> -->
<!-- 																								<input id="rbOrden1" name="reporteDescargar" -->
<!-- 																								type="radio" -->
<%-- 																								${reporteDescargar=='01' ? 'checked="checked"' : ''} --%>
<!-- 																								value="01"> <span class="Subtitulo">Excel</span></td> -->
<!-- 																						<td>&nbsp;</td> -->
<!-- 																					</tr> -->

																					<tr>
																						<td>&nbsp;</td>
																						<td style="text-align: right">&nbsp;</td>
																						<td style="text-align: center">&nbsp;</td>
																						<td>&nbsp;</td>
																						<td>&nbsp;</td>
																						<td>&nbsp;</td>
																						<td>&nbsp;</td>
																						<td>&nbsp;</td>
																						<td>&nbsp;</td>
																						<td>&nbsp;</td>
																						<td>&nbsp;</td>
																						<td align="left">&nbsp;</td>
																						<td align="left">&nbsp;</td>
																						<td>&nbsp;</td>
																						<td align="left">&nbsp;</td>
																						<td align="left">&nbsp;</td>
																					</tr>

																				</tbody>
																			</table>


																			<hr class="Noimprimir">
																		</td>
																		<td
																			style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_der.gif); width: 10px; height: 150px"
																			class="NoImprimir"></td>
																	</tr>
																	<tr>
																		<td
																			style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_inf_izq.gif); width: 8px; height: 10px"
																			class="NoImprimir"></td>
																		<td colspan="2"
																			style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_barra_inf.gif); width: 484px; height: 10px"
																			class="NoImprimir"></td>
																		<td
																			style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_inf_der.gif); width: 8px; height: 10px"
																			class="NoImprimir"></td>
																	</tr>
																</tbody>
															</table>

														</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>

												</tbody>
											</table> <br>
										</td>
									</tr>
									<tr>
										<td><span id="spnMensaje" class="ErrorMsg"></span></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td style="text-align: center;"><input type="button"
											id="btnBuscar" value="Descargar" style="width: 175px"
											onclick="descargarResultadosConsulta()"
											class="cssButton-gris-larger-Retornar" tabindex="6">
												&nbsp; </td>
									</tr>
								</tbody>
							</table>

						</form>
					</td>
				</tr>

			</tbody>
		</table>

	</div>

</body>
</html>