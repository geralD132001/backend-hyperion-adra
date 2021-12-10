package backend.hyperion.adra.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import backend.hyperion.adra.entity.TipoRecurso;
import backend.hyperion.adra.repository.TipoRecursoRepository;
import backend.hyperion.adra.servicio.TipoRecursoService;


@Service
public class TipoRecursoServiceImpl implements TipoRecursoService {
	
	@Autowired
	private TipoRecursoRepository tiporecursoRepository;

	@Transactional(readOnly = true)
	@Override
	public List<TipoRecurso> findAll() {
		return (List<TipoRecurso>) tiporecursoRepository.findAll();
	}

	@Override
	public TipoRecurso findById(Long id) {
		return tiporecursoRepository.findById(id).orElse(null);
	}

}
