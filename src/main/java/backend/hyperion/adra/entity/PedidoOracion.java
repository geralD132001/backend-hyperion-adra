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
@Table(name = "pedidosOracion")
public class PedidoOracion implements Serializable {

	@Id
	@Column(name = "id_pedido")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;

	@Column(name = "desc_oracion")
	private String descripcionOracion;

	@Column(name = "esta_oracion")
	private String estadoOracion;

	@ManyToOne
	@JoinColumn(name = "id_persona")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Persona persona;

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public String getDescripcionOracion() {
		return descripcionOracion;
	}

	public void setDescripcionOracion(String descripcionOracion) {
		this.descripcionOracion = descripcionOracion;
	}

	public String getEstadoOracion() {
		return estadoOracion;
	}

	public void setEstadoOracion(String estadoOracion) {
		this.estadoOracion = estadoOracion;
	}

	private static final long serialVersionUID = 1L;

}
