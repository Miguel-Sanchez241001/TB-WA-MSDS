package pe.com.bn.msds.dao.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import pe.com.bn.msds.common.LoggerBn;

public class DataComUtil {
	
	private static LoggerBn log = LoggerBn.getInstance(DataComUtil.class.getName());
	
	private static DataComUtil instance = null;
	private static Connection cnn = null;
	
	private DataComUtil(String urlDataCom) throws ClassNotFoundException, SQLException {
		
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver") ;

		cnn = DriverManager.getConnection(urlDataCom);

		log.debug("DataComUtil/constructor:", "Connection:" + cnn.toString(), "1");
	}

	public synchronized static DataComUtil getInstance(String urlDataCom) {
		
		if (instance == null || cnn == null) {
			
			try{
				
				instance = new DataComUtil(urlDataCom);
				
			} catch(Exception e){
				System.out.println("DataComUtil:" + e.getMessage());
				log.error(e, "", "DataComUtil/getInstance ERROR:" + e.getMessage());
				log.debug("DataComUtil/getInstance ERROR:", e.getMessage(), "1");
				return null;
			}
		}
		
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
		
		synchronized (cnn){
		}
		
		log.debug("DataComUtil/getConnection :", cnn.toString(), "1");
		
		return cnn;
	}
	
}
