package backend.hyperion.adra.entity;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "registrosSocias")
public class Registro implements Serializable{


	@Id
	@Column(name = "id_registro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRegistro;

	@Column(name = "fech_registro")
    private Date fechaRegistro;
	
	@Column(name = "desc_valoracion")
    private String descripcionValoracion;
	
	@Column(name = "cant_visto")
    private Integer cantidadVisto;
	

    
    @ManyToOne
    @JoinColumn(name = "id_capacitacion")
    private Capacitacion capacitacion;
    

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<RecursoVisto> recursovisto;
	
	
	public Long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getDescripcionValoracion() {
		return descripcionValoracion;
	}

	public void setDescripcionValoracion(String descripcionValoracion) {
		this.descripcionValoracion = descripcionValoracion;
	}

	public Integer getCantidadVisto() {
		return cantidadVisto;
	}

	public void setCantidadVisto(Integer cantidadVisto) {
		this.cantidadVisto = cantidadVisto;
	}



	public Capacitacion getCapacitacion() {
		return capacitacion;
	}

	public List<RecursoVisto> getRecursovisto() {
		return recursovisto;
	}

	public void setRecursovisto(List<RecursoVisto> recursovisto) {
		this.recursovisto = recursovisto;
	}

	public void setCapacitacion(Capacitacion capacitacion) {
		this.capacitacion = capacitacion;
	}


	
	private static final long serialVersionUID = 1L;

}
