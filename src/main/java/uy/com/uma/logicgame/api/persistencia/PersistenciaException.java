package uy.com.uma.logicgame.api.persistencia;

/**
 * Excepcion costumizada para el paquete de persistencia
 *
 * @author Santiago Dalchiele
 */
public class PersistenciaException extends Exception {
	
	private static final long serialVersionUID = 7032257182850288215L;
	

	public PersistenciaException() {
	}

	public PersistenciaException(String msg) {
		super(msg);
	}

	public PersistenciaException(Throwable cause) {
		super(cause);
	}

	public PersistenciaException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
