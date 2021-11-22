package backend.hyperion.adra.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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



	private static final long serialVersionUID = 1L;

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


	
}
