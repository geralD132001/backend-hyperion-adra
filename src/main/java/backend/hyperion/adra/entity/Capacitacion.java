package backend.hyperion.adra.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@Column(name = "tipo_capacitacion")
	private String tipoCapacitacion;


	@Column(name = "esta_capacitacion")
	private String estadoCapacitacion;


	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Sesion> sesion;

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

	public String getTipoCapacitacion() {
		return tipoCapacitacion;
	}

	public void setTipoCapacitacion(String tipoCapacitacion) {
		this.tipoCapacitacion = tipoCapacitacion;
	}


	public String getEstadoCapacitacion() {
		return estadoCapacitacion;
	}

	public void setEstadoCapacitacion(String estadoCapacitacion) {
		this.estadoCapacitacion = estadoCapacitacion;
	}


	public List<Sesion> getSesion() {
		return sesion;
	}

	public void setSesion(List<Sesion> sesion) {
		this.sesion = sesion;
	}
	

}
