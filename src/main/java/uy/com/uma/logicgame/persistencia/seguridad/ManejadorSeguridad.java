package uy.com.uma.logicgame.persistencia.seguridad;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uy.com.uma.logicgame.api.bean.RutaDO;
import uy.com.uma.logicgame.api.bean.UsuarioDO;
import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.api.conf.ConfiguracionLoadHelper;
import uy.com.uma.logicgame.api.persistencia.IManejadorSeguridad;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.api.validacion.UtilValidacionParametros;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;
import uy.com.uma.logicgame.persistencia.UtilHibernate;
import uy.com.uma.logicgame.persistencia.inter.Idioma;

/**
 * Manejador del esquema de seguridad
 *
 * @author Santiago Dalchiele
 */
public class ManejadorSeguridad implements IManejadorSeguridad {
	
	private static final Logger log = LogManager.getLogger(ManejadorSeguridad.class.getName());

	
	/** Locale */
	private static final Locale LOCALE = Locale.getDefault();

	/** session factory */
	private SessionFactory sessions = SessionFactoryUtil.getSessionFactory();
	
	
	
	/** 
	 * Retorna TRUE si el usuario está logeado en el sistema 
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
	 * Retorna TRUE si existe un usuario con este identificado o dirección de correo 
	 */
	@Override
	public boolean existeUsuario (String idUsuario) throws PersistenciaException {
		if (!UtilValidacionParametros.esValidoIdUsuario(idUsuario))
			UtilValidacionParametros.validarCorreo(idUsuario);
		
		Session session = sessions.openSession();
		
		try {
			idUsuario = idUsuario.toLowerCase(LOCALE);
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
				
			return (u != null);
		} catch (Exception e) {
			throw new PersistenciaException("Error al controlar existencia de usuario", e);
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
			idUsuario = idUsuario.toLowerCase(LOCALE);
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
					u.setToken(null);
					u.setFchExpiraToken(null);
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
	 * Se puede autenticar con id de usuario o correo, retorna en valor en minúsculas del id de usuario 
	 */
	public String getIdUsuario (String idUsuario) throws PersistenciaException {
		if (idUsuario == null)
			throw new PersistenciaException("Identificador de usuario nulo");

		if (!UtilValidacionParametros.esValidoIdUsuario(idUsuario))
			UtilValidacionParametros.validarCorreo(idUsuario);
		
		Session session = sessions.openSession();
		
		try {	
			idUsuario = idUsuario.toLowerCase(LOCALE);
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
			idUsuario = idUsuario.toLowerCase(LOCALE);
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
			idUsuario = idUsuario.toLowerCase(LOCALE);
			correo = correo.toLowerCase(LOCALE);
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
		} catch (ConfiguracionException e) {
			UtilHibernate.rollback(tx);
			throw new PersistenciaException("Error en el logout del usuario", e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
	
	
	
	/** 
	 * Retorna un token generado en forma aleatoria y persistido en la base de datos para dicho usuario (el token dura 24 horas) 
	 */
	@Override
	public String generarToken (String idUsuario) throws PersistenciaException {
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		Session session = sessions.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();			
			idUsuario = idUsuario.toLowerCase(LOCALE);
			Usuario u = (Usuario) session.get(Usuario.class, idUsuario);
				
			if (u != null) {
				String token = UUID.randomUUID().toString();
				Calendar venc = Calendar.getInstance();
				venc.setTime(new Date());
				venc.add(Calendar.HOUR_OF_DAY, 24);
				u.setToken(token);
				u.setFchExpiraToken(venc.getTime());
				tx.commit();
				return token;
			} else
				return null;
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenciaException("Error en el logout del usuario", e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
	
	
	
	/** 
	 * Retorna TRUE si el token coincide con el identificador de usuario y no ha expirado 
	 */
	@Override
	public boolean esValidoToken (String idUsuario, String token) throws PersistenciaException {
		if (token == null)
			return false;
		
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		Session session = sessions.openSession();
		
		try {		
			Usuario u = getUsuario(session, idUsuario);
			
			if ((u.getToken() == null) || (u.getFchExpiraToken() == null))
				return false;
			else {
				Date ahora = new Date();
			
				if (ahora.after(u.getFchExpiraToken()))
					return false;				
				else
					return token.trim().equals(u.getToken().trim());
			}			
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
	
	
	
	/** 
	 * Asigna un nuevo valor a la clave 
	 */
	@Override
	public void resetClave (String idUsuario, String clave) throws PersistenciaException {
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		UtilValidacionParametros.validarClave(clave);
		Session session = sessions.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();			
			idUsuario = idUsuario.toLowerCase(LOCALE);
			Usuario u = (Usuario) session.get(Usuario.class, idUsuario);
				
			if (u != null) {
				u.setClave(UtilSeguridad.getClaveEncriptada(idUsuario, clave));
				u.setToken(null);
				u.setFchExpiraToken(null);
				tx.commit();
			}				
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenciaException("Error en el login de usuario", e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
	
	
	
	/** 
	 * Retorna datos del usuario 
	 */
	@Override
	public UsuarioDO getDatosUsuario(String idUsuario) throws PersistenciaException {
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
	 * Retorna el ranking de los primeros N usuarios (incluidos el pasado como parámetro) para la ruta del usuario 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<UsuarioDO> getRanking(String idUsuario, int cant) throws PersistenciaException {
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		Session session = sessions.openSession();
		
		try {
			ArrayList<UsuarioDO> ret = new ArrayList<UsuarioDO>();
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
	 * Retorna todos los usuarios del sistema ordenados por id 
	 */
	@Override
	public Collection<UsuarioDO> getUsuarios() throws PersistenciaException {
		log.debug("Obteniendo todos los usuarios del sistema");
		Collection<UsuarioDO> col = new ArrayList<UsuarioDO>();
		Session session = sessions.openSession();
		
		try {
			Query query = session.createQuery("FROM " + Usuario.class.getName() + " ORDER BY id");
			
			for (Object o : query.list())
				col.add(Usuario.getDatosUsuario((Usuario) o));
			
			return col;
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
	
	
	
	/** 
	 * Retorna las rutas persistidas en la base de datos (cada ruta con su nivel y su id de juego) 
	 */
	@Override
	public Collection<RutaDO> getRutas() throws PersistenciaException {
		log.debug("Obteniendo todas la rutas del sistema");
		Collection<RutaDO> col = new ArrayList<RutaDO>();		
		Session session = sessions.openSession();
		
		try {
			Query query = session.createQuery("FROM " + Ruta.class.getName() + " ORDER BY id");
			
			for (Object o : query.list())
				col.add(Ruta.getRuta((Ruta) o));
			
			return col;
		} finally {
			UtilHibernate.closeSession(session);
		}
	}

	
	
	/**
	 * Obtiene el usuario dado su identificador
	 */
	private static Usuario getUsuario (Session session, String idUsuario) throws PersistenciaException {
		if (idUsuario == null)
			throw new PersistenciaException("Identificador de usuario nulo");
		
		idUsuario = idUsuario.toLowerCase(LOCALE);
		
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