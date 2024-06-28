package pe.com.bn.msds.model;

public class EstadoCuentaCabecera {

	private String nombreCompleto;
	private String nroDni;
	private String mesInicial;
	private String annoInicial;
	private String mesFinal;
	private String annoFinal;
	private String nroCuenta;
	private String cci;
	private String totalTransacciones;
	private String totalCargos;
	private String totalAbonos;
	private String saldoActual;

	public EstadoCuentaCabecera() {
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNroDni() {
		return nroDni;
	}
	
	public void setNroDni(String nroDni) {
		this.nroDni = nroDni;
	}
	
	public String getMesInicial() {
		return mesInicial;
	}

	public void setMesInicial(String mesInicial) {
		this.mesInicial = mesInicial;
	}

	public String getAnnoInicial() {
		return annoInicial;
	}

	public void setAnnoInicial(String annoInicial) {
		this.annoInicial = annoInicial;
	}

	public String getMesFinal() {
		return mesFinal;
	}

	public void setMesFinal(String mesFinal) {
		this.mesFinal = mesFinal;
	}

	public String getAnnoFinal() {
		return annoFinal;
	}

	public void setAnnoFinal(String annoFinal) {
		this.annoFinal = annoFinal;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getCci() {
		return cci;
	}

	public void setCci(String cci) {
		this.cci = cci;
	}

	public String getTotalTransacciones() {
		return totalTransacciones;
	}

	public void setTotalTransacciones(String totalTransacciones) {
		this.totalTransacciones = totalTransacciones;
	}

	public String getTotalCargos() {
		return totalCargos;
	}

	public void setTotalCargos(String totalCargos) {
		this.totalCargos = totalCargos;
	}

	public String getTotalAbonos() {
		return totalAbonos;
	}

	public void setTotalAbonos(String totalAbonos) {
		this.totalAbonos = totalAbonos;
	}

	public String getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(String saldoActual) {
		this.saldoActual = saldoActual;
	}

	@Override
	public String toString() {
		return "EstadoCuentaCabecera [nombreCompleto=" + nombreCompleto
				+ ", nroDni=" + nroDni + ", mesInicial=" + mesInicial
				+ ", annoInicial=" + annoInicial + ", mesFinal=" + mesFinal
				+ ", annoFinal=" + annoFinal + ", nroCuenta=" + nroCuenta
				+ ", cci=" + cci + ", totalTransacciones=" + totalTransacciones
				+ ", totalCargos=" + totalCargos + ", totalAbonos="
				+ totalAbonos + ", saldoActual=" + saldoActual + "]";
	}
	
}
