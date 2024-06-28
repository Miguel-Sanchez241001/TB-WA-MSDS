<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<style type="text/css">

/* pone lista inicial en blanco */
#nav ul, 
#nav li:hover ul ul,
#nav li:hover ul li:hover ul ul,
#nav li:hover ul li:hover ul li:hover ul ul,
#nav li:hover ul li:hover ul li:hover ul li:hover ul ul {position:absolute; left:-9999px; top:-9999px; width:0; height:0; margin:0; padding:0; list-style:none;}



/* visualizacion de sublista */

#nav li:hover {position:relative; z-index:200;}

#nav li:hover ul.sub {left:1px; top:38px; background: #fff; padding:3px; border:1px solid #C61316; white-space:nowrap; width:160px; height:auto; z-index:300;}
#nav li:hover ul.sub li {display:block; height:20px; position:relative; float:left; width:160px; font-weight:normal;}
#nav li:hover ul.sub li a {display:block; font-size:11px; height:20px; width:150px; line-height:18px; text-indent:5px; color:#000; text-decoration:none;border:1px solid #fff;}
#nav li ul.sub li a.fly {background:#50b5d0 80px 6px no-repeat;}
#nav li:hover ul.sub li a:hover {background:#C61316; color:#fff;}
#nav li:hover ul.sub li a.fly:hover {background:#C61316 250px 7px no-repeat; color:#fff;}

/* height:0px */
/* Menu principal */
#nav {padding:0; margin:0;list-style:none; width:100%; height:60px; background:#fff repeat-x; position:relative; z-index:500; font-family:arial, verdana, sans-serif;}
#nav li.top {display:block; float:left;}
#nav li a.top_link {display:block; float:left; height:25px;  color:#C61316;border:2px solid #C61316;  text-decoration:none; font-size:11px; font-weight:bold; padding:10px 0 0 10px; cursor:pointer; }
#nav li a.top_link span {float:left; display:block; padding:0 12px 0 12px; height:35px; background: right top no-repeat;}
#nav li a.top_link span.down {float:left; display:block; padding:0 24px 0 12px; height:35px; background: no-repeat right top;}

#nav li:hover a.top_link {background: no-repeat;background:#FFD5D5; color:#C61316;}
#nav li:hover a.top_link span {background: no-repeat right top;}
#nav li:hover a.top_link span.down {background: no-repeat right top; padding-bottom:3px;}

/* Estilos CSS para el menú */
body {
  font-family: Arial, sans-serif;
  margin: 0;
}

.menu {
  width: 200px;
  background-color: #f4f4f4;
}

.menu ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.menu li {
  position: relative;
}

.menu li a {
  display: block;
  padding: 10px 15px;
  color: #333;
  text-decoration: none;
}

.menu li:hover {
  background-color: #ccc;
}

/* Estilos para los submenús */
.submenu {
  display: none;
  position: absolute;
  top: 0;
  left: 100%;
  background-color: #f4f4f4;
}

.menu li:hover .submenu {
  display: block;
}

.submenu li a {
  padding: 10px 20px;
}

.submenu li:hover {
  background-color: #ccc;
}
</style>
</style>

<% 
	response.setHeader("Cache-Control","no-cache"); 
	response.setHeader("Cache-Control"," no-store ");
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader ("Expires", 0); 
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Acordeon.css" type="text/css"></link>

<script type="text/javascript">

    $(document).ready(function () {
        $('.menujq > ul > li > a').click(function () {
            var comprobar = $(this).next();
            $('.menujq li').removeClass('activa');
            $(this).closest('li').addClass('activa');
            if ((comprobar.is('ul')) && (comprobar.is(':visible'))) {
                $(this).closest('li').removeClass('activa');
                comprobar.slideUp('normal');
            }
            if ((comprobar.is('ul')) && (!comprobar.is(':visible'))) {
                $('.menujq ul ul:visible').slideUp('normal');
                comprobar.slideDown('normal');
            }
        });
    });

</script>
    
<div>

    <div class="menu">
    


        <ul>



		<sec:authorize access="hasAnyRole('grbn_msds_administrador')">
			<c:url var="url" value="/" />
			<sec:authorize url="/consultaFactibilidad/**">
				<li class="desplegable" id="RptConsultaFactibilidad">
	                <a href='<c:out value="${url}"/>aplicacion/' class="myButton"
	                    title="Seleccion Consulta Factibilidad">Registrar Sistema</a>
	                    
	           <ul class="submenu">
        <li>
        <a href='<c:out value="${url}"/>aplicacion/' class="myButton"
	                    title="">Aplicaciones</a>
        </li>
        
         <!-- 
        <li><a href='<c:out value="${url}"/>servicios/' class="myButton"
	                    title="">Servicios</a></li>
         --> 
      </ul>
	                    
	                    
	                    
	            </li>
			</sec:authorize>		
		</sec:authorize>
		
		
		

	
		
		<sec:authorize access="hasAnyRole('grbn_msds_administrador')">
			<c:url var="url" value="/" />
			
			
			
			<sec:authorize url="/consulta/**">
				<li class="desplegable" id="RptGenerarReporte">
	                <a href='<c:out value="${url}"/>consultaApli/' class="myButton"
	                    title="Consulta Generar Reporte">Consultar</a>
	                    
	                    	           <ul class="submenu">
        <li>
        <a href='<c:out value="${url}"/>consultaApli/' class="myButton"
	                    title="">Aplicaciones</a>
        </li>
        
        <li><a href='<c:out value="${url}"/>reporteappsis/' class="myButton"
	                    title="">Reporte Sistemas y Aplicaciones</a></li>
        
        <!-- 	     AGREGANDO OPCION CARGAR Y ACTULIZAR PARAMETROS      
        
        <li><a href='<c:out value="${url}"/>consultaServ/' class="myButton"
	                    title="">Servicios</a></li>
        -->  
      </ul>
	                    
	            </li>
			</sec:authorize>
		</sec:authorize>
		
<sec:authorize access="hasAnyRole('grbn_msds_administrador')">
			<c:url var="url" value="/" />
			
			
			
			<sec:authorize url="/parametros/**">
				<li class="desplegable" id="RptGenerarReporte">
	                <a href='<c:out value="${url}"/>cargaxls/' class="myButton"
	                    title="Consulta Generar Reporte">Parámetros</a>
	                    
<!-- 	     AGREGANDO OPCION CARGAR Y ACTULIZAR PARAMETROS    -->            
 	    <ul class="submenu">
        <li>
        <a href='<c:out value="${url}"/>cargaxls/' class="myButton"
	                    title="">Carga de Archivo</a>
        </li>
        <li><a href='<c:out value="${url}"/>actuparam/' class="myButton"
	                    title="">Actualizar Parametro</a></li>
        
      </ul>
<!-- 	     AGREGANDO OPCION CARGAR Y ACTULIZAR PARAMETROS    -->      
      
      
	            </li>
			</sec:authorize>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('grbn_msds_administrador')">
			<c:url var="url" value="/" />
			
			
			
			<sec:authorize url="/auditoria/**">
				<li class="desplegable" id="RptGenerarReporte">
	                <a href='<c:out value="${url}"/>logaudi/' class="myButton"
	                    title="Consulta Generar Reporte">Auditoria</a>
  
	                    <!-- 	     AGREGANDO OPCION CARGAR Y ACTULIZAR PARAMETROS    -->            
 	    <ul class="submenu">
        <li>
        <a href='<c:out value="${url}"/>logaudi/' class="myButton"
	                    title="">Ver logs</a>
        </li>
 
        
      </ul>
<!-- 	     AGREGANDO OPCION CARGAR Y ACTULIZAR PARAMETROS    -->      
 
	            </li>
			</sec:authorize>
		</sec:authorize>
		

        </ul>
    </div>

</div>

