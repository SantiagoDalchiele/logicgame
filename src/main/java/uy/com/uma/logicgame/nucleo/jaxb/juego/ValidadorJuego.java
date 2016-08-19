package uy.com.uma.logicgame.nucleo.jaxb.juego;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import uy.com.uma.comun.util.UtilCollection;
import uy.com.uma.comun.util.UtilFormato;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension.Valores.Valor;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas.Pista;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Literal.Traduccion;

/**
 * Valida la consistencia de la clase Juego, todas las dimensiones tienen la misma cantidad de valores, etc
 *
 * @author Santiago Dalchiele
 */
public class ValidadorJuego {

	/** Mapea las dimensiones por su id con sus valores */
	private Map<String, Map<String,String>> dims = new HashMap<String, Map<String,String>>();
	
	/** Mapea las dimensiones por su nro y sus valores por nro */
	private Map<Short, Map<Short,Valor>> dimsXNro = new HashMap<Short, Map<Short,Valor>>();
	
	/** Enumera los idiomas a los que se traduce cada literal */
	private Collection<String> idiomas = new ArrayList<String>();
	
	
	
	
	/**
	 * Valida el juego y tira excepción con los mensajes de error
	 * @param juego juego definido en un archivo xml
	 * @throws ValidadorJuegoException excepcion con los mensajes de error (uno por línea)
	 */
	public void validarJuego (Juego juego) throws ValidadorJuegoException {
		try {
			Collection<String> msgs = validar(juego);
			
			if (!msgs.isEmpty()) {
				String mensajes = "";
				
				for(String m : msgs)
					mensajes += m + "\n";
				
				throw new ValidadorJuegoException(mensajes);
			}
		} catch (Exception e) {
			throw new ValidadorJuegoException(e);
		}
	}
	
	
	
	/**
	 * Retorna una colección de mensajes que reportan errores en la estructura del juego armado en un archivo xml
	 * 
	 * @param juego juego definido en un archivo xml 
	 * @return colección de mensajes de error
	 */
	public Collection<String> validar(Juego juego) {
		
		/** Lista de mensajes de retorno */
		Collection<String> msgs = new ArrayList<String>();
		
		dims.clear();
		dimsXNro.clear();
		
		if (juego == null) 
			msgs.add("Error el juego es nulo");
		else {
			if (juego.getDimensiones() == null)
				msgs.add("Error las dimensiones son nulas");
			else {
				if (juego.getPistasDelJuego() == null)
					msgs.add("Error las pistas del juego son nulas");
				else {
					if (juego.getDimensiones().getDimension().size() < 3)
						msgs.add("Debe definir al menos 3 dimensiones");
			
					if (juego.getPistasDelJuego().getPistaDelJuego().size() < 1)
						msgs.add("Debe definir al menos 1 pista");		
			
					validarTraducciones(juego, msgs);
					validarDimensiones(juego.getDimensiones().getDimension(), msgs);
					validarPistas(juego.getPistasDelJuego().getPistaDelJuego(), msgs);
				}
			}
		}
		
		return msgs;
	}
	
	
	
	/**
	 * Toma como válidos los idiomas de la traducción del título y en base a estos todas las restantes 
	 * traducciones del juego deben contener exactamente los mismos idiomas.
	 * Osea en todos los literales ya sea del juego, las dimensiones, sus valores o las pistas deben tener
	 * traducciones a los mismos idiomas
	 */
	private void validarTraducciones (Juego juego, Collection<String> msgs) {
		idiomas = UtilJuego.getIdiomas(juego.getTitulo());
		
		if (idiomas.isEmpty()) {
			msgs.add("Debe definir al menos un idioma");
			return;
		}
		
		for (String idioma : idiomas)
			if (UtilFormato.esNulo(idioma)) {
				msgs.add("Al menos un idioma es nulo");
				return;
			}				
		
		Collection<String> idm = UtilJuego.getIdiomas(juego.getTexto());		
		
		if (!UtilCollection.equals(idiomas, idm))
			msgs.add("Debe definir la traduccion para todos los idiomas en el texto del juego");
		
		validarLiteral(juego.getTexto(), msgs);
		validarLiteral(juego.getTitulo(), msgs);
		
		for (Dimension d : juego.getDimensiones().getDimension()) {
			idm = UtilJuego.getIdiomas(d.getId());		
			validarLiteral(d.getId(), msgs);
			
			if (!UtilCollection.equals(idiomas, idm))
				msgs.add("Debe definir la traduccion para todos los idiomas en cada dimension del juego, dimension nro " + d.getNro());
			
			for (Valor v : d.getValores().getValor()) {
				if ((v == null) || (v.getId() == null))
					msgs.add("Valor nulo en la dimension nro " + d.getNro());
				else {
					idm = UtilJuego.getIdiomas(v.getId());
					validarLiteral(v.getId(), msgs);
					
					if (!UtilCollection.equals(idiomas, idm))
						msgs.add("Debe definir la traduccion para todos los idiomas en cada dimension y cada valor del juego, dimension nro " + d.getNro() + " valor nro " + v.getNro());
				}
			}
		}
		
		int nroPista = 1;
		
		for (PistaDelJuego pista : juego.getPistasDelJuego().getPistaDelJuego()) {
			idm = UtilJuego.getIdiomas(pista.getTexto());
			validarLiteral(pista.getTexto(), msgs);
			
			if (!UtilCollection.equals(idiomas, idm))
				msgs.add("Debe definir la traduccion para todos los idiomas en cada pista del juego, pista nro " + nroPista);			
			
			nroPista++;
		}
	}
	
	
	
	/**
	 * Valida que todas las traducciones del literal tengan un idioma y un texto no nulo
	 */
	private void validarLiteral (Literal l, Collection<String> msgs) {
		for (Traduccion t : l.getTraduccion()) {
			if (UtilFormato.esNulo(t.getIdioma()))
				msgs.add("Idioma nulo para el literal " + l);
			
			if (UtilFormato.esNulo(t.getTexto()))
				msgs.add("Texto nulo para el literal " + l);					
		}
	}
	
	
	
	/**
	 * Valida las dimensiones
	 */
	private void validarDimensiones (List<Dimension> dim, Collection<String> msgs) {		
		int cantValores = -1;
		
		for (Dimension d : dim) {
			if (dimsXNro.containsKey(d.getNro()))
				msgs.add("Cada dimensión debe tener un numero unico (" + d.getNro() + ")");
			else
				dimsXNro.put(d.getNro(), new HashMap<Short, Valor>());
			
			validarValoresXNro(d.getNro(), d.getValores().getValor(), msgs);
			
			for (Traduccion t : d.getId().getTraduccion()) {
				if (UtilFormato.esNulo(d.getId()))
					msgs.add("Debe definir el identificador de cada dimensión (" + d.getNro() + ")");
				else {
					String clave = claveXIdioma(t.getIdioma(), t.getTexto());					
					
					if (dims.containsKey(clave))
						msgs.add("Cada dimensión debe tener un identificador unico (" + clave + "), (" + d.getNro() + ")");
					else {
						dims.put(clave, new HashMap<String, String>());
						
				
						if (cantValores == -1) {
							if ((d.getValores().getValor().size() < 3) && (d.getNro() == 1))
								msgs.add("Debe definir al menos 3 valores para cada dimensión");
							else				
								cantValores = d.getValores().getValor().size();
						}
			
						if (d.getValores().getValor().size() != cantValores)
							msgs.add("Cada dimensión debe tener " + cantValores + " valores definidos (" + d.getNro() + ")");
						
						validarValores(t.getTexto(), t.getIdioma(), d.getValores().getValor(), msgs);
					}
				}
			}
		}
	}
	
	
	
	/**
	 * Valida que los numeros de valores no se repitan
	 */
	private void validarValoresXNro (Short nroDim, List<Valor> vals, Collection<String> msgs) {
		Map<Short, Valor> mapXNro = dimsXNro.get(nroDim);
		
		for (Valor v : vals) {
			if ((v != null) && (v.getId() != null)) {
				if (mapXNro.containsKey(v.getNro()))
					msgs.add("La dimension nro " + nroDim + " repite el valor nro " + v.getNro());
				else
					mapXNro.put(v.getNro(), v);
			}
		}
	}
	

	
	/**
	 * Valida los valores de una dimensión
	 * @param idDim Identificador de la dimensión
	 * @param vals Lista de valores registrados en el archivo xml
	 * @param msgs colección de mensajes de error
	 */
	private void validarValores (String idDim, String idioma, List<Valor> vals, Collection<String> msgs) {
		Map<String, String> mapVal = dims.get(claveXIdioma(idioma, idDim));
		
		for (Valor v : vals) {
			if ((v != null) && (v.getId() != null)) {
				for (Traduccion t : v.getId().getTraduccion()) {
					try {
						String valor = claveXIdioma(t.getIdioma(), UtilJuego.getTextoXIdioma(t.getIdioma(), v.getId()));
					
						if (mapVal.containsKey(valor))
							msgs.add("La dimension " + idDim + " repite el valor " + valor + " para el idioma " + t.getIdioma());
						else
							mapVal.put(valor, valor);
					} catch (PersistenciaException e) {
						msgs.add(e.getMessage());
					}
				}
			}
		}
	}
	
	

	/**
	 * Valida de cada pista que su texto no sea nulo, que defina una secuencia de relaciones entre dimensiones y valores, y que
	 * cada dimensión esté definida, y que los valores estén definidos dentro de cada dimensión.
	 * @param pistas Pistas del juego
	 * @param msgs colección de mensajes de error
	 */
	private void validarPistas (List<PistaDelJuego> pistas, Collection<String> msgs) {
		int cont = 1;
		
		for (Iterator<PistaDelJuego> it = pistas.iterator(); it.hasNext(); cont++) {
			PistaDelJuego pista = it.next();
			
			if (UtilFormato.esNulo(pista.getTexto()))
				msgs.add("Debe definir un texto para cada pista (" + cont + ")");
			
			if (pista.getPistas().getPista().size() < 1)
				msgs.add("Debe definir al menos una relación entre dimensiones y valores para cada pista (" + cont + ")");			
			
			for (Pista p : pista.getPistas().getPista()) {
				if (!dimsXNro.containsKey(p.getDimension1()))
					msgs.add("La dimension " + p.getDimension1() + " definida en una pista, no hace referencia a una dimensión definida (" + cont + ")");
				else {
					if (!dimsXNro.get(p.getDimension1()).containsKey(p.getValor1()))
						msgs.add("El valor " + p.getValor1() + " no esta definido para la dimension " + p.getDimension1() + "  (" + cont + ")");
				}
				
				if (!dimsXNro.containsKey(p.getDimension2()))
					msgs.add("La dimension " + p.getDimension2() + " definida en una pista, no hace referencia a una dimension definida (" + cont + ")");
				else {
					if (p.getDimension1() == p.getDimension2())
						msgs.add("La pista no puede relacionar dos dimensiones iguales (" + p.getDimension1() + ") - (" + cont + ")");
					
					if (!dimsXNro.get(p.getDimension2()).containsKey(p.getValor2()))
						msgs.add("El valor " + p.getValor2() + " no está definido para la dimensión " + p.getDimension2() + "  (" + cont + ")");
				}
			}
		}
	}
	
	
	
	/**
	 * Retorna una clave única por idioma e identificador
	 */
	private static String claveXIdioma (String idioma, String id) {
		return idioma + "|" + id;
	}
}