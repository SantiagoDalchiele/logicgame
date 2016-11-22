package uy.com.uma.logicgame.api.bean;

import uy.com.uma.comun.util.UtilNumerico;

/**
 * Encapsula la información necesaria por la interface web para construir un modelo de la matriz de juego
 *
 * @author Santiago Dalchiele
 */
public class ParametrosJuego {

	private short cantDimensiones;
	private short cantValores;
	private String solucion;
	private int maxIntentos;
	
	
	
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
		this.setMaxIntentos();
	}
	public short getCantValores() {
		return cantValores;
	}
	public void setCantValores(short cantValores) {
		this.cantValores = cantValores;
		this.setMaxIntentos();
	}
	public String getSolucion() {
		return solucion;
	}
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}
	public int getMaxIntentos() {
		return this.maxIntentos;
	}
	
	
	
	/**
	 * El máximo de intentos es: piso (((cant_valores!) xy (cant_dims-1)) / max(cant_valores, cant_dims)))
	 */
	private void setMaxIntentos() {
		if ((cantValores > 0) && (cantDimensiones > 0)) {
			int dividendo = (int) Math.pow(UtilNumerico.factorial(cantValores), cantDimensiones-1);  
			this.maxIntentos = Math.floorDiv(dividendo, Math.max(cantValores, cantDimensiones));
		}
	}

}
