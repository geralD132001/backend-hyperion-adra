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
import backend.hyperion.adra.entity.Recurso;
import backend.hyperion.adra.repository.RecursoRepository;
import backend.hyperion.adra.servicio.RecursoService;


@Service
public class RecursoServiceImpl implements RecursoService {

	@Autowired
	private RecursoRepository recursoRepository;

	@Override
	public List<Recurso> findAll() {
		return (List<Recurso>) recursoRepository.findAll();
	}

	@Override
	public Recurso findById(Long id) {
		return recursoRepository.findById(id).orElse(null);
	}

	@Override
	public Recurso save(Recurso recurso) {
		return recursoRepository.save(recurso);

	}

	@Override
	public void delete(Recurso recurso) {
		recursoRepository.delete(recurso);

	}

	@Override
	public List<Recurso> findAll(String query, String sortBy) {
		Sort sort;
		if (!sortBy.equals("")) {/* sortBy = COLUMNA|ASC O DESC idRecurso|ASC */
			String sortColumn = sortBy.split("\\|")[0];
			String sortDirection = sortBy.split("\\|")[1].toUpperCase();
			sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
		} else {
			sort = Sort.by(Direction.ASC, "idRecurso");
		}
		return recursoRepository.findAll("%" + query.toUpperCase() + "%", sort);
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
			Sort sort = Sort.by(Direction.ASC, "idRecurso");
			pageable = PageRequest.of(page - 1, limit, sort);
		}
		Page<Recurso> data = recursoRepository.findAllParams("%" + query.toUpperCase() + "%", pageable);
		if (!data.getContent().isEmpty()) {
			result.put("items", data.getContent());
		} else {
			result.put("items", new ArrayList<Recurso>());
		}
		result.put("totalPage", data.getTotalPages());
		result.put("totalRows", data.getNumberOfElements());
		result.put("page", page);
		result.put("limit", limit);
		return result;
	}
	
	@Override
	public List<Recurso> findBySesion(Long idSesion) {
		return recursoRepository.findBySesion(idSesion);
	}

}
