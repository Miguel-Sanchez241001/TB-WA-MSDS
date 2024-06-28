package pe.com.bn.msds.common;

import java.math.BigDecimal;
import java.text.CharacterIterator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import pe.com.bn.comp.cryto.service.BNClaveSegura;
import pe.com.bn.msds.dao.config.DatosCompConfig;


public class Funciones{
	
	private static LoggerBn log3 = LoggerBn.getInstance(Funciones.class.getName());
	
	static public float redondear_numero_decimales1(float nNumero, int nDecimal)  

	 {  
		return (float) (Math.round(nNumero*Math.pow(10,nDecimal))/Math.pow(10,nDecimal));
	 }
	


	public static String eliminarCerosAlaIzquierda(String texto){ 
		String valor = ""; 
		for( int i=0; i < texto.length(); i++){
			if ( texto.charAt(i) != '0' ){ 
				valor = texto.substring(i); 
				break; 
			}
		}
			 
		return valor; 
	}
 	
 	public static String formatearMonto11(Float monto){
 		//System.out.println("Valores float: "+monto);
		DecimalFormat formateador=null;
		try{
		monto=Funciones.redondear_numero_decimales1(monto,2);
		if (monto < 0){
			monto = monto * (-1);
		}
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		formateador = new DecimalFormat("###,###,###,###,###,##0.00");
		formateador.setDecimalFormatSymbols(simbolos);
		}catch(Exception e){
			//log3.error(e, "", e.getMessage());
		}
		return formateador.format(monto);
		
	}
	
	public static BigDecimal tramaToBigDecimal(String numeroTrama, int decimales){			
		if(numeroTrama==null)
			return new BigDecimal(0.00);
		if(numeroTrama.length()<15)
			numeroTrama = setearCaracterLeft(numeroTrama,'0',15-numeroTrama.length());
		int enteros = 15 - decimales; 
		numeroTrama = numeroTrama.substring(0,enteros)+"."+numeroTrama.substring(enteros);
		BigDecimal bd = new BigDecimal(numeroTrama);
		bd.setScale(decimales);
		return bd;
	}
	
	public static BigDecimal tramaToBigDecimal11(String numeroTrama){		
		
		if(numeroTrama==null)
			return new BigDecimal(0.00);
		if(numeroTrama.length()<15)
			numeroTrama = Funciones.setearCaracterLeft(numeroTrama,'0',15-numeroTrama.length());
		numeroTrama = numeroTrama.substring(0,13)+"."+numeroTrama.substring(13);
		
		BigDecimal bd = new BigDecimal(numeroTrama);
		bd.setScale(3);
		
		return bd;
	}
	
	


	public static BigDecimal tramaToBigDecimal1(String numeroTrama){		
		
		if(numeroTrama==null)
			return new BigDecimal(0.00);
		if(numeroTrama.length()<15)
			numeroTrama = Funciones.setearCaracterLeft(numeroTrama,'0',15-numeroTrama.length());
		numeroTrama = numeroTrama.substring(0,13)+"."+numeroTrama.substring(13);
		
		BigDecimal bd = new BigDecimal(numeroTrama);
		bd.setScale(3);
		
		return bd;
	}
	
	
	
	
	
	public static String setearCaracterLeft(String cadena,char c,int num){
		String temp = "";
		if (cadena==null)
			cadena = "";
		
		for (int i=0;i<num;i++)
			temp+=c;
		temp+=cadena;
		
		return temp;
	}
	
	
	
	
	static public char[][] LIST_ACENTOS = new char [][]{{'�','A'},{'�','A'},
		{'�','E'},{'�','E'},
		{'�','I'},{'�','I'},
		{'�','O'},{'�','O'},
		{'�','U'},{'�','U'}};
	
	static public String MODO_VALIDATE_DIR = "DIR";
	
	public static String getRutaApp(){
		ResourceBundle rb = ResourceBundle.getBundle("parametro");
	    String filePath = rb.getString("bn.logger.file.path.app");
	    return filePath;
	}
	
	public static String getRutaErr(){
		ResourceBundle rb = ResourceBundle.getBundle("parametro");
	    String filePath = rb.getString("bn.logger.file.path.err");
	    return filePath;
	}
	
	

	public static String formatearMesFecha (String fecha){
		try{
			if(fecha==null || fecha.trim().equals("")){
				fecha="";
			}else{
				SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
				Date fechaDate = new Date();   
				fecha = formateador.format(fechaDate);   
			}
			return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String formatearMesAnioFecha (String fecha){
		try{
			if(fecha==null || fecha.trim().equals("")){
				fecha="";
			}else{ 
				SimpleDateFormat formateadorAnt = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaDate = null;
				fechaDate = (Date) formateadorAnt.parse(fecha);
				
				SimpleDateFormat formateador = new SimpleDateFormat("MMM - yy", new Locale("es_ES"));
				fecha =	formateador.format(fechaDate);   
			}
			return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	 
	public static String izquierda (String stringToPad, String padder, int size) {
		if (padder.length() == 0) {
			return stringToPad;
		}
		
		StringBuffer sb;
		StringCharacterIterator sci;
	
		sb = new StringBuffer(size);
		sci  = new StringCharacterIterator(padder);
	
	    while (sb.length() < (size - stringToPad.length())) {
			for (char ch = sci.first(); ch != CharacterIterator.DONE ; ch = sci.next()) {
				if (sb.length() <  size - stringToPad.length()) {
					sb.insert(  sb.length(),String.valueOf(ch));
				}
			}
		}
		return sb.append(stringToPad).toString();
	}
	
		public static String derecha (String stringToPad, String padder, int size) {
		if (padder.length() == 0) {
			return stringToPad;
		}
		
		StringBuffer sb;
		StringCharacterIterator sci;
		
		sb = new StringBuffer(stringToPad);
		sci  = new StringCharacterIterator(padder);
	
	    while (sb.length() < size) {
			for (char ch = sci.first(); ch != CharacterIterator.DONE ; ch = sci.next()) {
				if (sb.length() < size) {
					sb.append(String.valueOf(ch));
				}
			}
		}
		return sb.toString();
	}	
	
	static public String validar_texto(String cadena){		
		String cadenaEvaluar="";
		try{			
			for(int i=0;i<cadena.length();i++){
				if(cadena.charAt(i)=='�' || cadena.charAt(i)=='�' || cadena.charAt(i)=='�' || cadena.charAt(i)==164 || cadena.charAt(i)==165)
					cadenaEvaluar+="#";
				else
					cadenaEvaluar+=cadena.charAt(i);
			}			
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return cadenaEvaluar;
	}
	
	static public String validar_texto_reves(String cadena){		
		String cadenaEvaluar="";
		try{			
			for(int i=0;i<cadena.length();i++){
				if(cadena.charAt(i)=='#')
					cadenaEvaluar+="�";
				else
					cadenaEvaluar+=cadena.charAt(i);
			}			
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return cadenaEvaluar;
	}
	
	public static String ajustar_cadena(String valor, int tamano){
		if( valor ==null) valor = "";
		return (valor.length() > tamano)?valor.substring(0, tamano):valor;
	}
	
	public static Long parseLong(String value){
		Long result = new Long(0);
		if( value!=null ){
			try{
				result = new Long(value.trim());
			}catch(Exception e){
				log3.error(e, "", e.getMessage());
			}
		}
		return result;
	}
	
	public static Integer parseInt(String value){
		Integer result = new Integer(0);
		if( value!=null ){
			try{
				result = new Integer(value.trim());
			}catch(Exception e){
				log3.error(e, "", e.getMessage());
			}
		}
		return result;
	}
	
	static public Double parseDouble(String value){
		Double result = new Double(0);
		if( value!=null ){
			try{
				result = new Double(value.trim());
			}catch(Exception e){
				log3.error(e, "", e.getMessage());
			}
		}
		return result;
	}
	
	static public String validar_texto_inverso(String cadena){
		String cadenaEvaluar="";
		try{
			
			for(int i=0;i<cadena.length();i++){
				if(cadena.charAt(i)=='#')
					cadenaEvaluar+="�";
				else if (cadena.charAt(i)=='\n')
					cadenaEvaluar+="";
				else
					cadenaEvaluar+=cadena.charAt(i);
			}
			
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		
		return cadenaEvaluar;
	}
	
	static public String validar_N(String cadena){
		String cadenaEvaluar="";
		try{
			for(int i=0;i<cadena.length();i++){
				if(cadena.charAt(i)=='�')
					cadenaEvaluar+="N";
				else
					cadenaEvaluar+=cadena.charAt(i);
			}
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		
		return cadenaEvaluar;
	}
	
	static public String valor(String cadena){
		String g="";
		cadena=validar_texto_inverso(cadena);
		try{
			if(cadena!=null)
					g=cadena.trim().length()>0?cadena.trim():"";
				else
					g="";
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return g;
	}
	
	static public String valor(String cadena,String defecto){
		String g="";
		
		cadena=validar_texto_inverso(cadena);
		
		try{
	
			if(cadena!=null)
				g=cadena.trim().length()>0?cadena.trim():defecto;
			else
				g=defecto;
			
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
	
		return g;
	}
	
	public static int getAleatorio(int inicio, int fin){
		return  inicio + ((int) (Math.random()*((fin+1) - inicio)));
		
	}
	
	public static int getAleatorio(){
		return  1000 + ((int) (Math.random()*((9000+1) - 1000)));
		
	}
	
	public static String pintarException(Exception e){
		StringBuffer excep = new StringBuffer( e.toString() );
		try {
			StackTraceElement[] stacktrace =  e.getStackTrace();
			if( stacktrace!=null ){
				for( int i=0; i<stacktrace.length;i ++){
					excep.append(stacktrace[i].toString());
				}
			}
		} catch (Exception e2) {
			log3.error(e2, "", e2.getMessage());
		}
		return excep.toString();
	}
	
	public static int veces_caracter(String pr, char _toCompare){
		int veces=0;
		char []caracteres=pr.toCharArray();
		for(int i=0;i<=caracteres.length-1;i++){
			if(_toCompare ==caracteres[i]){
				veces++;
			}
			
		}
		return veces;
	}
	
	public static String eliminarEspaciosIntermedios(String texto){
		String resultado="";
		try{
			if(texto!=null){
				boolean espacio = false;
				for(int i=0; i<texto.length(); i++){
					if(texto.charAt(i)==' '){
						if(espacio==false){
							resultado += " ";
							espacio = true;
						}
					}else{
						resultado += texto.charAt(i);
						espacio = false;
					}
				}
			}
			
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return resultado;
		
	}

	public static String eliminarTodosEspacios(String texto){
		String sCadenaSinBlancos="";
		try{
			if(texto!=null){
				for (int x=0; x < texto.length(); x++) {
					  if (texto.charAt(x) != ' ')
					    sCadenaSinBlancos += texto.charAt(x);
					}
				
			}	
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return sCadenaSinBlancos;
		
	}
	
	
	public static boolean isNumeric(String text){
		return  text.matches("[0-9]+");
	}
	public static boolean isAlfanumeric(String text){
		return  text.matches("[A-Za-z 0-9]+");
	}
	public static boolean isAlfa(String text){ 
		return  text.matches("[A-Za-z ]+");
	}
	
	static public String cortar_izquierda(String texto, int cantidad){
		String valor = "";
		texto = parseString(texto);
		if(texto.length()>=cantidad){
			for( int i=0; i<cantidad; i++){
				valor += texto.charAt(i);
			}
		}else{
			valor = texto;
		}
		return valor;
		
	}
	
	static public String cortar_derecha(String texto, int cantidad){
		String valor = "";
		texto = parseString(texto);
		if(texto.length()>=cantidad){
			for( int i=texto.length()-1; i>=texto.length()-cantidad; i--){
				valor = texto.charAt(i) + valor;
			}
			
		}	else{
			valor = texto;
		}
		return valor;
		
	}
	

	public static String remove(String input) {     
		// Cadena de caracteres original a sustituir.     
		String original = "��������������u�������������������\n";     
		// Cadena de caracteres ASCII que reemplazar�n los originales.     
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC ";     
		String output = input;     
			for (int i=0; i<original.length(); i++) {         
				// Reemplazamos los caracteres especiales.         
				output = output.replace(original.charAt(i), ascii.charAt(i));     
				}
				//for i     
			return output; 
	}
	//remove1

	public static String elmiminarCerosAlaIzquierda(String texto){ 
		String valor = ""; 
		for( int i=0; i < texto.length(); i++){
			if ( texto.charAt(i) != '0' ){ 
				valor = texto.substring(i); 
				break; 
			}
		}
			 
		return valor; 
	} 
	
	static public String obtener_fecha(){
		Date fechaActual = Calendar.getInstance().getTime();  
		long tiempoActual = fechaActual.getTime();  
		DateFormat formatter_bd = new SimpleDateFormat("yyyyMMdd");
		String fecha = formatter_bd.format(fechaActual);
		return fecha;
		
	}
	
	static public String obtener_fecha_2(){
		Date fechaActual = Calendar.getInstance().getTime();  
		long tiempoActual = fechaActual.getTime();  
		DateFormat formatter_bd = new SimpleDateFormat("yyyyMMdd");
		String fecha = formatter_bd.format(fechaActual);
		
		return fecha;
	}
	
	static public String obtener_fecha_app(){
		int diferenciaEnDias = 1;  
		Date fechaActual = Calendar.getInstance().getTime();  
		long tiempoActual = fechaActual.getTime();  
		long unDia = diferenciaEnDias * 24 * 60 * 60 * 1000;  
		Date fechaAyer = new Date(tiempoActual - unDia); 
		DateFormat formatter_secm = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat formatter_bd = new SimpleDateFormat("yyyy/MM/dd");
		String ayer = formatter_secm.format(fechaAyer);
		String ayer_bd = formatter_bd.format(fechaAyer);
		
		return ayer;
	}
	
	static public String obtener_fecha_log(){
		int diferenciaEnDias = 1;  
		Date fechaActual = Calendar.getInstance().getTime();  
		long tiempoActual = fechaActual.getTime();  
		long unDia = diferenciaEnDias * 24 * 60 * 60 * 1000;  
		Date fechaAyer = new Date(tiempoActual); 
		DateFormat formatter_secm = new SimpleDateFormat("dd/MM/yyyy");
		//DateFormat formatter_bd = new SimpleDateFormat("yyyy/MM/dd");
		String ayer = formatter_secm.format(fechaAyer);
		//String ayer_bd = formatter_bd.format(fechaAyer);
		
		return ayer;
	}
	
	static public String formateo_ceros_izquierda(String valor, int maximo){
		int cantidad=valor.trim().length();
		for(int i=1;i<=(maximo-cantidad);i++){
			valor="0"+valor;
		}
		return valor;
	}
	
	public static String formatearTasa(Double monto){		
		monto=Funciones.redondear_numero_decimales(monto,2);
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		DecimalFormat formateador = new DecimalFormat("###,###,###,###,###,###0.00");
		formateador.setDecimalFormatSymbols(simbolos);
		return formateador.format(monto);		 
		
	}
	
	public static String formatearTelefono(String texto){		
		String resultado="";
		int tam=0;   
		try{  
			resultado="";
			tam=texto.length();
			if(tam==1){
				resultado =""; 	
				}
			else
				resultado=texto;
		   }catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return resultado;
		    
	}
	
	public static String formatearPagare(String texto){		
		String resultado="";
		try{
			resultado="";
			if(texto!=null){
				for(int i=0; i<texto.length(); i++){
					switch(i){
							case 5 :resultado +=texto.charAt(i);break;
							case 6 :resultado +=texto.charAt(i);break;
							case 7 :resultado +=" "+texto.charAt(i);break;
							case 8 :resultado +=texto.charAt(i);break;
							case 9 :resultado +=texto.charAt(i);break;
							case 10:resultado +=" "+texto.charAt(i);break;
							case 11:resultado +=texto.charAt(i);break;
							case 12:resultado +=texto.charAt(i);break;
							case 13:resultado +=texto.charAt(i);break;
							case 14:resultado +=texto.charAt(i);break;
							case 15:resultado +=texto.charAt(i);break;
							case 16:resultado +="-" + texto.charAt(i);break;
							case 17:resultado +=texto.charAt(i);break;}
							}
				}
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return resultado;
		  
		
	}
	
	
	public static String cortarfecha(String texto){		
		String resultado="";
		try{
			resultado="";
			if(texto!=null){
				for(int i=0; i<texto.length(); i++){
					switch(i){
							case 0 :resultado +=texto.charAt(i);break;
							case 1 :resultado +=texto.charAt(i);break;
							case 2 :resultado +=texto.charAt(i);break;
							case 3 :resultado +=texto.charAt(i);break;
							case 4 :resultado +=texto.charAt(i);break;
							case 5: resultado +=texto.charAt(i);break;
							case 6:resultado +=texto.charAt(i);break;
							case 7:resultado +=texto.charAt(i);break;
							case 8:resultado +=texto.charAt(i);break;
							case 9:resultado +=texto.charAt(i);break;
							}
				}
			}   
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return resultado;
		  
		
	}
	
	
	public static String completarEspacios(String texto, int limite){		
		try{
			if (texto==null || texto==""){
				texto="";
				for(int i=0; i<limite; i++)
					texto +=" ";
			}
			else{
				if (texto.length()<limite){
					if(texto!=null){
						for(int i=texto.length(); i<limite; i++)
							texto +=" ";
					}
					if (texto.length()==0){
						for(int i=0; i<limite; i++)
								texto +=" ";
					}
				}
			}
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return texto;		
	}
	
	
	public static String formatearCuenta1(String texto){		
		String resultado="";
		try{
			resultado="0";
			if(texto!=null){ 
				for(int i=0; i<texto.length(); i++){
					switch(i){
							case 0 :resultado +=texto.charAt(i);break;
							case 1 :resultado +=" "+texto.charAt(i);break;
							case 2 :resultado +=texto.charAt(i);break;
							case 3 :resultado +=texto.charAt(i);break;
							case 4 :resultado +=" "+texto.charAt(i);break;
							case 5 :resultado +=texto.charAt(i);break;
							case 6 :resultado +=texto.charAt(i);break;
							case 7 :resultado +=texto.charAt(i);break;
							case 8 :resultado +=texto.charAt(i);break;
							case 9 :resultado +=texto.charAt(i);break;
							
							}
				}
			}
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return resultado;
	}
	
	public static String formatearCuenta2(String texto){		
		String resultado="";
		try{
			resultado="0";
			if(texto!=null){ 
				for(int i=0; i<texto.length(); i++){
					switch(i){
							case 0 :resultado +=texto.charAt(i);break;
							case 1 :resultado +=" "+texto.charAt(i);break;
							case 2 :resultado +=texto.charAt(i);break;
							case 3 :resultado +=texto.charAt(i);break;
							case 4 :resultado +=" "+texto.charAt(i);break;
							case 5 :resultado +=texto.charAt(i);break;
							
							case 6 :resultado +=texto.charAt(i);break;
							case 7 :resultado +=texto.charAt(i);break;
							case 8 :resultado +=texto.charAt(i);break;
							case 9 :resultado +=texto.charAt(i);break;
							
							}
				}
			}
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return resultado;
	}
	
	public static String eliminarPuntoComa(String texto){
		String resultado="";
		try{
			resultado="";
			if(texto!=null){
				for(int i=0; i<texto.length(); i++){
					if(texto.charAt(i)!='.' && texto.charAt(i)!=','){
							resultado +=texto.charAt(i);
					}
				}
			}
			
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return resultado;
		
	}

	public static String formatearMontoComa(BigDecimal monto){		
		
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		DecimalFormat formateador = new DecimalFormat("###,###,###,###,###,##0.00");
		formateador.setDecimalFormatSymbols(simbolos);
		return formateador.format(monto);		
		
	}

	public static String formatearMontoEntero(String number){
		double value; 
		String numberFormat = "###,###,###,###"; 
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		DecimalFormat formatter = new DecimalFormat(numberFormat); 
		simbolos.setGroupingSeparator(',');	
		formatter.setDecimalFormatSymbols(simbolos);
		try { 
		value = Double.parseDouble(number); 
		} catch (Throwable t) { 
		return null; 
		} 
		return formatter.format(value); 
		}
	
	public static String formatearMonto(BigDecimal monto){		
		
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		DecimalFormat formateador = new DecimalFormat("###,###,###,###,###,##0.00");
		formateador.setDecimalFormatSymbols(simbolos);
		return formateador.format(monto);		
		
	}
	
	public static String formatearMonto3(Double monto){
		DecimalFormat formateador=null;
		try{
		monto=Funciones.redondear_numero_decimales(monto,2);
		if (monto < 0){
			monto = monto * (-1);
		}
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		formateador = new DecimalFormat("###,###,###,###,###,##0.00");
		formateador.setDecimalFormatSymbols(simbolos);
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return formateador.format(monto);
		
	}
	
	
	public static String formatearMonto1(Double monto){
		monto=Funciones.redondear_numero_decimales(monto,2);
		if (monto < 0){
			monto = monto * (-1);
		}
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		DecimalFormat formateador = new DecimalFormat("###,###,###,###,###.##");
		
		formateador.setDecimalFormatSymbols(simbolos);
		return formateador.format(monto);
		
	}
	
 	
 	public static String formatearMonto111(Float monto){
 		//System.out.println("Valores float: "+monto);
		DecimalFormat formateador=null;
		try{
		monto=Funciones.redondear_numero_decimales1(monto,2);
		if (monto < 0){
			monto = monto * (-1);
		}
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		formateador = new DecimalFormat("###,###,###,###,###,##0.00");
		formateador.setDecimalFormatSymbols(simbolos);
		}catch(Exception e){
			//log3.error(e, "", e.getMessage());
		}
		return formateador.format(monto);
		
	}
	
	public static String formatearMonto2(Double monto){		
		if (monto < 0){
			monto = monto * (-1);
		}
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		DecimalFormat formateador = new DecimalFormat("###,###,###,###,###,##0.0000");
		formateador.setDecimalFormatSymbols(simbolos);
		return formateador.format(monto);
		
	}
	
	public static String formatearMontoNeg(Double monto){
		
		String signo = "";
		if (monto.toString().substring(1).equals('-')){
			signo = "-";
			monto = Double.parseDouble(monto.toString().substring(1));
		}
			
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		DecimalFormat formateador = new DecimalFormat("###,###,###,###,###,##0.00");
		formateador.setDecimalFormatSymbols(simbolos);
		return formateador.format(monto);		 
		
	}
	
	public static double formatearMontoDouble(String monto){
		Double montoConvertido = 0.0;
		if(!("".equals(monto.trim()) || monto ==null)){
			monto = monto.replaceAll(",", "");
			montoConvertido =  parseDouble(monto);
		}
		return montoConvertido;
	}
	
	public static String formatearCuenta(String fecha){
		try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(0,2)+"-"+fecha.substring(2,5)+"-"+fecha.substring(5,11);
	    	}
		return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String formatearCuentaTotal(String fecha){
		try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(5,7)+"-"+fecha.substring(7,10)+"-"+fecha.substring(10,16);
	    	}
		return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String formatearCuentaPlazo(String fecha){
		try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(7,9)+"-"+fecha.substring(9,12)+"-"+fecha.substring(12,18);
	    	}
		return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String formatearCuentaHipotecario(String fecha){
		try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(2,16);
	    	}
		return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String formatearCuentaGarantiaHipotecaria(String cuenta){
		try{
	    if(cuenta==null || cuenta.trim().equals("")) cuenta="";
	    	else{
	    		cuenta = cuenta.substring(2,16);
	    	}
		return cuenta;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return cuenta;
	}
	
	public static String formatearCuentaCartaFianzaMN(String cuenta){
		try{
	    if(cuenta==null || cuenta.trim().equals("")) cuenta="";
	    else{
	    	cuenta = cuenta.substring(10,18);
	    }
		return cuenta;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return cuenta;
	}
	
	public static String formatearCuentaCartaFianzaME(String cuenta){
		try{
	    if(cuenta==null || cuenta.trim().equals("")) cuenta="";
	    else{
	    	cuenta = "GAR-"+cuenta.substring(13,18);
	    }
		return cuenta;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return cuenta;
	}
	
	public static String formatearCuentaOtros(String fecha){
		try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(6,16);
	    	}
		return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String formatearCuentaCredDocImport(String fecha){
		try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(9,18);
	    	}
		return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String formatearCuentaGarantiaTercerNivel(String fecha){
		try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(0,2)+"-"+fecha.substring(2,5)+"-"+fecha.substring(5,11);
	    	}
		return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String formatearCuentaCteTercerNivel(String fecha){
		try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(0,2)+"-"+fecha.substring(2,5)+"-"+fecha.substring(5,11);
	    	}
		return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String formatearCuentaMultired(String fecha){
		try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(5,16)+"-"+fecha.substring(16,18);
	    	}
		return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String formatearCuentaGarantiaHip(String fecha){
		try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(2,14);
	    	}
		return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String formatearCuentaCartaFianza(String fecha){
		try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(9,18);
	    	}
		return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String formatearCuentaGarantia(String fecha){
		try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(7,18);
	    	}
		return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String formatearFecha(String fecha){
		try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(6)+"/"+fecha.substring(4,6)+"/"+fecha.substring(0,4);
	    	}
		return fecha;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return fecha;
	}
	
	public static String obtenerFechaActual(String formato){
		String timestamp="";
		try {
			Calendar fecha = Calendar.getInstance();
		       SimpleDateFormat sdf =  new SimpleDateFormat(formato);
		       timestamp = sdf.format(fecha.getTime());
		} catch (Exception e) {
			timestamp="";
			log3.error(e, "", e.getMessage());
		}
		return timestamp;
		
	}
	

	   public static String lpad(String valueToPad, String filler, int size) { 
        while (valueToPad.length() < size) {   
            valueToPad = filler + valueToPad;   
        }   
        return valueToPad;   
    }

    public static String rpad(String valueToPad, String filler, int size) { 
        while (valueToPad.length() < size) {   
            valueToPad = valueToPad+filler;   
        }   
        return valueToPad;   
    }   
    
	public static String enmascararTramaRecepcion(String value){
		if(value==null) return null;
		
		value = value.replaceAll("#", "�");
		
		return value;
	}
	
	public static String enmascararTramaEnvio(String value){
		if(value==null) return null;
		
		value = value.replaceAll("�", "#");
		value = value.replaceAll("�", "#");
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
	//Obtener gettimestamp --utilizado en el Head
	public static String getTimeStamp() {
		Calendar calendario = GregorianCalendar.getInstance();
		Date fecha = calendario.getTime();
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd-HH-m-s-999999");
		return formatoDeFecha.format(fecha);
	}
	//  repeat utilizado en wsdata
 	public static String repeat(String s, int n) { 
	    if(s == null) { 
	        return null; 
	    } 
	    final StringBuilder sb = new StringBuilder(); 
	    for(int i = 0; i < n; i++) { 
	        sb.append(s); 
	    } 
	    return sb.toString(); 
	}
 	
 	public static String formatearTasaInteres(BigDecimal monto){		
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("#0.0000");
		formateador.setDecimalFormatSymbols(simbolos);
		return formateador.format(monto);		
	}
 	
 	public static String formatearTasaInteresMon(BigDecimal monto){		
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("#0.0000000");
		formateador.setDecimalFormatSymbols(simbolos);
		return formateador.format(monto);		
	}
 	


 	// Funciones Rix
	/***********************************************************/
	/**        METODOS DE VALIDACION DE TIPOS DE DATOS
	/***********************************************************/
	
	

	public static final BigDecimal parseBigDecimal(String value){
		BigDecimal result = new BigDecimal(0);
		try{
			value= value.trim();
			result = new BigDecimal(value);
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		} 
		return result;
	}
	
	
	public static final BigDecimal parseBigDecimal(Double value){
		BigDecimal result = new BigDecimal(0);
		try{	
			result = new BigDecimal(value.doubleValue());
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		} 
		return result;
	}
	
	public static final Double parseDouble(Double value){
		Double result = new Double(0);
		try{	
			result = new Double(value.doubleValue());
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		} 
		return result;
	}

	public static final Long parseLong(Long value){
		Long result = new Long(0);
		try{
			result = new Long(value.longValue());
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		} 
		return result;
	}
	public static final Long parseLong(Double value){
		Long result = new Long(0);
		try{
			result = new Long(value.longValue());
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		} 
		return result;
	}

	
	public static final Integer parseInt(Integer value){
		Integer result = new Integer(0);
		try{
			result = new Integer(value.intValue());
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		} 
		return result;
	}
	public static final Integer parseInt(Double value){
		Integer result = new Integer(0);
		try{
			result = new Integer(value.intValue());
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		} 
		return result;
	}
	public static final String parseString(String value){
		String result = "";
		try{
			if(value==null) value = "";
			value = value.trim();
			result = value;
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return result;
	}
	public static final String parseNull(String value){
		String result = null;
		try{
			if(value==null || value.trim().length()==0) 
				result = null;
			else
				result = value.trim();
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return result;
	}
	public static final String parseString(Object value){
		String result = "";
		try{
			if(value!=null){
				result = (String)value; 
				result = result.trim();
			}
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return result;
	}
	
	static public String validar_indicador_restriccion(String numero,String valor1,String valor2){
		String g="";
		
		try{
			g=(validar_entero(numero)==10?valor1:(validar_entero(numero)==1?valor2:""));
			
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		
		return g;
		
	}
	
	static public int validar_entero(String numero){
		int n=0;
		try{
			n=Integer.parseInt(numero.trim());
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return n;
	}
	
	static public boolean obtener(String key,String permisos){
		boolean value=false;
		try{
			value=(permisos.substring(permisos.indexOf(key)+2,permisos.indexOf(key)+3)).equals("S")?true:false;
			
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		
		return value;
	}
	
	static public String cleanStringSpecialChars(String cadena, String modo){
		String resultado = "";
		if(cadena != null){
			cadena = validar_texto_sinAcento(cadena);
			for (int i = 0; i < cadena.length(); i++) {
				resultado += cleanCharSpecialChars(cadena, i, ' ', modo);
			}
		}
		return resultado;
	}
	static public String validar_texto_sinAcento(String cadena){
		String cadenaEvaluar="";
		try{
			if(cadena != null){
				for(int i=0;i<cadena.length();i++){
					char car = cadena.charAt(i);
					for (int j = 0; j < LIST_ACENTOS.length; j++) {					
						if(car == LIST_ACENTOS[j][0]){
							car = LIST_ACENTOS[j][1];
							break;
						}
					}
					cadenaEvaluar += car;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return cadenaEvaluar;
	}
	
public static String formatearSaldo(String texto){		
		
		try{
			
		if(texto!=null){
		
			texto = texto.replace(".", "");
			int tam=texto.length(); 
			switch(tam){
			
			case 1 : texto="0000000000000000"+texto;break;
			case 2 : texto="000000000000000"+texto;break;
			case 3 : texto="00000000000000"+texto;break;
			case 4 : texto="0000000000000"+texto;break;
			case 5 : texto="000000000000"+texto;break;
			case 6 : texto="00000000000"+texto;break;
			case 7 : texto="0000000000"+texto;break;
			case 8 : texto="000000000"+texto;break;
			case 9 : texto="00000000"+texto;break;
			case 10 :texto="0000000"+texto;break;
			case 11 :texto="000000"+texto;break;
			case 12 :texto="00000"+texto;break;
			case 13 :texto="0000"+texto;break;
			case 14 :texto="000"+texto;break;
			case 15 :texto="00"+texto;break;
			case 16 :texto="0"+texto;break;
			case 17 :texto=texto;break;
			}
		}
		}catch(Exception e){
			log3.error(e, "", e.getMessage());
		}
		return texto;
		  
		
	}
	
	private static char cleanCharSpecialChars(String cadena, int posicion, char replaceChar, String modo){
		char a = ' ';
		try {
			if(cadena!=null){
				if(posicion>=0){
					a = cadena.charAt(posicion);
					int aint = (int)a;
					if(modo.equalsIgnoreCase(MODO_VALIDATE_DIR)){
						if(aint==39){
							return ' ';
						}
					}
					/*if(!((47<aint && aint<58) || (64<aint && aint<91) || (96<aint && aint<123) || 
							aint==46 || aint==209 || aint==241 || aint==32)){*/
					if( (35<aint && aint<40) || (44<aint && aint<58 )  || 
						(64<aint && aint<91) || (94<aint && aint<123) ||  
						aint==32 || aint==209 || aint==241){
						//a = replaceChar;
						return a;
					}else{
						a = replaceChar;
						//return a;
					}
				}
			}
		} catch (Exception nfe){
			return ' ';
		}
		return a;
	}
	
	/**
	 * Meotodo que sirve para redondear un numero Double a N decimales
	 * @param nNumero
	 * @param nDecimal
	 * @autor: Angela Risco 
	 * @return
	 */
	static public double redondear_numero_decimales(double nNumero, int nDecimal)  

	 {  
		return Math.round(nNumero*Math.pow(10,nDecimal))/Math.pow(10,nDecimal);  
		
	 } 
	
	/**
	 * Meotodo que sirve para redondear un numero Double  
	 * si la cifra base para el redondeo es mayor o igual que 5, se aumenta en una unidad la cifra anterior; 
	 * si la cifra base es menor que 5, no se modifica la cifra anterior.
	 * @param nNumero
	 * @autor: Angela Risco 
	 * @return
	 */
	static public double redondear(double nNumero){
			return Math.rint(nNumero);		
	}
	
	
}
	



