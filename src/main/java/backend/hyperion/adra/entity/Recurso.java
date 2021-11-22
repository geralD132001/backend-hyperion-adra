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
@Table(name = "recursos")
public class Recurso implements Serializable {

   
	@Id
	@Column(name = "id_recurso")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRecurso;

	@Column(name = "nomb_recursi")
	private String nombreRecurso;
	
	@Column(name = "fi_url")
	private String url;
	
	@Column(name = "fech_inicioR")
    private Date fechaInicioRecurso;
	
	@Column(name = "fech_finR")
    private Date fechaFinRecurso;
	

    

	
    @ManyToOne
    @JoinColumn(name = "id_tiporecurso")
    private TipoRecurso tiporecurso;
    
	@OneToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	private List<Pregunta> pregunta;
	
	private static final long serialVersionUID = 1L;

	public Long getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(Long idRecurso) {
		this.idRecurso = idRecurso;
	}

	public String getNombreRecurso() {
		return nombreRecurso;
	}

	public void setNombreRecurso(String nombreRecurso) {
		this.nombreRecurso = nombreRecurso;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getFechaInicioRecurso() {
		return fechaInicioRecurso;
	}

	public void setFechaInicioRecurso(Date fechaInicioRecurso) {
		this.fechaInicioRecurso = fechaInicioRecurso;
	}

	public Date getFechaFinRecurso() {
		return fechaFinRecurso;
	}

	public void setFechaFinRecurso(Date fechaFinRecurso) {
		this.fechaFinRecurso = fechaFinRecurso;
	}





	public TipoRecurso getTiporecurso() {
		return tiporecurso;
	}

	public void setTiporecurso(TipoRecurso tiporecurso) {
		this.tiporecurso = tiporecurso;
	}

	public List<Pregunta> getPregunta() {
		return pregunta;
	}

	public void setPregunta(List<Pregunta> pregunta) {
		this.pregunta = pregunta;
	}
	
	

}
