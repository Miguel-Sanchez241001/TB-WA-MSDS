package pe.com.bn.msds.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;
import pe.com.bn.msds.common.LoggerBn;
import pe.com.bn.msds.dao.config.JNDIConnectorPlugin;
import pe.com.bn.msds.dao.inf.LogConsultaDAO;
import pe.com.bn.msds.model.FiltroLogConsulta;
import pe.com.bn.msds.model.LogConsulta;

import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

public class LogConsultaDAOImpl implements LogConsultaDAO {

	private static LoggerBn log = LoggerBn.getInstance(LogConsultaDAOImpl.class.getName());
	
	public LogConsultaDAOImpl() {
	}
	
	@Override
	public boolean insertLogConsulta(final LogConsulta logConsulta) {
		
		boolean result = false;
		
		try {
			
			JNDIConnectorPlugin jcp = new JNDIConnectorPlugin();

			JdbcTemplate jdbcTemplate = new JdbcTemplate(jcp.getDataSource());
			
			
			log.debug("insertLogConsulta | logConsulta: " + logConsulta.toString(), "1");
			
			
			List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
									                    new SqlOutParameter("P_RESULT", Types.VARCHAR));
		    
			
			Map<String, Object> out = jdbcTemplate.call(new CallableStatementCreator() {
				
				@Override
				public CallableStatement createCallableStatement(Connection cnn) throws SQLException {

					CallableStatement cs = cnn.prepareCall("{call BN_MGEC.BNPKG_MGEC_OPERACIONES.BNSP_INSERT_F02_LOGCONSULTA(?,?,?,?,?,?,?,?,?,?)}");

					/**
					P_F02_TIP_CUENTA   IN VARCHAR2,
		  			P_F02_NRO_CUENTA   IN VARCHAR2,
		  			P_F02_MES_INICIAL  IN VARCHAR2,
		  			P_F02_ANNO_INICIAL IN VARCHAR2,
		  			P_F02_MES_FINAL    IN VARCHAR2,
		  			P_F02_ANNO_FINAL   IN VARCHAR2,
		  			P_F02_NOM_USUARIO  IN VARCHAR2,
		  			P_F02_OFICINA      IN VARCHAR2,
		  			P_F02_COD_USUARIO  IN VARCHAR2,
		  			P_RESULT 		   OUT SYS_REFCURSOR
		  			
					F02_TIP_CUENTA	VARCHAR2(15 BYTE)
					F02_NRO_CUENTA	VARCHAR2(14 BYTE)
					F02_MES_INICIAL	VARCHAR2(2 BYTE)
					F02_ANNO_INICIAL	VARCHAR2(4 BYTE)
					F02_MES_FINAL	VARCHAR2(2 BYTE)
					F02_ANNO_FINAL	VARCHAR2(4 BYTE)
					F02_NOM_USUARIO	VARCHAR2(60 BYTE)
					F02_OFICINA		VARCHAR2(30 BYTE)
					F02_AUUSUINS	VARCHAR2(35 BYTE)
					**/
					
			        cs.setString(1, logConsulta.getTipoConsulta());
			        cs.setString(2, logConsulta.getNroConsulta());
			        cs.setString(3, logConsulta.getMesInicial());
			        cs.setString(4, logConsulta.getAnnoInicial());
			        cs.setString(5, logConsulta.getMesFinal());
			        cs.setString(6, logConsulta.getAnnoFinal());
			        cs.setString(7, logConsulta.getNombreUsuario());
			        cs.setString(8, logConsulta.getOficina());
			        cs.setString(9, logConsulta.getAudiUsuarioReg());
					
			        cs.registerOutParameter(10, Types.VARCHAR);
			        
			        return cs;
				}
				
			}, parameters);
			
			
			if (out != null) {
				
				log.debug("RESULTADO DEL INSERT: " + out, "1");
				
				if (String.valueOf(out.get("P_RESULT")).equals("OK")) {
					result = true;
				} else {
					result = false;
					log.debug("ERROR INSERT LOG, INPUT:" + logConsulta.toString(), "1");
					log.error(new Exception(String.valueOf(out.get("P_RESULT"))), "", String.valueOf(out.get("P_RESULT")));
				}
			} else {
				log.debug("RESULTADO NULL PARA INSERT LOG, INPUT:" + logConsulta.toString(), "1");
				log.error(new Exception("RESULTADO NULL PARA INSERT LOG"), "", "RESULTADO NULL PARA INSERT LOG");
			}
			
		} catch (Exception e) {
			log.error(e, "", e.getMessage());
		}
		
		return result;
	}

	@Override
	public List<LogConsulta> listarLogConsulta(final FiltroLogConsulta filtroLogConsulta) {
		
		List<LogConsulta> list = new ArrayList<LogConsulta>();
		
		try {
			
			JNDIConnectorPlugin jcp = new JNDIConnectorPlugin();

			JdbcTemplate jdbcTemplate = new JdbcTemplate(jcp.getDataSource());
			
			log.debug("listarLogConsulta INPUT:" + filtroLogConsulta.toString(), "1");
			
			
			List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
														new SqlParameter(Types.VARCHAR),
									                    new SqlOutParameter("C_CURSOR", OracleTypes.CURSOR, new RowMapper<LogConsulta>() {
															@Override
															public LogConsulta mapRow(
																	ResultSet rs,
																	int arg1) throws SQLException {
																
																LogConsulta logc = new LogConsulta();
																logc.setCodigoLogConsulta(rs.getBigDecimal("F02_CLOG"));
																logc.setTipoConsulta(rs.getString("F02_TIP_CUENTA"));
																logc.setNroConsulta(rs.getString("F02_NRO_CUENTA"));
																logc.setMesInicial(rs.getString("F02_MES_INICIAL"));
																logc.setAnnoInicial(rs.getString("F02_ANNO_INICIAL"));
																logc.setMesFinal(rs.getString("F02_MES_FINAL"));
																logc.setAnnoFinal(rs.getString("F02_ANNO_FINAL"));
																logc.setOficina(rs.getString("F02_OFICINA"));
																logc.setAudiUsuarioReg(rs.getString("F02_AUUSUINS"));
																logc.setAudiFechaReg(rs.getString("F02_AUFECINS"));
																logc.setAudiUsuarioAct(rs.getString("F02_AUUSUMOD"));
																logc.setAudiFechaAct(rs.getString("F02_AUFECMOD"));
																
																/**
																F02_CLOG,
													            F02_TIP_CUENTA,
													            F02_NRO_CUENTA,
													            F02_MES_INICIAL,
													            F02_ANNO_INICIAL,
													            F02_MES_FINAL,
													            F02_ANNO_FINAL,
													            F02_NOM_USUARIO,
													            F02_OFICINA,
													            F02_AUUSUINS,
													            F02_AUFECINS,
													            F02_AUUSUMOD,
													            F02_AUFECMOD
																**/
															
																return logc;
															}
														}));
		    
			
			Map<String, Object> out = jdbcTemplate.call(new CallableStatementCreator() {
				
				@Override
				public CallableStatement createCallableStatement(Connection cnn) throws SQLException {
					
					CallableStatement cs = cnn.prepareCall("{call BN_MGEC.BNPKG_MGEC_OPERACIONES.BNSP_LISTA_F02_LOGCONSULTA(?,?,?,?,?,?)}");

					/**
					P_TIP_CUENTA    IN VARCHAR2,
		            P_NRO_CUENTA    IN VARCHAR2,
		            P_FEC_INICIAL   IN VARCHAR2,
		            P_FEC_FINAL     IN VARCHAR2,
		            P_ORDEN         IN VARCHAR2,
		           	C_CURSOR 		OUT SYS_REFCURSOR
					**/
							
					cs.setString(1, "");
					cs.setString(2, filtroLogConsulta.getNroCuenta());
					cs.setString(3, filtroLogConsulta.getFechaInicial());
					cs.setString(4, filtroLogConsulta.getFechaFinal());
					cs.setString(5, filtroLogConsulta.getOrdenamiento());
					
					cs.registerOutParameter(6, OracleTypes.CURSOR);

			        return cs;
				}
				
			}, parameters);
			
			if (out != null) {
				list = (List<LogConsulta>) out.get("C_CURSOR");
			} else {
				log.debug("NO HAY RESULTADOS CONSULTA EECC, INPUT:" + filtroLogConsulta.toString(), "1");
			}

		} catch (Exception e) {
			log.error(e, "", e.getMessage());
		}
		
		return list;
	}

}
