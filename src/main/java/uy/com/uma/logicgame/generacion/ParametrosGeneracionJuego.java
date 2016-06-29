package uy.com.uma.logicgame.generacion;

/**
 * Atributos que sirven de parámetros para el proceso de generación de juegos al azar
 *
 * @author Santiago Dalchiele
 */
public class ParametrosGeneracionJuego {

	private int cantDimensiones;
	private int cantValores;
	
	/** Entre porcentaje de afirma y niega suman 100%, es para distribuir el porcentaje al azar para elegir las pistas */
	private int porcAfirma;
	private int porcNiega;
	
	private long costoMin;
	private long costoMax;	
	private int timeout;

	
	
	public ParametrosGeneracionJuego() {		
	}
	
	public ParametrosGeneracionJuego(int cantDimensiones, int cantValores, int porcAfirma, int porcNiega, long costoMin,
			long costoMax, int timeout) {
		this.cantDimensiones = cantDimensiones;
		this.cantValores = cantValores;
		this.porcAfirma = porcAfirma;
		this.porcNiega = porcNiega;
		this.costoMin = costoMin;
		this.costoMax = costoMax;
		this.timeout = timeout;
	}



	public int getCantDimensiones() {
		return cantDimensiones;
	}
	public void setCantDimensiones(int cantDimensiones) {
		this.cantDimensiones = cantDimensiones;
	}
	public int getCantValores() {
		return cantValores;
	}
	public void setCantValores(int cantValores) {
		this.cantValores = cantValores;
	}
	public int getPorcAfirma() {
		return porcAfirma;
	}
	public void setPorcAfirma(int porcAfirma) {
		this.porcAfirma = porcAfirma;
		this.porcNiega = (porcAfirma < 100) ? (100 - porcAfirma) : 0;		
	}
	public int getPorcNiega() {
		return porcNiega;
	}
	public long getCostoMin() {
		return costoMin;
	}
	public void setCostoMin(long costoMin) {
		this.costoMin = costoMin;
	}
	public long getCostoMax() {
		return costoMax;
	}
	public void setCostoMax(long costoMax) {
		this.costoMax = costoMax;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}	
}
