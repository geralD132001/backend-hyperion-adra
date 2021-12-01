package backend.hyperion.adra.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import backend.hyperion.adra.entity.Pregunta;

public interface PreguntaRepository extends CrudRepository<Pregunta,Long> {
	
    @Query("SELECT e FROM Pregunta e WHERE (nombrePregunta like %:query%)")
    List<Pregunta> findAll(String query, Sort sort);

    @Query("SELECT e FROM Pregunta e WHERE (nombrePregunta like %:query%)")
    Page<Pregunta> findAllParams(String query, Pageable pageable);

}
