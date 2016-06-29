package uy.com.uma.logicgame.api.conf;

import java.io.File;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.hibernate.cfg.Configuration;

import uy.com.uma.comun.util.UtilFormato;
import uy.com.uma.logicgame.nucleo.jaxb.conf.Configuracion;
import uy.com.uma.logicgame.nucleo.jaxb.conf.Configuracion.Estrategias.Estrategia;
import uy.com.uma.logicgame.resolucion.EstrategiaException;
import uy.com.uma.logicgame.resolucion.estrategias.EstrategiasComparator;
import uy.com.uma.logicgame.resolucion.estrategias.IEstrategia;

/**
 * Carga en un SortedSet las estrategias definidas en el archivo de configuración de la aplicación
 *
 * @author Santiago Dalchiele
 */
public class ConfiguracionLoadHelper implements IConfiguracionConstantes {
	
	/** Unica instancia de la clase */
	private static ConfiguracionLoadHelper instancia = null;
	
	/** Configuración en el archivo xml */
	private Configuracion conf;
	
	
	
	/**
	 * Implemanta singleton, retorna la única instancia de esta clase
	 * @return
	 */
	public static ConfiguracionLoadHelper getInstancia() throws ConfiguracionException {
		if (instancia == null)
			instancia = new ConfiguracionLoadHelper();
		return instancia;
	}
	
	
	
	/**
	 * Intenta re-configurar la única instancia de esta clase 
	 */
	public static void configure() throws ConfiguracionException {
		if (instancia == null)
			instancia = new ConfiguracionLoadHelper();
		else
			instancia.cargaConfiguracion();
	}
	
	
	
	/**
	 * Carga la configuración del sistema de: System.getenv(VAR_ENV_LG_HOME) + File.pathSeparator + LOGICGAME_CONF_FILE_NAME
	 */
	public static Configuracion loadConfiguracion() throws ConfiguracionException {
		String path = System.getenv(VAR_ENV_LG_HOME);

		if (UtilFormato.esNulo(path)) {
			System.out.println("Debe parametrizar la ruta de la configuración en la variable de environment: " + VAR_ENV_LG_HOME);
			path = ".";
		}
		
		System.out.println("La variable de la ruta de configuracion del environment es: [" + VAR_ENV_LG_HOME + "] y su contenido es [" + path + "]");
		
		if (!path.endsWith(File.separator))
			path += File.separator;
		
		path += LOGICGAME_CONF_FILE_NAME;		
		File f = new File(path);
		
		if (!f.exists())
			throw new ConfiguracionException("No se encuentra la configuración en [" + path + "]");
		
		try {			
			System.out.println("Obteniendo la configuracion de: [" + path + "]");
			JAXBContext jaxbContext = JAXBContext.newInstance(Configuracion.class);		 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (Configuracion) jaxbUnmarshaller.unmarshal(f);
		} catch (Exception e) {
			throw new ConfiguracionException("Error al cargar las estrategias del archivo de configuración", e);
		}
	}
	
	
	
	/**
	 * Carga la configuración de hibernate
	 */
	public static Configuration loadHibernateConfiguration() {
		String path = System.getenv(VAR_ENV_LG_HOME);

		if (UtilFormato.esNulo(path)) {
			System.out.println("Debe parametrizar la ruta de la configuración en la variable de environment: " + VAR_ENV_LG_HOME);
			path = ".";
		}
		
		System.out.println("La variable de la ruta de configuracion del environment es: [" + VAR_ENV_LG_HOME + "] y su contenido es [" + path + "]");
		
		if (!path.endsWith(File.separator))
			path += File.separator;
		
		path += HIBERNATE_CONF_FILE_NAME;
		System.out.println("Obteniendo la configuracion de hibernate de: [" + path + "]");
		Configuration configuration = new Configuration(); 
		configuration.configure(new File(path));
		return configuration;
	}
	
	
	
	/**
	 * Constructor por defecto, carga la configuración del sistema
	 */
	private ConfiguracionLoadHelper() throws ConfiguracionException {
		cargaConfiguracion();
	}
	
	
	
	/**
	 * Carga en un SortedSet las estrategias definidas en el archivo de configuración de la aplicación
	 * @param f archivo de configuración
	 * @return estrategias ordenadas por su orden
	 * @throws EstrategiaException
	 */
	public SortedSet<IEstrategia> cargarEstrategias() throws ConfiguracionException {
		SortedSet<IEstrategia> estrategias = new TreeSet<IEstrategia>(new EstrategiasComparator());
		
		for (Estrategia e : conf.getEstrategias().getEstrategia()) {			
			try {
				IEstrategia estrategia = (IEstrategia) Class.forName(e.getClase()).newInstance();
				estrategia.setOrden(e.getOrden());
				estrategia.setCosto(e.getCosto().intValue());
				estrategias.add(estrategia);
			} catch (Exception e1) {
				throw new ConfiguracionException("Error al crear la clase " + e.getClase(), e1);
			}
			
		}
		
		return estrategias;
	}
	
	
	
	/**
	 * Retorna el costo inicial de la estrategia, analizar por absurdo
	 */
	public int getCostoInicialXAbsuarod() {
		return conf.getCostoInicialPorAbsurdo().intValue();
	}
	
	
	
	/**
	 * Retorna la ruta por defecto al crear un usuario
	 */
	public short getIdRutaXDefecto() {
		return conf.getRutaDefecto();
	}
	
	
	
	/**
	 * Retorna el security SALT
	 */
	public String getSecuritySalt() {
		return conf.getSecuritySalt();
	}
	
	
	
	/**
	 * Claves de los roles en la base de datos
	 * Utilizados cuando la estructura de la base se crea por programa y no directamente por un script (Heroku)
	 */
	public String getClaveRolAdm() {
		return conf.getClaveRolAdm();
	}	
	public String getClaveRolWeb() {
		return conf.getClaveRolWeb();
	}
	
	
	
	/**
	 * Carga la configuración del sistema
	 */
	private void cargaConfiguracion() throws ConfiguracionException {
		this.conf = loadConfiguracion();
	}
}
