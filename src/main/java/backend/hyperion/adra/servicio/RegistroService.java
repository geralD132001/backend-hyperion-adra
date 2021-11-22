package backend.hyperion.adra.servicio;

import java.util.HashMap;
import java.util.List;
import backend.hyperion.adra.entity.Registro;

public interface RegistroService {

	public List<Registro> findAll();

    public Registro findById(Long id);

    public Registro save(Registro registro);

    public void delete(Registro registro);

    public List<Registro> findAll(String query, String sortBy);

    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy);
    
}
