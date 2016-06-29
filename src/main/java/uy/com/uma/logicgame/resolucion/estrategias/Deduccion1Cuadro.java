package uy.com.uma.logicgame.resolucion.estrategias;

import uy.com.uma.logicgame.resolucion.EstrategiaException;
import uy.com.uma.logicgame.resolucion.modelo.CuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Intenta deducir relaciones transitivas que infieran los valores del primer cuadro
 *
 * @author Santiago Dalchiele
 */
public class Deduccion1Cuadro extends EstrategiaAbstracta {

	
	/**
	 * Busca realaciones transitivas del estilos (dim1, dimH) ^ (dim2, dimH) => (dim1, dim2) con H variando entre 3 y cantidad de dimensiones
	 * @param matriz
	 * @throws EstrategiaException
	 * @see uy.com.uma.logicgame.resolucion.estrategias.EstrategiaAbstracta#aplicarInt(uy.com.uma.logicgame.resolucion.modelo.MatrizDecision)
	 */
	@Override
	protected void aplicarInt(MatrizDecision matriz) throws EstrategiaException {
		CuadroDecision primerCuadro = matriz.getPrimerCuadro();
		
		if (!primerCuadro.resuelto()) {
			short dimMatriz = (short) matriz.getDimensionesXCols().size();
			
			for (short dim = 1; dim < dimMatriz; dim++) {
				CuadroDecision cuadroXFil1 = matriz.getCuadro(CERO, dim);
				String idDim1 = cuadroXFil1.getDimensionXColumna().getId();
				short filaDim = matriz.getDimensionesXFila().get(idDim1).getFila();
				CuadroDecision cuadroXCol1 = matriz.getCuadro(filaDim, CERO);
				CuadroDecision cuadroXCol2 = matriz.getCuadro(dim, CERO);
				String idDim2 = cuadroXCol2.getDimensionXFila().getId();
				short colDim = matriz.getDimensionesXCols().get(idDim2).getCol();
				CuadroDecision cuadroXFil2 = matriz.getCuadro(CERO,  colDim);
				
				for (short fila1C = 0; fila1C < cantValores; fila1C++)
					for (short col1C = 0; col1C < cantValores; col1C++)
						if (primerCuadro.getValor(fila1C, col1C) == VACIA)
							for (short k = 0; k < cantValores; k++) {
								if ((cuadroXFil1.getValor(fila1C, k) == AFIRMA) && (cuadroXCol1.getValor(k, col1C) == AFIRMA))
									addCambio(fila1C, col1C, AFIRMA, primerCuadro);
								
								if ((cuadroXFil2.getValor(fila1C, k) == AFIRMA) && (cuadroXCol2.getValor(k, col1C) == AFIRMA))
									addCambio(fila1C, col1C, AFIRMA, primerCuadro);
							}
			}
		}
	}

}
