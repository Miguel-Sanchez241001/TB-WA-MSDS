package pe.com.bn.msds.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;
import pe.com.bn.msds.common.LoggerBn;
import pe.com.bn.msds.dao.config.JNDIConnectorPlugin;
import pe.com.bn.msds.dao.inf.EstadoCuentaDAO;
import pe.com.bn.msds.model.EstadoCuentaCabecera;

import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

public class EstadoCuentaDAOImpl implements EstadoCuentaDAO {

	private static LoggerBn log = LoggerBn.getInstance(EstadoCuentaDAOImpl.class.getName());
	
	public EstadoCuentaDAOImpl() {
	}
	
	@Override
	public List<Map<String, String>> listarEstadoCuentaMovimientos(final EstadoCuentaCabecera estadoCuentaCabecera) {
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		try {

			JNDIConnectorPlugin jcp = new JNDIConnectorPlugin();

			JdbcTemplate jdbcTemplate = new JdbcTemplate(jcp.getDataSource());
			
			
			log.debug("listarEstadoCuentaMovimientos INPUT:" + estadoCuentaCabecera.toString(), "1");
			
			
			List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
									                    new SqlOutParameter("C_CURSOR", OracleTypes.CURSOR, new RowMapper<Map<String, String>>() {
															@Override
															public Map<String, String> mapRow(
																	ResultSet rs,
																	int arg1) throws SQLException {
																
																Map<String, String> map = new HashMap<String, String>();
																
																map.put("F01_ID", rs.getString("F01_ID"));
																map.put("F01_CCUENTA", rs.getString("F01_CCUENTA"));
																map.put("F01_CCI", rs.getString("F01_CCI"));
																map.put("F01_DNI", rs.getString("F01_DNI"));
																map.put("F01_FECPROC", rs.getString("F01_FECPROC"));
																map.put("F01_CAGEN", rs.getString("F01_CAGEN") == null ? "0000" : rs.getString("F01_CAGEN"));
																map.put("F01_CCAJERO", rs.getString("F01_CCAJERO") == null ? "" : rs.getString("F01_CCAJERO"));
																map.put("F01_REFERENCIA", rs.getString("F01_REFERENCIA"));
																map.put("F01_CONCEPTO", rs.getString("F01_CONCEPTO"));
																map.put("F01_CARGO", rs.getBigDecimal("F01_CARGO") == null ? "" : String.valueOf(rs.getBigDecimal("F01_CARGO").setScale(2)));
																map.put("F01_ABONO", rs.getBigDecimal("F01_ABONO") == null ? "" : String.valueOf(rs.getBigDecimal("F01_ABONO").setScale(2)));
																map.put("F01_SALDO", String.valueOf(rs.getBigDecimal("F01_SALDO").setScale(2)));
																map.put("F01_HORA", rs.getString("F01_HORA") == null ? "" : rs.getString("F01_HORA"));
																map.put("F01_ANIO", String.valueOf(rs.getInt("F01_ANIO")));
																map.put("F01_MES", String.valueOf(rs.getInt("F01_MES")));
																
																/**
																F01_ID,
													            F01_CCUENTA,
													            F01_CCI,
													            F01_DNI,
													            F01_FECPROC,
													            F01_CAGEN,
													            F01_CCAJERO,
													            F01_REFERENCIA,
													            F01_CONCEPTO,
													            F01_CARGO,
													            F01_ABONO,
													            F01_SALDO,
													            F01_HORA,
													            F01_ANIO,
													            F01_MES
																**/
															
																return map;
															}
														}));
		    
			
			Map<String, Object> out = jdbcTemplate.call(new CallableStatementCreator() {
				
				@Override
				public CallableStatement createCallableStatement(Connection cnn) throws SQLException {
					
					CallableStatement cs = cnn.prepareCall("{call BN_MGEC.BNPKG_MGEC_OPERACIONES.BNSP_LISTA_F01_MOV_CUENTA(?,?,?,?,?)}");
					
					/**
					P_NROCUENTA     IN VARCHAR2,
		            P_NRO_DNI       IN VARCHAR2,
		            P_FEC_INICIAL   IN VARCHAR2,
		            P_FEC_FINAL     IN VARCHAR2,
		            C_CURSOR 		OUT SYS_REFCURSOR
					**/
							
					cs.setString(1, estadoCuentaCabecera.getNroCuenta());
					log.debug("P_NROCUENTA: " + estadoCuentaCabecera.getNroCuenta(), "1");
					
					cs.setString(2, estadoCuentaCabecera.getNroDni());
					log.debug("P_NRO_DNI: " + estadoCuentaCabecera.getNroDni(), "1");
					
					String fechaInicial = "01/" + estadoCuentaCabecera.getMesInicial() + "/" + estadoCuentaCabecera.getAnnoInicial();
					
					cs.setString(3, fechaInicial);
					log.debug("P_FEC_INICIAL: " + fechaInicial, "1");
					
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.MONTH, Integer.parseInt(estadoCuentaCabecera.getMesFinal()) - 1);
					int ultimoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
					
					String fechaFinal = String.valueOf(ultimoDia) + "/" + estadoCuentaCabecera.getMesFinal() + "/" + estadoCuentaCabecera.getAnnoFinal();
					
					
					cs.setString(4, fechaFinal);
					log.debug("P_FEC_FINAL: " + fechaFinal, "1");
					
					cs.registerOutParameter(5, OracleTypes.CURSOR);

			        return cs;
				}
				
			}, parameters);
			
			if (out != null) {
				list = (List<Map<String, String>>) out.get("C_CURSOR");
	        } else {
	        	log.debug("NO HAY RESULTADOS CONSULTA EECC, INPUT:" + estadoCuentaCabecera.toString(), "1");
	        }
			
		} catch (Exception e) {
			log.error(e, "", e.getMessage());
		}
		
		return list;
	}
	
}
