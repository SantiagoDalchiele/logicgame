package uy.com.uma.logicgame.nucleo.jaxb.juego;

import java.util.ArrayList;
import java.util.Collection;

import uy.com.uma.comun.util.UtilString;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension.Valores;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension.Valores.Valor;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas.Pista;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Literal.Traduccion;

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
		nuevo.setTexto(cloneLiteral(juego.getTexto()));
		nuevo.setTitulo(cloneLiteral(juego.getTitulo()));
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
			pistJueg.setTexto(cloneLiteral(pj.getTexto()));
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
		nueva.setNro(d.getNro());
		nueva.setId(cloneLiteral(d.getId()));
		nueva.setValores(new Valores());
		
		for (Valor v : d.getValores().getValor())
			nueva.getValores().getValor().add(cloneValor(v));
		
		return nueva;
	}
	
	
	
	/**
	 * Retorna una copia del valor
	 */
	public static Valor cloneValor (Valor v) {
		Valor nuevo = new Valor();
		nuevo.setId(cloneLiteral(v.getId()));
		nuevo.setNro(v.getNro());
		return nuevo;
	}
	
	
	
	/**
	 * Retorna una copia de la pista
	 */
	public static Pista clonePista (Pista p) {
		Pista nueva = new Pista();
		nueva.setAfirmaNiega(p.isAfirmaNiega());
		nueva.setDimension1(p.getDimension1());
		nueva.setDimension2(p.getDimension2());
		nueva.setValor1(p.getValor1());
		nueva.setValor2(p.getValor2());
		return nueva;
	}
	
	
	
	/**
	 * Retorna una copia del literal
	 */
	public static Literal cloneLiteral (Literal l) {
		Literal nuevo = new Literal();
		
		for (Traduccion t : l.getTraduccion())
			nuevo.getTraduccion().add(cloneTraduccion(t));
		
		return nuevo;
	}
	
	
	
	/**
	 * Retorna una copia de la traducción
	 */
	public static Traduccion cloneTraduccion (Traduccion t) {
		Traduccion nueva = new Traduccion();
		nueva.setIdioma(t.getIdioma());
		nueva.setTexto(t.getTexto());
		return nueva;
	}
	
	
	
	/**
	 * Retorna TRUE si ambas pistas son identicas, coinciden en sus dimensiones y valores y si afirma/niega
	 */
	public static boolean equalsPista (Pista p1, Pista p2) {
		return (p1.isAfirmaNiega() == p2.isAfirmaNiega()) && 
				(p1.getDimension1() == p2.getDimension1()) && (p1.getDimension2() == p2.getDimension2()) &&
				(p1.getValor1() == p2.getValor1()) && (p1.getValor2() == p2.getValor2());
	}
	
	

	/**
	 * Retorna el texto para el idioma pasado como parámetro en el literal (juego.xsd)
	 * Si no lo encuentra tira excepción
	 */
	public static String getTextoXIdioma (String idioma, Literal lit) throws PersistenciaException {
		for (Traduccion trad : lit.getTraduccion())
			if (idioma.equalsIgnoreCase(trad.getIdioma()))
				return trad.getTexto();
		
		throw new PersistenciaException("No se ha definido el texto para el idioma " + idioma + " en el literal " + lit);
	}
	
	
	
	
	/**
	 * Retorna un literal (juego.xsd) con una única traducción del juego en el idioma pasado como parámetro
	 */
	public static Literal getLiteral (String idioma, String texto) {		
		Traduccion trad = new Traduccion();
		trad.setIdioma(idioma);
		trad.setTexto(texto);
		Literal lit = new Literal();
		lit.getTraduccion().add(trad);
		return lit;
	}
	
	
	
	/**
	 * Retorna el primer idioma que encuentra en la primer traducción del literal del titulo del juego
	 */
	public static String getIdiomaXDefecto (Juego j) {
		return j.getTitulo().getTraduccion().get(0).getIdioma();
	}
	
	
	
	/**
	 * Asume que el juego es válido y toma del literal del titulo todas las traducciones 
	 * y de ellas saca todos los posibles idiomas a retornar
	 */
	public static String [] getIdiomas (Juego j) {
		Collection<String> idiomas = new ArrayList<String>();
		
		for (Traduccion t : j.getTitulo().getTraduccion())
			idiomas.add(t.getIdioma());
		
		return UtilString.col2ArrayOfString(idiomas);
	}
	
	
	
	/**
	 * Toma del literal del titulo todas las traducciones y de ellas saca todos los posibles idiomas a retornar
	 */
	public static Collection<String> getIdiomas (Literal l) {
		Collection<String> idiomas = new ArrayList<String>();
		
		for (Traduccion t : l.getTraduccion())
			idiomas.add(t.getIdioma());
		
		return idiomas;
	}
	
	
	
	/**
	 * Retorna el valor de la dimension dado su numero
	 * Asume un número válido del valor, sino retorna nulo
	 */
	public static Valor getValorXNro (Dimension d, short nro) {
		for (Valor v : d.getValores().getValor()) {
			if (v.getNro() == nro)
				return v;
		}
		
		return null;
	}
}
