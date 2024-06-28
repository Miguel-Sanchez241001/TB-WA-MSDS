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

<title>CONSULTA ESTADO CON VAUCHER</title>

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
		

		/** VALIDAR NRO. PEDIDO **/
		opcNroPedido = document.getElementById("rdNroPedido").checked;
		
		if (opcNroPedido) {
			
			document.getElementById("txtNroCuenta").value = "";
			
			// Nro Pedido
			nroPedido = document.getElementById("txtNroPedido").value;
			
			if (nroPedido.trim().length == 0) {
				document.getElementById("txtNroPedido").value = "000000000";
				document.getElementById("spnMensaje").textContent = "EL NRO. PEDIDO DEBE TENER 9 DIGITOS.";
				document.getElementById("txtNroPedido").focus();
				return false;
			}
			
			if (nroPedido.trim().length != 9) {
				document.getElementById("spnMensaje").textContent = "EL NRO. PEDIDO DEBE TENER 9 DIGITOS.";
				document.getElementById("txtNroPedido").focus();
				return false;	
			}
		}
		/** FIN VALIDAR NRO. PEDIDO **/
		
		/** VALIDAR NRO. CUENTA **/
		opcNroCuenta = document.getElementById("rdNroCuenta").checked;
		
		if (opcNroCuenta) {
			
			document.getElementById("txtNroPedido").value = "";
			
			// Nro Cuenta
			nroCuenta = document.getElementById("txtNroCuenta").value;
			
			if (nroCuenta.trim().length == 0) {
				document.getElementById("txtNroCuenta").value = "00000000000";
				document.getElementById("spnMensaje").textContent = "EL NRO. PEDIDO DEBE TENER 11 DIGITOS.";
				document.getElementById("txtNroCuenta").focus();
				return false;
			}
			
			if (nroCuenta.trim().length != 11) {
				document.getElementById("spnMensaje").textContent = "EL NRO. CUENTA DEBE TENER 11 DIGITOS.";
				document.getElementById("txtNroCuenta").focus();
				return false;	
			}
		}
		/** FIN VALIDAR NRO. CUENTA **/
		
		
		if (!opcNroPedido && !opcNroCuenta) {
			document.getElementById("spnMensaje").textContent = "DEBE SELECCIONAR UN OPCION DE BUSQUEDA.";
			return false;
		}
		
		return true;
	}
	
	function cambiarLimpiarValores() {
		
		opcNroPedido = document.getElementById("rdNroPedido").checked;
		
		if (opcNroPedido) {
			document.getElementById("txtNroCuenta").value = "";
		}
		
		opcNroCuenta = document.getElementById("rdNroCuenta").checked;
		
		if (opcNroCuenta) {
			document.getElementById("txtNroPedido").value = "";
		}	
	}
	
	function mostrarResultadosConsulta() {
		
		valido = validarCamposConsulta();
		
		if (valido) {
   			document.consultaEstadoConVaucher.submit();		
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
										<form id="consultaEstadoConVaucher" name="consultaEstadoConVaucher" method="post" action='<c:out value="${url}"/>resultadoConsultaEstadoConVaucher/' runat="server" >
										
										<table style="width: 100%">
											<tbody>
												<tr>
													<td>
														<span class="titulo">Búsqueda Estado de Cuenta con Voucher</span>
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
																							Seleccionar Nro Pedido / Cuenta</span>
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
																										<input type="radio" id="rdNroPedido" name="rdVoucher" value="01" ${rdVoucher=='01' ? 'checked' : ''} onchange="cambiarLimpiarValores();" />
																										<span class="Subtitulo">Nro. Pedido</span>&nbsp;
																									</td>
																									<td colspan="11" style="text-align: left">
																										<input type="text" id="txtNroPedido"
																												name="nroPedido" class="cssTextBox"
																												style="width: 250px" tabindex="3"
																												maxlength="9" 
																												onkeypress="return onlyNumberKey(event)"  
																												value="${nroPedido}" />
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
																									<td style="text-align: right">
																										<input type="radio" id="rdNroCuenta" value="02" name="rdVoucher" value="02" ${rdVoucher=='02' ? 'checked' : ''} onchange="cambiarLimpiarValores();" />
																										<span class="Subtitulo">Nro. Cuenta</span>&nbsp;
																									</td>
																									<td colspan="11" style="text-align: left">
																										<input type="text" id="txtNroCuenta"
																												name="nroCuenta" class="cssTextBox"
																												style="width: 250px" tabindex="3"
																												maxlength="11" 
																												onkeypress="return onlyNumberKey(event)"  
																												value="${nroCuenta}" />
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
						<table style="padding-left: 7%;width: 100%;">
							<thead>
								<tr>
									<td colspan="6" style="text-align: left;">
										PEDIDO NRO. ${dataVoucher.nroPedido}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">
										Nro. de Cuenta
									</td>
									<td style="text-align: left;">
										:
									</td>
									<td colspan="4" style="text-align: left;">
										${dataVoucher.nroCuenta}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">
										Fecha de Solicitud
									</td>
									<td style="text-align: left;">
										:
									</td>
									<td style="text-align: left;">
										${dataVoucher.fechaSolicitud}
									</td>
									<td style="text-align: left;">
										Hora de Solicitud
									</td>
									<td style="text-align: left;">
										:
									</td>
									<td style="text-align: left;">
										${dataVoucher.horaSolicitud}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">
										Periodo Inicial
									</td>
									<td style="text-align: left;">
										:
									</td>
									<td style="text-align: left;">
										${dataVoucher.periodoInicial}
									</td>
									<td style="text-align: left;">
										Periodo Final
									</td>
									<td style="text-align: left;">
										:
									</td>
									<td style="text-align: left;">
										${dataVoucher.periodoFinal}
									</td>
								</tr>
								<tr>
									<td colspan="2" style="text-align: right;">
										<input type="button"
												id="btnDownload" value="Ver PDF" style="width: 173px" 
												onclick="visualizarPdf()" 
												class="cssButton-gris-larger-Retornar" tabindex="6" />
											<span style="padding: 0 4px; display: inline-block"></span>
											&nbsp; 
									</td>
								</tr>
							</thead>
						</table>
					</div>
				</td>
			</tr>
			
		</tbody>
	</table>
	
</div>

</body>
