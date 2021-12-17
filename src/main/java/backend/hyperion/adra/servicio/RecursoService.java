package backend.hyperion.adra.servicio;

import java.util.HashMap;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.util.stream.Stream;
import java.nio.file.Path;
import backend.hyperion.adra.entity.Recurso;

public interface RecursoService {
	
	public List<Recurso> findAll();

    public Recurso findById(Long id);

    public Recurso save(Recurso recurso);

    public void delete(Recurso recurso);

    public List<Recurso> findAll(String query, String sortBy);

    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy);
    
    public List<Recurso> findBySesion(Long idSesion);
    
    public void init();

    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();

    public String deleteFile(String filename);

}
