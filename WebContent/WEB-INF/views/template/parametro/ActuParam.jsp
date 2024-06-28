<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
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

<title>Parametros</title>

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
	gap: 15px; flex-direction : column;
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
.form-con{
display: flex;
	flex-direction: row;
	align-items: center;
	gap:15px;
	margin: 5px 15px
}
</style>

<script type="text/javascript">
 
document.addEventListener('DOMContentLoaded', function () {
    var modal = document.getElementById('modal-modi');
    modal.style.display = 'none';
    var modalNuevo = document.getElementById('modal-actu');
    modalNuevo.style.display = 'none';
 var tabla = document.getElementById('modal-tabla');
    tabla.style.display = 'none';

}); 
function openModalTabla(id, valor) {
    var modal = document.getElementById('modal-tabla');
    modal.style.display = 'flex';
      var selectTabla = document.getElementById('opttblnone');
      
      
    	document.getElementById("titulo").textContent  = selectTabla.options[selectTabla.selectedIndex].text; 
	  document.getElementById("tabla-id").value = id;
      document.getElementById("tabla-valor").value = valor;
 
 }

function openModal(id, cod, nombre, descripcion) {
    var modal = document.getElementById('modal-modi');
    modal.style.display = 'flex';
   
	  document.getElementById("modificar-id").value = id;
      document.getElementById("modificar-codigo").value = cod;
      document.getElementById("modificar-nombre").value = nombre;
      document.getElementById("modificar-descripcion").value = descripcion; 
}
function openModalNuevo( ) {
    var modal = document.getElementById('modal-actu');
   var tabla = document.getElementById('modal-tabla');
    var selectTabla = document.getElementById('opttbl');
    
    if (selectTabla.value !== "0") {
		 document.getElementById("titulo").textContent  = selectTabla.options[selectTabla.selectedIndex].text; 
		  if (selectTabla.value == "1") {
			 modal.style.display = 'flex';
		}else{
		tabla.style.display = 'flex';
		    var select = document.getElementById("opttblnone");
  
    var opciones = select.options;

    for (var i = 0; i < opciones.length; i++) {
        if (opciones[i].value == selectTabla.value) {
            opciones[i].selected = true;
            break;
        }
    }
		}
		 
	}else{
		alert("Elija un parametro");
	}
}
function closeModal() {
    var modal = document.getElementById('modal-modi');
    modal.style.display = 'none';
}
function closeModalNuevo() {
    var modal = document.getElementById('modal-actu');
    modal.style.display = 'none';
}
function closeModalTabla() {
    var modal = document.getElementById('modal-tabla');
    modal.style.display = 'none';
}
window.onclick = function(event) {
    var modal = document.getElementById('modal-modi');
     var modalNuevo = document.getElementById('modal-actu');
    if (event.target == modal) {
        modal.style.display = 'none';
    }
     if (event.target == modalNuevo) {
        modalNuevo.style.display = 'none';
    }
}
</script>
<script type="text/javascript">

$(document).ready(function() {

  $("#items").hide();
 $("#opttbl").change(function() {
    // Obtener el valor seleccionado del primer select
 
      var selectedValue = $(this).val();
    
    if(selectedValue !== "0") {
      // Mostrar el segundo select si el valor seleccionado no es cero
      $("#items").show();
    // Realizar la petición AJAX
    $.ajax({
       url: "<%=request.getContextPath()%>/data",  
      method: "GET", // o "POST" según tu caso
       data: { tipo: selectedValue }, // Datos a enviar al servidor
      success: function(response) {
        // Manejar la respuesta del servidor y actualizar el segundo select
        $("#items").empty(); // Limpiar opciones actuales
         //console.log({response});
        // Agregar nuevas opciones basadas en la respuesta del servidor
        $.each(response, function(key, value) {
     	if(selectedValue==="1"){
     	          $("#items").append($("<option></option>").attr("value", value.id).text(value.cod +'-' +value.nombre));	
     	}else{
     	$("#items").append($("<option></option>").attr("value", value.id).text(value.valor ));
     	}
                 
       
        });
      },
      error: function(xhr, status, error) {
        // Manejar errores
        console.error("Error en la petición AJAX:", error);
      }
    });
    }else {
      // Ocultar el segundo select si el valor seleccionado es cero
      $("#items").hide();
    }
  
  
  });
});

 


</script>
</head>
<body>

	<c:url var="url" value="/" />

	<div class="container-box">
		<h2>ACTUALIZR PARAMETROS</h2>
		<div class="box-archivo  ">
<div class="box">
    <form  class="form-con" action="<%=request.getContextPath()%>/actuparamconsul" method="post">
        <span class="titulo">Seleccionar : </span>
        <select id="opttbl" name="opcion" style="font-size: 12px;width: 100px">
        	<option value="0" ${selectedOption == '0' ? 'selected="selected"' : ''}>Elige</option>
            <option value="1" ${selectedOption == '1' ? 'selected="selected"' : ''}>Sistema</option>
            <option value="2" ${selectedOption == '2' ? 'selected="selected"' : ''}>Area Usuaria</option>
            <option value="3" ${selectedOption == '3' ? 'selected="selected"' : ''}>Area Responsable</option>
            <option value="4" ${selectedOption == '4' ? 'selected="selected"' : ''}>Proceso Negocio</option>
            <option value="5" ${selectedOption == '5' ? 'selected="selected"' : ''}>Tipo Aplicacion</option>
            <option value="6" ${selectedOption == '6' ? 'selected="selected"' : ''}>Tiempo Atencion</option>
            <option value="7" ${selectedOption == '7' ? 'selected="selected"' : ''}>Version</option>
            <option value="8" ${selectedOption == '8' ? 'selected="selected"' : ''}>Lenguaje</option>
            <option value="9" ${selectedOption == '9' ? 'selected="selected"' : ''}>Base de datos</option>
            <option value="10" ${selectedOption == '10' ? 'selected="selected"' : ''}>Responsable Desa</option>
            <option value="11" ${selectedOption == '11' ? 'selected="selected"' : ''}>Fabrica</option>
        </select>
           <select name="item" id="items"style="font-size: 14px;width: 200px">
            <option value="0">Elige</option>
        </select>
        <button type="submit" class="btn-red">Consultar</button>
    </form>
    <button onClick="openModalNuevo()" class="btn-red">Nuevo</button>
</div>
	<c:if test="${not empty sistemas}">
			<div class="box-table">
			
				<display:table class="headerDisplay" name="sistemas" pagesize="10"
					id="item" requestURI="">
 					<display:column property="cod" title="Código" />
					<display:column property="nombre" title="Nombre" />
					<display:column property="descripcion" title="Descripción" />
					<display:column title="Modificar">
						<button onClick="openModal( '${item.id}', '${item.cod}', '${item.nombre}', '${item.descripcion}')" class="btn-red">
							<img
								src="<%=request.getContextPath()%>/assets/img/icon/check.png"
								alt="Botón con Imagen" />
						</button>
					</display:column>

				</display:table>
	

			</div>
	 
	</c:if>
		<c:if test="${not empty tablas}">
			<div class="box-table">
			
				<display:table class="headerDisplay" name="tablas" pagesize="10"
					id="item" requestURI="">
 					<display:column property="valor" title="Parametro" />
			 
					<display:column title="Modificar">
						<button onClick="openModalTabla( '${item.id}','${item.valor}')" class="btn-red">
							<img
								src="<%=request.getContextPath()%>/assets/img/icon/check.png"
								alt="Botón con Imagen" />
						</button>
					</display:column>

				</display:table>
	

			</div>
	 
	</c:if>
		<c:if test="${not empty mensaje}">
				<div class="box-botom">
				<span class="message message-success">${mensaje}</span>
				
			</div>
			</c:if>
	<c:if test="${not empty error}">
				<div class="box-botom">
				<span class="message message-error">${error}</span>
				
			</div>
			</c:if>
			
		</div>


	</div>
	<!-- MODAL ACTUALIZAR SISTEMA -->
	<div id="modal-modi" class="modal">
		<div class="modal-content">
			<div class="modal-content-top">
				<h3 class="titulo">Sistema</h3>
				<span onClick="closeModal()" class="close">&times;</span>
			</div>
			<div class="modal-content-body">
				<form action="<%=request.getContextPath()%>/param" method="post">
					<div class="box-form">
					<input type="hidden"  name="id"  id="modificar-id"    />
						<label for="name">Codigo:</label> <input type="text" id="modificar-codigo" 
							name="codigo" required />
					</div>
					<div class="box-form">
						<label for="name">Nombre:</label> <input type="text" id="modificar-nombre"
							name="nombre" required />
					</div>
					<div class="box-form">
						<label for="name">Descripcion:</label> <input type="text"
							id="modificar-descripcion" name="descripcion" required />
					</div>
					<div class="box-form">
						<input type="submit" class="btn-red" value="Modificar" />
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
<!-- MODAL NUEVO  SISTEMA -->	
		<div id="modal-actu" class="modal">
		<div class="modal-content">
			<div class="modal-content-top">
				<h3 class="titulo">Sistema</h3>
				<span onClick="closeModalNuevo()" class="close">&times;</span>
			</div>
			<div class="modal-content-body">
				<form action="<%=request.getContextPath()%>/param" method="post">
					<div class="box-form">
					 
						<label for="name">Codigo:</label> <input type="text" id="modificar-codigo" 
							name="codigo" required />
					</div>
					<div class="box-form">
						<label for="name">Nombre:</label> <input type="text" id="modificar-nombre"
							name="nombre" required />
					</div>
					<div class="box-form">
						<label for="name">Descripcion:</label> <input type="text"
							id="modificar-descripcion" name="descripcion" required />
					</div>
					<div class="box-form">
						<input type="submit" class="btn-red" value="Guardar" />
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	<!-- MODAL tabla -->	
	<div id="modal-tabla" class="modal">
		<div class="modal-content">
			<div class="modal-content-top">
				<h3 id="titulo" class="titulo">NN</h3>
				<span onClick="closeModalTabla()" class="close">&times;</span>
			</div>
			<div class="modal-content-body">
				<form action="<%=request.getContextPath()%>/param" method="post">
						<select id="opttblnone" name="tipoTabla" style="display: none">
				        	<option value="0" ${selectedOption == '0' ? 'selected="selected"' : ''}>Elige</option>
				            <option value="1" ${selectedOption == '1' ? 'selected="selected"' : ''}>Sistema</option>
				            <option value="2" ${selectedOption == '2' ? 'selected="selected"' : ''}>Area Usuaria</option>
				            <option value="3" ${selectedOption == '3' ? 'selected="selected"' : ''}>Area Responsable</option>
				            <option value="4" ${selectedOption == '4' ? 'selected="selected"' : ''}>Proceso Negocio</option>
				            <option value="5" ${selectedOption == '5' ? 'selected="selected"' : ''}>Tipo Aplicacion</option>
				            <option value="6" ${selectedOption == '6' ? 'selected="selected"' : ''}>Tiempo Atencion</option>
				            <option value="7" ${selectedOption == '7' ? 'selected="selected"' : ''}>Version</option>
				            <option value="8" ${selectedOption == '8' ? 'selected="selected"' : ''}>Lenguaje</option>
				            <option value="9" ${selectedOption == '9' ? 'selected="selected"' : ''}>Base de datos</option>
				            <option value="10" ${selectedOption == '10' ? 'selected="selected"' : ''}>Responsable Desa</option>
				            <option value="11" ${selectedOption == '11' ? 'selected="selected"' : ''}>Fabrica</option>
				        </select>
				<input type="hidden"  name="id"  id="tabla-id"    />
					<div class="box-form">					 
						<label for="name">Valor :</label> <input type="text" id="tabla-valor" name="valor" required />
					</div> 
					<div class="box-form">
						<input type="submit" class="btn-red" value="Guardar" />
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	
	
	
	
</body>
</html>