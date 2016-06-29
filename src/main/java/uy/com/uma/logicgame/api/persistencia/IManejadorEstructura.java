package uy.com.uma.logicgame.api.persistencia;

/**
 * Manejador utilizado para administrar la base de datos.
 * Motivado por el uso de heroku como servidor de deployment.
 *
 * @author Santiago Dalchiele
 */
public interface IManejadorEstructura {

	
	/** Crea los roles usados por el sistema logic-game */
	void crearRoles() throws PersistenciaException;
	
	/** Crea la estructura de las tablas y sus indices */
	void crearTablas() throws PersistenciaException;
	
	/** Asigna los permisos para cada tabla y rol */
	void asignarPermisos() throws PersistenciaException;
	
	/** Crea la ruta por defecto a asignarle a los usuarios cuando recien son creados */
	void crearRutaXDefecto() throws PersistenciaException;
}
