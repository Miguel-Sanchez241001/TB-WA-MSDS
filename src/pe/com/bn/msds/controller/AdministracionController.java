package pe.com.bn.msds.controller;
import java.text.ParseException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont; 
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;


import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import pe.com.bn.msds.common.Constante;
import pe.com.bn.msds.common.Funciones;
import pe.com.bn.msds.common.LoggerBn;
import pe.com.bn.msds.common.View;
import pe.com.bn.msds.config.CustomUser;
import pe.com.bn.msds.dao.inf.ParametrosDAO;
import pe.com.bn.msds.dao.pool.CargarDocumento;
import pe.com.bn.msds.dao.pool.ConexionJndi;
import pe.com.bn.msds.model.Auditoria;
import pe.com.bn.msds.model.BnAplicaciones;
import pe.com.bn.msds.model.BnFactibilidad;
import pe.com.bn.msds.model.BnProgramas;
import pe.com.bn.msds.model.InvalidColumnCountException;
import pe.com.bn.msds.model.Programa;

import pe.com.bn.msds.model.Sistema;
import pe.com.bn.msds.model.Tablas;
import pe.com.bn.msds.reports.inte.RepoXLS;


@Controller
public class AdministracionController {

	private static LoggerBn log3 = LoggerBn
			.getInstance(AdministracionController.class.getName());
	
//	ConexionJndi dss = new ConexionJndi();

	@Autowired
	private ParametrosDAO parametrosDAO;
	
	@Autowired
	private RepoXLS repoXLS;

	// INICIO REPORTE SISTEMAS Y APLICATIVOS


		@RequestMapping(value = "reporteappsis", method = RequestMethod.GET)
		public String pageCargarReporte(ModelMap model, HttpServletRequest request,
				HttpServletResponse response) {
			String path = View.returnJsp(model, "reporte/ReporteView");
	 		return path;
		}
		@RequestMapping(value = "resulreport")
		public void sendReport(
				@RequestParam(value = "opcion", defaultValue = "0") Integer opcion,
	 			@RequestParam(value = "forFechaInicio", required = false) String forFechaInicio,
				@RequestParam(value = "forFechaFin", required = false) String forFechaFin,
				@RequestParam(value = "forDiaDate", required = false) String forDiaDate,
				
				ModelMap model, HttpServletRequest request,
				HttpServletResponse response) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			
			 response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		      response.setHeader("Content-Disposition", "attachment; filename=Reporte Sistemas y aplicaciones.xlsx");

		  
		      OutputStream outputStream = response.getOutputStream();
		      long startTime = System.currentTimeMillis();
		      List<Programa> programas = parametrosDAO.buscarProgramas(opcion,forFechaInicio,forFechaFin,forDiaDate);
		      long endTime = System.currentTimeMillis();
		      long duration = endTime - startTime;
		      double durationSeconds = duration / 1000.0;
		      log3.debug("Tiempo de consulta en segundos: "+durationSeconds, "1");
		      try {
			      long startTimeXLS = System.currentTimeMillis();

		        	Workbook workbook =  repoXLS.getReporteExcelProgramas(programas);
		            // Escribir el archivo Excel en el OutputStream
		            workbook.write(outputStream);
		            outputStream.flush();
		            workbook.close();
				     long endTimeXLS = System.currentTimeMillis();
				     long durationXLS = endTimeXLS - startTimeXLS;
				     double durationSecondsXLS = durationXLS / 1000.0;
				     log3.debug("Tiempo de generar EXCEL en segundos: "+durationSecondsXLS, "1");
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		            outputStream.close();
		        }
			
			
		}

	// FIN REPORTE SISTEMAS Y APLICATIVOS



	    private String capitalize(String str) {
	        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
		}

		public static List<String> getAttributeNames(Class<?> clazz) {
	        List<String> attributeNames = new ArrayList<String>();
	        for (Field field : clazz.getDeclaredFields()) {
	            attributeNames.add(field.getName());
	        }
	        return attributeNames;
	    }

	
	@RequestMapping(value = "aplicacion2", method = RequestMethod.POST)
	public String aplicacion2(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		DataSource dataSourceProxy = new TransactionAwareDataSourceProxy(ConexionJndi.getDataSource());
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(ConexionJndi.getDataSource()); 

		 String path="";
		 
			List<String> aplicacionList = new ArrayList<String>();
			 Connection conn = null;																		
			 PreparedStatement pstmt = null;				 
		     Statement stmt = null;
				    				    
			 String aplicacion =  (request.getParameter("aplicacion")).trim();
			 ConexionJndi dss = new ConexionJndi();    
				    			   
		try {

		      String sql = "SELECT F03_CODIGO, F03_NOMBRE, F03_DESCRIPCION FROM BNMSDSF03_APLICACIONES ORDER BY F03_CODIGO ASC";
		      conn =    dss.connect();
              stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql);

              while (rs.next()) {
            	  aplicacionList.add(rs.getString("F03_CODIGO"));
                 
             }   
	              
 		} catch (Exception e) {
			log3.error(e, "", "ERROR consultaFactibilidad");
			throw new Exception(e);
		}
		
		 request.setAttribute("aplicacionList", aplicacionList);
		 
		 if (aplicacion.length()>1){
			 
			 
			   
			 
			       	
	            	 String sqlAplicacion = "SELECT F01_CODSISTEMA FROM BNMSDSF01_PROGRAMAS WHERE '"+aplicacion+"' = F01_CODSISTEMA";
	            	 conn =    dss.connect();
    	              stmt = conn.createStatement();
	  		        String apli = "";
	  		       ResultSet rsApli = stmt.executeQuery(sqlAplicacion);
	            	   while (rsApli.next()) {
	 	            	
	            		   apli =rsApli.getString(1);
	 	     			
	 	              } 
	            	   
	            	   if (apli.length() > 2){
	            		 	  String mensaje1 = "registrado";
	            		  	  
	            			  
	            			  	model.addAttribute("mensaje1", mensaje1);
	            				model.addAttribute("muestra", "La aplicacion ya se encuentra registrada");
	            				
	            				path = View.returnJsp(model, "registro/aplicacion");
	            	   }else{
	            	
	            		   String sql1 = "SELECT F03_CODIGO, F03_NOMBRE, F03_DESCRIPCION FROM BNMSDSF03_APLICACIONES WHERE '"+aplicacion+"' = F03_CODIGO";
	     			      conn =    dss.connect();
	     	              stmt = conn.createStatement();
	     	              ResultSet rs = stmt.executeQuery(sql1);
	     	            
	     	              
	     	              BnAplicaciones item =new BnAplicaciones();
	     	              List registros = null;
	     	            	registros=new ArrayList(); 
	     	      
	            	   
	              while (rs.next()) {
	            	  item =new BnAplicaciones();				 
	      			
	     			 item.setCodigo(rs.getString(1));
	     			 item.setNombre(rs.getString(2));
	     			 item.setDescripcion(rs.getString(3));
	     			 
	     			 registros.add(item);     
	                  
	              } 
	              request.setAttribute("item", item); 
		
		
			 try {
		        	
		        	
						 
				    
		            String sql = "SELECT * FROM BNMSDSF04_AREAUSUARIA ORDER BY F04_AREAUSUARIA ASC";
                    List<Tablas> dataList =      jdbcTemplate.query(sql, new MapRowMapper("BNMSDSF04_AREAUSUARIA"));    
		              
                    String sqlArea = "SELECT * FROM BNMSDSF05_AREARESPONSABLE ORDER BY F05_AREARESPONSABLE ASC";
                    List<Tablas> dataListArea =      jdbcTemplate.query(sqlArea, new MapRowMapper("BNMSDSF05_AREARESPONSABLE"));   
		            
                    String sqlProceso = "SELECT * FROM BNMSDSF06_PROCESONEGOCIO ORDER BY F06_PROCESONEGOCIO ASC";
                    List<Tablas> dataLisProceso =      jdbcTemplate.query(sqlProceso, new MapRowMapper("BNMSDSF06_PROCESONEGOCIO"));   
		          
                    String sqlTipo = "SELECT * FROM BNMSDSF07_TIPOAPLICACION ORDER BY F07_TIPOAPLICACION ASC";
                    List<Tablas> dataLisTipo =      jdbcTemplate.query(sqlTipo, new MapRowMapper("BNMSDSF07_TIPOAPLICACION"));  
                    
                    String sqlTiempo = "SELECT * FROM BNMSDSF08_TIEMPOATENCION ORDER BY F08_TIEMPOATENCION ASC";
                    List<Tablas> dataLisTiempo =      jdbcTemplate.query(sqlTiempo, new MapRowMapper("BNMSDSF08_TIEMPOATENCION"));   
		          
                    String sqlVersion = "SELECT * FROM BNMSDSF09_VERSION ORDER BY F09_VERSION ASC";
                    List<Tablas> dataLisVersion =      jdbcTemplate.query(sqlVersion, new MapRowMapper("BNMSDSF09_VERSION"));  
                    
                    String sqlLenguaje = "SELECT * FROM BNMSDSF10_LENGUAJE ORDER BY F10_LENGUAJE ASC";
                    List<Tablas> dataLisLenguaje =      jdbcTemplate.query(sqlLenguaje, new MapRowMapper("BNMSDSF10_LENGUAJE"));
                    
                    String sqlBaseDatos = "SELECT * FROM BNMSDSF11_BASEDATOS ORDER BY F11_BASEDATOS ASC";
                    List<Tablas> dataLisBaseDatos =      jdbcTemplate.query(sqlBaseDatos, new MapRowMapper("BNMSDSF11_BASEDATOS"));
                    
                    String sqlResponsableDesa = "SELECT * FROM BNMSDSF12_RESPONSABLEDESA ORDER BY F12_RESPONSABLEDESA ASC";
                    List<Tablas> dataLisResponsableDesa =      jdbcTemplate.query(sqlResponsableDesa, new MapRowMapper("BNMSDSF12_RESPONSABLEDESA"));
                    
                    String sqlFabrica = "SELECT * FROM BNMSDSF13_FABRICA ORDER BY F13_FABRICA ASC";
                    List<Tablas> dataLisFabrica =      jdbcTemplate.query(sqlFabrica, new MapRowMapper("BNMSDSF13_FABRICA"));
                    
		            
		              request.setAttribute("dataList", dataList);
		              request.setAttribute("dataListArea", dataListArea);
		              request.setAttribute("dataLisProceso", dataLisProceso);
		              request.setAttribute("dataLisTipo", dataLisTipo);
		              request.setAttribute("dataLisTiempo", dataLisTiempo);
		              request.setAttribute("dataLisVersion", dataLisVersion);
		              request.setAttribute("dataLisLenguaje", dataLisLenguaje);
		              request.setAttribute("dataLisBaseDatos", dataLisBaseDatos);
		              request.setAttribute("dataLisResponsableDesa", dataLisResponsableDesa);
		              request.setAttribute("dataLisFabrica", dataLisFabrica);
		             
		        } catch (Exception e) {
		            e.printStackTrace();
		        }

			    String mensaje = "0000";
				model.addAttribute("mensaje", mensaje);

			path = View.returnJsp(model, "registro/aplicacion");
	            	   }
		 }

		return path;
	}	
	
	@RequestMapping(value = "aplicacion", method = RequestMethod.GET)
	public String aplicacion(ModelMap model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DataSource dataSourceProxy = new TransactionAwareDataSourceProxy(ConexionJndi.getDataSource());
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(ConexionJndi.getDataSource()); 

		 String path="";
		 
			List<String> aplicacionList = new ArrayList<String>();
			 Connection conn = null;																		
			 	PreparedStatement pstmt = null;				 
				    Statement stmt = null;
		
				    ConexionJndi dss = new ConexionJndi();    
					   
		try {

		      String sql = "SELECT F03_CODIGO, F03_NOMBRE, F03_DESCRIPCION FROM BNMSDSF03_APLICACIONES ORDER BY F03_CODIGO ASC";
		      conn =    dss.connect();
             stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);

             while (rs.next()) {
            	 aplicacionList.add(rs.getString("F03_CODIGO"));
                 
             }   
	              
           
	           
	             

		} catch (Exception e) {
			log3.error(e, "", "ERROR consultaFactibilidad");
			throw new Exception(e);
		}
		
		 request.setAttribute("aplicacionList", aplicacionList);
		 
		 //   String mensaje = "0001";
		//	model.addAttribute("mensaje", mensaje);
			
		

			path = View.returnJsp(model, "registro/aplicacion");

		

		return path;
	}

	@RequestMapping(value = "modificar", method = RequestMethod.POST)
	public String modificar(ModelMap model, @RequestParam("arquitectura") MultipartFile archivo,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DataSource dataSourceProxy = new TransactionAwareDataSourceProxy(ConexionJndi.getDataSource());
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(ConexionJndi.getDataSource()); 
		 
		 
		
		
		  

		String path = "";

		try {

			String codSistema =  request.getParameter("codSistema");
			String nomSistema =  request.getParameter("nomSistema");
			String desSistema =  request.getParameter("desSistema");
			String nivel =  request.getParameter("nivel");
			String areaUsuaria =  request.getParameter("areaUsuaria");
			String areaResponsable =  request.getParameter("areaResponsable");
			String areaProceso =  request.getParameter("areaProceso");
			String areaTipo =  request.getParameter("areaTipo");
			String plataforma =  request.getParameter("plataforma");
			String rto =  request.getParameter("rto");
			String mtdp =  request.getParameter("mtdp");
			String rpo =  request.getParameter("rpo");
			String mtdl =  request.getParameter("mtdl");
			String mbco =  request.getParameter("mbco");
			String version =  request.getParameter("version");
			String versionServ =  request.getParameter("versionServ");
			String urlDesarrollo =  request.getParameter("urlDesarrollo");
			String urlCertificacion =  request.getParameter("urlCertificacion");
			String urlProduccion =  request.getParameter("urlProduccion");
			String urlGit =  request.getParameter("urlGit");			
			String lenguaje =  request.getParameter("lenguaje");
			String baseDatos =  request.getParameter("baseDatos");
			String personaResp =  request.getParameter("personaResp");
			String desaRealizado =  request.getParameter("desaRealizado");
			byte[] fileBytes = archivo.getBytes();
			String plataformaCom =  request.getParameter("plataformaCom");
			String requisitos =  request.getParameter("requisitos");
			String fecha =  request.getParameter("fecha");
			String comentarios =  request.getParameter("comentarios");
					
			ConexionJndi dss = new ConexionJndi();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			StringBuffer sql = new StringBuffer();

						
					
					
					sql.append("UPDATE  BNMSDSF01_PROGRAMAS SET F01_CODSISTEMA = ? ,  F01_NOMSISTEMA = ? , F01_DESSISTEMA = ? , F01_NIVEL = ? , F01_AREAUSUARIA = ? ," +
							" F01_AREARESPONSABLE = ? , F01_AREAPROCESO = ? , F01_AREATIPO = ? , F01_PLATAFORMA = ? , F01_RTO = ? , F01_MTDP = ? , F01_RPO = ? , " +
							"F01_MTDL = ? , F01_MBCO = ? , F01_VERSION = ? , F01_VERSIONDESA = ? ," +
			      	    	"F01_URLDESARROLLO = ? , F01_URLCERTIFICACION = ? , F01_URLPRODUCCION = ? , F01_URLGIT = ? , F01_LENGUAJE = ? , " +
			      	    	"F01_BASEDATOS = ? , F01_PERSONARESP = ? , " +
			                "F01_DESAREALIZADO  = ? , F01_ARQUITECTURA = ?   ,  F01_PLATAFORMACOM  = ? , F01_REQUISITOS = ? , F01_FECHA = ? , " +
			                "F01_COMENTARIOS  = ? WHERE F01_CODSISTEMA = ? " ); 
				     
					
					
		  	      try {
		  			
		  	    	conn = dss.connect();
					conn.setAutoCommit(false);
					pstmt = conn.prepareStatement(sql.toString());
					
		  	 		pstmt.setString(1, codSistema);  	 	
		  	 		pstmt.setString(2, nomSistema);	     	 		
		  	 		pstmt.setString(3, desSistema);	
		  	 		pstmt.setString(4, nivel);	
		  	 		pstmt.setString(5, areaUsuaria);	
		  	 		pstmt.setString(6, areaResponsable);	
		  	 		pstmt.setString(7, areaProceso);	
		  	 		pstmt.setString(8, areaTipo);	
		  	 		pstmt.setString(9, plataforma);
		  	 		pstmt.setString(10, rto);	
		  	 		pstmt.setString(11, mtdp);	
		  	 		pstmt.setString(12, rpo);	
		  	 		pstmt.setString(13, mtdl);	
		  	 		pstmt.setString(14, mbco);	
		  	 		pstmt.setString(15, version);
		  	 		pstmt.setString(16, versionServ);
		  	 		pstmt.setString(17, urlDesarrollo);	
		  	 		pstmt.setString(18, urlCertificacion);	
		  	 		pstmt.setString(19, urlProduccion);	
		  	 		pstmt.setString(20, urlGit);
		  	 		pstmt.setString(21, lenguaje);
		  	 		pstmt.setString(22, baseDatos);
		  	 		pstmt.setString(23, personaResp);
		  	 		pstmt.setString(24, desaRealizado);
                     pstmt.setBytes(25, fileBytes);
                    pstmt.setString(26, plataformaCom);
                    pstmt.setString(27, requisitos);
                    pstmt.setString(28, fecha);
                    pstmt.setString(29, comentarios);
		  	    	
                    pstmt.setString(30, codSistema);
                    
                    pstmt.executeUpdate();
        	 		conn.commit();
        	 		
        	 		conn =    dss.connect();
                    
		  		} catch (Exception e) {

		  			log3.error(e, "", e.getMessage());
		  			
		  			if (conn != null)
		  				conn.rollback();
		  			
		  			log3.debug("LA CONEXION DE BASE DE DATOS SE HA CERRADO CORRECTAMENTE", "");
		  						
		  			throw e;
		  			
		  		} finally {
		  			
		  			if (conn != null)
		  				conn.setAutoCommit(true);
		  			
		  			if (pstmt != null) {
		  				try {
		  					pstmt.close();
		  				} catch (Exception e) {
		  				}
		  				pstmt = null;
		  			}
		  			if (conn != null) {
		  				try {
		  					conn.close();
		  				} catch (Exception e) {
		  				}
		  				conn = null;
		  			}
		  		}
				
			// iniciando la ventana
			
			//String mensaje = "0000";
			//model.addAttribute("mensaje", mensaje);
		  	    List<String> dataList = new ArrayList<String>();     
		  		try {
		  			
		  			 Statement stmt1 = null;
		  			

				      String sqlApli = "SELECT F03_CODIGO, F03_NOMBRE, F03_DESCRIPCION FROM BNMSDSF03_APLICACIONES ORDER BY F03_CODIGO ASC";
				      conn =    dss.connect();
		             stmt1 = conn.createStatement();
		             ResultSet rs = stmt1.executeQuery(sqlApli);

		             while (rs.next()) {
		            	 dataList.add((rs.getString("F03_CODIGO")).trim());
		                 
		             }   
			              
					} catch (Exception e) {
					log3.error(e, "", "ERROR consultaFactibilidad");
					throw new Exception(e);
				}
		  		 String usuario = obtenerUsuarioAutenticado().getNombre();
		  		registrarLogAuditoria(Constante.CONST_ACCION_LOG_ACTUALIZACION,"BNMSDSF01_PROGRAMAS",codSistema);
				
		  		
				 request.setAttribute("dataList", dataList);  
		  	      
		  	  String mensaje = "exito";
		  	  
		  
		  	model.addAttribute("mensaje", mensaje);
			model.addAttribute("muestra", "Se actualizaron los datos correctamente ");
			
		//	model.addAttribute("mensaje", "Se guardaron los datos correctamente ");

			path = View.returnJsp(model, "consulta/consultaApli");
			
			
		

		} catch (Exception e) {
			log3.error(e, "", "ERROR consultaFactibilidad");
			throw new Exception(e);
		}
		

		return path;
	}

	 private String armarInsertSqlSin(String tabla, List<String> columnas,int colueliminados) {
		 String sql = "INSERT INTO " + tabla + " (";
		 columnas = columnas.subList(0, columnas.size() - colueliminados);
			 // Añadir columnas
	        for (int i = 0; i < columnas.size(); i++) {
	        	sql = sql + columnas.get(i);
	            if (i < columnas.size() - 1) {
	            	sql = sql + ", ";
	            }
	        }
	       sql = sql + ") VALUES (";
	      for (int i = 0; i < columnas.size(); i++) {
	    	 sql = sql + "?";
	            if (i < columnas.size() - 1) {
	            	sql = sql + ", ";
	            }
	        }
	     sql = sql + ")";
		return sql;
	}	 
	 private JdbcTemplate jdbcTemplate;
	
	public void registrarLogAuditoria(String accion,String tabla,String valor) throws Exception{
		
		String usuario = obtenerUsuarioAutenticado().getNombre();
    	String operacion = accion
    			.replace(Constante.CONST_ACCION_LOG_USER, usuario)
    			.replace(Constante.CONST_ACCION_LOG_TABLA, tabla)
    			.replace(Constante.CONST_ACCION_LOG_VALOR, valor);
    	List<String> columnas  = Constante.getTableColumnsMap().get(Constante.TABLE_BNMSDSF14_AUDITORIA) ;
    	Auditoria auditoria = new Auditoria();
    	
    	
    // String fechaFormateada = new SimpleDateFormat("dd/MM/yyyy").format(new Date());	
    //	Date dia = new Date(System.currentTimeMillis());
    	
    	Date fechaActual = new Date();
    	SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
    	String fechaFormateada = formateador.format(fechaActual);
    	
    	Date fecha = null;
    	try {
    	    fecha = formateador.parse(fechaFormateada);
    	} catch (ParseException e) {
    	    e.printStackTrace();
    	}
    	auditoria.setFecha(new java.sql.Date(fecha.getTime()));
    	auditoria.setHora(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
    	auditoria.setUsuario(usuario);
    	auditoria.setAccion(operacion);
    	auditoria.setAplicacion(tabla);
    	String sql = armarInsertSqlSin(Constante.TABLE_BNMSDSF14_AUDITORIA,columnas,3);
    	
    
    ConexionJndi dss = new ConexionJndi();
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	StringBuffer sql2 = new StringBuffer();
	
				
			sql2.append("INSERT INTO BNMSDSF14_AUDITORIA (F14_FECHA, F14_HORA, F14_USUARIO, F14_ACCION, F14_APLICACION) VALUES (?, ?, ?, ?, ?)");
		      
	  	        
  	      try {
  			
  	    	conn = dss.connect();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql2.toString());
			
  	 		pstmt.setDate(1, (java.sql.Date) auditoria.getFecha());  	 	
  	 		pstmt.setString(2, auditoria.getHora());	     	 		
  	 		pstmt.setString(3, usuario);	
  	 		pstmt.setString(4, operacion);	
  	 		pstmt.setString(5, tabla);	
  	 		
  	    	  
            pstmt.executeUpdate();
            
            conn.commit(); 
  		} catch (Exception e) {

  			log3.error(e, "", e.getMessage());
  			
  			if (conn != null)
  				conn.rollback();
  			
  			log3.debug("LA CONEXION DE BASE DE DATOS SE HA CERRADO CORRECTAMENTE", "");
  						
  			throw e;
  			
  		} finally {
  			
  			if (conn != null)
  				conn.setAutoCommit(true);
  			
  			if (pstmt != null) {
  				try {
  					pstmt.close();
  				} catch (Exception e) {
  				}
  				pstmt = null;
  			}
  			if (conn != null) {
  				try {
  					conn.close();
  				} catch (Exception e) {
  				}
  				conn = null;
  			}
  		}
    	
    	
    /*	jdbcTemplate.update(sql, auditoria.getFecha(),
    			auditoria.getHora(),
    			auditoria.getUsuario(),
    			auditoria.getAccion(),
    			valor
    			);
	*/
	}
    	
	@RequestMapping(value = "aplicacionreg", method = RequestMethod.POST)
	public String aplicacionreg(ModelMap model,
			HttpServletRequest request,@RequestParam("arquitectura") MultipartFile archivo, HttpServletResponse response)
			throws Exception {
		DataSource dataSourceProxy = new TransactionAwareDataSourceProxy(ConexionJndi.getDataSource());
		 JdbcTemplate jdbcTemplate = new JdbcTemplate(ConexionJndi.getDataSource()); 
		 
		 
		 
		 

		 try {
	        	
	        	
					 
			    
	            String sql = "SELECT * FROM BNMSDSF04_AREAUSUARIA ORDER BY F04_AREAUSUARIA ASC";
               List<Tablas> dataList =      jdbcTemplate.query(sql, new MapRowMapper("BNMSDSF04_AREAUSUARIA"));    
	              
               String sqlArea = "SELECT * FROM BNMSDSF05_AREARESPONSABLE ORDER BY F05_AREARESPONSABLE ASC";
               List<Tablas> dataListArea =      jdbcTemplate.query(sqlArea, new MapRowMapper("BNMSDSF05_AREARESPONSABLE"));   
	            
               String sqlProceso = "SELECT * FROM BNMSDSF06_PROCESONEGOCIO ORDER BY F06_PROCESONEGOCIO ASC";
               List<Tablas> dataLisProceso =      jdbcTemplate.query(sqlProceso, new MapRowMapper("BNMSDSF06_PROCESONEGOCIO"));   
	          
               String sqlTipo = "SELECT * FROM BNMSDSF07_TIPOAPLICACION ORDER BY F07_TIPOAPLICACION ASC";
               List<Tablas> dataLisTipo =      jdbcTemplate.query(sqlTipo, new MapRowMapper("BNMSDSF07_TIPOAPLICACION"));  
               
               String sqlTiempo = "SELECT * FROM BNMSDSF08_TIEMPOATENCION ORDER BY F08_TIEMPOATENCION ASC";
               List<Tablas> dataLisTiempo =      jdbcTemplate.query(sqlTiempo, new MapRowMapper("BNMSDSF08_TIEMPOATENCION"));   
	          
               String sqlVersion = "SELECT * FROM BNMSDSF09_VERSION ORDER BY F09_VERSION ASC";
               List<Tablas> dataLisVersion =      jdbcTemplate.query(sqlVersion, new MapRowMapper("BNMSDSF09_VERSION"));  
               
               String sqlLenguaje = "SELECT * FROM BNMSDSF10_LENGUAJE ORDER BY F10_LENGUAJE ASC";
               List<Tablas> dataLisLenguaje =      jdbcTemplate.query(sqlLenguaje, new MapRowMapper("BNMSDSF10_LENGUAJE"));
               
               String sqlBaseDatos = "SELECT * FROM BNMSDSF11_BASEDATOS ORDER BY F11_BASEDATOS ASC";
               List<Tablas> dataLisBaseDatos =      jdbcTemplate.query(sqlBaseDatos, new MapRowMapper("BNMSDSF11_BASEDATOS"));
               
               String sqlResponsableDesa = "SELECT * FROM BNMSDSF12_RESPONSABLEDESA ORDER BY F12_RESPONSABLEDESA ASC";
               List<Tablas> dataLisResponsableDesa =      jdbcTemplate.query(sqlResponsableDesa, new MapRowMapper("BNMSDSF12_RESPONSABLEDESA"));
               
               String sqlFabrica = "SELECT * FROM BNMSDSF13_FABRICA ORDER BY F13_FABRICA ASC";
               List<Tablas> dataLisFabrica =      jdbcTemplate.query(sqlFabrica, new MapRowMapper("BNMSDSF13_FABRICA"));
               
	            
	              request.setAttribute("dataList", dataList);
	              request.setAttribute("dataListArea", dataListArea);
	              request.setAttribute("dataLisProceso", dataLisProceso);
	              request.setAttribute("dataLisTipo", dataLisTipo);
	              request.setAttribute("dataLisTiempo", dataLisTiempo);
	              request.setAttribute("dataLisVersion", dataLisVersion);
	              request.setAttribute("dataLisLenguaje", dataLisLenguaje);
	              request.setAttribute("dataLisBaseDatos", dataLisBaseDatos);
	              request.setAttribute("dataLisResponsableDesa", dataLisResponsableDesa);
	              request.setAttribute("dataLisFabrica", dataLisFabrica);
	             
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		  

		String path = "";

		try {

			String codSistema =  request.getParameter("codSistema");
			String nomSistema =  request.getParameter("nomSistema");
			String desSistema =  request.getParameter("desSistema");
			String nivel =  request.getParameter("nivel");
			String areaUsuaria =  request.getParameter("areaUsuaria");
			String areaResponsable =  request.getParameter("areaResponsable");
			String areaProceso =  request.getParameter("areaProceso");
			String areaTipo =  request.getParameter("areaTipo");
			String plataforma =  request.getParameter("plataforma");
			String rto =  request.getParameter("rto");
			String mtdp =  request.getParameter("mtdp");
			String rpo =  request.getParameter("rpo");
			String mtdl =  request.getParameter("mtdl");
			String mbco =  request.getParameter("mbco");
			String version =  request.getParameter("version");
			String versionServ =  request.getParameter("versionServ");
			String urlDesarrollo =  request.getParameter("urlDesarrollo");
			String urlCertificacion =  request.getParameter("urlCertificacion");
			String urlProduccion =  request.getParameter("urlProduccion");
			String urlGit =  request.getParameter("urlGit");			
			String lenguaje =  request.getParameter("lenguaje");
			String baseDatos =  request.getParameter("baseDatos");
			String personaResp =  request.getParameter("personaResp");
			String desaRealizado =  request.getParameter("desaRealizado");
			byte[] fileBytes = archivo.getBytes();
			String plataformaCom =  request.getParameter("plataformaCom");
			String requisitos =  request.getParameter("requisitos");
			String fecha =  request.getParameter("fecha");
			String comentarios =  request.getParameter("comentarios");
					
			ConexionJndi dss = new ConexionJndi();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			StringBuffer sql = new StringBuffer();

						
					sql.append("INSERT INTO BNMSDSF01_PROGRAMAS (F01_CODSISTEMA, F01_NOMSISTEMA, F01_DESSISTEMA, F01_NIVEL, F01_AREAUSUARIA, F01_AREARESPONSABLE, " +
				      	    	"F01_AREAPROCESO, F01_AREATIPO, F01_PLATAFORMA, F01_RTO, F01_MTDP, F01_RPO, F01_MTDL, F01_MBCO, F01_VERSION, F01_VERSIONDESA," +
				      	    	" F01_URLDESARROLLO, F01_URLCERTIFICACION," +
				                "F01_URLPRODUCCION, F01_URLGIT, F01_LENGUAJE, F01_BASEDATOS, F01_PERSONARESP, " +
				                "F01_DESAREALIZADO,  F01_ARQUITECTURA,  F01_PLATAFORMACOM, F01_REQUISITOS, F01_FECHA, F01_COMENTARIOS ) " +
				                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				      
			  	        
		  	      try {
		  			
		  	    	conn = dss.connect();
					conn.setAutoCommit(false);
					pstmt = conn.prepareStatement(sql.toString());
					
		  	 		pstmt.setString(1, codSistema);  	 	
		  	 		pstmt.setString(2, nomSistema);	     	 		
		  	 		pstmt.setString(3, desSistema);	
		  	 		pstmt.setString(4, nivel);	
		  	 		pstmt.setString(5, areaUsuaria);	
		  	 		pstmt.setString(6, areaResponsable);	
		  	 		pstmt.setString(7, areaProceso);	
		  	 		pstmt.setString(8, areaTipo);	
		  	 		pstmt.setString(9, plataforma);
		  	 		pstmt.setString(10, rto);	
		  	 		pstmt.setString(11, mtdp);	
		  	 		pstmt.setString(12, rpo);	
		  	 		pstmt.setString(13, mtdl);	
		  	 		pstmt.setString(14, mbco);	
		  	 		pstmt.setString(15, version);
		  	 		pstmt.setString(16, versionServ);
		  	 		pstmt.setString(17, urlDesarrollo);	
		  	 		pstmt.setString(18, urlCertificacion);	
		  	 		pstmt.setString(19, urlProduccion);	
		  	 		pstmt.setString(20, urlGit);
		  	 		pstmt.setString(21, lenguaje);
		  	 		pstmt.setString(22, baseDatos);
		  	 		pstmt.setString(23, personaResp);
		  	 		pstmt.setString(24, desaRealizado);
                    pstmt.setBytes(25, fileBytes);
                    pstmt.setString(26, plataformaCom);
                    pstmt.setString(27, requisitos);
                    pstmt.setString(28, fecha);
                    pstmt.setString(29, comentarios);
		  	    	  
                    pstmt.executeUpdate();
                    
                    conn.commit(); 
		  		} catch (Exception e) {

		  			log3.error(e, "", e.getMessage());
		  			
		  			if (conn != null)
		  				conn.rollback();
		  			
		  			log3.debug("LA CONEXION DE BASE DE DATOS SE HA CERRADO CORRECTAMENTE", "");
		  						
		  			throw e;
		  			
		  		} finally {
		  			
		  			if (conn != null)
		  				conn.setAutoCommit(true);
		  			
		  			if (pstmt != null) {
		  				try {
		  					pstmt.close();
		  				} catch (Exception e) {
		  				}
		  				pstmt = null;
		  			}
		  			if (conn != null) {
		  				try {
		  					conn.close();
		  				} catch (Exception e) {
		  				}
		  				conn = null;
		  			}
		  		}
				
			// iniciando la ventana
			
			//String mensaje = "0000";
			//model.addAttribute("mensaje", mensaje);
		  	    List<String> aplicacionList = new ArrayList<String>();     
		  		try {
		  			
		  			 Statement stmt1 = null;
		  			

				      String sqlApli = "SELECT F03_CODIGO, F03_NOMBRE, F03_DESCRIPCION FROM BNMSDSF03_APLICACIONES ORDER BY F03_CODIGO ASC";
				      conn =    dss.connect();
		             stmt1 = conn.createStatement();
		             ResultSet rs = stmt1.executeQuery(sqlApli);

		             while (rs.next()) {
		            	 aplicacionList.add(rs.getString("F03_CODIGO"));
		                 
		             }   
			              
					} catch (Exception e) {
					log3.error(e, "", "ERROR consultaFactibilidad");
					throw new Exception(e);
				}
				
				 request.setAttribute("aplicacionList", aplicacionList);  
				 
				 
				 String mensaje = null;     
		  	  String mensaje1 = "exito";
		  	  
		  	  String usuario = obtenerUsuarioAutenticado().getNombre();
		  	  
		  	registrarLogAuditoria(Constante.CONST_ACCION_LOG_ACTUALIZACION,"BNMSDSF01_PROGRAMAS",codSistema);
		  
		  	model.addAttribute("mensaje", mensaje);
			model.addAttribute("mensaje1", mensaje1);
			model.addAttribute("muestra", "Se guardaron los datos correctamente ");
			
		//	model.addAttribute("mensaje", "Se guardaron los datos correctamente ");

			path = View.returnJsp(model, "registro/aplicacion");

		} catch (Exception e) {
			log3.error(e, "", "ERROR consultaFactibilidad");
			throw new Exception(e);
		}

		return path;
	}

	
	
	
	
	@RequestMapping(value = "servicios", method = RequestMethod.GET)
	public String servicios(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String path = "";

		try {

			log3.debug("consultaFactibilidad", "1");

			// iniciando la ventana
			request.setAttribute("tipoDocConsulta", "01");
			request.setAttribute("numeroDocConsulta", "");
			request.setAttribute("plazoConsulta", "");
			request.setAttribute("cpConsulta", "N");
			request.setAttribute("sdgConsulta", "0");

			path = View.returnJsp(model, "consulta/servicios");

		} catch (Exception e) {
			log3.error(e, "", "ERROR consultaFactibilidad");
			throw new Exception(e);
		}

		return path;
	}

	@RequestMapping(value = "auditoria", method = RequestMethod.GET)
	public String auditoria(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String path = "";

		try {

			log3.debug("consultaFactibilidad", "1");

			// iniciando la ventana
			request.setAttribute("tipoDocConsulta", "01");
			request.setAttribute("numeroDocConsulta", "");
			request.setAttribute("plazoConsulta", "");
			request.setAttribute("cpConsulta", "N");
			request.setAttribute("sdgConsulta", "0");

			path = View.returnJsp(model, "consulta/auditoria");

		} catch (Exception e) {
			log3.error(e, "", "ERROR consultaFactibilidad");
			throw new Exception(e);
		}

		return path;
	}

	@RequestMapping(value = "consulta", method = RequestMethod.GET)
	public String consulta(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String path = "";

		try {

			log3.debug("consultaFactibilidad", "1");

			// iniciando la ventana
			request.setAttribute("tipoDocConsulta", "01");
			request.setAttribute("numeroDocConsulta", "");
			request.setAttribute("plazoConsulta", "");
			request.setAttribute("cpConsulta", "N");
			request.setAttribute("sdgConsulta", "0");

			path = View.returnJsp(model, "consulta/consulta");

		} catch (Exception e) {
			log3.error(e, "", "ERROR consultaFactibilidad");
			throw new Exception(e);
		}

		return path;
	}

	@RequestMapping(value = "consultaApli", method = RequestMethod.GET)
	public String consultaApli(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String path = "";
		ConexionJndi dss = new ConexionJndi();
		
	//	DataSource dataSourceProxy = new TransactionAwareDataSourceProxy(ConexionJndi.getDataSource());
		// JdbcTemplate jdbcTemplate = new JdbcTemplate(ConexionJndi.getDataSource()); 
			List<String> dataList = new ArrayList<String>();
			 Connection conn = null;																		
			 	PreparedStatement pstmt = null;				 
				    Statement stmt = null;
		
				    
				    
				   
		try {

		      String sql = "SELECT F03_CODIGO, F03_NOMBRE, F03_DESCRIPCION FROM BNMSDSF03_APLICACIONES ORDER BY F03_CODIGO ASC";
		      conn =    dss.connect();
              stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql);

              
              

              while (rs.next()) {
                  dataList.add(rs.getString("F03_CODIGO"));
                  
              }   
	              
            
	           
	             

		} catch (Exception e) {
			log3.error(e, "", "ERROR consultaFactibilidad");
			throw new Exception(e);
		}
		
		 request.setAttribute("dataList", dataList);

			path = View.returnJsp(model, "consulta/consultaApli");

		return path;
	}

	
	
	@RequestMapping(value = "consultarApli", method = RequestMethod.POST)
	public String consultarApli(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String path = "";
		ConexionJndi dss = new ConexionJndi();
		
		List<String> dataList = new ArrayList<String>();
		 Connection conn = null;																		
		 	PreparedStatement pstmt = null;				 
			    Statement stmt = null;
		
		String codSistema =  (request.getParameter("aplicacion")).trim();
		
		if (codSistema.length()<1){
				   
		try {

			   String sql = "SELECT F03_CODIGO, F03_NOMBRE, F03_DESCRIPCION FROM BNMSDSF03_APLICACIONES ORDER BY F03_CODIGO ASC";
			      conn =    dss.connect();
	              stmt = conn.createStatement();
	              ResultSet rs = stmt.executeQuery(sql);
	              while (rs.next()) {
	                  dataList.add(rs.getString("F03_CODIGO"));
	                  
	              }   
		              
	        request.setAttribute("dataList", dataList);
			String mensaje = "0000";
			model.addAttribute("mensaje", mensaje);
			
			model.addAttribute("mensaje", "Archivo subido exitosamente: ");         

		} catch (Exception e) {
			log3.error(e, "", "ERROR consultaFactibilidad");
			throw new Exception(e);
		}
		
		}else{
			
			try {
				

				 String sql = "SELECT F01_CODSISTEMA, F01_NOMSISTEMA, F01_DESSISTEMA, F01_NIVEL, F01_AREAUSUARIA, F01_AREARESPONSABLE, F01_AREAPROCESO, F01_AREATIPO," +
				 	          "F01_PLATAFORMA, F01_RTO, F01_MTDP, F01_RPO, F01_MTDL, F01_MBCO, F01_VERSION, F01_VERSIONDESA, F01_URLDESARROLLO, F01_URLCERTIFICACION,  F01_URLPRODUCCION, F01_URLGIT, " +
				 	          "F01_LENGUAJE, F01_BASEDATOS, F01_PERSONARESP, F01_DESAREALIZADO, F01_ARQUITECTURA , F01_PLATAFORMACOM, F01_REQUISITOS, F01_FECHA,  F01_COMENTARIOS" +
				 	          " FROM BNMSDSF01_PROGRAMAS WHERE F01_CODSISTEMA = '"+codSistema+"'";
			 
				  String sqlCodigo = "SELECT F03_CODIGO, F03_NOMBRE, F03_DESCRIPCION FROM BNMSDSF03_APLICACIONES ORDER BY F03_CODIGO ASC";
				  
			      conn =    dss.connect();
	              stmt = conn.createStatement();
	              ResultSet rs = stmt.executeQuery(sql);
	              
	              BnProgramas prog =new BnProgramas();
	              List registros = null;
	            	registros=new ArrayList(); 
	              
	            	InputStream input = null;
	            	
	              while (rs.next()) {
	            	  prog =new BnProgramas();				 
	      			
	            	  prog.setCodsistema(rs.getString(1));
	            	  prog.setNomsistema(rs.getString(2));
	            	  prog.setDessistema(rs.getString(3));
	            	  prog.setNivelsistema(rs.getString(4));
	            	  prog.setAreausuaria(rs.getString(5));
	            	  prog.setArearesponsable(rs.getString(6));
	            	  prog.setAreaproceso(rs.getString(7));
	            	  prog.setAreatipo(rs.getString(8));	            	  
	            	  prog.setPlataforma(rs.getString(9));
	            	  prog.setRto(rs.getString(10));
	            	  prog.setMtdp(rs.getString(11));
	            	  prog.setRpo(rs.getString(12));
	            	  prog.setMtdl(rs.getString(13));
	            	  prog.setMbco(rs.getString(14));
	            	  prog.setVersion(rs.getString(15));
	            	  prog.setVersiondesa(rs.getString(16));
	            	  prog.setUrldesarrollo(rs.getString(17));
	            	  prog.setUrlcertificacio(rs.getString(18));
	            	  prog.setUrlproduccion(rs.getString(19));
	            	  prog.setUrlgit(rs.getString(20));
	            	  prog.setLenguaje(rs.getString(21));
	            	  prog.setBasedatos(rs.getString(22));
	            	  prog.setPersonaresp(rs.getString(23));
	            	  prog.setDesarealizado(rs.getString(24));
	            	  prog.setArquitectura(rs.getBlob(25));
	            	  prog.setPlataformacom(rs.getString(26));
	            	  prog.setRequisitos(rs.getString(27));
	            	  prog.setFecha(rs.getString(28));
	            	  prog.setComentarios(rs.getString(29));
	            	  
	            	  
	            	  
	     			 registros.add(prog);     
	                  
	              }  
	              ResultSet rsCodigo = stmt.executeQuery(sqlCodigo);
	              while (rsCodigo.next()) {
	                  dataList.add(rsCodigo.getString("F03_CODIGO"));
	                  
	              } 
	              
	              DataSource dataSourceProxy = new TransactionAwareDataSourceProxy(ConexionJndi.getDataSource());
	     		  JdbcTemplate jdbcTemplate = new JdbcTemplate(ConexionJndi.getDataSource()); 
	     		 
		     	        String sqlAreausu = "SELECT * FROM BNMSDSF04_AREAUSUARIA ORDER BY F04_AREAUSUARIA ASC";
	                    List<Tablas> dataAreausu =      jdbcTemplate.query(sqlAreausu, new MapRowMapper("BNMSDSF04_AREAUSUARIA"));    
	     	              
	                    String sqlArea = "SELECT * FROM BNMSDSF05_AREARESPONSABLE ORDER BY F05_AREARESPONSABLE ASC";
	                    List<Tablas> dataListArea =      jdbcTemplate.query(sqlArea, new MapRowMapper("BNMSDSF05_AREARESPONSABLE"));   
	     	            
	                    String sqlProceso = "SELECT * FROM BNMSDSF06_PROCESONEGOCIO ORDER BY F06_PROCESONEGOCIO ASC";
	                    List<Tablas> dataLisProceso =      jdbcTemplate.query(sqlProceso, new MapRowMapper("BNMSDSF06_PROCESONEGOCIO"));   
	     	          
	                    String sqlTipo = "SELECT * FROM BNMSDSF07_TIPOAPLICACION ORDER BY F07_TIPOAPLICACION ASC";
	                    List<Tablas> dataLisTipo =      jdbcTemplate.query(sqlTipo, new MapRowMapper("BNMSDSF07_TIPOAPLICACION"));  
	                    
	                    String sqlTiempo = "SELECT * FROM BNMSDSF08_TIEMPOATENCION ORDER BY F08_TIEMPOATENCION ASC";
	                    List<Tablas> dataLisTiempo =      jdbcTemplate.query(sqlTiempo, new MapRowMapper("BNMSDSF08_TIEMPOATENCION"));   
	     	          
	                    String sqlVersion = "SELECT * FROM BNMSDSF09_VERSION ORDER BY F09_VERSION ASC";
	                    List<Tablas> dataLisVersion =      jdbcTemplate.query(sqlVersion, new MapRowMapper("BNMSDSF09_VERSION"));  
	                    
	                    String sqlLenguaje = "SELECT * FROM BNMSDSF10_LENGUAJE ORDER BY F10_LENGUAJE ASC";
	                    List<Tablas> dataLisLenguaje =      jdbcTemplate.query(sqlLenguaje, new MapRowMapper("BNMSDSF10_LENGUAJE"));
	                    
	                    String sqlBaseDatos = "SELECT * FROM BNMSDSF11_BASEDATOS ORDER BY F11_BASEDATOS ASC";
	                    List<Tablas> dataLisBaseDatos =      jdbcTemplate.query(sqlBaseDatos, new MapRowMapper("BNMSDSF11_BASEDATOS"));
	                    
	                    String sqlResponsableDesa = "SELECT * FROM BNMSDSF12_RESPONSABLEDESA ORDER BY F12_RESPONSABLEDESA ASC";
	                    List<Tablas> dataLisResponsableDesa =      jdbcTemplate.query(sqlResponsableDesa, new MapRowMapper("BNMSDSF12_RESPONSABLEDESA"));
	                    
	                    String sqlFabrica = "SELECT * FROM BNMSDSF13_FABRICA ORDER BY F13_FABRICA ASC";
	                    List<Tablas> dataLisFabrica =      jdbcTemplate.query(sqlFabrica, new MapRowMapper("BNMSDSF13_FABRICA"));
	                    
		     	          request.setAttribute("dataAreausu", dataAreausu);
	     	              request.setAttribute("dataListArea", dataListArea);
	     	              request.setAttribute("dataLisProceso", dataLisProceso);
	     	              request.setAttribute("dataLisTipo", dataLisTipo);
	     	              request.setAttribute("dataLisTiempo", dataLisTiempo);
	     	              request.setAttribute("dataLisVersion", dataLisVersion);
	     	              request.setAttribute("dataLisLenguaje", dataLisLenguaje);
	     	              request.setAttribute("dataLisBaseDatos", dataLisBaseDatos);
	     	              request.setAttribute("dataLisResponsableDesa", dataLisResponsableDesa);
	     	              request.setAttribute("dataLisFabrica", dataLisFabrica);
	     	             
	     	      
	   
		          
	              if ( prog.getCodsistema() == null ){
	            	  
	            	  request.setAttribute("dataList", dataList);
			          String mensaje = "0001";
				      model.addAttribute("mensaje", mensaje);
				      model.addAttribute("muestra", "No se encontraron registros, tiene que registrar en la Opción: REGISTRAR SISTEMA");
	            	  
	              }else{
	            	  
	            
	            	  if(prog.getArquitectura() == null){
	            		  request.setAttribute("dataAreausu", dataAreausu);
	    	              request.setAttribute("dataListArea", dataListArea);
	     	              request.setAttribute("dataLisProceso", dataLisProceso);
	     	              request.setAttribute("dataLisTipo", dataLisTipo);
	     	              request.setAttribute("dataLisTiempo", dataLisTiempo);
	     	              request.setAttribute("dataLisVersion", dataLisVersion);
	     	              request.setAttribute("dataLisLenguaje", dataLisLenguaje);
	     	              request.setAttribute("dataLisBaseDatos", dataLisBaseDatos);
	     	              request.setAttribute("dataLisResponsableDesa", dataLisResponsableDesa);
	     	              request.setAttribute("dataLisFabrica", dataLisFabrica);
	    	              request.setAttribute("prog", prog);
	    		          String mensaje = "0000";
	    			      model.addAttribute("mensaje", mensaje);
	    			      request.setAttribute("dataList", dataList);  
	    			      
	    			      byte[] pdfData = null;
	    			      request.setAttribute("pdfData", pdfData); 
	            		  
	            	  }else{
	            	  
	            	  Blob blob = prog.getArquitectura();
	            	  byte[] pdfData = blob.getBytes(1, (int) blob.length());

	            	  // Pasa los datos del PDF como un atributo al objeto de solicitud
	            	  request.setAttribute("pdfData", pdfData);    
	            	  
	            	  request.setAttribute("dataAreausu", dataAreausu);
    	              request.setAttribute("dataListArea", dataListArea);
     	              request.setAttribute("dataLisProceso", dataLisProceso);
     	              request.setAttribute("dataLisTipo", dataLisTipo);
     	              request.setAttribute("dataLisTiempo", dataLisTiempo);
     	              request.setAttribute("dataLisVersion", dataLisVersion);
     	              request.setAttribute("dataLisLenguaje", dataLisLenguaje);
     	              request.setAttribute("dataLisBaseDatos", dataLisBaseDatos);
     	              request.setAttribute("dataLisResponsableDesa", dataLisResponsableDesa);
     	              request.setAttribute("dataLisFabrica", dataLisFabrica);
    	              request.setAttribute("prog", prog);
    		          String mensaje = "0000";
    			      model.addAttribute("mensaje", mensaje);
    			      request.setAttribute("dataList", dataList); 
	            	  }
			      
			  
			      
	              }
	              
	              
	              
	              
	              

			} catch (Exception e) {
				log3.error(e, "", "ERROR consultaFactibilidad");
				throw new Exception(e);
			}	
		
			
			
		}  //termina else
		
		
		

			path = View.returnJsp(model, "consulta/consultaApli");

		return path;
	}

	
	
	
	@RequestMapping(value = "consultaServ", method = RequestMethod.GET)
	public String consultaServ(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String path = "";

		try {

			log3.debug("consultaFactibilidad", "1");

			// iniciando la ventana
			request.setAttribute("tipoDocConsulta", "01");
			request.setAttribute("numeroDocConsulta", "");
			request.setAttribute("plazoConsulta", "");
			request.setAttribute("cpConsulta", "N");
			request.setAttribute("sdgConsulta", "0");

			path = View.returnJsp(model, "consulta/consultaServ");

		} catch (Exception e) {
			log3.error(e, "", "ERROR consultaFactibilidad");
			throw new Exception(e);
		}

		return path;
	}

	@RequestMapping(value = "parametros", method = RequestMethod.GET)
	public String parametros(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String path = "";

		try {

			log3.debug("consultaFactibilidad", "1");

			// iniciando la ventana
			request.setAttribute("tipoDocConsulta", "01");
			request.setAttribute("numeroDocConsulta", "");
			request.setAttribute("plazoConsulta", "");
			request.setAttribute("cpConsulta", "N");
			request.setAttribute("sdgConsulta", "0");

			path = View.returnJsp(model, "consulta/parametros");

		} catch (Exception e) {
			log3.error(e, "", "ERROR consultaFactibilidad");
			throw new Exception(e);
		}

		return path;
	}

	@RequestMapping(value = "generarReporte", method = RequestMethod.GET)
	public String generarReporte(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String path = "";

		log3.debug("generarReporte", "1");

		// iniciando la ventana
		model.addAttribute("fechaInicial", "");
		model.addAttribute("fechaFinal", "");

		model.addAttribute("existenDatos", "0");
		model.addAttribute("mensajeResultado", "");

		path = View.returnJsp(model, "reporte/generarReporte");

		return path;
	}



	private void writeHeaderLineCro(XSSFSheet sheet) {

		Row headerRow = sheet.createRow(0);

		Cell headerCell = headerRow.createCell(0);
		headerCell.setCellValue("N°");

		headerCell = headerRow.createCell(1);
		headerCell.setCellValue("F07_APELLIDO");

		headerCell = headerRow.createCell(2);
		headerCell.setCellValue("F07_NOMBRE");

		headerCell = headerRow.createCell(3);
		headerCell.setCellValue("F07_TIPO");

		headerCell = headerRow.createCell(4);
		headerCell.setCellValue("F07_NUMERO");

		headerCell = headerRow.createCell(5);
		headerCell.setCellValue("F07_FNAC");

		headerCell = headerRow.createCell(6);
		headerCell.setCellValue("F07_CUENTA");

		headerCell = headerRow.createCell(7);
		headerCell.setCellValue("F07_SITUACION");

		headerCell = headerRow.createCell(8);
		headerCell.setCellValue("F07_LABORAL");

		headerCell = headerRow.createCell(9);
		headerCell.setCellValue("F07_CELULAR");

		headerCell = headerRow.createCell(10);
		headerCell.setCellValue("F07_CORREO");

		headerCell = headerRow.createCell(11);
		headerCell.setCellValue("F07_IMPSOL");

		headerCell = headerRow.createCell(12);
		headerCell.setCellValue("F07_IMPMAX");

		headerCell = headerRow.createCell(13);
		headerCell.setCellValue("F07_PLAZO");

		headerCell = headerRow.createCell(14);
		headerCell.setCellValue("F07_SCUOTA");

		headerCell = headerRow.createCell(15);
		headerCell.setCellValue("F07_IMPDES");

		headerCell = headerRow.createCell(16);
		headerCell.setCellValue("F07_IP");

		headerCell = headerRow.createCell(17);
		headerCell.setCellValue("F07_FECHA");

		headerCell = headerRow.createCell(18);
		headerCell.setCellValue("F07_FECHA_CREA");

		headerCell = headerRow.createCell(19);
		headerCell.setCellValue("F07_USUCONSULTA");

		headerCell = headerRow.createCell(20);
		headerCell.setCellValue("F07_SDG");

		headerCell = headerRow.createCell(21);
		headerCell.setCellValue("F07_MSCUOTA");

		headerCell = headerRow.createCell(22);
		headerCell.setCellValue("F07_MTEA");

		headerCell = headerRow.createCell(23);
		headerCell.setCellValue("F07_MTCEA");
	}


	@RequestMapping(value = "consultaEstadoSinVaucher", method = RequestMethod.GET)
	public String consultaEstadoSinVaucher(ModelMap model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = "";

		try {
			// log3.debug("consultaEstadoSinVaucher SESSION", "1");

			// HttpSession sesion = request.getSession(false);
			// log3.debug(sesion == null ?
			// "consultaEstadoSinVaucher SESSION IS NULL" : "SESSION:" +
			// sesion.toString(), "1");

			// log3.debug("consultaEstadoSinVaucher | Session-CreationTime:" +
			// new Date(sesion.getCreationTime()), "1");
			// log3.debug("consultaEstadoSinVaucher | MaxInactiveInterval(seg):"
			// + sesion.getMaxInactiveInterval(), "1");

			// DatosSesion datosSesion = Util.getIdUsuario(request);
			// DatosSesion datosSesion = (DatosSesion)
			// sesion.getAttribute("datosSesion");

			// log3.debug(datosSesion == null ? "datosSesion IS NULL" :
			// datosSesion.toString(), "1");
			log3.debug("consultaEstadoSinVaucher", "1");

			// iniciando la ventana
			request.setAttribute("cboCuenta", "");
			request.setAttribute("nroDni", "");
			request.setAttribute("nroCuenta", "");
			request.setAttribute("mesInicial", "");
			request.setAttribute("annoInicial", "");
			request.setAttribute("mesFinal", "");
			request.setAttribute("annoFinal", "");

			request.setAttribute("existenDatos", "0");
			request.setAttribute("mensajeResultado", "");

			path = View.returnJsp(model, "consulta/consultaEstadoSinVaucher");

		} catch (Exception e) {
			log3.error(e, "", "ERROR consultaEstadoSinVaucher");
			throw new Exception(e);
		}

		return path;
	}

	@RequestMapping(value = "consultaEstadoConVaucher", method = RequestMethod.GET)
	public String consultaEstadoConVaucher(ModelMap model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = "";

		try {

			log3.debug("consultaEstadoSinVaucher", "1");

			// iniciando la ventana
			request.setAttribute("rdVoucher", "01");
			request.setAttribute("nroPedido", "");
			request.setAttribute("nroCuenta", "");

			request.setAttribute("existenDatos", "0");
			request.setAttribute("mensajeResultado", "");

			path = View.returnJsp(model, "consulta/consultaEstadoConVaucher");

		} catch (Exception e) {
			log3.error(e, "", "ERROR consultaEstadoConVaucher");
			throw new Exception(e);
		}

		return path;
	}

	@RequestMapping(value = "consultaLogCuentas", method = RequestMethod.GET)
	public String consultaLogCuentas(ModelMap model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = "";

		// HttpSession sesion = request.getSession(false);
		// DatosSesion datosSesion = Util.getIdUsuario(request);

		log3.debug("consultaLogCuentas", "1");
		// log3.debug(sesion == null ? "consultaLogCuentas SESSION IS NULL" :
		// "SESSION:" + sesion.toString(), "1");

		// log3.debug("consultaEstadoSinVaucher | Session-CreationTime:" + new
		// Date(sesion.getCreationTime()), "1");
		// log3.debug("consultaEstadoSinVaucher | MaxInactiveInterval(seg):" +
		// sesion.getMaxInactiveInterval(), "1");

		// log3.debug(datosSesion == null ?
		// "consultaLogCuentas datosSesion IS NULL" : datosSesion.toString(),
		// "1");
		// log3.debug("consultaLogCuentas", "1");

		// iniciando la ventana
		model.addAttribute("nroCuenta", "");
		model.addAttribute("fechaInicial", "");
		model.addAttribute("fechaFinal", "");
		model.addAttribute("ordenamiento", "02");

		model.addAttribute("existenDatos", "0");
		model.addAttribute("mensajeResultado", "");

		path = View.returnJsp(model, "logconsulta/consultaLogCuentas");

		return path;
	}

	private String getMesLetras(String mesInicial) {

		String mesLetras = "";

		try {

			switch (Integer.parseInt(mesInicial)) {
			case 1:
				mesLetras = "Enero";
				break;

			case 2:
				mesLetras = "Febrero";
				break;

			case 3:
				mesLetras = "Marzo";
				break;

			case 4:
				mesLetras = "Abril";
				break;

			case 5:
				mesLetras = "Mayo";
				break;

			case 6:
				mesLetras = "Junio";
				break;

			case 7:
				mesLetras = "Julio";
				break;

			case 8:
				mesLetras = "Agosto";
				break;

			case 9:
				mesLetras = "Septiembre";
				break;

			case 10:
				mesLetras = "Octubre";
				break;

			case 11:
				mesLetras = "Noviembre";
				break;

			case 12:
				mesLetras = "Diciembre";
				break;

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			mesLetras = "";
		}

		return mesLetras;
	}




	// AGREGANDO CARGA XLS Y CATULIZAR PARAMETRO
		@RequestMapping(value = "cargaxls", method = RequestMethod.GET)
		public String pageCargaXls(ModelMap model, HttpServletRequest request,
				HttpServletResponse response) {
			String path = View.returnJsp(model, "parametro/CargaArchivo");
			return path;
		}

		@RequestMapping(value = "/cargaxlspost", method = RequestMethod.POST)
		public String CargaXls(@RequestParam("archivo") MultipartFile archivo,
				@RequestParam("opcion") String opcion,

				ModelMap model, HttpServletRequest request,
				HttpServletResponse response) {

			String path = View.returnJsp(model, "parametro/CargaArchivo");

			if (archivo.isEmpty()) {
				model.addAttribute("mensaje",
						"Por favor selecciona un archivo para subir");
				return path;
			}
			
			Integer opcionInt = null;
			try {
				opcionInt = Integer.parseInt(opcion);
				if (opcionInt == 0) {
					model.addAttribute("mensaje",
							"Por favor selecciona una opcion");
					return path;
				}
			} catch (Exception e) {
				model.addAttribute("error",
						"Problemas con los datos de la opcion");
				return path;
			}

			try {
				InputStream inputStream = archivo.getInputStream();

				// Realiza la carga del archivo excel y devuelve 4 claves en un map
				// COD,MJS,RC
			
				Map<String, String> result = parametrosDAO.cargarArchivoParametro(
						opcionInt, inputStream,obtenerUsuarioAutenticado());

				inputStream.close();
				if (result.get("COD").equals("00")) {
					model.addAttribute("mensaje", "Archivo subido exitosamente: "
							+ archivo.getOriginalFilename());
				} else {
					model.addAttribute("error", "Problema al cargar archivo");
				}
			
				model.addAttribute("RC", result.get("RC"));

			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("error", "Fallo al subir el archivo ");
			} catch (DuplicateKeyException dke) {
				dke.printStackTrace();
				model.addAttribute("error", "Clave duplicada en la base de datos ");
			} catch (DataIntegrityViolationException dive) {
				dive.printStackTrace();
				model.addAttribute("error", "Violacion de integridad de datos ");
			} catch (EmptyResultDataAccessException erdae) {
				erdae.printStackTrace();
				model.addAttribute("error", "No se encontro el resultado esperado ");
			} catch (DataAccessException dae) {
				dae.printStackTrace();
				model.addAttribute("error",
						"Fallo en la operacion de base de datos ");
			} catch (InvalidColumnCountException dae) {
				dae.printStackTrace();
				model.addAttribute("error", dae.getMessage());
			}

			return path;
		}

		@RequestMapping(value = "/actuparam", method = RequestMethod.GET)
		public String pageActuParam(ModelMap model, HttpServletRequest request,
				HttpServletResponse response) {
			String path = View.returnJsp(model, "parametro/ActuParam");
			return path;
		}

		@RequestMapping(value = "/actuparamconsul" )
		public String pageActuParamConsult(
				@RequestParam(value = "opcion", defaultValue = "0") Integer opcion,
				@RequestParam(value = "item", defaultValue = "0")Integer item,
				ModelMap model, HttpServletRequest request,
				HttpServletResponse response) {
			String path = View.returnJsp(model, "parametro/ActuParam");
		 
			
			
			if (opcion == 0 ) {
				model.addAttribute("mensaje",
						"Por favor selecciona una opcion valida a consultar");

				return path;
			}
			 model.addAttribute("selectedOption", opcion);
			String tabla = Constante.getTableNamesMap().get(opcion);
			if (tabla.equals(Constante.TABLE_BNMSDSF03_APLICACIONES)) {
				List<Sistema> sistemas = parametrosDAO.getAllParametros(tabla,item,obtenerUsuarioAutenticado());
				model.addAttribute("sistemas",
						sistemas);
				return path;
			}else {
				List<Tablas> sistemas = parametrosDAO.getAllParametros(tabla,item,obtenerUsuarioAutenticado());
				model.addAttribute("tablas",sistemas);
				return path;
			}
			
			 
			 
	 
		}
		
		@RequestMapping(value = "/param" )
		public String   Param (
				@RequestParam(value = "id", defaultValue = "0") String id,
				@RequestParam(value = "tipoTabla", defaultValue = "1" ) Integer tipoTabla,
				@RequestParam(value = "codigo", defaultValue = "NN") String codigo,
				@RequestParam(value = "nombre", defaultValue = "NN") String nombre,
				@RequestParam(value = "descripcion", defaultValue = "NN") String descripcion,
				@RequestParam(value = "valor", defaultValue = "NN") String valor,
				ModelMap model, HttpServletRequest request,
				HttpServletResponse response) {
			String path = View.returnJsp(model, "parametro/ActuParam");
			
			
			
			  if ("NN".equals(codigo) && "NN".equals(nombre) && "NN".equals(descripcion) && "NN".equals(valor)) {
	 	            return path;
		        }
			String tableName = Constante.getTableNamesMap().get(tipoTabla);
			try {
				
			
			if (id != "0") {
				Integer opcionInt = null;
				try {
					opcionInt = Integer.parseInt(id);
				} catch (Exception e) {
					model.addAttribute("error",
							"Problemas con los datos");
					return path;
				}	 
				
				if (tipoTabla == 1) {
					Sistema sistema = new Sistema(opcionInt, codigo, nombre, descripcion);
					parametrosDAO.actulizarParametro(tableName, sistema,obtenerUsuarioAutenticado());
					model.addAttribute("mensaje","Exito modificando "+codigo);
				
				}else{
					Tablas tabla = new Tablas(opcionInt, valor);
					parametrosDAO.actulizarParametro(tableName, tabla,obtenerUsuarioAutenticado());
					model.addAttribute("mensaje","Exito modificando "+valor);
					
					
				}
			}else{
				if (tipoTabla == 1) {
					Sistema sistema = new Sistema(0, codigo, nombre, descripcion);
					parametrosDAO.nuevoParametro(tableName, sistema,obtenerUsuarioAutenticado());
					model.addAttribute("mensaje","Exito registrando "+codigo);
					
				}else{
					Tablas tabla = new Tablas(0, valor);
					parametrosDAO.nuevoParametro(tableName, tabla,obtenerUsuarioAutenticado());
					model.addAttribute("mensaje","Exito registrando "+valor);
					
				}
				
			}
			
			} catch (Exception e) {
				model.addAttribute("error","Error en la actualizacion del parametro "+e.getMessage());
			}
			
			
			return path;
			

			
	 
		}

		
	 
			@RequestMapping(value = "/data ", method = RequestMethod.GET)
			@ResponseBody
		    public List<Object> getData(@RequestParam(value="tipo",defaultValue="0")Integer tipo) {
				if(tipo == 0) {
					 List<Object> sistemas = parametrosDAO.getAllParametros(Constante.TABLE_BNMSDSF03_APLICACIONES);
				      return sistemas;
				}
				String tabla = Constante.getTableNamesMap().get(tipo);
				List<Object> sistemas = parametrosDAO.getAllParametros(tabla);
			      return sistemas;
		       
		    }
		
		
		// AGREGANDO CARGA XLS Y CATULIZAR PARAMETRO


	
			// LOG DE AUDITORIA
			@RequestMapping(value = "logaudi", method = RequestMethod.GET)
			public String pageCargaAudi(ModelMap model, HttpServletRequest request,
					HttpServletResponse response) {
				String path = View.returnJsp(model, "auditoria/LogAuditoria");
				List<Auditoria> registros = new ArrayList<Auditoria>();

				model.addAttribute("logs",registros );
				request.getSession().setAttribute("logs", registros);
				return path;
			}
			
			@RequestMapping(value = "buscarlog" )
			public String pageBuscarAudi(
					@RequestParam(value = "opcion", defaultValue = "0") Integer opcion,
		 			@RequestParam(value = "forFechaInicio", required = false) String forFechaInicio,
					@RequestParam(value = "forFechaFin", required = false) String forFechaFin,
					@RequestParam(value = "forDiaDate", required = false) String forDiaDate,
					ModelMap model, HttpServletRequest request,
					HttpServletResponse response) {
				
				String path = View.returnJsp(model, "auditoria/LogAuditoria");
				
				
				if (opcion == 0 ) {
					model.addAttribute("mensaje",
							"Por favor selecciona una opcion valida a consultar");

					return path;
				}
				
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

				try {
					List<Auditoria> registros = new ArrayList<Auditoria>();
					if (opcion == 1 ) {
					 
						java.sql.Date fechaInicio = new java.sql.Date(formatter.parse(forFechaInicio).getTime());
						java.sql.Date fechaFin = new java.sql.Date(formatter.parse(forFechaFin).getTime());
				            
						registros  = parametrosDAO.buscarRegistros(fechaInicio,fechaFin);
					}
					
					if (opcion == 2 ) {
						java.sql.Date fecha = new java.sql.Date(formatter.parse(forDiaDate).getTime());
		 				registros  = parametrosDAO.buscarRegistros(fecha);
					}
					
					if (opcion == 4 ) {
						registros  = parametrosDAO.buscarRegistros();
					}
					model.addAttribute("logs",registros );
					request.getSession().setAttribute("logs", registros);
					if (registros.isEmpty()) {
						model.addAttribute("mensaje",
								"No hay resultados para su consulta.");
					}
				} catch (Exception e) {
					model.addAttribute("logs",
							"Error en el aplicativo");
				
					 
				}
				
			 
				
				
				return path;
			}	
			
			
			 @RequestMapping(value = "/logaudi/exportExcel", method = RequestMethod.GET)
			    public void exportExcel(HttpServletResponse response,HttpServletRequest request) throws IOException {
				
				 response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			      response.setHeader("Content-Disposition", "attachment; filename=auditoria.xlsx");

			  
			        OutputStream outputStream = response.getOutputStream();

			        try {
			            // Crear un nuevo libro de trabajo
			            XSSFWorkbook workbook = new XSSFWorkbook();

			            // Crear una nueva hoja de clculo
			            XSSFSheet sheet = workbook.createSheet("Auditoria");

			            // Crear una nueva fila
			            XSSFRow row = sheet.createRow(0);

			            // Crear una nueva celda en la fila
			            XSSFCell cell = row.createCell(0);
			            // Asignar un valor a la celda
			            cell.setCellValue("Fecha");
			            cell = row.createCell(1);
			            cell.setCellValue("Hora");
			            cell = row.createCell(2);
			            cell.setCellValue("Usuario");
			            cell = row.createCell(3);
			            cell.setCellValue("Accion");
			            cell = row.createCell(4);
			            cell.setCellValue("Aplicativo");
			            // Obtener los datos de la sesi�n
				        List<Auditoria> registros = (List<Auditoria>) request.getSession().getAttribute("logs");
				        int numcell = 1;
				        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			            for (Auditoria registro : registros) {
			            	XSSFRow roww = sheet.createRow(numcell++);
			            	roww.createCell(0).setCellValue(dateFormat.format(registro.getFecha()).toString());
			            	roww.createCell(1).setCellValue(registro.getHora());
			            	roww.createCell(2).setCellValue(registro.getUsuario());
			            	roww.createCell(3).setCellValue(registro.getAccion());
			            	roww.createCell(4).setCellValue(registro.getAplicacion());
						}
			            
			            
			            
			            // Escribir el archivo Excel en el OutputStream
			            workbook.write(outputStream);
			            outputStream.flush();
			            workbook.close();
			        } catch (IOException e) {
			            e.printStackTrace();
			        } finally {
			            outputStream.close();
			        }
			    }
			
			
			
			
			// LOG DE AUDITORIA
			
			
			public static CustomUser obtenerUsuarioAutenticado() {
		        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		        if (authentication != null && authentication.getPrincipal() instanceof CustomUser) {
		            return (CustomUser) authentication.getPrincipal();
		        }
		        return null;
		    }
}

class MapRowMapper implements RowMapper<Tablas> {
	String tabla ;
	MapRowMapper(String table){
	this.tabla = table;
	}
    @Override
    public Tablas mapRow(ResultSet rs, int rowNum) throws SQLException {
         Tablas tablas = new Tablas();
        List<String> colums = Constante.getTableColumnsMap().get(tabla);
        tablas.setId(rs.getInt("ID"));
        for (String colum : colums) {
        	tablas.setValor(rs.getString(colum));
		}
         
         

        return tablas;
    }
}

class MapRowMapperAplicacion implements RowMapper<Tablas> {
	String tabla ;
	MapRowMapperAplicacion(String table){
	this.tabla = table;
	}
    @Override
    public Tablas mapRow(ResultSet rs, int rowNum) throws SQLException {
         Tablas tablas = new Tablas();
        List<String> colums = Constante.getTableColumnsMap().get(tabla);
  
     tablas.setValor(rs.getString("F03_CODIGO"));
     for (String colum : colums) {
     	tablas.setValor(rs.getString(colum));
		}
         
         

        return tablas;
    }
}
