<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", " no-store ");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<style>
.message-success {
	background-color: #d4edda;
	color: #155724;
	border: 1px solid #c3e6cb;
}

.mensaje {
    background-color: #d4edda; /* Color de fondo */
    border: 1px solid #c3e6cb; /* Borde */
    color: #155724; /* Color del texto */
    padding: 10px 15px; /* Espaciado interno */
    margin-bottom: 20px; /* Margen inferior */
    border-radius: 5px; /* Borde redondeado */
}

/* Estilo para el texto del mensaje */
.mensaje-texto {
    margin: 0; /* Elimina el margen del texto */
    font-weight: bold; /* Texto en negrita */
    font-size: 18px;
}

 .uppercase {
            text-transform: uppercase;
        }

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

<title>SISTEMA DE REGISTRO DE APLICACIONES</title>


<script type="text/javascript">

function registrar(){
	document.frmRegistro.submit();

}
function registrar2(){


	document.frmRegistro2.submit();

}

function validar(){
	

	

   if(document.frmRegistro.codSistema.value==''){
		alert('Ingrese Código');
		
		return false;
	}else {
	if(document.frmRegistro.codSistema.value.length < 2){
		alert('Código de 4 letras');
		
		return false;
	}
	
	} 
	
	var texto = document.getElementById("codSistema").value;
	var textoEnMayusculas = texto.toUpperCase();
	document.getElementById("codSistema").value = textoEnMayusculas;
	
	return true;
}

</script>

</head>
<html>
<body>

	<c:url var="url" value="/" />
	

	

<form id="frmRegistro" name="frmRegistro" method="post" action='<c:out value="${url}"/>aplicacion2/' runat="server" >

<div class="container">

  <h2 style="font-weight: bold">REGISTRO DE APLICACIONES DEL BN</h2>
  

<div class="form-group">


<table  width="100%" class="form-group">


 <tr>
   <td width="35%" align="left" > <label for="email" style="font-size: 14px">Cod. Sistema:</label>
   </td>
															
 
  <td width="55%" align="left" style="font-size: 14px">
		 <select id="aplicacion" name="aplicacion">
            <c:forEach var="item" items="${aplicacionList}">
                <option value="${item}">${item}</option>
            </c:forEach>
        </select>
	</td>
 
 </tr>
 
<tr>
    <td colspan="2" align="left">
		
			<input type="button" class="cssButton-gris-larger-Retornar" submit="true" style="width: 140px" value="APLICACION" onclick="registrar();" />
	</td>
</tr>



</form>





<c:if test="${not empty mensaje}">


<form id="frmRegistro2" name="frmRegistro2" method="post" action='<c:out value="${url}"/>aplicacionreg/' runat="server"  enctype="multipart/form-data">


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
  <input type="text" id="codSistema" name="codSistema" size="4" style="width: 50px; font-size: 11px"  maxlength="4"  value="${item.codigo}"/>
  </td>
 </tr>
 
 
  <tr>
  <td width="35%" align="left" > <label for="email" style="font-size: 14px; ">Nombre del Sistema:</label>
  </td>
															
  <td width="55%"  >
	  <input type="text" id="nomSistema" name="nomSistema" size="4" style="width: 500px; font-size: 11px"  maxlength="100"  value="${item.nombre}" />
  </td>
 </tr>
 
  <tr>
  <td width="35%" align="left" > <label for="email" style="font-size: 14px; ">Descripción del Sistema:</label>
  </td>
															
  <td width="55%"  >                                                                                                              
	  <textarea id="desSistema" name="desSistema" style="height: 50px; width: 500px; font-size: 11px">${item.descripcion}</textarea>
	  </td>
 </tr>
	
	 <tr>
   <td width="35%" align="left" >
	<label for="email" style="font-size: 14px">Nivel:</label>
   </td>
   <td width="55%" align="left" style="font-size: 14px">
	   <select name="nivel" class="Editar Requerido" style="font-size: 14px">
			<option value="Sistema" ${cpConsulta=='N' ? 'selected' : ''}>Sistema</option>
			<option value="Subsistema" ${cpConsulta=='S' ? 'selected' : ''}>Subsistema</option>
	   </select>
  </td>
 </tr>


 <tr>														
	<td width="35%" align="left">
		<label for="email" style="font-size: 14px">Area Usuaria:</label>
	</td>
    <td width="55%" align="left" class="Editar Requerido" >
	 <select id="areaUsuaria" name="areaUsuaria" style="font-size: 13px">
            <c:forEach var="item" items="${dataList}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	</td>
 </tr>		
 
 <tr>														
	<td width="35%" align="left">
		<label for="email" style="font-size: 14px">Area TI Responsable:</label>
	</td>
    <td width="55%" align="left" class="Editar Requerido" >
	 <select id="areaResponsable" name="areaResponsable" style="font-size: 13px">
            <c:forEach var="item" items="${dataListArea}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	</td>
 </tr>	
 
	
 <tr>														
	<td width="35%" align="left">
		<label for="email" style="font-size: 14px">Proceso de Negocio:</label>
	</td>
    <td width="55%" align="left" class="Editar Requerido" >
	 <select id="areaProceso" name="areaProceso" style="font-size: 13px">
            <c:forEach var="item" items="${dataLisProceso}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	</td>
 </tr>	
 
  <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Tipo:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select id="areaTipo" name="areaTipo" style="font-size: 13px">
            <c:forEach var="item" items="${dataLisTipo}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
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
		<select name="plataforma" class="Editar Requerido" style="font-size: 14px">
				<option value="OPEN" ${cpConsulta=='OPEN' ? 'selected' : ''}>OPEN</option>
				<option value="HOST" ${cpConsulta=='HOST' ? 'selected' : ''}>HOST</option>
		</select>

	</td>
 </tr>
 
  <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">RTO:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
			<select id="rto" name="rto" style="font-size: 13px">
            <c:forEach var="item" items="${dataLisTiempo}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	</td>
</tr>


 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">MTDP:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select id="mtdp" name="mtdp" style="font-size: 13px">
            <c:forEach var="item" items="${dataLisTiempo}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	</td>
</tr>

 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">RPO:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select id="rpo" name="rpo" style="font-size: 13px">
            <c:forEach var="item" items="${dataLisTiempo}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	</td>
</tr>

 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">MTDL:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select id="mtdl" name="mtdl" style="font-size: 13px">
            <c:forEach var="item" items="${dataLisTiempo}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	</td>
</tr>

 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">MBCO:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select id="mbco" name="mbco" style="font-size: 13px">
            <c:forEach var="item" items="${dataLisTiempo}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	</td>
</tr>

<tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Versión:</label>
	</td>
	<td width="55%"  >
			<select id="version" name="version" style="font-size: 13px">
            <c:forEach var="item" items="${dataLisVersion}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	</td>
</tr>

 <tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Versión desplegada Servidor Desarrollo:</label>
	</td>
	<td width="55%"  >
			<select id="versionServ" name="versionServ" style="font-size: 13px">
            <c:forEach var="item" items="${dataLisVersion}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	</td>
</tr>

<tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Url Desarrollo:</label>
	</td>
	<td width="55%"  >
		 <input type="text" id="urlDesarrollo" name="urlDesarrollo" size="4" style="width: 500px; font-size: 13px "  />
	</td>
</tr>	

<tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Url Certificación:</label>
	</td>
															
	<td width="55%"  >
		 <input type="text" id="urlCertificacion" name="urlCertificacion" size="4" style="width: 500px; font-size: 13px"  />
	</td>
</tr>
															
<tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Url Producción:</label>
	</td>
    <td width="55%"  >
		 <input type="text" id="urlProduccion" name="urlProduccion" size="4" style="width: 500px; font-size: 13px"  />
	</td>
</tr>

 <tr>
	<td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Url Git:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		 <input type="text" id="urlGit" name="urlGit" size="4" style="width: 500px; font-size: 13px"  />
	</td>
 </tr>

<tr>
	<td width="35%" align="left">
		<label for="email" style="font-size: 14px">Lenguaje:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
			<select id="lenguaje" name="lenguaje" style="font-size: 13px">
            <c:forEach var="item" items="${dataLisLenguaje}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	</td>
 </tr>
										
<tr>
	 <td width="35%" align="left">
		 <label for="email" style="font-size: 14px">Base de datos:</label>
     </td>
	 <td width="55%" align="left" style="font-size: 14px">
			<select id="baseDatos" name="baseDatos" style="font-size: 13px">
            <c:forEach var="item" items="${dataLisBaseDatos}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	 </td>
</tr>
															
 <tr>
	 <td width="35%" align="left">
		 <label for="email" style="font-size: 14px">Persona Responsable BN:</label>
	 </td>
	 <td width="55%" align="left" style="font-size: 14px">
		 	<select id="personaResp" name="personaResp" style="font-size: 13px">
            <c:forEach var="item" items="${dataLisResponsableDesa}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	 </td>
</tr>

<tr>
	 <td width="35%" align="left">
		 <label for="email" style="font-size: 14px">Desarrollo realizado por:</label>
	 </td>
	 <td width="55%" align="left" style="font-size: 14px">
		 <select id="desaRealizado" name="desaRealizado" style="font-size: 13px">
            <c:forEach var="item" items="${dataLisFabrica}">
                <option value="${item.valor}">${item.valor}</option>
            </c:forEach>
        </select>
	 </td>
</tr>                                                         

 <tr>
	<td width="35%" align="left">
		<label for="email" style="font-size: 14px">Diagrama de Arquitectura:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		  <input type="file" class="Editar Requerido" id="arquitectura" name="arquitectura" >
	</td>
</tr>

 <tr>
	<td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Plataforma compatible:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		 <input type="text" id="plataformaCom" name="plataformaCom" size="4" style="width: 500px; font-size: 11px" class="uppercase" />
	</td>
 </tr>
 
  <tr>
	<td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Requisistos Hardware:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		 <input type="text" id="requisitos" name="requisitos" size="4" style="width: 500px; font-size: 11px" class="uppercase" />
	</td>
 </tr>
 
   <tr>
	<td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Fecha de última actualización:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		<input type="date" id="fecha" name="fecha">
	</td>
 </tr>
 
 <tr>
   <td width="35%" align="left" rows="1">
	 <label for="email" style="font-size: 14px">Comentarios:</label>
  </td>
  <td width="55%" align="left" >
		 <textarea id="comentarios" name="comentarios" style="height: 50px; width: 500px; font-size: 11px"  maxlength="300" class="uppercase"></textarea>

  </td>
 </tr>

<tr>
    <td colspan="2" align="left">
			<input type="button" class="cssButton-gris-larger-Retornar" submit="true" style="width: 140px" value="REGISTRAR" onclick="registrar2();" />
	</td>
</tr>

<tr>
    <td colspan="2" align="left">

	</td>
</tr>


</table>

</div>



</c:if>



</table>



</div> 
</div>


</form>

<c:if test="${mensaje1 == 'registrado'}">
   <div class="mensaje">
				<span class="mensaje-texto">${muestra}</span>
				
			</div>

</c:if>

<c:if test="${mensaje1 == 'exito'}">
   <div class="mensaje">
				<span class="mensaje-texto">${muestra}</span>
				
			</div>
</c:if>	

<c:if test="${mensaje1 == '0001'}">
   <div class="mensaje">
				<span class="mensaje-texto">${muestra}</span>
				
			</div>
</c:if>								

</body>
</html>