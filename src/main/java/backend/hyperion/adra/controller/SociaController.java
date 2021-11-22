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

import backend.hyperion.adra.entity.Socia;
import backend.hyperion.adra.servicio.SociaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/socia")
@Api(value = "Microservicios de Gestion de Socias", description = "Microservicio de Socia")
public class SociaController {

	@Autowired
	private SociaService sociaService;


	@ApiOperation(value = "Listar todas las Socias")
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
			result.put("data", sociaService.findAll());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && "".equals(sortBy)) {
			result.put("data", sociaService.findAll(query, ""));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && !"".equals(sortBy)) {
			result.put("data", sociaService.findAll(query, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (limit != -1 && page == -1) {
			return new ResponseEntity<>(new Exception("Parámetro page es requerido"), HttpStatus.CONFLICT);
		} else if (page != -1 && limit == -1) {
			return new ResponseEntity<>(new Exception("Parámetro limit es requerido"), HttpStatus.CONFLICT);
		} else {
			result.put("data", sociaService.findAll(query, page, limit, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Registra el Estado de la Socia")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Socia socia, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Socia data = sociaService.save(socia);

		result.put("success", true);
		result.put("message", "El estado de la Socia se a registrado Correctamente");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Actualiza el estado en el que se encuetra la Socia")
	@PutMapping("/{idSocia}")
	public ResponseEntity<?> update(@PathVariable(value = "idSocia") Long idSocia, @RequestBody Socia socia,
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Socia data = sociaService.findById(idSocia);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Socia con Id: " + idSocia);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			sociaService.save(data);
			result.put("success", true);
			result.put("message", "Se ha actualizado los datos de la Socia");
			result.put("data", socia);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Obtiene Datos de la Socia")
	@GetMapping(value = "/{idSocia}")
	public ResponseEntity<?> findById(@PathVariable(value = "idSocia") Long idSocia, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Socia data = sociaService.findById(idSocia);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Socia con Id: " + idSocia);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Elimina datos de una Socia")
	@DeleteMapping(value = "/{idSocia}")
	public ResponseEntity<?> delete(@PathVariable(value = "idSocia") Long idSocia, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Socia data = sociaService.findById(idSocia);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe División con id: " + idSocia);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			// data.setEstado(false);
			sociaService.delete(data);
			result.put("success", true);
			result.put("message", "Se ha eliminado los datos de la Socia.");
			result.put("data", data);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
