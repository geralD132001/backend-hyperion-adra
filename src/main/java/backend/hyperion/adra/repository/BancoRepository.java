package backend.hyperion.adra.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import backend.hyperion.adra.entity.BancoComunal;

@Repository
public interface BancoRepository extends CrudRepository<BancoComunal, Long> {
	
    @Query("SELECT e FROM BancoComunal e WHERE (nombreBanco like %:query% or nombreEncargado  like %:query%)")
    List<BancoComunal> findAll(String query, Sort sort);

    @Query("SELECT e FROM BancoComunal e WHERE (nombreBanco like %:query% or nombreEncargado  like %:query%)")
    Page<BancoComunal> findAllParams(String query, Pageable pageable);

}
