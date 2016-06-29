package uy.com.uma.logicgame.resolucion.estrategias;

import uy.com.uma.logicgame.resolucion.EstrategiaException;
import uy.com.uma.logicgame.resolucion.modelo.CuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Intenta realizar deducciones transitivas analizando los cuadros centrales.
 * Dado un cuadro central, donde se afirma por ejemplo: dim3v1, dim4v2 y en la primer fila de la matriz se afirma dim1v3, dim3v1
 * entonces se afirma que dim1v3, dim4v2
 * (dim1v3, dim3v1) ^ (dim3v1, dim4v2) => (dim1v3, dim4v2) 
 *
 * @author Santiago Dalchiele
 */
public class DeduccionXCuadrosCentrales extends EstrategiaAbstracta {

	/**
	 * Aplica la estrategia
	 * Corta la aplicación de la estrategia al encontrar el primer cambio para bajar el costo total de resolucion ?
	 * @param matriz
	 * @see uy.com.uma.logicgame.resolucion.estrategias.EstrategiaAbstracta#aplicarInt(uy.com.uma.logicgame.resolucion.modelo.MatrizDecision)
	 */
	@Override
	protected void aplicarInt(MatrizDecision matriz) throws EstrategiaException {
		short dimMatriz = (short) matriz.getDimensionesXCols().size();
		CuadroDecision primerCuadro = matriz.getPrimerCuadro();		
		
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
								
								/** Recorre el primer cuadro */
								for (short k = 0; (k < cantValores) && cambios.isEmpty(); k++)
									for (short h = 0; (h < cantValores) && cambios.isEmpty(); h++)
										if (primerCuadro.getValor(k, h) == AFIRMA) {											
											deducirXFila(matriz, k, h, m, n, idDimFila, idDimCol);
											deducirXFila(matriz, k, h, n, m, idDimCol, idDimFila);
											
											if (cambios.isEmpty()) {
												deducirXColumna(matriz, k, h, m, n, idDimFila, idDimCol);
												deducirXColumna(matriz, k, h, n, m, idDimCol, idDimFila);
											}
										}
							}
				}
			}
	}

	
	
	/**
	 * Intenta realizar la transitividad desde el cuadro origen situado en la primer columna de la matriz, al cuadro destino en la primer
	 * fila de la matriz.
	 * @param matriz matriz de decisión
	 * @param fila1C fila actual en el primer cuadro
	 * @param col1C columna actual en el primer cuadro
	 * @param filaCC fila actual en el cuadro central
	 * @param colCC columna actual en el cuadro central
	 * @param idDimFila identificador de la dimensión por filas del cuadro central
	 * @param idDimCol identificador de la dimensión por columnas del cuadro central
	 */
	private void deducirXFila (MatrizDecision matriz, short fila1C, short col1C, short filaCC, short colCC, String idDimFila, String idDimCol) throws EstrategiaException {
		short filaOri = matriz.getDimensionesXFila().get(idDimCol).getFila();
		short colDest = matriz.getDimensionesXCols().get(idDimFila).getCol();
		CuadroDecision cuadroOrigen = matriz.getCuadro(filaOri, CERO);
		CuadroDecision cuadroDestino = matriz.getCuadro(CERO, colDest);
		
		if (cuadroOrigen.getValor(colCC, col1C) == AFIRMA)
			addCambio(fila1C, filaCC, AFIRMA, cuadroDestino);
	}	
	
	

	/**
	 * Intenta realizar la transitividad desde el cuadro origen situado en la primer fila de la matriz, al cuadro destino en la primer
	 * columna de la matriz.
	 * @param matriz matriz de decisión
	 * @param fila1C fila actual en el primer cuadro
	 * @param col1C columna actual en el primer cuadro
	 * @param filaCC fila actual en el cuadro central
	 * @param colCC columna actual en el cuadro central
	 * @param idDimFila identificador de la dimensión por filas del cuadro central
	 * @param idDimCol identificador de la dimensión por columnas del cuadro central
	 */
	private void deducirXColumna (MatrizDecision matriz, short fila1C, short col1C, short filaCC, short colCC, String idDimFila, String idDimCol) throws EstrategiaException {
		short colOri = matriz.getDimensionesXCols().get(idDimCol).getCol();
		short filaDest = matriz.getDimensionesXFila().get(idDimFila).getFila();
		CuadroDecision cuadroOrigen = matriz.getCuadro(CERO, colOri);
		CuadroDecision cuadroDestino = matriz.getCuadro(filaDest, CERO);
		
		if (cuadroOrigen.getValor(fila1C, colCC) == AFIRMA)
			addCambio(filaCC, col1C, AFIRMA, cuadroDestino);
	}
}
