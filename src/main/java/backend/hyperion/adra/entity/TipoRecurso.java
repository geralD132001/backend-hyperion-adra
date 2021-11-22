package backend.hyperion.adra.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "tiposRecursos")
public class TipoRecurso implements Serializable {

	@Id
	@Column(name = "id_tiporecurso")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTiporecur;

	@Column(name = "nomb_tiporecur")
	private String nombreTiporecurso;



	public Long getIdTiporecur() {
		return idTiporecur;
	}

	public void setIdTiporecur(Long idTiporecur) {
		this.idTiporecur = idTiporecur;
	}

	public String getNombreTiporecurso() {
		return nombreTiporecurso;
	}

	public void setNombreTiporecurso(String nombreTiporecurso) {
		this.nombreTiporecurso = nombreTiporecurso;
	}



	private static final long serialVersionUID = 1L;

}
