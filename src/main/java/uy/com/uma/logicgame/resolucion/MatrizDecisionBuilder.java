package uy.com.uma.logicgame.resolucion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuego;
import uy.com.uma.logicgame.resolucion.modelo.Dimension;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Construye a partir de las dimensiones de un juego la matriz de decisión. 
 *
 * @author Santiago Dalchiele
 * @see MatrizDecision
 */
public abstract class MatrizDecisionBuilder {

	
	/**
	 * Construye a partir de las dimensiones de un juego la matriz de decisión.
	 * Se asume, que las dimensiones son válidas.
	 * 
	 * @param dimensiones dimensiones definidas en un archivo xml
	 * @return matriz de decisión
	 * @see ValidadorJuego
	 */
	public static MatrizDecision construir (Dimensiones dimensiones) {
		List<Dimension> dimsXFila = new ArrayList<Dimension>();
		List<Dimension> dimsXCols = new ArrayList<Dimension>();		
		List<uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension> dims = dimensiones.getDimension();
		short NO_CORRESPONDE_IND = -1;
		
		/** Dimensiones por fila: la 1° y de la nésima hasta la 3° */
		uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension d = dims.get(0);
		dimsXFila.add(new Dimension(d.getId(), (short) 0, NO_CORRESPONDE_IND, getValores(d)));
		short j = 1;
		
		for (int i = (dims.size()-1); i >= 2; i--, j++) {
			d = dims.get(i);
			dimsXFila.add(new Dimension(d.getId(), j, NO_CORRESPONDE_IND, getValores(d)));
		}		
		
		/** Dimensiones por columna: de la 2° hasta la última */
		for (int i = 1; i < dims.size(); i++) {
			d = dims.get(i);
			dimsXCols.add(new Dimension(d.getId(), NO_CORRESPONDE_IND, (short) (i-1), getValores(d)));
		}		
		
		return new MatrizDecision(dimsXFila, dimsXCols);
	}
	
	
	
	/**
	 * Retorna una colección de identificadores de los valores de la dimensión
	 * 
	 * @param d dimensión
	 * @return colección de indentificadores de los valores
	 */
	private static Collection<String> getValores (uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension d) {
		Collection<String> valores = new ArrayList<String>();
		
		for (String val : d.getValores().getValor())
			valores.add(val);
		
		return valores;
	}
}
