package pe.com.bn.msds.model;

public class FiltroLogConsulta {

	private String nroCuenta;
	private String fechaInicial;
	private String fechaFinal;
	private String ordenamiento;
	
	public FiltroLogConsulta() {
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getOrdenamiento() {
		return ordenamiento;
	}

	public void setOrdenamiento(String ordenamiento) {
		this.ordenamiento = ordenamiento;
	}

	@Override
	public String toString() {
		return "FiltroLogConsulta [nroCuenta=" + nroCuenta + ", fechaInicial="
				+ fechaInicial + ", fechaFinal=" + fechaFinal
				+ ", ordenamiento=" + ordenamiento + "]";
	}
	
}
