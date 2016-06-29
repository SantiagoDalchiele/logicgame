package uy.com.uma.logicgame.persistencia;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import uy.com.uma.comun.util.UtilFormato;
import uy.com.uma.logicgame.api.conf.ConfiguracionLoadHelper;

/**
 * Implementa singleton a los efectos de retornar sessiones para hibernate
 *
 * @author Santiago Dalchiele
 */
public class SessionFactoryUtil {
	
	private static final Logger log = LogManager.getLogger(SessionFactoryUtil.class.getName());

	/** Unica instancia de la clase */
	private static SessionFactory sessionFactory = null;
	
	
	
	/**
	 * Retorna la única instancia de la clase
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) 
			return getSessionFactory(new Configuration().configure());
		else
			return sessionFactory;
	}
	
	public static SessionFactory getSessionFactory(Configuration configuration) {
		if (sessionFactory == null)
	        sessionFactory = configurar(configuration);
		
		return sessionFactory;
	}
	
	private SessionFactoryUtil() {		
	}
	
	
	
	/**
	 * Resetea la session factory con una nueva configuracion
	 */
	public static void reset (String user, String password) {
		Configuration configuration = ConfiguracionLoadHelper.loadHibernateConfiguration();
		Properties p = configuration.getProperties(); 
		
		if (System.getenv("DATABASE_URL") != null) {			
			try {
				log.info("Configurando la base de datos a partir de la variable de environment DATABASE_URL [" + System.getenv("DATABASE_URL") + "]");
				DBData dbData = new DBData(new URI(System.getenv("DATABASE_URL")));
			    p.setProperty("hibernate.connection.url", dbData.getUrl());
			    p.setProperty("hibernate.connection.username", dbData.getUsername());
				p.setProperty("hibernate.connection.password", dbData.getPassword());
			} catch (ClassNotFoundException | URISyntaxException e) {
				log.error("Error al intentar configurar la base de datos, mal formada la url o no existe el driver");
				e.printStackTrace();
			}
		} else if ((!UtilFormato.esNulo(user)) && (!UtilFormato.esNulo(password))) {
			p.setProperty("hibernate.connection.username", user);
			p.setProperty("hibernate.connection.password", password);
		}
		
		log.debug("Configurando hibernate url [" + p.getProperty("hibernate.connection.url") + "] user: " + p.getProperty("hibernate.connection.username"));
		reset();
		getSessionFactory(configuration);
	}
	
	
	
	/**
	 * Resetea la session factory según la variable de entorno DATABASE_URL, utilizada en heroku
	 */
	public static Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException {
		if (UtilFormato.esNulo(System.getenv("DATABASE_URL"))) {
			String message = "No se puede configurar la conexion a la base de datos, porque no está seteado en el ambiente la variable DATABASE_URL"; 
			log.fatal(message);
			System.out.println(message);
			throw new SQLException(message);
		} else {
			DBData dbData = new DBData(new URI(System.getenv("DATABASE_URL")));
		    return DriverManager.getConnection(dbData.getUrl(), dbData.getUsername(), dbData.getPassword());
		}
	}
	
	
	
	/**
	 * Setea a nulo la session factory
	 */
	public static void reset() {
		sessionFactory = null;
	}
	
	
	
	/**
	 * Retorna TRUE si se inicializó
	 */
	public static boolean isInitialized() {
		return (sessionFactory != null);
	}
	
	
	
	/**
	 * Cierra la session
	 */
	public static void shutdown() {
		if (sessionFactory != null) {
			sessionFactory.close();
			sessionFactory = null;
		}
	}
	
	
	
	/**
	 * Retorna el resultado de configurar la sessionfactory
	 */
	private static SessionFactory configurar (Configuration configuration) {
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        return configuration.buildSessionFactory(serviceRegistry);
	}
}
