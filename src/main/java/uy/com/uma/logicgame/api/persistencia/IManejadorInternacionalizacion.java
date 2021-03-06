package uy.com.uma.logicgame.api.persistencia;

import java.util.Collection;

/**
 * Define metodos a implementar por la lógica de persistencia de la internacionalización de literales
 *
 * @author Santiago Dalchiele
 */
public interface IManejadorInternacionalizacion {

	/**
	 * Si existe un registro para este texto e idioma retorna el identificador, de lo contrario crea un registro y 
	 * retorna el id del registro creado 
	 * @param idioma identificador del idioma (2 letras)
	 * @param texto texto a internacionalizar
	 */
	long internacionalizar (String idioma, String texto) throws PersistenciaException;	
	
	/**
	 * Crea un registro para el idioma, identificador y texto en la internacionalización (tabla literales, clase Literal)
	 */
	void internacionalizar (long id, String idioma, String texto) throws PersistenciaException;
	

	/** Dados el idioma y el identificador retorna el texto */
	String getTexto (String idioma, long id) throws PersistenciaException;
	
	/** Retorna los identificadores de todos los idiomas registrados en el sistema */
	Collection<String> getIdsIdiomas() throws PersistenciaException;
	
	/** Retorna los idiomas para los cuales esta definido el literal cuyo identificador se pasa como parametro */
	String [] getIdiomasXId (long id) throws PersistenciaException;
}
