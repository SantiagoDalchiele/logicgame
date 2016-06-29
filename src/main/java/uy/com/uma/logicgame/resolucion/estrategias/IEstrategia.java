package uy.com.uma.logicgame.resolucion.estrategias;

import java.util.Collection;

import uy.com.uma.logicgame.resolucion.EstrategiaException;
import uy.com.uma.logicgame.resolucion.modelo.DatoCuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;

/**
 * Define los métodos a implementar por cada estrategia de resolución de un juego de lógica
 *
 * @author Santiago Dalchiele
 */
public interface IEstrategia {

	/** Setea el orden dentro de la resolución en que debe ser ejecutado esta estrategia */
	void setOrden (short orden);
	
	/** Retorna el orden dentro de la resolución en que debe ser ejecutado esta estrategia */
	short getOrden();
	
	/** Setea el costo de cada inferencia realizada */
	void setCosto (int costo);
	
	/** Retorna el costo de cada inferencia realizada */
	int getCosto();
	
	/** Aplica la estrategia para resolver el juego de lógica */
	Collection<DatoCuadroDecision> aplicar (MatrizDecision matriz) throws EstrategiaException;
}
