package uy.com.uma.logicgame.resolucion.estrategias;

import uy.com.uma.logicgame.resolucion.EstrategiaException;
import uy.com.uma.logicgame.resolucion.modelo.CuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.Dimension;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Llena los cuadros centrales con negaciones inferidas a partir de afirmaciones y negaciones encontradas en la primer columna de la matriz
 *
 * @author Santiago Dalchiele
 */
public class NegacionXCuadrosV extends EstrategiaAbstracta {

	
	
	/**
	 * Aplica la estrategia, infiere tansitividades del estilo que si (dimK R dim2) ^ (dimH !R dim2) => (dimK !R dimH)
	 * @param matriz
	 * @throws EstrategiaException
	 * @see uy.com.uma.logicgame.resolucion.estrategias.EstrategiaAbstracta#aplicarInt(uy.com.uma.logicgame.resolucion.modelo.MatrizDecision)
	 */
	@Override
	protected void aplicarInt(MatrizDecision matriz) throws EstrategiaException {
		short dimMatriz = (short) matriz.getDimensionesXCols().size();
		
		for (short k = 1; k < dimMatriz; k++) {
			CuadroDecision cuadroActual = matriz.getCuadro(k, CERO);
			
			for (short h = 1; h < dimMatriz; h++) {
				if (k != h) {
					CuadroDecision cuadro = matriz.getCuadro(h, CERO);
					short min = (short) Math.min(k, h);
					short max = (short) Math.max(k, h);
					Dimension dimMin = (k == min) ? cuadroActual.getDimensionXFila() : cuadro.getDimensionXFila();
					String idMax = (k == max) ? cuadroActual.getDimensionXFila().getId() : cuadro.getDimensionXFila().getId();
					Dimension dimMax = matriz.getDimensionesXCols().get(idMax);
					
					for (short i = 0; i < cantValores; i++)
						for (short j = 0; j < cantValores; j++)
							if (cuadroActual.getValor(i, j) == AFIRMA)
								for (short m = 0; m < cantValores; m++)
									if (cuadro.getValor(m, j) == NIEGA)	{										
										short fila = (k == min) ? i : m;
										short col = (k == min) ? m : i;
										addCambio(fila, col, NIEGA, matriz.getCuadro(dimMin, dimMax));
									}
				}
			}
		}
	}
}
