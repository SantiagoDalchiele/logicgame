package uy.com.uma.logicgame.resolucion.estrategias;

import uy.com.uma.logicgame.resolucion.EstrategiaException;
import uy.com.uma.logicgame.resolucion.modelo.CuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.Dimension;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Si en una fila o una columna de un cuadro son todas negaciones y la celda restante es vacía, entonces asigna una afirmación
 *
 * @author Santiago Dalchiele
 */
public class AfirmacionXDescarte extends EstrategiaAbstracta {

	/**
	 * @param matriz
	 * @see uy.com.uma.logicgame.resolucion.estrategias.EstrategiaAbstracta#aplicarInt(uy.com.uma.logicgame.resolucion.modelo.MatrizDecision)
	 */
	@Override
	protected void aplicarInt(MatrizDecision matriz) throws EstrategiaException {
		for (Dimension fila : matriz.getDimensionesXFila().values()) {			
			for (Dimension col : matriz.getDimensionesXCols().values()) {
				CuadroDecision cuadro = matriz.getCuadro(fila, col);
				
				if (cuadro != null) {
					for (short i = 0; i < super.cantValores; i++) {
						short k = colCandidata(cuadro, i);
						
						if (k != -1)
							super.addCambio(i, k, AFIRMA, cuadro);
					}
					
					for (short j = 0; j < super.cantValores; j++) {
						short h = filaCandidata(cuadro, j);
						
						if (h != -1)
							super.addCambio(h, j, AFIRMA, cuadro);
					}						
				}
			}
		}
	}

	
	
	/**
	 * Retorna la fila de la celda vacía para esta columna si es la única celda vacía en toda la columna y las restantes celdas son negaciones.
	 * @param cuadro Cuadro de decisión
	 * @param col columna
	 * @return fila de la única celda vacía, -1 en otro caso
	 */
	private short filaCandidata (CuadroDecision cuadro, short col) {
		int cantVacias = 0;
		short fila = -1;
		
		for (short i = 0; i < super.cantValores; i++) {
			if (cuadro.getValor(i, col) == AFIRMA)
				return -1;
			else
				if (cuadro.getValor(i, col) == VACIA) {
					fila = i;
					cantVacias++;
				}
		}
		
		return (cantVacias == 1) ? fila : -1;
	}
	
	
	
	/**
	 * Retorna la columna de la celda vacía para esta fila si es la única celda vacía en toda la fila y las restantes celdas son negaciones.
	 * @param cuadro Cuadro de decisión
	 * @param fila fila
	 * @return columna de la única celda vacía, -1 en otro caso
	 */
	private short colCandidata (CuadroDecision cuadro, short fila) {
		int cantVacias = 0;
		short col = -1;
		
		for (short j = 0; j < super.cantValores; j++) {
			if (cuadro.getValor(fila, j) == AFIRMA)
				return -1;
			else
				if (cuadro.getValor(fila, j) == VACIA) {
					col = j;
					cantVacias++;
				}
		}
		
		return (cantVacias == 1) ? col : -1;
	}
}
