package uy.com.uma.logicgame.test;

import uy.com.uma.logicgame.persistencia.seguridad.UtilSeguridad;

/**
 * Test de la utileria de seguridad
 *
 * @author Santiago Dalchiele
 */
public class TestSeguridad {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/** @deprecated usar variable de ambiente LOGICGAME_HOME=c:/santiago/git/logicgame/conf */
		//System.setProperty(IConfiguracionConstantes.CONF_SYSTEM_VAR, "c:/santiago/git/logicgame/conf/logicgame-conf.xml");
		String clave = UtilSeguridad.getClaveEncriptada("santiago", "secret");
		System.out.println(clave.length());
		System.out.println("[" + clave + "]");
		
		if ("qCNoSvnpblB+Mttl/Ms0chEB8CV2A0y5MZxYuYfHbPaghKq0rL69BnkjtWpeutQS".equals(clave))
			System.out.println("si son iguales");
		else
			System.out.println("mmmm");
		
		clave = UtilSeguridad.getClaveEncriptada("santiago algo mucho mas largo", "secret y la clave tambien");
		System.out.println(clave.length());
		System.out.println("[" + clave + "]");
		
		clave = UtilSeguridad.getClaveEncriptada("Juan", "secret");
		System.out.println(clave.length());
		System.out.println("[" + clave + "]");
	}

}
