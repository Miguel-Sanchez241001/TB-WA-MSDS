package pe.com.bn.msds.service.impl;

import java.util.List;

import pe.com.bn.msds.dao.impl.VoucherDAOImpl;
import pe.com.bn.msds.dao.inf.VoucherDAO;
import pe.com.bn.msds.model.Bndpf28;
import pe.com.bn.msds.service.inf.VoucherService;

public class VoucherServiceImpl implements VoucherService {

	private VoucherDAO voucherDAO;
	
	public VoucherServiceImpl() {
		this.voucherDAO = new VoucherDAOImpl();
	}
	
	@Override
	public List<Bndpf28> datosVoucherByNroPedido(String nroPedido) {
		return this.voucherDAO.datosVoucherByNroPedido(nroPedido);
	}

}
