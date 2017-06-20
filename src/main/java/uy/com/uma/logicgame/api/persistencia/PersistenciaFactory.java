package uy.com.uma.logicgame.api.persistencia;

import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.api.conf.ConfiguracionManejadoresLoadHelper;

/**
 * Retorna una instancia de cada manejador de persistencia
 *
 * @author Santiago Dalchiele
 */
public class PersistenciaFactory {

	/** Unica instancia de la clase */
	private static volatile PersistenciaFactory instancia = null;
	private static final Object lock = new Object();
	
	/** Configuracion */
	private ConfiguracionManejadoresLoadHelper conf = ConfiguracionManejadoresLoadHelper.getInstancia();
	
	/** Instancias de clase */
	private IManejadorEstructura manEstructura;
	private IManejadorSesiones manSesiones;
	private IManejadorAdminInternacionalizacion manAdminInter;
	private IManejadorInternacionalizacion manInter;
	private IManejadorJuego manJuego;
	private IManejadorJuegoWeb manJuegoWeb;
	private IManejadorSeguridad manSeguridad;
	private IManejadorConfiguracion manConfiguracion;
	private ICargadorRecursos cargaRecursos;
	private IManejadorLogAcciones manLogAcciones;
	
	
	
	/**
	 * Retorna la única instancia de la clase
	 */
	public static PersistenciaFactory getInstancia() throws ConfiguracionException {
		if (instancia == null)
			synchronized (lock) {
				if (instancia == null)
					instancia = new PersistenciaFactory();
			}			
		
		return instancia;
	}
	
	private PersistenciaFactory() throws ConfiguracionException {		
	}
	
	
	
	/**
	 * Metodos de acceso
	 */
	public IManejadorEstructura getManejadorEstructura() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (manEstructura == null)
			manEstructura = (IManejadorEstructura) Class.forName(conf.getManejadorEstructura()).newInstance();
		
		return manEstructura;
	}
	
	public IManejadorSesiones getManejadorSesiones() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (manSesiones == null)
			manSesiones = (IManejadorSesiones) Class.forName(conf.getManejadorSesiones()).newInstance();
		
		return manSesiones;
	}
	
	public IManejadorAdminInternacionalizacion getManejadorAdminInternacionalizacion() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (manAdminInter == null)
			manAdminInter = (IManejadorAdminInternacionalizacion) Class.forName(conf.getManejadorAdminInternacionalizacion()).newInstance();
		
		return manAdminInter;
	}
	
	public IManejadorInternacionalizacion getManejadorInternacionalizacion() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (manInter == null)
			manInter = (IManejadorInternacionalizacion) Class.forName(conf.getManejadorInternacionalizacion()).newInstance();
		
		return manInter;
	}
	
	public IManejadorJuego getManejadorJuego() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (manJuego == null)
			manJuego = (IManejadorJuego) Class.forName(conf.getManejadorJuego()).newInstance();
		
		return manJuego;
	}
	
	public IManejadorJuegoWeb getManejadorJuegoWeb() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (manJuegoWeb == null)
			manJuegoWeb = (IManejadorJuegoWeb) Class.forName(conf.getManejadorJuegoWeb()).newInstance();
		
		return manJuegoWeb;
	}
	
	public IManejadorSeguridad getManejadorSeguridad() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (manSeguridad == null)
			manSeguridad = (IManejadorSeguridad) Class.forName(conf.getManejadorSeguridad()).newInstance();
		
		return manSeguridad;
	}
	
	public IManejadorConfiguracion getManejadorConfiguracion() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (manConfiguracion == null)
			manConfiguracion = (IManejadorConfiguracion) Class.forName(conf.getManejadorConfiguracion()).newInstance();
	
		return manConfiguracion;
	}
	
	public ICargadorRecursos getCargadorRecursos() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (cargaRecursos == null)
			cargaRecursos = (ICargadorRecursos) Class.forName(conf.getCargadorRecursos()).newInstance();
		
		return cargaRecursos;
	}
	
	public IManejadorLogAcciones getManejadorLogAcciones() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (manLogAcciones == null)
			manLogAcciones = (IManejadorLogAcciones) Class.forName(conf.getManejadorLogAcciones()).newInstance();
		
		return manLogAcciones;
	}
}
