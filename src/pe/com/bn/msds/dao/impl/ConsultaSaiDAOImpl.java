package pe.com.bn.msds.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import pe.com.bn.msds.common.LoggerBn;
import pe.com.bn.msds.dao.config.JNDIConnectorPlugin;
import pe.com.bn.msds.dao.inf.ConsultaSaiDAO;
import pe.com.bn.msds.model.ConsultaSAI;

public class ConsultaSaiDAOImpl implements ConsultaSaiDAO {

	private static LoggerBn log = LoggerBn.getInstance(ConsultaSaiDAOImpl.class.getName());
	
	@Override
	public ConsultaSAI obtenerDatosUsuario(String codEmpleado) {
		
		try {
			
			JNDIConnectorPlugin jcp = new JNDIConnectorPlugin();
			
//			JdbcTemplate jdbcTemplate = new JdbcTemplate(jcp.getDataSourceSAI());
			
//			String sql = "select * from desorabn.bn_bn_hr_vi_sca_empleados where codigoempleado = ?";
//			SELECT CODIGOEMPLEADO, NOMBRES, APELLIDOS, ORGANIZACION, FECHAINICIOASIG FROM DESORABN.BN_BN_HR_VI_SCA_EMPLEADOS WHERE CODIGOEMPLEADO = '0335509' ORDER BY FECHAINICIOASIG DESC;
//			String sql = "SELECT CODIGOEMPLEADO, NOMBRES, APELLIDOS, ORGANIZACION FROM DESORABN.BN_BN_HR_VI_SCA_EMPLEADOS WHERE CODIGOEMPLEADO = ? ORDER BY FECHAINICIOASIG DESC";
			
//			List<ConsultaSAI> data = jdbcTemplate.query(sql, new Object[] {codEmpleado}, mapperConsultaSAI);

//			if (	data != null 
//				&& 	data.size() > 0) {
//				
//				return data.get(0);
//				
//			} else {
				return null;
//			}
			
		} catch(Exception e){
			e.printStackTrace();
			log.error(e, "", e.getMessage());
			return null;
		}
		
	}

	private RowMapper<ConsultaSAI> mapperConsultaSAI = new RowMapper<ConsultaSAI>() {
		
		public ConsultaSAI mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			ConsultaSAI bean = new ConsultaSAI();
			
			bean.setCodUsuario("0");
			bean.setCodEmpleado(rs.getString("CODIGOEMPLEADO"));
			bean.setNomusuario(rs.getString("NOMBRES") + " " + rs.getString("APELLIDOS"));
			
			String organizacion = rs.getString("ORGANIZACION");
			bean.setCodDepend(organizacion.substring(0,4));
			bean.setNomDependen(organizacion.substring(5,organizacion.length()));
			
			return bean;
		}
	};
	
}
