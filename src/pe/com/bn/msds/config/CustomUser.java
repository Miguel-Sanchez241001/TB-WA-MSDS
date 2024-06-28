package pe.com.bn.msds.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
	
	private static final long serialVersionUID = -4177569885154948770L;

	public CustomUser(	String username, 
						String password, 
						boolean enabled,
						boolean accountNonExpired, 
						boolean credentialsNonExpired,
						boolean accountNonLocked,
						Collection<? extends GrantedAuthority> authorities,
						String nombre, 
						String seccion, 
						String codUsuario, 
						String codDependencia) {
		
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		this.nombre  		= nombre;
		this.seccion 		= seccion;
		this.codUsuario 	= codUsuario;
		this.codDependencia = codDependencia;
	}

	private String nombre;
	private String seccion;
	private String codUsuario;
	private String codDependencia;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	
	public String getCodUsuario() {
		return codUsuario;
	}
	
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getCodDependencia() {
		return codDependencia;
	}
	
	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}

	@Override
	public String toString() {
		return "CustomUser [nombre=" + nombre + ", seccion=" + seccion
				+ ", codUsuario=" + codUsuario + ", codDependencia="
				+ codDependencia + "]";
	}
	
}
