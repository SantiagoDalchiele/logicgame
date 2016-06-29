package uy.com.uma.logicgame.resolucion.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Define una dimensión una agrupación dentro de la matriz de decisión del juego de lógica.
 * Su identificador, el secuencial al que hace referencia en la fila de la matriz -1,0,1,..,N-1, 
 * además el secuencial al que hace referencia en la columna de la matriz de decisión -1,0,1,..,N-1,
 *
 * @author Santiago Dalchiele
 * @see MatrizDecision
 */
public class Dimension {

	private String id;
	private short fila;
	private short columna;
	private Map<String, ValorDimension> valores = new HashMap<String, ValorDimension>();
	private List<ValorDimension> valoresXsec = new ArrayList<ValorDimension>();
		
	
	public Dimension(String id, short fila, short columna, Collection<String> valores) {
		this.id = id;
		this.fila = fila;
		this.columna = columna;
		
		short sec = 0;
		
		for (Iterator<String> it = valores.iterator(); it.hasNext(); sec++) {
			String val = it.next();
			ValorDimension vd = new ValorDimension(val, sec);
			this.valores.put(val, vd);
			this.valoresXsec.add(vd);
		}		
	}
	
	
	
	public String getId() {
		return id;
	}
	public short getFila() {
		return fila;
	}
	public short getColumna() {
		return columna;
	}
	public short getCol() {
		return getColumna();
	}
	public Map<String, ValorDimension> getValores() {
		return this.valores;
	}
	public List<ValorDimension> getValoresXSec() {
		return this.valoresXsec;
	}
	
	@Override
	public String toString() {
		return id + " [" + fila + "," + columna + "]";
	}	
}
