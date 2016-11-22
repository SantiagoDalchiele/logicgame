package uy.com.uma.logicgame.api.persistencia;

import uy.com.uma.logicgame.api.bean.ParametrosJuego;

/**
 * Fachada de servicios de persistencia para la interface del juego web
 *
 * @author Santiago Dalchiele
 */
public interface IManejadorJuegoWeb {
	
	/** Constantes que representan el resultado de evaluar el juego */
	public static final short JUEGO_INCOMPLETO = 1;
	public static final short JUEGO_ERRONEO = 2;
	public static final short JUEGO_EXITOSO = 3;
	
	

	/** Retorna la definición del juego dado su identificador y un idioma */
	String getDefinicion (int id, String idioma) throws PersistenciaException;
	
	/** Retorna los parametros del juego a los efectos de construir la matriz de juego */
	ParametrosJuego getParametros (int id) throws PersistenciaException;
}
