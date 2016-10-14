package uy.com.uma.logicgame.api.validacion;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Encapsula metodos para la validación de parametros
 *
 * @author Santiago Dalchiele
 */
public abstract class UtilValidacionParametros implements IConstantesValidacionParametros {

	/** Mapeo entre el texto del patron y el patron en si compilado, usado como cache en memoria */
	private static Map<String, Pattern> patternCache = new HashMap<String, Pattern>(5);
	
	
	/**
	 * Carga los patrones por defecto utilizados
	 */
	static {
		patternCache.put(PATRON_CLAVES, Pattern.compile(PATRON_CLAVES));
		patternCache.put(PATRON_EMAILS, Pattern.compile(PATRON_EMAILS));
		patternCache.put(PATRON_IDIOMAS, Pattern.compile(PATRON_IDIOMAS));
		patternCache.put(PATRON_IDS, Pattern.compile(PATRON_IDS));
		patternCache.put(PATRON_IDS_MATRIZ_JUEGO, Pattern.compile(PATRON_IDS_MATRIZ_JUEGO));
	}
	
	
	
	
	/**
	 * Retorna TRUE si el dato "matchea" contra la expresión regular (patron) pasado como parámetro
	 */
	public static boolean esValido (String dato, String patron) {
		if ((dato == null) || (patron == null))
			return false;
		else {
			Pattern pat = null;
			
			if (patternCache.containsKey(patron)) {
				pat = patternCache.get(patron);
			} else {
				pat = Pattern.compile(patron);
				patternCache.put(patron, pat);
			}			
			
			Matcher mat = pat.matcher(dato);	    
			return mat.matches();
		}
	}
	
	
	
	/**
	 * Retorna TRUE si es válido el identificador de usuario
	 */
	public static boolean esValidoIdUsuario (String idUsuario) {
		return esValido (idUsuario, PATRON_IDS);
	}

	
	
	/**
	 * Retorna TRUE si es válido el identificador de idiomas
	 */
	public static boolean esValidoIdIdioma (String idIdioma) {
		return esValido(idIdioma, PATRON_IDIOMAS);
	}
	
	
	
	/**
	 * Retorna TRUE si es válida la clave
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
	 * Tira excepción si el identificador de usuario no tiene un valor válido
	 */
	public static void validarIdUsuario (String idUsuario) throws ValidacionParametrosException {
		if (!esValidoIdUsuario(idUsuario))
			throw new ValidacionParametrosException("Identificador de usuario no valido");
	}
	
	
	
	/**
	 * Tira excepción si el identificador del idioma no tiene un valor válido
	 */
	public static void validarIdIdioma (String idIdioma) throws ValidacionParametrosException {
		if (!esValidoIdIdioma(idIdioma))
			throw new ValidacionParametrosException("Identificador de idioma no valido");
	}
	
	
	
	/**
	 * Tira excepción si la clave no tiene un valor válido
	 */
	public static void validarClave (String clave) throws ValidacionParametrosException {
		if (!esValidaClave(clave))
			throw new ValidacionParametrosException("Clave no valida");
	}
	
	
	
	/**
	 * Tira excepción si el correo no tiene un valor válido
	 */
	public static void validarCorreo (String correo) throws ValidacionParametrosException {
		if (!esValidoCorreo(correo))
			throw new ValidacionParametrosException("Correo no valido");
	}
}
