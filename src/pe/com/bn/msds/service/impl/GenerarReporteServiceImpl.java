package pe.com.bn.msds.service.impl;

import java.util.List;

import pe.com.bn.msds.dao.impl.GenerarReporteDAOImpl;
import pe.com.bn.msds.dao.inf.GenerarReporteDAO;
import pe.com.bn.msds.model.F07Factibilidad;
import pe.com.bn.msds.model.FiltroGenerarReporte;
import pe.com.bn.msds.service.inf.GenerarReporteService;

public class GenerarReporteServiceImpl implements GenerarReporteService {

	private GenerarReporteDAO generarReporteDAO;
	
	public GenerarReporteServiceImpl() {
		this.generarReporteDAO = new GenerarReporteDAOImpl();
	}
	
	@Override
	public List<F07Factibilidad> listarFactibilidad(FiltroGenerarReporte filtro)  throws Exception {
		return this.generarReporteDAO.listarFactibilidad(filtro);
	}

}
