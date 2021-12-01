package backend.hyperion.adra.entity;

import java.io.Serializable;
import java.util.Date;
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


	@OneToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	private List<Recurso> recurso;

	
	
	public Long getIdSesion() {
		return idSesion;
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







	public List<Recurso> getRecurso() {
		return recurso;
	}



	public void setRecurso(List<Recurso> recurso) {
		this.recurso = recurso;
	}



	private static final long serialVersionUID = 1L;
	

}
