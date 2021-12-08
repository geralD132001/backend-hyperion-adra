package backend.hyperion.adra.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eventos")
public class Evento implements Serializable {

	@Id
	@Column(name = "id_evento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvento;

	@Column(name = "nomb_evento")
	private String nombreEvento;
	

	@Column(name = "desc_evento")
	private String descEvento;

	@Column(name = "fi_url")
	private String fiUrl;

	@Column(name = "fech_incioE")
	private Date fechaInicioevento;

	@Column(name = "esta_evento")
	private String estadoEvento;

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public String getFiUrl() {
		return fiUrl;
	}

	public void setFiUrl(String fiUrl) {
		this.fiUrl = fiUrl;
	}

	public Date getFechaInicioevento() {
		return fechaInicioevento;
	}

	public void setFechaInicioevento(Date fechaInicioevento) {
		this.fechaInicioevento = fechaInicioevento;
	}

	public String getEstadoEvento() {
		return estadoEvento;
	}

	public void setEstadoEvento(String estadoEvento) {
		this.estadoEvento = estadoEvento;
	}

	public String getDescEvento() {
		return descEvento;
	}

	public void setDescEvento(String descEvento) {
		this.descEvento = descEvento;
	}

	private static final long serialVersionUID = 1L;

}
