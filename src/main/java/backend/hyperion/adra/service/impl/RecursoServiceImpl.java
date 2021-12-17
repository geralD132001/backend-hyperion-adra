package backend.hyperion.adra.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import backend.hyperion.adra.entity.Recurso;
import backend.hyperion.adra.repository.RecursoRepository;
import backend.hyperion.adra.servicio.RecursoService;

@Service
public class RecursoServiceImpl implements RecursoService {

	@Autowired
	private RecursoRepository recursoRepository;

	private final Path root = Paths.get("archivos");

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

	@Override
	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("No se puede inicializar la carpeta archivos");
		}
	}

	@Override
	public void save(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			throw new RuntimeException("No se puede guardar el archivo. Error " + e.getMessage());
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("No se puede leer el archivo ");
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (RuntimeException | IOException e) {
			throw new RuntimeException("No se pueden cargar los archivos ");
		}
	}

	@Override
	public String deleteFile(String filename) {
		try {
			Boolean delete = Files.deleteIfExists(this.root.resolve(filename));
			return "Borrado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error Borrando ";
		}
	}

}
