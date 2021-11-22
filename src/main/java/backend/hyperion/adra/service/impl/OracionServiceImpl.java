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
import backend.hyperion.adra.entity.PedidoOracion;
import backend.hyperion.adra.repository.OracionRepository;
import backend.hyperion.adra.servicio.OracionService;

@Service
public class OracionServiceImpl implements OracionService {

	@Autowired
	private OracionRepository oracionRepository;

	@Transactional(readOnly = true)
	@Override
	public List<PedidoOracion> findAll() {
		return (List<PedidoOracion>) oracionRepository.findAll();
	}

	@Override
	public PedidoOracion findById(Long id) {
		return oracionRepository.findById(id).orElse(null);
	}

	@Override
	public PedidoOracion save(PedidoOracion oracion) {
		return oracionRepository.save(oracion);
	}

	@Override
	public void delete(PedidoOracion oracion) {
		oracionRepository.delete(oracion);

	}

	@Override
	public List<PedidoOracion> findAll(String query, String sortBy) {
		Sort sort;
		if (!sortBy.equals("")) {/* sortBy = COLUMNA|ASC O DESC idEmployee|ASC */
			String sortColumn = sortBy.split("\\|")[0];
			String sortDirection = sortBy.split("\\|")[1].toUpperCase();
			sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
		} else {
			sort = Sort.by(Direction.ASC, "idPedido");
		}
		return oracionRepository.findAll("%" + query.toUpperCase() + "%", sort);
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
			Sort sort = Sort.by(Direction.ASC, "idPedido");
			pageable = PageRequest.of(page - 1, limit, sort);
		}

		Page<PedidoOracion> data = oracionRepository.findAllParams("%" + query.toUpperCase() + "%", pageable);

		if (!data.getContent().isEmpty()) {
			result.put("items", data.getContent());
		} else {
			result.put("items", new ArrayList<PedidoOracion>());
		}
		result.put("totalPage", data.getTotalPages());
		result.put("totalRows", data.getNumberOfElements());
		result.put("page", page);
		result.put("limit", limit);
		return result;
	}

}
