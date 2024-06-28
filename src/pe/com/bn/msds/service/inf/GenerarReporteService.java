package pe.com.bn.msds.service.inf;

import java.util.List;

import pe.com.bn.msds.model.F07Factibilidad;
import pe.com.bn.msds.model.FiltroGenerarReporte;

public interface GenerarReporteService {

	public List<F07Factibilidad> listarFactibilidad(FiltroGenerarReporte filtro) throws Exception;

}
