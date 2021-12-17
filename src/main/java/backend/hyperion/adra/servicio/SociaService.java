package backend.hyperion.adra.servicio;

import java.util.HashMap;
import java.util.List;
import backend.hyperion.adra.entity.Socia;

public interface SociaService {
	
	public List<Socia> findAll();

    public Socia findById(Long id);

    public Socia save(Socia socia);

    public void delete(Socia socia);

    public List<Socia> findAll(String query, String sortBy);

    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy);
    
    public List<Socia> findByBanco(Long idBanco);

}
