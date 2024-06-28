package pe.com.bn.msds.model;

public class EstadoCuentaDetalle {

	private String fechaOperacion;
	private String oficina;
	private String referencia;
	private String concepto;
	private String cargo;
	private String abono;
	private String saldo;

	public EstadoCuentaDetalle() {
	}

	public String getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getAbono() {
		return abono;
	}

	public void setAbono(String abono) {
		this.abono = abono;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "EstadoCuentaDetalle [fechaOperacion=" + fechaOperacion
				+ ", oficina=" + oficina + ", referencia=" + referencia
				+ ", concepto=" + concepto + ", cargo=" + cargo + ", abono="
				+ abono + ", saldo=" + saldo + "]";
	}
	
}
