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
import backend.hyperion.adra.entity.Persona;
import backend.hyperion.adra.servicio.PersonaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/persona")
@Api(value = "Microservicios de Gestion de Personas", description = "Microservicio de Persona")
public class PersonaController {

	@Autowired	
	private PersonaService personaService;

	@ApiOperation(value = "Lista de Personas")
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
			result.put("data", personaService.findAll());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && "".equals(sortBy)) {
			result.put("data", personaService.findAll(query, ""));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && !"".equals(sortBy)) {
			result.put("data", personaService.findAll(query, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (limit != -1 && page == -1) {
			return new ResponseEntity<>(new Exception("Parámetro page es requerido"), HttpStatus.CONFLICT);
		} else if (page != -1 && limit == -1) {
			return new ResponseEntity<>(new Exception("Parámetro limit es requerido"), HttpStatus.CONFLICT);
		} else {
			result.put("data", personaService.findAll(query, page, limit, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Crea una nueva Persona")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Persona persona, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Persona data = personaService.save(persona);

		result.put("success", true);
		result.put("message", "La persona se ha registrado correctamente.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Actualiza datos de una persona")
	@PutMapping("/{idPersona}")
	public ResponseEntity<?> update(@PathVariable(value = "idPersona") Long idPersona, @RequestBody Persona persona,
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Persona data = personaService.findById(idPersona);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe persona con Id: " + idPersona);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			personaService.save(persona);
			result.put("success", true);
			result.put("message", "Se ha actualizado los datos de la Persona.");
			result.put("data", persona);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Obtiene Datos la persona")
	@GetMapping(value = "/{idPersona}")
	public ResponseEntity<?> findById(@PathVariable(value = "idPersona") Long idPersona, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Persona data = personaService.findById(idPersona);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe persona con Id: " + idPersona);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Elimina una Persona")
	@DeleteMapping(value = "/{idPersona}")
	public ResponseEntity<?> delete(@PathVariable(value = "idPersona") Long idPersona, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Persona data = personaService.findById(idPersona);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe División con id: " + idPersona);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			// data.setEstado(false);
			personaService.delete(data);
			result.put("success", true);
			result.put("message", "Se ha eliminado los datos del registro.");
			result.put("data", data);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
