package uy.com.uma.logicgame.persistencia;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.uma.comun.util.UtilSQL;
import uy.com.uma.logicgame.api.persistencia.IManejadorEstructura;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;

/**
 * Manejador utilizado para administrar la estructura de la base de datos.
 * Motivado por el uso de heroku como servidor de deployment.
 *
 * @author Santiago Dalchiele
 */
public class ManejadorEstructura implements IManejadorEstructura {

	private static final Logger log = LogManager.getLogger(ManejadorEstructura.class.getName());
	
	/** Lista de tablas a crear */
	private List<String> createTablas = new ArrayList<String>();
	
	/** Nombre de las tablas ordenadas en el orden inverso a las que se crean */
	private List<String> idsTablas = new ArrayList<String>();
	
	
	
	
	/**
	 * Agrega a la lista de tablas los scripts de creación de tablas
	 */
	public ManejadorEstructura() {
		createTablas.add("CREATE TABLE IF NOT EXISTS idiomas (" + 
						"id CHAR(2) PRIMARY KEY," +
						"nombre VARCHAR(64) NOT NULL," + 
						"icono VARCHAR(256) NOT NULL)");
		
		createTablas.add("CREATE TABLE IF NOT EXISTS literales (" +
						"id BIGINT," +
						"idioma CHAR(2) REFERENCES idiomas (id)," +
						"texto TEXT NOT NULL," +
						"PRIMARY KEY (id, idioma))");
		
		createTablas.add("CREATE TABLE IF NOT EXISTS juegos (" +
						"id INTEGER PRIMARY KEY," +
						"titulo BIGINT NOT NULL," +
						"texto BIGINT NOT NULL," +
						"costo INTEGER NOT NULL," +
						"cant_dims SMALLINT," +
						"cant_valores SMALLINT," +
						"solucion TEXT)");
		
		createTablas.add("CREATE TABLE IF NOT EXISTS juegosxidioma (" +
						"id_juego INTEGER REFERENCES juegos (id)," +
						"idioma CHAR(2) REFERENCES idiomas (id)," +
						"def_juego TEXT," +
						"PRIMARY KEY (id_juego, idioma))");
		
		createTablas.add("CREATE TABLE IF NOT EXISTS dimensiones (" +
						"id_juego INTEGER REFERENCES juegos (id)," +
						"sec SMALLINT," +
						"id BIGINT NOT NULL," +
						"PRIMARY KEY (id_juego, sec))");
		
		createTablas.add("CREATE TABLE IF NOT EXISTS valores (" +
						"id_juego INTEGER," +
						"sec_dim SMALLINT," +
						"sec SMALLINT," +
						"valor BIGINT NOT NULL," +
						"PRIMARY KEY (id_juego, sec_dim, sec)," +
						"FOREIGN KEY (id_juego, sec_dim) REFERENCES dimensiones (id_juego, sec))");
		
		createTablas.add("CREATE TABLE IF NOT EXISTS pistas_juego (" +
						"id_juego INTEGER REFERENCES juegos (id)," +
						"sec SMALLINT," +
						"texto BIGINT NOT NULL," +
						"PRIMARY KEY (id_juego, sec))");
		
		createTablas.add("CREATE TABLE IF NOT EXISTS pistas (" +
						"id_juego INTEGER," +
						"sec_pista SMALLINT," +
						"sec SMALLINT," +
						"dim1 SMALLINT," +
						"valor1 SMALLINT," +
						"dim2 SMALLINT," +
						"valor2 SMALLINT," +
						"afirma_niega BOOLEAN NOT NULL," +
						"PRIMARY KEY (id_juego, sec_pista, sec)," +
						"FOREIGN KEY (id_juego, sec_pista) REFERENCES pistas_juego (id_juego, sec)," +
						"FOREIGN KEY (id_juego, dim1, valor1) REFERENCES valores (id_juego, sec_dim, sec)," +
						"FOREIGN KEY (id_juego, dim2, valor2) REFERENCES valores (id_juego, sec_dim, sec))");
		
		createTablas.add("CREATE TABLE IF NOT EXISTS rutas (" +
						"id SMALLINT," +
						"nivel INTEGER," +
						"id_juego INTEGER REFERENCES juegos (id)," +
						"hoja_estilo VARCHAR(256)," +
						"PRIMARY KEY (id, nivel))");
		
		createTablas.add("CREATE TABLE IF NOT EXISTS usuarios (" +
						"id VARCHAR(64) PRIMARY KEY," +
						"alias VARCHAR(64) NOT NULL," +
						"correo VARCHAR(128) NOT NULL," +
						"clave CHAR(256) NOT NULL," +
						"logeado BOOLEAN NOT NULL," +
						"ruta SMALLINT," +
						"nivel INTEGER," +
						"estado TEXT," +
						"idioma CHAR(2) REFERENCES idiomas (id)," +
						"FOREIGN KEY (ruta, nivel) REFERENCES rutas (id, nivel))");
		
		idsTablas.add("usuarios");
		idsTablas.add("rutas");
		idsTablas.add("pistas");
		idsTablas.add("pistas_juego");
		idsTablas.add("valores");
		idsTablas.add("dimensiones");
		idsTablas.add("juegosxidioma");
		idsTablas.add("juegos");
		idsTablas.add("literales");
		idsTablas.add("idiomas");
	}	
	
	
	
	/**
	 * Crea la estructura de las tablas y sus indices
	 */
	@Override
	public void crearTablas() throws PersistenciaException {
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = SessionFactoryUtil.getConnection();
			stmt = connection.createStatement();
			
			for (String sent : createTablas) {
				log.info(sent);
				stmt.execute(sent);
			}			
		} catch (Exception e) {
			throw new PersistenciaException(e);
		} finally {
			UtilSQL.close(connection, stmt);
		}
	}
	
	
	
	/** 
	 * Borra la estructura de las tablas creadas 
	 */
	@Override
	public void borrarTablas() throws PersistenciaException {
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = SessionFactoryUtil.getConnection();
			stmt = connection.createStatement();
			
			for (String id : idsTablas) {
				String sent = "DROP TABLE " + id;
				log.info(sent);
				stmt.execute(sent);
			}			
		} catch (Exception e) {
			throw new PersistenciaException(e);
		} finally {
			UtilSQL.close(connection, stmt);
		}		
	}



	/** 
	 * Borra todos los registros de todas las tablas del sistema 
	 */
	@Override
	public void borrarDatos() throws PersistenciaException {
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = SessionFactoryUtil.getConnection();
			stmt = connection.createStatement();
			
			for (String id : idsTablas) {
				String sent = "DELETE FROM " + id;
				log.info(sent);
				stmt.execute(sent);
			}			
		} catch (Exception e) {
			throw new PersistenciaException(e);
		} finally {
			UtilSQL.close(connection, stmt);
		}		
	}
	


	/** 
	 * Crea la ruta por defecto a asignarle a los usuarios cuando recien son creados 
	 */
	@Override
	public void crearRutaXDefecto() throws PersistenciaException {
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = SessionFactoryUtil.getConnection();
			stmt = connection.createStatement();
			final String ins = "INSERT INTO rutas (id, nivel, id_juego) " + 
									"SELECT 1, 1, id FROM juegos WHERE id = 10 " +
									"UNION SELECT 1, 2, id FROM juegos WHERE id = 17 " +
									"UNION SELECT 1, 3, id FROM juegos WHERE id = 14 " +
									"UNION SELECT 1, 4, id FROM juegos WHERE id = 7 " +
									"UNION SELECT 1, 5, id FROM juegos WHERE id = 2 " +
									"UNION SELECT 1, 6, id FROM juegos WHERE id = 1 " +
									"UNION SELECT 1, 7, id FROM juegos WHERE id = 15 " +
									"UNION SELECT 1, 8, id FROM juegos WHERE id = 6 " +
									"UNION SELECT 1, 9, id FROM juegos WHERE id = 16 " +
									"UNION SELECT 1, 10, id FROM juegos WHERE id = 13 " +
									"UNION SELECT 1, 11, id FROM juegos WHERE id = 18 " +
									"UNION SELECT 1, 12, id FROM juegos WHERE id = 4 " +
									"UNION SELECT 1, 13, id FROM juegos WHERE id = 8 " +
									"UNION SELECT 1, 14, id FROM juegos WHERE id = 3 " +
									"UNION SELECT 1, 15, id FROM juegos WHERE id = 19 " +
									"UNION SELECT 1, 16, id FROM juegos WHERE id = 20 " +
									"UNION SELECT 1, 17, id FROM juegos WHERE id = 22 " +
									"UNION SELECT 1, 18, id FROM juegos WHERE id = 9 " +
									"UNION SELECT 1, 19, id FROM juegos WHERE id = 21";			
			log.info(ins);
			stmt.execute(ins);						
		} catch (Exception e) {
			throw new PersistenciaException(e);
		} finally {
			UtilSQL.close(connection, stmt);
		}		
	}
}
