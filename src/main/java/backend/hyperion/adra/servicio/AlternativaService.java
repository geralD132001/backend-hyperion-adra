package backend.hyperion.adra.servicio;

import java.util.HashMap;
import java.util.List;
import backend.hyperion.adra.entity.Alternativa;

public interface AlternativaService {
	
	public List<Alternativa> findAll();

    public Alternativa findById(Long id);

    public Alternativa save(Alternativa alternativa);

    public void delete(Alternativa alternativa);

    public List<Alternativa> findAll(String query, String sortBy);

    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy);

}
