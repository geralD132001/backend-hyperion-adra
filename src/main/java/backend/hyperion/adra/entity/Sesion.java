package backend.hyperion.adra.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sesiones")
public class Sesion implements Serializable {

	@Id
	@Column(name = "id_sesion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSesion;

	@Column(name = "desc_tema")
	private String descripcionTema;

	@Column(name = "desc_secion")
	private String descripcionSecion;

	@Column(name = "fech_inicio")
	private Date fechaInicio;

	@Column(name = "fech_fin")
	private Date fechaFin;

	@ManyToOne
	@JoinColumn(name = "id_capacitacion")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Capacitacion capacitacion;


	public Long getIdSesion() {
		return idSesion;
	}

	public Capacitacion getCapacitacion() {
		return capacitacion;
	}

	public void setCapacitacion(Capacitacion capacitacion) {
		this.capacitacion = capacitacion;
	}

	public void setIdSesion(Long idSesion) {
		this.idSesion = idSesion;
	}

	public String getDescripcionTema() {
		return descripcionTema;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getDescripcionSecion() {
		return descripcionSecion;
	}

	public void setDescripcionSecion(String descripcionSecion) {
		this.descripcionSecion = descripcionSecion;
	}

	public void setDescripcionTema(String descripcionTema) {
		this.descripcionTema = descripcionTema;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	private static final long serialVersionUID = 1L;

}
