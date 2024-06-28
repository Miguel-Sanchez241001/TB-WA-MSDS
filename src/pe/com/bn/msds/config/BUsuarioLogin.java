package pe.com.bn.msds.config;

import java.util.List;

public class BUsuarioLogin {

	private String login;
	private String password;
	private String nombre;
	private String seccion;
	private String codUsuario;

	private String codDependencia;

	private List<BOpcion> listaOpciones;

	public BUsuarioLogin() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public List<BOpcion> getListaOpciones() {
		return listaOpciones;
	}

	public void setListaOpciones(List<BOpcion> listaOpciones) {
		this.listaOpciones = listaOpciones;
	}

	@Override
	public String toString() {
		return "BUsuarioLogin [login=" + login + ", password=" + password
				+ ", nombre=" + nombre + ", seccion=" + seccion
				+ ", codUsuario=" + codUsuario + ", codDependencia="
				+ codDependencia + ", listaOpciones=" + listaOpcionesString() + "]";
	}

	private String listaOpcionesString() {
		String cadena = "";
		for (int i = 0; i < listaOpciones.size(); i++) {
			cadena = cadena + "|" + listaOpciones.get(i).getCodigo();
		}
		return cadena;
	}

}
