package pe.com.bn.msds.dao.pool;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.sql.DataSource;
import javax.naming.InitialContext;
public class ConexionJndi {

	   public static DataSource getDataSource() throws NamingException {
	        Context initialContext = new InitialContext();
	        
	        return (DataSource) initialContext.lookup("jdbc/msdsOracle");
	    }
	
	public Connection connect() throws SQLException, Exception {

		Connection conetion = null;
		
		try {
			String jdni = "jdbc/msdsOracle";
//			String jdni = DatosCompConfig.getInstance().getJndi();
			
			conetion = getDinamicConection(jdni);
			conetion.setAutoCommit(false);
			
		} catch (SQLException e) {

			throw e;
		} catch (Exception e) {
			throw e;
		}
		
		return conetion;
	}

	private Connection getDinamicConection(String jdni) throws SQLException, Exception {
		
		Connection conetion = null;
		
		try {

			Context ic = new javax.naming.InitialContext();
			DataSource dataSource = (javax.sql.DataSource) ic.lookup(jdni);

			conetion = dataSource.getConnection();

		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		
		return conetion;
	}

}
