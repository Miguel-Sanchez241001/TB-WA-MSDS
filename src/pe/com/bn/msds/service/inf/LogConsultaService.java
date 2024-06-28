package pe.com.bn.msds.service.inf;

import java.util.List;

import pe.com.bn.msds.model.FiltroLogConsulta;
import pe.com.bn.msds.model.LogConsulta;

public interface LogConsultaService {

	public boolean insertLogConsulta(LogConsulta logConsulta);
	public List<LogConsulta> listarLogConsulta(FiltroLogConsulta filtroLogConsulta);

}
