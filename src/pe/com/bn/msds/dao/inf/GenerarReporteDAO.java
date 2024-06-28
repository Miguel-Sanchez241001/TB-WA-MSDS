package pe.com.bn.msds.dao.inf;

import java.util.List;

import pe.com.bn.msds.model.F07Factibilidad;
import pe.com.bn.msds.model.FiltroGenerarReporte;


public interface GenerarReporteDAO {
	
	public List<F07Factibilidad> listarFactibilidad(FiltroGenerarReporte filtro)  throws Exception;

}
