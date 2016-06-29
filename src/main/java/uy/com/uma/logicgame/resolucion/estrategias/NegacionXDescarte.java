package uy.com.uma.logicgame.resolucion.estrategias;

import uy.com.uma.logicgame.resolucion.EstrategiaException;
import uy.com.uma.logicgame.resolucion.modelo.CuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.Dimension;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Dadas las afirmaciones en cada cuadro de decision, niega los valores restantes por filas y por columnas.
 *
 * @author Santiago Dalchiele
 */
public class NegacionXDescarte extends EstrategiaAbstracta {

	
	/**
	 * Dadas las afirmaciones en cada cuadro de decision, niega los valores restantes por filas y por columnas.
	 */
	@Override
	protected void aplicarInt (MatrizDecision matriz) throws EstrategiaException {		
		for (Dimension fila : matriz.getDimensionesXFila().values()) {			
			for (Dimension col : matriz.getDimensionesXCols().values()) {		
				CuadroDecision cuadro = matriz.getCuadro(fila, col);
				
				if (cuadro != null) {
					for (short i = 0; i < super.cantValores; i++)
						for (short j = 0; j < super.cantValores; j++)
							if (cuadro.getValor(i, j) == AFIRMA)
								negar(i, j, cuadro);						
				}
			}
		}
	}


	
	/**
	 * Niega todas las celdas de la fila i, y todas las celdas de la columna j del cuadro que estén vacías
	 * @param i fila
	 * @param j columna
	 * @param cuadro
	 */
	private void negar (short i, short j, CuadroDecision cuadro) throws EstrategiaException {
		for (short k = 0; k < super.cantValores; k++) {
			if (k != j)
				addCambio(i, k, NIEGA, cuadro);
			
			if (k != i)
				addCambio(k, j, NIEGA, cuadro);
		}
	}
}
