package uy.com.uma.logicgame.api.bean;

import java.util.ArrayList;
import java.util.Collection;

import uy.com.uma.comun.util.UtilJSON;
import uy.com.uma.comun.util.UtilString;

/**
 * Clase "liviana" que encapsula la información de un juego para trasegar estos datos
 *
 * @author Santiago Dalchiele
 */
public class JuegoDO implements IJSONObject {
	
	/** TAGs para retornar el objeto JSON */
	private static final String TAG_ID_JUEGO = "id";
	private static final String TAG_IDIOMAS_JUEGO = "idiomas";
	private static final String TAG_TITULO_JUEGO = "titulo";
	private static final String TAG_TEXTO_JUEGO = "texto";
	private static final String TAG_COSTO_JUEGO = "costo";
	private static final String TAG_CANT_DIMS_JUEGO = "cantDims";
	private static final String TAG_CANT_VALORES_JUEGO = "cantValores";
	private static final String TAG_SOLUCION_JUEGO = "solucion";
	private static final String TAG_DEFINICION_JUEGO = "defJuego";
	
	
	/** Atributos */
	private int id;
	private Collection<String> idiomas = new ArrayList<String>();
	private String titulo;
	private String texto;
	private int costo;
	private short cantDims;
	private short cantValores;
	private String solucion;
	private String definicion;
	
	
	
	
	/**
	 * Retorna el objeto en formato JSON: {"id":<id>,"idiomas":<idioma1,idioma2,...>,"titulo":<titulo>,"texto":<texto>,"costo":<costo>,
	 * 				"cantDims":<cantDims>,"cantValores":<cantValores>,"solucion":<solucion>,"defJuego":<defJuego>}
	 */
	public String toJSON() {
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		buf.append(UtilJSON.getPropJSON(TAG_ID_JUEGO) + UtilJSON.getValorJSON("" + getId()));
		buf.append(UtilJSON.getPropJSON(TAG_IDIOMAS_JUEGO) + UtilJSON.getValorJSON(UtilString.concatenar(getIdiomas(), ",")));
		buf.append(UtilJSON.getPropJSON(TAG_TITULO_JUEGO) + UtilJSON.getValorJSON(getTitulo()));
		buf.append(UtilJSON.getPropJSON(TAG_TEXTO_JUEGO) + UtilJSON.getValorJSON(getTexto()));
		buf.append(UtilJSON.getPropJSON(TAG_COSTO_JUEGO) + UtilJSON.getValorJSON("" + getCosto()));
		buf.append(UtilJSON.getPropJSON(TAG_CANT_DIMS_JUEGO) + UtilJSON.getValorJSON("" + getCantDims()));
		buf.append(UtilJSON.getPropJSON(TAG_CANT_VALORES_JUEGO) + UtilJSON.getValorJSON("" + getCantValores()));
		buf.append(UtilJSON.getPropJSON(TAG_SOLUCION_JUEGO) + UtilJSON.getValorJSON(getSolucion()));
		final String def = getDefinicion().replaceAll("\"", "'");
		buf.append(UtilJSON.getPropJSON(TAG_DEFINICION_JUEGO) + UtilJSON.getComillasJSON(def)); 
		buf.append("}");
		return buf.toString();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Collection<String> getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(Collection<String> idiomas) {
		this.idiomas = idiomas;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public short getCantDims() {
		return cantDims;
	}
	public void setCantDims(short cantDims) {
		this.cantDims = cantDims;
	}
	public short getCantValores() {
		return cantValores;
	}
	public void setCantValores(short cantValores) {
		this.cantValores = cantValores;
	}
	public String getSolucion() {
		return solucion;
	}
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}
	public String getDefinicion() {
		return definicion;
	}
	public void setDefinicion(String definicion) {
		this.definicion = definicion;
	}
}
