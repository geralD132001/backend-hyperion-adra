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
import backend.hyperion.adra.entity.Alternativa;
import backend.hyperion.adra.repository.AlternativaRepository;
import backend.hyperion.adra.servicio.AlternativaService;

@Service
public class AlternativaServiceImpl implements AlternativaService {

	@Autowired
	private AlternativaRepository alternativaRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Alternativa> findAll() {
		return (List<Alternativa>) alternativaRepository.findAll();
	}

	@Override
	public Alternativa findById(Long id) {
		return alternativaRepository.findById(id).orElse(null);
	}

	@Override
	public Alternativa save(Alternativa alternativa) {
		return alternativaRepository.save(alternativa);

	}

	@Override
	public void delete(Alternativa alternativa) {
		alternativaRepository.delete(alternativa);
		
	}

	@Override
	public List<Alternativa> findAll(String query, String sortBy) {
		Sort sort;
		if (!sortBy.equals("")) {
			String sortColumn = sortBy.split("\\|")[0];
			String sortDirection = sortBy.split("\\|")[1].toUpperCase();
			sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
		} else {
			sort = Sort.by(Direction.ASC, "idAlternativa");
		}
		return alternativaRepository.findAll("%" + query.toUpperCase() + "%", sort);
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
			Sort sort = Sort.by(Direction.ASC, "idAlternativa");
			pageable = PageRequest.of(page - 1, limit, sort);
		}

		Page<Alternativa> data = alternativaRepository.findAllParams("%" + query.toUpperCase() + "%", pageable);

		if (!data.getContent().isEmpty()) {
			result.put("items", data.getContent());
		} else {
			result.put("items", new ArrayList<Alternativa>());
		}
		result.put("totalPage", data.getTotalPages());
		result.put("totalRows", data.getNumberOfElements());
		result.put("page", page);
		result.put("limit", limit);
		return result;
	}

}
