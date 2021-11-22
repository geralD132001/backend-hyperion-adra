package backend.hyperion.adra.servicio;

import java.util.HashMap;
import java.util.List;
import backend.hyperion.adra.entity.PedidoOracion;

public interface OracionService {
	
	public List<PedidoOracion> findAll();

    public PedidoOracion findById(Long id);

    public PedidoOracion save(PedidoOracion oracion);

    public void delete(PedidoOracion oracion);

    public List<PedidoOracion> findAll(String query, String sortBy);

    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy);

}
