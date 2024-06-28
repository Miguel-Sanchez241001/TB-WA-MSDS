package pe.com.bn.msds.service.impl;

import java.util.List;

import pe.com.bn.msds.dao.impl.LogConsultaDAOImpl;
import pe.com.bn.msds.dao.inf.LogConsultaDAO;
import pe.com.bn.msds.model.FiltroLogConsulta;
import pe.com.bn.msds.model.LogConsulta;
import pe.com.bn.msds.service.inf.LogConsultaService;

public class LogConsultaServiceImpl implements LogConsultaService {

	private LogConsultaDAO logConsultaDAO;
	
	public LogConsultaServiceImpl() {
		this.logConsultaDAO = new LogConsultaDAOImpl();
	}
	
	@Override
	public boolean insertLogConsulta(LogConsulta logConsulta) {
		return logConsultaDAO.insertLogConsulta(logConsulta);
	}

	@Override
	public List<LogConsulta> listarLogConsulta(FiltroLogConsulta filtroLogConsulta) {
		
		return logConsultaDAO.listarLogConsulta(filtroLogConsulta);
	}
	
}
