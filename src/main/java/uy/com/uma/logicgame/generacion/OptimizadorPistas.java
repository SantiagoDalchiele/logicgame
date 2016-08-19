package uy.com.uma.logicgame.generacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas.Pista;
import uy.com.uma.logicgame.nucleo.jaxb.juego.UtilJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;
import uy.com.uma.logicgame.resolucion.Resolucion;

/**
 * Setea el conjunto mínimo de pistas con el cuál el juego es resoluble y tiene una única solución
 *
 * @author Santiago Dalchiele
 */
public class OptimizadorPistas {
	
	private Collection<Pista> pistasBorradas = new ArrayList<Pista>();
	
	

	/**
	 * Setea el conjunto mínimo de pistas con el cuál el juego es resoluble y tiene una única solución
	 */
	public Collection<Pista> optimizar (Juego original) throws ValidadorJuegoException, ConfiguracionException {
		Resolucion r = Resolucion.resolver(original);
		
		if (r.tieneSolucion() && r.solucionUnica() && (r.getCosto() > 0)) {
			pistasBorradas.clear();
			Juego juego = UtilJuego.cloneJuego(original);
			Collection<Pista> afirmaciones = getAfirmaNiega(juego, true);
			Collection<Pista> negaciones = getAfirmaNiega(juego, false);
			removerPistas(juego, negaciones);
			removerPistas(juego, afirmaciones);
			
			for (Pista p : pistasBorradas)
				removerPista(original, p);
		}
		
		return pistasBorradas;
	}

		
	
	/**
	 * Retorna una colección de pistas que son afirmaciones/negaciones
	 */
	private static Collection<Pista> getAfirmaNiega (Juego juego, boolean afirma) {
		Collection<Pista> ret = new ArrayList<Pista>();
		
		for (PistaDelJuego pj : juego.getPistasDelJuego().getPistaDelJuego())
			for (Pista p : pj.getPistas().getPista())
				if (afirma == p.isAfirmaNiega())					
					ret.add(UtilJuego.clonePista(p));
						
		return ret;
	}
	
	
	
	/**
	 * Intenta eliminar una a una las pistas siempre que el juego se mantenga "resoluble"
	 */
	private void removerPistas (Juego juego, Collection<Pista> pistas) throws ValidadorJuegoException, ConfiguracionException {
		Resolucion r = null;
		
		for (Pista p : pistas) {
			removerPista(juego, p);
			r = Resolucion.resolver(juego);
			
			if (r.tieneSolucion() && r.solucionUnica() && (r.getCosto() > 0))
				pistasBorradas.add(p);
			else
				agregarPista(juego, p);
		}
	}
	

	
	/**
	 * Remueve una pista en particular del juego
	 */
	private static void removerPista (Juego juego, Pista pista) {
		PistaDelJuego borrarDelJuego = null;
		
		for (PistaDelJuego pj : juego.getPistasDelJuego().getPistaDelJuego()) {
			Pista aBorrar = null;
		
			for (Pista p : pj.getPistas().getPista())
				if (UtilJuego.equalsPista(p, pista))
					aBorrar = p;
				
			if (aBorrar != null) {
				pj.getPistas().getPista().remove(aBorrar);
			
				if (pj.getPistas().getPista().isEmpty())
					borrarDelJuego = pj;
			}
		}
		
		if (borrarDelJuego != null)
			juego.getPistasDelJuego().getPistaDelJuego().remove(borrarDelJuego);
	}
	
	
	
	/**
	 * Agrega una pista con un texto por defecto
	 */
	private static void agregarPista (Juego juego, Pista p) {
		PistaDelJuego pj = new PistaDelJuego();
		pj.setTexto(UtilJuego.getLiteral(Locale.getDefault().getLanguage(), GeneradorJuegos.JUEGO_AUTO_GEN));
		pj.setPistas(new Pistas());
		pj.getPistas().getPista().add(p);
		juego.getPistasDelJuego().getPistaDelJuego().add(pj);
	}
}
