package uy.com.uma.logicgame.generacion;

import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas.Pista;

/**
 * Persiste información de una dimensión, la cantidad de apariciones y pistas que contienen esta dimensión.
 * Utilizada a los efectos de agrupar pistas.
 *
 * @author Santiago Dalchiele
 */
class DimensionYPistas {
	
	private String id;
	private Pistas pistas = new Pistas();
	
	
	
	public DimensionYPistas() {		
	}
	
	public DimensionYPistas (String id) {
		this.setId(id);
	}
	
	
	
	public static String key (String idDim, String idVal) {
		return idDim + "|" + idVal;
	}
	
	public static String key (short nroDim, short nroVal) {
		return nroDim + "|" + nroVal;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Pistas getPistas() {
		return pistas;
	}
	public void addPista (Pista p) {
		this.pistas.getPista().add(p);
	}
}
