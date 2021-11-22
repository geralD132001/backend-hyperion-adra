package backend.hyperion.adra.controller;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import backend.hyperion.adra.entity.Capacitacion;
import backend.hyperion.adra.servicio.CapacitacionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/capacitacion")
@Api(value = "Microservicios de Gestion de Capacitaciones", description = "Microservicio de Capacitación")
public class CapacitacionController {

	@Autowired
	private CapacitacionService capacitacionService;

	@ApiOperation(value = "Lista de todas las Capacitaciones registradas")
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
			result.put("data", capacitacionService.findAll());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && "".equals(sortBy)) {
			result.put("data", capacitacionService.findAll(query, ""));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && !"".equals(sortBy)) {
			result.put("data", capacitacionService.findAll(query, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (limit != -1 && page == -1) {
			return new ResponseEntity<>(new Exception("Parámetro page es requerido"), HttpStatus.CONFLICT);
		} else if (page != -1 && limit == -1) {
			return new ResponseEntity<>(new Exception("Parámetro limit es requerido"), HttpStatus.CONFLICT);
		} else {
			result.put("data", capacitacionService.findAll(query, page, limit, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Registra una Capacitacion nueva")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Capacitacion capacitacion, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Capacitacion data = capacitacionService.save(capacitacion);

		result.put("success", true);
		result.put("message", "La Capacitacion se ha registrado correctamente.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Actualiza datos de una Capacitacion")
	@PutMapping("/{idCapacitacion}")
	public ResponseEntity<?> update(@PathVariable(value = "idCapacitacion") Long idCapacitacion, @RequestBody Capacitacion capacitacion,
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Capacitacion data = capacitacionService.findById(idCapacitacion);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Capacitacion con Id: " + idCapacitacion);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			capacitacionService.save(capacitacion);
			result.put("success", true);
			result.put("message", "Se ha actualizado los datos de la Capacitacion.");
			result.put("data", capacitacion);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Obten los datos de alguna Capacitacion")
	@GetMapping(value = "/{idCapacitacion}")
	public ResponseEntity<?> findById(@PathVariable(value = "idCapacitacion") Long idCapacitacion, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Capacitacion data = capacitacionService.findById(idCapacitacion);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Capacitacion con Id: " + idCapacitacion);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Elimina el registro de una Capacitacion")
	@DeleteMapping(value = "/{idCapacitacion}")
	public ResponseEntity<?> delete(@PathVariable(value = "idCapacitacion") Long idCapacitacion, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Capacitacion data = capacitacionService.findById(idCapacitacion);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe División con id: " + idCapacitacion);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			// data.setEstado(false);
			capacitacionService.delete(data);
			result.put("success", true);
			result.put("message", "Se ha eliminado los datos del registro.");
			result.put("data", data);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
