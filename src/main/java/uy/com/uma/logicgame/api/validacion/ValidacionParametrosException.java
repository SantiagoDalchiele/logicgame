package uy.com.uma.logicgame.api.validacion;

import uy.com.uma.logicgame.api.persistencia.PersistenciaException;

/**
 * Excepcipon costumizada para la validación de parametros
 *
 * @author Santiago Dalchiele
 */
public class ValidacionParametrosException extends PersistenciaException {

	private static final long serialVersionUID = -1627446707593969969L;

	
	public ValidacionParametrosException() {
	}

	public ValidacionParametrosException(String msg) {
		super(msg);
	}

	public ValidacionParametrosException(Throwable cause) {
		super(cause);
	}

	public ValidacionParametrosException(String msg, Throwable cause) {
		super(msg, cause);
	}
}