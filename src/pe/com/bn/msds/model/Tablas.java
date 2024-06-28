package pe.com.bn.msds.model;

public class Tablas {
	private int id;
	private String valor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public Tablas(int id, String valor) {
		super();
		this.id = id;
		this.valor = valor;
	}
	public Tablas() {
		super();
	}
	
}