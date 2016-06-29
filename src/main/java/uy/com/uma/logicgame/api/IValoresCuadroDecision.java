package uy.com.uma.logicgame.api;

/**
 * Define las constantes de los posibles valores de una celda de un cuadro de decisi�n 
 *
 * @author Santiago Dalchiele
 */
public interface IValoresCuadroDecision {

	/** La celda se encuentra sin ninguna marca */
	public static final short VACIA = 1;
	
	/** Se marca como que entre los dos valores de las dimensiones que se cruzan en el cuadro existe una afirmaci�n */
	public static final short AFIRMA = 2;
	
	/** Se marca como que entre los dos valores de las dimensiones que se cruzan en el cuadro existe una negaci�n */
	public static final short NIEGA = 3;
}
