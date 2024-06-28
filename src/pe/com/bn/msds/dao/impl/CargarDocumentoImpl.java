package pe.com.bn.msds.dao.impl;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.sql.DataSource;

import pe.com.bn.msds.common.Constant;
import pe.com.bn.msds.common.LoggerBn;
import pe.com.bn.msds.dao.pool.ConexionJndi;

public class CargarDocumentoImpl {

	private static LoggerBn log3 = LoggerBn.getInstance(CargarDocumentoImpl.class.getName());

	ConexionJndi dss = new ConexionJndi();
	Connection conn = null;

	public String cargaFactibilidad(String apellidos, String nombres, String tdocum1, String ndocum1, String fnac,
			String mctaae, String mqsbs, String mcelul, String mmail, String msdes, String msmax, String ncuotas,
			String mabono, String ipAddress, String fechaG, String solOri, String mscp, String laboral, String usuario,
			String sdg, String mscuota, String mtea, String mtcea, String fultop, String mltc, String mcch, String mclabo,
			String abanco1, String tcredi1, String sdeuda1, String abanco2, String tcredi2, String sdeuda2, 
			String abanco3, String tcredi3, String sdeuda3, String abanco4, String tcredi4, String sdeuda4,
			String abanco5, String tcredi5, String sdeuda5, String abanco6, String tcredi6, String sdeuda6) throws Exception {
		
		String sms = "ENVIADO";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		
		log3.debug("num doc insert " + ndocum1, "1");
		
		sql.append("INSERT INTO BN_MSDS.BNREPRF07_FACTIBILIDAD(F07_APELLIDO, " +
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
																"F07_MTCEA, " +																
																"F07_FULTOP, " +
																"F07_MLTC, " +
																"F07_MCCH, " +
																"F07_MCLAVO, " +
																"F07_ABANCO1, " +
																"F07_TCREDI1, " +
																"F07_SDEUDA1, " +
																"F07_ABANCO2, " +
																"F07_TCREDI2, " +
																"F07_SDEUDA2, " +
																"F07_ABANCO3, " +
																"F07_TCREDI3, " +
																"F07_SDEUDA3, " +
																"F07_ABANCO4, " +
																"F07_TCREDI4, " +
																"F07_SDEUDA4, " +
																"F07_ABANCO5, " +
																"F07_TCREDI5, " +
																"F07_SDEUDA5, " +
																"F07_ABANCO6, " +
																"F07_TCREDI6, " +
																"F07_SDEUDA6 " +															
																") VALUES (" +
																"'" + apellidos + "'," +
																"'" + nombres + "'," +
																"'" + tdocum1.replace("0", "") + "'," +
																"'" + ndocum1.trim() + "'," +
																"'" + fnac + "'," +
																"'" + mctaae + "'," +
																"'" + mqsbs + "'," +
																"'" + laboral + "'," +
																"'" + mcelul.trim() + "'," +
																"'" + mmail.trim() + "'," +
																"'" + solOri.trim() + "'," +
																"'" + msmax.trim() + "'," +
																"'" + ncuotas.trim() + "'," +
																"'" + mscp.trim() + "'," +
																"'" + mabono.trim() + "'," +
																"'" + ipAddress.trim() + "'," +
																"'" + fechaG.trim() + "'," +
																"SYSDATE," +
																"'" + usuario + "'," +
																"'" + sdg + "'," +
																"'" + mscuota + "'," +
																"'" + mtea + "'," +
																"'" + mtcea + "'," +														
                                                                "'" + fultop + "'," +
                                                                "'" + mltc + "'," +
                                                                "'" + mcch + "'," +
                                                                "'" + mclabo + "'," +
                                                                "'" + abanco1 + "'," +
                                                                "'" + tcredi1 + "'," +
                                                                "'" + sdeuda1 + "'," +                                                            
                                                                "'" + abanco2 + "'," +
                                                                "'" + tcredi2 + "'," +
                                                                "'" + sdeuda2 + "'," +
                                                                "'" + abanco3 + "'," +
                                                                "'" + tcredi3 + "'," +
                                                                "'" + sdeuda3 + "'," +
                                                                "'" + abanco4 + "'," +
                                                                "'" + tcredi4 + "'," +
                                                                "'" + sdeuda4 + "'," +
                                                                "'" + abanco5 + "'," +
                                                                "'" + tcredi5 + "'," +
                                                                "'" + sdeuda5 + "'," +
                                                                "'" + abanco6 + "'," +
                                                                "'" + tcredi6 + "'," +
                                                                "'" + sdeuda6 + "'" +
                                                              
																")");

		try {
			conn = dss.connect();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.execute();
			conn.commit();

			log3.debug("cargaFactibilidad - INSERT: " + sql.toString(), Constant.LOGGER_DEBUG_NIVEL_1);
			
		} catch (Exception e) {

			log3.error(e, "", e.getMessage());
			
			if (conn != null)
				conn.rollback();
			
			log3.debug("LA CONEXION DE BASE DE DATOS SE HA CERRADO CORRECTAMENTE", Constant.LOGGER_DEBUG_NIVEL_1);
						
			throw e;
			
		} finally {
			
			if (conn != null)
				conn.setAutoCommit(true);
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
				pstmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
				conn = null;
			}
		}

		return sms;
	}

	public String cargaTarjetaSolicitud(String dni, String tipo, byte[] a, String mail, String fechaEnv, String horaEnv,
			String fechaLec, String horaLec, String ipLec, String estado, String campo1, String campo2)
			throws Exception {

		// System.out.println("carga doc");

		String sms = "ENVIADO";

		String tabla = "BN_MODC.BNMODCF03_DOCTARJETA";

		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();

		String query = "";
		String fechaSistema = "SYSDATE()";

		sql.append("INSERT INTO " + tabla + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)");

		try {

			conn = dss.connect();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, dni.trim());
			pstmt.setString(2, tipo);
			pstmt.setBinaryStream(3, new ByteArrayInputStream(a), a.length);
			pstmt.setString(4, mail);
			pstmt.setString(5, fechaEnv);
			pstmt.setString(6, horaEnv);
			pstmt.setString(7, fechaLec);
			pstmt.setString(8, horaLec);
			pstmt.setString(9, ipLec);
			pstmt.setString(10, estado);
			pstmt.setString(11, campo1);
			pstmt.setString(12, campo2);
			pstmt.executeUpdate();
			conn.commit();

			conn = dss.connect();

		} catch (Exception e) {
			if (conn != null)
				conn.rollback();
			log3.error(e, "", "");
			System.out.println("NO ENVIADO");
			throw e;
		} finally {
			if (conn != null)
				conn.setAutoCommit(true);
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
				;
				pstmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
				;
				conn = null;
			}
		}

		return sms;
	}

	public String cargaPrestamo(String desembolso, String tipo, byte[] a, String mail, String fechaEnv, String horaEnv,
			String fechaLec, String horaLec, String ipLec, String estado, String campo1, String campo2, String nombres,
			String age, String usuario) throws Exception {

		// System.out.println("carga doc");

		String sms = "ENVIADO";

		String tabla = "BN_MODC.BNMODCF02_DOCPRESTAMO";

		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();

		String query = "";
		String fechaSistema = "SYSDATE";

		// sql.append("INSERT INTO "+tabla+" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		// sql.append("INSERT INTO "+tabla+" (f02_desembolso, f02_tipo, f02_documento,
		// f02_email, f02_fecha_envio, f02_hora_envio, f02_fecha_lectura, " +
		// "f02_hora_lectura. f02_ip_pc_lectura,f02_estado, f02_campo1, f02_campo2) " +
		// "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

		sql.append("INSERT INTO " + tabla
				+ " (f02_desembolso, f02_tipo, f02_documento, f02_email, f02_fecha_envio, f02_hora_envio, f02_fecha_lectura,f02_hora_lectura,"
				+ " f02_ip_pc_lectura, f02_estado , f02_campo1, f02_campo2, f02_fecha_carga,nombres, agencia, usuario) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?, ?, ?)");

		try {

			conn = dss.connect();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, desembolso.trim());

			pstmt.setString(2, tipo);

			pstmt.setBinaryStream(3, new ByteArrayInputStream(a), a.length);
			pstmt.setString(4, mail.trim());
			pstmt.setString(5, fechaEnv);
			pstmt.setString(6, horaEnv);
			pstmt.setString(7, fechaLec);

			pstmt.setString(8, horaLec);
			pstmt.setString(9, ipLec);
			pstmt.setString(10, estado);
			pstmt.setString(11, campo1);
			pstmt.setString(12, campo2);

			pstmt.setString(13, nombres);
			pstmt.setString(14, age);
			pstmt.setString(15, usuario);

			pstmt.executeUpdate();
			conn.commit();

			conn = dss.connect();

		} catch (Exception e) {
			if (conn != null)
				conn.rollback();
			log3.error(e, "", "");
			System.out.println("NO ENVIADO");
			throw e;
		} finally {
			if (conn != null)
				conn.setAutoCommit(true);
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
				;
				pstmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
				;
				conn = null;
			}
		}

		return sms;
	}

	public String cargaPrestamoCronograma(String desembolso, String tipo, byte[] a, String mail, String fechaEnv,
			String horaEnv, String fechaLec, String horaLec, String ipLec, String estado, String usuario, String campo1,
			String campo2) throws Exception {

		// System.out.println("carga doc");

		String sms = "ENVIADO";

		String tabla = "BN_MODC.BNMODCF04_DOCPRESTAMOCRO";

		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();

		String query = "";
		String fechaSistema = "SYSDATE()";

		sql.append("INSERT INTO " + tabla + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)");

		try {

			conn = dss.connect();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, desembolso.trim());
			pstmt.setString(2, tipo);
			pstmt.setBinaryStream(3, new ByteArrayInputStream(a), a.length);
			pstmt.setString(4, mail);
			pstmt.setString(5, fechaEnv);
			pstmt.setString(6, horaEnv);
			pstmt.setString(7, fechaLec);
			pstmt.setString(8, horaLec);
			pstmt.setString(9, ipLec);
			pstmt.setString(10, estado);
			pstmt.setString(11, usuario);
			pstmt.setString(12, campo1);
			pstmt.setString(13, campo2);
			pstmt.executeUpdate();
			conn.commit();

			conn = dss.connect();

		} catch (Exception e) {
			if (conn != null)
				conn.rollback();
			log3.error(e, "", "");
			System.out.println("NO ENVIADO");
			throw e;
		} finally {
			if (conn != null)
				conn.setAutoCommit(true);
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
				;
				pstmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
				;
				conn = null;
			}
		}

		return sms;
	}

	// ACTUALIZA LA TABLA DE PRESTAMO ENVIO DE CORREO fecha de envio

	public String actualizaPrestamoEnvio(String fechaEnv, String horaEnv, String desembolso) throws Exception {

		String tabla = "BN_MODC.BNMODCF02_DOCPRESTAMO";

		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();

		String query = "";
		String fecha = fechaEnv;
		String hora = horaEnv;
		String desemb = desembolso;
		String mensaje = "ACTUALIZADO";

		sql.append("UPDATE BN_MODC.BNMODCF02_DOCPRESTAMO set F02_FECHA_ENVIO = '" + fechaEnv + "', F02_HORA_ENVIO = '"
				+ horaEnv + "' WHERE F02_DESEMBOLSO = '" + desembolso + "'");

		try {

			conn = dss.connect();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.executeUpdate();
			conn.commit();

			conn = dss.connect();

		} catch (Exception e) {
			if (conn != null)
				conn.rollback();
			log3.error(e, "", "");
			System.out.println("NO ACTUALIZADO");
			throw e;
		} finally {
			if (conn != null)
				conn.setAutoCommit(true);
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
				;
				pstmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
				;
				conn = null;
			}
		}

		return mensaje;
	}

	// actualiza documento generado

	public String prestamoActualizaDoc(String nro_prestamo, byte[] ba, String correo, String campo1, String campo2)
			throws Exception {

		// System.out.println("carga doc");

		String sms = "ACTUALIZADO";

		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();

		// sql.append("UPDATE BN_MODC.BNMODCF02_DOCPRESTAMO set F02_DOCUMENTO =
		// "+documento+", F02_EMAIL = '"+correo+"',
		// F02_CAMPO1 = '"+camp1+"', F02_CAMPO2 = '"+camp2+"' WHERE F02_DESEMBOLSO =
		// '"+desemb+"'");

		sql.append(
				"UPDATE BN_MODC.BNMODCF02_DOCPRESTAMO SET F02_DOCUMENTO = ? , F02_EMAIL = ? , F02_CAMPO1 = ? , F02_CAMPO2 = ?, F02_FECHA_CARGA = SYSDATE WHERE F02_DESEMBOLSO = ? ");

		try {

			// conn = dss.connect();
			// conn.setAutoCommit(false);
			// pstmt= conn.prepareStatement(sql.toString());

			conn = getConnectionmodcService();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setBinaryStream(1, new ByteArrayInputStream(ba), ba.length);
			pstmt.setString(2, correo);
			pstmt.setString(3, campo1);
			pstmt.setString(4, campo2);

			pstmt.setString(5, nro_prestamo);

			pstmt.executeUpdate();
			conn.commit();

			conn = dss.connect();

		} catch (Exception e) {
			if (conn != null)
				conn.rollback();
			log3.error(e, "", "");
			System.out.println("NO ENVIADO");
			throw e;
		} finally {
			if (conn != null)
				conn.setAutoCommit(true);
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
				;
				pstmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
				;
				conn = null;
			}
		}

		return sms;
	}

	/*
	 * public String prestamoActualizaDoc(String nro_prestamo, byte[] ba, String
	 * correo, String campo1,String campo2) throws Exception {
	 * 
	 * 
	 * String tabla = "BN_MODC.BNMODCF02_DOCPRESTAMO";
	 * 
	 * 
	 * 
	 * Connection conn = null; PreparedStatement pstmt = null; StringBuffer sql =
	 * new StringBuffer();
	 * 
	 * String query = ""; String corre = correo.trim(); String camp1 = campo1;
	 * String camp2 = campo2; String desemb = nro_prestamo; String mensaje
	 * ="ACTUALIZADO";
	 * 
	 * byte[] documento =ba;
	 * 
	 * // sql.append("UPDATE BN_MODC.BNMODCF02_DOCPRESTAMO set F02_FECHA_ENVIO = '"
	 * +fechaEnv+"', F02_HORA_ENVIO = '"+horaEnv+"' WHERE F02_DESEMBOLSO = '"
	 * +desembolso+"'");
	 * 
	 * 
	 * sql.append("UPDATE BN_MODC.BNMODCF02_DOCPRESTAMO set F02_DOCUMENTO = "
	 * +documento+", F02_EMAIL = '"+correo+"', F02_CAMPO1 = '"
	 * +camp1+"', F02_CAMPO2 = '"+camp2+"' WHERE F02_DESEMBOLSO = '"+desemb+"'");
	 * 
	 * 
	 * 
	 * try {
	 * 
	 * 
	 * conn = dss.connect(); conn.setAutoCommit(false); pstmt=
	 * conn.prepareStatement(sql.toString());
	 * 
	 * 
	 * 
	 * pstmt.executeUpdate(); conn.commit();
	 * 
	 * conn = dss.connect();
	 * 
	 * } catch (Exception e) { if (conn != null) conn.rollback();
	 * log3.error(e,"",""); System.out.println("NO ACTUALIZADO"); throw e; }finally
	 * { if (conn != null) conn.setAutoCommit(true); if (pstmt != null)
	 * {try{pstmt.close();}catch(Exception e){}; pstmt = null; } if (conn != null) {
	 * try{conn.close();}catch(Exception e){}; conn = null;} }
	 * 
	 * return mensaje; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public String cargaTarjetaSolicitudConsulta(String dni) throws Exception {

		// System.out.println("carga doc");

		String respuesta = "";

		String tabla = "BN_MODC.BNMODCF03_DOCTARJETA";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		String dni1 = "";

		String query = "";
		String fechaSistema = "SYSDATE()";

		sql.append("SELECT * FROM " + tabla + " WHERE F03_DNI = '" + dni + "'");

		try {
			conn = dss.connect();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				respuesta = rs.getString(1);

			}

			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			if (conn != null)
				conn.rollback();
			log3.error(e, "", "");
			System.out.println("NO ENVIADO");
			throw e;
		} finally {
			if (conn != null)
				conn.setAutoCommit(true);
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
				;
				pstmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
				;
				conn = null;
			}
		}

		return respuesta;
	}

	public String prestamoConsulta(String desembolso) throws Exception {

		// System.out.println("carga doc");

		String respuesta = "";

		String tabla = "BN_MODC.BNMODCF02_DOCPRESTAMO";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		String dni1 = "";

		String query = "";
		String fechaSistema = "SYSDATE()";

		sql.append("SELECT * FROM " + tabla + " WHERE F02_DESEMBOLSO = '" + desembolso + "'");

		try {
			conn = dss.connect();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				respuesta = rs.getString(1);

			}

			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			if (conn != null)
				conn.rollback();
			log3.error(e, "", "");

			throw e;
		} finally {
			if (conn != null)
				conn.setAutoCommit(true);
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
				;
				pstmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
				;
				conn = null;
			}
		}

		return respuesta;
	}

	public String prestamoConsultaGuardo(String desembolso) throws Exception {

		// System.out.println("carga doc");

		String respuesta = "";

		String tabla = "BN_MODC.BNMODCF02_DOCPRESTAMO";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		String dni1 = "";

		String query = "";
		String fechaSistema = "SYSDATE()";

		// sql.append("SELECT F02_CAMPO1 FROM "+tabla+" WHERE F02_DESEMBOLSO =
		// '"+desembolso+"' AND F02_CAMPO1 = '1' ");
		sql.append("SELECT F02_CAMPO1 FROM " + tabla + " WHERE F02_DESEMBOLSO = '" + desembolso + "'  ");

		try {
			conn = dss.connect();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				respuesta = rs.getString(1);

			}

			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			if (conn != null)
				conn.rollback();
			log3.error(e, "", "");

			throw e;
		} finally {
			if (conn != null)
				conn.setAutoCommit(true);
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
				;
				pstmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
				;
				conn = null;
			}
		}

		return respuesta;
	}

	public String prestamoConsultaCronograma(String desembolso) throws Exception {

		String respuesta = "";

		String tabla = "BN_MODC.BNMODCF04_DOCPRESTAMOCRO";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT F04_CAMPO1 FROM " + tabla + " WHERE F04_DESEMBOLSO = '" + desembolso + "'  ");

		try {
			conn = dss.connect();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				respuesta = rs.getString(1);

			}

			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			if (conn != null)
				conn.rollback();
			log3.error(e, "", "");

			throw e;
		} finally {
			if (conn != null)
				conn.setAutoCommit(true);
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
				;
				pstmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
				;
				conn = null;
			}
		}

		return respuesta;
	}

	public Connection getConnectionmodcService() throws SQLException, Exception {
		Connection connection = null;
		try {
			ResourceBundle rb = ResourceBundle.getBundle("parametro");
			String jdni = rb.getString("database.jndiname.modc");

			Context ic = new javax.naming.InitialContext();
			DataSource dataSource = (javax.sql.DataSource) ic.lookup(jdni);
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			log3.error(e, Constant.ERR_CLASS_UTIL, "");
			throw e;
		} catch (Exception e) {
			log3.error(e, Constant.ERR_CLASS_UTIL, "");
			throw e;
		}
		return connection;
	}

}
