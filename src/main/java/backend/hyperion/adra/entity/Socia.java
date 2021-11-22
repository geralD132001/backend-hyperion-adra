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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "socias")
public class Socia {

	@Id
	@Column(name = "id_socia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSocia;

	@Column(name = "esta_socia")
	private String estadoSocia;

	@ManyToOne
	@JoinColumn(name = "id_banco")
	private BancoComunal bancocomunal;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Registro> registro;

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

	public BancoComunal getBancocomunal() {
		return bancocomunal;
	}

	public void setBancocomunal(BancoComunal bancocomunal) {
		this.bancocomunal = bancocomunal;
	}

	public List<Registro> getRegistro() {
		return registro;
	}

	public void setRegistro(List<Registro> registro) {
		this.registro = registro;
	}

}
