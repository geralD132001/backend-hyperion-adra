package backend.hyperion.adra.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import backend.hyperion.adra.entity.Pregunta;
import backend.hyperion.adra.repository.PreguntaRepository;
import backend.hyperion.adra.servicio.PreguntaService;

@Service
public class PreguntaServiceImpl implements PreguntaService{
	
	@Autowired
	private PreguntaRepository preguntaRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Pregunta> findAll() {
		return (List<Pregunta>) preguntaRepository.findAll();

	}

	@Override
	public Pregunta findById(Long id) {
		return preguntaRepository.findById(id).orElse(null);
	}

	@Override
	public Pregunta save(Pregunta pregunta) {
		return preguntaRepository.save(pregunta);
	}

	@Override
	public void delete(Pregunta pregunta) {
		preguntaRepository.delete(pregunta);
	}

	@Override
	public List<Pregunta> findAll(String query, String sortBy) {
		Sort sort;
		if (!sortBy.equals("")) {/* sortBy = COLUMNA|ASC O DESC idBanco|ASC */
			String sortColumn = sortBy.split("\\|")[0];
			String sortDirection = sortBy.split("\\|")[1].toUpperCase();
			sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
		} else {
			sort = Sort.by(Direction.ASC, "idPregunta");
		}
		return preguntaRepository.findAll("%" + query.toUpperCase() + "%", sort);
	}

	@Override
	public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy) {
		HashMap<String, Object> result = new HashMap<>();
		Pageable pageable;
		if (!sortBy.equals("")) {
			String sortColumn = sortBy.split("\\|")[0];
			String sortDirection = sortBy.split("\\|")[1].toUpperCase();

			Sort sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
			pageable = PageRequest.of(page - 1, limit, sort);
		} else {
			Sort sort = Sort.by(Direction.ASC, "idPregunta");
			pageable = PageRequest.of(page - 1, limit, sort);
		}

		Page<Pregunta> data = preguntaRepository.findAllParams("%" + query.toUpperCase() + "%", pageable);

		if (!data.getContent().isEmpty()) {
			result.put("items", data.getContent());
		} else {
			result.put("items", new ArrayList<Pregunta>());
		}
		result.put("totalPage", data.getTotalPages());
		result.put("totalRows", data.getNumberOfElements());
		result.put("page", page);
		result.put("limit", limit);
		return result;
	}

}
