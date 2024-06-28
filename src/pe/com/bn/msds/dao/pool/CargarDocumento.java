package pe.com.bn.msds.dao.pool;

import pe.com.bn.msds.dao.impl.CargarDocumentoImpl;

public class CargarDocumento {

	public String factibilidad(String apellidos, String nombres, String tdocum1, String ndocum1, String fnac,
			String mctaae, String mqsbs, String mcelul, String mmail, String msdes, String msmax, String ncuotas,
			String mabono, String ipAddress, String fechaG, String solOri, String mscp, String laboral, String usuario,
			String sdg, String mscuota, String mtea, String mtcea, String fultop, String mltc, String mcch, String mclabo,
			String abanco1, String tcredi1, String sdeuda1, String abanco2, String tcredi2, String sdeuda2, 
			String abanco3, String tcredi3, String sdeuda3, String abanco4, String tcredi4, String sdeuda4,
			String abanco5, String tcredi5, String sdeuda5, String abanco6, String tcredi6, String sdeuda6) throws Exception {

	

		
		
		String resultado = "";

		CargarDocumentoImpl factibilidad = new CargarDocumentoImpl();
		resultado = factibilidad.cargaFactibilidad(apellidos, nombres, tdocum1, ndocum1, fnac, mctaae, mqsbs, mcelul,
				mmail, msdes, msmax, ncuotas, mabono, ipAddress, fechaG, solOri, mscp, laboral, usuario, 
				sdg, mscuota, mtea, mtcea, fultop,  mltc,  mcch,  mclabo,
				abanco1,  tcredi1, sdeuda1, abanco2,  tcredi2, sdeuda2, 
				 abanco3,  tcredi3,  sdeuda3,  abanco4,  tcredi4, sdeuda4,
				 abanco5,  tcredi5,  sdeuda5,  abanco6,  tcredi6,  sdeuda6);

		return resultado;
	}

	public String tarjetaSolicitud(String dni, String tipo, byte[] a, String mail, String fechaEnv, String horaEnv, String fechaLec,
			String horaLec, String ipLec, String estado, String campo1, String campo2) throws Exception{
		String resultado = "";
		
		
		CargarDocumentoImpl solicitud = new CargarDocumentoImpl ();
		resultado = solicitud.cargaTarjetaSolicitud( dni,  tipo, a,  mail,  fechaEnv,  horaEnv,  fechaLec,
				 horaLec,  ipLec,  estado,  campo1,  campo2);
		
		return resultado;
			
		}
	
	
	
	//ACTUALIZA FECHA DE ENVIO CORREO
	
	public String prestamoActualiza(String fechaEnv,String horaEnv, String desembolso) throws Exception{
		String resultado = "";
		
		
		CargarDocumentoImpl prestamo = new CargarDocumentoImpl ();
		resultado = prestamo.actualizaPrestamoEnvio(fechaEnv,horaEnv, desembolso);
		
		return resultado;
			
		}
	
	public String prestamo(String desembolso, String tipo, byte[] a, String mail, String fechaEnv, String horaEnv, String fechaLec,
			String horaLec, String ipLec, String estado, String campo1, String campo2, String nombres, String age, String usuario) throws Exception{
		String resultado = "";
		
		
		CargarDocumentoImpl prestamo = new CargarDocumentoImpl ();
		resultado = prestamo.cargaPrestamo( desembolso,  tipo, a,  mail,  fechaEnv,  horaEnv,  fechaLec,
				 horaLec,  ipLec,  estado,  campo1,  campo2, nombres, age, usuario);
		
		return resultado;
			
		}
	
	public String prestamoCronograma(String desembolso, String tipo, byte[] a, String mail, String fechaEnv, String horaEnv, String fechaLec,
			String horaLec, String ipLec, String estado,String usuario,  String campo1, String campo2) throws Exception{
		String resultado = "";
		
		
		CargarDocumentoImpl prestamo = new CargarDocumentoImpl ();
		resultado = prestamo.cargaPrestamoCronograma( desembolso,  tipo, a,  mail,  fechaEnv,  horaEnv,  fechaLec,
				 horaLec,  ipLec,  estado,usuario,   campo1,  campo2);
		
		return resultado;
			
		}
	
	//actualiza documento generado
	
	public String prestamoActualizaDoc(String desembolso, byte[] a, String mail,   String campo1, String campo2) throws Exception{
		String resultado = "";
		
		
		CargarDocumentoImpl prestamo = new CargarDocumentoImpl ();
		resultado = prestamo.prestamoActualizaDoc( desembolso,  a,  mail,  campo1,  campo2);
		
		return resultado;
			
		}
	
	public String tarjetaSolicitudConsulta(String dni) throws Exception{
		String resultado = "";
		
		
		CargarDocumentoImpl solicitud = new CargarDocumentoImpl ();
		resultado = solicitud.cargaTarjetaSolicitudConsulta( dni);
		
		return resultado;
			
		}

	public String prestamoConsulta(String desembolso) throws Exception{
		String resultado = "";
		
		
		CargarDocumentoImpl prestamo = new CargarDocumentoImpl ();
		resultado = prestamo.prestamoConsulta(desembolso);
		
		return resultado;
			
		}
	
	
	public String prestamoConsultaGuardo(String desembolso) throws Exception{
		String resultado = "";
		
		
		CargarDocumentoImpl prestamo = new CargarDocumentoImpl ();
		resultado = prestamo.prestamoConsultaGuardo(desembolso);
		
		return resultado;
			
		}
	
	
	public String prestamoConsultaCronograma(String desembolso) throws Exception{
		String resultado = "";
		
		
		CargarDocumentoImpl prestamo = new CargarDocumentoImpl ();
		resultado = prestamo.prestamoConsultaCronograma(desembolso);
		
		return resultado;
			
		}
	
}
