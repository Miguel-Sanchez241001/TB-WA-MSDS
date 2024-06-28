package pe.com.bn.msds.dao.inf;

import java.util.List;

import pe.com.bn.msds.model.FiltroLogConsulta;
import pe.com.bn.msds.model.LogConsulta;

public interface LogConsultaDAO {
	
	public boolean insertLogConsulta(LogConsulta logConsulta);
	public List<LogConsulta> listarLogConsulta(FiltroLogConsulta filtroLogConsulta);

}
