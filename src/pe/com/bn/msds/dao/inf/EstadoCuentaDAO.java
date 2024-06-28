package pe.com.bn.msds.dao.inf;

import java.util.List;
import java.util.Map;

import pe.com.bn.msds.model.EstadoCuentaCabecera;

public interface EstadoCuentaDAO {

	public List<Map<String, String>> listarEstadoCuentaMovimientos(EstadoCuentaCabecera estadoCuentaCabecera);

}
