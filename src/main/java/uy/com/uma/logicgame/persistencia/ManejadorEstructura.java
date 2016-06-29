package uy.com.uma.logicgame.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.uma.comun.util.UtilSQL;
import uy.com.uma.logicgame.api.conf.ConfiguracionLoadHelper;
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
	private List<String> tablas = new ArrayList<String>();
	
	/** Lista de permisos/privilegios */
	private List<String> permisos = new ArrayList<String>();
	
	
	
	/**
	 * Agrega a la lista de tablas los scripts de creación de tablas
	 */
	public ManejadorEstructura() {
		tablas.add("CREATE TABLE IF NOT EXISTS idiomas (" + 
						"id CHAR(2) PRIMARY KEY," +
						"nombre VARCHAR(64) NOT NULL," + 
						"icono VARCHAR(256) NOT NULL)");
		
		tablas.add("CREATE TABLE IF NOT EXISTS literales (" +
						"id BIGINT," +
						"idioma CHAR(2) REFERENCES idiomas (id)," +
						"texto TEXT NOT NULL," +
						"PRIMARY KEY (id, idioma))");
		
		tablas.add("CREATE TABLE IF NOT EXISTS juegos (" +
						"id INTEGER PRIMARY KEY," +
						"titulo BIGINT NOT NULL," +
						"texto BIGINT NOT NULL," +
						"costo INTEGER NOT NULL," +
						"cant_dims SMALLINT," +
						"cant_valores SMALLINT," +
						"solucion TEXT)");
		
		tablas.add("CREATE TABLE IF NOT EXISTS juegosxidioma (" +
						"id_juego INTEGER REFERENCES juegos (id)," +
						"idioma CHAR(2) REFERENCES idiomas (id)," +
						"def_juego TEXT," +
						"PRIMARY KEY (id_juego, idioma))");
		
		tablas.add("CREATE TABLE IF NOT EXISTS dimensiones (" +
						"id_juego INTEGER REFERENCES juegos (id)," +
						"sec SMALLINT," +
						"id BIGINT NOT NULL," +
						"PRIMARY KEY (id_juego, sec))");
		
		tablas.add("CREATE TABLE IF NOT EXISTS valores (" +
						"id_juego INTEGER," +
						"sec_dim SMALLINT," +
						"sec SMALLINT," +
						"valor BIGINT NOT NULL," +
						"PRIMARY KEY (id_juego, sec_dim, sec)," +
						"FOREIGN KEY (id_juego, sec_dim) REFERENCES dimensiones (id_juego, sec))");
		
		tablas.add("CREATE TABLE IF NOT EXISTS pistas_juego (" +
						"id_juego INTEGER REFERENCES juegos (id)," +
						"sec SMALLINT," +
						"texto BIGINT NOT NULL," +
						"PRIMARY KEY (id_juego, sec))");
		
		tablas.add("CREATE TABLE IF NOT EXISTS pistas (" +
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
		
		tablas.add("CREATE TABLE IF NOT EXISTS rutas (" +
						"id SMALLINT," +
						"nivel INTEGER," +
						"id_juego INTEGER REFERENCES juegos (id)," +
						"hoja_estilo VARCHAR(256)," +
						"PRIMARY KEY (id, nivel))");
		
		tablas.add("CREATE TABLE IF NOT EXISTS usuarios (" +
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
		
		permisos.add("GRANT SELECT, INSERT, UPDATE ON idiomas TO lg");
		permisos.add("GRANT SELECT, INSERT, UPDATE ON literales TO lg");
		permisos.add("GRANT SELECT, INSERT, UPDATE, DELETE ON juegos TO lg");
		permisos.add("GRANT SELECT, INSERT, UPDATE, DELETE ON juegosxidioma TO lg");
		permisos.add("GRANT SELECT, INSERT, DELETE ON dimensiones TO lg");
		permisos.add("GRANT SELECT, INSERT, DELETE ON valores TO lg");
		permisos.add("GRANT SELECT, INSERT, DELETE ON pistas_juego TO lg");
		permisos.add("GRANT SELECT, INSERT, DELETE ON pistas TO lg");
		permisos.add("GRANT SELECT, INSERT, UPDATE, DELETE ON rutas TO lg");
		permisos.add("GRANT SELECT, INSERT, UPDATE ON usuarios TO lg");
		
		permisos.add("GRANT SELECT ON juegos TO lgweb");
		permisos.add("GRANT SELECT ON juegosxidioma TO lgweb");
		permisos.add("GRANT SELECT ON rutas TO lgweb");
		permisos.add("GRANT SELECT, INSERT, UPDATE ON usuarios TO lgweb");
		permisos.add("GRANT SELECT ON idiomas TO lgweb");
	}	
	
	
	
	/** 
	 * Crea los roles usados por el sistema logic-game 
	 */
	@Override
	public void crearRoles() throws PersistenciaException {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;		
		
		try {
			connection = SessionFactoryUtil.getConnection();
			stmt = connection.createStatement();
			String sel = "SELECT rolname FROM pg_roles WHERE rolname IN ('lg', 'lgweb')";
			log.info(sel);
			rs = stmt.executeQuery(sel);			
			
			if (rs.next())
				throw new PersistenciaException("Ya existe al menos uno de los dos roles [lg, lgweb]");
			else {
				final ConfiguracionLoadHelper conf = ConfiguracionLoadHelper.getInstancia();
				final String createRolAdm = "CREATE ROLE lg LOGIN ENCRYPTED PASSWORD '" + conf.getClaveRolAdm()  + "' " +
											"NOSUPERUSER NOINHERIT NOCREATEDB NOCREATEROLE NOREPLICATION";
				final String createRolWeb = "CREATE ROLE lgweb LOGIN ENCRYPTED PASSWORD '" + conf.getClaveRolWeb() + "' " +
											"NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;";
				log.info(createRolAdm);
				stmt.execute(createRolAdm);
				log.info(createRolWeb);
				stmt.execute(createRolWeb);
			}
		} catch (Exception e) {
			throw new PersistenciaException(e);
		} finally {
			UtilSQL.close(connection, stmt, rs);
		}
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
			
			for (String sent : tablas) {
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
	 * Asigna los permisos para cada tabla y rol 
	 */
	@Override
	public void asignarPermisos() throws PersistenciaException {
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = SessionFactoryUtil.getConnection();
			stmt = connection.createStatement();
			
			for (String sent : permisos) {
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
