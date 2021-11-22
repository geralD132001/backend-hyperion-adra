package backend.hyperion.adra.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import backend.hyperion.adra.entity.Socia;

@Repository
public interface SociaRepository extends CrudRepository<Socia, Long> {

	@Query("SELECT e FROM Socia e WHERE (estadoSocia like %:query%)")
	List<Socia> findAll(String query, Sort sort);

	@Query("SELECT e FROM Socia e WHERE (estadoSocia like %:query%)")
	Page<Socia> findAllParams(String query, Pageable pageable);
}
