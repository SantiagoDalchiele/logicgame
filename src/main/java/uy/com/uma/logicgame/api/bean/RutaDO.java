package uy.com.uma.logicgame.api.bean;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.json.JsonObject;

import uy.com.uma.comun.util.UtilJSON;
import uy.com.uma.comun.util.UtilString;

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
	public JsonObject toJSON() {
		Map<String, Object> props = new LinkedHashMap<String, Object>();
		props.put(TAG_ID_RUTA, getId());
		props.put(TAG_NIVEL, getNivel());
		props.put(TAG_ID_JUEGO, getIdJuego());
		props.put(TAG_HOJA_ESTILO, UtilString.quitarNulo(getHojaEstilo()));
		return UtilJSON.getJSONObject(props);
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
