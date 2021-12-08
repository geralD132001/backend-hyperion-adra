package backend.hyperion.adra.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "socias")
public class Socia {

	@Id
	@Column(name = "id_socia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSocia;

	@Column(name = "esta_socia")
	private String estadoSocia;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_persona")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Persona persona;

	@ManyToOne
	@JoinColumn(name = "id_banco")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private BancoComunal bancoComunal;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "visto", joinColumns = @JoinColumn(name = "id_socia"), inverseJoinColumns = @JoinColumn(name = "id_recurso"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "id_socia", "id_recurso" }) })
	private List<Recurso> recurso;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "registro", joinColumns = @JoinColumn(name = "id_socia"), inverseJoinColumns = @JoinColumn(name = "id_capacitacion"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "id_socia", "id_capacitacion" }) })
	private List<Capacitacion> capacitacion;

	public BancoComunal getBancoComunal() {
		return bancoComunal;
	}

	public void setBancoComunal(BancoComunal bancoComunal) {
		this.bancoComunal = bancoComunal;
	}

	public List<Recurso> getRecurso() {
		return recurso;
	}

	public void setRecurso(List<Recurso> recurso) {
		this.recurso = recurso;
	}

	public List<Capacitacion> getCapacitacion() {
		return capacitacion;
	}

	public void setCapacitacion(List<Capacitacion> capacitacion) {
		this.capacitacion = capacitacion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Long getIdSocia() {
		return idSocia;
	}

	public void setIdSocia(Long idSocia) {
		this.idSocia = idSocia;
	}

	public String getEstadoSocia() {
		return estadoSocia;
	}

	public void setEstadoSocia(String estadoSocia) {
		this.estadoSocia = estadoSocia;
	}

}
