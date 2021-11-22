package backend.hyperion.adra.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import backend.hyperion.adra.entity.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona,Long>{ 

    @Query("SELECT e FROM Persona e WHERE (nombrePersona like %:query% or apellidoPaterno  like %:query%)")
    List<Persona> findAll(String query, Sort sort);

    @Query("SELECT e FROM Persona e WHERE (nombrePersona like %:query% or apellidoPaterno  like %:query%)")
    Page<Persona> findAllParams(String query, Pageable pageable);
}
