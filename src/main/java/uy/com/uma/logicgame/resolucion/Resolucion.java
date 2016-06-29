package uy.com.uma.logicgame.resolucion;

import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Encapsula los datos de resoluci�n de un juego
 *
 * @author Santiago Dalchiele
 */
public class Resolucion {	
	
	/** Tiene soluci�n el juego ? */
	private boolean tieneSolucion = true;
	
	/** La soluci�n es �nica */
	private boolean solucionUnica = true;
	
	/** Costo de resolver el juego */
	private long costo = -1;
	
	/** Solucion del juego */
	private String solucion = null;
	
	/** Matriz de resolucion */
	private MatrizDecision matriz;
	

	
	
	/**
	 * Utiliza los razonadores para intentar resolver un juego y obtener si tiene soluci�n o no y su costo
	 */
	public static Resolucion resolver (Juego juego) throws ValidadorJuegoException, ConfiguracionException {
		Resolucion r = new Resolucion();
		RazonadorXProgDinamica man = new RazonadorXProgDinamica(juego);
		r.setTieneSolucion(man.tieneSolucion());
		r.setSolucionUnica(man.solucionUnica());
		
		if (man.tieneSolucion()) {
			RazonadorXDeducciones xDeduc = new RazonadorXDeducciones(juego);
			r.setCosto(xDeduc.resolver());
			r.setSolucion(xDeduc.getSolucion());
			r.setMatriz(xDeduc.getMatriz());
		} else
			r.setCosto(-1);
		
		return r;
	}
	
	
	
	public boolean tieneSolucion() {
		return tieneSolucion;
	}
	public void setTieneSolucion(boolean tieneSolucion) {
		this.tieneSolucion = tieneSolucion;
	}
	public boolean solucionUnica() {
		return solucionUnica;
	}
	public void setSolucionUnica(boolean solucionUnica) {
		this.solucionUnica = solucionUnica;
	}
	public long getCosto() {
		return costo;
	}
	public void setCosto(long costo) {
		this.costo = costo;
	}
	public void setMatriz (MatrizDecision matriz) {
		this.matriz = matriz;
	}
	public MatrizDecision getMatriz() {
		return this.matriz;
	}
	

	
	/**
	 * Retorna la soluci�n obtenida de la forma: fila_cuadro.col_cuadro.fila_celda.col_celda,afirma/niega;.....
	 * Se debe invocar primero al m�todo resolver de esta clase
	 */
	public String getSolucion() {
		return solucion;
	}
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}
}
