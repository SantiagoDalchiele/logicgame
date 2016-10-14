package uy.com.uma.logicgame.api.bean;

import uy.com.uma.comun.util.UtilJSON;

/**
 * Clase "liviana" que encapsula datos del usuario
 *
 * @author Santiago Dalchiele
 */
public class UsuarioDO implements IJSONObject {
	
	/** TAGs para retornar el objeto JSON */
	private static final String TAG_ID_USUARIO = "id";
	private static final String TAG_ALIAS = "alias";
	private static final String TAG_CORREO = "correo";
	private static final String TAG_LOGEADO = "logeado";
	private static final String TAG_RUTA = "ruta";
	private static final String TAG_NIVEL = "nivel";
	private static final String TAG_IDIOMA = "idioma";
	private static final String TAG_ESTADO = "estado";	
	

	/** Atributos */
	private String idUsuario;
	private boolean logeado;
	private int idJuego;
	private String idioma;
	private String alias;
	private int nivel;
	private String estado;
	private String correo;
	private short ruta;
	
	
	
	/**
	 * Retorna el objeto en formato JSON: {"id":<idUsuario>,"alias":<alias>,"correo":<correo>,"logeado":<logeado>,
	 * 									"ruta":<ruta>,"nivel":<nivel>,"idioma":<idioma>,"estado":<estado>}
	 */
	public String toJSON() {
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		buf.append(UtilJSON.getPropJSON(TAG_ID_USUARIO) + UtilJSON.getValorJSON(getIdUsuario()));
		buf.append(UtilJSON.getPropJSON(TAG_ALIAS) + UtilJSON.getValorJSON(getAlias()));
		buf.append(UtilJSON.getPropJSON(TAG_CORREO) + UtilJSON.getValorJSON(getCorreo()));
		buf.append(UtilJSON.getPropJSON(TAG_LOGEADO) + UtilJSON.getValorJSON(Boolean.toString(isLogeado())));
		buf.append(UtilJSON.getPropJSON(TAG_RUTA) + UtilJSON.getValorJSON("" + getRuta()));
		buf.append(UtilJSON.getPropJSON(TAG_NIVEL) + UtilJSON.getValorJSON("" + getNivel()));
		buf.append(UtilJSON.getPropJSON(TAG_IDIOMA) + UtilJSON.getValorJSON("" + getIdioma()));
		buf.append(UtilJSON.getPropJSON(TAG_ESTADO) + "[" + getEstado() + "]");
		buf.append("}");
		return buf.toString();
	}
	
	
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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public short getRuta() {
		return ruta;
	}
	public void setRuta(short ruta) {
		this.ruta = ruta;
	}	
}
