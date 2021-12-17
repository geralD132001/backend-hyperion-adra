package backend.hyperion.adra.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import backend.hyperion.adra.entity.Capacitacion;

@Repository
public interface CapacitacionRepository extends CrudRepository<Capacitacion, Long> {
	
    @Query("SELECT e FROM Capacitacion e WHERE (nombreCapacitacion like %:query% or descripcionCapacitacion like %:query%)")
    List<Capacitacion> findAll(String query, Sort sort);

    @Query("SELECT e FROM Capacitacion e WHERE (nombreCapacitacion like %:query% or descripcionCapacitacion like %:query%)")
    Page<Capacitacion> findAllParams(String query, Pageable pageable);
   

    
  
    
}
