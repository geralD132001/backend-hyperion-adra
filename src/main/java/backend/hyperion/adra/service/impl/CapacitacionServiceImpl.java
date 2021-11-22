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
import backend.hyperion.adra.entity.Capacitacion;
import backend.hyperion.adra.repository.CapacitacionRepository;
import backend.hyperion.adra.servicio.CapacitacionService;

@Service
public class CapacitacionServiceImpl implements CapacitacionService {

	@Autowired
	private CapacitacionRepository capacitacionRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Capacitacion> findAll() {
		return (List<Capacitacion>) capacitacionRepository.findAll();
	}

	@Override
	public Capacitacion findById(Long id) {
		return capacitacionRepository.findById(id).orElse(null);
	}

	@Override
	public Capacitacion save(Capacitacion capacitacion) {
		return capacitacionRepository.save(capacitacion);
	}

	@Override
	public void delete(Capacitacion capacitacion) {
		capacitacionRepository.delete(capacitacion);
	}

	@Override
	public List<Capacitacion> findAll(String query, String sortBy) {
		Sort sort;
		if (!sortBy.equals("")) {/* sortBy = COLUMNA|ASC O DESC idCapacitacion|ASC */
			String sortColumn = sortBy.split("\\|")[0];
			String sortDirection = sortBy.split("\\|")[1].toUpperCase();
			sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
		} else {
			sort = Sort.by(Direction.ASC, "idCapacitacion");
		}
		return capacitacionRepository.findAll("%" + query.toUpperCase() + "%", sort);
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
			Sort sort = Sort.by(Direction.ASC, "idCapacitacion");
			pageable = PageRequest.of(page - 1, limit, sort);
		}

		Page<Capacitacion> data = capacitacionRepository.findAllParams("%" + query.toUpperCase() + "%", pageable);

		if (!data.getContent().isEmpty()) {
			result.put("items", data.getContent());
		} else {
			result.put("items", new ArrayList<Capacitacion>());
		}
		result.put("totalPage", data.getTotalPages());
		result.put("totalRows", data.getNumberOfElements());
		result.put("page", page);
		result.put("limit", limit);
		return result;
	}

}
