package backend.hyperion.adra.servicio;

import java.util.HashMap;
import java.util.List;

import backend.hyperion.adra.entity.Recurso;
import backend.hyperion.adra.entity.Sesion;

public interface SesionService {
	
	public List<Sesion> findAll();

    public Sesion findById(Long id);

    public Sesion save(Sesion sesion);

    public void delete(Sesion sesion);

    public List<Sesion> findAll(String query, String sortBy);

    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy);

    public List<Sesion> findByCapacitacion(Long idCapacitacion);

}
