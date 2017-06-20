package uy.com.uma.logicgame.resolucion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.uma.logicgame.api.IValoresCuadroDecision;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension.Valores.Valor;
import uy.com.uma.logicgame.nucleo.jaxb.juego.UtilJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;
import uy.com.uma.logicgame.resolucion.modelo.ArbolValores;
import uy.com.uma.logicgame.resolucion.modelo.CuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.DatoCuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;
import uy.com.uma.logicgame.resolucion.modelo.ValorDimension;

/**
 * Implementa la resolución de un juego por programación dinámica, probando todas las formas posibles de solución, para buscar cuales son válidas 
 *
 * @author Santiago Dalchiele
 */
class RazonadorXProgDinamica implements IValoresCuadroDecision {
	
	private static final Logger log = LogManager.getLogger(RazonadorXProgDinamica.class.getName());
	

	/** Matriz con las pistas asignadas */
	private MatrizDecision matrizInicial;
	
	/** Matriz auxiliar a los efectos de iterar sobre las distintas posibilidades de solución */
	private MatrizDecision matrizActual;
	
	/** Arboles con las posibles soluciones */
	private ArbolValores [] arboles;
	
	/** Dimensiones del juego en el archivo xml */
	private List<Dimension> dimensiones;
	
	/** Tiene solución el juego ? */
	private boolean tieneSolucion = true;
	
	/** La solución es única */
	private boolean solucionUnica = true;
	
	/** Idioma por defecto del juego */
	private String idiomaXDefecto;
	
	
	
	/**
	 * Construye la matriz inicial a partir del juego
	 * @param juego
	 * @throws ValidadorJuegoException
	 */
	public RazonadorXProgDinamica (Juego juego) throws ValidadorJuegoException {
		this.idiomaXDefecto =  UtilJuego.getIdiomaXDefecto(juego);
		new ValidadorJuego().validarJuego(juego);
		this.matrizInicial = MatrizDecisionBuilder.construir(idiomaXDefecto, juego.getDimensiones());
		this.matrizActual = MatrizDecisionBuilder.construir(idiomaXDefecto, juego.getDimensiones());
		this.dimensiones = juego.getDimensiones().getDimension();
		AsignadorPistas ap = new AsignadorPistas(this.matrizInicial);
		ap.asignar(this.idiomaXDefecto, juego.getPistasDelJuego());
		log.debug("Resolviendo por programacion dinamica el juego " + juego.getId());
		/*log.debug("Matriz inicial con las pistas asignadas:");
		log.debug(matrizInicial);*/
		resolver(juego);
	}	
	
	
	
	/**
	 * Retorna si tiene solución o no el juego
	 */
	public boolean tieneSolucion() {
		return tieneSolucion;
	}

	
	
	/**
	 * Retorna si la solución es única o no
	 */
	public boolean solucionUnica() {
		return solucionUnica;
	}
	

	
	/**
	 * Intenta resolver el juego por programación dinámica
	 * @param juego
	 */
	private void resolver (Juego juego) {
		construirArboles();
		/*log.debug("Arboles luego de inicializarlos:");
		for (int i = 0; i < arboles.length; i++) log.debug(arboles[i]);*/
		
		podarArboles();
		/*log.debug("Arboles luego de podarlos:");
		for (int i = 0; i < arboles.length; i++) log.debug(arboles[i]);*/
		
		//log.debug("Candidatos aún ambiguos y matriz de decisión:");
		matrizActual.copiar(matrizInicial);
		List<ArbolValores> abAmbiguos = new ArrayList<ArbolValores>();
		Collection<List<String>> solCandidata = new ArrayList<List<String>>();
		
		for (int i = 0; i < arboles.length; i++) {
			if (arboles[i].profundidad() != dimensiones.size()) {
				tieneSolucion = false;
				return;
			} else if (arboles[i].esLista()) {
				List<String> tuplaCandidata = arboles[i].getCandidatos().get(0); 
				setearRelaciones(tuplaCandidata);
				solCandidata.add(tuplaCandidata);
			} else
				abAmbiguos.add(arboles[i]);
		}		

		int cantNodos = sizeArboles(abAmbiguos);
		boolean huboPoda = true;
		
		/** Poda los árboles ambiguos hasta que no existan cambios, no existan arboles ambiguos o no tiene solución el juego */
		while (!abAmbiguos.isEmpty() && huboPoda) {
			//log.debug(matrizActual);
			Stack<Integer> aBorrar = new Stack<Integer>();
			int i = 0;
			
			for (Iterator<ArbolValores> it = abAmbiguos.iterator(); it.hasNext(); i++) {
				ArbolValores ab = it.next();				
				List<String> candidato = new ArrayList<String>();
				candidato.add(ab.getValor());
				podarArbol(ab, candidato);
				
				if ((!ab.esLista()) && (ab.profundidad() == dimensiones.size()))
					podarXValorUnico(ab, abAmbiguos);
				
				if (ab.profundidad() != dimensiones.size()) {
					tieneSolucion = false;
					return;
				} else if (ab.esLista()) {
					List<String> tuplaCandidata = ab.getCandidatos().get(0); 
					setearRelaciones(tuplaCandidata);
					solCandidata.add(tuplaCandidata);
					aBorrar.push(Integer.valueOf(i));
				}
			}						
			
			while (!aBorrar.isEmpty())
				abAmbiguos.remove(aBorrar.pop().intValue());
			
			huboPoda = (cantNodos != sizeArboles(abAmbiguos));
			cantNodos = sizeArboles(abAmbiguos);
		}
		
		/*log.debug("Arboles al final:");
		for (int i = 0; i < arboles.length; i++) log.debug(arboles[i]);*/
		
		solucionUnica = abAmbiguos.isEmpty();
		
		if (abAmbiguos.isEmpty())
			tieneSolucion = esCoherente(solCandidata);
	}
	
	
	
	/**
	 * Inicializa los arboles con todas las posibilidades para cada  valor de cada dimensión
	 */
	private void construirArboles() {
		Dimension primerDim = dimensiones.get(0);
		arboles = new ArbolValores[primerDim.getValores().getValor().size()];
		int i = 0;
		
		for (Valor v : primerDim.getValores().getValor()) {
			try {
				arboles[i] = construirNodo(UtilJuego.getTextoXIdioma(idiomaXDefecto, v.getId()), 1);
			} catch (PersistenciaException e) {
				log.error("Error inesperado, no se encuentra el texto del valor " + v.getId() + " para el idioma " + idiomaXDefecto);
			}
			
			i++;
		}
	}
	
	
	
	/**
	 * De forma recursiva construye cada nodo del árbol y sus hijos
	 * @param valor Valor del nodo
	 * @param nroDim número de dimensión a tener en cuento para agregar los hijos
	 * @return nodo del árbol
	 */
	private ArbolValores construirNodo (String valor, int nroDim) {		
		ArbolValores a = new ArbolValores(valor);
		
		if (nroDim < dimensiones.size())
			for (Valor v : dimensiones.get(nroDim).getValores().getValor()) {
				try {
					String val = UtilJuego.getTextoXIdioma(idiomaXDefecto, v.getId());
					a.hijos().add(construirNodo(val, nroDim+1));
				} catch (PersistenciaException e) {
					log.error("Error inesperado, no se encuentra el texto del valor " + v.getId() + " para el idioma " + idiomaXDefecto);
				}				
			}
				
		return a;
	}
	
	
	
	/**
	 * Inicia el proceso de podar todos los árboles con las posibles soluciones previamente armado
	 */
	private void podarArboles() {
		for (int i = 0; i < arboles.length; i++) {
			matrizActual.copiar(matrizInicial);
			List<String> candidato = new ArrayList<String>();
			candidato.add(arboles[i].getValor());
			podarArbol(arboles[i], candidato);
		}
	}
	
	
	
	/**
	 * Poda una rama del árbol en particular
	 * @param a nodo actual
	 * @param candidato tupla candidata a ser solución
	 */
	private void podarArbol(ArbolValores a, List<String> candidato) {
		int h = 0;
		Stack<Integer> aBorrar = new Stack<Integer>();
	
		for (Iterator<ArbolValores> it = a.hijos().iterator(); it.hasNext(); h++) {
			ArbolValores hijo = it.next();
			candidato.add(hijo.getValor());
			Collection<DatoCuadroDecision> cambiosAct = new ArrayList<DatoCuadroDecision>();					
			
			if (setearRelacion(candidato, cambiosAct))				
				podarArbol(hijo, candidato);
			else				
				aBorrar.push(Integer.valueOf(h));
			
			deshacerCambios(cambiosAct);
			candidato.remove(candidato.size()-1);
		}
		
		if ((aBorrar.isEmpty()) && (a.hijos().size() > 1)) {
			int prof = 0;
			h = 0;
			
			for (ArbolValores hijo : a.hijos()) {				
				if (hijo.profundidad() > prof)
					prof = hijo.profundidad();
			}
			
			for (Iterator<ArbolValores> it = a.hijos().iterator(); it.hasNext(); h++) {
				ArbolValores hijo = it.next();
				
				if (hijo.profundidad() != prof)
					aBorrar.push(Integer.valueOf(h));
			}
		}
		
		while (!aBorrar.isEmpty())
			a.hijos().remove(aBorrar.pop().intValue());
	}
	
	
	
	/**
	 * Setea todas las relaciones posibles dada la tupla candidata
	 * @param candidato
	 */
	private void setearRelaciones (List<String> candidato) {
		if (candidato.size() >= 2) {
			List<String> candidatoIncr = new ArrayList<String>();
			candidatoIncr.add(candidato.get(0));
			
			for (int i = 1; i < candidato.size(); i++) {
				Collection<DatoCuadroDecision> cambios = new ArrayList<DatoCuadroDecision>();
				candidatoIncr.add(candidato.get(i));
				setearRelacion(candidatoIncr, cambios);
			}
		}		
	}
	
	
	
	/**
	 * Setea en la matriz actual todos los valores dada las relaciones que se forman al relacionar el último
	 * elemento agregado a la lista de valores candidato con los restantes valores: (v1,vn)(v2,vn)..(vn-1,vn)
	 * Algunas relaciones pueden implicar modificar dos cuadros decisión, entrando por columnas o por filas a la matriz
	 * Si al intentar setear alguna relación se dá una contradicción retorna false
	 * @param candidato tupla candidata a ser solución
	 * @return cambios realizados
	 * @throws ContradiccionException
	 */
	private boolean setearRelacion (List<String> candidato, Collection<DatoCuadroDecision> cambios) {		
		if (candidato.size() > 1) {
			try {
				short ultimaDim = (short) (candidato.size() - 1);
				String idUltDim = UtilJuego.getTextoXIdioma(idiomaXDefecto, dimensiones.get(ultimaDim).getId());
				uy.com.uma.logicgame.resolucion.modelo.Dimension ultimaDimXCol = matrizActual.getDimensionesXCols().get(idUltDim);
				uy.com.uma.logicgame.resolucion.modelo.Dimension ultimaDimXFila = matrizActual.getDimensionesXFila().get(idUltDim);
				String valorUltDim = candidato.get(ultimaDim);
				
				for (short dim = 0; dim < ultimaDim; dim++) {
					CuadroDecision cuadro;
					String valorDim = candidato.get(dim);				
					
					if (dim == 0) {
						cuadro = matrizActual.getCuadro(dim, (short) (ultimaDim-1));
						short fila = cuadro.getDimensionXFila().getValores().get(valorDim).getSec();
						short col = cuadro.getDimensionXColumna().getValores().get(valorUltDim).getSec();
						
						if (! afirmarCandidato(fila, col, cuadro, cambios))
							return false;					
					} else if (dim == 1) {					
						cuadro = matrizActual.getCuadro(ultimaDimXFila.getFila(), (short) (dim-1));
						short fila = cuadro.getDimensionXFila().getValores().get(valorUltDim).getSec();
						short col = cuadro.getDimensionXColumna().getValores().get(valorDim).getSec();
						
						if (! afirmarCandidato(fila, col, cuadro, cambios))
							return false;
					} else {
						String idDim = UtilJuego.getTextoXIdioma(idiomaXDefecto, dimensiones.get(dim).getId());				
						uy.com.uma.logicgame.resolucion.modelo.Dimension dimXFila = matrizActual.getDimensionesXFila().get(idDim);
						cuadro = matrizActual.getCuadro(ultimaDimXFila.getFila(), (short) (dim-1));
						
						if (cuadro != null) {
							short fila = cuadro.getDimensionXFila().getValores().get(valorUltDim).getSec();
							short col = cuadro.getDimensionXColumna().getValores().get(valorDim).getSec();
							
							if (! afirmarCandidato(fila, col, cuadro, cambios))
								return false;
						}
						
						cuadro = matrizActual.getCuadro(dimXFila.getFila(), ultimaDimXCol.getCol());
						
						if (cuadro != null) {
							short fila = cuadro.getDimensionXFila().getValores().get(valorDim).getSec();
							short col = cuadro.getDimensionXColumna().getValores().get(valorUltDim).getSec();
							
							if (! afirmarCandidato(fila, col, cuadro, cambios))
								return false;
						}
					}
				}
			} catch (PersistenciaException e) {
				log.error("Error inesperado, no se encuentra el texto para el idioma " + idiomaXDefecto);
			}
		}
		
		return true;
	}
	
	
	
	/**
	 * Retorna la colección de cambios resultante de afirmar la posición [fila,col] del cuadro y
	 * negar todas las celdas de la columna col (excepto en la fila pasada como parámetro) y 
	 * todas las celdas de la fila pasada como parámetro (excepto en la columna pasada como parámetro)
	 * Retorna FALSE si se genera una contradicción
	 */
	private static boolean afirmarCandidato (short fila, short col, CuadroDecision cuadro, Collection<DatoCuadroDecision> cambios) {	
		try {
			for (short i = 0; i < cuadro.getCantValores(); i++)
				if (i != fila) {
					DatoCuadroDecision dato = addCambio(i, col, NIEGA, cuadro);
					
					if (dato != null)
						cambios.add(dato);
				}
			
			for (short j = 0; j < cuadro.getCantValores(); j++)
				if (j != col) {
					DatoCuadroDecision dato = addCambio(fila, j, NIEGA, cuadro);
					
					if (dato != null)
						cambios.add(dato);
				}
			
			DatoCuadroDecision dato = addCambio(fila, col, AFIRMA, cuadro);
			
			if (dato != null)
				cambios.add(dato);
			
			return true;
		} catch (ContradiccionException e) {
			return false;
		}					
	}
	
	
	
	/** 
	 * Retorna un cambio en un cuadro de decisión, si es que lo realiza. 
	 * Si el valor es el mismo que ya tenía en esa celda no retorna nada
	 * Si el valor de la celda no es vacía y es contradictorio con lo que ya hay tira excepción
	 */
	private static DatoCuadroDecision addCambio (short fila, short col, short valor, CuadroDecision cuadro) throws ContradiccionException {		
		if (cuadro.getValor(fila, col) == VACIA) {			
			uy.com.uma.logicgame.resolucion.modelo.Dimension dimXFila = cuadro.getDimensionXFila();
			uy.com.uma.logicgame.resolucion.modelo.Dimension dimXCol = cuadro.getDimensionXColumna();
			ValorDimension valorXFila = dimXFila.getValoresXSec().get(fila);
			ValorDimension valorXCol = dimXCol.getValoresXSec().get(col);		
			DatoCuadroDecision cambio = new DatoCuadroDecision(dimXCol, dimXFila, valorXCol, valorXFila);
			cambio.setValor(valor);
			cuadro.setValor(fila, col, valor);
			return cambio;
		} else
			if (cuadro.getValor(fila, col) != valor)
				throw new ContradiccionException("Cuadro: " + cuadro + 
						" [" + fila + "," + col + "]=" + (cuadro.getValor(fila, col) == AFIRMA ? "AFIRMA" : "NIEGA") + 
						", valor a asignar=" + (valor == AFIRMA ? "AFIRMA" : "NIEGA"));
		
		return null;
	}
	
	
	
	/**
	 * Deshace los cambios realizados en las iteraciones anteriores
	 */
	private void deshacerCambios (Collection<DatoCuadroDecision> cambios) {
		for (DatoCuadroDecision cambio : cambios) {
			matrizActual.setValor(cambio.getDimensionXFila(), cambio.getDimensionXColumna(), cambio.getValorXFila(), cambio.getValorXColumna(), VACIA);
		}
	}

	
	
	/**
	 * Retorna la cantidad de nodos del todos los árboles en la colección
	 */
	private static int sizeArboles (Collection<ArbolValores> arboles) {
		int acum = 0;
		
		for (ArbolValores arbol : arboles)
			acum += arbol.size();
		
		return acum;
	}
	
	
	
	/**
	 * Retorna TRUE si no se repiten valores entre las soluciones para las distintas dimensiones
	 */
	private boolean esCoherente (Collection<List<String>> solCandidata) {
		if (solCandidata.size() != matrizInicial.getCantValores())
			return false;
		else {
			@SuppressWarnings("unchecked")
			Map<String, String> [] valores = new HashMap[dimensiones.size()];
			
			for (int i = 0; i < dimensiones.size(); i++)
				valores[i] = new HashMap<String, String>();
			
			for (List<String> tupla : solCandidata) {				
				for (int i = 0; i < dimensiones.size(); i++) {
					String valor = tupla.get(i);
					
					if (valores[i].containsKey(valor))
						return false;
					else
						valores[i].put(valor, valor);
				}
			}
			
			return true;
		}		
	}
	
	
	
	/**
	 * Recorre todos los árboles ambiguos excepto el actual y si encuentra valores únicos en algún nivel de los demás árboles
	 * poda el actual para ese valor único
	 */
	private static void podarXValorUnico (ArbolValores actual, List<ArbolValores> abAmbiguos) {
		for (ArbolValores ab : abAmbiguos) {
			if (ab != actual)
				/** Busco valor único */
				for (int i = 2; i < actual.profundidad(); i++) {
					Map<String,String> valores = ab.getValoresEnNivel(i);
					
					if (valores.size() == 1) {
						String valor = valores.values().iterator().next();
						podarArbolXValor(actual, valor, i);
					}
				}				
		}
	}
	

	
	/**
	 * Si una rama del árbol tiene en el nivel el valor pasado como parámetro como único valor, poda esta rama
	 */
	private static void podarArbolXValor (ArbolValores ab, String valor, int nivel) {
		Stack<Integer> aBorrar = new Stack<Integer>();
		int h = 0;
		
		for (Iterator<ArbolValores> it = ab.hijos().iterator(); it.hasNext(); h++) {
			ArbolValores hijo = it.next();
			Map<String,String> valores = hijo.getValoresEnNivel(nivel-1);
			
			if ((valores.size() == 1) && (valor.equals(valores.values().iterator().next())))
				aBorrar.push(Integer.valueOf(h));
			else
				podarArbolXValor(hijo, valor, nivel-1);
		}
		
		while (!aBorrar.isEmpty())
			ab.hijos().remove(aBorrar.pop().intValue());
	}
}
