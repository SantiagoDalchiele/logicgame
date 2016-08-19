package uy.com.uma.logicgame.resolucion;

import uy.com.uma.logicgame.api.IValoresCuadroDecision;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas.Pista;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;
import uy.com.uma.logicgame.resolucion.modelo.CuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.Dimension;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;
import uy.com.uma.logicgame.resolucion.modelo.ValorDimension;

/**
 * Dada una matriz de decisión y una colección de pistas iniciales del juego, asigna los valores de estas pistas.
 * Se asume que las pistas son consistentes con el juego
 *
 * @author Santiago Dalchiele
 * @see ValidadorJuego
 * @see MatrizDecisionBuilder
 */
class AsignadorPistas implements IValoresCuadroDecision {
	
	private MatrizDecision matriz;
	
	
	/**
	 * Construye la clase guardando referencia a la matriz de decisión
	 * @param m matriz de decisión
	 */
	public AsignadorPistas (MatrizDecision m) {
		this.matriz = m;
	}

	
	
	/**
	 * Dada una colección de pistas iniciales del juego, asigna los valores de estas pistas.
	 * 
	 * @param p pistas
	 */
	public void asignar (String idiomaXDefecto, PistasDelJuego p) throws ValidadorJuegoException {
		for (PistaDelJuego pj : p.getPistaDelJuego())
			for (Pista pista : pj.getPistas().getPista()) {
				Dimension dim1 = matriz.getDimensionXNro(pista.getDimension1());
				Dimension dim2 = matriz.getDimensionXNro(pista.getDimension2());
				String idDim1 =  dim1.getId();
				String idDim2 = dim2.getId();
				String idVal1 = dim1.getValorXNro((short) (pista.getValor1()-1));
				String idVal2 = dim2.getValorXNro((short) (pista.getValor2()-1));
				CuadroDecision c = getCuadroXPista(idDim1, idDim2);
				ValorDimension vf = getValorFila(idDim1, idDim2, idVal1, idVal2);
				ValorDimension vc = getValorColumna(idDim1, idDim2, idVal1, idVal2);
				matriz.setValor(c.getDimensionXFila(), c.getDimensionXColumna(), vf, vc, (pista.isAfirmaNiega() ? AFIRMA : NIEGA));
			}		
	}
	
	
	
	/**
	 * Retorna TRUE si se accede por la dimension 1 por columnas y por la dimension 2 por filas
	 * @param idDim1 identificador de la dimensión 1
	 * @param idDim2 identificador de la dimensión 2
	 * @return
	 */
	private boolean esXColumnayFila (String idDim1, String idDim2) {
		if (!matriz.getDimensionesXCols().containsKey(idDim1))
			return false;
		else if (!matriz.getDimensionesXFila().containsKey(idDim1))
			return true;			
		else if (!matriz.getDimensionesXCols().containsKey(idDim2))
			return true;
		else {
			Dimension dim1 = matriz.getDimensionesXCols().get(idDim1);
			Dimension dim2 = matriz.getDimensionesXCols().get(idDim2);			
			return (dim1.getCol() < dim2.getCol());
		}		
	}
	
	
	
	/**
	 * Retorna el cuadro de decisión dados dos identificadores de dimensiones
	 * @param idDim1 identificador de la dimensión 1
	 * @param idDim2 identificador de la dimensión 2
	 * @return Cuadro de decisión correspondiente
	 */
	private CuadroDecision getCuadroXPista (String idDim1, String idDim2) {
		Dimension dimFila;
		Dimension dimCol;		
			
		if (esXColumnayFila(idDim1, idDim2)) {
			dimCol = matriz.getDimensionesXCols().get(idDim1);
			dimFila = matriz.getDimensionesXFila().get(idDim2);
		} else {
			dimCol = matriz.getDimensionesXCols().get(idDim2);
			dimFila = matriz.getDimensionesXFila().get(idDim1);
		}	
		
		return matriz.getCuadro(dimFila, dimCol);
	}
	
	
	
	/**
	 * Retorna el valor por filas según de que dimensión acceder
	 * @param idDim1 identificador de la dimensión 1
	 * @param idDim2 identificador de la dimensión 2
	 * @param idVal1 identificador del valor 1
	 * @return
	 */
	private ValorDimension getValorFila (String idDim1, String idDim2, String idVal1, String idVal2) {
		if (esXColumnayFila(idDim1, idDim2))
			return matriz.getDimensionesXFila().get(idDim2).getValores().get(idVal2);
		else
			return matriz.getDimensionesXFila().get(idDim1).getValores().get(idVal1);
	}
	
	
	
	/**
	 * Retorna el valor por columnas según de que dimensión acceder
	 * @param idDim1 identificador de la dimensión 1
	 * @param idDim2 identificador de la dimensión 2
	 * @param idVal2 identificador del valor 2
	 * @return
	 */
	private ValorDimension getValorColumna (String idDim1, String idDim2, String idVal1, String idVal2) {
		if (esXColumnayFila(idDim1, idDim2))
			return matriz.getDimensionesXCols().get(idDim1).getValores().get(idVal1);
		else
			return matriz.getDimensionesXCols().get(idDim2).getValores().get(idVal2);
	}
}
