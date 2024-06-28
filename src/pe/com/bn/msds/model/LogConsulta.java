package pe.com.bn.msds.model;

import java.math.BigDecimal;

public class LogConsulta {

	private BigDecimal codigoLogConsulta;
	private String tipoConsulta;
	private String nroConsulta;
	private String mesInicial;
	private String annoInicial;
	private String mesFinal;
	private String annoFinal;
	private String nombreUsuario;
	private String oficina;
	private String audiUsuarioReg;
	private String audiFechaReg;
	private String audiUsuarioAct;
	private String audiFechaAct;
	
	public LogConsulta() {
	}

	public BigDecimal getCodigoLogConsulta() {
		return codigoLogConsulta;
	}
	
	public void setCodigoLogConsulta(BigDecimal codigoLogConsulta) {
		this.codigoLogConsulta = codigoLogConsulta;
	}
	
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public String getNroConsulta() {
		return nroConsulta;
	}

	public void setNroConsulta(String nroConsulta) {
		this.nroConsulta = nroConsulta;
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

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getAudiUsuarioReg() {
		return audiUsuarioReg;
	}

	public void setAudiUsuarioReg(String audiUsuarioReg) {
		this.audiUsuarioReg = audiUsuarioReg;
	}

	public String getAudiFechaReg() {
		return audiFechaReg;
	}

	public void setAudiFechaReg(String audiFechaReg) {
		this.audiFechaReg = audiFechaReg;
	}

	public String getAudiUsuarioAct() {
		return audiUsuarioAct;
	}

	public void setAudiUsuarioAct(String audiUsuarioAct) {
		this.audiUsuarioAct = audiUsuarioAct;
	}

	public String getAudiFechaAct() {
		return audiFechaAct;
	}

	public void setAudiFechaAct(String audiFechaAct) {
		this.audiFechaAct = audiFechaAct;
	}

	@Override
	public String toString() {
		return "LogConsulta [codigoLogConsulta=" + codigoLogConsulta
				+ ", tipoConsulta=" + tipoConsulta + ", nroConsulta="
				+ nroConsulta + ", mesInicial=" + mesInicial + ", annoInicial="
				+ annoInicial + ", mesFinal=" + mesFinal + ", annoFinal="
				+ annoFinal + ", nombreUsuario=" + nombreUsuario + ", oficina="
				+ oficina + ", audiUsuarioReg=" + audiUsuarioReg
				+ ", audiFechaReg=" + audiFechaReg + ", audiUsuarioAct="
				+ audiUsuarioAct + ", audiFechaAct=" + audiFechaAct + "]";
	}
	
}
