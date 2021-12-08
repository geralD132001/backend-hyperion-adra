package backend.hyperion.adra.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private String cod_identidad;

	@Column(name = "ti_nacionalidad")
	private String tiNacionalidad;

    private boolean estado;

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}


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


	public String getTiNacionalidad() {
		return tiNacionalidad;
	}

	public void setTiNacionalidad(String tiNacionalidad) {
		this.tiNacionalidad = tiNacionalidad;
	}

	public String getCod_identidad() {
		return cod_identidad;
	}

	public void setCod_identidad(String cod_identidad) {
		this.cod_identidad = cod_identidad;
	}


}
