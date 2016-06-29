package uy.com.uma.logicgame.api.conf;


/**
 * Define constantes generales del sistema
  *
 * @author Santiago Dalchiele
 */
public interface IConfiguracionConstantes {

	/** 
	 * Variable de ambiente con la ruta del home de la aplicación, 
	 * donde se encuentran los archivos de configuración: hibernate.cfg.xml, logicgame-conf.xml, logicgame.properties 
	 */
	public static final String VAR_ENV_LG_HOME = "LOGICGAME_HOME";
	
	/** Nombre de los archivos de configuracion */
	public static final String HIBERNATE_CONF_FILE_NAME = "hibernate.cfg.xml"; 
	public static final String LOGICGAME_CONF_FILE_NAME = "logicgame-conf.xml";
	public static final String LOGICGAME_PROPS_FILE_NAME = "logicgame.properties";
}
