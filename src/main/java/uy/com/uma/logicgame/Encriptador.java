package uy.com.uma.logicgame;

import uy.com.uma.comun.util.UtilFormato;
import uy.com.uma.logicgame.persistencia.seguridad.UtilSeguridad;

/**
 * Utilidad usada para encriptar la contraseña de un usuario y su clave según las reglas del sistema
 *
 * @author Santiago Dalchiele
 */
public class Encriptador {

	/**
	 * Recibe 2 parámetros el usuario y la clave, y opcionalmente el condimento de seguridad o security salt
	 * Muestra en pantalla la clave encriptada
	 */
	public static void main(String[] args) {
		if ((args == null) || (args.length < 2) || (UtilFormato.esNulo(args[0])) || (UtilFormato.esNulo(args[1])))
			usage();
		else {
			String clave;
			
			if (args.length == 3) {
				System.out.println("Usando [" + args[2] + "] como security salt");
				clave = UtilSeguridad.getClaveEncriptada(args[0], args[1], args[2]);
			} else
				clave = UtilSeguridad.getClaveEncriptada(args[0], args[1]);
			
			System.out.println(clave);
		}
	}

	
	
	/**
	 * Muestra el uso de la aplicación
	 */
	private static void usage() {
		System.out.println("Usage: Encriptador usuario clave [security_salt]");
	}
}
