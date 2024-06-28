package pe.com.bn.msds.dao.impl;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pe.com.bn.msds.dao.pool.ConexionJndi;

//import pe.bn.wsen.common.Constante;
//import pe.com.bn.wsen.envio.dao.ConexionJndi;
//import pe.com.bn.wsen.util.logger.Logger;


public class ConsultaImagen {
	
	
	//private static Logger log3 = Logger.getInstance(ConsultaImagen.class.getName());

	ConexionJndi dss = new ConexionJndi();
	Connection conn = null;
	
	
	
public byte[] consultarFirma1(String codFirma1) throws Exception {
		
		Long id =(long) 0;
	
		
		 PreparedStatement stmt = null;
		 String sql = null;
		 ConexionJndi cn=new ConexionJndi();
		 ResultSet rs = null;

		byte[] bytes=null;
		Blob bytes1=null;
		String fila = codFirma1;
			
		sql = 	"select * from bn_modc.BNMODCF01_PARAMETROS where F01_COD_TABLA = '"+fila+"'";

			
			try {
				conn=cn.connect();
				stmt = conn.prepareStatement(sql);
	            rs = stmt.executeQuery();

				
				while (rs.next()){
			
					bytes = rs.getBytes(3);
					// bytes1 = rs.get
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();			
				//log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				
			}	finally {
	            if(rs!=null){rs.close();rs= null;};
	            if(stmt !=null){stmt.close();stmt = null;};    
	            if(conn !=null){conn.close();conn = null;};
	     }
	

		return bytes;
	 }

public byte[] consultarFirma2(String codFirma2) throws Exception {
	
	Long id =(long) 0;

	
	 PreparedStatement stmt = null;
	 String sql = null;
	 ConexionJndi cn=new ConexionJndi();
	 ResultSet rs = null;

	byte[] bytes=null;
	Blob bytes1=null;
	String fila = codFirma2;
		
	sql = 	"select * from bn_modc.BNMODCF01_PARAMETROS where F01_COD_TABLA = '"+fila+"'";
	
		
		try {
			conn=cn.connect();
			stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

			
			while (rs.next()){
		
				bytes = rs.getBytes(3);
			
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();			
			//log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			
		}	finally {
            if(rs!=null){rs.close();rs= null;};
            if(stmt !=null){stmt.close();stmt = null;};    
            if(conn !=null){conn.close();conn = null;};
     }


	return bytes;
 }


public String consultarNombre1(String codNombre1) throws Exception {
	
	PreparedStatement stmt = null;
	 String sql = null;
	 ConexionJndi cn=new ConexionJndi();
	 ResultSet rs = null;

	
	String nombre1="";
	String fila = codNombre1;
		
	sql = 	"select * from bn_modc.BNMODCF01_PARAMETROS where F01_COD_TABLA = '"+fila+"'";

		
		try {
			conn=cn.connect();
			stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

			
			while (rs.next()){
		
				nombre1 = rs.getString(2);
			
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();			
			//log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			
		}	finally {
            if(rs!=null){rs.close();rs= null;};
            if(stmt !=null){stmt.close();stmt = null;};    
            if(conn !=null){conn.close();conn = null;};
     }


	return nombre1;
 }



public String consultarNombre2(String codNombre2) throws Exception {
	
	PreparedStatement stmt = null;
	 String sql = null;
	 ConexionJndi cn=new ConexionJndi();
	 ResultSet rs = null;

	
	String nombre2="";
	String fila = codNombre2;
		
	sql = 	"select * from bn_modc.BNMODCF01_PARAMETROS where F01_COD_TABLA = '"+fila+"'";

		
		try {
			conn=cn.connect();
			stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

			
			while (rs.next()){
		
				nombre2 = rs.getString(2);
			
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		//	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			
		}	finally {
            if(rs!=null){rs.close();rs= null;};
            if(stmt !=null){stmt.close();stmt = null;};    
            if(conn !=null){conn.close();conn = null;};
     }


	return nombre2;
 }


}
