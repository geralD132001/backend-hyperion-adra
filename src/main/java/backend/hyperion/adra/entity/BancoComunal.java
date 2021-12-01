package backend.hyperion.adra.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bancosComunales")
public class BancoComunal implements Serializable {

	@Id
	@Column(name = "id_banco")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBanco;

	@Column(name = "nomb_banco")
	private String nombreBanco;

	@Column(name = "nomb_encargado")
	private String nombreEncargado;

	@Column(name = "esta_banco")
	private String estadoBanco;


	private static final long serialVersionUID = 1L;

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getNombreEncargado() {
		return nombreEncargado;
	}

	public void setNombreEncargado(String nombreEncargado) {
		this.nombreEncargado = nombreEncargado;
	}

	public String getEstadoBanco() {
		return estadoBanco;
	}

	public void setEstadoBanco(String estadoBanco) {
		this.estadoBanco = estadoBanco;
	}


	

}
