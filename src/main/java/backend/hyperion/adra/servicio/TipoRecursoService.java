package backend.hyperion.adra.servicio;

import java.util.List;

import backend.hyperion.adra.entity.TipoRecurso;

public interface TipoRecursoService {
	
	public List<TipoRecurso> findAll();

    public TipoRecurso findById(Long id);

}
