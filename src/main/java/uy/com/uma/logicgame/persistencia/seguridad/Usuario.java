package uy.com.uma.logicgame.persistencia.seguridad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import uy.com.uma.logicgame.api.bean.UsuarioDO;
import uy.com.uma.logicgame.persistencia.inter.Idioma;

/**
 * Tabla usuarios
 *
 * @author Santiago Dalchiele
 */
@Entity
@Table(name = "usuarios")
public
class Usuario {

	@Id
	@Column
	private String id;
	
	@Column
	private String alias;
	
	@Column
	private String correo;
	
	@Column(columnDefinition = "bpchar(32)")
	private String clave;
	
	@Column
	private Boolean logeado;
	
	@OneToOne
	@JoinColumns({@JoinColumn(name="ruta", referencedColumnName="id", insertable=true, updatable=true),
				@JoinColumn(name="nivel", referencedColumnName="nivel", insertable=true, updatable=true) })
	private Ruta rutaNivel;
	
	@Column
	private String estado;
	
	@ManyToOne
    @JoinColumn(name="idioma", referencedColumnName="id")
	private Idioma idioma;
	
	@Column(columnDefinition = "bpchar(128)")
	private String token;
	
	@Column(name="fch_expira_token")
	private Date fchExpiraToken;
	
	
	
	/**
	 * Retorna una clase "liviana" con los datos del usuario
	 */
	public static UsuarioDO getDatosUsuario(Usuario usuario) {
		UsuarioDO du = new UsuarioDO();
		du.setAlias (usuario.getAlias());
		du.setIdioma (usuario.getIdioma().getId());
		du.setIdJuego (usuario.getRutaNivel().getJuego().getId());
		du.setIdUsuario (usuario.getId());
		du.setLogeado (usuario.getLogeado());
		du.setNivel (usuario.getRutaNivel().getId().getNivel());
		du.setEstado ((usuario.getEstado() == null) ? "" : usuario.getEstado());
		du.setCorreo (usuario.getCorreo());
		du.setRuta (usuario.getRutaNivel().getId().getId());
		return du;
	}
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Boolean getLogeado() {
		return logeado;
	}
	public void setLogeado(Boolean logeado) {
		this.logeado = logeado;
	}
	public Ruta getRutaNivel() {
		return rutaNivel;
	}
	public void setRutaNivel(Ruta ruta) {
		this.rutaNivel = ruta;
	}	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}	
	public Idioma getIdioma() {
		return idioma;
	}
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getFchExpiraToken() {
		return fchExpiraToken;
	}
	public void setFchExpiraToken(Date fchExpiraToken) {
		this.fchExpiraToken = fchExpiraToken;
	}
	
}
