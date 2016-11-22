package uy.com.uma.logicgame.api.persistencia;

import uy.com.uma.logicgame.api.bean.LogAccionDO;

/**
 * Metodos a implementar para el manejo del log de acciones en el sistema
 *
 * @author Santiago Dalchiele
 */
public interface IManejadorLogAcciones {
	
	/** Definición de los posibles tipos de acciones */
	public final static short LOGIN			= 1;
	public final static short REGISTRO		= 2;
	public final static short ENVIO_TOKEN	= 3;
	public final static short FIN_JUEGO		= 4;
	
	

	/** Persiste una acción determinada en la base de datos */
	void persistirAccion (LogAccionDO acc) throws PersistenciaException;
	
	/**
	 * Retorna la cantidad de acciones fallidas desde la última que se tuvo éxito del tipo de acción pasada como parámetro.
	 * 
	 * @param tipoAccion tipo de acción: Login, Registro, FinJuego, EnvioToken, etc
	 * @param ip indica si contabilizar solo las ips dado el parámetro, null todos las ips
	 * @param idUsuario indica si contabilizar solo las del usuario pasado como parámetro, null todos los usuarios
	 * @param xDia indica si contabilizar solo las del día de hoy
	 * @return cantidad de acciones
	 */
	int getCantAccionesUltExito (short tipoAccion, String ip, String idUsuario, boolean xDia) throws PersistenciaException;
	
	/**
	 * Retorna la cantidad de acciones del tipo de acción pasada como parámetro.
	 * 
	 * @param tipoAccion tipo de acción: Login, Registro, FinJuego, EnvioToken, etc
	 * @param ip indica si contabilizar solo las ips dado el parámetro, null todos las ips
	 * @param xDia indica si contabilizar solo las del día de hoy
	 * @return cantidad de acciones
	 */
	int getCantAcciones (short tipoAccion, String ip, boolean xDia) throws PersistenciaException;
}
