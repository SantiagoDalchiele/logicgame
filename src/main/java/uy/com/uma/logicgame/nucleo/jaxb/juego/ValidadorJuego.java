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
				msgs.add("Debe definir el identificador de cada dimensión (" + cont + ")");
			else
				if (dims.containsKey(d.getId()))
					msgs.add("Cada dimensión debe tener un identificador único (" + d.getId() + ") (" + cont + ")");
				else {
					dims.put(d.getId(), new HashMap<String, String>());
			
					if (cantValores == -1) {
						if ((d.getValores().getValor().size() < 3) && (cont == 1))
							msgs.add("Debe definir al menos 3 valores para cada dimensión");
						else				
							cantValores = d.getValores().getValor().size();
					}
		
					if (d.getValores().getValor().size() != cantValores)
						msgs.add("Cada dimensión debe tener " + cantValores + " valores definidos (" + cont + ")");
					
					validarValores(d.getId(), d.getValores().getValor(), msgs);
				}
		}
	}
	
	
	
	/**
	 * Valida los valores de una dimensión
	 * @param idDim Identificador de la dimensión
	 * @param vals Lista de valores registrados en el archivo xml
	 * @param msgs colección de mensajes de error
	 */
	private void validarValores (String idDim, List<String> vals, Collection<String> msgs) {
		Map<String,String> mapVal = dims.get(idDim);
		
		for (String valor : vals)			
			if (mapVal.containsKey(valor))
				msgs.add("La dimensión " + idDim + " repite el valor " + valor);
			else
				mapVal.put(valor, valor);
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
				if (!dims.containsKey(p.getIdDimension1()))
					msgs.add("La dimensión " + p.getIdDimension1() + " definida en una pista, no hace referencia a una dimensión definida (" + cont + ")");
				else {
					if (!dims.get(p.getIdDimension1()).containsKey(p.getIdValor1()))
						msgs.add("El valor " + p.getIdValor1() + " no está definido para la dimensión " + p.getIdDimension1() + "  (" + cont + ")");
				}
				
				if (!dims.containsKey(p.getIdDimension2()))
					msgs.add("La dimensión " + p.getIdDimension2() + " definida en una pista, no hace referencia a una dimensión definida (" + cont + ")");
				else {
					if (p.getIdDimension2().equals(p.getIdDimension1()))
						msgs.add("La pista no puede relacionar dos dimensiones iguales (" + p.getIdDimension1() + ") - (" + cont + ")");
					
					if (!dims.get(p.getIdDimension2()).containsKey(p.getIdValor2()))
						msgs.add("El valor " + p.getIdValor2() + " no está definido para la dimensión " + p.getIdDimension2() + "  (" + cont + ")");
				}
			}
		}
	}
}