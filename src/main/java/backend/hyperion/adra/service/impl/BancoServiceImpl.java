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
import backend.hyperion.adra.entity.BancoComunal;
import backend.hyperion.adra.repository.BancoRepository;
import backend.hyperion.adra.servicio.BancoService;

@Service
public class BancoServiceImpl implements BancoService {

	@Autowired
	private BancoRepository bancoRepository;

	@Transactional(readOnly = true)
	@Override
	public List<BancoComunal> findAll() {
		return (List<BancoComunal>) bancoRepository.findAll();
	}

	@Override
	public BancoComunal findById(Long id) {
		return bancoRepository.findById(id).orElse(null);
	}

	@Override
	public BancoComunal save(BancoComunal banco) {
		return bancoRepository.save(banco);
	}

	@Override
	public void delete(BancoComunal banco) {
		bancoRepository.delete(banco);

	}

	@Override
	public List<BancoComunal> findAll(String query, String sortBy) {
		Sort sort;
		if (!sortBy.equals("")) {/* sortBy = COLUMNA|ASC O DESC idBanco|ASC */
			String sortColumn = sortBy.split("\\|")[0];
			String sortDirection = sortBy.split("\\|")[1].toUpperCase();
			sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
		} else {
			sort = Sort.by(Direction.ASC, "idBanco");
		}
		return bancoRepository.findAll("%" + query.toUpperCase() + "%", sort);
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
			Sort sort = Sort.by(Direction.ASC, "idBanco");
			pageable = PageRequest.of(page - 1, limit, sort);
		}

		Page<BancoComunal> data = bancoRepository.findAllParams("%" + query.toUpperCase() + "%", pageable);

		if (!data.getContent().isEmpty()) {
			result.put("items", data.getContent());
		} else {
			result.put("items", new ArrayList<BancoComunal>());
		}
		result.put("totalPage", data.getTotalPages());
		result.put("totalRows", data.getNumberOfElements());
		result.put("page", page);
		result.put("limit", limit);
		return result;
	}

}
