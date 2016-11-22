package uy.com.uma.logicgame.persistencia.seguridad;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uy.com.uma.comun.util.UtilDate;
import uy.com.uma.comun.util.UtilFormato;
import uy.com.uma.logicgame.api.bean.LogAccionDO;
import uy.com.uma.logicgame.api.persistencia.IManejadorJuegoWeb;
import uy.com.uma.logicgame.api.persistencia.IManejadorLogAcciones;
import uy.com.uma.logicgame.api.persistencia.IManejadorSeguridad;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;
import uy.com.uma.logicgame.persistencia.UtilHibernate;

/**
 * Manejador del log de acciones en el sistema
 * 
 * @author Santiago Dalchiele
 */
public class ManejadorLogAcciones implements IManejadorLogAcciones {
	
	/** session factory */
	private SessionFactory sessions = SessionFactoryUtil.getSessionFactory();


	/** 
	 * Persiste una acción determinada en la base de datos 
	 */
	@Override
	public void persistirAccion (LogAccionDO acc) throws PersistenciaException {
		Session session = sessions.openSession();
		Transaction tx = null;	
		
		try {
			tx = session.beginTransaction();
			LogAccion a = new LogAccion(acc);
			session.save(a);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			UtilHibernate.rollback(tx);
			throw new PersistenciaException("Error al persistir la accion", e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}


	
	/**
	 * Retorna la cantidad de acciones fallidas desde la última que se tuvo éxito del tipo de acción pasada como parámetro.
	 * 
	 * @param tipoAccion tipo de acción: Login, Registro, FinJuego, EnvioToken, etc
	 * @param ip indica si contabilizar solo las ips dado el parámetro, null todos las ips
	 * @param idUsuario indica si contabilizar solo las del usuario pasado como parámetro, null todos los usuarios
	 * @param xDia indica si contabilizar solo las del día de hoy
	 * @return cantidad de acciones
	 */	
	@Override
	public int getCantAccionesUltExito (short tipoAccion, String ip, String idUsuario, boolean xDia) throws PersistenciaException {
		Session session = sessions.openSession();
		
		try {
			short result = ((tipoAccion == LOGIN) || (tipoAccion == ENVIO_TOKEN)) ? IManejadorSeguridad.LOGIN_EXITOSO :
							(tipoAccion == REGISTRO) ? IManejadorSeguridad.REGISTRO_OK : IManejadorJuegoWeb.JUEGO_EXITOSO;							
			String queryString = "FROM " + LogAccion.class.getName() + " WHERE tipoAccion = :tipo AND resultado = :result" +																
																((UtilFormato.esNulo(ip) ? "" : " AND ip = :ipCli")) +
																((UtilFormato.esNulo(idUsuario) ? "" : " AND usuario = :idUsuario")) + 
								 " ORDER BY fchRegistro DESC";			
			Query sel = session.createQuery(queryString);
			sel.setShort("tipo", tipoAccion);
			sel.setShort("result", result);
			
			if (!UtilFormato.esNulo(ip))
				sel.setString("ipCli", ip);
			
			if (!UtilFormato.esNulo(idUsuario))
				sel.setString("idUsuario", idUsuario);
			
			@SuppressWarnings("unchecked")
			List<LogAccion> queryResult = sel.list();
			Date ultExito = UtilFormato.FECHA_NULA.getTime();
			
			if (!queryResult.isEmpty())
				ultExito = queryResult.iterator().next().getFchRegistro();
			
			queryString = "FROM " + LogAccion.class.getName() + " WHERE tipoAccion = :tipo AND fchRegistro > :fecha" +
															(xDia ? " AND fchRegistro > :ayer" : "") +
															((UtilFormato.esNulo(ip) ? "" : " AND ip = :ipCli")) +
															((UtilFormato.esNulo(idUsuario) ? "" : " AND usuario = :idUsuario"));
			sel = session.createQuery(queryString);
			sel.setShort("tipo", tipoAccion);
			sel.setTimestamp("fecha", ultExito);
			
			if (xDia)
				sel.setTimestamp("ayer", UtilDate.ultimaHoraAyer());
			
			if (!UtilFormato.esNulo(ip))
				sel.setString("ipCli", ip);
			
			if (!UtilFormato.esNulo(idUsuario))
				sel.setString("idUsuario", idUsuario);			
			
			return sel.list().size();
		} catch (Exception e) {
			throw new PersistenciaException("Error al obtener cantidad de acciones", e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}


	
	/**
	 * Retorna la cantidad de acciones del tipo de acción pasada como parámetro.
	 * 
	 * @param tipoAccion tipo de acción: Login, Registro, FinJuego, EnvioToken, etc
	 * @param ip indica si contabilizar solo las ips dado el parámetro, null todos las ips
	 * @param xDia indica si contabilizar solo las del día de hoy
	 * @return cantidad de acciones
	 */
	@Override
	public int getCantAcciones (short tipoAccion, String ip, boolean xDia) throws PersistenciaException {
		Session session = sessions.openSession();
		
		try {
			String queryString = "FROM " + LogAccion.class.getName() + " WHERE tipoAccion = :tipo" + 
																(xDia ? " AND fchRegistro > :fecha" : "") +
																((UtilFormato.esNulo(ip) ? "" : " AND ip = :ipCli"));			
			Query sel = session.createQuery(queryString);
			sel.setShort("tipo", tipoAccion);
			
			if (xDia)
				sel.setTimestamp("fecha", UtilDate.ultimaHoraAyer());
			
			if (!UtilFormato.esNulo(ip))
				sel.setString("ipCli", ip);
			
			return sel.list().size();
		} catch (Exception e) {
			throw new PersistenciaException("Error al obtener cantidad de acciones", e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
}
