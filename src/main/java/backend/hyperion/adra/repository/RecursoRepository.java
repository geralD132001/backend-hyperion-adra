package backend.hyperion.adra.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import backend.hyperion.adra.entity.Recurso;


public interface RecursoRepository extends CrudRepository<Recurso, Long> {
	
    @Query("SELECT e FROM Recurso e WHERE (nombrePersona like %:query%)")
    List<Recurso> findAll(String query, Sort sort);

    @Query("SELECT e FROM Recurso e WHERE (nombrePersona like %:query%)")
    Page<Recurso> findAllParams(String query, Pageable pageable);

}
