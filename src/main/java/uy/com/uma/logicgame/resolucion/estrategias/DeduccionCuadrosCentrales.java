package uy.com.uma.logicgame.resolucion.estrategias;

import uy.com.uma.logicgame.resolucion.EstrategiaException;
import uy.com.uma.logicgame.resolucion.modelo.CuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Intenta deducir relaciones transitivas que infieran los valores de los cuadros centrales, cuadros que relacionan dimensiones de la 3° en delante.
 *
 * @author Santiago Dalchiele
 */
public class DeduccionCuadrosCentrales extends EstrategiaAbstracta {

	/**
	 * Aplica la estrategia
	 * @param matriz
	 * @see uy.com.uma.logicgame.resolucion.estrategias.EstrategiaAbstracta#aplicarInt(uy.com.uma.logicgame.resolucion.modelo.MatrizDecision)
	 */
	@Override
	protected void aplicarInt(MatrizDecision matriz) throws EstrategiaException {
		CuadroDecision primerCuadro = matriz.getPrimerCuadro();
		
		for (short i = 0; i < cantValores; i++)
			for (short j = 0; j < cantValores; j++)
				if (primerCuadro.getValor(i, j) == AFIRMA) {
					deducirRelacionesXFila(matriz, i);
					deducirRelacionesXColumna(matriz, j);
				}
	}
	
	
	
	/**
	 * Deduce relaciones en los cuadros centrales recorriendo la primer fila de la matriz
	 * @param matriz matriz de decisión
	 * @param i fila del primer cuadro de decisión de la matriz donde se encuentra el valor afirmativo
	 */
	private void deducirRelacionesXFila (MatrizDecision matriz, short i) throws EstrategiaException {
		short dimMatriz = (short) matriz.getDimensionesXCols().size();
		
		for (short colOri = 1; colOri < (dimMatriz-1); colOri++) {
			CuadroDecision cuadroOri = matriz.getCuadro(CERO, colOri);
			
			for (short j = 0; j < cantValores; j++)
				if (cuadroOri.getValor(i, j) == AFIRMA)
					for (short colDest = (short)(colOri+1); colDest < dimMatriz; colDest++) {
						CuadroDecision cuadroDest = matriz.getCuadro(CERO, colDest);
						
						for (short k = 0; k < cantValores; k++)
							if (cuadroDest.getValor(i, k) == AFIRMA) {
								String idDimDest = cuadroDest.getDimensionXColumna().getId();
								short fila = matriz.getDimensionesXFila().get(idDimDest).getFila();
								CuadroDecision cuadroCentral = matriz.getCuadro(fila, colOri);
								addCambio(k, j, AFIRMA, cuadroCentral);
							}								
					}				
		}
	}
	
	
		
	/**
	 * Deduce relaciones en los cuadros centrales recorriendo la primer columna de la matriz
	 * @param matriz matriz de decisión
	 * @param j columna del primer cuadro de decisión de la matriz donde se encuentra el valor afirmativo
	 */
	private void deducirRelacionesXColumna (MatrizDecision matriz, short j) throws EstrategiaException {
		short dimMatriz = (short) matriz.getDimensionesXCols().size();
		
		for (short filaOri = 1; filaOri < (dimMatriz-1); filaOri++) {
			CuadroDecision cuadroOri = matriz.getCuadro(filaOri, CERO);
			
			for (short i = 0; i < cantValores; i++)
				if (cuadroOri.getValor(i, j) == AFIRMA)
					for (short filaDest = (short)(filaOri+1); filaDest < dimMatriz; filaDest++) {
						CuadroDecision cuadroDest = matriz.getCuadro(filaDest, CERO);
						
						for (short k = 0; k < cantValores; k++)
							if (cuadroDest.getValor(k, j) == AFIRMA) {
								String idDimDest = cuadroDest.getDimensionXFila().getId();
								short col = matriz.getDimensionesXCols().get(idDimDest).getCol();
								CuadroDecision cuadroCentral = matriz.getCuadro(filaOri, col);
								addCambio(i, k, AFIRMA, cuadroCentral);
							}								
					}				
		}
	}
}
