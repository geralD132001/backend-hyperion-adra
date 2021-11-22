package backend.hyperion.adra.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


@Entity
@Table(name = "recursosVistos")
public class RecursoVisto implements Serializable {

	@Id
	@Column(name = "id_recurvisto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRecursovisto;


	@Column(name = "esta_visto")
	private String estadoVisto;


    
    
    
	private static final long serialVersionUID = 1L;


	public Long getIdRecursovisto() {
		return idRecursovisto;
	}


	public void setIdRecursovisto(Long idRecursovisto) {
		this.idRecursovisto = idRecursovisto;
	}


	public String getEstadoVisto() {
		return estadoVisto;
	}


	public void setEstadoVisto(String estadoVisto) {
		this.estadoVisto = estadoVisto;
	}




	
	
	

}
