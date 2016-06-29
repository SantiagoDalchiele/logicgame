package uy.com.uma.logicgame.nucleo.jaxb.juego;

/**
 * Excepción costumizada a los efectos de validar la estructura de un juego incluida en un archivo xml
 *
 * @author Santiago Dalchiele
 */
public class ValidadorJuegoException extends Exception {

	private static final long serialVersionUID = 1635508166136215855L;

	public ValidadorJuegoException() {
	}

	public ValidadorJuegoException(String arg0) {
		super(arg0);
	}

	public ValidadorJuegoException(Throwable arg0) {
		super(arg0);
	}

	public ValidadorJuegoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
