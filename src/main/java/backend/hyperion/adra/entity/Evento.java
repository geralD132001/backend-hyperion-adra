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
@Table(name = "tmv_evento")
public class Evento implements Serializable {

    @Id
	@Column(name = "id_evento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvento;
    
    @Column(name = "no_evento")
	private String nombreEvento;
    
    @Column(name = "de_evento")
	private String descripEvento;
    
    @Column(name = "fi_url")
	private String fileUrl;
    
    @Column(name = "fe_inicioevento")
	private Date inicioEvento;
    
    @Column(name = "fe_finevento")
	private Date finEvento;
    
     @Column(name = "es_evento")
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

    public String getDescripEvento() {
        return descripEvento;
    }

    public void setDescripEvento(String descripEvento) {
        this.descripEvento = descripEvento;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getInicioEvento() {
        return inicioEvento;
    }

    public void setInicioEvento(Date inicioEvento) {
        this.inicioEvento = inicioEvento;
    }

    public Date getFinEvento() {
        return finEvento;
    }

    public void setFinEvento(Date finEvento) {
        this.finEvento = finEvento;
    }

    public String getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(String estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

     
     
}