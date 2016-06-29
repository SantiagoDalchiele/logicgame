package uy.com.uma.logicgame.resolucion;

/**
 * Excepcion costumizada para las clases del paquete
 *
 * @author Santiago Dalchiele
 */
public class EstrategiaException extends Exception {

	private static final long serialVersionUID = -2065906534490413961L;

	public EstrategiaException() {
		super();
	}

	public EstrategiaException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public EstrategiaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EstrategiaException(String arg0) {
		super(arg0);
	}

	public EstrategiaException(Throwable arg0) {
		super(arg0);
	}
}