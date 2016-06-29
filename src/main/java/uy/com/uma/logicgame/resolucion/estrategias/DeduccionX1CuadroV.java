package uy.com.uma.logicgame.resolucion.estrategias;

import java.util.Iterator;

import uy.com.uma.logicgame.resolucion.EstrategiaException;
import uy.com.uma.logicgame.resolucion.modelo.CuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.Dimension;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Analiza el primer cuadro por los valores de la columna (2° dimensión) y deduce valores (positivos o negativos, afirmaciones o negaciones)
 * para la primer fila de la matriz, siempre que encuentre valores positivos.
 *
 * @author Santiago Dalchiele
 */
public class DeduccionX1CuadroV extends EstrategiaAbstracta {

	
	
	/**
	 * Aplica la estrategia
	 * @param matriz
	 * @see uy.com.uma.logicgame.resolucion.estrategias.EstrategiaAbstracta#aplicarInt(uy.com.uma.logicgame.resolucion.modelo.MatrizDecision)
	 */
	@Override
	protected void aplicarInt(MatrizDecision matriz) throws EstrategiaException {
		CuadroDecision primerCuadro = matriz.getPrimerCuadro();
		Dimension primerDimension = matriz.getPrimerDimensionXFila();
		Dimension segundaDimension = matriz.getPrimerDimensionXCols();
		
		for (short i = 0; (i < cantValores) && cambios.isEmpty(); i++)
			for (short j = 0; (j < cantValores) && cambios.isEmpty(); j++)
				if (primerCuadro.getValor(i, j) == AFIRMA) {
					for (Iterator<Dimension> it1 = matriz.getDimensionesXFila().values().iterator(); it1.hasNext() && cambios.isEmpty();) {
						Dimension fila = it1.next();						
						CuadroDecision cuadro = matriz.getCuadro(fila, segundaDimension);						
							
						if ((cuadro != null) && (fila.getFila() != primerDimension.getFila())) {
							String idDim = cuadro.getDimensionXFila().getId();
							short colDest = matriz.getDimensionesXCols().get(idDim).getColumna();
							CuadroDecision cuadroDestino = matriz.getCuadro(CERO, colDest);
							
							for (short k = 0; (k < cantValores) && cambios.isEmpty(); k++)
								if (cuadro.getValor(k, j) != VACIA)
									addCambio(i, k, cuadro.getValor(k, j), cuadroDestino);				
						}
					}
				}			
	}
}
