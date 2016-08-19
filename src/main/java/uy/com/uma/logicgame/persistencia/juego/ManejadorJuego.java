package uy.com.uma.logicgame.persistencia.juego;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uy.com.uma.comun.util.UtilString;
import uy.com.uma.logicgame.api.bean.JuegoDO;
import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.api.persistencia.IManejadorInternacionalizacion;
import uy.com.uma.logicgame.api.persistencia.IManejadorJuego;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.api.persistencia.PersistenciaFactory;
import uy.com.uma.logicgame.api.validacion.UtilValidacionParametros;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension.Valores;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.PistasDelJuego.PistaDelJuego.Pistas.Pista;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Literal;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Literal.Traduccion;
import uy.com.uma.logicgame.nucleo.jaxb.juego.UtilJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;
import uy.com.uma.logicgame.persistencia.UtilHibernate;
import uy.com.uma.logicgame.resolucion.Resolucion;

/**
 * Implementa la lógica de persistencia de los juegos
 *
 * @author Santiago Dalchiele
 */
public class ManejadorJuego implements IManejadorJuego {
	
	private static final Logger log = LogManager.getLogger(ManejadorJuego.class.getName());
	
	/** session factory */
	private SessionFactory sessions = SessionFactoryUtil.getSessionFactory();
	
	/** Manejador de la internacionalización */
	private IManejadorInternacionalizacion inter = PersistenciaFactory.getInstancia().getManejadorInternacionalizacion();
	
	/** Mapeo temporal para la persistencia de las dimensiones, mapea el id de internacionalización y su literal */
	private Map<String, Short> dimensiones = new HashMap<String, Short>();
	
	/** Mapeo temporal para la persistencia de los valores, mapea el id de internacionalización y su literal */
	private Map<String, Short> valores = new HashMap<String, Short>();

	
	
	public ManejadorJuego() throws ConfiguracionException, InstantiationException, IllegalAccessException, ClassNotFoundException {
	}
	
	
	
	/**
	 * Retorna TRUE si existe un juego con este identificador
	 */
	@Override
	public boolean existe(int id) throws PersistenciaException {
		Session session = sessions.openSession();
		
		try {
			uy.com.uma.logicgame.persistencia.juego.Juego juego = (uy.com.uma.logicgame.persistencia.juego.Juego) 
					session.get(uy.com.uma.logicgame.persistencia.juego.Juego.class, new Integer(id));
			
			return (juego != null);		
		} catch (PersistenceException e) {
			throw new PersistenciaException(e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}

	
	
	/**
	 * Retorna un juego dado su identificador (para todos los idiomas para los cuales está definido)
	 */
	@Override
	public Juego obtener(int id) throws PersistenciaException {
		Session session = sessions.openSession();
		
		try {
			uy.com.uma.logicgame.persistencia.juego.Juego j = (uy.com.uma.logicgame.persistencia.juego.Juego) 
					session.get(uy.com.uma.logicgame.persistencia.juego.Juego.class, new Integer(id));
			
			if (j == null)
				throw new PersistenceException("No existe ningún juego con el identificador " + id);
			
			Collection<String> idiomas = new ArrayList<String>();
			
			for (JuegoXIdioma ji : j.getJuegosXIdioma())
				idiomas.add(ji.getId().getIdioma());
			
			return obtener(id, UtilString.col2ArrayOfString(idiomas));
		} catch (PersistenceException e) {
			throw new PersistenciaException("Error al obtener el juego " + id, e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}

	
	
	/** 
	 * Retorna un juego dado su identificador 
	 */
	public Juego obtener (int id, String[] idiomas) throws PersistenciaException {
		Session session = sessions.openSession();
		log.debug("Obteniendo el juego " + id + " para los idiomas " + idiomas);
		
		try {
			uy.com.uma.logicgame.persistencia.juego.Juego j = (uy.com.uma.logicgame.persistencia.juego.Juego) 
					session.get(uy.com.uma.logicgame.persistencia.juego.Juego.class, new Integer(id));
			
			if (j == null)
				throw new PersistenceException("No existe ningún juego con el identificador " + id);
			else {
				Juego juego = new Juego();
				juego.setId(BigInteger.valueOf(id));
				juego.setTitulo(getLiteral(idiomas, j.getTitulo()));
				juego.setTexto(getLiteral(idiomas, j.getTexto()));
				juego.setCosto(BigInteger.valueOf(j.getCosto()));
				juego.setDimensiones(new Dimensiones());
				juego.setPistasDelJuego(new PistasDelJuego());
				
				for (uy.com.uma.logicgame.persistencia.juego.Dimension d : j.getDimensiones()) {
					Dimension dim = new Dimension();
					dim.setId(getLiteral(idiomas, d.getIdDim()));
					dim.setNro(d.getId().getSec());
					dim.setValores(new Valores());
					
					for (Valor v : d.getValores()) {
						uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension.Valores.Valor val = 
								new uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension.Valores.Valor();
						val.setId(getLiteral(idiomas, v.getValor()));
						val.setNro(v.getId().getSec());
						dim.getValores().getValor().add(val);
					}
					
					juego.getDimensiones().getDimension().add(dim);
				}
				
				for (PistaJuego pj : j.getPistas()) {
					PistaDelJuego pistaJuego = new PistaDelJuego();
					pistaJuego.setTexto(getLiteral(idiomas, pj.getTexto()));
					pistaJuego.setPistas(new Pistas());
					
					for (uy.com.uma.logicgame.persistencia.juego.Pista p : pj.getPistas()) {
						Pista pista = new Pista();
						pista.setDimension1 (p.getDim1());
						pista.setValor1 (p.getValor1());
						pista.setDimension2 (p.getDim2());
						pista.setValor2 (p.getValor2());
						pista.setAfirmaNiega(p.getAfirmaNiega());						
						pistaJuego.getPistas().getPista().add(pista);
					}
					
					juego.getPistasDelJuego().getPistaDelJuego().add(pistaJuego);
				}
				
				return juego;
			}
		} catch (PersistenceException e) {
			throw new PersistenciaException("Error al obtener el juego " + id + " para el idioma " + idiomas, e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
	
	
	
	/** 
	 * Retorna los juegos persistidos en la base de datos, según el idioma setea la definición del mismo 
	 */
	public Collection<JuegoDO> getJuegos (String idioma) throws PersistenciaException {
		UtilValidacionParametros.validarIdIdioma(idioma);
		log.debug("Obteniendo todos los juegos del sistema");
		Collection<JuegoDO> col = new ArrayList<JuegoDO>();
		Session session = sessions.openSession();
		
		try {
			final String table = uy.com.uma.logicgame.persistencia.juego.Juego.class.getName();
			Query query = session.createQuery("FROM " + table + " j ORDER BY j.id");
			
			for (Object o : query.list()) {
				col.add(uy.com.uma.logicgame.persistencia.juego.Juego.getJuego(idioma, 
										(uy.com.uma.logicgame.persistencia.juego.Juego) o));				
			}
			
			return col;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | ConfiguracionException e) {
			throw new PersistenciaException("Error al obtener los juegos del sistema" , e);
		} finally {
			UtilHibernate.closeSession(session);
		}	
	}
	
	
	
	/** 
	 * Persiste (inserta) un juego en la base de datos.  Si ya existe modifica su definición previa
	 */
	public void persistir (Juego juego) throws PersistenciaException {
		Session session = sessions.openSession();
		Transaction tx = null;
		String [] idiomas = UtilJuego.getIdiomas(juego);
		String idiomaXDefecto = UtilJuego.getIdiomaXDefecto(juego);
		log.debug("Persistiendo el juego " + juego.getId() + " " + juego.getTitulo() + " para el idioma [" + idiomas + "]");
		dimensiones.clear();
		valores.clear();		
		
		try {			
			tx = session.beginTransaction();
			uy.com.uma.logicgame.persistencia.juego.Juego j = (uy.com.uma.logicgame.persistencia.juego.Juego) 
					session.get(uy.com.uma.logicgame.persistencia.juego.Juego.class, new Integer(juego.getId().intValue()));
			
			if (j == null) {
				j = new uy.com.uma.logicgame.persistencia.juego.Juego();
				j.setId(juego.getId().intValue());
			} else {
				log.warn("Ya existe el juego " + juego.getId() + " " + juego.getTitulo());			
			}						

			for (PistaJuego pj : j.getPistas())
				session.delete(pj);

			for (uy.com.uma.logicgame.persistencia.juego.Dimension d : j.getDimensiones())
				session.delete(d);			
			
			j.setTitulo(inter.internacionalizar(idiomaXDefecto, UtilJuego.getTextoXIdioma(idiomaXDefecto, juego.getTitulo())));
			j.setTexto(inter.internacionalizar(idiomaXDefecto, UtilJuego.getTextoXIdioma(idiomaXDefecto, juego.getTexto())));
			j.setCosto(juego.getCosto().intValue());
			j.getPistas().clear();
			j.getDimensiones().clear();			
			session.save(j);			
			internacionalizar(j.getTitulo(), juego.getTitulo(), idiomaXDefecto, idiomas);
			internacionalizar(j.getTexto(), juego.getTexto(), idiomaXDefecto, idiomas);
			persistirDimensiones(tx, session, juego);
			persistirPistas(tx, session, juego);		
			tx.commit();
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenciaException("No se pudo insertar el juego [" + juego.getId() + "] para el idioma [" + idiomas + "]",  e);
		} finally {			
			UtilHibernate.closeSession(session);
		}
		
		
	}



	/**
	 * Borra de la base de datos el juego dado su identificador
	 */
	@Override
	public void borrar(int id) throws PersistenciaException {
		Session session = sessions.openSession();
		Transaction tx = null;
		
		try {		
			uy.com.uma.logicgame.persistencia.juego.Juego juego = (uy.com.uma.logicgame.persistencia.juego.Juego) 
					session.get(uy.com.uma.logicgame.persistencia.juego.Juego.class, new Integer(id));
			
			if (juego != null) {
				tx = session.beginTransaction();
				
				for (PistaJuego pj : juego.getPistas())
					session.delete(pj);
				
				session.delete(juego);
				tx.commit();
			} else
				log.warn("No se pudo borrar el juego porque no existe el juego " + id);
		} catch (Exception e) {
			UtilHibernate.rollback(tx);
			throw new PersistenciaException("No se pudo borrar el juego " + id,  e);
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
	
	
	
	/** 
	 * Actualiza los atributos redundantes de un juego en la base de datos 
	 */
	public void actualizarRedundancias (int id) throws PersistenciaException {		
		if (!existe(id))
			throw new PersistenceException("No existe el juego " + id);
		else {
			Session session = sessions.openSession();
			Transaction tx = null;
			
			try {		
				tx = session.beginTransaction();
				uy.com.uma.logicgame.persistencia.juego.Juego j = (uy.com.uma.logicgame.persistencia.juego.Juego) 
						session.get(uy.com.uma.logicgame.persistencia.juego.Juego.class, new Integer(id));
				
				if (j == null)
					throw new PersistenceException("No existe ningún juego con el identificador " + id);
				else {					
					short cantDims = (short) j.getDimensiones().size();
					short cantValores = (short) j.getDimensiones().get(0).getValores().size();
					Juego juego = obtener(id, inter.getIdiomasXId(j.getTitulo()));		
					Resolucion r = Resolucion.resolver(juego);				
					j.setCantDims(cantDims);
					j.setCantValores(cantValores);
					j.setSolucion(r.getSolucion());
					session.update(j);
					
					for (String idioma : inter.getIdsIdiomas()) {
						try {
							juego = obtener(id, new String[] {idioma});
							String defJuego = UtilString.reemplazarLetrasEspeciales(DefJuegoBuilder.construir(idioma, juego));
							JuegoXIdiomaPK pk = new JuegoXIdiomaPK(id, idioma);
							JuegoXIdioma jxi = (JuegoXIdioma) session.get(JuegoXIdioma.class, pk);
							
							if (jxi == null) {
								jxi = new JuegoXIdioma();
								jxi.setId(pk);
								jxi.setDefJuego(defJuego);
								session.save(jxi);
							} else {
								jxi.setDefJuego(defJuego);
								session.update(jxi);
							}	
						} catch (PersistenciaException e) {
							log.warn("Error al construir redundancias el juego " + id + " para el idioma " + idioma, e);
						}
					}
					
					tx.commit();
				}			
			} catch (ConfiguracionException | ValidadorJuegoException e) {
				UtilHibernate.rollback(tx);
				throw new PersistenciaException("Error no esperado al actualizar redundancias del juego " + id, e);
			} finally {
				UtilHibernate.closeSession(session);
			}
		}
	}
	
	
	
	/**
	 * Persiste las dimensiones y cada uno de sus valores de un juego
	 */
	private void persistirDimensiones (Transaction tx, Session session, Juego juego) throws PersistenciaException {
		short secDim = 1;
		String [] idiomas = UtilJuego.getIdiomas(juego);
		String idiomaXDefecto = UtilJuego.getIdiomaXDefecto(juego);
		
		for (Dimension dim : juego.getDimensiones().getDimension()) {
			String idDimStr = UtilJuego.getTextoXIdioma(idiomaXDefecto, dim.getId());
			long idDim = inter.internacionalizar(idiomaXDefecto, idDimStr);
			uy.com.uma.logicgame.persistencia.juego.Dimension d = new uy.com.uma.logicgame.persistencia.juego.Dimension();
			d.setId(new DimensionPK(juego.getId().intValue(), secDim));						
			d.setIdDim(idDim);
			session.save(d);
			internacionalizar(idDim, dim.getId(), idiomaXDefecto, idiomas);	
			
			if (!dimensiones.containsKey(idDimStr))
				dimensiones.put(idDimStr, secDim);
			
			for (uy.com.uma.logicgame.nucleo.jaxb.juego.Juego.Dimensiones.Dimension.Valores.Valor valor : dim.getValores().getValor()) {				
				String val = UtilJuego.getTextoXIdioma(idiomaXDefecto, valor.getId());
				long idValor = inter.internacionalizar(idiomaXDefecto, val);
				Valor v = new Valor();
				v.setId(new ValorPK(juego.getId().intValue(), secDim, valor.getNro()));
				v.setValor(idValor);
				session.save(v);
				internacionalizar(idValor, valor.getId(), idiomaXDefecto, idiomas);
				
				if (!valores.containsKey(keyDimValor(idDimStr, val)))
					valores.put(keyDimValor(idDimStr, val), valor.getNro());				
			}
			
			secDim++;
		}
	}

	
	
	/**
	 * Persiste las pistas del juego y cada una de sus afirmaciones/negaciones
	 */
	private void persistirPistas (Transaction tx, Session session, Juego juego) throws PersistenciaException {
		short secPista = 1;
		String [] idiomas = UtilJuego.getIdiomas(juego);
		String idiomaXDefecto = UtilJuego.getIdiomaXDefecto(juego);
		
		for (PistaDelJuego p : juego.getPistasDelJuego().getPistaDelJuego()) {			
			long idTexto = inter.internacionalizar(idiomaXDefecto, UtilJuego.getTextoXIdioma(idiomaXDefecto, p.getTexto()));
			PistaJuego pj = new PistaJuego();
			pj.setId(new PistaJuegoPK(juego.getId().intValue(), secPista));
			pj.setTexto(idTexto);
			session.save(pj);
			internacionalizar(idTexto, p.getTexto(), idiomaXDefecto, idiomas);
			short sec = 1;
			
			for (Pista pista : p.getPistas().getPista()) {
				uy.com.uma.logicgame.persistencia.juego.Pista pi = new uy.com.uma.logicgame.persistencia.juego.Pista();
				pi.setId(new PistaPK(juego.getId().intValue(), secPista, sec));
				pi.setDim1(pista.getDimension1());
				pi.setDim2(pista.getDimension2());
				pi.setValor1(pista.getValor1());
				pi.setValor2(pista.getValor2());
				pi.setAfirmaNiega(pista.isAfirmaNiega());
				session.save(pi);
				sec++;
			}
			
			secPista++;
		}
	}
	
	
	
	/**
	 * Retorna un valor clave de una dimensión y un valor (separados estos por un |) 
	 */
	private static String keyDimValor (String idDim, String idValor) {
		return idDim + "|" + idValor;
	}
	
	
	
	/**
	 * Retorna un literal (juego.xsd) con las traducciones en los idiomas pasado como parámetro
	 */
	private Literal getLiteral (String [] idiomas, long id) throws PersistenciaException {
		Literal lit = new Literal();
	
		for (String idioma : idiomas) {
			Traduccion trad = new Traduccion();
			trad.setIdioma(idioma);
			trad.setTexto(inter.getTexto(idioma, id));		
			lit.getTraduccion().add(trad);
		}
		
		return lit;
	}	
	
	
	
	/**
	 * Internacionaliza el literal de identificador id en todos los restantes idiomas que no sean el idioma por defecto
	 * @param id identificador del literal en la tabla literales
	 * @param lit Literal en el xml del juego
	 * @param idiomaXDefecto idioma a no tener en cuenta
	 * @param idiomas lista de idiomas a internacionalizar
	 * @throws PersistenciaException
	 */
	private void internacionalizar (long id, Literal lit, String idiomaXDefecto, String[] idiomas) throws PersistenciaException {
		for (String otroIdioma : idiomas)
			if (!otroIdioma.equals(idiomaXDefecto)) 
				inter.internacionalizar(id, otroIdioma, UtilJuego.getTextoXIdioma(otroIdioma, lit));
	}
}
