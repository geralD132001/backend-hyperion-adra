package backend.hyperion.adra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import backend.hyperion.adra.entity.TipoRecurso;

@Repository
public interface TipoRecursoRepository extends CrudRepository<TipoRecurso,Long> {

}
