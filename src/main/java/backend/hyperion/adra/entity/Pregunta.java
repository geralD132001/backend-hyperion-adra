package backend.hyperion.adra.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "preguntas")
public class Pregunta implements Serializable {

	@Id
	@Column(name = "id_pregunta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPregunta;

	@Column(name = "nomb_pregunta")
	private String nombrePregunta;

	@ManyToOne
	@JoinColumn(name = "id_recurso")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Recurso recurso;

	private static final long serialVersionUID = 1L;

	public Long getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getNombrePregunta() {
		return nombrePregunta;
	}

	public void setNombrePregunta(String nombrePregunta) {
		this.nombrePregunta = nombrePregunta;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

}
