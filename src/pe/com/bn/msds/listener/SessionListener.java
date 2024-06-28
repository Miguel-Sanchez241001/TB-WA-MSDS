package pe.com.bn.msds.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import pe.com.bn.msds.common.LoggerBn;

public class SessionListener implements HttpSessionListener {

	private static LoggerBn log = LoggerBn.getInstance(SessionListener.class.getName());
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		
		HttpSession session = event.getSession();
		
//		log.debug("LISTENER | sessionCreated | Id:" + session.getId(), "1");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
		HttpSession session = event.getSession();
		
//		log.debug("LISTENER | sessionDestroyed | Id:" + session.getId(), "1");
	}

}
