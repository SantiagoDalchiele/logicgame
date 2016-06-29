package uy.com.uma.logicgame.api.bean;

/**
 * Encapsula la información necesaria por la interface web para construir un modelo de la matriz de juego
 *
 * @author Santiago Dalchiele
 */
public class ParametrosJuego {

	private short cantDimensiones;
	private short cantValores;
	private String solucion;
	
	
	
	public ParametrosJuego() {		
	}
	
	public ParametrosJuego (short dim, short val, String sol) {
		setCantDimensiones(dim);
		setCantValores(val);
		setSolucion(sol);
	}
	
	
	public short getCantDimensiones() {
		return cantDimensiones;
	}
	public void setCantDimensiones(short cantDimensiones) {
		this.cantDimensiones = cantDimensiones;
	}
	public short getCantValores() {
		return cantValores;
	}
	public void setCantValores(short cantValores) {
		this.cantValores = cantValores;
	}
	public String getSolucion() {
		return solucion;
	}
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}
}
