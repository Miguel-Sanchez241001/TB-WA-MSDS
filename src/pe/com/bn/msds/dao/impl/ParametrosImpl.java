package pe.com.bn.msds.dao.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import pe.com.bn.msds.common.Constante;
import pe.com.bn.msds.config.CustomUser;
import pe.com.bn.msds.dao.inf.ParametrosDAO;
import pe.com.bn.msds.dao.pool.ConexionJndi;
import pe.com.bn.msds.model.Auditoria;
import pe.com.bn.msds.model.InvalidColumnCountException;
import pe.com.bn.msds.model.Programa;
import pe.com.bn.msds.model.Sistema;
import pe.com.bn.msds.model.Tablas;

@Repository
public class ParametrosImpl implements ParametrosDAO {

	 private JdbcTemplate jdbcTemplate;

	    
	   @PostConstruct
	    public void init() {
	        // Aqu� puedes agregar la l�gica que desees ejecutar al inicializar el bean
	        System.out.println("Inicializando bean ParametrosImpl");
	        try {
	            // Envolver el DataSource con TransactionAwareDataSourceProxy
	            DataSource dataSourceProxy = new TransactionAwareDataSourceProxy(ConexionJndi.getDataSource());
				this.jdbcTemplate = new JdbcTemplate(dataSourceProxy);
			} catch (NamingException e) {	 
				e.printStackTrace();
			}
	    }

	@Override
	public Map<String, String> cargarArchivoParametro(Integer tipo, InputStream archivo,CustomUser usuario) throws InvalidColumnCountException {
		 
		
		 Map<String, String> resultado = new HashMap<String, String>();
		 String TablaName = Constante.getTableNamesMap().get(tipo);
		 int ColumnasTabla  = Constante.getTableColumnsMap().get(TablaName).size();
		  try {
			  
			  limpiarTabla(TablaName);
			  
        // Crear un objeto Workbook a partir del archivo
        Workbook workbook = new XSSFWorkbook(archivo);

        // Obtener la primera hoja del libro de trabajo
        Sheet hoja = workbook.getSheetAt(0);
        
        int cellCountComplete = 0;
        
       
        // Recorrer las filas de la hoja
        Iterator<Row> filaIterator = hoja.iterator();
        if (filaIterator.hasNext()) {
        	Row Fila = filaIterator.next();
        	 if (Fila != null) {
                 int cellCount = Fila.getPhysicalNumberOfCells(); // Get the number of non-empty cells
                 if (cellCount != ColumnasTabla) {
                     throw new InvalidColumnCountException("Verique el formato del documento se esperan  "+
                 ColumnasTabla + " columnas y se encontraron "+cellCount);
                 }
             } else {
                 throw new InvalidColumnCountException("Verique el documento");
             }
        }
       
        Sistema sistema = null;
        
        while (filaIterator.hasNext()) {
         
        	sistema = new Sistema();
            Row fila = filaIterator.next();

            // Recorrer las celdas de la fila
            Iterator<Cell> celdaIterator = fila.cellIterator();

            // Extraer los valores de las 3 primeras columnas
            int columna = 0;
            while (celdaIterator.hasNext() && columna < ColumnasTabla) {
                Cell celda = celdaIterator.next();

                // Obtener el valor de la celda
                String valor = obtenerValorCelda(celda);

               
                // Imprimir el valor si no est� vac�o
                if (!valor.isEmpty()) {
                	 if (ColumnasTabla != 3) { 
                		 Tablas tbl = new Tablas() ;
                		 tbl.setValor(valor);
                     	 resultado =  nuevoParametro(TablaName,tbl, usuario);
                     	 cellCountComplete++;
     				}else {
     					 if (columna == 0) {
                         	sistema.setcod(valor);
     					}
                         if (columna == 1) {
                         	sistema.setNombre( valor);
     					}
                         if (columna == 2) {
                         	sistema.setDescripcion( valor);
                         	 resultado =  nuevoParametro(TablaName,sistema,usuario);
                         	 cellCountComplete++;
     					}
     				}
                	 
                   
                   
                    
                } else {
                    break;
                }

                columna++;
            }

           
        }

   
        resultado.put("RC", String.valueOf(cellCountComplete));
        // Cerrar el archivo
        	workbook.close();
			archivo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	@Override
	public Map<String, String> actulizarParametro(String tabla,  Object param,CustomUser usuario) {

		 
		int filasAfectadas = 0;
		Map<String, String> respuesta = new HashMap<String, String>();
		List<String> columnas  = Constante.getTableColumnsMap().get(tabla) ;
		String sql =  armarUpdateSql(tabla,columnas);
		String valorTem = "";
		if (tabla.equalsIgnoreCase(Constante.TABLE_BNMSDSF03_APLICACIONES)) {
			 
			Sistema sistema = (Sistema)param;
			 
			filasAfectadas = jdbcTemplate.update(sql, sistema.getcod(),
					sistema.getNombre(), sistema.getDescripcion(),
					sistema.getId());
			valorTem = sistema.getcod();
		}else {
			Tablas tablatbl = (Tablas)param;
			filasAfectadas = jdbcTemplate.update(sql,tablatbl.getValor(), tablatbl.getId());
			valorTem = tablatbl.getValor();
		}
		if (filasAfectadas == 1) {
			registrarLogAuditoria(Constante.CONST_ACCION_LOG_ACTUALIZACION,usuario,tabla,valorTem);
			respuesta.put("COD", "00");
			respuesta.put("MJS", "Exito operacion");
		}

		return respuesta;
	}

	@Override
	public Map<String, String> nuevoParametro(String tabla,
			Object param,CustomUser usuario) {

	 
		int filasAfectadas = 0;

		Map<String, String> respuesta = new HashMap<String, String>();
		List<String> columnas  = Constante.getTableColumnsMap().get(tabla) ;
		String sql = armarInsertSql(tabla,columnas);
		String valorTem = "";
		if (tabla.equalsIgnoreCase(Constante.TABLE_BNMSDSF03_APLICACIONES)) {
			 
			Sistema sistema = (Sistema)param;
			
			filasAfectadas = jdbcTemplate.update(sql, sistema.getcod(),
					sistema.getNombre(), sistema.getDescripcion());
			valorTem =  sistema.getcod();
		}else {
			Tablas valor = (Tablas)param;
			filasAfectadas = jdbcTemplate.update(sql, valor.getValor());
			valorTem = valor.getValor();
		}
		
		if (filasAfectadas == 1) {
			registrarLogAuditoria(Constante.CONST_ACCION_LOG_INSERT,usuario,tabla,valorTem);
			respuesta.put("COD", "00");
			
			respuesta.put("MJS", "Exito operacion");
		}else{
			respuesta.put("COD", "99");
			respuesta.put("MJS", "Error en la oeracion");
		}

		return respuesta;
	}

	 private String armarInsertSql(String tabla, List<String> columnas) {
		 String sql = "INSERT INTO " + tabla + " (";
 		 
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
	private String armarUpdateSql(String tabla, List<String> columnas) {
		    StringBuilder sqlBuilder = new StringBuilder();
		    sqlBuilder.append("UPDATE ").append(tabla).append(" SET ");

		    // Añadir columnas para actualizar
		    for (int i = 0; i < columnas.size(); i++) {
		        sqlBuilder.append(columnas.get(i)).append(" = ?");
		        if (i < columnas.size() - 1) {
		            sqlBuilder.append(", ");
		        }
		    }

		    sqlBuilder.append(" WHERE ID = ?");
		    return sqlBuilder.toString();
		}

	// M�todo para obtener el valor de una celda
    private static String obtenerValorCelda(Cell celda) {
        switch (celda.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return celda.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf(celda.getNumericCellValue());
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(celda.getBooleanCellValue());
            default:
                return "";
        }
    }

    public void limpiarTabla(String nombreTabla) {
        // Opci�n 1: Utilizando DELETE
        String sqlDelete = "DELETE FROM " + nombreTabla;
        jdbcTemplate.execute(sqlDelete);
        System.out.println("Limpiando tabla "+nombreTabla);
 
    }

	@Override
	public <T> List<T> getAllParametros(String tabla) {
		  if (tabla.equals(Constante.TABLE_BNMSDSF03_APLICACIONES)) {
		        String sql = "SELECT * FROM "+tabla+" ORDER BY "+Constante.getTableColumnsMap().get(0);
	            return (List<T>) jdbcTemplate.query(sql, new SistemaRowMapper());
	        } else {
	            String sql =  "SELECT * FROM "+tabla;
	            return (List<T>) jdbcTemplate.query(sql, new MapRowMapper(tabla));
	        }
	}

	@Override
	public <T> List<T> getAllParametros(String tabla, Integer id,CustomUser usuario) {
		 String sql = "SELECT * FROM "+tabla+ " WHERE ID = "+id;
		 if (tabla.equals(Constante.TABLE_BNMSDSF03_APLICACIONES)) {
			 return (List<T>) jdbcTemplate.query(sql, new SistemaRowMapper());
		 }else {
			 return (List<T>) jdbcTemplate.query(sql, new MapRowMapper(tabla));
		 }
        
	}
	@Override
    public void registrarLogAuditoria(String accion,CustomUser usuario,String tabla,String valor){
    	String operacion = accion
    			.replace(Constante.CONST_ACCION_LOG_USER, usuario.getNombre())
    			.replace(Constante.CONST_ACCION_LOG_TABLA, tabla)
    			.replace(Constante.CONST_ACCION_LOG_VALOR, valor);
    	List<String> columnas  = Constante.getTableColumnsMap().get(Constante.TABLE_BNMSDSF14_AUDITORIA) ;
    	Auditoria auditoria = new Auditoria();
    	auditoria.setFecha( new Date(System.currentTimeMillis()));
    	auditoria.setHora(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
    	auditoria.setUsuario(usuario.getNombre());
    	auditoria.setAccion(operacion);
    	auditoria.setAplicacion(tabla);
    	String sql = armarInsertSqlSin(Constante.TABLE_BNMSDSF14_AUDITORIA,columnas,3);
    	
    	jdbcTemplate.update(sql, auditoria.getFecha(),
    			auditoria.getHora(),
    			auditoria.getUsuario(),
    			auditoria.getAccion(),
    			valor
    			);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }

	@Override
	public List<Auditoria> buscarRegistros() {
		 String sql = "SELECT * FROM BNMSDSF14_AUDITORIA ORDER BY F14_FECHA ASC"; 
		 return  jdbcTemplate.query(sql, new LogRowMapper());
	 
	}

	@Override
	public List<Auditoria> buscarRegistros( Date date) {
		 String sql = " SELECT * FROM BNMSDSF14_AUDITORIA  WHERE TRUNC(F14_FECHA) = ? ORDER BY F14_FECHA ASC"; 
		 return  jdbcTemplate.query(sql,new Object[] { date }, new LogRowMapper());
	}

	@Override
	public List<Auditoria> buscarRegistros( Date date,
			 Date dateFin) {
		 String sql = "SELECT * FROM BNMSDSF14_AUDITORIA  WHERE TRUNC(F14_FECHA) BETWEEN ? AND ? ORDER BY F14_FECHA ASC"; 
		 return  jdbcTemplate.query(sql,new Object[] { date,dateFin }, new LogRowMapper());
	}

	@Override
	public List<Programa> buscarProgramas(Integer opcion,
			String forFechaInicio, String forFechaFin, String forDiaDate) {
		   String sql = "SELECT " +
		            "ID, " +
		            "F01_CODSISTEMA, " +
		            "F01_NOMSISTEMA, " +
		            "F01_DESSISTEMA, " +
		            "F01_NIVEL, " +
		            "F01_AREAUSUARIA, " +
		            "F01_AREARESPONSABLE, " +
		            "F01_AREAPROCESO, " +
		            "F01_AREATIPO, " +
		            "F01_PLATAFORMA, " +
		            "F01_RTO, " +
		            "F01_MTDP, " +
		            "F01_RPO, " +
		            "F01_MTDL, " +
		            "F01_MBCO, " +
		            "F01_VERSION, " +
		            "F01_VERSIONDESA, " +
		            "F01_URLDESARROLLO, " +
		            "F01_URLCERTIFICACION, " +
		            "F01_URLPRODUCCION, " +
		            "F01_URLGIT, " +
		            "F01_LENGUAJE, " +
		            "F01_BASEDATOS, " +
		            "F01_PERSONARESP, " +
		            "F01_DESAREALIZADO, " +
		            "F01_PLATAFORMACOM, " +
		            "F01_REQUISITOS, " +
		            "F01_FECHA, " +
		            "F01_COMENTARIOS " +
		            "FROM BNMSDSF01_PROGRAMAS";		 
		   switch (opcion) {
           case 1:
        	   
               sql += " WHERE TO_DATE(F01_FECHA, '"+Constante.CONST_FECHA_FORMAT+"') BETWEEN TO_DATE(?, '"+Constante.CONST_FECHA_FORMAT+"') AND TO_DATE(?, '"+Constante.CONST_FECHA_FORMAT+"')";
               String startDate =  convertDate(forFechaInicio);
               String endDate =  convertDate(forFechaFin);
               return jdbcTemplate.query(sql, new ProgramaRowMapper(), startDate, endDate);
            case 2:
               sql += " WHERE TO_DATE(F01_FECHA, '"+Constante.CONST_FECHA_FORMAT+"') = TO_DATE(?, '"+Constante.CONST_FECHA_FORMAT+"')";
               String specificDate = convertDate(forDiaDate);
               return jdbcTemplate.query(sql, new ProgramaRowMapper(), specificDate);    
               case 3:
               // No se necesita ninguna modificación a la consulta SQL
               return jdbcTemplate.query(sql, new ProgramaRowMapper());
           default:
               throw new IllegalArgumentException("Opción no válida: " + opcion);
       } 	}

	private String convertDate(String dateStr) {
		 SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat targetFormat = new SimpleDateFormat(Constante.CONST_FECHA_FORMAT);
	        java.util.Date date = null;
	        try {
	            date = originalFormat.parse(dateStr);
	        } catch (ParseException e) {
	            e.printStackTrace(); // Manejar la excepción según sea necesario
	        }
	        return targetFormat.format(date);	
	}
}
class ProgramaRowMapper implements RowMapper<Programa> {
    @Override
    public Programa mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Programa programa = new Programa();
        programa.setId(rs.getLong("ID"));
        programa.setF01Codsistema(getValueOrDefault(rs, "F01_CODSISTEMA", Constante.CONST_VALOR_NULLO));
        programa.setF01Nomsistema(getValueOrDefault(rs, "F01_NOMSISTEMA", Constante.CONST_VALOR_NULLO));
        programa.setF01Dessistema(getValueOrDefault(rs, "F01_DESSISTEMA", Constante.CONST_VALOR_NULLO));
        programa.setF01Nivel(getValueOrDefault(rs, "F01_NIVEL", Constante.CONST_VALOR_NULLO));
        programa.setF01Areausuaria(getValueOrDefault(rs, "F01_AREAUSUARIA", Constante.CONST_VALOR_NULLO));
        programa.setF01Arearesponsable(getValueOrDefault(rs, "F01_AREARESPONSABLE", Constante.CONST_VALOR_NULLO));
        programa.setF01Areaproceso(getValueOrDefault(rs, "F01_AREAPROCESO", Constante.CONST_VALOR_NULLO));
        programa.setF01Areatipo(getValueOrDefault(rs, "F01_AREATIPO", Constante.CONST_VALOR_NULLO));
        programa.setF01Plataforma(getValueOrDefault(rs, "F01_PLATAFORMA", Constante.CONST_VALOR_NULLO));
        programa.setF01Rto(getValueOrDefault(rs, "F01_RTO", Constante.CONST_VALOR_NULLO));
        programa.setF01Mtdp(getValueOrDefault(rs, "F01_MTDP", Constante.CONST_VALOR_NULLO));
        programa.setF01Rpo(getValueOrDefault(rs, "F01_RPO", Constante.CONST_VALOR_NULLO));
        programa.setF01Mtdl(getValueOrDefault(rs, "F01_MTDL", Constante.CONST_VALOR_NULLO));
        programa.setF01Mbco(getValueOrDefault(rs, "F01_MBCO", Constante.CONST_VALOR_NULLO));
        programa.setF01Version(getValueOrDefault(rs, "F01_VERSION", Constante.CONST_VALOR_NULLO));
        programa.setF01Versiondesa(getValueOrDefault(rs, "F01_VERSIONDESA", Constante.CONST_VALOR_NULLO));
        programa.setF01Urldesarrollo(getValueOrDefault(rs, "F01_URLDESARROLLO", Constante.CONST_VALOR_NULLO));
        programa.setF01Urlcertificacion(getValueOrDefault(rs, "F01_URLCERTIFICACION", Constante.CONST_VALOR_NULLO));
        programa.setF01Urlproduccion(getValueOrDefault(rs, "F01_URLPRODUCCION", Constante.CONST_VALOR_NULLO));
        programa.setF01Urlgit(getValueOrDefault(rs, "F01_URLGIT", Constante.CONST_VALOR_NULLO));
        programa.setF01Lenguaje(getValueOrDefault(rs, "F01_LENGUAJE", Constante.CONST_VALOR_NULLO));
        programa.setF01Basedatos(getValueOrDefault(rs, "F01_BASEDATOS", Constante.CONST_VALOR_NULLO));
        programa.setF01Personaresp(getValueOrDefault(rs, "F01_PERSONARESP", Constante.CONST_VALOR_NULLO));
        programa.setF01Desarealizado(getValueOrDefault(rs, "F01_DESAREALIZADO", Constante.CONST_VALOR_NULLO));
        programa.setF01Plataformacom(getValueOrDefault(rs, "F01_PLATAFORMACOM", Constante.CONST_VALOR_NULLO));
        programa.setF01Requisitos(getValueOrDefault(rs, "F01_REQUISITOS", Constante.CONST_VALOR_NULLO));
        programa.setF01Fecha(getValueOrDefault(rs, "F01_FECHA", Constante.CONST_VALOR_NULLO));
        programa.setF01Comentarios(getValueOrDefault(rs, "F01_COMENTARIOS", Constante.CONST_VALOR_NULLO));
        return programa;
    }

	private String getValueOrDefault(ResultSet rs, String columnName, String defaultValue) throws SQLException {
		  String value = rs.getString(columnName);
          return value != null ? value : defaultValue;
	}
}

class SistemaRowMapper implements RowMapper<Sistema> {
    @Override
    public Sistema mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sistema sistema = new Sistema();
        sistema.setId(rs.getInt("ID"));
        sistema.setcod(rs.getString(Constante.BNMSDSF03_F03_CODIGO));
        sistema.setNombre(rs.getString(Constante.BNMSDSF03_F03_NOMBRE));
        sistema.setDescripcion(rs.getString(Constante.BNMSDSF03_F03_DESCRIPCION));
        return sistema;
    }
}
class LogRowMapper implements RowMapper<Auditoria> {
    @Override
    public Auditoria mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Auditoria auditoria = new Auditoria();
    	auditoria.setId((long) rs.getInt("ID"));
    	auditoria.setFecha(rs.getDate(Constante.BNMSDSF14_F14_FECHA));
    	auditoria.setHora(rs.getString(Constante.BNMSDSF14_F14_HORA));
    	auditoria.setUsuario(rs.getString(Constante.BNMSDSF14_F14_USUARIO));
    	auditoria.setAccion(rs.getString(Constante.BNMSDSF14_F14_ACCION));
    	auditoria.setAplicacion(rs.getString(Constante.BNMSDSF14_F14_APLICACION));

        return auditoria;
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