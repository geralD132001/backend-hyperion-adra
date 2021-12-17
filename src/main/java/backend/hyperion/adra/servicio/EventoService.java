package backend.hyperion.adra.servicio;

import java.util.HashMap;
import java.util.List;
<<<<<<< HEAD
import backend.hyperion.adra.entity.Evento;

public interface EventoService {

=======


import backend.hyperion.adra.entity.Evento;

public interface EventoService {
>>>>>>> ee18319b15a132d1352f3e58253ac3e073f10e88
	
	public List<Evento> findAll();

    public Evento findById(Long id);

    public Evento save(Evento evento);

    public void delete(Evento evento);

    public List<Evento> findAll(String query, String sortBy);

    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy);
<<<<<<< HEAD
}
=======

}
>>>>>>> ee18319b15a132d1352f3e58253ac3e073f10e88
