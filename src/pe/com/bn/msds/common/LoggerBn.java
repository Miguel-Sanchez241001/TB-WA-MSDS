package pe.com.bn.msds.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import pe.com.bn.msds.common.Constant;
import pe.com.bn.msds.common.ParamConfig;

public class LoggerBn {

	private String FQCN = "";

	protected LoggerBn(String name) {
		this.FQCN = name;
	}

	public static LoggerBn getInstance(String name) {
		LoggerBn hub = new LoggerBn(name);
		return hub;
	}

	public void error(Exception e, String codigoError, String mensaje) {
		if (e != null)
			e.printStackTrace();

		if (!(isPrintLogErr("ERROR")))
			return;
		try {

			String formato = "[ERROR/" + FQCN;

			formato = formato + "]-";
			formato = formato + mensaje + "|";
			String tracer = "";

			if (e != null) {
				formato = formato + "MESSAGE:" + e.getMessage();
				if (e.getStackTrace() != null) {
					for (int i = 0; i < e.getStackTrace().length; ++i) {
						tracer = tracer + e.getStackTrace()[i].toString()
								+ "\n";
					}

				}
				formato = formato + "|TRACE:\n" + tracer;
			}
			grabarLog(formato, "E");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void debug(byte[] msg, String nivel) {
		if (!(isPrintLogDebug("DEBUG", nivel)))
			return;
		try {
			if (msg != null && msg.length > 0) {

				String formato = "[DEBUG/" + this.FQCN;

				formato = formato + "]-TRAMA:" + new String(msg);
				grabarLog(formato, "D");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void debug(String mensaje, String nivel) {
		if (!(isPrintLogDebug("DEBUG", nivel)))
			return;
		try {
			String formato = "[DEBUG/" + this.FQCN;

			formato = formato + "]-" + mensaje;
			grabarLog(formato, "D");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void debug(String metdo, String mensaje, String nivel) {

		if (!isPrintLogDebug("DEBUG", nivel))
			return;

		try {
			String blk = "";
			for (int f = 0; f < 25 - metdo.length(); f++) {
				blk += " ";
			}
			String formato = "[DBG/" + metdo + blk + "]\t";
			formato = formato + mensaje;
			grabarLog(formato, "D");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void tracer(String mensaje) {
		if (!(isPrintLogTracer("TRACER")))
			return;
		try {
			String formato = "[TRACER/" + this.FQCN;

			formato = formato + "]-" + mensaje;
			grabarLog(formato, "T");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	boolean isPrintLogDebug(String tipo, String nivel) {
		try {
			if ((nivel.equals(Constant.LOGGER_DEBUG_NIVEL_1))
					&& (tipo.equals("DEBUG"))
					&& (ParamConfig.getLoggerPrintDebugNivel_1().equals("true")))
				return true;

			if ((nivel.equals(Constant.LOGGER_DEBUG_NIVEL_2))
					&& (tipo.equals("DEBUG"))
					&& (ParamConfig.getLoggerPrintDebugNivel_2().equals("true")))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	boolean isPrintLogTracer(String tipo) {
		try {
			if ((tipo.equals("TRACER"))
					&& (ParamConfig.getLoggerPrintTracer().equals("true")))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	boolean isPrintLogErr(String tipo) {
		try {
			if ((tipo.equals("ERROR"))
					&& (ParamConfig.getLoggerPrintError().equals("true")))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	void grabarLog(String mensaje, String tipo) {
		String timestamp1 = "";
		String strMensaje = "";
		Calendar fecha = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH.mm.ss");
		timestamp1 = formato.format(fecha.getTime());
		strMensaje = "[" + timestamp1 + "]" + mensaje;

		try {
			if (tipo.equals("E")) {
				if (ParamConfig.getErrLoggerFile().equals("true")) {
					saveFile(ParamConfig.getErrLoggerPath(), strMensaje);
				}
				if (ParamConfig.getErrLoggerConsole().equals("true")) {
					System.out.println(strMensaje);
				}
			}
			if (tipo.equals("D")) {
				if (ParamConfig.getProcLoggerFile().equals("true")) {
					saveFile(ParamConfig.getProcLoggerPath(), strMensaje);
				}
				if (ParamConfig.getProcLoggerConsole().equals("true"))
					System.out.println(strMensaje);
			}
			if (tipo.equals("T")) {
				if (ParamConfig.getTracerLoggerFile().equals("true")) {
					saveFile(ParamConfig.getTracerLoggerPath(), strMensaje);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void saveFile(String path, String mensaje) {
		BufferedWriter bw = null;
		File fichero = null;
		try {
			fichero = new File(path);
			bw = new BufferedWriter(new FileWriter(fichero, true));
			bw.write(mensaje + "\n");

			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
					bw = null;
				} catch (Exception localException1) {
				}
			if (fichero != null)
				try {
					fichero = null;
				} catch (Exception e) {
				}
		}
	}

}
