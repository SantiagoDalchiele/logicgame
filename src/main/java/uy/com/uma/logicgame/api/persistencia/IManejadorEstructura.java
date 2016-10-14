package uy.com.uma.logicgame.api.persistencia;

/**
 * Manejador utilizado para administrar la base de datos.
 * Motivado por el uso de heroku como servidor de deployment.
 *
 * @author Santiago Dalchiele
 */
public interface IManejadorEstructura {

	
	/** Crea la estructura de las tablas y sus indices */
	void crearTablas() throws PersistenciaException;
	
	/** Borra la estructura de las tablas creadas */
	void borrarTablas() throws PersistenciaException;
	
	/** Borra todos los registros de todas las tablas del sistema */
	void borrarDatos() throws PersistenciaException;
	
	/** Crea la ruta por defecto a asignarle a los usuarios cuando recien son creados */
	void crearRutaXDefecto() throws PersistenciaException;
	
	/** Crea los atributos token y fch_expira_token en la tabla usuarios */
	void parche01TokenUsuarios() throws PersistenciaException;
}
