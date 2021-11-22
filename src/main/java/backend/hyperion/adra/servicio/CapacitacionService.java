package backend.hyperion.adra.servicio;

import java.util.HashMap;
import java.util.List;
import backend.hyperion.adra.entity.Capacitacion;

public interface CapacitacionService {
	
	public List<Capacitacion> findAll();

    public Capacitacion findById(Long id);

    public Capacitacion save(Capacitacion capacitacion);

    public void delete(Capacitacion capacitacion);

    public List<Capacitacion> findAll(String query, String sortBy);

    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy);

}
