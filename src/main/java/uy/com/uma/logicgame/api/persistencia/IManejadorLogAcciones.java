package uy.com.uma.logicgame.api.persistencia;

import uy.com.uma.logicgame.api.bean.LogAccionDO;

/**
 * Metodos a implementar para el manejo del log de acciones en el sistema
 *
 * @author Santiago Dalchiele
 */
public interface IManejadorLogAcciones {
	
	/** Definici�n de los posibles tipos de acciones */
	public final static short LOGIN			= 1;
	public final static short REGISTRO		= 2;
	public final static short ENVIO_TOKEN	= 3;
	public final static short FIN_JUEGO		= 4;
	
	

	/** Persiste una acci�n determinada en la base de datos */
	void persistirAccion (LogAccionDO acc) throws PersistenciaException;
	
	/**
	 * Retorna la cantidad de acciones fallidas desde la �ltima que se tuvo �xito del tipo de acci�n pasada como par�metro.
	 * 
	 * @param tipoAccion tipo de acci�n: Login, Registro, FinJuego, EnvioToken, etc
	 * @param ip indica si contabilizar solo las ips dado el par�metro, null todos las ips
	 * @param idUsuario indica si contabilizar solo las del usuario pasado como par�metro, null todos los usuarios
	 * @param xDia indica si contabilizar solo las del d�a de hoy
	 * @return cantidad de acciones
	 */
	int getCantAccionesUltExito (short tipoAccion, String ip, String idUsuario, boolean xDia) throws PersistenciaException;
	
	/**
	 * Retorna la cantidad de acciones del tipo de acci�n pasada como par�metro.
	 * 
	 * @param tipoAccion tipo de acci�n: Login, Registro, FinJuego, EnvioToken, etc
	 * @param ip indica si contabilizar solo las ips dado el par�metro, null todos las ips
	 * @param xDia indica si contabilizar solo las del d�a de hoy
	 * @return cantidad de acciones
	 */
	int getCantAcciones (short tipoAccion, String ip, boolean xDia) throws PersistenciaException;
}
