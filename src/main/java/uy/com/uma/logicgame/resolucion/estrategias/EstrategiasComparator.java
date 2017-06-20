package uy.com.uma.logicgame.resolucion.estrategias;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparador de estrategias, define el orden por su método orden
 *
 * @author Santiago Dalchiele
 */
public class EstrategiasComparator implements Comparator<IEstrategia>, Serializable {

	private static final long serialVersionUID = 7200600914257870344L;
	

	@Override
	public int compare(IEstrategia e1, IEstrategia e2) {
		return (e1.getOrden() < e2.getOrden() ? -1 : (e1.getOrden() > e2.getOrden() ? 1 : 0));
	}

	@Override
	public boolean equals(Object obj) {		
		return this == obj;
	}

	@Override
	public int hashCode() {
		return 97 + super.hashCode(); 
	}	
}
