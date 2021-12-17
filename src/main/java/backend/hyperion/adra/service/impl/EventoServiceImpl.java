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
<<<<<<< HEAD
import org.springframework.transaction.annotation.Transactional;
import backend.hyperion.adra.entity.Capacitacion;
=======
>>>>>>> ee18319b15a132d1352f3e58253ac3e073f10e88
import backend.hyperion.adra.entity.Evento;
import backend.hyperion.adra.repository.EventoRepository;
import backend.hyperion.adra.servicio.EventoService;

<<<<<<< HEAD
=======

>>>>>>> ee18319b15a132d1352f3e58253ac3e073f10e88
@Service
public class EventoServiceImpl implements EventoService {

	@Autowired
	private EventoRepository eventoRepository;

<<<<<<< HEAD
	@Transactional(readOnly = true)
=======
>>>>>>> ee18319b15a132d1352f3e58253ac3e073f10e88
	@Override
	public List<Evento> findAll() {
		return (List<Evento>) eventoRepository.findAll();
	}

	@Override
	public Evento findById(Long id) {
		return eventoRepository.findById(id).orElse(null);
	}

	@Override
	public Evento save(Evento evento) {
		return eventoRepository.save(evento);
<<<<<<< HEAD
=======

>>>>>>> ee18319b15a132d1352f3e58253ac3e073f10e88
	}

	@Override
	public void delete(Evento evento) {
		eventoRepository.delete(evento);
<<<<<<< HEAD
=======

>>>>>>> ee18319b15a132d1352f3e58253ac3e073f10e88
	}

	@Override
	public List<Evento> findAll(String query, String sortBy) {
		Sort sort;
<<<<<<< HEAD
		if (!sortBy.equals("")) {/* sortBy = COLUMNA|ASC O DESC idEvento|ASC */
=======
		if (!sortBy.equals("")) {/* sortBy = COLUMNA|ASC O DESC idRecurso|ASC */
>>>>>>> ee18319b15a132d1352f3e58253ac3e073f10e88
			String sortColumn = sortBy.split("\\|")[0];
			String sortDirection = sortBy.split("\\|")[1].toUpperCase();
			sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
		} else {
			sort = Sort.by(Direction.ASC, "idEvento");
		}
		return eventoRepository.findAll("%" + query.toUpperCase() + "%", sort);
	}

	@Override
	public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy) {
		HashMap<String, Object> result = new HashMap<>();
		Pageable pageable;
<<<<<<< HEAD
=======

>>>>>>> ee18319b15a132d1352f3e58253ac3e073f10e88
		if (!sortBy.equals("")) {
			String sortColumn = sortBy.split("\\|")[0];
			String sortDirection = sortBy.split("\\|")[1].toUpperCase();

			Sort sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
			pageable = PageRequest.of(page - 1, limit, sort);
		} else {
<<<<<<< HEAD
			Sort sort = Sort.by(Direction.ASC, "idEvento");
			pageable = PageRequest.of(page - 1, limit, sort);
		}

		Page<Evento> data = eventoRepository.findAllParams("%" + query.toUpperCase() + "%", pageable);

		if (!data.getContent().isEmpty()) {
			result.put("items", data.getContent());
		} else {
			result.put("items", new ArrayList<Capacitacion>());
=======
			Sort sort = Sort.by(Direction.ASC, "idRecurso");
			pageable = PageRequest.of(page - 1, limit, sort);
		}
		Page<Evento> data = eventoRepository.findAllParams("%" + query.toUpperCase() + "%", pageable);
		if (!data.getContent().isEmpty()) {
			result.put("items", data.getContent());
		} else {
			result.put("items", new ArrayList<Evento>());
>>>>>>> ee18319b15a132d1352f3e58253ac3e073f10e88
		}
		result.put("totalPage", data.getTotalPages());
		result.put("totalRows", data.getNumberOfElements());
		result.put("page", page);
		result.put("limit", limit);
		return result;
	}

<<<<<<< HEAD
}
=======
}
>>>>>>> ee18319b15a132d1352f3e58253ac3e073f10e88
