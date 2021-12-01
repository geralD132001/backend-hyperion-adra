package backend.hyperion.adra.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import backend.hyperion.adra.entity.Alternativa;

public interface AlternativaRepository extends CrudRepository<Alternativa, Long>  {
	
    @Query("SELECT e FROM Alternativa e WHERE (nombreAlternativa like %:query% or esCorrecta like %:query%)")
    List<Alternativa> findAll(String query, Sort sort);

    @Query("SELECT e FROM Alternativa e WHERE (nombreAlternativa like %:query% or esCorrecta like %:query%)")
    Page<Alternativa> findAllParams(String query, Pageable pageable);


}
