package pe.com.bn.msds.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import pe.com.bn.msds.common.LoggerBn;

public class CustomRequestHeaderAuthenticationFilter extends RequestHeaderAuthenticationFilter {

	private static LoggerBn log = LoggerBn.getInstance(CustomRequestHeaderAuthenticationFilter.class.getName());
	
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		
		String principal = "";
		
		try {
			
			
		/*		String username = request.getHeader("iv-user");
				String grupos = request.getHeader("iv-groups");
				String codTrabajador = request.getHeader("codtrabajador");
				String dniTrabajador = request.getHeader("dnitrabajador");*/
			// TRABAJO LOCAL SIN AUTENTICACION A ESAMM	
				
				String username = "dcribillero";
				String grupos = "grupoUsuariosWeb,grbn_BolPag_User,grbn_msds_administrador,grbn_msds_consulta,grbn_msds_administrador";
			//	String grupos = "grupoUsuariosWeb,grbn_BolPag_User,grbn_msds_administrador,grbn_msds_consulta,grbn_msds_consulta";

				String codTrabajador = "0312991";
				String dniTrabajador = "40300639";
			// TRABAJO LOCAL SIN AUTENTICACION A ESAMM		
				
				log.debug("getPreAuthenticatedPrincipal HEADERS", "1");
				log.debug("getPreAuthenticatedPrincipal iv-user: " + username, "1");
				log.debug("getPreAuthenticatedPrincipal iv-groups: " + grupos, "1");
				log.debug("getPreAuthenticatedPrincipal codtrabajador: " + codTrabajador, "1");
				log.debug("getPreAuthenticatedPrincipal dnitrabajador: " + dniTrabajador, "1");
			
			
			
			/*
			
			String username = request.getHeader("iv-user");
			String grupos = request.getHeader("iv-groups");
			String codTrabajador = request.getHeader("codtrabajador");
			String dniTrabajador = request.getHeader("dnitrabajador");
			*/
			
			
			
			log.debug("getPreAuthenticatedPrincipal HEADERS", "1");
			log.debug("getPreAuthenticatedPrincipal iv-user: " + username, "1");
			log.debug("getPreAuthenticatedPrincipal iv-groups: " + grupos, "1");
			log.debug("getPreAuthenticatedPrincipal codtrabajador: " + codTrabajador, "1");
			log.debug("getPreAuthenticatedPrincipal dnitrabajador: " + dniTrabajador, "1");
			
			
			principal = username + "|" + 
						grupos.replace("\"", "") + "|" +
						codTrabajador + "|" +
						dniTrabajador;
			
			log.debug("getPreAuthenticatedPrincipal principal: " + principal, "1");
			
		} catch (Exception e) {
			log.error(e, "", "CustomRequestHeaderAuthenticationFilter " + e.getMessage());
		}
		
		return principal;
	}
	
}
