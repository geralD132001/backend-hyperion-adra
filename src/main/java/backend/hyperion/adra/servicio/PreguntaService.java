package backend.hyperion.adra.servicio;

import java.util.HashMap;
import java.util.List;
import backend.hyperion.adra.entity.Pregunta;

public interface PreguntaService {
	
	public List<Pregunta> findAll();

    public Pregunta findById(Long id);

    public Pregunta save(Pregunta pregunta);

    public void delete(Pregunta pregunta);

    public List<Pregunta> findAll(String query, String sortBy);

    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy);

}
