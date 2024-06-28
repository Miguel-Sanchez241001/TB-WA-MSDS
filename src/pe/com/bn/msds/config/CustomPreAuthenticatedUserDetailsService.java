package pe.com.bn.msds.config;

//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
//
//import pe.com.bn.mgec.common.LoggerBn;
//import pe.com.bn.mgec.dao.impl.ConsultaSaiDAOImpl;
//import pe.com.bn.mgec.dao.inf.ConsultaSaiDAO;
//import pe.com.bn.mgec.model.ConsultaSAI;

public class CustomPreAuthenticatedUserDetailsService /*implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken>*/ {

//	private static LoggerBn log = LoggerBn.getInstance(CustomPreAuthenticatedUserDetailsService.class.getName());
	
//	@Override
//	public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
//
//		String principal = (String) token.getPrincipal();
//		
//		log.debug("loadUserDetails principal: " + principal, "1");
//		
//		System.out.println("loadUserDetails principal: " + principal);
//		
//		/**
//		principal = username + "|" + 
//					grupos + "|" +
//					codTrabajador + "|" +
//					dniTrabajador;
//		**/
//		
//		String[] datos = principal.split("\\|");
//
//		String username = datos[0];
//		String grupos = datos[1];
//		String codEmpleado = datos[2];
//		String dniEmpleado = datos[3];
//		
//		
//		ConsultaSaiDAO consultaSaiDAO = new ConsultaSaiDAOImpl();
//		
//		ConsultaSAI datauser = consultaSaiDAO.obtenerDatosUsuario(codEmpleado);
//		
//		if (datauser == null) {
//			throw new BadCredentialsException("Consulta SAI datos de usuario no encontrado.");
//		}
//		
//		String user_id = datauser.getCodUsuario();
//		String nombre_usuario = datauser.getNomusuario();
//		String dependencia_usuario = datauser.getNomDependen();
//		
//		String codigo_dependencia = datauser.getCodDepend();
//		String codigo_empleado = datauser.getCodEmpleado();
//        
//		log.debug("CODIGO DE USUARIO " + user_id, "1");
//		log.debug("NOMBRE DE USUARIO " + nombre_usuario, "1");
//		log.debug("NOMBRE DE DEPENDENCIA " + dependencia_usuario, "1");
//		log.debug("CODIGO DE DEPENDENCIA " + codigo_dependencia, "1");
//		log.debug("CODIGO DE EMPLEADO " + codigo_empleado, "1");
//		
//		
////		NServiciosPermiso permiso = new NServiciosPermiso();
//
//		BUsuarioLogin usuario = new BUsuarioLogin();
//		usuario.setLogin(username); //authentication.getName());
//		usuario.setPassword("");//((String) authentication.getCredentials());
//		usuario.setNombre(nombre_usuario); 
//		usuario.setSeccion(dependencia_usuario);
//		usuario.setCodUsuario(codigo_empleado);
//		usuario.setCodDependencia(codigo_dependencia);
//		
////		usuario = permiso.listarPermisos(usuario);
//
//		
//		/** separamos los grupos **/
//		String [] option  = grupos.split(","); 
//		
//		List<BOpcion> lista = new LinkedList<BOpcion>();
//		
//		for (int i = 0; i < option.length; i++) {
//		
//			BOpcion opcion = new BOpcion();
//			opcion.setCodigo(option[i]);
//			
//			lista.add(opcion);
//		}
//		/** separamos los grupos **/
//		
//		usuario.setListaOpciones(lista);
//	
//		
//		if (usuario.getListaOpciones().size() >= 1) {
//			
//			log.debug("CustomPreAuthenticatedUserDetailsService | Login correcto:" + usuario.getLogin(), "1");
//			
//			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//			
//			for (BOpcion opcion : usuario.getListaOpciones()) {
//				authorities.add(new SimpleGrantedAuthority("ROLE_" + opcion.getCodigo()));
//			}
//
//			CustomUser customUser = new CustomUser(	usuario.getLogin(),
//													usuario.getPassword(), 
//													true, 
//													true, 
//													true, 
//													true, 
//													authorities,
//													usuario.getNombre(), 
//													usuario.getSeccion(), 
//													usuario.getCodUsuario(), 
//													usuario.getCodDependencia());
//			
//			return customUser;
//
////		} else if (usuario == null) {
////			
////			log.debug("CustomUserDetailsService | Error de login:" + usuario.getLogin(), "1");
////			throw new BadCredentialsException("Usuario y/o clave no son correctos.");
//		} else {
//			log.debug("CustomUserDetailsService | Usuario no tiene autorizacion:" + usuario.getLogin(), "1");
//			throw new BadCredentialsException("Usuario no tiene autorizacion.");
//		}
//	}

}
