package pe.com.bn.msds.dao.inf;

import java.util.List;

import pe.com.bn.msds.model.Bndpf28;

public interface VoucherDAO {

	public List<Bndpf28> datosVoucherByNroPedido(String nroPedido);

}
