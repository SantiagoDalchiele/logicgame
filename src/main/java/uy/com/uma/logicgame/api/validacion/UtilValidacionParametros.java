package uy.com.uma.logicgame.api.validacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Encapsula metodos para la validaci�n de parametros
 *
 * @author Santiago Dalchiele
 */
public abstract class UtilValidacionParametros implements IConstantesValidacionParametros {

	
	
	/**
	 * Retorna TRUE si el dato "matchea" contra la expresi�n regular (patron) pasado como par�metro
	 * TODO tener una estructura de Mapeo para cachear los Pattern's ?
	 */
	public static boolean esValido (String dato, String patron) {
		if ((dato == null) || (patron == null))
			return false;
		else {
			Pattern pat = Pattern.compile(patron);
			Matcher mat = pat.matcher(dato);	    
			return mat.matches();
		}
	}
	
	
	
	/**
	 * Retorna TRUE si es v�lido el identificador de usuario
	 */
	public static boolean esValidoIdUsuario (String idUsuario) {
		return esValido (idUsuario, PATRON_IDS);
	}

	
	
	/**
	 * Retorna TRUE si es v�lido el identificador de idiomas
	 */
	public static boolean esValidoIdIdioma (String idIdioma) {
		return esValido(idIdioma, PATRON_IDIOMAS);
	}
	
	
	
	/**
	 * Retorna TRUE si es v�lida la clave
	 */
	public static boolean esValidaClave (String clave) {
		return esValido(clave, PATRON_CLAVES);
	}
	
	
	
	/**
	 * Retorna TRUE si es valido el correo
	 */
	public static boolean esValidoCorreo (String correo) {
		return esValido(correo, PATRON_EMAILS) && (correo.length() <= LARGO_MAX_EMAILS);
	}
	
	
	
	/**
	 * Retorna TRUE si es valido el id de la matriz de juego (ej: 1.1.2.4)
	 */
	public static boolean esValidoIdMatrizJuego (String id) {
		return esValido(id, PATRON_IDS_MATRIZ_JUEGO);
	}
	
	
	
	/**
	 * Tira excepci�n si el identificador de usuario no tiene un valor v�lido
	 */
	public static void validarIdUsuario (String idUsuario) throws ValidacionParametrosException {
		if (!esValidoIdUsuario(idUsuario))
			throw new ValidacionParametrosException("Identificador de usuario no valido");
	}
	
	
	
	/**
	 * Tira excepci�n si el identificador de usuario no tiene un valor v�lido
	 */
	public static void validarIdIdioma (String idIdioma) throws ValidacionParametrosException {
		if (!esValidoIdIdioma(idIdioma))
			throw new ValidacionParametrosException("Identificador de idioma no valido");
	}
	
	
	
	/**
	 * Tira excepci�n si la clave no tiene un valor v�lido
	 */
	public static void validarClave (String clave) throws ValidacionParametrosException {
		if (!esValidaClave(clave))
			throw new ValidacionParametrosException("Clave no valida");
	}
	
	
	
	/**
	 * Tira excepci�n si el correo no tiene un valor v�lido
	 */
	public static void validarCorreo (String correo) throws ValidacionParametrosException {
		if (!esValidoCorreo(correo))
			throw new ValidacionParametrosException("Correo no valido");
	}
}
