package backend.hyperion.adra.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import backend.hyperion.adra.entity.Capacitacion;

public interface CapacitacionRepositoryProcedure extends JpaRepository<Capacitacion, Long>{
	
	 @Query(value = "{call lista_procedure()}", nativeQuery = true)
	    List<Capacitacion> listaProcedure();

	    @Query(value = "{call id_procedure(:idCapacitacionIn)}", nativeQuery = true)
	    Optional<Capacitacion> idProcedure(@Param("idCapacitacionIn") Long idCapacitacionIn);


	    @Query(value = "{call save_procedure(:nombreCapacitacionIn, :descripcionCapacitacionIn, :cantRecursoIn, :estadoCapacitacionIn)}", nativeQuery = true)
	    void saveProcedure(
	            @Param("nombreCapacitacionIn")String nombreCapacitacionIn,
	            @Param("descripcionCapacitacionIn")String descripcionCapacitacionIn,
	            @Param("cantRecursoIn")Integer cantRecursoIn,
	            @Param("estadoCapacitacionIn")String estadoCapacitacionIn
	    );


	    @Query(value = "{call borrar_procedure(:idCapacitacionIn)}", nativeQuery = true)
	    void borrarProcedure(@Param("idCapacitacionIn") Long idCapacitacionIn);

}
