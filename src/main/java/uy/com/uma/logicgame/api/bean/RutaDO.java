package uy.com.uma.logicgame.api.bean;

import uy.com.uma.comun.util.UtilJSON;

/**
 * Clase "liviana" que encapsula los datos de una ruta
 * 
 * @author Santiago Dalchiele
 */
public class RutaDO implements IJSONObject {
	
	/** TAGs para retornar el objeto JSON */
	private static final String TAG_ID_RUTA = "ruta";
	private static final String TAG_NIVEL = "nivel";
	private static final String TAG_ID_JUEGO = "juego";
	private static final String TAG_HOJA_ESTILO = "estilo";

	
	/** Atributos */
	private short id;
	private int nivel;
	private int idJuego;
	private String hojaEstilo;
	
	
	
	/**
	 * Retorna el objeto en formato JSON: {"ruta":<id>,"nivel":<nivel>,"juego":<idJuego>,"estilo":<hojaEstilo>}
	 */
	public String toJSON() {
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		buf.append(UtilJSON.getPropJSON(TAG_ID_RUTA) + UtilJSON.getValorJSON("" + getId()));
		buf.append(UtilJSON.getPropJSON(TAG_NIVEL) + UtilJSON.getValorJSON("" + getNivel()));
		buf.append(UtilJSON.getPropJSON(TAG_ID_JUEGO) + UtilJSON.getValorJSON("" + getIdJuego())); 
		buf.append(UtilJSON.getPropJSON(TAG_HOJA_ESTILO) + UtilJSON.getComillasJSON(getHojaEstilo()));
		buf.append("}");
		return buf.toString();
	}
	
	
	
	public short getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getIdJuego() {
		return idJuego;
	}
	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}
	public String getHojaEstilo() {
		return hojaEstilo;
	}
	public void setHojaEstilo(String hojaEstilo) {
		this.hojaEstilo = hojaEstilo;
	}	
}
