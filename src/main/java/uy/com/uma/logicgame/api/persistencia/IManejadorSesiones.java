package uy.com.uma.logicgame.api.persistencia;

/**
 * Define los metodos del manejador de sesiones en la base de datos
 *
 * @author Santiago Dalchiele
 */
public interface IManejadorSesiones {

	/** Resetea la configuracion */
	void reset ();
	
	/** Retorna TRUE si se inicializó */
	boolean isInitialized();
	
	/** Cierra la session */
	void shutdown();
}
