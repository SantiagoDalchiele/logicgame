package uy.com.uma.logicgame.api;

/**
 * Excepcion costumizada generica
 *
 * @author Santiago Dalchiele
 */
public class LogicGameException extends Exception {

	private static final long serialVersionUID = -3906398880214648715L;
	

	public LogicGameException() {
	}

	public LogicGameException(String arg0) {
		super(arg0);
	}

	public LogicGameException(Throwable arg0) {
		super(arg0);
	}

	public LogicGameException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LogicGameException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}