package uy.com.uma.logicgame.resolucion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension.Valores.Valor;
import uy.com.uma.logicgame.nucleo.jaxb.juego.UtilJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;
import uy.com.uma.logicgame.resolucion.modelo.Dimension;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;
import uy.com.uma.logicgame.resolucion.modelo.ValorDimension;

/**
 * Construye a partir de las dimensiones de un juego la matriz de decisión. 
 *
 * @author Santiago Dalchiele
 * @see MatrizDecision
 */
public abstract class MatrizDecisionBuilder {

	
	/**
	 * Construye a partir de las dimensiones de un juego la matriz de decisión.
	 * Se asume, que las dimensiones son válidas.
	 * 
	 * @param dimensiones dimensiones definidas en un archivo xml
	 * @return matriz de decisión
	 * @see ValidadorJuego
	 */
	public static MatrizDecision construir (String idiomaXDefecto, Dimensiones dimensiones) throws ValidadorJuegoException {
		List<Dimension> dimsXFila = new ArrayList<Dimension>();
		List<Dimension> dimsXCols = new ArrayList<Dimension>();		
		List<uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension> dims = dimensiones.getDimension();
		short NO_CORRESPONDE_IND = -1;
		
		/** Dimensiones por fila: la 1° y de la nésima hasta la 3° */
		uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension d = dims.get(0);
		
		try {
			String idDim = UtilJuego.getTextoXIdioma(idiomaXDefecto, d.getId());
			dimsXFila.add(new Dimension(idDim, d.getNro(), (short) 0, NO_CORRESPONDE_IND, getValores(idiomaXDefecto, d)));
			short j = 1;
			
			for (int i = (dims.size()-1); i >= 2; i--, j++) {
				d = dims.get(i);
				idDim = UtilJuego.getTextoXIdioma(idiomaXDefecto, d.getId());
				dimsXFila.add(new Dimension(idDim, d.getNro(), j, NO_CORRESPONDE_IND, getValores(idiomaXDefecto, d)));
			}		
			
			/** Dimensiones por columna: de la 2° hasta la última */
			for (int i = 1; i < dims.size(); i++) {
				d = dims.get(i);
				idDim = UtilJuego.getTextoXIdioma(idiomaXDefecto, d.getId());
				dimsXCols.add(new Dimension(idDim, d.getNro(), NO_CORRESPONDE_IND, (short) (i-1), getValores(idiomaXDefecto, d)));
			}		
			
			return new MatrizDecision(dimsXFila, dimsXCols);
		} catch (PersistenciaException e) {
			throw new ValidadorJuegoException(e);
		}		
	}
	
	
	
	/**
	 * Retorna una colección de identificadores + nro de secuencia de los valores de la dimensión
	 */
	private static Collection<ValorDimension> getValores (String idioma, uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension d) 
																			throws PersistenciaException {
		Collection<ValorDimension> valores = new ArrayList<ValorDimension>();
		
		for (Valor v : d.getValores().getValor())
			valores.add(new ValorDimension(UtilJuego.getTextoXIdioma(idioma, v.getId()), (short) (v.getNro()-1)));
		
		return valores;
	}
}
