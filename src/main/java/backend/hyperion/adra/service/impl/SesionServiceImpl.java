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
import backend.hyperion.adra.entity.Sesion;
import backend.hyperion.adra.repository.SesionRepository;
import backend.hyperion.adra.servicio.SesionService;

@Service
public class SesionServiceImpl implements SesionService {

	@Autowired
	private SesionRepository sesionRepository;

	@Override
	public List<Sesion> findAll() {
		return (List<Sesion>) sesionRepository.findAll();
	}

	@Override
	public Sesion findById(Long id) {
		return sesionRepository.findById(id).orElse(null);
	}

	@Override
	public Sesion save(Sesion sesion) {
		return sesionRepository.save(sesion);
	}

	@Override
	public void delete(Sesion sesion) {
		sesionRepository.delete(sesion);
	}

	@Override
	public List<Sesion> findAll(String query, String sortBy) {
		Sort sort;
		if (!sortBy.equals("")) {/* sortBy = COLUMNA|ASC O DESC idEmployee|ASC */
			String sortColumn = sortBy.split("\\|")[0];
			String sortDirection = sortBy.split("\\|")[1].toUpperCase();
			sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
		} else {
			sort = Sort.by(Direction.ASC, "idSesion");
		}
		return sesionRepository.findAll("%" + query.toUpperCase() + "%", sort);
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
            Sort sort = Sort.by(Direction.ASC, "idSesion");
            pageable = PageRequest.of(page - 1, limit, sort);
        }

        Page<Sesion> data = sesionRepository.findAllParams("%" + query.toUpperCase() + "%", pageable);

        if (!data.getContent().isEmpty()) {
            result.put("items", data.getContent());
        } else {
            result.put("items", new ArrayList<Sesion>());
        }
        result.put("totalPage", data.getTotalPages());
        result.put("totalRows", data.getNumberOfElements());
        result.put("page", page);
        result.put("limit", limit);
        return result;
	}

}
