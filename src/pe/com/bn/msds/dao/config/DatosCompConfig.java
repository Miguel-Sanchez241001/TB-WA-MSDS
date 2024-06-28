package pe.com.bn.msds.dao.config;

import java.util.ResourceBundle;

import pe.com.bn.msds.common.LoggerBn;

public class DatosCompConfig {

	private static LoggerBn log = LoggerBn.getInstance(DatosCompConfig.class.getName());
	
	private static DatosCompConfig jndiConfig;
	private String jndi;
	private String jndiSAI;
	private String endPointBduc;
	private String password;
	private String urlConexion;
	private String username;
	private String apiRestPdf;
	private String claveApiRest;
	

	public static DatosCompConfig getInstance() {
		
		if (jndiConfig == null) {
			jndiConfig = new DatosCompConfig();
		}
		
		return jndiConfig;
    }
	
	public String getJndi() {
		
		if (jndi == null || jndi.trim().equals("")) {
			ResourceBundle rb = ResourceBundle.getBundle("parametro");
			jndi = rb.getString("database.jndiname.msds");//"jdbc/msdsOracle";//solicitarDatosComp();
		}
		log.debug("DatosCompConfig jndi: " + jndi, "1");
		return jndi;
	}
	



	

	

	

	

	


}
