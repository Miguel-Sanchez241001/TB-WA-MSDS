package pe.com.bn.msds.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import pe.com.bn.msds.common.LoggerBn;

public class SessionTimeoutFilter implements Filter {
	
	private static LoggerBn log = LoggerBn.getInstance(SessionTimeoutFilter.class.getName());
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		log.debug(request != null ? "FILTER | request:" + request.toString() : "FILTER | request is null", "1");
		log.debug((request != null && request.getParameterMap() != null) ? "FILTER | request.getParameterMap:" + request.getParameterMap().toString() : "FILTER | request.getParameterMap is null", "1");
		
		log.debug(response != null ? "FILTER | response:" + response.toString() : "FILTER | response is null", "1");
		
//		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {

//			HttpServletRequest 	httpServletRequest 	= (HttpServletRequest) request;
//			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//			
//			if (isSessionControlRequiredForThisResource(httpServletRequest)) {
//				
//				HttpSession session = (httpServletRequest.getSession());
//				
//				DatosSesion datosSesion = null;
//				
//				log.debug(session == null ? "FILTER | SESSION IS NULL" : "FILTER | SESSION ID: " + session.getId(), "1");
//				
//				if (session != null) {
//					
//					datosSesion = (DatosSesion)session.getAttribute("datosSesion");
//				}
//				
//				if (isSessionInvalid(httpServletRequest) || datosSesion == null) {
//			
//					String timeoutUrl = httpServletRequest.getContextPath() + "/login";
//					
//					httpServletResponse.sendRedirect(timeoutUrl);
//					
//					return;
//				}
//			 }
//		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {	
	}

//	private boolean isSessionControlRequiredForThisResource(HttpServletRequest httpServletRequest) {
//		
//		String requestPath = httpServletRequest.getRequestURI();
//		
////		log.debug("FILTER | requestPath: " + requestPath, "1");
//		
//		if (StringUtils.contains(requestPath, "/assets/") || StringUtils.contains(requestPath, "/login")) {
//			return false;
//		}
//		
//		boolean controlRequired = !StringUtils.contains(requestPath, "iniciarSesion");
//		
//		return controlRequired;
//	}	
//	
//	private boolean isSessionInvalid(HttpServletRequest httpServletRequest) {
//		
//		boolean sessionInValid = httpServletRequest.getRequestedSessionId() != null && !httpServletRequest.isRequestedSessionIdValid();
//	
//		return sessionInValid;
//	}
	
}
