package backend.hyperion.adra.controller;

import java.util.ArrayList;
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
import backend.hyperion.adra.entity.Sesion;
import backend.hyperion.adra.entity.Socia;
import backend.hyperion.adra.servicio.RecursoService;
import backend.hyperion.adra.servicio.SesionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("api/sesion")
@Api(value = "Microservicios de Gestion de Sesiones", description = "Microservicio de Sesion")
public class SesionController {

	@Autowired
	private SesionService sesionService;
	
	@Autowired
	private RecursoService recursoService;	
	

	@ApiOperation(value = "Lista de todas las Sesiones")
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
			result.put("data", sesionService.findAll());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && "".equals(sortBy)) {
			result.put("data", sesionService.findAll(query, ""));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (!query.equals("") && page == -1 && limit == -1 && !"".equals(sortBy)) {
			result.put("data", sesionService.findAll(query, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (limit != -1 && page == -1) {
			return new ResponseEntity<>(new Exception("Parámetro page es requerido"), HttpStatus.CONFLICT);
		} else if (page != -1 && limit == -1) {
			return new ResponseEntity<>(new Exception("Parámetro limit es requerido"), HttpStatus.CONFLICT);
		} else {
			result.put("data", sesionService.findAll(query, page, limit, sortBy));
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Obtiene datos del Sesion por ID de la Capacitacion")
	@GetMapping(value = "/capacitacionn/{idCapacitacion}")
	public ResponseEntity<?> findByIdSesion(@PathVariable(value = "idCapacitacion") Long idCapacitacion, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		List<Sesion> data = sesionService.findByCapacitacion(idCapacitacion);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Recurso con Id: " + idCapacitacion);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Lista de todas las Sesiones")
	@GetMapping(value = "/capacitacion/{idCapacitacion}")
	public ResponseEntity<?> findSesionByCapacitacion(@PathVariable(value = "idCapacitacion") Long idCapacitacion, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("message", "Consulta correcta");
		
		List<Object> sesionesNew = new ArrayList<>();
		
		List<Sesion> sesiones = sesionService.findByCapacitacion(idCapacitacion);
		
		
		for (int i = 0; i<sesiones.size(); i++){
			Sesion s = new Sesion(new ArrayList<Recurso>());
			s.setIdSesion(sesiones.get(i).getIdSesion());
			s.setDescripcionTema(sesiones.get(i).getDescripcionTema());
			s.setDescripcionSecion(sesiones.get(i).getDescripcionSecion());
			s.setFechaInicio(sesiones.get(i).getFechaInicio());
			s.setFechaFin(sesiones.get(i).getFechaFin());
			s.setRecursos(recursoService.findBySesion(sesiones.get(i).getIdSesion()));
			sesionesNew.add(s);
		}

		result.put("data", sesionesNew);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
	@ApiOperation(value = "Registra una nueva Sesion")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Sesion sesion, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Sesion data = sesionService.save(sesion);

		result.put("success", true);
		result.put("message", "La Sesion se ha registrado correctamente.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Actualiza datos de una Sesion")
	@PutMapping("/{idSesion}")
	public ResponseEntity<?> update(@PathVariable(value = "idSesion") Long idSesion, @RequestBody Sesion sesion,
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Sesion data = sesionService.findById(idSesion);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Sesion con Id: " + idSesion);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			sesionService.save(sesion);
			result.put("success", true);
			result.put("message", "Se ha actualizado los datos de la Sesion!");
			result.put("data", sesion);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Obten los datos de alguna Sesion")
	@GetMapping(value = "/{idSesion}")
	public ResponseEntity<?> findById(@PathVariable(value = "idSesion") Long idSesion, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Sesion data = sesionService.findById(idSesion);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe Sesion con Id: " + idSesion);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		result.put("success", true);
		result.put("message", "Se ha encontrado el registro.");
		result.put("data", data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "Elimina datos de una Sesion")
	@DeleteMapping(value = "/{idSesion}")
	public ResponseEntity<?> delete(@PathVariable(value = "idSesion") Long idSesion, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<>();
		Sesion data = sesionService.findById(idSesion);
		if (data == null) {
			result.put("success", false);
			result.put("message", "No existe División con id: " + idSesion);
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		try {
			// data.setEstado(false);
			sesionService.delete(data);
			result.put("success", true);
			result.put("message", "Se ha eliminado los datos del registro.");
			result.put("data", data);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
