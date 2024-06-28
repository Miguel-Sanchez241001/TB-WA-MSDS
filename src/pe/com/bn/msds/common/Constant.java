package pe.com.bn.msds.common;

import java.util.HashMap;
import java.util.List;

public class Constant {
	

	public static final String LOGGER_DEBUG_NIVEL_1 = "1";
	public static final String LOGGER_DEBUG_NIVEL_2 = "2";
	public static String BN_LOGGER_PRINT_ERROR = null;
	public static String BN_LOGGER_PRINT_DEBUG_NIVEL_1 = null;
	public static String BN_LOGGER_PRINT_DEBUG_NIVEL_2 = null;
	public static String BN_LOGGER_PRINT_TRACER = null;
	
	public static String BN_ERR_LOGGER_FILE = null;
	public static String BN_ERR_LOGGER_CONSOLE = null;
	public static String BN_ERR_LOGGER_PATH = null;
	
	public static String BN_PROC_LOGGER_FILE = null;
	public static String BN_PROC_LOGGER_CONSOLE = null;
	public static String BN_PROC_LOGGER_PATH = null;
	
	public static String BN_TRACER_LOGGER_FILE = null;
	public static String BN_TRACER_LOGGER_PATH = null;
	
	public static HashMap parametrosHash = new HashMap();
	
	/**  Datos  usuario  **/
	public static  String VAR_GLB_COD_APLICATIVO = "msds";
	public static final String CONST_ID_DESA = "00";
	
	public static String ERR_LOGICA_NEGOCIO="5000";
	public static String ERR_CLASS_UTIL    ="5001";	
	public static String ERR_ARCHIVO_NO_ENCONTRADO="5002";
	public static String ERR_LOGICA_CNX_DB = "5004";

	//Parametros en memoria
	public static HashMap<String,List<Row>> BN_TABLE_MAIN_DATO = null; 
	
	public static int NUM_DIGITOS_DECIMAL_2 = 2;
	public static int NUM_DIGITOS_DECIMAL_0 = 0;

	public static int NUM_DECIMAL_CON_COMA_2 = 2;
	public static int NUM_DECIMAL_SIN_COMA_1 = 1;
	public static int NUM_ENTERO_0 = 0;
	
	public static String VAR_GLB_UBICACION_ALL = "07";
	public static String VAR_GLB_UBICACION_LIMA = "08";
	public static String VAR_GLB_UBICACION_PIURA = "09";
	public static String VAR_GLB_UBICACION_TRUJILLO = "10";
	public static String VAR_GLB_UBICACION_HUANCAYO = "11";
	public static String VAR_GLB_UBICACION_CUZCO = "12";
	public static String VAR_GLB_UBICACION_AREQUIPA = "13";
	public static String VAR_GLB_UBICACION_IQUITOS= "14";
	
	
}
