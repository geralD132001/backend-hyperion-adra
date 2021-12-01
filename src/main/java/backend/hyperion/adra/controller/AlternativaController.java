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
import backend.hyperion.adra.entity.Alternativa;
import backend.hyperion.adra.servicio.AlternativaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("api/alternativa")
@Api(value = "Gestion de altenativas", description = "Alternativas microservicios")
public class AlternativaController {
	
	@Autowired
	private AlternativaService alternativaService;
	
	@ApiOperation(value = "Lista de de alternativas")
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
			result.put("data", alternativaService.findAll());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && "".equals(sortBy)) {
			result.put("data", alternativaService.findAll(query, ""));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && !"".equals(sortBy)) {
			result.put("data", alternativaService.findAll(query, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (limit != -1 && page == -1) {
			return new ResponseEntity<>(new Exception("Parámetro page es requerido"), HttpStatus.CONFLICT);
		} else if (page != -1 && limit == -1) {
			return new ResponseEntity<>(new Exception("Parámetro limit es requerido"), HttpStatus.CONFLICT);
		} else {
			result.put("data", alternativaService.findAll(query, page, limit, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Registra una nueva alternativa")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Alternativa alternativa, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Alternativa data = alternativaService.save(alternativa);

		result.put("success", true);
		result.put("message", "La alternativa se ha registrado correctamente");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Actualiza el registro de una alternativa")
	@PutMapping("/{idAlternativa}")
	public ResponseEntity<?> update(@PathVariable(value = "idAlternativa") Long idAlternativa, @RequestBody Alternativa alternativa,
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Alternativa data = alternativaService.findById(idAlternativa);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Alternativa con Id: " + idAlternativa);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			alternativaService.save(alternativa);
			result.put("success", true);
			result.put("message", "Se ha actualizaron los datos correctamente.");
			result.put("data", alternativa);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Obten datos de alguna alternativa")
	@GetMapping(value = "/{idAlternativa}")
	public ResponseEntity<?> findById(@PathVariable(value = "idAlternativa") Long idAlternativa, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Alternativa data = alternativaService.findById(idAlternativa);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Alternativa con Id: " + idAlternativa);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Elimina el registro de una Alternativa")
	@DeleteMapping(value = "/{idAlternativa}")
	public ResponseEntity<?> delete(@PathVariable(value = "idAlternativa") Long idAlternativa, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Alternativa data = alternativaService.findById(idAlternativa);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe División con id: " + idAlternativa);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			alternativaService.delete(data);
			result.put("success", true);
			result.put("message", "Se ha eliminado los datos del registro.");
			result.put("data", data);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
