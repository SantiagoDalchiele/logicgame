package uy.com.uma.logicgame.api.persistencia;

import java.util.Collection;

import uy.com.uma.logicgame.api.bean.DatosIdioma;
import uy.com.uma.logicgame.api.bean.LiteralBean;

/**
 * Metodos para la administración de la internacionalización
 *
 * @author Santiago Dalchiele
 */
public interface IManejadorAdminInternacionalizacion {

	/** Inserta o actualiza el idioma */
	void setIdioma (String id, String nombre, String icono) throws PersistenciaException;
	
	/** Inserta o actualiza un literal */
	void setLiteral (long id, String idioma, String texto) throws PersistenciaException;
	
	/** Inserta o actualiza varios literales */
	void setLiterales (Collection<LiteralBean> literales) throws PersistenciaException;
	
	/** Retorna la colección de idiomas persistidos en la base de datos */
	Collection<DatosIdioma> getIdiomas() throws PersistenciaException;
}
