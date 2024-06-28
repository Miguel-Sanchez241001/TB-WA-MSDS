package pe.com.bn.msds.config;

public class BOpcion {

	private String codigo;

	public BOpcion() {
		super();
	}
	
	public BOpcion(String codigo) {
		super();
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "BOpcion [codigo=" + codigo + "]";
	}
	
}
