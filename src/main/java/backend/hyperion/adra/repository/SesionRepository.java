package backend.hyperion.adra.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import backend.hyperion.adra.entity.Sesion;

@Repository
public interface SesionRepository extends CrudRepository<Sesion, Long> {

	@Query("SELECT e FROM Sesion e WHERE (DescripcionTema like %:query% or DescripcionSecion like %:query%)")
	List<Sesion> findAll(String query, Sort sort);

	@Query("SELECT e FROM Sesion e WHERE (DescripcionTema like %:query% or DescripcionSecion like %:query%)")
	Page<Sesion> findAllParams(String query, Pageable pageable);

    @Query("SELECT e FROM Sesion e WHERE capacitacion.idCapacitacion = :idCapacitacion")
    List<Sesion> findByCapacitacion(Long idCapacitacion);
}
