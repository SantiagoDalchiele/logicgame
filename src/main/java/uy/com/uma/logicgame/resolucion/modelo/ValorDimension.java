package uy.com.uma.logicgame.resolucion.modelo;

/**
 * Define un valor de una dimensión, su identificador y su secuencial dentro del cuadro de decision 0..(m-1)
 *
 * @author Santiago Dalchiele
 * @see CuadroDecision
 */
public class ValorDimension {

	private String id;
	private short sec;
	
	
	
	public ValorDimension(String id, short sec) {
		this.id = id;
		this.sec = sec;
	}
	
	
	
	public String getId() {
		return id;
	}
	public short getSec() {
		return sec;
	}
	public String toString() {
		return getId() + " (" + getSec() + ")";
	}
}