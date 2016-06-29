package uy.com.uma.logicgame.persistencia.seguridad;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sun.misc.BASE64Encoder;
import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.api.conf.ConfiguracionLoadHelper;

/**
 * Clase de utileria para el manejo de la seguridad
 *
 * @author Santiago Dalchiele
 */
@SuppressWarnings("restriction")
public abstract class UtilSeguridad {
	
	private static final Logger log = LogManager.getLogger(UtilSeguridad.class.getName());

	
	/** Metodo de hash utilizado para encriptar */
	public static final String HASH_METHOD = "SHA-384";
	
	/** Constante para ponerle "sal" a la combinación de usuario y clave para encriptarla */
	private static String SALT = "security_salt";
	
	
	
	/**
	 * Levanta de la configuracion la security SALT
	 */
	static {
		try {
			SALT = ConfiguracionLoadHelper.getInstancia().getSecuritySalt();
		} catch (ConfiguracionException e) {
			log.error("No pude setear el SALT de seguridad a partir de la configuracion", e);
		}
	}
	
	
	
	
	/**
	 * En caso de exito retorna la clave del usuario encriptada
	 */
	public static String getClaveEncriptada (final String idUsuario, final String clave) {
		return getClaveEncriptada(idUsuario, clave, SALT);
	}
	
	
	
	/**
	 * En caso de exito retorna la clave del usuario encriptada
	 */
	public static String getClaveEncriptada (final String idUsuario, final String clave, final String securitySalt) {
		if ((idUsuario != null) && (clave != null)) {
			try {
				final String text = clave + securitySalt + idUsuario; 
				MessageDigest digest = MessageDigest.getInstance(HASH_METHOD);
				byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
				return new BASE64Encoder().encode(hash);
			} catch (NoSuchAlgorithmException e) {
				log.fatal("No se puede encriptar las clave porque no se soporta el algoritmo SHA-256");				
			}			
		}
		
		return null;
	}
}
