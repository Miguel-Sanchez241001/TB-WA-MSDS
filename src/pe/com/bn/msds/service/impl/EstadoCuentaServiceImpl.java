package pe.com.bn.msds.service.impl;

import java.util.List;
import java.util.Map;

import pe.com.bn.msds.dao.impl.EstadoCuentaDAOImpl;
import pe.com.bn.msds.dao.inf.EstadoCuentaDAO;
import pe.com.bn.msds.model.EstadoCuentaCabecera;
import pe.com.bn.msds.service.inf.EstadoCuentaService;

public class EstadoCuentaServiceImpl implements EstadoCuentaService {
	
	private EstadoCuentaDAO estadoCuentaDAO;
	
	public EstadoCuentaServiceImpl() {
		this.estadoCuentaDAO = new EstadoCuentaDAOImpl();
	}
	
	@Override
	public List<Map<String, String>> listarEstadoCuentaMovimientos(EstadoCuentaCabecera estadoCuentaCabecera) {
		
		return this.estadoCuentaDAO.listarEstadoCuentaMovimientos(estadoCuentaCabecera);
	}

}
