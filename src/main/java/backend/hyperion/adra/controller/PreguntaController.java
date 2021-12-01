package backend.hyperion.adra.controller;

import java.util.HashMap;
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
import backend.hyperion.adra.entity.Pregunta;
import backend.hyperion.adra.servicio.PreguntaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("api/pregunta")
@Api(value = "Microservicios de Gestion de Preguntas", description = "Preguntas microservicio")
public class PreguntaController {

	@Autowired
	private PreguntaService preguntaService;

	@ApiOperation(value = "Lista de todas las preguntas")
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
			result.put("data", preguntaService.findAll());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && "".equals(sortBy)) {
			result.put("data", preguntaService.findAll(query, ""));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && !"".equals(sortBy)) {
			result.put("data", preguntaService.findAll(query, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (limit != -1 && page == -1) {
			return new ResponseEntity<>(new Exception("Parámetro page es requerido"), HttpStatus.CONFLICT);
		} else if (page != -1 && limit == -1) {
			return new ResponseEntity<>(new Exception("Parámetro limit es requerido"), HttpStatus.CONFLICT);
		} else {
			result.put("data", preguntaService.findAll(query, page, limit, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Registra una nueva pregunta")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Pregunta pregunta, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Pregunta data = preguntaService.save(pregunta);

		result.put("success", true);
		result.put("message", "La pregunta se ha registrado de manera correcta");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Actualiza una pregunta")
	@PutMapping("/{idPregunta}")
	public ResponseEntity<?> update(@PathVariable(value = "idPregunta") Long idPregunta, @RequestBody Pregunta pregunta,
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Pregunta data = preguntaService.findById(idPregunta);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Pregunta con Id: " + idPregunta);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			preguntaService.save(pregunta);
			result.put("success", true);
			result.put("message", "Se ha actualizado exitosamente la pregunta");
			result.put("data", pregunta);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Listar una pregunta espeficica")
	@GetMapping(value = "/{idPregunta}")
	public ResponseEntity<?> findById(@PathVariable(value = "idPregunta") Long idPregunta, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Pregunta data = preguntaService.findById(idPregunta);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Pregunta con Id: " + idPregunta);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Eliminar el registro de una pregunta")
	@DeleteMapping(value = "/{idPregunta}")
	public ResponseEntity<?> delete(@PathVariable(value = "idPregunta") Long idPregunta, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Pregunta data = preguntaService.findById(idPregunta);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe División con id: " + idPregunta);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			preguntaService.delete(data);
			result.put("success", true);
			result.put("message", "Se ha eliminado los datos del registro.");
			result.put("data", data);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
