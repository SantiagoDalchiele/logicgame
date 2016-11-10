package uy.com.uma.logicgame.api.bean;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.json.JsonObject;

import uy.com.uma.comun.util.UtilJSON;

/**
 * Clase liviana para trasegar datos con fe de los idiomas
 *
 * @author Santiago Dalchiele
 */
public class IdiomaDO implements IJSONObject {

	/** TAGs para retornar el objeto JSON */
	private static final String TAG_ID_IDIOMA = "id";
	private static final String TAG_NOMBRE_IDIOMA = "nombre";
	private static final String TAG_ICONO_IDIOMA = "icono";
	
	
	/** Atributos */
	private String id;
	private String nombre;
	private String icono;
	
	
	
	/**
	 * Retorna el objeto en formato JSON: {"id":<id>,"nombre":<nombre>,"icono":<icono>}
	 */
	public JsonObject toJSON() {
		Map<String, Object> props = new LinkedHashMap<String, Object>();
		props.put(TAG_ID_IDIOMA, getId());
		props.put(TAG_NOMBRE_IDIOMA, getNombre());
		props.put(TAG_ICONO_IDIOMA, getIcono());
		return UtilJSON.getJSONObject(props);
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
}
