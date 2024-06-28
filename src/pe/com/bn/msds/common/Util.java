package pe.com.bn.msds.common;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import pe.com.bn.msds.common.Constant;
import pe.com.bn.msds.common.DatosSesion;
import pe.com.bn.msds.common.LoggerBn;
import pe.com.bn.msds.common.Row;
import pe.com.bn.msds.common.Util;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class Util {

	private static LoggerBn log3 = LoggerBn.getInstance(Util.class.getName());

	public static String formatNumber(double valor, int tipoformat, int decimal) {

		String varFormat = "";

		switch (tipoformat) {
		case 0: // Entero
			varFormat = "0";
			break;
		case 1: // Decimal sin coma demiles
			switch (decimal) {
			case 0:
				varFormat = "#0";
				break;
			case 1:
				varFormat = "#0.0";
				break;
			case 2:
				varFormat = "#0.00";
				break;
			case 3:
				varFormat = "#0.000";
				break;
			default:
				varFormat = "#";
				break;
			}
			break;
		case 2: // Decimal con coma demiles
			switch (decimal) {
			case 0:
				varFormat = "#,###,##0";
				break;
			case 1:
				varFormat = "#,###,##0.0";
				break;
			case 2:
				varFormat = "#,###,##0.00";
				break;
			case 3:
				varFormat = "#,###,##0.000";
				break;
			case 4:
				varFormat = "# ### ##0.00";
				break;
			default:
				varFormat = "#,###,###";
				break;
			}
			break;
		default:
			varFormat = "#";
			break;
		}

		Locale locale = new Locale("es", "PE");
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(',');

		DecimalFormat df = new DecimalFormat(varFormat, symbols);
		String formatted = df.format(valor);
		return formatted;
	}

	public static boolean getValidarCampoMontoMeta(String campo) {

		if (!isDouble(campo))
			return false;
		double cant = Double.parseDouble(campo);
		if (cant >= 0)
			return true;
		return false;

	}

	public static boolean getValidarCampoCantMeta(String campo) {

		if (!isEntero(campo))
			return false;
		int cant = Integer.parseInt(campo);
		if (cant >= 0)
			return true;
		return false;

	}

	public static boolean getValidarCampoValue(String campo, int longitud) {

		if (campo.trim().length() > 0 && campo.length() <= longitud)
			return true;
		return false;

	}

	public static boolean getValidarCampoOficina(String campo) {

		if (!isEntero(campo))
			return false;
		int oficina = Integer.parseInt(campo);
		if (oficina >= 0)
			return true;
		return false;

	}

	public static boolean getValidarCampoPeriodo(String campo) {

		if (!isEntero(campo))
			return false;
		if (campo.length() == 6)
			return true;
		return false;

	}

	public static boolean getValidarCampoProducto(String campo) {

		String productos = "PR|SD|SO|SP|SS|TC|HP|EH|";
		if (productos.indexOf(campo) >= 0)
			return true;
		return false;

	}

	public static boolean getValidarCampoCuenta(String campo) {

		// String productos = "PR|SD|SO|SP|SS|TC|HP|EH|";
		if (campo.length() == 16)
			return true;
		return false;

	}

	public static boolean getValidarCampoOfi(String campo) {

		// String productos = "PR|SD|SO|SP|SS|TC|HP|EH|";
		if (campo.length() == 4)
			return true;
		return false;

	}

	public static boolean getValidarCampoFecha(String campo) {

		// String productos = "PR|SD|SO|SP|SS|TC|HP|EH|";
		if (campo.length() == 8)
			return true;
		return false;

	}

	public static boolean getValidarCampoFechaVacia(String campo) {

		if (campo.length() == 0 || campo.length() == 8)
			return true;
		return false;

	}

	public static boolean getValidarEstado(String campo) {

		// String productos = "PR|SD|SO|SP|SS|TC|HP|EH|";
		if (campo.length() == 1)
			return true;
		return false;

	}

	public static boolean getValidarFechaMod(String campo) {

		if (campo.length() == 0)
			return true;
		return false;

	}

	public static boolean isEntero(String s) {
		return s.matches("\\d+");
	}

	public static boolean isDouble(String s) {
		return s.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)");
	}

	public static String getValorCeldaExcelString(HSSFCell celda) {

		String value = "";

		if (celda != null) {
			switch (celda.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				value = celda.getStringCellValue();
				break;
			}
		}
		return value;
	}

	public static String getValorCeldaExcelNumeric(HSSFCell celda, int decimal) {

		String value = "";
		double valueNum = 0;
		if (celda != null) {
			switch (celda.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				valueNum = celda.getNumericCellValue();
				value = formatNumber(valueNum, Constant.NUM_DECIMAL_SIN_COMA_1,
						decimal);
				break;
			case HSSFCell.CELL_TYPE_STRING:
				value = celda.getStringCellValue();
				break;
			}
		}
		return value;
	}

	public static String getFormatSemanaLine(int dia) {

		if (dia == 7 || dia == 14 || dia == 21)
			return "3";
		return "";
	}

	public static String getFormatSemana(int semana) {

		try {
			if (semana == 1)
				return "1S.";
			if (semana == 2)
				return "2S.";
			if (semana == 3)
				return "3S.";
			if (semana == 4)
				return "4S.";
			if (semana == 5)
				return "5S.";
			if (semana == 6)
				return "6S.";

		} catch (Exception e) {
			log3.error(e, "", "");
		}

		return "";

	}

	public static String colorFilaTabla(int reg) {
		String result = "";
		if (reg % 2 == 0) {
			result = "even2";
		} else {
			result = "odd2";
		}
		return result;
	}

	public static String formatNumero(double numero, int decimal) {
		String result = "";
		String formatted = formatNumber(numero, Constant.NUM_ENTERO_0, decimal);
		result = formatted;

		return result;

	}

	public static String ColorFlagAvance(double porcentaje, String estilo) {
		String result = "";
		if (porcentaje < 90) {
			result = "tableFormatRojo" + estilo;
		} else {
			if (porcentaje >= 90 && porcentaje < 100) {
				result = "tableFormatAmarillo" + estilo;
			} else {
				result = "tableFormatVerde" + estilo;
			}
		}
		return result;
	}

	/*
	 * public static String ColorFlagAvance(double porcentaje,String estilo){
	 * String result = ""; if(porcentaje<=50){ result =
	 * "tableFormatRojo"+estilo; }else{ if(porcentaje<=90){ result =
	 * "tableFormatAzul"+estilo; }else{ result = "tableFormatVerde"+estilo; } }
	 * return result; }
	 */

	public static String labelFlagAvance5(double valor, int decimal) {
		String result = "<span ";

		if (valor <= 0)
			result += " class='tableFormatRojo1' ";
		String formatted = formatNumber(valor, Constant.NUM_DECIMAL_CON_COMA_2,
				decimal);
		result += ">" + formatted + "</span>";

		return result;

	}

	public static String labelFlagAvance(double avance, int decimal,
			String addText) {
		String result = "";

		if (avance < 0) {
			result = "";
		} else {
			String formatted = formatNumber(avance,
					Constant.NUM_DECIMAL_CON_COMA_2, decimal);
			result = formatted + addText;
		}
		return result;

	}

	public static String labelFlagAvance(double avance, int decimal,
			String addText, double meta, double ejec) {
		String result = "";
		if (meta <= 0 && ejec <= 0) {
			result = "";
			// result = "&nbsp;?&nbsp;";
			return result;
		}
		if (meta <= 0 && ejec != 0) {
			result = "";
			// result = "&nbsp;?&nbsp;";
			return result;
		}
		if (avance < 0) {
			result = "";
		} else {
			String formatted = formatNumber(avance,
					Constant.NUM_DECIMAL_CON_COMA_2, decimal);
			result = formatted + addText;
		}
		return result;

	}

	public static String labelFlagAvance3(double avance, int tipoformat,
			String addText, double meta, double ejec) {
		String span = "";
		if (meta <= 0 && ejec <= 0) {
			return span;
		}
		if (avance < 0) {
			span = "<span class='tableDetalleContent1'>RESTA</span>";
		} else {
			if (avance == 0) {
				span = "<span class='tableDetalleContent1'>CUMPLIO</span>";
			} else {
				span = "<span class='tableDetalleContent1'>SUPERO</span>";
			}
		}

		span = span + addText;
		return span;

	}

	public static String labelFlagAvance4(double ejec, double meta) {
		String result1 = "";
		String span = "";
		String icon = "";
		double avance = 0;
		if (meta <= 0 && ejec <= 0) {
			span = "<span class='tableFormatNegro1 tableDetalleContent6' >";
			icon = "<i class='forma-icon2 fa fa-exclamation-circle' ></i>";
			result1 = span + icon + "</span>";
			return result1;
		}

		avance = ejec - meta;
		if (avance < 0) {
			span = "<span class='tableFormatRojo1 tableDetalleContent6' >";
			icon = "<i class='forma-icon2 glyphicon glyphicon-download' ></i>";
			result1 = span + icon + "</span>";
		} else {
			if (avance == 0) {
				span = "<span class='tableFormatVerde1 tableDetalleContent6'>";
				icon = "<i class='forma-icon2 glyphicon glyphicon-ok' ></i>";
				result1 = span + icon + "</span>";
			} else {
				span = "<span class='tableFormatVerde1 tableDetalleContent6'>";
				icon = "<i class='forma-icon2 glyphicon glyphicon-upload' ></i>";
				result1 = span + icon + "</span>";
			}
		}

		return result1;

	}

	public static String labelFlagAvance2(double avance1, int decimal,
			String addText, double meta, double ejec) {
		String result1 = "";
		String span = "";
		String icon = "";
		double avance = avance1;

		if (meta <= 0 && ejec <= 0) {
			span = "<span class='tableFormatNegro1 tableDetalleContent4' >";
			icon = "<i class='forma-icon fa fa-exclamation-circle' ></i>";
			result1 = span + icon + "?</span>";
			return result1;
		}

		if (avance < 0) {
			span = "<span class='tableFormatRojo1 tableDetalleContent4' >";
			icon = "<i class='forma-icon glyphicon glyphicon-download' ></i>";
			avance = (-1) * avance;
		} else {
			if (avance == 0) {
				span = "<span class='tableFormatVerde1 tableDetalleContent4'>";
				icon = "<i class='forma-icon glyphicon glyphicon-ok' ></i>";
			} else {
				span = "<span class='tableFormatVerde1 tableDetalleContent4'>";
				icon = "<i class='forma-icon glyphicon glyphicon-upload' ></i>";
			}
		}

		String formatted = formatNumber(avance,
				Constant.NUM_DECIMAL_CON_COMA_2, decimal);
		result1 = span + icon + formatted + addText + "</span>";
		return result1;

	}

	public static double getRestaDouble(double meta, double ejec) {

		double newNum = 0.0;
		newNum = Util.redondeoDouble((ejec - meta),
				Constant.NUM_DIGITOS_DECIMAL_0);
		return newNum;

	}

	public static double getPorcentajeDouble(double meta, double ejec) {

		double newNum = 0.0;
		if (meta <= 0) {
			if (ejec <= 0) {
				newNum = Util.redondeoDouble(-1.0,
						Constant.NUM_DIGITOS_DECIMAL_2);
			} else {
				newNum = Util.redondeoDouble(100.0,
						Constant.NUM_DIGITOS_DECIMAL_2);
			}
		} else {
			newNum = Util.redondeoDouble((ejec / meta) * 100,
					Constant.NUM_DIGITOS_DECIMAL_2);
		}
		return newNum;
	}

	public static double redondeoDouble(double d, int places) {

		double newNum = 0.0;
		newNum = Math.round(d * Math.pow(10, (double) places))
				/ Math.pow(10, (double) places);

		return newNum;
	}

	public static boolean validContentText(String input1, String input2) {

		if (input1 == null || input1.trim().equals(""))
			return false;
		if (input2 == null || input2.trim().equals(""))
			return false;
		String myOpt = rpad2(input1, 2) + "S";
		return input2.contains(myOpt);

	}

	public static boolean validContentElement(String input1, String input2) {

		if (input1 == null || input1.trim().equals(""))
			return false;
		if (input2 == null || input2.trim().equals(""))
			return false;

		return input1.contains(input2);

	}

	public static String formatText(String info) {
		if (info != null) {
			return info.replaceAll("|", "");
		}
		return "";
	}

	public static String formatText(BigDecimal info) {

		if (info != null) {
			return info.toString();
		}
		return "";
	}

	public static String formatText(Date info) {

		if (info != null) {
			return getFormattedDateHora(info);
		}
		return "";
	}

	public static String formatString(byte[] info) {

		String dato = "";
		try {
			if (info == null || info.length == 0)
				return "";
			dato = new String(info, "ISO-8859-1");
			dato = dato.trim();
		} catch (Exception e) {
			log3.error(e, "", "");
		}
		return dato;
	}

	public static String formatUtfUpper(String info) {

		String dato = "";
		try {
			if (info == null || info.trim().equals(""))
				return "";
			dato = new String(info.getBytes("ISO-8859-1"), "UTF-8");
			dato = dato.trim().toUpperCase();
		} catch (Exception e) {
			log3.error(e, "", "");
		}
		return dato;
	}

	public static String formatUtfNanCase(String info) {

		String dato = "";
		try {
			if (info == null || info.trim().equals(""))
				return "";
			dato = new String(info.getBytes("ISO-8859-1"), "UTF-8");
			dato = dato.trim();
		} catch (Exception e) {
			log3.error(e, "", "");
		}
		return dato;
	}

	public static String tamanoArchivo(long bytes) {
		String result = "";
		if (bytes < 1024) {
			result = bytes + " bytes";
		} else if (bytes / 1024 < 1024) {
			result = new BigDecimal(bytes / 1024).setScale(2) + " KB";
		} else if (bytes / 1024 / 1024 < 1024) {
			result = new BigDecimal(bytes / 1024 / 1024).setScale(2) + " MB";
		} else if (bytes / 1024 / 1024 / 1024 < 1024) {
			result = new BigDecimal(bytes / 1024 / 1024 / 1024).setScale(2)
					+ " GB";
		} else if (bytes / 1024 / 1024 / 1024 / 1024 < 1024) {
			result = new BigDecimal(bytes / 1024 / 1024 / 1024 / 1024)
					.setScale(2) + " TB";
		}
		return result;
	}

	public static String getValMax(String value, int lengthMax) {
		if (value != null && value.length() > lengthMax) {
			value = value.substring(0, lengthMax);
		}
		return value;
	}

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	public static String concat(StackTraceElement[] array, String encadenador) {
		String result = "";
		try {
			if (array != null) {
				for (int i = 0; i < array.length; i++) {
					result += ((i == 0) ? "" : encadenador)
							+ parseString(array[i].toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String exceptionToString(Throwable exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);

		return sw.toString();
	}

	public static String getFormattedDate(Date fecha) {

		String cadenaFecha = null;
		if (fecha != null) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			cadenaFecha = formato.format(fecha);
		}
		return cadenaFecha;
	}

	public static String getFormattedDateHora(Date fecha) {
		String cadenaFecha = null;
		if (fecha != null) {
			SimpleDateFormat formato = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm:ss");
			cadenaFecha = formato.format(fecha);
		}
		return cadenaFecha;
	}

	public static final String getTimestamp() {
		Calendar fecha = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(fecha.getTime());
	}

	public static Timestamp getFormattedScriptDateIni(Date fecha) {
		Timestamp cadenaFecha = null;
		if (fecha != null) {
			Calendar date = new GregorianCalendar();
			date.setTime(fecha);
			date.set(Calendar.HOUR_OF_DAY, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MILLISECOND, 0);
			cadenaFecha = new Timestamp(date.getTime().getTime());
		}
		return cadenaFecha;
	}

	public static Timestamp getFormattedScriptDateFin(Date fecha) {
		Timestamp cadenaFecha = null;
		if (fecha != null) {
			Calendar date = new GregorianCalendar();
			date.setTime(fecha);
			date.set(Calendar.HOUR_OF_DAY, 23);
			date.set(Calendar.MINUTE, 59);
			date.set(Calendar.SECOND, 59);
			date.set(Calendar.MILLISECOND, 0);
			cadenaFecha = new Timestamp(date.getTime().getTime());
		}
		return cadenaFecha;
	}

	public static String getFormattedScriptDate2(Date fecha) {
		String cadenaFecha = null;
		if (fecha != null) {
			SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
			cadenaFecha = formato.format(fecha);
		}
		return cadenaFecha;
	}

	public static String getFormattedScriptDate3(HSSFCell celda) {
		String cadenaFecha = null;
		if (celda != null) {
			SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
			cadenaFecha = formato.format(celda);
		}
		return cadenaFecha;
	}

	public static String getFormattedHora(Date fecha) {
		String cadenaFecha = null;
		if (fecha != null) {
			SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
			cadenaFecha = formato.format(fecha);
		}
		return cadenaFecha;
	}

	public static final String getTimestamp(String format) {
		Calendar fecha = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat(format);
		return formato.format(fecha.getTime());
	}

	public static final Date getDate() {
		Calendar fecha = Calendar.getInstance();
		return fecha.getTime();

	}

	public static final Date addHourToDate(Date fecha, int hora) {

		Date increment = DateUtils.addHours(fecha, hora);
		return increment;

	}

	public static final Timestamp getConvertTimestamp(Date fechaValue) {

		return (fechaValue != null) ? new java.sql.Timestamp(
				fechaValue.getTime()) : null;

	}

	public static final Date getConvertDate(Timestamp timestampValue) {

		return (timestampValue != null) ? new Date(timestampValue.getTime())
				: null;

	}

	public static final Date getConvertDate(java.sql.Date fecha2) {

		Date fechaNew = null;
		if (fecha2 != null) {
			fechaNew = new java.util.Date(fecha2.getTime());
		}
		return fechaNew;

	}

	public static final Date getStringToDate(String fechaHora) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = null;
		date = formatter.parse(fechaHora);
		return date;

	}

	public byte[] generarFileToPdf(String cadenaHtml) {

		ByteArrayOutputStream outputStream = null;
		byte[] bytes = null;
		try {
			// now write the PDF content to the output stream
			outputStream = new ByteArrayOutputStream();
			writePdf(outputStream, cadenaHtml);
			bytes = outputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * Writes the content of a PDF file (using iText API) to the
	 * {@link OutputStream}.
	 * 
	 * @param outputStream
	 *            {@link OutputStream}.
	 * @throws Exception
	 */
	public void writePdf(OutputStream outputStream, String cadenaHtml)
			throws Exception {
		Document document = new Document();

		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		document.open();

		InputStream is = new ByteArrayInputStream(cadenaHtml.getBytes());
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);

		document.close();
	}

	public static String enmascararTrama(String value) {
		if (value == null)
			return null;

		value = value.replaceAll("�", "n");
		value = value.replaceAll("�", "N");
		value = value.replaceAll("�", "A");
		value = value.replaceAll("�", "A");
		value = value.replaceAll("�", "A");
		value = value.replaceAll("�", "A");
		value = value.replaceAll("�", "E");
		value = value.replaceAll("�", "E");
		value = value.replaceAll("�", "E");
		value = value.replaceAll("�", "E");
		value = value.replaceAll("�", "I");
		value = value.replaceAll("�", "I");
		value = value.replaceAll("�", "I");
		value = value.replaceAll("�", "I");
		value = value.replaceAll("�", "O");
		value = value.replaceAll("�", "O");
		value = value.replaceAll("�", "O");
		value = value.replaceAll("�", "O");
		value = value.replaceAll("�", "U");
		value = value.replaceAll("�", "U");
		value = value.replaceAll("�", "U");
		value = value.replaceAll("�", "U");

		return value;
	}

	public static String repeat(String s, int n) {
		if (s == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			sb.append(s);
		}
		return sb.toString();
	}

	public static String enmascararTramaRecepcion(String value) {
		if (value == null)
			return null;

		value = value.replaceAll("#", "�");

		return value;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static String getValueFromArray(int index, int lenghArr,
			String[] arrRow) {

		if (index < lenghArr) {
			return arrRow[index];
		}
		return null;
	}

	public static DatosSesion getIdUsuario(HttpServletRequest request) {
		DatosSesion datosSesion = (DatosSesion) request.getSession()
				.getAttribute("datosSesion");
		return datosSesion;
	}

	public static Row getRowIndex(List<Row> lsRow, String codVal) {
		if (lsRow != null) {
			for (Iterator iterator = lsRow.iterator(); iterator.hasNext();) {
				Row row = (Row) iterator.next();
				if (row.getOption().equals(codVal))
					return row;
			}
		}
		return null;
	}

	public static String rpad2(String str, int cant) {
		String conts = "";
		String contr = "";

		if (str.length() == cant) {
			return str;
		}
		// 10 5
		if (str.length() > cant) {
			for (int i = 0; i < cant; i++) {
				conts = str.substring(cant - i);
			}
			contr = conts;
		}

		if (str.length() < cant) {
			for (int i = 0; i < (cant - str.length()); i++) {
				conts = conts + "0";
			}
			contr = conts + str;
		}

		return contr;
	}

	public static String getStringVal(String tabla, String codVal) {
		return "";
	}

	public static String openFileToString(byte[] _bytes) {
		String file_string = "";

		for (int i = 0; i < _bytes.length; i++) {
			file_string += (char) _bytes[i];
		}

		return file_string;
	}

	public static final Short parseShort(String value) {
		Short result = new Short((short) 0);
		try {

			if (value == null || value.trim().equals("")) {
				return result;
			}

			String expresionRegular = "[0-9]+";
			boolean b = Pattern.matches(expresionRegular, value);

			if (!b)
				return result;

			value = value.trim();
			result = new Short(value);
		} catch (Exception e) {
			return 0;
		}
		return result;
	}

	public static final Short parseShort(BigDecimal value) {
		Short result = new Short((short) 0);
		try {

			if (value == null) {
				return result;
			}

			result = new Short(value.shortValue());
		} catch (Exception e) {
			return 0;
		}
		return result;
	}

	public static final Integer parseInt(String value) {
		Integer result = new Integer(0);
		try {

			if (value == null || value.trim().equals("")) {
				return result;
			}

			String expresionRegular = "[0-9]+";
			boolean b = Pattern.matches(expresionRegular, value);

			if (!b)
				return result;

			value = value.trim();
			result = new Integer(value);
		} catch (Exception e) {
			return 0;
		}
		return result;
	}

	public static final String parseString(String value) {
		String result = "";
		try {
			if (value == null)
				value = "";
			value = value.trim();
			result = value;
		} catch (Exception e) {
			// log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		}
		return result;
	}

	public static String subString(String str, int cant) {

		if (str == null || str.trim().equals("")) {
			return "";
		}

		if (str.length() <= cant) {
			return str.trim();
		}

		return str.substring(0, cant);

	}

	public static String lpad(String str, int cant) {
		String conts = "";
		String contr = "";

		if (str.length() == cant) {
			return str;
		}
		// 10 5
		if (str.length() > cant) {
			for (int i = 0; i < cant; i++) {
				conts = conts + " ";
			}
			contr = conts;
		}

		if (str.length() < cant) {
			for (int i = 0; i < (cant - str.length()); i++) {
				conts = conts + " ";
			}
			contr = str + conts;
		}

		return contr;
	}

	public static void escapeObject(Object o) {

		try {
			if (o == null) {
				log3.debug("OBjeto Nulo", Constant.LOGGER_DEBUG_NIVEL_2);
				return;
			}
			StringBuffer buf = new StringBuffer();

			Class clase = o.getClass();
			buf.append("\nClase:" + clase.getName() + "\n");
			Method[] metodos = clase.getDeclaredMethods();

			String valor = "";
			Method metodo = null;

			for (int i = 0; i < metodos.length; i++) {
				metodo = metodos[i];
				String nombreMetodo = metodo.getName();
				if (nombreMetodo.startsWith("get")) {
					Object respuestaMetodo = metodo.invoke(o, null);
					if (respuestaMetodo != null) {
						// valor =
						// StringEscapeUtils.escapeHtml(respuestaMetodo.toString());
						// metodo.getReturnType().equals(String.class)
						valor = respuestaMetodo.toString();
						buf.append("\tMetodo [" + nombreMetodo + "] \t Tipo ["
								+ metodo.getReturnType() + "] \t  Valor: ["
								+ valor + "] \n");
					} else {
						buf.append("\tMetodo [" + nombreMetodo + "] \t Tipo ["
								+ metodo.getReturnType()
								+ "] \t  Valor: [?] \n");
					}
				}
			}
			log3.debug(buf.toString(), Constant.LOGGER_DEBUG_NIVEL_2);
		} catch (Exception e) {
			log3.error(e, "", "");
		}

	}

	public static String infoPeriodoFormat(String value) {

		String str = "";
		if (value != null && value.length() == 6) {
			String mes = value.substring(4, 6);
			String anio = value.substring(2, 4);
			str = mes + "/" + anio;
		}
		return str;

	}

	public static String infoPeriodoFormatDD(String value) {

		String str = "";
		if (value != null && value.length() == 8) {
			String dia = value.substring(6, 8);
			String mes = value.substring(4, 6);
			String anio = value.substring(2, 4);

			str = dia + "/" + mes + "/" + anio;
		}
		return str;

	}

	public static String infoMesLetras(String value1) {

		StringBuffer str = new StringBuffer();
		if (value1 != null && value1.length() == 2) {
			if (value1.equals("01"))
				str.append("Enero");
			if (value1.equals("02"))
				str.append("Febrero");
			if (value1.equals("03"))
				str.append("Marzo");
			if (value1.equals("04"))
				str.append("Abril");
			if (value1.equals("05"))
				str.append("Mayo");
			if (value1.equals("06"))
				str.append("Junio");
			if (value1.equals("07"))
				str.append("Julio");
			if (value1.equals("08"))
				str.append("Agosto");
			if (value1.equals("09"))
				str.append("Setiembre");
			if (value1.equals("10"))
				str.append("Octubre");
			if (value1.equals("11"))
				str.append("Noviembre");
			if (value1.equals("12"))
				str.append("Diciembre");
		}
		return str.toString();

	}

	public static String infoPeriodoLetras(String value) {

		StringBuffer str = new StringBuffer();
		if (value != null && value.length() == 6) {
			String mes = value.substring(4, 6);
			String anio = value.substring(0, 4);
			if (mes.equals("01"))
				str.append("Ene");
			if (mes.equals("02"))
				str.append("Feb");
			if (mes.equals("03"))
				str.append("Mar");
			if (mes.equals("04"))
				str.append("Abr");
			if (mes.equals("05"))
				str.append("May");
			if (mes.equals("06"))
				str.append("Jun");
			if (mes.equals("07"))
				str.append("Jul");
			if (mes.equals("08"))
				str.append("Ago");
			if (mes.equals("09"))
				str.append("Set");
			if (mes.equals("10"))
				str.append("Oct");
			if (mes.equals("11"))
				str.append("Nov");
			if (mes.equals("12"))
				str.append("Dic");

			str.append(" " + anio);

		}
		return str.toString();

	}

	public static void textoMayuscula(Object o, boolean formato)
			throws Exception {

		Class clase = o.getClass();
		Method[] metodos = clase.getDeclaredMethods();
		String valor = "";
		Method metodo = null;

		for (int i = 0; i < metodos.length; i++) {
			metodo = metodos[i];
			String nombreMetodo = metodo.getName();
			if (nombreMetodo.startsWith("get")) {
				Object respuestaMetodo = metodo.invoke(o, null);
				if (respuestaMetodo != null
						&& metodo.getReturnType().equals(String.class)) {
					if (respuestaMetodo.toString() != null) {
						valor = respuestaMetodo.toString().toUpperCase();
						if (formato)
							valor = Util.formatUtfUpper(valor);
						String stringSet = "s" + nombreMetodo.substring(1);
						Method methodset = clase.getMethod(stringSet,
								String.class);
						methodset.invoke(o, valor);
					}
				}
			}
		}
	}

	public static String getArrayToString(byte[] info) {

		String str2 = "";
		try {
			if (info != null && info.length > 0) {
				str2 = new String(info, "ISO-8859-1");
			}
		} catch (Exception e) {
			log3.error(e, "", "");
		}
		return str2;
	}

	public static byte[] getStringToArray(String info) {

		byte[] mensaje = null;
		try {
			if (info != null && !info.trim().equals("")) {
				mensaje = info.getBytes("ISO-8859-1");
			}
		} catch (Exception e) {
			log3.error(e, "", "");
		}
		return mensaje;
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		if (date1 == null || date2 == null)
			return 0;
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	static public String customFormat(String patron, BigDecimal value) {

		DecimalFormat myFormatter = new DecimalFormat(patron);
		String output = myFormatter.format(value);
		return output;

	}

	public static boolean validContentText2(String input1, String input2) {

		if (input1 == null || input1.trim().equals(""))
			return false;
		if (input2 == null || input2.trim().equals(""))
			return false;
		String myOpt = rpad2(input1, 2);
		return input2.contains(myOpt);

	}

	public static final Calendar getStringToDateFormat(String fechaHora) {

		try {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

			Date date = formatter.parse(fechaHora);

			Calendar calendar = Calendar.getInstance();

			calendar.setTime(date);

			return calendar;

		} catch (Exception e) {

			return null;

		}

	}

	public static final String[] getContarDomingos(String fecha) {

		Calendar myDate = Util.getStringToDateFormat(fecha);
		int dow = myDate.get(Calendar.DAY_OF_WEEK);
		String[] dia = { "", "" };
		switch (dow) {
		case Calendar.SUNDAY:
			dia[0] = "0";
			dia[1] = "D";
			return dia;
		case Calendar.MONDAY:
			dia[0] = "1";
			dia[1] = "L";
			return dia;
		case Calendar.TUESDAY:
			dia[0] = "1";
			dia[1] = "M";
			return dia;
		case Calendar.WEDNESDAY:
			dia[0] = "1";
			dia[1] = "M";
			return dia;
		case Calendar.THURSDAY:
			dia[0] = "1";
			dia[1] = "J";
			return dia;
		case Calendar.FRIDAY:
			dia[0] = "1";
			dia[1] = "V";
			return dia;
		case Calendar.SATURDAY:
			dia[0] = "1";
			dia[1] = "S";
			return dia;
		}
		return dia;

	}
}
