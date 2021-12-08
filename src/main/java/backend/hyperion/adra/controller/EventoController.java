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
import backend.hyperion.adra.entity.Evento;
import backend.hyperion.adra.servicio.EventoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/evento")
@Api(value = "Microservicios de Gestion de Eventos", description = "Microservicio de Evento")
public class EventoController {
	
	@Autowired
	private EventoService eventoService;

	@ApiOperation(value = "Lista de todos los eventos registrados")
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
			result.put("data", eventoService.findAll());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && "".equals(sortBy)) {
			result.put("data", eventoService.findAll(query, ""));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && !"".equals(sortBy)) {
			result.put("data", eventoService.findAll(query, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (limit != -1 && page == -1) {
			return new ResponseEntity<>(new Exception("Parámetro page es requerido"), HttpStatus.CONFLICT);
		} else if (page != -1 && limit == -1) {
			return new ResponseEntity<>(new Exception("Parámetro limit es requerido"), HttpStatus.CONFLICT);
		} else {
			result.put("data", eventoService.findAll(query, page, limit, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Registra un evento nuevo")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Evento evento, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Evento data = eventoService.save(evento);

		result.put("success", true);
		result.put("message", "El evento se ha registrado correctamente.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Actualiza datos de un evento")
	@PutMapping("/{idEvento}")
	public ResponseEntity<?> update(@PathVariable(value = "idEvento") Long idEvento, @RequestBody Evento evento,
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Evento data = eventoService.findById(idEvento);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Evento con Id: " + idEvento);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			eventoService.save(evento);
			result.put("success", true);
			result.put("message", "Se ha actualizado los datos del evento.");
			result.put("data", evento);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Obten los datos de algún evento")
	@GetMapping(value = "/{idEvento}")
	public ResponseEntity<?> findById(@PathVariable(value = "idEvento") Long idEvento, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Evento data = eventoService.findById(idEvento);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Evento con Id: " + idEvento);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Elimina el registro de un Evento")
	@DeleteMapping(value = "/{idEvento}")
	public ResponseEntity<?> delete(@PathVariable(value = "idEvento") Long idEvento, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Evento data = eventoService.findById(idEvento);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe División con id: " + idEvento);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			eventoService.delete(data);
			result.put("success", true);
			result.put("message", "Se ha eliminado los datos del registro.");
			result.put("data", data);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
