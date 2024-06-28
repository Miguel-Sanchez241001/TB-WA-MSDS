package pe.com.bn.msds.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDataComDao {

	public static void main(String[] args) {
		
		Connection con = null;
	    
		try {
			
//			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver") ;
//			
//			// Connect with a url string
////			con = DriverManager.getConnection("jdbc:odbc:Driver={SQL Server};SERVER=10.1.0.8;DATABASE=ESCTHost;UID=DMSM;PWD=DMSM2112");
//			con = DriverManager.getConnection("jdbc:odbc:Driver={SQL Server};SERVER=10.1.0.8;DATABASE=ESCTHost", "DMSM", "DMSM2112");
//			System.out.println("DSN Connection ok.");
//	      	con.close();
	      
	      	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); //Loading the JDBC driver
	      	con = DriverManager.getConnection("jdbc:odbc:ESCTHost;SERVER=10.1.0.8;UID=DMSM;PWD=DMSM2112"); //Establishing the connection
	      	System.out.println("Connection Created to ISC Database");
	      	con.close(); //Closing the connection
	      	System.out.println("Connection closed");
	      	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		
	}
}
