package uy.com.uma.logicgame.api.conf;

import uy.com.uma.logicgame.nucleo.jaxb.conf.Configuracion.Manejadores;

/**
 * Obtiene el nombre de las clases que implementan los distintos manejadores de persistencia
 *
 * @author Santiago Dalchiele
 */
public class ConfiguracionManejadoresLoadHelper {

	/** Unica instancia de la clase */
	private static ConfiguracionManejadoresLoadHelper instancia = null;
	
	/** Nombre de las clases parametrizadas */
	private String manejadorEstructura;
	private String manejadorSesiones;
	private String manejadorAdminInternacionalizacion;
	private String manejadorInternacionalizacion;
	private String manejadorJuego;
	private String manejadorJuegoWeb;
	private String manejadorSeguridad;
	private String manejadorConfiguracion;
	private String cargadorRecursos;
	private String manejadorLogAcciones;
	
	
	
	/**
	 * Implementa singleton, retorna la unica instancia de la clase
	 */
	public static ConfiguracionManejadoresLoadHelper getInstancia() throws ConfiguracionException {
		if (instancia == null)
			instancia = new ConfiguracionManejadoresLoadHelper();
		
		return instancia;
	}
	
	
	
	/**
	 * Intenta re-configurar la única instancia de esta clase 
	 */
	public static void configure() throws ConfiguracionException {
		if (instancia == null)
			instancia = new ConfiguracionManejadoresLoadHelper();
		else
			instancia.cargaConfiguracion();
	}
	
	
	
	/**
	 * Constructor por defecto, carga la configuración del sistema
	 */
	private ConfiguracionManejadoresLoadHelper() throws ConfiguracionException {
		cargaConfiguracion();
	}
	
	
	
	/**
	 * Carga la configuración del sistema
	 */
	private void cargaConfiguracion() throws ConfiguracionException {			
		Manejadores man = ConfiguracionLoadHelper.loadConfiguracion().getManejadores();
		cargadorRecursos = man.getCargadorRecursos();			
		manejadorAdminInternacionalizacion = man.getAdmInternacionalizacion();
		manejadorInternacionalizacion = man.getInternacionalizacion();
		manejadorJuego = man.getJuego();
		manejadorJuegoWeb = man.getJuegoWeb();
		manejadorSeguridad = man.getSeguridad();
		manejadorSesiones = man.getSesiones();
		manejadorEstructura = man.getEstructura();
		manejadorConfiguracion = man.getConfiguracion();
		manejadorLogAcciones = man.getLogAcciones();
	}



	/**
	 * Metodos de acceso
	 */
	public String getManejadorEstructura() {
		return manejadorEstructura;
	}
	public String getManejadorSesiones() {
		return manejadorSesiones;
	}
	
	public String getManejadorAdminInternacionalizacion() {
		return manejadorAdminInternacionalizacion;
	}

	public String getManejadorInternacionalizacion() {
		return manejadorInternacionalizacion;
	}

	public String getManejadorJuego() {
		return manejadorJuego;
	}
	
	public String getManejadorJuegoWeb() {
		return manejadorJuegoWeb;
	}
	
	public String getManejadorSeguridad() {
		return manejadorSeguridad;
	}
	
	public String getManejadorConfiguracion() {
		return manejadorConfiguracion;
	}
	
	public String getCargadorRecursos() {
		return cargadorRecursos;
	}
	
	public String getManejadorLogAcciones() {
		return manejadorLogAcciones;
	}
}
