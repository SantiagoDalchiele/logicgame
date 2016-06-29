package uy.com.uma.logicgame.resolucion.estrategias;

import java.util.Comparator;

/**
 * Comparador de estrategias, define el orden por su método orden
 *
 * @author Santiago Dalchiele
 */
public class EstrategiasComparator implements Comparator<IEstrategia> {

	@Override
	public int compare(IEstrategia e1, IEstrategia e2) {
		return (e1.getOrden() < e2.getOrden() ? -1 : (e1.getOrden() > e2.getOrden() ? 1 : 0));
	}

	@Override
	public boolean equals(Object obj) {		
		return this == obj;
	}
}
