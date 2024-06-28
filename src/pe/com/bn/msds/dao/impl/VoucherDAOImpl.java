package pe.com.bn.msds.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pe.com.bn.msds.common.LoggerBn;
import pe.com.bn.msds.dao.inf.VoucherDAO;
import pe.com.bn.msds.model.Bndpf28;

public class VoucherDAOImpl implements VoucherDAO {

	private static LoggerBn log = LoggerBn.getInstance(VoucherDAOImpl.class.getName());

	@Override
	public List<Bndpf28> datosVoucherByNroPedido(String nroPedido) {
		return null;
	}
	
//	@Override
//	public List<Bndpf28> datosVoucherByNroPedido(String nroPedido) {
//		
//		List<Bndpf28> list = new ArrayList<Bndpf28>();
//		
//		try {
//			
//			DataComConnectorPlugin dccp = new DataComConnectorPlugin();
//			
//			String sqlQuery = "SELECT F28_NSECUENCIA, " +
//								"F28_CCUENTA, " +
//								"F28_FPROCESO, " +
//								"F28_HPROCESO, " +
//								"F28_CUSUARIO, " +
//								"F28_CAGENCIA, " +
//								"F28_PERIODO_INICIO, " +
//								"F28_PERIODO_FINAL, " +
//								"F28_HIMPRESION, " +
//								"F28_GIMPRESION " +
//								"from NACION.BNDPF28 " +
//								"where F28_NSECUENCIA =" + nroPedido + " AND F28_GIMPRESION ='0'";
//			
//			
//			Connection con = dccp.getConnection();
//			
//			Statement stmt = con.createStatement();
//			
//			ResultSet rs = stmt.executeQuery(sqlQuery);
//			
//			while (rs.next()) {
//				
//				Bndpf28 bndpf28 = new Bndpf28();
//				bndpf28.setF28_NSECUENCIA(rs.getString("F28_NSECUENCIA"));
//				bndpf28.setF28_CCUENTA(rs.getString("F28_CCUENTA"));
//				bndpf28.setF28_FPROCESO(rs.getString("F28_FPROCESO"));
//				bndpf28.setF28_HPROCESO(rs.getString("F28_HPROCESO"));
//				bndpf28.setF28_CUSUARIO(rs.getString("F28_CUSUARIO"));
//				bndpf28.setF28_CAGENCIA(rs.getString("F28_CAGENCIA"));
//				bndpf28.setF28_PERIODO_INICIO(rs.getString("F28_PERIODO_INICIO"));
//				bndpf28.setF28_PERIODO_FINAL(rs.getString("F28_PERIODO_FINAL"));
//				bndpf28.setF28_HIMPRESION(rs.getString("F28_HIMPRESION"));
//				bndpf28.setF28_GIMPRESION(rs.getString("F28_GIMPRESION"));
//				
//				list.add(bndpf28);
//			}
//			
//			
//		} catch (Exception e) {
//			log.error(e, "", e.getMessage());
//			e.printStackTrace();
//		}
//		
//		return list;
//	}

}
