package pe.com.bn.msds.dao.inf;

 
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import pe.com.bn.msds.config.CustomUser;
import pe.com.bn.msds.model.Auditoria;
import pe.com.bn.msds.model.InvalidColumnCountException;
import pe.com.bn.msds.model.Programa;

 
public interface ParametrosDAO {
	
	public Map<String,String> cargarArchivoParametro(Integer tipo,InputStream archivo,CustomUser customer) throws InvalidColumnCountException;

	public Map<String,String> actulizarParametro(String tipo,Object param,CustomUser customer);

	public Map<String,String> nuevoParametro(String tipo,Object param,CustomUser customer);

	public <T> List<T> getAllParametros(String tipo);
	public <T> List<T> getAllParametros(String tipo,Integer id,CustomUser customer);
	public void registrarLogAuditoria(String accion,CustomUser usuario,String tabla,String valor);

	public List<Auditoria> buscarRegistros();

	public List<Auditoria> buscarRegistros(Date date);

	public List<Auditoria> buscarRegistros(Date date, Date dateFin);

	public List<Programa> buscarProgramas(Integer opcion,
			String forFechaInicio, String forFechaFin, String forDiaDate);
}