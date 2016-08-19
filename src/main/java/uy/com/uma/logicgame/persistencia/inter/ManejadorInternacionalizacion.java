package uy.com.uma.logicgame.persistencia.inter;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uy.com.uma.comun.util.UtilString;
import uy.com.uma.logicgame.api.persistencia.IManejadorInternacionalizacion;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;
import uy.com.uma.logicgame.persistencia.UtilHibernate;


/**
 * Implementa la lógica de persistencia de la internacionalización de literales
 *
 * @author Santiago Dalchiele
 */
public class ManejadorInternacionalizacion implements IManejadorInternacionalizacion {

	/** session factory */
	private SessionFactory sessions = SessionFactoryUtil.getSessionFactory();
	
	
	
	/**
	 * Si existe un registro para este texto e idioma retorna el identificador, de lo contrario crea un registro y 
	 * retorna el id del registro creado 
	 * @param idioma identificador del idioma (2 letras)
	 * @param texto texto a internacionalizar
	 */
	@Override
	public long internacionalizar(String idioma, String texto) throws PersistenceException {
		texto = UtilString.reemplazarLetrasEspeciales(texto);
		long id = findByTexto(idioma, texto);
		
		if (id == -1)
			id = insertLiteral(idioma, texto);
		
		return id;
	}

	
	/**
	 * Crea un registro para el idioma, identificador y texto en la internacionalización (tabla literales, clase Literal)
	 */
	@Override
	public void internacionalizar (long id, String idiomaId, String texto) throws PersistenciaException {
		Session session = sessions.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Idioma idioma = (Idioma) session.get(Idioma.class, idiomaId);			
			LiteralPK pk = new LiteralPK();
			pk.setId(id);
			pk.setIdioma(idioma);
			
			Literal lit = (Literal) session.get(Literal.class, pk);
			
			if (lit == null) {
				lit = new Literal();
				lit.setId(pk);
			}			
			
			lit.setTexto(texto);
			session.save(lit);
			tx.commit();
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
	
	
	
	/** 
	 * Dados el idioma y el identificador retorna el texto 
	 */
	@Override
	public String getTexto(String idiomaId, long id) throws PersistenceException {
		Session session = sessions.openSession();
		
		try {
			Idioma idioma = (Idioma) session.get(Idioma.class, idiomaId);
			
			if (idioma == null)
				throw new PersistenceException("No existe un idioma con el identificador [" + idiomaId + "]");
			else {
				Literal lit = (Literal) session.get(Literal.class, new LiteralPK(new Long(id), idioma));
				
				if (lit == null)
					throw new PersistenceException("No existe un literal con el identificador [" + id + "] para el idioma [" + idiomaId + "]");
				else
					return UtilString.quitarLetrasEspeciales(lit.getTexto());
			}
		} finally {
			UtilHibernate.closeSession(session);
		}
	}

	

	/** 
	 * Retorna los identificadores de todos los idiomas registrados en el sistema 
	 */
	public Collection<String> getIdsIdiomas() throws PersistenceException {
		Collection<String> ids = new ArrayList<String>();		
		Session session = sessions.openSession();
		
		try {
			Query query = session.createQuery("FROM " + Idioma.class.getName());
			
			for (Object o : query.list())
				ids.add(((Idioma) o).getId());
		} finally {
			UtilHibernate.closeSession(session);		
		}
		
		return ids;
	}
	
	
	
	/** 
	 * Retorna los idiomas para los cuales esta definido el literal cuyo identificador se pasa como parametro 
	 */
	@Override
	public String [] getIdiomasXId (long id) throws PersistenciaException {				
		Session session = sessions.openSession();
		
		try {
			Collection<String> idiomas = new ArrayList<String>();
			Query query = session.createQuery("FROM " + Literal.class.getName() + " WHERE id.id = :idL");
			query.setLong("idL", id);
			
			for (Object o : query.list())
				idiomas.add(((Literal) o).getId().getIdioma().getId());
			
			return UtilString.col2ArrayOfString(idiomas);
		} finally {
			UtilHibernate.closeSession(session);		
		}
	}
	
	
	
	/**
	 * Inserta un literal y retorna su id
	 */
	private long insertLiteral (String idiomaId, String texto) throws PersistenceException {
		Session session = sessions.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Literal lit = new Literal();
			long id = 1 + findMaxIdLiteral();
			Idioma idioma = (Idioma) session.get(Idioma.class, idiomaId);
			lit.setId(new LiteralPK(new Long(id), idioma));
			lit.setTexto(texto);
			session.save(lit);
			tx.commit();
			return id;
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenceException("No se pudo insertar el literal [" + texto + "] para el idioma [" + idiomaId + "]",  e);
		} finally {			
			UtilHibernate.closeSession(session);
		}
	}


	
	/**
	 * Retorna el máximo id de literal, si la tabla de literales está vacía retorna 0 
	 */
	private long findMaxIdLiteral() {
		Session session = sessions.openSession();
		
		try {
			Long id = (Long) session.createQuery("SELECT MAX(l.id.id) FROM Literal l").uniqueResult();		
		
			if (id == null)
				return 0;
			else
				return id.longValue();
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
	
	
	
	/**
	 * Retorna el id del literal dado el idioma y el texto
	 */
	private long findByTexto (String idioma, String texto) {		
		Session session = sessions.openSession();
		
		try {
			Query select = session.createQuery("FROM Literal WHERE idioma=? AND texto=?");
			select.setString(0, idioma);
			select.setString(1, texto);
			Literal l = (Literal) select.uniqueResult();		
		
			if (l == null)
				return -1;
			else
				return l.getId().getId().longValue();
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
}
