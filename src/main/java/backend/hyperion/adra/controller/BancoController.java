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
import backend.hyperion.adra.entity.BancoComunal;
import backend.hyperion.adra.servicio.BancoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("api/banco")
@Api(value = "Microservicios de Gestion de Bancos Comunales", description = "Microservicio de Banco Comunal")
public class BancoController {

	@Autowired
	private BancoService bancoService;

	@ApiOperation(value = "Lista de todos los Bancos Comunales")
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
			result.put("data", bancoService.findAll());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && "".equals(sortBy)) {
			result.put("data", bancoService.findAll(query, ""));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && !"".equals(sortBy)) {
			result.put("data", bancoService.findAll(query, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (limit != -1 && page == -1) {
			return new ResponseEntity<>(new Exception("Parámetro page es requerido"), HttpStatus.CONFLICT);
		} else if (page != -1 && limit == -1) {
			return new ResponseEntity<>(new Exception("Parámetro limit es requerido"), HttpStatus.CONFLICT);
		} else {
			result.put("data", bancoService.findAll(query, page, limit, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Registra un nuevo Banco Comunal")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody BancoComunal banco, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		BancoComunal data = bancoService.save(banco);

		result.put("success", true);
		result.put("message", "El Banco Comunal se ha registrado correctamente.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Actualiza datos de un Banco Comunal")
	@PutMapping("/{idBanco}")
	public ResponseEntity<?> update(@PathVariable(value = "idBanco") Long idBanco, @RequestBody BancoComunal banco,
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		BancoComunal data = bancoService.findById(idBanco);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Banco Comunal con Id: " + idBanco);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			bancoService.save(banco);
			result.put("success", true);
			result.put("message", "Se ha actualizado los datos del Banco Comunal.");
			result.put("data", banco);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Obten datos de un Banco Comunal")
	@GetMapping(value = "/{idBanco}")
	public ResponseEntity<?> findById(@PathVariable(value = "idBanco") Long idBanco, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		BancoComunal data = bancoService.findById(idBanco);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe BancoComunal con Id: " + idBanco);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Elimina el registro de un BancoComunal")
	@DeleteMapping(value = "/{idBanco}")
	public ResponseEntity<?> delete(@PathVariable(value = "idBanco") Long idBanco, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		BancoComunal data = bancoService.findById(idBanco);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe División con id: " + idBanco);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			// data.setEstado(false);
			bancoService.delete(data);
			result.put("success", true);
			result.put("message", "Se ha eliminado los datos del registro.");
			result.put("data", data);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
