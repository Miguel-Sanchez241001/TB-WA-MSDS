package pe.com.bn.msds.dao.pool;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.sql.DataSource;

import pe.com.bn.msds.common.Constant;
import pe.com.bn.msds.common.LoggerBn;
import pe.com.bn.msds.dao.pool.ParamDescriptor;
 
public class JDBCPool {
 
	private static LoggerBn log3 = LoggerBn.getInstance(JDBCPool.class.getName());
 
	   public Connection getConnectionmgecService() throws SQLException,Exception {
	        Connection connection   = null;
	         try{
	        	 ResourceBundle rb = ResourceBundle.getBundle("parametro");
		         String jdni = rb.getString("database.jndiname.mgec");
	             Context ic  = new javax.naming.InitialContext();
	             DataSource dataSource = (javax.sql.DataSource)ic.lookup(jdni);           
	               connection   = dataSource.getConnection(); 		 
	              }catch(SQLException e) {
	            	  log3.error(e,Constant.ERR_CLASS_UTIL,""); 
	                  throw e;
	              }catch(Exception e) {
	            	  log3.error(e,Constant.ERR_CLASS_UTIL,""); 
	                  throw e;
	              }
	          return connection;
	    }
    
	   public Connection getConnectionBnTablasService() throws SQLException,Exception {
	        Connection connection   = null;
	         try{
	        	 ResourceBundle rb = ResourceBundle.getBundle("parametro");
		         String jdni = rb.getString("database.jndiname.bnTablas");
	             Context ic  = new javax.naming.InitialContext();
	             DataSource dataSource = (javax.sql.DataSource)ic.lookup(jdni);           
	               connection   = dataSource.getConnection(); 		 
	              }catch(SQLException e) {
	            	  log3.error(e,Constant.ERR_CLASS_UTIL,""); 
	                  throw e;
	              }catch(Exception e) {
	            	  log3.error(e,Constant.ERR_CLASS_UTIL,""); 
	                  throw e;
	              }
	          return connection;
	    }
	   
	   public PreparedStatement instancePreparedStatement(Connection conn,String sql, List<ParamDescriptor> parameters) throws SQLException {    
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);

	        int counter = 1;
	        for (ParamDescriptor paramDescriptor : parameters) {
	            preparedStatement.setObject(counter, 
	                                        paramDescriptor.getValue(),
	                                        paramDescriptor.getDataType());
	            counter++;
	        }
	        return preparedStatement;
	    }

 }