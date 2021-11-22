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
import backend.hyperion.adra.entity.PedidoOracion;
import backend.hyperion.adra.servicio.OracionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/oracion")
@Api(value = "Microservicios de Gestion de Pedidos de Oracion", description = "Microservicio de Pedido de Oracion")
public class OracionController {

	@Autowired
	private OracionService oracionService;

	@ApiOperation(value = "Lista de todos los pedidos de Oracion")
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
			result.put("data", oracionService.findAll());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && "".equals(sortBy)) {
			result.put("data", oracionService.findAll(query, ""));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && !"".equals(sortBy)) {
			result.put("data", oracionService.findAll(query, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (limit != -1 && page == -1) {
			return new ResponseEntity<>(new Exception("Parámetro page es requerido"), HttpStatus.CONFLICT);
		} else if (page != -1 && limit == -1) {
			return new ResponseEntity<>(new Exception("Parámetro limit es requerido"), HttpStatus.CONFLICT);
		} else {
			result.put("data", oracionService.findAll(query, page, limit, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Registra un nuevo Pedido de Oracion")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PedidoOracion oracion, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		PedidoOracion data = oracionService.save(oracion);

		result.put("success", true);
		result.put("message", "El Pedido de Oracion se registro correctamente");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Actualiza algún pedido de Oracion")
	@PutMapping("/{idPedido}")
	public ResponseEntity<?> update(@PathVariable(value = "idPedido") Long idPedido, @RequestBody PedidoOracion oracion,
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		PedidoOracion data = oracionService.findById(idPedido);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Pedido de Oracion con Id: " + idPedido);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			oracionService.save(oracion);
			result.put("success", true);
			result.put("message", "Se ha actualizado el Pedido de Oracion");
			result.put("data", oracion);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Obten algún Pedido de Oración")
	@GetMapping(value = "/{idPedido}")
	public ResponseEntity<?> findById(@PathVariable(value = "idPedido") Long idPedido, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		PedidoOracion data = oracionService.findById(idPedido);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Pedido de Oración con Id: " + idPedido);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Elimina el registro de un Pedido de Oracion")
	@DeleteMapping(value = "/{idPedido}")
	public ResponseEntity<?> delete(@PathVariable(value = "idPedido") Long idPedido, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		PedidoOracion data = oracionService.findById(idPedido);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe División con id: " + idPedido);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			// data.setEstado(false);
			oracionService.delete(data);
			result.put("success", true);
			result.put("message", "Se ha eliminado los datos del registro.");
			result.put("data", data);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
