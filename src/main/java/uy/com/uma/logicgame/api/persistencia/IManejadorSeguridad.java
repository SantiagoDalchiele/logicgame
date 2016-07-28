package uy.com.uma.logicgame.api.persistencia;

import java.util.Collection;

import uy.com.uma.logicgame.api.bean.RutaDO;
import uy.com.uma.logicgame.api.bean.UsuarioDO;

/**
 * Metodos a implementar por el manejador de la seguridad
 *
 * @author Santiago Dalchiele
 */
public interface IManejadorSeguridad {
	
	/** Constantes que enumeran el resultado de la función de login */
	public final static short LOGIN_EXITOSO = 1;
	public final static short LOGIN_USUARIO_INEXISTENTE = 2;
	public final static short LOGIN_CLAVE_INCORRECTA = 3;
	
	/** Constantes que enumeran el resultado de la función de registro de usuarios */
	public final static short REGISTRO_OK = 1;
	public final static short REGISTRO_USUARIO_EXISTENTE = 4;
	public final static short REGISTRO_CORREO_EXISTENTE = 5;
	
	

	/** Retorna TRUE si el usuario está logeado en el sistema */
	boolean estaLogeado (String idUsuario) throws PersistenciaException;
	
	/** Realiza el login en el sistema */
	short login (String idUsuario, String clave) throws PersistenciaException;
	
	/** Se puede autenticar con id de usuario o correo, retorna en valor en minúsculas del id de usuario */
	String getIdUsuario (String idUsuario) throws PersistenciaException;
	
	/** Realiza el logout del sistema */
	void logout (String idUsuario) throws PersistenciaException;
	
	/** Registra un usuario en el sistema */
	short registro (String idioma, String idUsuario, String correo, String clave) throws PersistenciaException;
	
	/** Retorna datos del usuario */
	UsuarioDO getDatosUsuario (String idUsuario) throws PersistenciaException;
	
	/** Retorna el idioma del usuario */
	String getIdioma (String idUsuario) throws PersistenciaException;
	
	/** Setea el idioma del usuario */
	void setIdioma (String idUsuario, String idioma) throws PersistenciaException;
	
	/** Incrementa el nivel del usuario */
	void incNivel (String idUsuario) throws PersistenciaException;
	
	/** Retorna el ranking de los primeros N usuarios (incluidos el pasado como parámetro) para la ruta del usuario */
	Collection<UsuarioDO> getRanking (String idUsuario, int cant) throws PersistenciaException;
	
	/** Setea el estado del juego actual del usuario */
	void setEstado (String idUsuario, String estado) throws PersistenciaException;	
	
	/** Retorna la hoja de estilos de la ruta + nivel actual del usuario, si es que tiene */
	String getRutaHojaEstilo (String idUsuario) throws PersistenciaException;
	
	/** Retorna todos los usuarios del sistema ordenados por id */
	Collection<UsuarioDO> getUsuarios() throws PersistenciaException;
	
	/** Retorna las rutas persistidas en la base de datos (cada ruta con su nivel y su id de juego) */
	Collection<RutaDO> getRutas() throws PersistenciaException;	
}
