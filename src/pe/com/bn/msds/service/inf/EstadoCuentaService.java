package pe.com.bn.msds.service.inf;

import java.util.List;
import java.util.Map;

import pe.com.bn.msds.model.EstadoCuentaCabecera;

public interface EstadoCuentaService {
	
	public List<Map<String, String>> listarEstadoCuentaMovimientos(EstadoCuentaCabecera estadoCuentaCabecera);

}
