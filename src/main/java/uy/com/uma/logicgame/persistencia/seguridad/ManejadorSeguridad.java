package uy.com.uma.logicgame.persistencia.seguridad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uy.com.uma.logicgame.api.bean.DatosUsuario;
import uy.com.uma.logicgame.api.conf.ConfiguracionLoadHelper;
import uy.com.uma.logicgame.api.persistencia.IManejadorSeguridad;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.api.validacion.IConstantesValidacionParametros;
import uy.com.uma.logicgame.api.validacion.UtilValidacionParametros;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;
import uy.com.uma.logicgame.persistencia.UtilHibernate;
import uy.com.uma.logicgame.persistencia.inter.Idioma;

/**
 * Manejador del esquema de seguridad
 *
 * @author Santiago Dalchiele
 */
public class ManejadorSeguridad implements IManejadorSeguridad, IConstantesValidacionParametros {
	
	private static final Logger log = LogManager.getLogger(ManejadorSeguridad.class.getName());
	

	/** session factory */
	private SessionFactory sessions = SessionFactoryUtil.getSessionFactory();
	
	
	
	/** 
	 * Retorna TRUE si el usuario est� logeado en el sistema 
	 */
	public boolean estaLogeado (String idUsuario) throws PersistenciaException {
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		Session session = sessions.openSession();
		
		try {		
			return getUsuario(session, idUsuario).getLogeado();
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
	
	
	
	/** 
	 * Realiza el login en el sistema por id de usuario o su correo
	 */
	public short login (String idUsuario, String clave) throws PersistenciaException {		
		if ((idUsuario == null) || (clave == null))
			throw new PersistenciaException("Identificador de usuario o clave en nulo");
		
		if (!UtilValidacionParametros.esValidoIdUsuario(idUsuario))
			UtilValidacionParametros.validarCorreo(idUsuario);
		
		UtilValidacionParametros.validarClave(clave);
		Session session = sessions.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();			
			idUsuario = idUsuario.toLowerCase();
			Usuario u = null;
			
			if (idUsuario.contains("@")) {
				Query sel = session.createQuery("FROM Usuario WHERE correo = :mail");
				sel.setString("mail", idUsuario);
				@SuppressWarnings("unchecked")
				List<Usuario> l = sel.list();
				
				if (!l.isEmpty())
					u = l.get(0);
			} else
				u = (Usuario) session.get(Usuario.class, idUsuario);
				
			if (u == null)
				return LOGIN_USUARIO_INEXISTENTE;
			else {			
				if (u.getClave().trim().equals(UtilSeguridad.getClaveEncriptada(idUsuario, clave))) {
					u.setLogeado(true);
					tx.commit();
					return LOGIN_EXITOSO;
				} else
					return LOGIN_CLAVE_INCORRECTA;
			}				
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenciaException("Error en el login de usuario", e);
		} finally {
			UtilHibernate.closeSession(session);
		}		
	}
	
	
	
	/** 
	 * Se puede autenticar con id de usuario o correo, retorna en valor en min�sculas del id de usuario 
	 */
	public String getIdUsuario (String idUsuario) throws PersistenciaException {
		if (idUsuario == null)
			throw new PersistenciaException("Identificador de usuario nulo");

		if (!UtilValidacionParametros.esValidoIdUsuario(idUsuario))
			UtilValidacionParametros.validarCorreo(idUsuario);
		
		Session session = sessions.openSession();
		
		try {	
			idUsuario = idUsuario.toLowerCase();
			Usuario u = null;
			
			if (idUsuario.contains("@")) {
				Query sel = session.createQuery("FROM Usuario WHERE correo = :mail");
				sel.setString("mail", idUsuario);
				@SuppressWarnings("unchecked")
				List<Usuario> l = sel.list();
				
				if (!l.isEmpty())
					u = l.get(0);
			} else
				u = (Usuario) session.get(Usuario.class, idUsuario);
				
			if (u == null)
				return idUsuario;
			else
				return u.getId();
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
	
	
	
	/** 
	 * Realiza el logout del sistema 
	 */
	public void logout (String idUsuario) throws PersistenciaException {
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		Session session = sessions.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();			
			idUsuario = idUsuario.toLowerCase();
			Usuario u = (Usuario) session.get(Usuario.class, idUsuario);
				
			if (u != null) {			
				u.setLogeado(false);
				tx.commit();
			}				
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenciaException("Error en el logout del usuario", e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
	
	
	
	/** 
	 * Registra un usuario en el sistema 
	 */
	@SuppressWarnings("unchecked")
	public short registro (String idioma, String idUsuario, String correo, String clave) throws PersistenciaException {
		UtilValidacionParametros.validarIdIdioma(idioma);
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		UtilValidacionParametros.validarCorreo(correo);
		UtilValidacionParametros.validarClave(clave);		
		Session session = sessions.openSession();
		Transaction tx = null;		
		
		try {
			String alias = idUsuario;
			idUsuario = idUsuario.toLowerCase();
			correo = correo.toLowerCase();
			tx = session.beginTransaction();			
			Usuario u = (Usuario) session.get(Usuario.class, idUsuario);
			
			if (u != null)
				return REGISTRO_USUARIO_EXISTENTE;
			else {			
				Query selUsuario = session.createQuery("FROM Usuario WHERE correo = :mail");
				selUsuario.setString("mail", correo);				
				List<Usuario> l = selUsuario.list();
				
				if (!l.isEmpty())
					return REGISTRO_CORREO_EXISTENTE;
				else {
					/** Idioma */
					Idioma lang = (Idioma) session.get(Idioma.class, idioma);
					
					if (lang == null) {
						Query selIdioma = session.createQuery("FROM " + Idioma.class.getName());
						List<Idioma> li = selIdioma.list();
						
						if (li.isEmpty())
							throw new PersistenciaException("No se han definido idiomas");
						else
							lang = li.get(0);
					}
					
					/** Primer nivel de la ruta por defecto */
					Query selRuta = session.createQuery("FROM " + Ruta.class.getName() + " WHERE id.id = :idRuta ORDER BY nivel");
					selRuta.setShort("idRuta", ConfiguracionLoadHelper.getInstancia().getIdRutaXDefecto());
					List<Ruta> niveles = selRuta.list();
					Ruta ruta = null;
					
					if (niveles.isEmpty()) {
						Query selRutas = session.createQuery("FROM " + Ruta.class.getName() + " ORDER BY nivel");
						niveles = selRutas.list();
						
						if (niveles.isEmpty())
							throw new PersistenciaException("No se ha definido ni una sola ruta");
						else
							ruta = niveles.get(0); 
					} else
						ruta = niveles.get(0);					 
					
					u = new Usuario();
					u.setAlias(alias);
					u.setClave(UtilSeguridad.getClaveEncriptada(idUsuario, clave));
					u.setCorreo(correo);
					u.setEstado("");
					u.setId(idUsuario);					
					u.setIdioma(lang);
					u.setLogeado(false);
					u.setRutaNivel(ruta);
					session.save(u);
					tx.commit();
					
					return REGISTRO_OK;
				}
			}
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenciaException("Error en el logout del usuario", e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
	
	
	
	/** 
	 * Retorna datos del usuario 
	 */
	@Override
	public DatosUsuario getDatosUsuario(String idUsuario) throws PersistenciaException {
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		Session session = sessions.openSession();
		
		try {
			return Usuario.getDatosUsuario(getUsuario(session, idUsuario)); 
		} finally {
			UtilHibernate.closeSession(session);
		}
	}


	
	/** 
	 * Retorna el idioma del usuario 
	 */
	@Override
	public String getIdioma(String idUsuario) throws PersistenciaException {
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		Session session = sessions.openSession();
		
		try {
			return getUsuario(session, idUsuario).getIdioma().getId();
		} finally {
			UtilHibernate.closeSession(session);
		}
	}


	
	/** 
	 * Setea el idioma del usuario 
	 */
	@Override
	public void setIdioma(String idUsuario, String idiomaId) throws PersistenciaException {
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		UtilValidacionParametros.validarIdIdioma(idiomaId);		
		Session session = sessions.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Usuario u = getUsuario(session, idUsuario);
			Idioma idioma = (Idioma) session.get(Idioma.class, idiomaId);
			
			if (idioma == null) {
				log.error("No se ha definido el lenguaje [" + idiomaId + "]");
				throw new PersistenciaException("No se ha definido el lenguaje [" + idiomaId + "]");
			} else {
				u.setIdioma(idioma);
				session.save(u);
				tx.commit();
			}
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenceException("Error al setear el idioma del usuario [" + idUsuario + "] lenguaje [" + idiomaId + "]",  e);
		} finally {
			UtilHibernate.closeSession(session);
		}		
	}

	
	
	/** 
	 * Incrementa el nivel del usuario 
	 */
	@Override
	public void incNivel(String idUsuario) throws PersistenciaException {
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		Session session = sessions.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Usuario u = getUsuario(session, idUsuario);
			int nuevoNivel = 1 + u.getRutaNivel().getId().getNivel();
			Ruta r = (Ruta) session.get(Ruta.class, new RutaPK(u.getRutaNivel().getId().getId(), nuevoNivel));
			
			if (r != null) {
				u.setRutaNivel(r);
				session.save(u);
				tx.commit();
			}			
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenceException("Error al incrementar el nivel del usuario [" + idUsuario + "]",  e);
		} finally {
			UtilHibernate.closeSession(session);
		}			
	}



	/** 
	 * Retorna el ranking de los primeros N usuarios (incluidos el pasado como par�metro) para la ruta del usuario 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<DatosUsuario> getRanking(String idUsuario, int cant) throws PersistenciaException {
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		Session session = sessions.openSession();
		
		try {
			ArrayList<DatosUsuario> ret = new ArrayList<DatosUsuario>();
			boolean incluyeUsuario = false;
			Usuario usuario = getUsuario(session, idUsuario);
			Query sel = session.createQuery("FROM Usuario WHERE ruta = :rutaId ORDER BY nivel DESC");
			sel.setShort("rutaId", usuario.getRutaNivel().getId().getId());
			int cont = 0;
			
			for (Iterator<Usuario> it = sel.list().iterator(); it.hasNext() && (cont < cant); cont++) {
				Usuario u = it.next();				
				incluyeUsuario = incluyeUsuario || u.getId().equalsIgnoreCase(usuario.getId());
				ret.add(Usuario.getDatosUsuario(u));
			}
			
			if (!incluyeUsuario && !ret.isEmpty()) {
				ret.remove(ret.size()-1);
				ret.add(Usuario.getDatosUsuario(usuario));
			}				
			
			return ret;
		} finally {
			UtilHibernate.closeSession(session);
		}
	}


	
	/** 
	 * Setea el estado del juego actual del usuario
	 */	
	@Override
	public void setEstado(String idUsuario, String estado) throws PersistenciaException {
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		Session session = sessions.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Usuario u = getUsuario(session, idUsuario);
			u.setEstado(estado);
			session.save(u);
			tx.commit();			
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenceException("Error al setear el estado del usuario [" + idUsuario + "]",  e);
		} finally {
			UtilHibernate.closeSession(session);
		}		
	}	
	
	
	
	/** 
	 * Retorna la hoja de estilos de la ruta + nivel actual del usuario, si es que tiene 
	 */
	public String getRutaHojaEstilo (String idUsuario) throws PersistenciaException {
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		Session session = sessions.openSession();
		
		try {
			Usuario u = (Usuario) session.get(Usuario.class, idUsuario);
			
			if (u == null)
				throw new PersistenciaException("No existe el usuario [" + idUsuario + "]");
			else
				return u.getRutaNivel().getHojaEstilo();
		} catch (PersistenceException e) {
			throw new PersistenciaException("Error al obtener la hoja de estilo de la ruta + nivel actual del usuario " + idUsuario, e);
		}
	}


	
	/**
	 * Obtiene el usuario dado su identificador
	 */
	private static Usuario getUsuario (Session session, String idUsuario) throws PersistenciaException {
		if (idUsuario == null)
			throw new PersistenciaException("Identificador de usuario nulo");
		
		idUsuario = idUsuario.toLowerCase();
		
		try {
			Usuario u = (Usuario) session.get(Usuario.class, idUsuario);
			
			if (u == null)
				throw new PersistenciaException("No existe el usuario [" + idUsuario + "]");
			else
				return u;
		} catch (PersistenceException e) {
			throw new PersistenciaException("Error al obtener el usuario " + idUsuario, e);
		}
	}	
}