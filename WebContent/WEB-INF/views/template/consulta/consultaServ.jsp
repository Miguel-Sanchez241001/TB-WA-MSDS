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
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/displaytag.css" type="text/css"></link>

<%-- <tag:scripts /> --%>

<script type="text/javascript">


function validar(){
	 var select = document.getElementById("myselect");

	
	if(select.options[select.selectedIndex].value == "1"){
	return true;
	}else{
	
	if(document.frmLogin.Numero.value <13){
		alert(' m\u00EDnimo 13 digitos');
		
		return false;
	}

   if(document.frmLogin.Numero.value==''){
		alert('Ingrese el .');
		
		return false;
	} 
	}
	
	return true;
}



	 function mostrar_control(){
        var select = document.getElementById("myselect");
        var inputText = document.getElementById("Numero");
        if(select.options[select.selectedIndex].value == "Nuevo"){
            inputText.style.visibility = "visible";
        }else{
            inputText.style.visibility = "hidden";
        }
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
  <h2>CONSULTA DE SERVICIOS </h2>
  
  <form>
 <div class="form-group">
										<table  width="100%" class="form-group">
										
										
														
													
														<tr>
														

															<td width="30%" align="left" > <label for="email" style="font-size: 14px">Buscar:</label>
															</td>
															
															<td width="70%" align="left">
															
																<select id="myselect" onchange="mostrar_control()" style="font-size: 14px"> 
													  <option value="0"></option>
                                                      <option value="1">Todo</option>
                                                      <option value="Nuevo">Microservicios</option>  
                                                          
                                                     
                                                      </select>
													
											         <select id="Numero" name="Numero" class="Editar Requerido" style="font-size: 14px;visibility: hidden; width: 250px; " onKeyPress="return checkIt(event)"  maxlength="15">
																	<option value="N" ${cpConsulta=='N' ? 'selected' : ''}>MSSU-Microservicio de NPD</option>
																	<option value="S" ${cpConsulta=='S' ? 'selected' : ''}>MHUB</option>
																</select>
		
							
																</td>
															
															
																				
															
															</tr>
															
	
										
                 
														<tr>
															<td style="height: 10px"></td>
														</tr>
														
							
													
											
															
									
															

														<tr>
															<td colspan="2" align="left">
																
																	<button onclick="mostrarFormulario()">Consultar</button>
															</td>
														</tr>

										
												
										

										</table>
										</div>
										</form>
  
  
</div>
		
		 <table width="85%" border="0" >
		
				<tr>
					<td>
		
			
  		<display:table     class="headerDisplay"  name="result"    id="result" cellpadding="0" cellspacing="0" requestURI="" pagesize="2"  >
		  
			<display:column title="N&deg;" >sss</display:column>
			<display:column title="DESEMBOLSO" >sss</display:column>
			<display:column title="PRODUCTO" >sss</display:column>
			<display:column title="USUARIO" >sss</display:column>
			<display:column title="FECHA Y HORA DE EMISION" >ss</display:column>
			<display:column title="DOCUMENTO" >		
			<input type="button" class="buttonCls" submit="true" style="width: 140px" value="Ver" name=""  id="" onclick="verTodo('${result.desembolso}')"  />
				    
            </display:column>
			
			
		</display:table>                                                                                                                                                                           
		
		
	    </td>
					
				</tr>
	
	   
		</table>
		
								
			
	</form>
</body>
</html>