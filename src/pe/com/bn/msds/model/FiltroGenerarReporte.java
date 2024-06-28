package pe.com.bn.msds.model;

public class FiltroGenerarReporte {
	
	private String fechaInicial;
	private String fechaFinal;
	private String reporteDescargar;
	
	public FiltroGenerarReporte() {
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
	
	public String getReporteDescargar() {
		return reporteDescargar;
	}
	
	public void setReporteDescargar(String reporteDescargar) {
		this.reporteDescargar = reporteDescargar;
	}

	@Override
	public String toString() {
		return "FiltroLogConsulta [fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + ", reporteDescargar=" + reporteDescargar + "]";
	}
	
}
