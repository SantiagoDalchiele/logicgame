package uy.com.uma.logicgame.api.bean;

/**
 * Clase "liviana" que encapsula datos del usuario
 *
 * @author Santiago Dalchiele
 */
public class DatosUsuario {

	private String idUsuario;
	private boolean logeado;
	private int idJuego;
	private String idioma;
	private String alias;
	private int nivel;
	private String estado;
	
	
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public boolean isLogeado() {
		return logeado;
	}
	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}
	public int getIdJuego() {
		return idJuego;
	}
	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}	
}
