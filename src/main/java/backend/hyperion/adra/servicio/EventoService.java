package backend.hyperion.adra.servicio;

import java.util.HashMap;
import java.util.List;


import backend.hyperion.adra.entity.Evento;

public interface EventoService {
	
	public List<Evento> findAll();

    public Evento findById(Long id);

    public Evento save(Evento evento);

    public void delete(Evento evento);

    public List<Evento> findAll(String query, String sortBy);

    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy);

}