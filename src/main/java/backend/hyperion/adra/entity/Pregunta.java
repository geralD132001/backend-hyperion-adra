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
@Table(name = "preguntas")
public class Pregunta implements Serializable {

	@Id
	@Column(name = "id_pregunta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPregunta;

	@Column(name = "nomb_pregunta")
	private String nombrePregunta;



	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Alternativa> alternativa;

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



	public List<Alternativa> getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(List<Alternativa> alternativa) {
		this.alternativa = alternativa;
	}

}
