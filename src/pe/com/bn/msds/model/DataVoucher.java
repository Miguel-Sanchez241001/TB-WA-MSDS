package pe.com.bn.msds.model;

public class DataVoucher {

	private String nroPedido;
	private String nroCuenta;
	private String fechaSolicitud;
	private String horaSolicitud;
	private String periodoInicial;
	private String periodoFinal;
	
	public DataVoucher() {
	}

	public String getNroPedido() {
		return nroPedido;
	}

	public void setNroPedido(String nroPedido) {
		this.nroPedido = nroPedido;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getHoraSolicitud() {
		return horaSolicitud;
	}

	public void setHoraSolicitud(String horaSolicitud) {
		this.horaSolicitud = horaSolicitud;
	}

	public String getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public String getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	@Override
	public String toString() {
		return "DataVoucher [nroPedido=" + nroPedido + ", nroCuenta="
				+ nroCuenta + ", fechaSolicitud=" + fechaSolicitud
				+ ", horaSolicitud=" + horaSolicitud + ", periodoInicial="
				+ periodoInicial + ", periodoFinal=" + periodoFinal + "]";
	}
	
}
