package uy.com.uma.logicgame.resolucion.estrategias;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.api.conf.ConfiguracionLoadHelper;
import uy.com.uma.logicgame.resolucion.ContradiccionException;
import uy.com.uma.logicgame.resolucion.EstrategiaException;
import uy.com.uma.logicgame.resolucion.modelo.CuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.DatoCuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Esta no es una estrategia de las que se parametrizan para iterar sobre ellas.
 * Esta estrategia de resolución se utiliza una vez que las restantes no logran generar mas cambios en la matriz.
 * Toma el primer cuadro de la matriz.  Si alguna de las filas o columnas está incompleta, le asigna un valor positivo a una de las celdas vacías,
 * a partir de esta suposición intenta llegar a un absurdo, de lo contrario el valor positivo asignado lo dá como válido.
 *
 * @author Santiago Dalchiele
 */
public class PorAbsurdo extends EstrategiaAbstracta {
	
	private static final Logger log = LogManager.getLogger(PorAbsurdo.class.getName());
	
	private SortedSet<IEstrategia> estrategias;
	private int costoInicial = 1;
	
	

	/**
	 * Aplica la estrategia de deducir por absurdo.
	 * 
	 * @param matriz
	 * @throws EstrategiaException
	 * @see uy.com.uma.logicgame.resolucion.estrategias.EstrategiaAbstracta#aplicarInt(uy.com.uma.logicgame.resolucion.modelo.MatrizDecision)
	 */
	@Override
	protected void aplicarInt(MatrizDecision matriz) throws EstrategiaException {
		CuadroDecision cuadro = getCuadroCandidato(matriz);
		
		if (cuadro != null) {
			try {
				ConfiguracionLoadHelper cl = ConfiguracionLoadHelper.getInstancia(); 
				estrategias = cl.cargarEstrategias();
				costoInicial = cl.getCostoInicialXAbsuarod();
			} catch (ConfiguracionException e) {
				throw new EstrategiaException(e.getMessage(), e); 
			}	
			
			setCosto(costoInicial);
			log.debug("Aplicando estrategia por absurdo -----------------------------------------------------------------------------------------------------------------------------------");
				
			for (short i = 0; (i < cantValores)  && cambios.isEmpty(); i++)
				if (cantCeldasVaciasXFila(cuadro, i) == 2)
					for (int indVacia = 0; (indVacia < 2) && cambios.isEmpty(); indVacia++) {
						afirmarCandidatoXFila (cuadro, i, indVacia);
						
						try {
							aplicarEstrategias(matriz);
						} catch (EstrategiaException e) {
							if ((e instanceof ContradiccionException) && (indVacia == 0)) {
								deshacerCambios(matriz);
								cambios.clear();
								negarCandidatoXFila (cuadro, i, indVacia);
								costo += costoInicial;
								/*log.debug("Luego de deshacer todos los cambios realizados ------------------------------------------------------------------------------------------------");
								log.debug(matriz);
								log.debug("Costo actualizado: " + costo);*/
							} else
								throw e;
						}
					}
			
			if (cambios.isEmpty()) {
				for (short j = 0; (j < cantValores)  && cambios.isEmpty(); j++)
					if (cantCeldasVaciasXColumna(cuadro, j) == 2)
						for (int indVacia = 0; (indVacia < 2) && cambios.isEmpty(); indVacia++) {
							afirmarCandidatoXColumna (cuadro, j, indVacia);
							
							try {
								aplicarEstrategias(matriz);
							} catch (EstrategiaException e) {
								if ((e instanceof ContradiccionException) && (indVacia == 0)) {
									deshacerCambios(matriz);
									cambios.clear();
									negarCandidatoXColumna (cuadro, j, indVacia);
									costo += costoInicial;
									/*log.debug("Luego de deshacer todos los cambios realizados ------------------------------------------------------------------------------------------------");
									log.debug(matriz);
									log.debug("Costo actualizado: " + costo);*/
								} else
									throw e;
							}
						}
			}
		}
	}


	
	/**
	 * Retorna el primer cuadro candidato a aplicar el absurdo, el primero que no esté resuelto y 
	 * tiene en una fila o en alguna columna 2 celdas y solo 2 celdas vacías.
	 * El primer caudro candidato es el primer cuadro, luego recorre la primer fila, luego la primer columna y finalmente los cuadros centrales
	 */
	private CuadroDecision getCuadroCandidato(MatrizDecision matriz) {
		short dimMatriz = (short) matriz.getDimensionesXCols().size();
		
		for (short col = 0; col < dimMatriz; col++)
			if (esCuadroCandidato(matriz.getCuadro(CERO, col)))
				return matriz.getCuadro(CERO, col);
		
		for (short fila = 1; fila < dimMatriz; fila++)
			if (esCuadroCandidato(matriz.getCuadro(fila, CERO)))
				return matriz.getCuadro(fila, CERO);
		
		for (short fila = 1; fila < dimMatriz; fila++)
			for (short col = 1; col < dimMatriz; col++)
				if (esCuadroCandidato(matriz.getCuadro(fila, col)))
					return matriz.getCuadro(fila, col);
		
		return null;
	}
	
	
	
	/**
	 * Retorna TRUE si el cuadro es distinto de nulo, no está resuelto y tiene en una fila o en alguna columna 2 celdas y solo 2
	 * celdas vacías
	 */
	private boolean esCuadroCandidato (CuadroDecision cuadro) {
		if ((cuadro != null) && (!cuadro.resuelto())) {
			for (short i = 0; i < cantValores; i++)
				if (cantCeldasVaciasXFila(cuadro, i) == 2)
					return true;
		
			for (short j = 0; j < cantValores; j++)
				if (cantCeldasVaciasXColumna(cuadro, j) == 2)
					return true;
		}
		
		return false;
	}
	
	
	
	/**
	 * Retorna la cantidad de celdas vacías para esta fila en el cuadro
	 * @param cuadro Cuadro de decisión
	 * @param fila fila en cuestión
	 * @return cantidad de celdas vacías
	 */
	private int cantCeldasVaciasXFila (CuadroDecision cuadro, short fila) {
		int vacias = 0;
		
		for (short col = 0; col < cantValores; col++)
			if (cuadro.getValor(fila, col) == VACIA)
				vacias++;
		
		return vacias;
	}
	
	
	
	/**
	 * Retorna la cantidad de celdas vacías para esta columna en el cuadro
	 * @param cuadro Cuadro de decisión
	 * @param col columna en cuestión
	 * @return cantidad de celdas vacías
	 */
	private int cantCeldasVaciasXColumna (CuadroDecision cuadro, short col) {
		int vacias = 0;
		
		for (short fila = 0; fila < cantValores; fila++)
			if (cuadro.getValor(fila, col) == VACIA)
				vacias++;
		
		return vacias;
	}
	
	
	
	/**
	 * Afirma el candidato i-esimo vacío en esta fila del cuadro de decisión
	 * @param cuadro Cuadro de decisión
	 * @param fila fila en cuestión
	 * @param indVacia i-esima celda vacía
	 * @throws ContradiccionException 
	 */
	private void afirmarCandidatoXFila (CuadroDecision cuadro, short fila, int indVacia) throws ContradiccionException {
		for (short col = 0; col < cantValores; col++)
			if (cuadro.getValor(fila, col) == VACIA)
				if (indVacia == 0) {
					addCambio(fila, col, AFIRMA, cuadro);
					return;
				} else
					indVacia--;
	}
	
	
	
	/**
	 * Afirma el candidato i-esimo vacío en esta columna del cuadro de decisión
	 * @param cuadro Cuadro de decisión
	 * @param col columna en cuestión
	 * @param indVacia i-esima celda vacía

	 */
	private void afirmarCandidatoXColumna (CuadroDecision cuadro, short col, int indVacia) throws ContradiccionException {
		for (short fila = 0; fila < cantValores; fila++)
			if (cuadro.getValor(fila, col) == VACIA)
				if (indVacia == 0) {
					addCambio(fila, col, AFIRMA, cuadro);
					return;
				} else
					indVacia--;
	}
	
	
	
	/**
	 * Pone como vacia la celda que se seteó como posible candidato afirmativo
	 * @param cuadro Cuadro de decisión
	 * @param fila fila en cuestión
	 * @param indVacia i-esima celda vacía
	 * @throws ContradiccionException 
	 */
	private void negarCandidatoXFila (CuadroDecision cuadro, short fila, int indVacia) throws ContradiccionException {
		for (short col = 0; col < cantValores; col++)
			if (cuadro.getValor(fila, col) == VACIA)
				if (indVacia == 0) {
					addCambio(fila, col, NIEGA, cuadro);
					return;
				} else
					indVacia--;			
	}
	
	
	
	/**
	 * Pone como vacia la celda que se seteó como posible candidato afirmativo
	 * @param cuadro Cuadro de decisión
	 * @param col columna en cuestión
	 * @param indVacia i-esima celda vacía
	 */
	private void negarCandidatoXColumna (CuadroDecision cuadro, short col, int indVacia) throws ContradiccionException {
		for (short fila = 0; fila < cantValores; fila++)
			if (cuadro.getValor(fila, col) == VACIA)
				if (indVacia == 0) {
					addCambio(fila, col, NIEGA, cuadro);
					return;
				} else
					indVacia--;			
	}
	
	
	
	/**
	 * Aplica todas las restantes estrategias tomando como válido el valor asignado en primer término
	 * @param matriz
	 * @throws EstrategiaException
	 */
	private void aplicarEstrategias(MatrizDecision matriz) throws EstrategiaException {
		boolean hubieronCambios = true;
		
		while (hubieronCambios && (!matriz.resueltoCompleto())) {
			hubieronCambios = false;
			
			for (Iterator<IEstrategia> it = estrategias.iterator(); it.hasNext() && !hubieronCambios;) {
				IEstrategia estrategia = it.next();
				Collection<DatoCuadroDecision> cambiosIt = estrategia.aplicar(matriz);
				hubieronCambios = hubieronCambios || (!cambiosIt.isEmpty());
					
				if (!cambiosIt.isEmpty()) {
					costo += (cambiosIt.size() * estrategia.getCosto());
					super.cambios.addAll(cambiosIt);
					/*log.debug("Luego de aplicar estrategia " + estrategia.getClass().getSimpleName() + " ----------------------------------------------------------------");
					log.debug(matriz);
					log.debug("Costo actualizado (solo de la estrategia por absurdo): " + costo);*/
				}
			}
			
			/*if (!matriz.resueltoCompleto())
				costo++;*/
		}

	}	
	
	

	/**
	 * Deshace los cambios realizados en las iteraciones anteriores
	 * @param matriz Matriz de decisión
	 */
	private void deshacerCambios (MatrizDecision matriz) {
		for (DatoCuadroDecision cambio : cambios) {
			matriz.setValor(cambio.getDimensionXFila(), cambio.getDimensionXColumna(), cambio.getValorXFila(), cambio.getValorXColumna(), VACIA);
		}
	}
}
