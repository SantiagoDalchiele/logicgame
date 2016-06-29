package uy.com.uma.logicgame.persistencia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Utilidades para el manejo de Hibernate
 *
 * @author Santiago Dalchiele
 */
public abstract class UtilHibernate {

	private static final Logger log = LogManager.getLogger(UtilHibernate.class.getName());
	
	
	
	/**
	 * Cierra la session sin tirar excepción
	 */
	public static void closeSession (Session session) {
		if (session != null)
			try {
				session.close();
			} catch (Exception e) {
				log.warn("Excepción al cerrar la session", e); //$NON-NLS-1$
			}
	}
	
	
	
	/**
	 * Realiza el rollback sin tirar excepción
	 */
	public static void rollback (Transaction tx) {
		if (tx != null)
			try {
				tx.rollback();
			} catch (Exception e) {
				log.warn("Excepción al realizar el rollback", e); //$NON-NLS-1$
			}
	}
}
