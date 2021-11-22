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
import backend.hyperion.adra.entity.Persona;
import backend.hyperion.adra.repository.PersonaRepository;
import backend.hyperion.adra.servicio.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Persona> findAll() {
		return (List<Persona>) personaRepository.findAll();
	}

	@Override
	public Persona findById(Long id) {
		return personaRepository.findById(id).orElse(null);
	}

	@Override
	public Persona save(Persona persona) {
		return personaRepository.save(persona);
	}

	@Override
	public void delete(Persona persona) {
		personaRepository.delete(persona);
	}

	@Override
	public List<Persona> findAll(String query, String sortBy) {
		Sort sort;
		if (!sortBy.equals("")) {/* sortBy = COLUMNA|ASC O DESC idEmployee|ASC */
			String sortColumn = sortBy.split("\\|")[0];
			String sortDirection = sortBy.split("\\|")[1].toUpperCase();
			sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
		} else {
			sort = Sort.by(Direction.ASC, "idPersona");
		}
		return personaRepository.findAll("%" + query.toUpperCase() + "%", sort);
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
			Sort sort = Sort.by(Direction.ASC, "idPersona");
			pageable = PageRequest.of(page - 1, limit, sort);
		}
		Page<Persona> data = personaRepository.findAllParams("%" + query.toUpperCase() + "%", pageable);
		if (!data.getContent().isEmpty()) {
			result.put("items", data.getContent());
		} else {
			result.put("items", new ArrayList<Persona>());
		}
		result.put("totalPage", data.getTotalPages());
		result.put("totalRows", data.getNumberOfElements());
		result.put("page", page);
		result.put("limit", limit);
		return result;
	}

}
