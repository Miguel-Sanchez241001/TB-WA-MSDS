package pe.com.bn.msds.dao.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;
import pe.com.bn.msds.common.LoggerBn;

public class JdbcUtil {
	
	private static LoggerBn log = LoggerBn.getInstance(JdbcUtil.class.getName());
	
	private static JdbcUtil instance = null;
	private static DataSource ds = null;
//	private static DataSource dsSai = null;
//	private static DataSource dsOracle = null;
	
	private JdbcUtil() {
	}
	
	public synchronized static JdbcUtil getInstance() {
		
		if (instance == null || ds == null ) {
			
			try{
				
				instance = new JdbcUtil();
				
				//Iniciando contexto y guardando referencia al DataSource		
//				Context context = new InitialContext();
//				ds = (DataSource) context.lookup(jndi);
				
				ResourceBundle rb = ResourceBundle.getBundle("parametro");
				
				OracleDataSource oracleDS = new OracleDataSource();
				oracleDS.setURL(rb.getString("database.jndiname.msdsURL"));
				oracleDS.setUser(rb.getString("database.jndiname.msdsUser"));
				oracleDS.setPassword(rb.getString("database.jndiname.msdsPass"));
				
				ds = oracleDS;
				
//				log.debug("JdbcUtil/constructor:", "jndi:" + jndi, "1");
				log.debug("JdbcUtil/constructor:", "DS:" + ds.toString(), "1");
				
				// DataSource SAI
//				dsSai = (DataSource) context.lookup(jndiSai);
				
//				OracleDataSource oracleDsSai = new OracleDataSource();
//				oracleDsSai.setURL(rb.getString("database.jndiname.msdsSaiURL"));
//				oracleDsSai.setUser(rb.getString("database.jndiname.msdsSaiUser"));
//				oracleDsSai.setPassword(rb.getString("database.jndiname.msdsSaiPass"));
//				
//				dsSai = oracleDsSai;
				
//				log.debug("JdbcUtil/getInstanceSai:", "jndiSAI:" + jndiSai, "1");
//				log.debug("JdbcUtil/getInstanceSai:", "DS:" + dsSai.toString(), "1");
				
//				dsOracle = createDataSourceOracle();
				
			} catch(Exception nex) {
				log.error(nex, "", "JdbcUtil/getInstance ERROR:" + nex.getMessage());
				return null;
			}
		}
		
		return instance;
	}
	
//	private static DataSource createDataSourceOracle() {
//		
//		OracleDataSource oracleDS = null;
//		
//		try {
//			oracleDS = new OracleDataSource();
//			oracleDS.setURL(DatosCompConfig.getInstance().getUrlConexion());
//			oracleDS.setUser(DatosCompConfig.getInstance().getUsername());
//			oracleDS.setPassword(DatosCompConfig.getInstance().getPassword());
//		} catch (SQLException e) {
//			log.error(e, "", "JdbcUtil/getOracleDataSource ERROR:" + e.getMessage());
//		}
//		return oracleDS;
//	}
	
	/**
	 * Obtiene una conexion del Data Source
	 */
//	public Connection getConnectionDS() throws SQLException{
//		Connection conn=null;
//		synchronized (ds){
//		conn= ds.getConnection();
//		//conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
//		}
//		return conn;
//	}
	
	public DataSource getDataSource() throws SQLException{
		
		synchronized (ds){
		}
		
		log.debug("JdbcUtil/getDataSource :", ds.toString(), "1");
		
		return ds;
	}
	
//	public DataSource getDataSourceSai() throws SQLException{
//		
//		synchronized (dsSai){
//		}
//		
//		log.debug("JdbcUtil/getDataSourceSai :", dsSai.toString(), "1");
//		
//		return dsSai;
//	}
	
//	public DataSource getDataSourceOracle() throws SQLException{
//		
//		synchronized (dsOracle){
//		}
//		
//		log.debug("JdbcUtil/getDataSourceOracle :", dsOracle.toString(), "1");
//		
//		return dsOracle;
//	}
	/**
	 * Obtiene una conexion del Data Source
	 * @param Un <code>boolean</code> que indica si es autocommit, true; o no, false
	 */
//	public Connection getConnectionDS(boolean autoCommit) throws SQLException{
//		Connection conn = getConnectionDS();
//		conn.setAutoCommit(autoCommit);
//		return conn;
//	}

//	public void closeConnectionSilent(Connection conn) {
//		try {
//			closeConnection(conn);
//		} catch (SQLException sqle) {
//		//	log.error(sqle, "[JdbcUtil/closeConnectionSilent|ERR:"+Constante.CODE_ERROR_GENERICO,Constante.DESC_ERROR_GENERICO);
//		}
//	}
	
//	public void closeStatementSilent(Statement stmt) {
//		try {
//			closeStatement(stmt);
//		} catch (SQLException sqle) {
//		//	log.error(sqle, "[JdbcUtil/closeStatementSilent|ERR:"+Constante.CODE_ERROR_GENERICO,Constante.DESC_ERROR_GENERICO);
//		}
//	}
	
//	public void closeResultSetSilent(ResultSet rs) {
//		try {
//			closeResultSet(rs);
//		} catch (SQLException sqle) {
//		//	log.error(sqle, "[JdbcUtil/closeResultSetSilent|ERR:"+Constante.CODE_ERROR_GENERICO,Constante.DESC_ERROR_GENERICO);
//		}
//	}
	
//	public void closeConnection(Connection conn) throws SQLException {
//		if (conn != null) {
//			conn.close();
//		}
//	}
	
//	public void closeStatement(Statement stmt) throws SQLException {
//		if (stmt != null) {
//			stmt.close();
//		}
//	}
	
//	public void closeResultSet(ResultSet rs) throws SQLException {
//		if (rs != null) {
//			rs.close();
//		}
//	}
	
//	public void closeAllSilent(Connection conn, Statement stmt, ResultSet rs) {
//		closeResultSetSilent(rs);
//		closeStatementSilent(stmt);
//		closeConnectionSilent(conn);
//	}
	
//	public void rollback(Connection conn) throws SQLException {
//		if (conn != null) {
//			conn.rollback();
//		}
//	}
	
//	public void rollbackSilent(Connection conn) {
//		try {
//			rollback(conn);
//		} catch (SQLException sqle) {
//		//	log.error(sqle, "[JdbcUtil/rollbackSilent|ERR:"+Constante.CODE_ERROR_GENERICO,Constante.DESC_ERROR_GENERICO);
//		}
//	}
	
//	public void commit(Connection conn) throws SQLException {
//		if (conn != null) {
//			conn.commit();
//		}
//	}
	
//	public void commitSilent(Connection conn) {
//		try {
//			commit(conn);
//		} catch (SQLException sqle) {
//		//	log.error(sqle, "[JdbcUtil/commitSilent|ERR:"+Constante.CODE_ERROR_GENERICO,Constante.DESC_ERROR_GENERICO);
//		}
//	}
	
//	public void close() {
//		instance=null;		
//	}

}
