package backend.hyperion.adra.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "capacitaciones")
public class Capacitacion implements Serializable {

	@Id
	@Column(name = "id_capacitacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCapacitacion;

	@Column(name = "nomb_capacitacion")
	private String nombreCapacitacion;

	@Column(name = "desc_capacitacion")
	private String descripcionCapacitacion;

	@Column(name = "cant_recurso")
	private Integer cantRecurso;

	@Column(name = "esta_capacitacion")
	private String estadoCapacitacion;



	private static final long serialVersionUID = 1L;

	public Long getIdCapacitacion() {
		return idCapacitacion;
	}

	public void setIdCapacitacion(Long idCapacitacion) {
		this.idCapacitacion = idCapacitacion;
	}

	public String getNombreCapacitacion() {
		return nombreCapacitacion;
	}

	public void setNombreCapacitacion(String nombreCapacitacion) {
		this.nombreCapacitacion = nombreCapacitacion;
	}

	public String getDescripcionCapacitacion() {
		return descripcionCapacitacion;
	}

	public void setDescripcionCapacitacion(String descripcionCapacitacion) {
		this.descripcionCapacitacion = descripcionCapacitacion;
	}



	public String getEstadoCapacitacion() {
		return estadoCapacitacion;
	}

	public void setEstadoCapacitacion(String estadoCapacitacion) {
		this.estadoCapacitacion = estadoCapacitacion;
	}

	public Integer getCantRecurso() {
		return cantRecurso;
	}

	public void setCantRecurso(Integer cantRecurso) {
		this.cantRecurso = cantRecurso;
	}





}
