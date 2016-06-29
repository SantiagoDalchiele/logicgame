package uy.com.uma.logicgame.resolucion.estrategias;

import uy.com.uma.logicgame.resolucion.EstrategiaException;
import uy.com.uma.logicgame.resolucion.modelo.CuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Dadas las relaciones en los cuadros centrales, descarta por filas y columnas de la matriz valores no posibles seg�n las afirmaciones
 * en las relaciones de los cuadros centrales
 *
 * @author Santiago Dalchiele
 */
public class NegacionXCuadrosCentrales extends EstrategiaAbstracta {

	
	/**
	 * Aplica la estrategia
	 * 
	 * @param matriz
	 * @throws EstrategiaException
	 * @see uy.com.uma.logicgame.resolucion.estrategias.EstrategiaAbstracta#aplicarInt(uy.com.uma.logicgame.resolucion.modelo.MatrizDecision)
	 */
	@Override
	protected void aplicarInt(MatrizDecision matriz) throws EstrategiaException {
		short dimMatriz = (short) matriz.getDimensionesXCols().size();
		
		for (short i = 1; (i < dimMatriz) && cambios.isEmpty(); i++) 
			for (short j = 1; (j < dimMatriz) && cambios.isEmpty(); j++) {
				CuadroDecision cuadro = matriz.getCuadro(i, j);			
				
				/** Recorre el cuadro central */
				if (cuadro != null) {
					String idDimFila = cuadro.getDimensionXFila().getId();
					String idDimCol = cuadro.getDimensionXColumna().getId();
					
					for (short m = 0; (m < cantValores) && cambios.isEmpty(); m++)
						for (short n = 0; (n < cantValores) && cambios.isEmpty(); n++)
							if (cuadro.getValor(m, n) == AFIRMA) {								
								negarXFila(matriz, m, n, idDimFila, idDimCol);
								negarXFila(matriz, n, m, idDimCol, idDimFila);
								
								if (cambios.isEmpty()) {
									negarXColumna(matriz, m, n, idDimFila, idDimCol);
									negarXColumna(matriz, n, m, idDimCol, idDimFila);
								}

							}
				}
			}
	}
	
	
	
	/**
	 * Toma como cuadro origen de la deducci�n el que est� en la primer fila y la dimensi�n por fila pasada como par�metro (de la relaci�n central),
	 * toma como cuadro destino de la deducci�n el que est� en la primer fila y la dimensi�n por columna pasada como par�metro (de la relaci�n central),
	 * recorre el cuadro origen y por cada negaci�n en el cuadro en la posici�n [fila, fila_cuadro_central] niega en el cuadro destino en la 
	 * posici�n [fila, columna_cuadro_central]
	 * @param matriz matriz de decisi�n
	 * @param filaCC fila de la relaci�n del cuadro central (afirma)
	 * @param colCC columna de la relaci�n del cuadro central (afirma)
	 * @param idDimFila identificador de la dimensi�n por fila del cuadro central
	 * @param idDimCol identificador de la dimensi�n por columna del cuadro central
	 * @throws EstrategiaException
	 */
	private void negarXFila (MatrizDecision matriz, short filaCC, short colCC, String idDimFila, String idDimCol) throws EstrategiaException {
		short colOri = matriz.getDimensionesXCols().get(idDimFila).getCol();
		short colDest = matriz.getDimensionesXCols().get(idDimCol).getCol();
		CuadroDecision cuadroOrigen = matriz.getCuadro(CERO, colOri);
		CuadroDecision cuadroDestino = matriz.getCuadro(CERO, colDest);
		
		for (short fila = 0; fila < cantValores; fila++)
			if (cuadroOrigen.getValor(fila, filaCC) == NIEGA)
				addCambio(fila, colCC, NIEGA, cuadroDestino);		
	}


	
	/**
	 * Toma como cuadro origen de la deducci�n el que est� en la primer columna y la dimensi�n por fila pasada como par�metro (de la relaci�n central),
	 * toma como cuadro destino de la deducci�n el que est� en la primer columna y la dimensi�n por columna pasada como par�metro (de la relaci�n central),
	 * recorre el cuadro origen y por cada negaci�n en el cuadro en la posici�n [fila_cuadro_central, columna] niega en el cuadro destino en la 
	 * posici�n [columna_cuadro_central, columna]
	 * @param matriz matriz de decisi�n
	 * @param filaCC fila de la relaci�n del cuadro central (afirma)
	 * @param colCC columna de la relaci�n del cuadro central (afirma)
	 * @param idDimFila identificador de la dimensi�n por fila del cuadro central
	 * @param idDimCol identificador de la dimensi�n por columna del cuadro central
	 * @throws EstrategiaException
	 */
	private void negarXColumna (MatrizDecision matriz, short filaCC, short colCC, String idDimFila, String idDimCol) throws EstrategiaException {
		short filaOri = matriz.getDimensionesXFila().get(idDimFila).getFila();
		short filaDest = matriz.getDimensionesXFila().get(idDimCol).getFila();
		CuadroDecision cuadroOrigen = matriz.getCuadro(filaOri, CERO);
		CuadroDecision cuadroDestino = matriz.getCuadro(filaDest, CERO);
		
		for (short col = 0; col < cantValores; col++)
			if (cuadroOrigen.getValor(filaCC, col) == NIEGA)
				addCambio(colCC, col, NIEGA, cuadroDestino);		
	}
}