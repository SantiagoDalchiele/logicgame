package uy.com.uma.logicgame.resolucion.estrategias;

import java.util.ArrayList;
import java.util.Collection;

import uy.com.uma.logicgame.api.IValoresCuadroDecision;
import uy.com.uma.logicgame.resolucion.ContradiccionException;
import uy.com.uma.logicgame.resolucion.EstrategiaException;
import uy.com.uma.logicgame.resolucion.modelo.CuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.DatoCuadroDecision;
import uy.com.uma.logicgame.resolucion.modelo.Dimension;
import uy.com.uma.logicgame.resolucion.modelo.MatrizDecision;
import uy.com.uma.logicgame.resolucion.modelo.ValorDimension;


/**
 * Encapsula los métodos getters/setters asociados a las propiedades de orden y costo.
 * Métodos utiles o comunes a todas las estrategias
 *
 * @author Santiago Dalchiele
 */
abstract class EstrategiaAbstracta implements IEstrategia, IValoresCuadroDecision {
	
	protected static final short CERO = 0;
	
	protected short orden;
	protected int costo;
	protected Collection<DatoCuadroDecision> cambios = new ArrayList<DatoCuadroDecision>();
	protected short cantValores;
	

	protected abstract void aplicarInt (MatrizDecision matriz) throws EstrategiaException;
	
	
	@Override
	public void setOrden(short orden) {
		this.orden = orden;
	}

	@Override
	public short getOrden() {
		return this.orden;
	}

	@Override
	public void setCosto(int costo) {
		this.costo = costo;
	}

	@Override
	public int getCosto() {
		return this.costo;
	}

	
	/**
	 * Aplica la estrategia en forma abstracta.
	 * Limpia la colección de cambios, setea la cantidad de valores que tiene cada dimensión.
	 * Invoca al procedimiento aplicarInt(matriz).
	 * Retorna la colección de cambios
	 * 
	 * @param matriz
	 * @return
	 * @see uy.com.uma.logicgame.resolucion.estrategias.IEstrategia#aplicar(uy.com.uma.logicgame.resolucion.modelo.MatrizDecision)
	 */
	@Override
	public Collection<DatoCuadroDecision> aplicar(MatrizDecision matriz) throws EstrategiaException {
		this.cambios.clear();
		this.cantValores = (short) matriz.getCantValores();
		
		if (!matriz.resueltoCompleto())
			aplicarInt(matriz);
		
		return this.cambios;
	}
	
	
	
	/**
	 * Setea el valor en el cuadro de decisión por fila y columna y agrega el cambio a la colección de cambios
	 * @param fila
	 * @param col
	 * @param valor
	 * @param cuadro
	 */
	protected void addCambio (short fila, short col, short valor, CuadroDecision cuadro) throws ContradiccionException {		
		if (cuadro.getValor(fila, col) == VACIA) {			
			Dimension dimXFila = cuadro.getDimensionXFila();
			Dimension dimXCol = cuadro.getDimensionXColumna();
			ValorDimension valorXFila = dimXFila.getValoresXSec().get(fila);
			ValorDimension valorXCol = dimXCol.getValoresXSec().get(col);		
			DatoCuadroDecision cambio = new DatoCuadroDecision(dimXCol, dimXFila, valorXCol, valorXFila);
			cambio.setValor(valor);
			this.cambios.add(cambio);
			cuadro.setValor(fila, col, valor);
		} else
			if (cuadro.getValor(fila, col) != valor)
				throw new ContradiccionException("Cuadro: " + cuadro + 
						" [" + fila + "," + col + "]=" + (cuadro.getValor(fila, col) == AFIRMA ? "AFIRMA" : "NIEGA") + 
						", valor a asignar=" + (valor == AFIRMA ? "AFIRMA" : "NIEGA"));
	}
}
