package backend.hyperion.adra.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import backend.hyperion.adra.entity.PedidoOracion;

@Repository
public interface OracionRepository extends CrudRepository<PedidoOracion, Long> {

	@Query("SELECT e FROM BancoComunal e WHERE (descripcionOracion like %:query% or estadoOracion like %:query%)")
	List<PedidoOracion> findAll(String query, Sort sort);

	@Query("SELECT e FROM Capacitacion e WHERE (descripcionOracion like %:query% or estadoOracion like %:query%)")
	Page<PedidoOracion> findAllParams(String query, Pageable pageable);

}
