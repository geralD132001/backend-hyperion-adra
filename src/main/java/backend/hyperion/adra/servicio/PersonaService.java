package backend.hyperion.adra.servicio;

import java.util.HashMap;
import java.util.List;


import backend.hyperion.adra.entity.Persona;

public interface PersonaService {
	
	public List<Persona> findAll();

    public Persona findById(Long id);

    public Persona save(Persona persona);

    public void delete(Persona persona);

    public List<Persona> findAll(String query, String sortBy);

    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy);

}
