package backend.hyperion.adra.controller;


import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.hyperion.adra.entity.TipoRecurso;
import backend.hyperion.adra.servicio.TipoRecursoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/tiporecurso")
@Api(value = "Microservicios de Gestion de Capacitaciones", description = "Microservicio de Capacitación")
public class TipoRecursoController {
	
	@Autowired
	TipoRecursoService tiporecursoService;
	
    @ApiOperation(value = "Lista de los tipos de recursos")
    @GetMapping
    public ResponseEntity<?> findAll(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "Consulta correcta.");
        result.put("data", tiporecursoService.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtiene Datos del tipo de recurso")
    @GetMapping(value = "/{idTiporecur}")
    public ResponseEntity<?> findById(@PathVariable(value = "idTiporecur") Long idTiporecur, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        TipoRecurso data = tiporecursoService.findById(idTiporecur);
        if (data == null) {
            result.put("success", false);
            result.put("message", "No existe  tipo de recurso con con Id: " + idTiporecur);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        result.put("success", true);
        result.put("message", "Se ha encontrado el registro.");
        result.put("data", data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
