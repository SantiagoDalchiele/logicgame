package uy.com.uma.logicgame.resolucion.modelo;

import uy.com.uma.logicgame.api.IValoresCuadroDecision;

/**
 * Dado el cruzamiento entre dos dimensiones y dos valores encapsula su valor
 *
 * @author Santiago Dalchiele
 */
public class DatoCuadroDecision implements IValoresCuadroDecision {

	private Dimension dimensionXColumna;
	private Dimension dimensionXFila;
	private ValorDimension valorXColumna;
	private ValorDimension valorXFila;
	private short valor;
	
	
	
	/**
	 * Construye una celda vacía dada las dimensiones y los valores que definen el cruzamiento o relación.
	 * 
	 * @param dimXCol dimensión por columna de la matriz de decisión
	 * @param dimXFila dimensión por fila de la matriz de decisión
	 * @param valorXCol valor por columna del cuadro de decisión
	 * @param valorXFila valor por fila del cuadro de decisión
	 */
	public DatoCuadroDecision (Dimension dimXCol, Dimension dimXFila, ValorDimension valorXCol, ValorDimension valorXFila) {
		this.dimensionXColumna = dimXCol;
		this.dimensionXFila = dimXFila;
		this.valorXColumna = valorXCol;
		this.valorXFila = valorXFila;
		this.valor = VACIA;
	}
	
	
	public Dimension getDimensionXColumna() {
		return dimensionXColumna;
	}
	public Dimension getDimensionXFila() {
		return dimensionXFila;
	}
	public ValorDimension getValorXColumna() {
		return valorXColumna;
	}
	public ValorDimension getValorXFila() {
		return valorXFila;
	}
	public short getValor() {
		return valor;
	}
	public void setValor(short valor) {
		this.valor = valor;
	}	
}
