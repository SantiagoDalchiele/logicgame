package uy.com.uma.logicgame.persistencia.inter;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uy.com.uma.comun.util.UtilString;
import uy.com.uma.logicgame.api.bean.IdiomaDO;
import uy.com.uma.logicgame.api.bean.LiteralDO;
import uy.com.uma.logicgame.api.persistencia.IManejadorAdminInternacionalizacion;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;
import uy.com.uma.logicgame.persistencia.UtilHibernate;

/**
 * Implementa la lógica de administración de la internacionalización
 *
 * @author Santiago Dalchiele
 */
public class ManejadorAdminInternacionalizacion implements IManejadorAdminInternacionalizacion {

	private static final Logger log = LogManager.getLogger(ManejadorAdminInternacionalizacion.class.getName());
	
	/** session factory */
	private SessionFactory sessions = SessionFactoryUtil.getSessionFactory();

	
	
	/** 
	 * Inserta o actualiza el idioma 
	 */
	@Override
	public void setIdioma(String id, String nombre, String icono) throws PersistenciaException {
		Session session = sessions.openSession();
		Transaction tx = null;
		
		try {
			nombre = UtilString.reemplazarLetrasEspeciales(nombre);
			log.debug("Seteando el idioma id=[" + id + "], nombre=[" + nombre + "], icono=[" + icono + "]");
			tx = session.beginTransaction();
			Idioma idioma = (Idioma) session.get(Idioma.class, id);
			
			if (idioma == null)
				idioma = new Idioma(id, nombre, icono);
			else
				idioma.setNombre(nombre);
			
			session.save(idioma);
			tx.commit();
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenciaException("Error al procesar el idioma " + id,  e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}

	
	
	/** 
	 * Inserta o actualiza un literal 
	 */
	@Override
	public void setLiteral(long id, String idIdioma, String texto) throws PersistenciaException {
		Session session = sessions.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Idioma idioma = (Idioma) session.get(Idioma.class, idIdioma);
			LiteralPK pk = new LiteralPK(id, idioma);
			Literal lit = (Literal) session.get(Literal.class, pk);
			
			if (lit == null)
				lit = new Literal(pk, texto);
			else
				lit.setTexto(texto);
			
			session.save(lit);
			tx.commit();
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenciaException("Error al procesar el literal " + id,  e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}

	
	
	/** 
	 * Inserta o actualiza varios literales 
	 */
	@Override
	public void setLiterales (Collection<LiteralDO> literales) throws PersistenciaException {
		Session session = sessions.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			for (LiteralDO litbean : literales) {
				Idioma idioma = (Idioma) session.get(Idioma.class, litbean.getIdioma());
				LiteralPK pk = new LiteralPK(litbean.getId(), idioma);
				Literal lit = (Literal) session.get(Literal.class, pk);
			
				if (lit == null)
					lit = new Literal(pk, litbean.getTexto());
				else
					lit.setTexto(litbean.getTexto());
			
				session.save(lit);
			}
			
			tx.commit();
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenciaException("Error al procesar los literales",  e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}



	/** 
	 * Retorna la colección de idiomas persistidos en la base de datos 
	 */
	@Override
	public Collection<IdiomaDO> getIdiomas() throws PersistenciaException {
		log.debug("Obteniendo los idiomas del sistema");
		Collection<IdiomaDO> col = new ArrayList<IdiomaDO>();
		Session session = sessions.openSession();
		
		try {		
			Query query = session.createQuery("FROM " + Idioma.class.getName());
			
			for (Object o : query.list()) {			
				IdiomaDO di = Idioma.getDatos((Idioma) o); 
				col.add(di);
				log.debug("Procesando el idioma id=[" + di.getId() + "], nombre=[" + di.getNombre() + "], icono=[" + di.getIcono() + "]");
			}
			
			return col;
		} finally {
			UtilHibernate.closeSession(session);
		}		
	}
}
