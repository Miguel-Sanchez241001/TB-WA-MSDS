package pe.com.bn.msds.model;

public class Sistema {
	private int id;
	private String cod;
	private String nombre;
	private String descripcion;
	
	
	public Sistema() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Sistema(int id,String cod, String nombre, String descripcion) {
		super();
		this.id = id;
		this.cod = cod;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	public String getcod() {
		return cod;
	}
	public void setcod(String cod) {
		this.cod = cod;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Sistema [cod=" + cod + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + "]";
	}
}
