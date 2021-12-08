package backend.hyperion.adra.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import backend.hyperion.adra.entity.Recurso;
import backend.hyperion.adra.servicio.RecursoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("api/recurso")
@Api(value = "Microservicios de Gestion de Recursos ", description = "Microservicio de Recurso")
public class RecursoController {

	@Autowired
	private RecursoService recursoService;

	@ApiOperation(value = "Lista de Recursos")
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(value = "query", required = false, defaultValue = "") String query,
			@RequestParam(value = "page", required = false, defaultValue = "-1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "-1") int limit,
			@RequestParam(value = "sortBy", required = false, defaultValue = "") String sortBy,
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("message", "Consulta correcta");

		if (query.equals("") && limit == -1 && "".equals(sortBy)) {
			result.put("data", recursoService.findAll());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && "".equals(sortBy)) {
			result.put("data", recursoService.findAll(query, ""));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && !"".equals(sortBy)) {
			result.put("data", recursoService.findAll(query, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (limit != -1 && page == -1) {
			return new ResponseEntity<>(new Exception("Parámetro page es requerido"), HttpStatus.CONFLICT);
		} else if (page != -1 && limit == -1) {
			return new ResponseEntity<>(new Exception("Parámetro limit es requerido"), HttpStatus.CONFLICT);
		} else {
			result.put("data", recursoService.findAll(query, page, limit, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Registra Datos de un nuevo Recurso")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Recurso recurso, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Recurso data = recursoService.save(recurso);

		result.put("success", true);
		result.put("message", "El Recurso se ha registrado correctamente.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Actualiza datos de un Recurso")
	@PutMapping("/{idRecurso}")
	public ResponseEntity<?> update(@PathVariable(value = "idRecurso") Long idRecurso, @RequestBody Recurso recurso,
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Recurso data = recursoService.findById(idRecurso);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Recurso con Id: " + idRecurso);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			recursoService.save(recurso);
			result.put("success", true);
			result.put("message", "Se ha actualizado los datos del Recurso.");
			result.put("data", recurso);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Obtien datos del Reurso")
	@GetMapping(value = "/{idRecurso}")
	public ResponseEntity<?> findById(@PathVariable(value = "idRecurso") Long idRecurso, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Recurso data = recursoService.findById(idRecurso);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Recurso con Id: " + idRecurso);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Elimina registro de un Recurso ")
	@DeleteMapping(value = "/{idRecurso}")
	public ResponseEntity<?> delete(@PathVariable(value = "idRecurso") Long idRecurso, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Recurso data = recursoService.findById(idRecurso);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe División con id: " + idRecurso);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			// data.setEstado(false);
			recursoService.delete(data);
			result.put("success", true);
			result.put("message", "Se ha eliminado los datos del registro.");
			result.put("data", data);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Obtiene datos del Recurso por ID de la Sesión")
	@GetMapping(value = "/sesion/{idSesion}")
	public ResponseEntity<?> findByIdSesion(@PathVariable(value = "idSesion") Long idSesion, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		List<Recurso> data = recursoService.findBySesion(idSesion);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Recurso con Id: " + idSesion);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
