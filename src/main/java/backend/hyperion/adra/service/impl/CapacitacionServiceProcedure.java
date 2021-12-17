package backend.hyperion.adra.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backend.hyperion.adra.entity.Capacitacion;
import backend.hyperion.adra.repository.CapacitacionRepositoryProcedure;

@Service
public class CapacitacionServiceProcedure {

	@Autowired
	private CapacitacionRepositoryProcedure capacitacionProcedure;
	
	  public List<Capacitacion> lista(){
	        return capacitacionProcedure.listaProcedure();
	    }

	    public Optional<Capacitacion> getById(Long idCapacitacion){
	        return capacitacionProcedure.idProcedure(idCapacitacion);
	    }

	    public void saveProcedure(Capacitacion capacitacion){
	    	capacitacionProcedure.saveProcedure(capacitacion.getNombreCapacitacion(), capacitacion.getDescripcionCapacitacion(), capacitacion.getCantRecurso(), capacitacion.getEstadoCapacitacion());
	    }


	    public void borrarProcedure(Long  idCapacitacion){
	    	capacitacionProcedure.borrarProcedure(idCapacitacion);
	    }


}

