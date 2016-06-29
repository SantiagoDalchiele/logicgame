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
	 * Construye una celda vac�a dada las dimensiones y los valores que definen el cruzamiento o relaci�n.
	 * 
	 * @param dimXCol dimensi�n por columna de la matriz de decisi�n
	 * @param dimXFila dimensi�n por fila de la matriz de decisi�n
	 * @param valorXCol valor por columna del cuadro de decisi�n
	 * @param valorXFila valor por fila del cuadro de decisi�n
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
