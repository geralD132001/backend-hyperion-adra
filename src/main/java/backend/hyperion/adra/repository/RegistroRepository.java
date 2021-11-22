package backend.hyperion.adra.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import backend.hyperion.adra.entity.Registro;


@Repository
public interface RegistroRepository extends CrudRepository<Registro, Long>{
	
	@Query("SELECT e FROM Registro e WHERE (descripcionValoracion like %:query% or cantidadVisto like %:query%)")
	List<Registro> findAll(String query, Sort sort);

	@Query("SELECT e FROM Registro e WHERE (descripcionValoracion like %:query% or cantidadVisto like %:query%)")
	Page<Registro> findAllParams(String query, Pageable pageable);

}
