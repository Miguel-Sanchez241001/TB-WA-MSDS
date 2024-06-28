package pe.com.bn.msds.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constante {
	
	public static final String ERR_LOGICA_NEGOCIO  			= "5000";
	
//	public static ParamSimm BN_PARAM_SIMM= null;
	public static final String ASUNTO_TCSOLICITUD ="SOLICICITUD DE TARJETA DE CREDITO";
	public static final int REQUE_SOLICITD = 171;
	public static final String ASUNTO_TCCANCELACION ="SOLICICITUD DE PAGO TOTAL DE PRESTAMO MULTIRED";
	public static final int REQUE_CANCELACION = 172;
	public static final String ASUNTO_PRESTAMO ="DOCUMENTOS CONTRACTUALES DEL PR�STAMO MULTIRED";
	public static final int REQUE_PRESTAMO = 173;
	
	public final static String FIRMA1	    	= 			"0001";	
	public final static String FIRMA2	    	= 			"0002";	
	
	public final static String NOMBRE1	    	= 			"0003";	
	public final static String NOMBRE2	    	= 			"0004";	

	//public static final String APP_ID_DESA								= "SECM";
	public static final String APP_ID_DESA								= "SECM";
	public static final String CONST_ID_DESA							= "00";
	//public static final String APP_ID_DESA2								= "SECM1";
	public static final String APP_ID_DESA2								= "SECM";
	public static final String CONST_ID_DESA2							= "00";
	public static final String CONST_ERROR_DESAHIS						= "Servicio de claves Inactivo";
	public final static String gBN_CONST_MENSAJE_PARAMETROS				= "PARAMETROS DEL SISTEMA VACIO";
	public final static String gBN_CONST_MENSAJE_ESTADOCARGA01			= "LA CARGA DE DATOS NO SE COMPLETO";
	public final static String gBN_CONST_MENSAJE_ESTADOCARGA00			= "NO SE CARGARON LOS DATOS";
	public final static String gBN_CONST_MENSAJE_NORESULTADO			= "NO SE ENCONTRARON RESULTADOS";
	
	//public final static int gBN_DB_SECM								= 1;
	public final static int gBN_DB_SECM								= 1;
	//public final static int gBN_DB_SECM_ORACLE						= 2;
	public final static int gBN_DB_SECM_SQL			= 2;
	public final static int gBN_DB_SBS							    = 3;	
	
	public final static int gBN_PARAM_DB_APP					    = 1; //1 DATA COM - 2 ORACLE
	
	public final static String gBN_TABLA_CLIENTE_FILTRO_BUSQUEDA	= "1";
	public final static String gBN_TABLA_CLIENTE_TIPO_DOCUMENTO		= "2";
	
	public final static String gBN_ITEM_FILTRO_BUSQUEDA_1_IDC					= "2";
	
//	reportes
	public final static String gBN_ITEM_FILTRO_BUSQUEDA_1_AGENCIA				= "1";
	public final static String gBN_ITEM_FILTRO_BUSQUEDA_2_REGION	 		    = "2";
	public final static String gBN_ITEM_FILTRO_BUSQUEDA_3_UNIDADEJECUTORA  			= "3";
	public final static String gBN_ITEM_FILTRO_BUSQUEDA_4_DEPARTAMENTO				= "4";
	public final static String gBN_ITEM_FILTRO_BUSQUEDA_5_PROVINCIA		= "5";
	public final static String gBN_ITEM_FILTRO_BUSQUEDA_6_DISTRITO		= "6";

	
	public final static String gBN_ITEM_IDC_DNI_TIPO_DOCUMENTO					= "1";
	//public final static String gBN_ITEM_IDC_CPN_TIPO_DOCUMENTO					= "2";
	//public final static String gBN_ITEM_IDC_SWIFT_TIPO_DOCUMENTO				= "3";
	public final static String gBN_ITEM_IDC_CE_TIPO_DOCUMENTO					= "4";
	public final static String gBN_ITEM_IDC_PASAP_TIPO_DOCUMENTO				= "5";
	public final static String gBN_ITEM_IDC_RUC_TIPO_DOCUMENTO					= "6";
	//public final static String gBN_ITEM_IDC_BP_TIPO_DOCUMENTO					= "7";
	//public final static String gBN_ITEM_IDC_BPN_TIPO_DOCUMENTO					= "8";
	//public final static String gBN_ITEM_IDC_BPJ_TIPO_DOCUMENTO					= "9";
	
	public final static String gBN_FETCH_FIRST_ROWS_ONLY						= "100";
	
	public final static String gBN_TIPO_PERSONA_NATURAL						    = "1";
	public final static String gBN_TIPO_PERSONA_JURIDICA						= "3";
	
	public final static String gBN_AHORROS_MN_ACTIVA							= "1";
	public final static String gBN_AHORROS_MN_CERRADA							= "2";
	public final static String gBN_AHORROS_MN_ANULADA							= "3";
	public final static String gBN_AHORROS_MN_BLOQUEADA							= "5";
	public final static String gBN_AHORROS_MN_VIGILADA							= "6";
	public final static String gBN_AHORROS_MN_PARALIZADA						= "7";
	
	public final static String gBN_MENSAJE_NO_PRESENTA							= "No Presenta";
	public final static String gBN_MENSAJE_NO_DISPONIBLE						= "No Disponible";
	
	public  static HashMap CONST_LISNATURALEZA									=null;
	public  static HashMap CONST_LISMONEDA										=null;
	public  static HashMap CONST_LISTIPOCUENTA									=null;
	public  static HashMap CONST_LISESTADOCUENTA								=null;
	public  static HashMap CONST_LISUBCTAMAYOR									=null;
	public  static HashMap CONST_LISOFICINA										=null;
	public  static HashMap CONST_LISPRODUCTO									=null;
	public  static HashMap CONST_LISSITUACIONCUENTA 							=null;
	public  static HashMap CONST_LISESTADOCONTABLE	 							=null;
	public  static HashMap CONST_TIPOCAMBIO			 							=null;
	//public  static HashMap CONST_LISCUENTAS			 							=null;
	
	public final static String CONST_COD_RPTA_NATURALEZA_NO_ENCONTRADO			= "N001";
    public final static String  CONST_DES_RPTA_NATURALEZA_NO_ENCONTRADO			= "NATURALEZA NO ENCONTRADO";
    
    public final static String CONST_COD_RPTA_MONEDA_NO_ENCONTRADO				= "M001";
    public final static String  CONST_DES_RPTA_MONEDA_NO_ENCONTRADO				= "MONEDA NO ENCONTRADO";
    
    public final static String CONST_COD_RPTA_TIPO_CUENTA_NO_ENCONTRADO			= "T001";
    public final static String  CONST_DES_RPTA_TIPO_CUENTA_NO_ENCONTRADO		= "TIPO CUENTA NO ENCONTRADO";
	
    public final static String CONST_COD_RPTA_ESTADO_CUENTA_NO_ENCONTRADO		= "E001";
    public final static String  CONST_DES_RPTA_ESTADO_CUENTA_NO_ENCONTRADO		= "ESTADO NO ENCONTRADO";
    
    public final static String CONST_COD_RPTA_SUBCTA_NO_ENCONTRADO				= "5001";
    public final static String  CONST_DES_RPTA_SUBCTA_ENCONTRADO				= "SUB CUENTA NO ENCONTRADA";
    
    public final static String CONST_COD_RPTA_OFIC_NO_ENCONTRADO				= "O001";
    public final static String CONST_DES_RPTA_OFIC_ENCONTRADO					= "OFICINA NO ENCONTRADA";
    
    public final static String CONST_COD_RPTA_PROD_NO_ENCONTRADO				= "P001";
    public final static String CONST_DES_RPTA_PROD_ENCONTRADO					= "PRODUCTO NO ENCONTRADO";
    
    public final static String CONST_COD_RPTA_SITUACION_NO_ENCONTRADO			= "S001";
    public final static String CONST_DES_RPTA_SITUACION_ENCONTRADO				= "SITUACION NO ENCONTRADA";
    
    public final static String CONST_COD_RPTA_ESTADO_CONTABLE_NO_ENCONTRADO		= "C001";
    public final static String CONST_DES_RPTA_ESTADO_CONTABLE_ENCONTRADO		= "ESTADO CONTABLE NO ENCONTRADA";
    
    public final static String CONST_COD_RPTA_CUENTA_NO_ENCONTRADO				= "F001";
    public final static String CONST_DES_RPTA_CUENTA_ENCONTRADO					= "CUENTA NO ENCONTRADA";
    
    public final static String CONST_COD_RPTA_CLIENTE_RESTRINGIDO				= "CLI1";
    public final static String CONST_DES_RPTA_CLIENTE_RESTRINGIDO				= "CLIENTE RESTRINGIDO";
    
    public final static String CONTROLLER_MENSAJE_OPERACION_DES_ALERTA			= "Existe un Defecto en la Operacion: ";
    public final static String CONTROLLER_MENSAJE_OPERACION_COD_OK				= "desMsjOk";
    public final static String CONTROLLER_MENSAJE_OPERACION_COD_ALERTA			= "desMsjAl";
    public final static String CONTROLLER_MENSAJE_OPERACION_COD_HIPOTECARIO		= "desMsjAlHIPOTECARIO";
    public final static String CONTROLLER_MENSAJE_OPERACION_COD_ALERTA_CICS		= "";
    public final static String CONTROLLER_MENSAJE_OPERACION_COD_ALERTA_RENIEC	= "desMsjAlRECA";
    
    
    
    public final static String CONTROLLER_MENSAJE_DBDATACOM 					= "No hay conexi�n con la base de datos";
    public final static String CONTROLLER_MENSAJE_TRAMA							= "Problemas recepci�n de la trama";
    public final static String CONTROLLER_MENSAJE_ORA							= "No se pudo establecer la conexi�n con ORACLE, error al grabar datos";
    public final static String CONTROLLER_MENSAJE_NO_EXISTE                     = "El DNI ingresado no existe";
    public final static String CONTROLLER_MENSAJE_EXCEPCION						= "Se ha generado una excepci�n";
    public final static String CONTROLLER_MENSAJE_EXCEPCION_RENIEC				= "Problemas recepci�n trama RENIEC";
    public final static String CONTROLLER_MENSAJE_EXCEPCION_LISTAS				= "Error al llenar la lista de cuentas";
    public final static String CONTROLLER_MENSAJE_EXCEPCION_SBS					= "Error en la conexi�n con la BD SBS";
    public static String CONTROLLER_MENSAJE_DBDATACOM_LOGIN				= null;
    
    public final static String CONTROLLER_MENSAJE_REPORTE_VACIO					= "NO SE ENCONTRARON RESULTADOS";
    public final static String CONTROLLER_MENSAJE_REPORTE_LLENO				    = "Haga Clic en Abrir para Confirmar la Exportaci�n";
    public final static String CONTROLLER_MENSAJE_REPORTE_MUESTRA_DATOS		    = "Eliga una Opci�n";
    public final static String CONTROLLER_MENSAJE_REPORTE_FECHA_NO_VALIDA       = "NO SE ENCONTRARON RESULTADOS"; 
    public final static String CONTROLLER_MENSAJE_REPORTE				    = "";
    
    public final static String LOG_TIPO_BITACORA_01			= "001";
    public final static String LOG_TIPO_BITACORA_02			= "002";
    public final static String LOG_TIPO_BITACORA_03			= "003";
    public final static String LOG_TIPO_BITACORA_04			= "004";
    public final static String LOG_TIPO_BITACORA_05			= "005";
    public final static String LOG_TIPO_BITACORA_06			= "006";
    
    public final static String LOG_DES_BITACORA_01			= "Consulta de ingreso a la aplicaci�n";
    public final static String LOG_DES_BITACORA_02			= "B�squeda del cliente";
    public final static String LOG_DES_BITACORA_03			= "Consulta de cliente seleccionado";
    public final static String LOG_DES_BITACORA_04			= "Consulta del cierre";
    public final static String LOG_DES_BITACORA_05			= "Consulta de Reportes";
    public final static String LOG_DES_BITACORA_06			= "Consulta de Indicadores";
    
    public final static String NIVEL_CONSULTA_00 = "0";
    public final static String NIVEL_CONSULTA_01 = "1";
    public final static String NIVEL_CONSULTA_02 = "2";
    public final static String NIVEL_CONSULTA_03 = "3";
    public final static String DNI_EN_CONSULTA = "";
    
    
    public final static String MODULO_OPCION_ACTIVAS = "0001";
    public final static String MODULO_OPCION_PASIVAS = "0002";
    public final static String MODULO_OPCION_GARANTIAS = "0003";
    public final static String MODULO_OPCION_AVALES = "0004";
    public final static String MODULO_OPCION_LINEAS = "0005";
    public final static String MODULO_OPCION_OTROS = "0006";
    public final static String MODULO_OPCION_PROVISIONES = "0007";
    public final static String MODULO_OPCION_DATOS_GEN = "0008";
    public final static String MODULO_OPCION_BUSQUEDA = "0009";
    public final static String MODULO_OPCION_POSICION_CLIENTE = "0010";
    public final static String MODULO_OPCION_CIERRE_SESION = "0011";
    public final static String MODULO_OPCION_SBS = "0012";
    public final static String MODULO_OPCION_REPORTES = "0013";
    public final static String MODULO_OPCION_INDICADORES = "0014";
    
    public static String BN_PARAM_BANCO = null;
    public static String BN_PARAM_AGENCIA = null;
    public static String BN_PARAM_CAJERO = null;
    public static String BN_PARAM_CANAL = null;
    public static String BN_PARAM_APLICACION = null;
    public static String BN_PARAM_ESTRUCTURA = null;
    public static String BN_PARAM_COD_TRX = null;
    public static String BN_PARAM_OPERACION = null;
    public static String BN_PARAM_ESTADO_INI_PROC = null;
    public static String BN_PARAM_FORMATO = null;
    public static String BN_PARAM_MONEDA = null;
    public static String BN_PARAM_TIPO_MENSAJE = null;
    public static String BN_PARAM_TIMEOUT = null;
    public static String BN_PARAM_PURGAR = null;
    public static String BN_PARAM_FILLER = null;
    
  
    //public static ConnectionPool BN_POOL_DATACOM_SECM = null;
  

    //public static ParamConJDNI BN_PARAM_SECM_JDNI = null;
 

    
    public static String ERR_CONECTION_DATACOM = "3000";
    public static String ERR_OUT_CONECTION_BDUC = "3001";
    public static String ERR_OUT_MEMORY  ="3002";
    public static String ERR_INTERRUPTED="3003";
    
    public static String CONST_PROCESO_OK = "00000";
    
    public static String BN_USUARIO_APP = null;
    
    //PARA GENERACION DE REPORTES
    public static String BN_VISTA_REPORTE = null; //RIESGOS CREDITOS ALINEAMIENTO
    public static String BN_PROCESO_1800 = null;  //EXITOSO RECHAZADO NOREALIZADO
    public static String BN_PROCESAMIENTO= null;  //EXITOSO RECHAZADO     
    
    //parametros del LOg
    
	//Registro Parametro
	public static final String LOGGER_DEBUG_NIVEL_1 = "1";
	public static final String LOGGER_DEBUG_NIVEL_2 = "2";
	public static String BN_LOGGER_PRINT_ERROR = null;
	public static String BN_LOGGER_PRINT_DEBUG_NIVEL_1 = null;
	public static String BN_LOGGER_PRINT_DEBUG_NIVEL_2 = null;
	
	public static String BN_ERR_LOGGER_FILE = null; 
	public static String BN_ERR_LOGGER_CONSOLE = null;
	public static String BN_ERR_LOGGER_PATH = null;
	
	public static String BN_PROC_LOGGER_FILE = null;
	public static String BN_PROC_LOGGER_CONSOLE = null;
	public static String BN_PROC_LOGGER_PATH = null;
	
	// Version SECM
	//public final static String gBN_VERSION_SECM = "1.2";
	public final static String gBN_VERSION_SECM = "1.1";
	public final static int gBN_INDICADOR_MESES = 11;
	
	// constantes de tablas
	
	
	// TABLAS 
	// Tabla BNMSDSF03_APLICACIONES
    public static final String TABLE_BNMSDSF03_APLICACIONES = "BNMSDSF03_APLICACIONES";
    public static final String BNMSDSF03_F03_CODIGO = "F03_CODIGO";
    public static final String BNMSDSF03_F03_NOMBRE = "F03_NOMBRE";
    public static final String BNMSDSF03_F03_DESCRIPCION = "F03_DESCRIPCION";

    // Tabla BNMSDSF04_AREAUSUARIA
    public static final String TABLE_BNMSDSF04_AREAUSUARIA = "BNMSDSF04_AREAUSUARIA";
    public static final String BNMSDSF04_F04_AREAUSUARIA = "F04_AREAUSUARIA";

    // Tabla BNMSDSF05_AREARESPONSABLE
    public static final String TABLE_BNMSDSF05_AREARESPONSABLE = "BNMSDSF05_AREARESPONSABLE";
    public static final String BNMSDSF05_F05_AREARESPONSABLE = "F05_AREARESPONSABLE";

    // Tabla BNMSDSF06_PROCESONEGOCIO
    public static final String TABLE_BNMSDSF06_PROCESONEGOCIO = "BNMSDSF06_PROCESONEGOCIO";
    public static final String BNMSDSF06_F06_PROCESONEGOCIO = "F06_PROCESONEGOCIO";

    // Tabla BNMSDSF07_TIPOAPLICACION
    public static final String TABLE_BNMSDSF07_TIPOAPLICACION = "BNMSDSF07_TIPOAPLICACION";
    public static final String BNMSDSF07_F07_TIPOAPLICACION = "F07_TIPOAPLICACION";

    // Tabla BNMSDSF08_TIEMPOATENCION
    public static final String TABLE_BNMSDSF08_TIEMPOATENCION = "BNMSDSF08_TIEMPOATENCION";
    public static final String BNMSDSF08_F08_TIEMPOATENCION = "F08_TIEMPOATENCION";

    // Tabla BNMSDSF09_VERSION
    public static final String TABLE_BNMSDSF09_VERSION = "BNMSDSF09_VERSION";
    public static final String BNMSDSF09_F09_VERSION = "F09_VERSION";

    // Tabla BNMSDSF10_LENGUAJE
    public static final String TABLE_BNMSDSF10_LENGUAJE = "BNMSDSF10_LENGUAJE";
    public static final String BNMSDSF10_F10_LENGUAJE = "F10_LENGUAJE";
	
	
 // Tabla BNMSDSF03_APLICACIONES
    public static final String TABLE_BNMSDSF11_BASEDATOS = "BNMSDSF11_BASEDATOS";
    public static final String BNMSDSF11_F11_BASEDATOS = "F11_BASEDATOS";
    
 // Tabla BNMSDSF12_RESPONSABLEDESA
    public static final String TABLE_BNMSDSF12_RESPONSABLEDESA = "BNMSDSF12_RESPONSABLEDESA";
    public static final String BNMSDSF12_F12_RESPONSABLEDESA = "F12_RESPONSABLEDESA";
    
    
 // Tabla BNMSDSF13_FABRICA
    public static final String TABLE_BNMSDSF13_FABRICA = "BNMSDSF13_FABRICA";
    public static final String BNMSDSF13_F13_FABRICA = "F13_FABRICA";
    // Tabla BNMSDSF14_AUDITORIA
    public static final String TABLE_BNMSDSF14_AUDITORIA = "BNMSDSF14_AUDITORIA";
    public static final String BNMSDSF14_F14_FECHA = "F14_FECHA";
    public static final String BNMSDSF14_F14_HORA = "F14_HORA";
    public static final String BNMSDSF14_F14_USUARIO = "F14_USUARIO";
    public static final String BNMSDSF14_F14_ACCION = "F14_ACCION";
    public static final String BNMSDSF14_F14_APLICACION = "F14_APLICACION";
    public static final String BNMSDSF14_F14_DATO1 = "F14_DATO1";
    public static final String BNMSDSF14_F14_DATO2 = "F14_DATO2";
    public static final String BNMSDSF14_F14_DATO3 = "F14_DATO3";
  
  public static Map<String, List<String>> getTableColumnsMap() {
        Map<String, List<String>> tableColumnsMap = new HashMap<String, List<String>>();

        tableColumnsMap.put(TABLE_BNMSDSF03_APLICACIONES, Arrays.asList(
            BNMSDSF03_F03_CODIGO,
            BNMSDSF03_F03_NOMBRE,
            BNMSDSF03_F03_DESCRIPCION
        ));

        tableColumnsMap.put(TABLE_BNMSDSF04_AREAUSUARIA, Arrays.asList(
            BNMSDSF04_F04_AREAUSUARIA
        ));

        tableColumnsMap.put(TABLE_BNMSDSF05_AREARESPONSABLE, Arrays.asList(
            BNMSDSF05_F05_AREARESPONSABLE
        ));

        tableColumnsMap.put(TABLE_BNMSDSF06_PROCESONEGOCIO, Arrays.asList(
            BNMSDSF06_F06_PROCESONEGOCIO
        ));

        tableColumnsMap.put(TABLE_BNMSDSF07_TIPOAPLICACION, Arrays.asList(
            BNMSDSF07_F07_TIPOAPLICACION
        ));

        tableColumnsMap.put(TABLE_BNMSDSF08_TIEMPOATENCION, Arrays.asList(
            BNMSDSF08_F08_TIEMPOATENCION
        ));

        tableColumnsMap.put(TABLE_BNMSDSF09_VERSION, Arrays.asList(
            BNMSDSF09_F09_VERSION
        ));

        tableColumnsMap.put(TABLE_BNMSDSF10_LENGUAJE, Arrays.asList(
            BNMSDSF10_F10_LENGUAJE
        ));
        
     
        tableColumnsMap.put(TABLE_BNMSDSF11_BASEDATOS, Arrays.asList(
        		BNMSDSF11_F11_BASEDATOS
            ));
        tableColumnsMap.put(TABLE_BNMSDSF12_RESPONSABLEDESA, Arrays.asList(
        		BNMSDSF12_F12_RESPONSABLEDESA
            ));
        tableColumnsMap.put(TABLE_BNMSDSF13_FABRICA, Arrays.asList(
        		BNMSDSF13_F13_FABRICA
            ));
        tableColumnsMap.put( TABLE_BNMSDSF14_AUDITORIA, Arrays.asList(
                 BNMSDSF14_F14_FECHA,
                 BNMSDSF14_F14_HORA,
                 BNMSDSF14_F14_USUARIO,
                 BNMSDSF14_F14_ACCION,
                 BNMSDSF14_F14_APLICACION,
                 BNMSDSF14_F14_DATO1,
                 BNMSDSF14_F14_DATO2,
                 BNMSDSF14_F14_DATO3
            ));
        return tableColumnsMap;
    }
	
	
	
	
    public static Map<Integer, String> getTableNamesMap() {
        Map<Integer, String> tableNamesMap = new HashMap<Integer, String>();

        tableNamesMap.put(1, TABLE_BNMSDSF03_APLICACIONES);
        tableNamesMap.put(2, TABLE_BNMSDSF04_AREAUSUARIA);
        tableNamesMap.put(3, TABLE_BNMSDSF05_AREARESPONSABLE);
        tableNamesMap.put(4, TABLE_BNMSDSF06_PROCESONEGOCIO);
        tableNamesMap.put(5, TABLE_BNMSDSF07_TIPOAPLICACION);
        tableNamesMap.put(6, TABLE_BNMSDSF08_TIEMPOATENCION);
        tableNamesMap.put(7, TABLE_BNMSDSF09_VERSION);
        tableNamesMap.put(8, TABLE_BNMSDSF10_LENGUAJE);
        tableNamesMap.put(9, TABLE_BNMSDSF11_BASEDATOS);
        tableNamesMap.put(10, TABLE_BNMSDSF12_RESPONSABLEDESA);
        tableNamesMap.put(11, TABLE_BNMSDSF13_FABRICA);
        tableNamesMap.put(12, TABLE_BNMSDSF14_AUDITORIA);

        return tableNamesMap;
    }
    
 // CONSTANTES REPORTE EXCEL 

    public static final  List<String> TITULOS_COLUMN = Arrays.asList(
    		"N.",
    	    "COD",
    	    "NOMBRE",
    	    "DESCRIPCION FUNCIONAL",
    	    "NIVEL",
    	    "AREA USUARIA",
    	    "AREA RESPONSABLE",
    	    "AREA PROCESO",
    	    "TIPO SERVICIO",
    	    "PLATAFORMA",
    	    "RTO",
    	    "MTDP",
    	    "RPO",
    	    "MTDL",
    	    "MBCO",
    	    "VERSION",
    	    "VERSION DESARROLLO",
    	    "URL DE SARROLLO",
    	    "URL CERTIFICACION",
    	    "URL PRODUCCION",
    	    "URL GIT",
    	    "LENGUAJE",
    	    "BASE DATOS",
    	    "PERSONA RESPONSABLE",
    	    "DESARROLLO REALIZADO",
    	    "SERVIDORES",
    	    "REQUISITOS",
    	    "FECHA",
    	    "COMENTARIOS"
    	);
    public static final String CONST_TITULO_REPORTE= "Inventario de Reporte Sistemas y Aplicaciones";
    public static final String CONST_VALOR_NULLO  = " ";
    public static final String CONST_FECHA_FORMAT  = "dd/MM/yyyy"; 
    

 // CONSTANTES AUDITORIA 
    public static final String CONST_ACCION_LOG_INSERT = " Registro:[PRM_VALOR] TABLA:[PRM_TABLA]";
    public static final String CONST_ACCION_LOG_CARGA = " CargoArchivo:[PRM_VALOR] TABLA:[PRM_TABLA]";
    public static final String CONST_ACCION_LOG_ACTUALIZACION = " Actualizo:[PRM_VALOR] TABLA:[PRM_TABLA]";
    public static final String CONST_ACCION_LOG_CONSULTA = " Consulto:[PRM_VALOR] TABLA:[PRM_TABLA]";
    public static final String CONST_ACCION_LOG_USER= "PRM_USER";
    public static final String CONST_ACCION_LOG_VALOR= "PRM_VALOR";
    public static final String CONST_ACCION_LOG_TABLA= "PRM_TABLA";
}
