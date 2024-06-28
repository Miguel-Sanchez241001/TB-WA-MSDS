<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.io.UnsupportedEncodingException" %>
<%@ page import="java.io.IOException" %>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", " no-store ");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
 <style>
        input[type="text"], select, textarea {
            width: 200px;
            padding: 5px;
            font-size: 11px;
            border-radius: 5px;
            border: 1px solid #ccc; /* Borde por defecto */
        }

        input[type="text"].editado, select.editado , textarea.editado{
            border-color: blue; /* Borde azul cuando se edita */
        }

        button {
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        
         #mensajeError {
            color: red;
            display: none;
            position: absolute;
            margin-top: -20px; /* Posición del mensaje sobre el campo de texto */
        }
        
    </style>


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
 <script>
      function editarCampo() {
      
       var nivel = document.getElementById("nivel");  
        var areaUsuaria = document.getElementById("areaUsuaria");  
      
      var areaResponsable = document.getElementById("areaResponsable");  
      var areaProceso = document.getElementById("areaProceso");  
      
      var areaTipo = document.getElementById("areaTipo");      
        var plataforma = document.getElementById("plataforma");
            var rto = document.getElementById("rto");
            var mtdp = document.getElementById("mtdp");
            var rpo = document.getElementById("rpo");
            var mtdl = document.getElementById("mtdl");                    
            var mbco = document.getElementById("mbco");
            var version = document.getElementById("version");       
            var versionServ = document.getElementById("versionServ");
            var urlDesarrollo = document.getElementById("urlDesarrollo");
            var urlCertificacion = document.getElementById("urlCertificacion");
            var urlProduccion = document.getElementById("urlProduccion");
            var urlGit = document.getElementById("urlGit");
            var lenguaje = document.getElementById("lenguaje");
            var baseDatos = document.getElementById("baseDatos");
            var personaResp = document.getElementById("personaResp");
            var desaRealizado = document.getElementById("desaRealizado");

          var plataformaCom = document.getElementById("plataformaCom");
          var requisitos = document.getElementById("requisitos");
          var fecha = document.getElementById("fecha");
          var comentarios = document.getElementById("comentarios");
          
           nivel.classList.add("editado");
            nivel.removeAttribute("disabled");
            
             areaUsuaria.classList.add("editado");
            areaUsuaria.removeAttribute("disabled");
          
          areaResponsable.classList.add("editado");
            areaResponsable.removeAttribute("disabled");
            
            areaProceso.classList.add("editado");
            areaProceso.removeAttribute("disabled");
          
           areaTipo.classList.add("editado");
            areaTipo.removeAttribute("disabled");
          
        plataforma.classList.add("editado");
            plataforma.removeAttribute("disabled");
          
            rto.classList.add("editado");
            rto.removeAttribute("disabled");
          
            mtdp.classList.add("editado");
            mtdp.removeAttribute("disabled");
            
            rpo.classList.add("editado");
            rpo.removeAttribute("disabled");
            
            mtdl.classList.add("editado");
            mtdl.removeAttribute("disabled");
          
            mbco.classList.add("editado");
            mbco.removeAttribute("disabled");
          
           version.classList.add("editado");
            version.removeAttribute("disabled");
            
            versionServ.classList.add("editado");
            versionServ.removeAttribute("disabled");
            
            urlDesarrollo.classList.add("editado");
            urlDesarrollo.removeAttribute("readonly");
            
            urlCertificacion.classList.add("editado");
            urlCertificacion.removeAttribute("readonly");
            
            urlProduccion.classList.add("editado");
            urlProduccion.removeAttribute("readonly");
            
            urlGit.classList.add("editado");
            urlGit.removeAttribute("readonly");
            
            lenguaje.classList.add("editado");
            lenguaje.removeAttribute("disabled");
            
             baseDatos.classList.add("editado");
             baseDatos.removeAttribute("disabled");
            
             personaResp.classList.add("editado");
             personaResp.removeAttribute("disabled");
            
             desaRealizado.classList.add("editado");
            desaRealizado.removeAttribute("disabled");
            
        
            
             plataformaCom.classList.add("editado");
            plataformaCom.removeAttribute("disabled");
         
          requisitos.classList.add("editado");
            requisitos.removeAttribute("disabled");
            
              fecha.classList.add("editado");
            fecha.removeAttribute("disabled");
            
              comentarios.classList.add("editado");
            comentarios.removeAttribute("disabled");
            
            
           
            
            
        }
        
        function modificar(){
	 
	
editarCampo();

    // Envía los datos del formulario
    document.frmModificar.submit();	
	
		
	
}


        
        
        
    </script>

<script type="text/javascript">

function consultar(){
	 
	

	    	
		document.frmConsultar.submit();
		
	
}







</script>

 

</head>
<html>
<body>

<c:url var="url" value="/" />

<form id="frmConsultar" name="frmConsultar" method="post" action='<c:out value="${url}"/>consultarApli/' runat="server">

<div class="container">
  <h2 style="font-weight: bold">CONSULTA DE APLICACIONES</h2>
  

<div class="form-group">
<table  width="100%" class="form-group">
  <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Buscar:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		 <select id="aplicacion" name="aplicacion">
            <c:forEach var="item" items="${dataList}">
                <option value="${item}">${item}</option>
            </c:forEach>
        </select>
	</td>
</tr>
<tr>
    <td colspan="2" align="left">
			<input type="button" class="cssButton-gris-larger-Retornar" submit="true" style="width: 140px" value="CONSULTAR" onclick="consultar();" />
	</td>
</tr>
</table>
</div>

</form> 
 <div class="form-group">
<table  width="100%" class="form-group">


<c:if test="${mensaje == '0000'}">
<form id="frmModificar" name="frmModificar" method="post" action='<c:out value="${url}"/>modificar/' runat="server" enctype="multipart/form-data">
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
	 <input type="text" id="codSistema" name="codSistema" size="4" style="width: 50px; font-size: 11px"  maxlength="4"  value="${prog.codsistema}"  />  
  </td>
 </tr>
	
<tr>
  <td width="35%" align="left" > <label for="email" style="font-size: 14px; ">Nombre del Sistema:</label>
  </td>
															
  <td width="55%"  >
	  <input type="text" id="nomSistema" name="nomSistema" size="4" style="width: 500px; font-size: 11px"  maxlength="100"  value="${prog.nomsistema}" readonly />
 
  <br />
   
                  
  </td>
 </tr>
 
  <tr>
  <td width="35%" align="left" > <label for="email" style="font-size: 14px; ">Descripción del Sistema:</label>
  </td>
															
  <td width="55%"  >                                                                                                              
	  <textarea id="desSistema" name="desSistema" style="height: 50px; width: 500px; font-size: 11px" readonly >${prog.dessistema}</textarea>
	  </td>
 </tr>
	
	 <tr>
   <td width="35%" align="left" >
	<label for="email" style="font-size: 14px">Nivel:</label>
   </td>
   <td width="55%" align="left" style="font-size: 14px">
	   <select name="nivel" id="nivel" class="Editar Requerido" style="font-size: 14px" disabled>
			 <option value="Sistema" ${prog.nivelsistema eq 'Sistema' ? 'selected' : ''}>Sistema</option>
             <option value="Subsistema" ${prog.nivelsistema eq 'Subsistema' ? 'selected' : ''}>Subsistema</option>
	   </select>
  </td>
 </tr>
 
  <tr>														
	<td width="35%" align="left">
		<label for="email" style="font-size: 14px">Area Usuaria:</label>
	</td>
    <td width="55%" align="left" class="Editar Requerido" >
	
       
       <select id="areaUsuaria" name="areaUsuaria" style="font-size: 13px" disabled>
    <c:forEach var="item" items="${dataAreausu}">
        <option value="${item.valor}" ${prog.areausuaria eq item.valor ? 'selected' : ''}>${item.valor}</option>
    </c:forEach>
</select> 
        
       
	</td>
 </tr>	

 <tr>														
	<td width="35%" align="left">
		<label for="email" style="font-size: 14px">Area TI Responsable:</label>
	</td>
    <td width="55%" align="left" class="Editar Requerido" >
	 <select id="areaResponsable" name="areaResponsable" style="font-size: 13px" disabled>
            <c:forEach var="item" items="${dataListArea}">
                <option value="${item.valor}" ${prog.arearesponsable eq item.valor ? 'selected' : ''}>${item.valor}</option>
            </c:forEach>
        </select>
	</td>
 </tr>	
 
	
 <tr>														
	<td width="35%" align="left">
		<label for="email" style="font-size: 14px">Proceso de Negocio:</label>
	</td>
    <td width="55%" align="left" class="Editar Requerido" >
	 <select id="areaProceso" name="areaProceso" style="font-size: 13px" disabled>
            <c:forEach var="item" items="${dataLisProceso}">
                <option value="${item.valor}" ${prog.areaproceso eq item.valor ? 'selected' : ''}>${item.valor}</option>
            </c:forEach>
        </select>
	</td>
 </tr>	
 
  <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Tipo:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select id="areaTipo" name="areaTipo" style="font-size: 13px" disabled>
            <c:forEach var="item" items="${dataLisTipo}">
                <option value="${item.valor}" ${prog.areatipo eq item.valor ? 'selected' : ''}>${item.valor}</option>
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
		<select name="plataforma" id="plataforma" class="Editar Requerido" style="font-size: 14px" disabled>
				 <option value="OPEN" ${prog.plataforma eq 'OPEN' ? 'selected' : ''}>OPEN</option>
                 <option value="HOST" ${prog.plataforma eq 'HOST' ? 'selected' : ''}>HOST</option>
		</select>
	</td>
 </tr>
 
  <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">RTO:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
			<select id="rto" name="rto" style="font-size: 13px" disabled> 
            <c:forEach var="item" items="${dataLisTiempo}" >
                <option value="${item.valor}" ${prog.rto eq item.valor ? 'selected' : ''}>${item.valor}</option>
            </c:forEach>
        </select>
        
        
	</td>
</tr>


 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">MTDP:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select id="mtdp" name="mtdp" style="font-size: 13px" disabled>
            <c:forEach var="item" items="${dataLisTiempo}" >
                 <option value="${item.valor}" ${prog.mtdp eq item.valor ? 'selected' : ''}>${item.valor}</option>
            </c:forEach>
        </select>
	</td>
</tr>

 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">RPO:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select id="rpo" name="rpo" style="font-size: 13px" disabled>
            <c:forEach var="item" items="${dataLisTiempo}" >
                <option value="${item.valor}" ${prog.rpo eq item.valor ? 'selected' : ''}>${item.valor}</option>
            </c:forEach>
        </select>
	</td>
</tr>

 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">MTDL:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select id="mtdl" name="mtdl" style="font-size: 13px" disabled>
            <c:forEach var="item" items="${dataLisTiempo}" >
                 <option value="${item.valor}" ${prog.mtdl eq item.valor ? 'selected' : ''}>${item.valor}</option>
            </c:forEach>
        </select>
	</td>
</tr>

 <tr>
    <td width="35%" align="left" >
		<label for="email" style="font-size: 14px">MBCO:</label>
	</td>
															
	<td width="55%" align="left" style="font-size: 14px">
		<select id="mbco" name="mbco" style="font-size: 13px" disabled>
            <c:forEach var="item" items="${dataLisTiempo}">
                <option value="${item.valor}" ${prog.mbco eq item.valor ? 'selected' : ''}>${item.valor}</option>
            </c:forEach>
        </select>
	</td>
</tr>

<tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Versión:</label>
	</td>
	<td width="55%"  >
			<select id="version" name="version" style="font-size: 13px" disabled>
            <c:forEach var="item" items="${dataLisVersion}">
                <option value="${item.valor}" ${prog.version eq item.valor ? 'selected' : ''}>${item.valor}</option>
            </c:forEach>
        </select>
	</td>
</tr>

 <tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Versión desplegada Servidor Desarrollo:</label>
	</td>
	<td width="55%"  >
			<select id="versionServ" name="versionServ" style="font-size: 13px" disabled>
            <c:forEach var="item" items="${dataLisVersion}">
                 <option value="${item.valor}" ${prog.versiondesa eq item.valor ? 'selected' : ''}>${item.valor}</option>
            </c:forEach>
        </select>
        
     
        
        
	</td>
</tr>

<tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Url Desarrollo:</label>
	</td>
	<td width="55%"  >
		 <input type="text" id="urlDesarrollo" name="urlDesarrollo" size="4" style="width: 500px; font-size: 13px " value="${prog.urldesarrollo}" readonly  />
        
 
	</td>
</tr>	

<tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Url Certificación:</label>
	</td>
															
	<td width="55%"  >
		 <input type="text" id="urlCertificacion" name="urlCertificacion" size="4" style="width: 500px; font-size: 13px" value="${prog.urlcertificacio}" readonly  />
		 
	</td>
</tr>
															
<tr>
	<td width="35%" align="left" > <label for="email" style="font-size: 14px">Url Producción:</label>
	</td>
    <td width="55%"  >
		 <input type="text" id="urlProduccion" name="urlProduccion" size="4" style="width: 500px; font-size: 13px" value="${prog.urlproduccion}" readonly   />
	</td>
</tr>

 <tr>
	<td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Url Git:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		 <input type="text" id="urlGit" name="urlGit" size="4" style="width: 500px; font-size: 13px" value="${prog.urlgit}" readonly  />
	
		 
	</td>
 </tr>

<tr>
	<td width="35%" align="left">
		<label for="email" style="font-size: 14px">Lenguaje:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
			<select id="lenguaje" name="lenguaje" style="font-size: 13px"  disabled>
            <c:forEach var="item" items="${dataLisLenguaje}">
                <option value="${item.valor}" ${prog.lenguaje eq item.valor ? 'selected' : ''}>${item.valor}</option>
            </c:forEach>
        </select>
    
	</td>
 </tr>
										
<tr>
	 <td width="35%" align="left">
		 <label for="email" style="font-size: 14px"  >Base de datos:</label>
     </td>
	 <td width="55%" align="left" style="font-size: 14px">
			<select id="baseDatos" name="baseDatos" style="font-size: 13px" disabled>
            <c:forEach var="item" items="${dataLisBaseDatos}">
                 <option value="${item.valor}" ${prog.basedatos eq item.valor ? 'selected' : ''}>${item.valor}</option>
            </c:forEach>
        </select>
	 </td>
</tr>
															
 <tr>
	 <td width="35%" align="left">
		 <label for="email" style="font-size: 14px">Persona Responsable BN:</label>
	 </td>
	 <td width="55%" align="left" style="font-size: 14px">
		 	<select id="personaResp" name="personaResp" style="font-size: 13px" disabled>
            <c:forEach var="item" items="${dataLisResponsableDesa}">
                <option value="${item.valor}" ${prog.personaresp eq item.valor ? 'selected' : ''}>${item.valor}</option>
            </c:forEach>
        </select>
	 </td>
</tr>

<tr>
	 <td width="35%" align="left">
		 <label for="email" style="font-size: 14px">Desarrollo realizado por:</label>
	 </td>
	 <td width="55%" align="left" style="font-size: 14px">
		 <select id="desaRealizado" name="desaRealizado" style="font-size: 13px" disabled>
            <c:forEach var="item" items="${dataLisFabrica}">
                 <option value="${item.valor}" ${prog.desarealizado eq item.valor ? 'selected' : ''}>${item.valor}</option>
            </c:forEach>
        </select>
	 </td>
</tr> 

<tr>
	<td width="35%" align="left">
		<label for="email" style="font-size: 14px">Diagrama de Arquitectura:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">

<%
// Obtiene los datos del PDF del atributo de la solicitud
byte[] pdfData = (byte[]) request.getAttribute("pdfData");

if (pdfData != null) {
    // Codifica los datos del PDF en base64 para pasarlos en la URL
    String base64Encoded = new String(Base64.encodeBase64(pdfData));

    // Codifica el nombre del archivo en la URL
    String fileName = "Arquitectura.pdf";
    try {
        fileName = URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }

    // Construye la URL de descarga
    String downloadURL = "data:application/pdf;base64," + base64Encoded;
%>
    <a href="<%=downloadURL%>" download="<%=fileName%>">Descargar PDF</a>
<%
} else {
%>
    <p style="color: red;" >No se encontró ningún archivo PDF.</p> <input type="file" class="Editar Requerido" id="arquitectura" name="arquitectura" > 
<%
}
%>   
	</td>
</tr> 

 <tr>
	<td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Plataforma compatible:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		 <input type="text" id="plataformaCom" name="plataformaCom" size="4" style="width: 500px; font-size: 11px" class="uppercase" value="${prog.plataforma}" readonly />
	</td>
 </tr>
 
  <tr>
	<td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Requisistos Hardware:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		 <input type="text" id="requisitos" name="requisitos" size="4" style="width: 500px; font-size: 11px" class="uppercase" value="${prog.requisitos}" readonly/>
	</td>
 </tr>
 
   <tr>
	<td width="35%" align="left" >
		<label for="email" style="font-size: 14px">Fecha de última actualización:</label>
	</td>
	<td width="55%" align="left" style="font-size: 14px">
		<input type="text" id="fecha" name="fecha" value="${prog.fecha}" readonly>
	</td>
 </tr>
 
 <tr>
   <td width="35%" align="left" rows="1">
	 <label for="email" style="font-size: 14px">Comentarios:</label>
  </td>
  <td width="55%" align="left" >
	     <textarea id="comentarios" name="comentarios" style="height: 50px; width: 500px; font-size: 11px" readonly >${prog.comentarios}</textarea>
  </td>
 </tr>
                           
						
<tr>
    <td colspan="2" align="left">
	

      
        	<input type="button" class="cssButton-gris-larger-Retornar" submit="true" style="width: 140px" value="Ver Campos para Modificar" onclick="editarCampo();" />
	
        	<input type="button" class="cssButton-gris-larger-Retornar" submit="true" style="width: 140px" value="GUARDAR" onclick="modificar();" />
	
	
	</td>
</tr>


				
</div>
</c:if>
	
</table>

</div> 
</div>
								
			

</form>

<c:if test="${mensaje == 'exito'}">
   <div class="mensaje">
				<span class="mensaje-texto">${muestra}</span>
				
			</div>

</c:if>	

<c:if test="${mensaje == '0001'}">
   <div class="mensaje">
				<span class="mensaje-texto">${muestra}</span>
				
			</div>

</c:if>	
</body>
</html>