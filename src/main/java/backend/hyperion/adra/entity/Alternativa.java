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
@Table(name = "alternativas")
public class Alternativa implements Serializable {

	@Id
	@Column(name = "id_alternativa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAlternativa;

	@Column(name = "nomb_alternativa")
	private String nombreAlternativa;

	@Column(name = "es_correcta")
	private String esCorrecta;


	@ManyToOne
	@JoinColumn(name = "id_pregunta")
	private Pregunta pregunta;


	public Long getIdAlternativa() {
		return idAlternativa;
	}

	public void setIdAlternativa(Long idAlternativa) {
		this.idAlternativa = idAlternativa;
	}

	public String getNombreAlternativa() {
		return nombreAlternativa;
	}

	public void setNombreAlternativa(String nombreAlternativa) {
		this.nombreAlternativa = nombreAlternativa;
	}

	public String getEsCorrecta() {
		return esCorrecta;
	}

	public void setEsCorrecta(String esCorrecta) {
		this.esCorrecta = esCorrecta;
	}



	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}



	private static final long serialVersionUID = 1L;

}
