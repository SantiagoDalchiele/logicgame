package uy.com.uma.logicgame.persistencia.juego;

import java.util.List;
import java.util.Locale;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import uy.com.uma.logicgame.api.bean.ParametrosJuego;
import uy.com.uma.logicgame.api.persistencia.IManejadorJuegoWeb;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.api.validacion.UtilValidacionParametros;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;

/**
 * Fachada de servicios de persistencia para la interface del juego web
 *
 * @author Santiago Dalchiele
 */
public class ManejadorJuegoWeb implements IManejadorJuegoWeb {
	
	private static final Logger log = LogManager.getLogger(ManejadorJuegoWeb.class.getName());
	
	/** Lenguaje por defecto */
	private final static String DEFAULT_LANG = Locale.getDefault().getLanguage();
	
	/** session factory */
	private SessionFactory sessions = SessionFactoryUtil.getSessionFactory();
	
	
	

	/** 
	 * Retorna la definición del juego dado su identificador y un idioma
	 * Intenta primero con el idioma pasado como parámetro, de lo contrario toma el idioma por defecto, 
	 * sino retorna la primer definición del juego encontrada para cualquier idioma 
	 */
	@Override
	public String getDefinicion(int id, String idioma) throws PersistenciaException {
		UtilValidacionParametros.validarIdIdioma(idioma);
		Session session = sessions.openSession();
		
		try {
			JuegoXIdioma ji = (JuegoXIdioma) session.get(JuegoXIdioma.class, new JuegoXIdiomaPK(id, idioma));
			
			if (ji == null) {
				log.warn("No se ha definido el juego " + id + " para el idioma " + idioma);
				
				if (!DEFAULT_LANG.equals(idioma))
					ji = (JuegoXIdioma) session.get(JuegoXIdioma.class, new JuegoXIdiomaPK(id, DEFAULT_LANG));
				
					if (ji == null) {
						log.warn("No se ha definido el juego " + id + " para el idioma " + DEFAULT_LANG);
						Query sel = session.createQuery("FROM JuegoXIdioma WHERE id.idJuego = :idJ");
						sel.setInteger("idJ", id);
						
						 @SuppressWarnings({"unchecked" })
						List<JuegoXIdioma> result = sel.list();
						
						if (result.isEmpty())
							throw new PersistenciaException("No se ha definido el juego " + id);
						else
							return result.get(0).getDefJuego();
					} else
						return ji.getDefJuego();
			} else
				return ji.getDefJuego();
		} catch (PersistenceException e) {
			throw new PersistenciaException("Error al obtener la definicion del juego " + id, e);
		}
	}
	
	
	
	/** 
	 * Retorna los parametros del juego a los efectos de construir la matriz de juego 
	 */
	public ParametrosJuego getParametros (int id) throws PersistenciaException {
		Session session = sessions.openSession();
		
		try {
			Juego j = (Juego) session.get(Juego.class, id);
			
			if (j == null)
				throw new PersistenceException("No se ha definido el juego " + id);
			else
				return new ParametrosJuego(j.getCantDims(), j.getCantValores(), j.getSolucion());			
		} catch (PersistenceException e) {
			throw new PersistenciaException("Error al obtener los parametros del juego " + id, e);
		}
	}
}
