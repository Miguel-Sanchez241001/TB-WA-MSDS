<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", " no-store ");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<head>

<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Cache-Control" content="max-age=0" />
<meta http-equiv="Expires" content="0" />

<title>CONSULTA ESTADO SIN VAUCHER</title>

<script type="text/javascript">
	
	function onlyNumberKey(evt) {
	    
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    
	    if (charCode > 31 && (charCode < 48 || charCode > 57)){
	        evt.preventDefault();
	        return false;
	    }
	    
    	return true;
	}
	
	function validarCamposConsulta() {
		
		// LIMPIAR MENSAJE DE VALIDACION
		document.getElementById("spnMensaje").textContent = "";
		
		
		/** VALIDAR CUENTA **/
		opcCuenta = document.getElementById("cboCuenta").value;
		
		if (opcCuenta == "") {
			document.getElementById("spnMensaje").textContent = "DEBE SELECCIONAR UN TIPO DE CUENTA.";
			return false;
		}
		
		if (opcCuenta == "02") {
			// DNI
			dni = document.getElementById("txtNroDni").value;
			
			if (dni.trim().length != 8) {
				document.getElementById("spnMensaje").textContent = "EL DNI DEBE TENER 8 DIGITOS.";
				document.getElementById("txtNroDni").focus();
				return false;	
			}
		}
		/** FIN VALIDAR CUENTA **/
		
		
		/** VALIDAR MES INICIAL **/
		opcMesIni = document.getElementById("cboMesesIni").value;
		
		if (opcMesIni == "") {
			document.getElementById("spnMensaje").textContent = "DEBE SELECCIONAR UN MES DE FECHA INICIO.";
			return false;
		}
		/** FIN VALIDAR MES INICIAL **/
		
		/** VALIDAR ANIO INICIAL **/
		opcAnioIni = document.getElementById("cboAnnosIni").value;
		
		if (opcAnioIni == "") {
			document.getElementById("spnMensaje").textContent = "DEBE SELECCIONAR UN AÑO DE FECHA INICIO.";
			return false;
		}
		/** FIN VALIDAR ANIO INICIAL **/
		
		/** VALIDAR MES FIN **/
		opcMesFin = document.getElementById("cboMesesFin").value;
		
		if (opcMesFin == "") {
			document.getElementById("spnMensaje").textContent = "DEBE SELECCIONAR UN MES DE FECHA FIN.";
			return false;
		}
		/** FIN VALIDAR MES FIN **/
		
		/** VALIDAR ANIO FIN **/
		opcAnioFin = document.getElementById("cboAnnosFin").value;
		
		if (opcAnioFin == "") {
			document.getElementById("spnMensaje").textContent = "DEBE SELECCIONAR UN AÑO DE FECHA FIN.";
			return false;
		}
		/** FIN VALIDAR ANIO FIN **/
		
		return true;
	}
	
	function mostrarResultadosConsulta() {
		
		valido = validarCamposConsulta();
		
		if (valido) {
   			document.consultaEstadoSinVaucher.submit();		
		}
	}
	
	function seleccionarCuenta(){
 		
		opc = document.getElementById("cboCuenta").value;
		
		if (opc == "01") {
			
			// Selecionado Nro Cuenta
			document.getElementById("bloqNroDni").style.display = "none";
			document.getElementById("bloqNroCuenta").style.display = "";
			document.getElementById("txtNroDni").value = "";
			document.getElementById("txtNroCuenta").value = "";
			
		} else if (opc == "02") {
			
			// Selecionado Nro DNI
			document.getElementById("bloqNroDni").style.display = "";
			document.getElementById("bloqNroCuenta").style.display = "none";
			document.getElementById("txtNroDni").value = "";
			document.getElementById("txtNroCuenta").value = "";
			
		} else {
			
			document.getElementById("bloqNroDni").style.display = "none";
			document.getElementById("bloqNroCuenta").style.display = "none";
			document.getElementById("txtNroDni").value = "";
			document.getElementById("txtNroCuenta").value = "";
		}
	}
	
	function descargarPdf() {
   		document.descargarPdfEstadoCuenta.submit();
	}
</script>


<script type="text/javascript">
	window.addEventListener('load', function() {
		
		idExistenDatos = document.getElementById("idExistenDatos").value;
		
		if (idExistenDatos == "1") {
			document.getElementById("btnBuscar").scrollIntoView();
		}
	});
</script>


</head>
<body>

<input type="hidden" id="idExistenDatos" value="${existenDatos}" />

<c:url var="url" value="/" />

<form id="descargarPdfEstadoCuenta" name="descargarPdfEstadoCuenta" method="post" action='<c:out value="${url}"/>descargarPdfEstadoCuenta/' runat="server" >
	<input	name="cboCuenta" value="${cboCuenta}" type="hidden" /> 
	<input	name="nroCuenta" value="${nroCuenta}" type="hidden" /> 
	<input	name="nroDni" value="${nroDni}" type="hidden" /> 
	<input	name="mesInicial" value="${mesInicial}" type="hidden" />
	<input	name="annoInicial" value="${annoInicial}" type="hidden" />
	<input	name="mesFinal" value="${mesFinal}" type="hidden" />
	<input	name="annoFinal" value="${annoFinal}" type="hidden" /> 
</form>

<div id="main-page-container" style="width: 855px">
	<table class="table-principal">
		<tbody>
			<tr>
				<td>
					<div id="divPrincipal" style="display: inline">

						<table style="width: 100%">
							<tbody>
								<tr>
									<td>
										<form id="consultaEstadoSinVaucher" name="consultaEstadoSinVaucher" method="post" action='<c:out value="${url}"/>resultadoConsultaEstadoSinVaucher/' runat="server" >
										
										<table style="width: 100%">
											<tbody>
												<tr>
													<td>
														<span class="titulo">Búsqueda Estado de Cuenta sin Voucher</span>
													</td>
												</tr>
												<tr>
													<td></td>
												</tr>
												<tr>
													<td>
														<table style="width: 100%">
															<tbody>
																<tr>
																	<td>
																		<span class="Subtitulo"><strong>Criterios de Búsqueda:</strong></span>&nbsp;
																	</td>
																</tr>
																<tr>
																	<td>&nbsp; &nbsp;</td>
																</tr>
																<tr>
																	<td>&nbsp;
																		<table cellpadding="0" cellspacing="0" style="z-index: 104; left: 16px; width: 712px; height: 168px">
																			<tbody>
																				<tr>
																					<td
																						style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_sup_izq.gif); width: 8px; height: 30px">
																					</td>
																					<td colspan="2"
																						style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_barra_sup.gif); width: 484px; height: 30px; text-align: left">
																						<span id="ContentPlaceHolder1_lblGesAct"
																							class="SubtituloPlantilla"
																							style="display: inline-block; color: #CC0000; background-color: Transparent; font-family: Verdana; font-weight: bold; width: 296px; position: static">
																							Seleccionar Nro de Cuenta y Periodo </span>
																					</td>
																					<td style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_sup_der.gif); width: 8px; height: 30px">
																					</td>
																				</tr>
																				<tr>
																					<td style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_izq.gif); width: 8px;height: 150px">
																					</td>
																					<td colspan="2"
																						style="vertical-align: top; width: 700px; height: 150px; text-align: center;">
																																											
																						<table>
																							<tbody>
																								<tr>
																									<td>&nbsp;</td>
																									<td>&nbsp;</td>
																									<td>&nbsp;&nbsp;&nbsp; &nbsp;</td>
																									<td colspan="11"><br></td>
																								</tr>
																								
																								<tr>
																									<td>&nbsp;</td>
																									<td style="text-align: right">
																										<span class="Subtitulo">Cuenta</span>&nbsp;
																									</td>
																									<td style="text-align: center">
																										<span class="Subtitulo">:</span>
																									</td>
																									<td colspan="11" style="text-align: left">
																										<select id="cboCuenta" 
																												name="cboCuenta" 
																												class="Editar Requerido" 
																												data-msg="Tipo Cuenta" 
																												onchange="seleccionarCuenta()" >
																											<option value="">Seleccione</option>
																											<option value="01" ${cboCuenta=='01' ? 'selected' : ''}>NRO CUENTA</option>
																											<option value="02" ${cboCuenta=='02' ? 'selected' : ''}>DNI</option>
																										</select>
																									</td>
																								</tr>

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
																									<td style="text-align: left">&nbsp;</td>
																									<td>&nbsp;</td>
																									<td style="text-align: left">&nbsp;</td>
																								</tr>
																								
																								<tr id="bloqNroCuenta" style="display: ${cboCuenta=='02' || cboCuenta=='' ? 'none' : ''}" >
																									<td>&nbsp;</td>
																									<td style="text-align: right">
																										<span class="Subtitulo">Nro Cuenta</span>&nbsp;
																									</td>
																									<td style="text-align: center">
																										<span class="Subtitulo">:</span>
																									</td>
																									<td colspan="11" style="text-align: left">
																										<input type="text" id="txtNroCuenta"
																												name="nroCuenta" class="cssTextBox"
																												style="width: 250px" tabindex="3"
																												maxlength="14" 
																												onkeypress="return onlyNumberKey(event)"  
																												value="${nroCuenta}" />
																									</td>
																								</tr>
																								
																								<tr id="bloqNroDni" style="display: ${cboCuenta=='01' || cboCuenta=='' ? 'none' : ''}" >
																									<td>&nbsp;</td>
																									<td style="text-align: right">
																										<span class="Subtitulo">Nro DNI</span>&nbsp;
																									</td>
																									<td style="text-align: center">
																										<span class="Subtitulo">:</span>
																									</td>
																									<td colspan="11" style="text-align: left">
																										<input type="text" id="txtNroDni"
																												name="nroDni" class="cssTextBox"
																												style="width: 250px" tabindex="3"
																												maxlength="08" 
																												onkeypress="return onlyNumberKey(event)" 
																												value="${nroDni}" />
																									</td>
																								</tr>
																								
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
																									<td style="text-align: left">&nbsp;</td>
																									<td>&nbsp;</td>
																									<td style="text-align: left">&nbsp;</td>
																								</tr>


																								<tr>
																									<td>&nbsp;</td>
																									<td style="text-align: right"><span
																										class="Subtitulo">Fecha Inicio</span></td>
																									<td style="text-align: center"><span
																										class="Subtitulo">:</span></td>
																									<td><span class="Subtitulo">MES</span></td>
																									<td><select id="cboMesesIni" 
																												name="mesInicial" 
																												class="Editar Requerido" data-msg="Meses">
																											<option value="">Seleccione</option>
																											<option value="01" ${mesInicial=='01' ? 'selected' : ''}>ENERO</option>
																											<option value="02" ${mesInicial=='02' ? 'selected' : ''}>FEBRERO</option>
																											<option value="03" ${mesInicial=='03' ? 'selected' : ''}>MARZO</option>
																											<option value="04" ${mesInicial=='04' ? 'selected' : ''}>ABRIL</option>
																											<option value="05" ${mesInicial=='05' ? 'selected' : ''}>MAYO</option>
																											<option value="06" ${mesInicial=='06' ? 'selected' : ''}>JUNIO</option>
																											<option value="07" ${mesInicial=='07' ? 'selected' : ''}>JULIO</option>
																											<option value="08" ${mesInicial=='08' ? 'selected' : ''}>AGOSTO</option>
																											<option value="09" ${mesInicial=='09' ? 'selected' : ''}>SETIEMBRE</option>
																											<option value="10" ${mesInicial=='10' ? 'selected' : ''}>OCTUBRE</option>
																											<option value="11" ${mesInicial=='11' ? 'selected' : ''}>NOVIEMBRE</option>
																											<option value="12" ${mesInicial=='12' ? 'selected' : ''}>DICIEMBRE</option>
																									</select></td>
																									<td class="auto-style2"><strong>/</strong>
																									</td>
																									<td><span class="Subtitulo">Año</span></td>
																									<td><select id="cboAnnosIni" 
																												name="annoInicial" 
																												class="Editar Requerido" data-msg="Annos">
																											<option value="">Seleccione</option>
																											<option value="2022" ${annoInicial=='2023' ? 'selected' : ''}>2023</option>
																											<option value="2022" ${annoInicial=='2022' ? 'selected' : ''}>2022</option>
																											<option value="2021" ${annoInicial=='2021' ? 'selected' : ''}>2021</option>
																											<option value="2020" ${annoInicial=='2020' ? 'selected' : ''}>2020</option>
																											<option value="2019" ${annoInicial=='2019' ? 'selected' : ''}>2019</option>
																											<option value="2018" ${annoInicial=='2018' ? 'selected' : ''}>2018</option>
																											<option value="2017" ${annoInicial=='2017' ? 'selected' : ''}>2017</option>
																											<option value="2016" ${annoInicial=='2016' ? 'selected' : ''}>2016</option>
																											<option value="2015" ${annoInicial=='2015' ? 'selected' : ''}>2015</option>
																											<option value="2014" ${annoInicial=='2014' ? 'selected' : ''}>2014</option>
																											<option value="2013" ${annoInicial=='2013' ? 'selected' : ''}>2013</option>
																											<option value="2012" ${annoInicial=='2012' ? 'selected' : ''}>2012</option>
																											<option value="2011" ${annoInicial=='2011' ? 'selected' : ''}>2011</option>
																									</select></td>
																									<td>&nbsp; &nbsp; &nbsp;&nbsp;</td>
																									<td style="text-align: left">&nbsp;&nbsp;
																									</td>
																									<td></td>
																									<td style="text-align: left"
																										class="auto-style1">&nbsp;&nbsp;</td>
																								</tr>


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
																									<td style="text-align: left">&nbsp;&nbsp;
																									</td>
																									<td>&nbsp;</td>
																									<td style="text-align: left"
																										class="auto-style1">&nbsp;&nbsp;</td>
																								</tr>


																								<tr>
																									<td>&nbsp;</td>
																									<td style="text-align: right"><span
																										class="Subtitulo">Fecha Fin</span></td>
																									<td style="text-align: center"><span
																										class="Subtitulo">:</span></td>
																									<td><span class="Subtitulo">MES</span></td>
																									<td>
																										<select id="cboMesesFin" 
																												name="mesFinal" 
																												class="Editar Requerido" data-msg="Meses">
																											<option value="">Seleccione</option>
																											<option value="01" ${mesFinal=='01' ? 'selected' : ''}>ENERO</option>
																											<option value="02" ${mesFinal=='02' ? 'selected' : ''}>FEBRERO</option>
																											<option value="03" ${mesFinal=='03' ? 'selected' : ''}>MARZO</option>
																											<option value="04" ${mesFinal=='04' ? 'selected' : ''}>ABRIL</option>
																											<option value="05" ${mesFinal=='05' ? 'selected' : ''}>MAYO</option>
																											<option value="06" ${mesFinal=='06' ? 'selected' : ''}>JUNIO</option>
																											<option value="07" ${mesFinal=='07' ? 'selected' : ''}>JULIO</option>
																											<option value="08" ${mesFinal=='08' ? 'selected' : ''}>AGOSTO</option>
																											<option value="09" ${mesFinal=='09' ? 'selected' : ''}>SETIEMBRE</option>
																											<option value="10" ${mesFinal=='10' ? 'selected' : ''}>OCTUBRE</option>
																											<option value="11" ${mesFinal=='11' ? 'selected' : ''}>NOVIEMBRE</option>
																											<option value="12" ${mesFinal=='12' ? 'selected' : ''}>DICIEMBRE</option>
																										</select>
																									</td>
																									<td class="auto-style2">/</td>
																									<td><span class="Subtitulo">Año</span></td>
																									<td>
																										<select id="cboAnnosFin" 
																												name="annoFinal" 
																												class="Editar Requerido" data-msg="Annos">
																											<option value="">Seleccione</option>
																											<option value="2022" ${annoFinal=='2023' ? 'selected' : ''}>2023</option>
																											<option value="2022" ${annoFinal=='2022' ? 'selected' : ''}>2022</option>
																											<option value="2021" ${annoFinal=='2021' ? 'selected' : ''}>2021</option>
																											<option value="2020" ${annoFinal=='2020' ? 'selected' : ''}>2020</option>
																											<option value="2019" ${annoFinal=='2019' ? 'selected' : ''}>2019</option>
																											<option value="2018" ${annoFinal=='2018' ? 'selected' : ''}>2018</option>
																											<option value="2017" ${annoFinal=='2017' ? 'selected' : ''}>2017</option>
																											<option value="2016" ${annoFinal=='2016' ? 'selected' : ''}>2016</option>
																											<option value="2015" ${annoFinal=='2015' ? 'selected' : ''}>2015</option>
																											<option value="2014" ${annoFinal=='2014' ? 'selected' : ''}>2014</option>
																											<option value="2013" ${annoFinal=='2013' ? 'selected' : ''}>2013</option>
																											<option value="2012" ${annoFinal=='2012' ? 'selected' : ''}>2012</option>
																											<option value="2011" ${annoFinal=='2011' ? 'selected' : ''}>2011</option>
																										</select>
																									</td>
																									<td>&nbsp;</td>
																									<td style="text-align: right">
																										&nbsp;&nbsp;</td>
																									<td>&nbsp;</td>
																									<td style="text-align: left"
																										class="auto-style1">&nbsp;&nbsp;</td>
																								</tr>
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
																									<td style="text-align: left">&nbsp;&nbsp;
																									</td>
																									<td>&nbsp;</td>
																									<td style="text-align: left"
																										class="auto-style1">&nbsp;&nbsp;</td>
																								</tr>

																							</tbody>
																						</table>


																						<hr>
																					</td>
																					<td
																						style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_der.gif); width: 10px;height: 150px">
																					</td>
																				</tr>
																				<tr>
																					<td
																						style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_inf_izq.gif); width: 8px; height: 10px">
																					</td>
																					<td colspan="2"
																						style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_barra_inf.gif); width: 484px;height: 10px">
																					</td>
																					<td
																						style="background-image: url(<%=request.getContextPath()%>/assets/img/body/c_inf_der.gif); width: 8px; height: 10px">
																					</td>
																				</tr>
																			</tbody>
																		</table>

																	</td>
																</tr>
															</tbody>
														</table> 
														<br>
													</td>
												</tr>
												<tr>
													<td>
														<span id="spnMensaje" class="ErrorMsg"></span>
													</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td style="text-align: center;">
														<input type="button"
																id="btnBuscar" value="Buscar" style="width: 173px" 
																onclick="mostrarResultadosConsulta()" 
																class="cssButton-gris-larger-Retornar" tabindex="6" />
															<span style="padding: 0 4px; display: inline-block"></span>
															&nbsp; 
													</td>
												</tr>
											</tbody>
										</table>
										
										</form>
										
									</td>
								</tr>
								<tr>
									<td>
										<div id="divDetalle" class="PopupContainerTablas">
											<div id="divPopup" class="PopupWindowTablas">
												<table style="width: 100%">
													<tbody>
														<tr class="BarraTitulo">
															<td style="width: 100%; text-align: center"><span
																id="spnTituloPopup" class="cssTituloBlanco"></span> <img
																id="imgCerrar" src="<%=request.getContextPath()%>/assets/img/shapes/closeVentana01.png"
																class="BotonClose"
																style="float: right; height: 20px; width: 20px"
																title="Cerrar Ventana">
															</td>
														</tr>
													</tbody>
												</table>
												<table>
													<tbody>
														<tr>
															<td><span id="spnMensajePopup"></span></td>
														</tr>
														<tr>
															<td>
																<div id="divData"></div>
															</td>
														</tr>
														<tr>
															<td>
																<div id="divPaginacion" style="display: none">
																	<nav class="centrado">
																	<ul class="paginacion">
																		<li id="liPagina"></li>
																	</ul>
																	</nav>
																</div>
															</td>
														</tr>
														<tr>
															<td style="text-align: center"><input id="btnCerrar"
																type="button" value="Cerrar" style="width: 86px;"
																class="cssButtonSmall">
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div id="divResultado" style="display: none">
											<table>
												<tbody>
													<tr>
														<td><span id="spnMensajeResultado"></span>&nbsp;&nbsp;&nbsp;<span
															id="spnTiempoBusqueda"></span></td>
													</tr>
													<tr>
														<td>
															<div id="divTablaFiltroResul"></div>
														</td>
													</tr>
													<tr>
														<td>
															<div id="divTablaResultado"></div>
														</td>
													</tr>
													<tr>
														<td style="text-align: center">
															<div id="divPaginacionResultado" style="display: none">
																<nav class="centrado">
																<ul class="paginacion">
																	<li id="liPaginaResul"></li>
																</ul>
																</nav>
															</div>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</td>
								</tr>
							</tbody>
						</table>


					</div>
				</td>
			</tr>
			<tr>
				<td>
					<hr>
				</td>
			</tr>
			<tr>
				<td>&nbsp; &nbsp;</td>
			</tr>
			<tr>
				<td>
					<div id="divprogress" class="PopupContainerProgress">
						<div id="div2" class="PopupWindowProgress">
							<img id="imgloading"
								src="<%=request.getContextPath()%>/assets/img/body/ajax-loader.gif"
								style="width: 110px; height: 110px" title="Cargando">
						</div>
					</div>
				</td>
			</tr>
			<tr style="display: ${existenDatos=='1' ? 'none' : ''}">
				<td>
					<div style="text-align: center">
						${mensajeResultado}
					</div>
				</td>
			</tr>
			<tr style="display: ${existenDatos=='0' ? 'none' : ''}">
				<td>
					<div>
						<table>
							<thead>
								<tr>
									<td colspan="2" style="text-align: left;">
										<input type="button"
												id="btnDownload" value="Descargar PDF" style="width: 173px" 
												onclick="descargarPdf()" 
												class="cssButton-gris-larger-Retornar" tabindex="6" />
											<span style="padding: 0 4px; display: inline-block"></span>
											&nbsp; 
									</td>
								</tr>
								<tr>
									<td style="text-align: left; width: 70%;">
										${ecCabecera.nombreCompleto}
									</td>
									<td style="text-align: left;">
										Estado de Cuenta de Ahorros MN. <br />
										Periodo: 01 ${ecCabecera.mesInicial} ${ecCabecera.annoInicial} a 01 ${ecCabecera.mesFinal} ${ecCabecera.annoFinal}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">
										CUENTA DE AHORROS MN NUEVOS SOLES NRO: ${ecCabecera.nroCuenta}
									</td>
									<td style="text-align: left;">
										CCI: ${ecCabecera.cci}
									</td>
								</tr>
							</thead>
						</table>
					</div>
				</td>
			</tr>
			<tr style="display: ${existenDatos=='0' ? 'none' : ''}">
				<td>
					<div>
						<table class="cssGridView">
							<thead>
								<tr>
									<th>Fecha Operación</th>
									<th>Oficina</th>
									<th>Referencia</th>
									<th>Concepto</th>
									<th>Cargo</th>
									<th>Abono</th>
									<th>Saldo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="ecd" items="${listECDetalle}">
									<tr>
										<td style="text-align: center;">
											<c:out value="${ecd.fechaOperacion}" />
										</td>
										<td style="text-align: center;">
											<c:out value="${ecd.oficina}" />
										</td>
										<td style="text-align: left;">	
											<c:out value="${ecd.referencia}" />
										</td>
										<td style="text-align: left;">
											<c:out value="${ecd.concepto}" />
										</td>
										<td style="text-align: right;">
											<c:out value="${ecd.cargo}" />
										</td>
										<td style="text-align: right;">
											<c:out value="${ecd.abono}" />
										</td>
										<td style="text-align: right;">
											<c:out value="${ecd.saldo}" />
										</td>
									</tr>
								</c:forEach>
									
							</tbody>
							<tbody>
								<tr>
									<td style="text-align: center" colspan="7">
										<div id="divPaginacionCertiHisto" style="display: none">
											<nav class="centrado">
											<ul class="paginacion">
												<li id="liPaginaCertiHisto"></li>
											</ul>
											</nav>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr style="display: ${existenDatos=='0' ? 'none' : ''}">
				<td>
					<table  class="cssGridView">
						<thead>
							<tr>
								<th>Total Transacciones</th>
								<th>Total Cargos</th>
								<th>Total Abonos</th>
								<th>Saldo Actual</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="text-align: center;">
									${ecCabecera.totalTransacciones}
								</td>
								<td style="text-align: center;">
									${ecCabecera.totalCargos}
								</td>
								<td style="text-align: center;">
									${ecCabecera.totalAbonos}
								</td>
								<td style="text-align: center;">
									${ecCabecera.saldoActual}
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	
</div>

</body>
