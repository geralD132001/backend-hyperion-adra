package backend.hyperion.adra.repository;

import java.util.List;
<<<<<<< HEAD
=======

>>>>>>> ee18319b15a132d1352f3e58253ac3e073f10e88
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import backend.hyperion.adra.entity.Evento;

<<<<<<< HEAD

@Repository
public interface EventoRepository extends CrudRepository<Evento, Long> {
	
    @Query("SELECT e FROM Evento e WHERE (nombreEvento like %:query%)")
    List<Evento> findAll(String query, Sort sort);

    @Query("SELECT e FROM Evento e WHERE (nombreEvento like %:query%)")
    Page<Evento> findAllParams(String query, Pageable pageable);

}
=======
@Repository
public interface EventoRepository extends CrudRepository<Evento,Long>{ 

    @Query("SELECT e FROM Evento e WHERE (nombreEvento like %:query% or descripEvento  like %:query%)")
    List<Evento> findAll(String query, Sort sort);

    @Query("SELECT e FROM Evento e WHERE (nombreEvento like %:query% or descripEvento like %:query%)")
    Page<Evento> findAllParams(String query, Pageable pageable);
}

>>>>>>> ee18319b15a132d1352f3e58253ac3e073f10e88
