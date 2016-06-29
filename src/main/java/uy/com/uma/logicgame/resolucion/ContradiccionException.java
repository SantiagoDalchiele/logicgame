package uy.com.uma.logicgame.resolucion;

/**
 * Implementa una excepción para cuando se intenta setear una celda de un cuadro de decisión con un valor contradictorio, 
 * la celda tenía valor afirmativo y se niega, o la celda tenía valor negado y se afirma. 
 *
 * @author Santiago Dalchiele
 */
public class ContradiccionException extends EstrategiaException {

	private static final long serialVersionUID = -4935120670020313994L;

	
	
	public ContradiccionException() {
	}

	public ContradiccionException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ContradiccionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ContradiccionException(String arg0) {
		super(arg0);
	}

	public ContradiccionException(Throwable arg0) {
		super(arg0);
	}

}
