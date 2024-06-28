/*
 * Creado el 30/04/2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package pe.com.bn.msds.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import pe.com.bn.msds.common.Constant;
import pe.com.bn.msds.common.LoggerBn;
import pe.com.bn.msds.listener.ContextListenerProperties;
 
/**
 * @author 
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public class ContextListenerProperties implements ServletContextListener, Runnable {

	ServletContextEvent arg;
	private static LoggerBn log3 = LoggerBn.getInstance(ContextListenerProperties.class.getName());
	
	public void contextInitialized(ServletContextEvent arg0) {

		arg = arg0;
		Thread connectThread = new Thread(this);
        connectThread.start();
		
	}

    public void run() {
		try{
			log3.debug("INIT LOAD DB PARAMETRO111", Constant.LOGGER_DEBUG_NIVEL_1);
	   		
		} catch (Exception e) {
			e.printStackTrace();
			log3.error(e, Constant.ERR_LOGICA_NEGOCIO, "");
	    }
		
    }

	public void contextDestroyed(ServletContextEvent arg0) {
		
  
	}

}
