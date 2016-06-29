package uy.com.uma.logicgame.api.persistencia;

import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;

/**
 * Metodos a implementar por el manejador de la persistencia de juegos
 *
 * @author Santiago Dalchiele
 */
public interface IManejadorJuego {

	/** Retorna TRUE si existe un juego con este identificador */
	boolean existe (int id) throws PersistenciaException;
	
	/** Retorna un juego dado su identificador (con el idioma por defecto del Locale) */
	Juego obtener (int id) throws PersistenciaException;
	
	/** Retorna un juego dado su identificador */
	Juego obtener (int id, String idioma) throws PersistenciaException;
	
	/** Persiste (inserta) un juego en la base de datos con el idioma por defecto del Locale */
	void persistir (Juego juego) throws PersistenciaException;
	
	/** Persiste (inserta) un juego en la base de datos */
	void persistir (Juego juego, String idioma) throws PersistenciaException;
	
	/** Borra de la base de datos el juego dado su identificador */
	void borrar (int id) throws PersistenciaException;
	
	/** Actualiza los atributos redundantes de un juego en la base de datos */
	void actualizarRedundancias (int id) throws PersistenciaException;
}
