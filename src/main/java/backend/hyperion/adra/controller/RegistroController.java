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
import backend.hyperion.adra.entity.Registro;
import backend.hyperion.adra.servicio.RegistroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/registro")
@Api(value = "Microservicios de Gestion de Registros de Resultados de las Socias en la Capacitacion", description = "Microservicio de Registro")
public class RegistroController {

	@Autowired
	private RegistroService registroService;

	@ApiOperation(value = "Lista de Registros")
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
			result.put("data", registroService.findAll());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && "".equals(sortBy)) {
			result.put("data", registroService.findAll(query, ""));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && !"".equals(sortBy)) {
			result.put("data", registroService.findAll(query, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (limit != -1 && page == -1) {
			return new ResponseEntity<>(new Exception("Parámetro page es requerido"), HttpStatus.CONFLICT);
		} else if (page != -1 && limit == -1) {
			return new ResponseEntity<>(new Exception("Parámetro limit es requerido"), HttpStatus.CONFLICT);
		} else {
			result.put("data", registroService.findAll(query, page, limit, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Inserta nuevos datos al Registro")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Registro registro, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Registro data = registroService.save(registro);

		result.put("success", true);
		result.put("message", "Los datos se insertaron de manera correcta");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Actualiza un Registro")
	@PutMapping("/{idRegistro}")
	public ResponseEntity<?> update(@PathVariable(value = "idRegistro") Long idRegistro, @RequestBody Registro registro,
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Registro data = registroService.findById(idRegistro);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Registro con  Id: " + idRegistro);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			registroService.save(data);
			result.put("success", true);
			result.put("message", "Se ha actualizado los datos del Registro.");
			result.put("data", registro);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Obten datos de algún Registro")
	@GetMapping(value = "/{idRegistro}")
	public ResponseEntity<?> findById(@PathVariable(value = "idRegistro") Long idRegistro, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Registro data = registroService.findById(idRegistro);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Registro con Id: " + idRegistro);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Elimina los datos de un Registro")
	@DeleteMapping(value = "/{idRegistro}")
	public ResponseEntity<?> delete(@PathVariable(value = "idRegistro") Long idRegistro, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Registro data = registroService.findById(idRegistro);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe División con id: " + idRegistro);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			// data.setEstado(false);
			registroService.delete(data);
			result.put("success", true);
			result.put("message", "Se ha eliminado los datos del registro.");
			result.put("data", data);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
