package backend.hyperion.adra.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "personas")
public class Persona implements Serializable {

	@Id
	@Column(name = "id_persona")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersona;

	@Column(name = "nomb_persona")
	private String nombrePersona;

	@Column(name = "apell_paterno")
	private String apellidoPaterno;

	@Column(name = "apell_materno")
	private String apellidoMaterno;

	@Column(name = "dire_persona")
	private String diDireccion;

	@Column(name = "nume_celular")
	private String numeroCelular;

	@Column(name = "codi_identidad")
	private Integer cod_identidad;

	@Column(name = "ti_nacionalidad")
	private String tiNacionalidad;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_socia")
	private Socia socia;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PedidoOracion> pedidosOracion;
	
    private boolean estado;

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<PedidoOracion> getPedidosOracion() {
		return pedidosOracion;
	}

	public void setPedidosOracion(List<PedidoOracion> pedidosOracion) {
		this.pedidosOracion = pedidosOracion;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usua_id")
	private Usuario usuario;

	private static final long serialVersionUID = 1L;

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getDiDireccion() {
		return diDireccion;
	}

	public void setDiDireccion(String diDireccion) {
		this.diDireccion = diDireccion;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public Integer getCod_identidad() {
		return cod_identidad;
	}

	public void setCod_identidad(Integer cod_identidad) {
		this.cod_identidad = cod_identidad;
	}

	public String getTiNacionalidad() {
		return tiNacionalidad;
	}

	public void setTiNacionalidad(String tiNacionalidad) {
		this.tiNacionalidad = tiNacionalidad;
	}

	public Socia getSocia() {
		return socia;
	}

	public void setSocia(Socia socia) {
		this.socia = socia;
	}



	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
