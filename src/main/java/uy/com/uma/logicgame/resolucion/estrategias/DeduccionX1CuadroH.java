package uy.com.uma.logicgame.resolucion.estrategias;

import java.util.Iterator;

import uy.com.uma.logicgame.resolucion.EstrategiaException;
import uy.com.uma.logicgame.resolucion.modelo.CuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.Dimension;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Analiza el primer cuadro por los valores de la fila (1° dimensión) y deduce valores (positivos o negativos, afirmaciones o negaciones)
 * para la primer columna de la matriz, siempre que encuentre valores positivos.
 *
 * @author Santiago Dalchiele
 */
public class DeduccionX1CuadroH extends EstrategiaAbstracta {

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
					for (Iterator<Dimension> it1 = matriz.getDimensionesXCols().values().iterator(); it1.hasNext() && cambios.isEmpty();) {
						Dimension col = it1.next();						
						CuadroDecision cuadro = matriz.getCuadro(primerDimension, col);						
							
						if ((cuadro != null) && (col.getCol() != segundaDimension.getCol())) {
							String idDim = cuadro.getDimensionXColumna().getId();
							short filaDest = matriz.getDimensionesXFila().get(idDim).getFila();
							CuadroDecision cuadroDestino = matriz.getCuadro(filaDest, CERO);
							
							for (short k = 0; (k < cantValores) && cambios.isEmpty(); k++)
								if (cuadro.getValor(i, k) != VACIA)
									addCambio(k, j, cuadro.getValor(i, k), cuadroDestino);				
						}
					}
				}	
	}
}
