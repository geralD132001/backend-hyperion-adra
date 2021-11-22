package backend.hyperion.adra.servicio;

import java.util.HashMap;
import java.util.List;
import backend.hyperion.adra.entity.BancoComunal;

public interface BancoService {
	
	public List<BancoComunal> findAll();

    public BancoComunal findById(Long id);

    public BancoComunal save(BancoComunal banco);

    public void delete(BancoComunal banco);

    public List<BancoComunal> findAll(String query, String sortBy);

    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy);

}
