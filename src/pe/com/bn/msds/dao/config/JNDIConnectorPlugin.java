package pe.com.bn.msds.dao.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import pe.com.bn.msds.common.LoggerBn;

public class JNDIConnectorPlugin {
	
	private static LoggerBn log = LoggerBn.getInstance(JNDIConnectorPlugin.class.getName());
	
	protected DataSource ds;
//	protected DataSource dsSai;
//	protected DataSource dsOracle;
	
//	protected Connection conn;

//	public Connection connect() {
//		try {
//			conn = JdbcUtil.getInstance(jndi).getConnectionDS();
//			conn.setAutoCommit(false);
//			// ds = (DataSource) ctx.lookup();
//			System.out.println("*** EXITOSA CONEXION ***");
//		} catch (Exception ex) {
//			 log.error(ex, "", "[JNDIConnectorPlugin/connect|ERR:"+ex.getMessage());
//		}
//		return conn;
//	}
	
	public DataSource getDataSource(){
		try {
			ds = JdbcUtil.getInstance().getDataSource();
		} catch (SQLException ex) {
			ex.printStackTrace();
			log.error(ex, "", "[JNDIConnectorPlugin/getDataSource|ERR:"+ex.getMessage());
		}
		return ds;
	}

//	public DataSource getDataSourceSAI(){
//		try {
//			dsSai = JdbcUtil.getInstance().getDataSourceSai();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			log.error(ex, "", "[JNDIConnectorPlugin/getDataSourceSAI|ERR:"+ex.getMessage());
//		}
//		return dsSai;
//	}
	
//	public DataSource getDataSourceOracle() {
//		try {
//			dsOracle = JdbcUtil.getInstance(DatosCompConfig.getInstance().getJndi(), DatosCompConfig.getInstance().getJndiSAI()).getDataSourceOracle();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			log.error(ex, "", "[JNDIConnectorPlugin/getDataSourceOracle|ERR:"+ex.getMessage());
//		}
//		return dsOracle;
//	}
	
//	public void destroy(Connection cn, Statement st, ResultSet rs,
//			CallableStatement proc, PreparedStatement pstmt) throws Exception {
//		if (cn != null) {
//			cn.setAutoCommit(true);
//		}
//		if (proc != null) {
//			try {
//				proc.close();
//			} catch (Exception ex) {
//				 log.error(ex, "", "[JNDIConnectorPlugin/destroy|ERR:"+ex.getMessage());
//			}
//			proc = null;
//		}
//		if (rs != null) {
//			try {
//				rs.close();
//			} catch (Exception ex) {
//				 log.error(ex, "", "[JNDIConnectorPlugin/destroy|ERR:"+ex.getMessage());
//			}
//			rs = null;
//		}
//		if (st != null) {
//			try {
//				st.close();
//			} catch (Exception ex) {
//				 log.error(ex, "", "[JNDIConnectorPlugin/destroy|ERR:"+ex.getMessage());
//			}
//			st = null;
//		}
//		if (cn != null) {
//			try {
//				cn.close();
//			} catch (Exception ex) {
//				 log.error(ex, "", "[JNDIConnectorPlugin/destroy|ERR:"+ex.getMessage());
//			}
//			cn = null;
//		}
//		if (pstmt != null) {
//			try {
//				pstmt.close();
//			} catch (Exception ex) {
//				 log.error(ex, "", "[JNDIConnectorPlugin/destroy|ERR:"+ex.getMessage());
//			}
//			pstmt = null;
//		}
//	}
	
}