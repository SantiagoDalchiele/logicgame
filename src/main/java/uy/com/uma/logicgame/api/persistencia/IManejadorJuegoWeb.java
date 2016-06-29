package uy.com.uma.logicgame.api.persistencia;

import uy.com.uma.logicgame.api.bean.ParametrosJuego;

/**
 * Fachada de servicios de persistencia para la interface del juego web
 *
 * @author Santiago Dalchiele
 */
public interface IManejadorJuegoWeb {

	/** Retorna la definición del juego dado su identificador y un idioma */
	String getDefinicion (int id, String idioma) throws PersistenciaException;
	
	/** Retorna los parametros del juego a los efectos de construir la matriz de juego */
	ParametrosJuego getParametros (int id) throws PersistenciaException;
}
