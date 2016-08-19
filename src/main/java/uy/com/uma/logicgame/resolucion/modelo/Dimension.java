package uy.com.uma.logicgame.resolucion.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
	private short nro;
	private short fila;
	private short columna;
	private Map<String, ValorDimension> valores = new HashMap<String, ValorDimension>();
	private List<ValorDimension> valoresXsec = new ArrayList<ValorDimension>();
	private Map<Short, ValorDimension> valoresXNro = new HashMap<Short, ValorDimension>();
		
	
	public Dimension (String id, short nro, short fila, short columna, Collection<ValorDimension> valores) {
		this.id = id;
		this.nro = nro;
		this.fila = fila;
		this.columna = columna;
		
		for (ValorDimension vd : valores) {
			this.valores.put(vd.getId(), vd);
			this.valoresXsec.add(vd);
			this.valoresXNro.put(vd.getSec(), vd);
		}		
	}
	
	
	
	public String getId() {
		return id;
	}
	public short getNro() {
		return this.nro;
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
	public String getValorXNro(Short nro) {
		return this.valoresXNro.get(nro).getId();
	}
	
	@Override
	public String toString() {
		return id + " [" + fila + "," + columna + "]";
	}	
}
