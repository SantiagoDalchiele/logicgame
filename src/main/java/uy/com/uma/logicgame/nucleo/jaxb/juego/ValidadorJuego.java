package uy.com.uma.logicgame.nucleo.jaxb.juego;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import uy.com.uma.comun.util.UtilFormato;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas.Pista;

/**
 * Valida la consistencia de la clase Juego, todas las dimensiones tienen la misma cantidad de valores, etc
 *
 * @author Santiago Dalchiele
 */
public class ValidadorJuego {

	/** Mapea las dimensiones por su id con sus valores */
	private Map<String, Map<String,String>> dims = new HashMap<String, Map<String,String>>();
	
	
	
	
	/**
	 * Valida el juego y tira excepci�n con los mensajes de error
	 * @param juego juego definido en un archivo xml
	 * @throws ValidadorJuegoException excepcion con los mensajes de error (uno por l�nea)
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
	 * Retorna una colecci�n de mensajes que reportan errores en la estructura del juego armado en un archivo xml
	 * 
	 * @param juego juego definido en un archivo xml 
	 * @return colecci�n de mensajes de error
	 */
	public Collection<String> validar(Juego juego) {
		
		/** Lista de mensajes de retorno */
		Collection<String> msgs = new ArrayList<String>();
		
		dims.clear();
		
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
			
					validarDimensiones(juego.getDimensiones().getDimension(), msgs);
					validarPistas(juego.getPistasDelJuego().getPistaDelJuego(), msgs);
				}
			}
		}
		
		return msgs;
	}
	
	
	
	/**
	 * Valida las dimensiones
	 */
	private void validarDimensiones (List<Dimension> dim, Collection<String> msgs) {	
		
		int cont = 1;
		int cantValores = -1;
		
		for (Iterator<Dimension> it = dim.iterator(); it.hasNext(); cont++) {
			Dimension d = it.next();
			
			if (UtilFormato.esNulo(d.getId()))
				msgs.add("Debe definir el identificador de cada dimensi�n (" + cont + ")");
			else
				if (dims.containsKey(d.getId()))
					msgs.add("Cada dimensi�n debe tener un identificador �nico (" + d.getId() + ") (" + cont + ")");
				else {
					dims.put(d.getId(), new HashMap<String, String>());
			
					if (cantValores == -1) {
						if ((d.getValores().getValor().size() < 3) && (cont == 1))
							msgs.add("Debe definir al menos 3 valores para cada dimensi�n");
						else				
							cantValores = d.getValores().getValor().size();
					}
		
					if (d.getValores().getValor().size() != cantValores)
						msgs.add("Cada dimensi�n debe tener " + cantValores + " valores definidos (" + cont + ")");
					
					validarValores(d.getId(), d.getValores().getValor(), msgs);
				}
		}
	}
	
	
	
	/**
	 * Valida los valores de una dimensi�n
	 * @param idDim Identificador de la dimensi�n
	 * @param vals Lista de valores registrados en el archivo xml
	 * @param msgs colecci�n de mensajes de error
	 */
	private void validarValores (String idDim, List<String> vals, Collection<String> msgs) {
		Map<String,String> mapVal = dims.get(idDim);
		
		for (String valor : vals)			
			if (mapVal.containsKey(valor))
				msgs.add("La dimensi�n " + idDim + " repite el valor " + valor);
			else
				mapVal.put(valor, valor);
	}
	
	

	/**
	 * Valida de cada pista que su texto no sea nulo, que defina una secuencia de relaciones entre dimensiones y valores, y que
	 * cada dimensi�n est� definida, y que los valores est�n definidos dentro de cada dimensi�n.
	 * @param pistas Pistas del juego
	 * @param msgs colecci�n de mensajes de error
	 */
	private void validarPistas (List<PistaDelJuego> pistas, Collection<String> msgs) {
		int cont = 1;
		
		for (Iterator<PistaDelJuego> it = pistas.iterator(); it.hasNext(); cont++) {
			PistaDelJuego pista = it.next();
			
			if (UtilFormato.esNulo(pista.getTexto()))
				msgs.add("Debe definir un texto para cada pista (" + cont + ")");
			
			if (pista.getPistas().getPista().size() < 1)
				msgs.add("Debe definir al menos una relaci�n entre dimensiones y valores para cada pista (" + cont + ")");
			
			for (Pista p : pista.getPistas().getPista()) {			
				if (!dims.containsKey(p.getIdDimension1()))
					msgs.add("La dimensi�n " + p.getIdDimension1() + " definida en una pista, no hace referencia a una dimensi�n definida (" + cont + ")");
				else {
					if (!dims.get(p.getIdDimension1()).containsKey(p.getIdValor1()))
						msgs.add("El valor " + p.getIdValor1() + " no est� definido para la dimensi�n " + p.getIdDimension1() + "  (" + cont + ")");
				}
				
				if (!dims.containsKey(p.getIdDimension2()))
					msgs.add("La dimensi�n " + p.getIdDimension2() + " definida en una pista, no hace referencia a una dimensi�n definida (" + cont + ")");
				else {
					if (p.getIdDimension2().equals(p.getIdDimension1()))
						msgs.add("La pista no puede relacionar dos dimensiones iguales (" + p.getIdDimension1() + ") - (" + cont + ")");
					
					if (!dims.get(p.getIdDimension2()).containsKey(p.getIdValor2()))
						msgs.add("El valor " + p.getIdValor2() + " no est� definido para la dimensi�n " + p.getIdDimension2() + "  (" + cont + ")");
				}
			}
		}
	}
}