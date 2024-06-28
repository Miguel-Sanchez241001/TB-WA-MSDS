package pe.com.bn.msds.service.inf;

import java.util.List;

import pe.com.bn.msds.model.Bndpf28;

public interface VoucherService {
	
	public List<Bndpf28> datosVoucherByNroPedido(String nroPedido);

}
