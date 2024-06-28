package pe.com.bn.msds.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pe.com.bn.msds.common.LoggerBn;
import pe.com.bn.msds.dao.config.JNDIConnectorPlugin;
import pe.com.bn.msds.dao.inf.GenerarReporteDAO;
import pe.com.bn.msds.model.F07Factibilidad;
import pe.com.bn.msds.model.FiltroGenerarReporte;

public class GenerarReporteDAOImpl implements GenerarReporteDAO {

	private static LoggerBn log = LoggerBn.getInstance(GenerarReporteDAOImpl.class.getName());
	
	@Override
	public List<F07Factibilidad> listarFactibilidad(FiltroGenerarReporte filtro)  throws Exception {

		try {
			
			JNDIConnectorPlugin jcp = new JNDIConnectorPlugin();
			
			String sql = "SELECT " +
						"F07_APELLIDO, " +
						"F07_NOMBRE, " +
						"F07_TIPO, " +
						"F07_NUMERO, " +
						"F07_FNAC, " +
						"F07_CUENTA, " +
						"F07_SITUACION, " +
						"F07_LABORAL, " +
						"F07_CELULAR, " +
						"F07_CORREO, " +
						"F07_IMPSOL, " +
						"F07_IMPMAX, " +
						"F07_PLAZO, " +
						"F07_SCUOTA, " +
						"F07_IMPDES, " +
						"F07_IP, " +
						"F07_FECHA, " +
						"F07_FECHA_CREA, " +
						"F07_USUCONSULTA, " +
						"F07_SDG, " +
						"F07_MSCUOTA, " +
						"F07_MTEA, " +
						"F07_MTCEA " +
						"FROM " +
						"BN_MSDS.BNMSDSF07_FACTIBILIDAD " +
						"WHERE " +
						"TO_DATE(TO_CHAR(TO_DATE(F07_FECHA, 'DD/MM/YYYY HH:MI:SS AM', 'NLS_DATE_LANGUAGE=ENGLISH'), 'DD/MM/YYYY'), 'DD/MM/YYYY') " +
						"BETWEEN TO_DATE('" + filtro.getFechaInicial() + "', 'DD/MM/YYYY') " +
						"AND TO_DATE('" + filtro.getFechaFinal() + "', 'DD/MM/YYYY') " +
						"ORDER BY F07_FECHA_CREA DESC";
			
			log.debug("listarFactibilidad - filtro: " + filtro, "1");
			log.debug("listarFactibilidad - sql: " + sql, "1");
			
			Connection conn = jcp.getDataSource().getConnection();
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			
			List<F07Factibilidad> lista = new ArrayList<F07Factibilidad>();
			
			while (rs.next()) {
				
				F07Factibilidad bean = new F07Factibilidad();
				
				bean.setF07Apellido(rs.getString("F07_APELLIDO") == null ? "" : rs.getString("F07_APELLIDO"));
				bean.setF07Nombre(rs.getString("F07_NOMBRE") == null ? "" : rs.getString("F07_NOMBRE"));
				bean.setF07Tipo(rs.getString("F07_TIPO") == null ? "" : rs.getString("F07_TIPO"));
				bean.setF07Numero(rs.getString("F07_NUMERO") == null ? "" : rs.getString("F07_NUMERO"));
				bean.setF07Fnac(rs.getString("F07_FNAC") == null ? "" : rs.getString("F07_FNAC"));
				bean.setF07Cuenta(rs.getString("F07_CUENTA") == null ? "" : rs.getString("F07_CUENTA"));
				bean.setF07Situacion(rs.getString("F07_SITUACION") == null ? "" : rs.getString("F07_SITUACION"));
				bean.setF07Laboral(rs.getString("F07_LABORAL") == null ? "" : rs.getString("F07_LABORAL"));
				bean.setF07Celular(rs.getString("F07_CELULAR") == null ? "" : rs.getString("F07_CELULAR"));
				bean.setF07Correo(rs.getString("F07_CORREO") == null ? "" : rs.getString("F07_CORREO"));
				bean.setF07Impsol(rs.getString("F07_IMPSOL") == null ? "" : rs.getString("F07_IMPSOL"));
				bean.setF07Impmax(rs.getString("F07_IMPMAX") == null ? "" : rs.getString("F07_IMPMAX"));
				bean.setF07Plazo(rs.getString("F07_PLAZO") == null ? "" : rs.getString("F07_PLAZO"));
				bean.setF07Scuota(rs.getString("F07_SCUOTA") == null ? "" : rs.getString("F07_SCUOTA"));
				bean.setF07Impdes(rs.getString("F07_IMPDES") == null ? "" : rs.getString("F07_IMPDES"));
				bean.setF07Ip(rs.getString("F07_IP") == null ? "" : rs.getString("F07_IP"));
				bean.setF07Fecha(rs.getString("F07_FECHA") == null ? "" : rs.getString("F07_FECHA"));
				bean.setF07FechaCrea(rs.getString("F07_FECHA_CREA") == null ? "" : rs.getString("F07_FECHA_CREA"));
				bean.setF07Usuconsulta(rs.getString("F07_USUCONSULTA") == null ? "" : (rs.getString("F07_USUCONSULTA").equalsIgnoreCase("null") ? "" : rs.getString("F07_USUCONSULTA")));
				bean.setF07Sdg(rs.getString("F07_SDG") == null ? "" : rs.getString("F07_SDG"));
				bean.setF07Mscuota(rs.getString("F07_MSCUOTA") == null ? "" : rs.getString("F07_MSCUOTA"));
				bean.setF07Mtea(rs.getString("F07_MTEA") == null ? "" : rs.getString("F07_MTEA"));
				bean.setF07Mtcea(rs.getString("F07_MTCEA") == null ? "" : rs.getString("F07_MTCEA"));
				
				lista.add(bean);
			}
			
			log.debug("listarFactibilidad - lista.size(): " + lista.size(), "1");
			
			return lista;
			
		} catch(Exception e){
			log.error(e, "", "GenerarReporteDAOImpl - listarFactibilidad " + e.getMessage());
			return null;
		}
	}

}
