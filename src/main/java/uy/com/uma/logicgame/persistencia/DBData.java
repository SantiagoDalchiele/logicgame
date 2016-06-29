package uy.com.uma.logicgame.persistencia;

import java.net.URI;

/**
 * Encapsula la info de conexión a la base de datos: usuario, password y url
 *
 * @author Santiago Dalchiele
 */
class DBData {
	
	private String username;
	private String password;
	private String url;
	
	
	public DBData (URI dbUri) throws ClassNotFoundException {
		username = dbUri.getUserInfo().split(":")[0];
	    password = dbUri.getUserInfo().split(":")[1];
	    String esquema = null;		    
	    
	    if ("postgres".equals(dbUri.getScheme())) {
	    	Class.forName("org.postgresql.Driver");
	    	esquema = "postgresql";
	    }
	    
	    url = "jdbc:" + esquema + "://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
	}
	
	
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getUrl() {
		return url;
	}		
}
