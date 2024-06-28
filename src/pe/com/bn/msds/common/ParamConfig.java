package pe.com.bn.msds.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import pe.com.bn.msds.common.Constant;

public class ParamConfig {
	
   public static String getLoggerPrintError() {
	      try {
	        if ((Constant.BN_LOGGER_PRINT_ERROR == null) || (Constant.BN_LOGGER_PRINT_ERROR.equals(""))) {
	          ResourceBundle rb = ResourceBundle.getBundle("parametro");
	          Constant.BN_LOGGER_PRINT_ERROR = rb.getString("msds.logger.error.print");
	          if ((Constant.BN_LOGGER_PRINT_ERROR == null) || (Constant.BN_LOGGER_PRINT_ERROR.equals("")))
	            throw new Exception("Parametro no definido msds.logger.error.print");
	        }
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	      return Constant.BN_LOGGER_PRINT_ERROR;
    }

   public static String getLoggerPrintTracer() {
	      try {
	        if ((Constant.BN_LOGGER_PRINT_TRACER == null) || (Constant.BN_LOGGER_PRINT_TRACER.equals(""))) {
	          ResourceBundle rb = ResourceBundle.getBundle("parametro");
	          Constant.BN_LOGGER_PRINT_TRACER = rb.getString("msds.logger.tracer.print");
	          if ((Constant.BN_LOGGER_PRINT_TRACER == null) || (Constant.BN_LOGGER_PRINT_TRACER.equals("")))
	            throw new Exception("Parametro no definido msds.logger.tracer.print");
	        }
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	      return Constant.BN_LOGGER_PRINT_TRACER;
    }

    public static String getLoggerPrintDebugNivel_1() {
	      try {
	        if ((Constant.BN_LOGGER_PRINT_DEBUG_NIVEL_1 == null) || (Constant.BN_LOGGER_PRINT_DEBUG_NIVEL_1.equals(""))) {
	          ResourceBundle rb = ResourceBundle.getBundle("parametro");
	          Constant.BN_LOGGER_PRINT_DEBUG_NIVEL_1 = rb.getString("msds.logger.debug.print.nivel1");
	          if ((Constant.BN_LOGGER_PRINT_DEBUG_NIVEL_1 == null) || (Constant.BN_LOGGER_PRINT_DEBUG_NIVEL_1.equals("")))
	            throw new Exception("Parametro no definido msds.logger.debug.print.nivel1");
	        }
	      } catch (Exception e) {
	    	 e.printStackTrace();
	      }
	      return Constant.BN_LOGGER_PRINT_DEBUG_NIVEL_1;
    }

	public static String getLoggerPrintDebugNivel_2() {
	      try {
	        if ((Constant.BN_LOGGER_PRINT_DEBUG_NIVEL_2 == null) || (Constant.BN_LOGGER_PRINT_DEBUG_NIVEL_2.equals(""))) {
	        	ResourceBundle rb = ResourceBundle.getBundle("parametro");
	          Constant.BN_LOGGER_PRINT_DEBUG_NIVEL_2 = rb.getString("msds.logger.debug.print.nivel2");
	          if ((Constant.BN_LOGGER_PRINT_DEBUG_NIVEL_2 == null) || (Constant.BN_LOGGER_PRINT_DEBUG_NIVEL_2.equals("")))
	            throw new Exception("Parametro no definido msds.logger.debug.print.nivel2");
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	      return Constant.BN_LOGGER_PRINT_DEBUG_NIVEL_2;
	}

	public static String getProcLoggerFile()
	    {
	      try {
	        if ((Constant.BN_PROC_LOGGER_FILE == null) || (Constant.BN_PROC_LOGGER_FILE.equals(""))) {
	        	ResourceBundle rb = ResourceBundle.getBundle("parametro");
	          Constant.BN_PROC_LOGGER_FILE = rb.getString("msds.logger.debug.file.flag");
	          if ((Constant.BN_PROC_LOGGER_FILE == null) || (Constant.BN_PROC_LOGGER_FILE.equals("")))
	            throw new Exception("Parametro no definido msds.logger.debug.file.flag");
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	      return Constant.BN_PROC_LOGGER_FILE;
	 }

	 public static String getProcLoggerConsole() {
	      try {
	        if ((Constant.BN_PROC_LOGGER_CONSOLE == null) || (Constant.BN_PROC_LOGGER_CONSOLE.equals(""))) {
	        	ResourceBundle rb = ResourceBundle.getBundle("parametro");
	          Constant.BN_PROC_LOGGER_CONSOLE = rb.getString("msds.logger.debug.console.flag");
	          if ((Constant.BN_PROC_LOGGER_CONSOLE == null) || (Constant.BN_PROC_LOGGER_CONSOLE.equals("")))
	            throw new Exception("Parametro no definido msds.logger.debug.console.flag");
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	      return Constant.BN_PROC_LOGGER_CONSOLE;
	 }

	public static String getProcLoggerPath() {
		try {
			
			if (Constant.BN_PROC_LOGGER_PATH != null && !Constant.BN_PROC_LOGGER_PATH.equals("")) {
				
				Calendar fecha = Calendar.getInstance();
				SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
				String timestamp1 = formato.format(fecha.getTime());
				ResourceBundle rb = ResourceBundle.getBundle("parametro");
				
				String path = rb.getString("msds.logger.debug.file.path") + "/msds_info_" + timestamp1 + ".log";
				
				if (!Constant.BN_PROC_LOGGER_PATH.equals(path)) {
					Constant.BN_PROC_LOGGER_PATH = path;
				}
			}
			
			if ((Constant.BN_PROC_LOGGER_PATH == null) || (Constant.BN_PROC_LOGGER_PATH.equals(""))) {
				
				Calendar fecha = Calendar.getInstance();
				SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
				String timestamp1 = formato.format(fecha.getTime());
				ResourceBundle rb = ResourceBundle.getBundle("parametro");
				
				String file1 = rb.getString("msds.logger.debug.file.path") + "/msds_info_" + timestamp1 + ".log";
				
				Constant.BN_PROC_LOGGER_PATH = file1;
				
				if ((Constant.BN_PROC_LOGGER_PATH == null) || (Constant.BN_PROC_LOGGER_PATH.equals("")))
					throw new Exception("Parametro no definido msds.logger.debug.file.path");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Constant.BN_PROC_LOGGER_PATH;
	}

	public static String getTracerLoggerPath() {
		try {
			
			if (Constant.BN_TRACER_LOGGER_PATH != null && !Constant.BN_TRACER_LOGGER_PATH.equals("")) {
				
				Calendar fecha = Calendar.getInstance();
				SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
				String timestamp1 = formato.format(fecha.getTime());
				ResourceBundle rb = ResourceBundle.getBundle("parametro");
				
				String path = rb.getString("msds.logger.tracer.file.path") + "/msds_tracer_" + timestamp1 + ".log";
				
				if (!Constant.BN_TRACER_LOGGER_PATH.equals(path)) {
					Constant.BN_TRACER_LOGGER_PATH = path;
				}
			}
			
			if ((Constant.BN_TRACER_LOGGER_PATH == null) || (Constant.BN_TRACER_LOGGER_PATH.equals(""))) {
				
				Calendar fecha = Calendar.getInstance();
				SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
				String timestamp1 = formato.format(fecha.getTime());
				ResourceBundle rb = ResourceBundle.getBundle("parametro");
				
				String file1 = rb.getString("msds.logger.tracer.file.path") + "/msds_tracer_" + timestamp1 + ".log";
				
				Constant.BN_TRACER_LOGGER_PATH = file1;
				
				if ((Constant.BN_TRACER_LOGGER_PATH == null) || (Constant.BN_TRACER_LOGGER_PATH.equals("")))
					throw new Exception("Parametro no definido msds.logger.tracer.file.path");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Constant.BN_TRACER_LOGGER_PATH;
	}

	 public static String getErrLoggerFile() {
	      try {
	        if ((Constant.BN_ERR_LOGGER_FILE == null) || (Constant.BN_ERR_LOGGER_FILE.equals(""))) {
	          ResourceBundle rb = ResourceBundle.getBundle("parametro");
	          Constant.BN_ERR_LOGGER_FILE = rb.getString("msds.logger.error.file.flag");
	          if ((Constant.BN_ERR_LOGGER_FILE == null) || (Constant.BN_ERR_LOGGER_FILE.equals("")))
	            throw new Exception("Parametro no definido msds.logger.error.file.flag");
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	      return Constant.BN_ERR_LOGGER_FILE;
	 }

	 public static String getTracerLoggerFile() {
	      try {
	        if ((Constant.BN_TRACER_LOGGER_FILE == null) || (Constant.BN_TRACER_LOGGER_FILE.equals(""))) {
	          ResourceBundle rb = ResourceBundle.getBundle("parametro");
	          Constant.BN_TRACER_LOGGER_FILE = rb.getString("msds.logger.tracer.file.flag");
	          if ((Constant.BN_TRACER_LOGGER_FILE == null) || (Constant.BN_TRACER_LOGGER_FILE.equals("")))
	            throw new Exception("Parametro no definido msds.logger.tracer.file.flag");
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	      return Constant.BN_TRACER_LOGGER_FILE;
	 }

	 public static String getErrLoggerConsole()
	    {
	      try {
	        if ((Constant.BN_ERR_LOGGER_CONSOLE == null) || (Constant.BN_ERR_LOGGER_CONSOLE.equals(""))) {
	          ResourceBundle rb = ResourceBundle.getBundle("parametro");
	          Constant.BN_ERR_LOGGER_CONSOLE = rb.getString("msds.logger.error.console.flag");
	          if ((Constant.BN_ERR_LOGGER_CONSOLE == null) || (Constant.BN_ERR_LOGGER_CONSOLE.equals("")))
	            throw new Exception("Parametro no definido msds.logger.error.console.flag");
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	      return Constant.BN_ERR_LOGGER_CONSOLE;
	 }

	public static String getErrLoggerPath() {
		
		try {
			
			if (Constant.BN_ERR_LOGGER_PATH != null && !Constant.BN_ERR_LOGGER_PATH.equals("")) {

				Calendar fecha = Calendar.getInstance();
				SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
				String timestamp1 = formato.format(fecha.getTime());
				ResourceBundle rb = ResourceBundle.getBundle("parametro");
				
				String path = rb.getString("msds.logger.error.file.path") + "/msds_err_" + timestamp1 + ".log";
				
				if (!Constant.BN_ERR_LOGGER_PATH.equals(path)) {
					Constant.BN_ERR_LOGGER_PATH = path;
				}
			}
			
			if ((Constant.BN_ERR_LOGGER_PATH == null) || (Constant.BN_ERR_LOGGER_PATH.equals(""))) {
				
				Calendar fecha = Calendar.getInstance();
				SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
				String timestamp1 = formato.format(fecha.getTime());
				ResourceBundle rb = ResourceBundle.getBundle("parametro");
				
				String file1 = rb.getString("msds.logger.error.file.path") + "/msds_err_" + timestamp1 + ".log";
				
				Constant.BN_ERR_LOGGER_PATH = file1;
				
				if ((Constant.BN_ERR_LOGGER_PATH == null) || (Constant.BN_ERR_LOGGER_PATH.equals("")))
					throw new Exception("Parametro no definido msds.logger.error.file.path");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Constant.BN_ERR_LOGGER_PATH;
	}
	
  }
