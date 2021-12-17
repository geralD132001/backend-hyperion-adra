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

import backend.hyperion.adra.entity.Socia;
import backend.hyperion.adra.repository.SociaRepository;
import backend.hyperion.adra.servicio.SociaService;

@Service
public class SociaServiceImpl implements SociaService {

	@Autowired
	private SociaRepository sociaRepository;;

	@Override
	public List<Socia> findAll() {
		return (List<Socia>) sociaRepository.findAll();
	}

	@Override
	public Socia findById(Long id) {
		return sociaRepository.findById(id).orElse(null);
	}

	@Override
	public Socia save(Socia socia) {
		return sociaRepository.save(socia);
	}

	@Override
	public void delete(Socia socia) {
		sociaRepository.delete(socia);
	}

	@Override
	public List<Socia> findAll(String query, String sortBy) {
		Sort sort;
		if (!sortBy.equals("")) {/* sortBy = COLUMNA|ASC O DESC idEmployee|ASC */
			String sortColumn = sortBy.split("\\|")[0];
			String sortDirection = sortBy.split("\\|")[1].toUpperCase();
			sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
		} else {
			sort = Sort.by(Direction.ASC, "idSocia");
		}
		return sociaRepository.findAll("%" + query.toUpperCase() + "%", sort);
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
			Sort sort = Sort.by(Direction.ASC, "idSocia");
			pageable = PageRequest.of(page - 1, limit, sort);
		}

		Page<Socia> data = sociaRepository.findAllParams("%" + query.toUpperCase() + "%", pageable);

		if (!data.getContent().isEmpty()) {
			result.put("items", data.getContent());
		} else {
			result.put("items", new ArrayList<Socia>());
		}
		result.put("totalPage", data.getTotalPages());
		result.put("totalRows", data.getNumberOfElements());
		result.put("page", page);
		result.put("limit", limit);
		return result;
	}




	@Override
	public List<Socia> findByBanco(Long idBanco) {
		return sociaRepository.findByBanco(idBanco);
	}

}
