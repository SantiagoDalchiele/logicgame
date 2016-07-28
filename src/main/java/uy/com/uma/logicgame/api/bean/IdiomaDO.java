package uy.com.uma.logicgame.api.bean;

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
	public String toJSON() {
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		buf.append(UtilJSON.getPropJSON(TAG_ID_IDIOMA) + UtilJSON.getValorJSON(getId()));
		buf.append(UtilJSON.getPropJSON(TAG_NOMBRE_IDIOMA) + UtilJSON.getValorJSON(getNombre()));
		buf.append(UtilJSON.getPropJSON(TAG_ICONO_IDIOMA) + UtilJSON.getComillasJSON(getIcono())); 
		buf.append("}");
		return buf.toString();
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
