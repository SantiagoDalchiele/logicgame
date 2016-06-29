package uy.com.uma.logicgame.nucleo.jaxb.juego;

import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension.Valores;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas.Pista;

/**
 * Clases de utilería para la representación del juego con jaxb
 *
 * @author Santiago Dalchiele
 */
public abstract class UtilJuego {

	
	/**
	 * Retorna una copia del objeto juego
	 */
	public static Juego cloneJuego (Juego juego) {
		Juego nuevo = new Juego();
		nuevo.setCosto(juego.getCosto());
		nuevo.setId(juego.getId());
		nuevo.setTexto(juego.getTexto());
		nuevo.setTitulo(juego.getTitulo());
		nuevo.setDimensiones(cloneDimensiones(juego.getDimensiones()));
		nuevo.setPistasDelJuego(clonePistas(juego.getPistasDelJuego()));
		return nuevo;
	}
	
	
	
	/**
	 * Retorna una copia de las dimensiones
	 */
	public static Dimensiones cloneDimensiones (Dimensiones dims) {
		Dimensiones nueva = new Dimensiones();
		
		for (Dimension d : dims.getDimension())
			nueva.getDimension().add(cloneDimension(d));
		
		return nueva;
	}
	
	
	
	/**
	 * Retorna una copia de las pistas del juego
	 */
	public static PistasDelJuego clonePistas (PistasDelJuego pistas) {
		PistasDelJuego nueva = new PistasDelJuego();
		
		for (PistaDelJuego pj : pistas.getPistaDelJuego()) {
			PistaDelJuego pistJueg = new PistaDelJuego();
			pistJueg.setTexto(pj.getTexto());
			pistJueg.setPistas(new Pistas());
			
			for (Pista p : pj.getPistas().getPista()) {
				pistJueg.getPistas().getPista().add(clonePista(p));
			}
			
			nueva.getPistaDelJuego().add(pistJueg);
		}
		
		return nueva;
	}
	
	
	
	/**
	 * Retorna una copia de la dimension
	 */
	public static Dimension cloneDimension (Dimension d) {
		Dimension nueva = new Dimension();
		nueva.setId(d.getId());
		nueva.setValores(new Valores());
		
		for (String v : d.getValores().getValor())
			nueva.getValores().getValor().add(v);
		
		return nueva;
	}
	
	
	
	/**
	 * Retorna una copia de la pista
	 */
	public static Pista clonePista (Pista p) {
		Pista nueva = new Pista();
		nueva.setAfirmaNiega(p.isAfirmaNiega());
		nueva.setIdDimension1(p.getIdDimension1());
		nueva.setIdDimension2(p.getIdDimension2());
		nueva.setIdValor1(p.getIdValor1());
		nueva.setIdValor2(p.getIdValor2());
		return nueva;
	}
	
	
	
	/**
	 * Retorna TRUE si ambas pistas son identicas, coinciden en sus dimensiones y valores y si afirma/niega
	 */
	public static boolean equalsPista (Pista p1, Pista p2) {
		return (p1.isAfirmaNiega() == p2.isAfirmaNiega()) && 
				(p1.getIdDimension1().equals(p2.getIdDimension1())) && (p1.getIdDimension2().equals(p2.getIdDimension2())) &&
				(p1.getIdValor1().equals(p2.getIdValor1())) && (p1.getIdValor2().equals(p2.getIdValor2()));
	}
}
