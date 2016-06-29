package uy.com.uma.logicgame.api.conf;

/**
 * Excepcion costumizada para el paquete
 *
 * @author Santiago Dalchiele
 */
public class ConfiguracionException extends Exception {

	private static final long serialVersionUID = -6941962793537661411L;

	
	
	/**
	 * 
	 */
	public ConfiguracionException() {
	}

	/**
	 * @param arg0
	 */
	public ConfiguracionException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public ConfiguracionException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ConfiguracionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public ConfiguracionException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
