package uy.com.uma.logicgame.resolucion.modelo;

import uy.com.uma.logicgame.api.IValoresCuadroDecision;

/**
 * Cuadro de decisión de un juego de lógica definido por el cruzamiento de dos dimensiones
 *
 * @author Santiago Dalchiele
 */
public class CuadroDecision {

	private Dimension dimensionXColumna;
	private Dimension dimensionXFila;
	private DatoCuadroDecision [][] cuadro;
	
	
	
	/**
	 * Constructor, a partir de las dimensiones que se relacionan, construye el cuadro de decisión
	 * @param dimXCol Dimensión por columna
	 * @param dimXFila Dimensión por fila
	 */
	public CuadroDecision (Dimension dimXCol, Dimension dimXFila) {
		this.dimensionXColumna = dimXCol;
		this.dimensionXFila = dimXFila;
		int cantCols = dimXCol.getValores().size();
		cuadro = new DatoCuadroDecision [cantCols][cantCols];
		
		for (int i = 0; i < cantCols; i++) {
			ValorDimension valorXCol = dimXCol.getValoresXSec().get(i);
			
			for (int j = 0; j < cantCols; j++) {
				ValorDimension valorXFila = dimXFila.getValoresXSec().get(j);
				cuadro[i][j] = new DatoCuadroDecision(dimXCol, dimXFila, valorXCol, valorXFila);
			}
		}
	}
	
	
	
	public Dimension getDimensionXColumna() {
		return dimensionXColumna;
	}
	public Dimension getDimensionXFila() {
		return dimensionXFila;
	}
	public void setValor (short fila, short col, short valor) {
		cuadro[fila][col].setValor(valor);
	}
	public void setValor (ValorDimension valXFila, ValorDimension valXCol, short valor) {
		setValor(valXFila.getSec(), valXCol.getSec(), valor);
	}
	public DatoCuadroDecision getDato (short fila, short col) {
		return cuadro[fila][col];
	}
	public short getValor (short fila, short col) {
		return getDato(fila, col).getValor();
	}	
	public DatoCuadroDecision getDato (ValorDimension valXFila, ValorDimension valXCol) {
		return getDato(valXFila.getSec(), valXCol.getSec());
	}
	public short getValor (ValorDimension valXFila, ValorDimension valXCol) {
		return getDato(valXFila, valXCol).getValor();
	}
	public int getCantValores() {
		return dimensionXFila.getValores().size();
	}
		
	@Override
	public String toString() {
		return dimensionXFila.getId() + " x " + dimensionXColumna.getId() + " [" + dimensionXFila.getFila() + "," + dimensionXColumna.getCol() + "]";
	}



	/**
	 * Retorna TRUE si el cuadro tiene tantas celdas afirmativas como posibles valores
	 */
	public boolean resuelto() {
		int cantValores = dimensionXFila.getValores().size();
		int cantAfirma = 0;
		
		for (int i = 0; i < dimensionXFila.getValores().size(); i++)
			for (int j = 0; j < dimensionXColumna.getValores().size(); j++)
				if (cuadro[i][j].getValor() == IValoresCuadroDecision.AFIRMA)
					cantAfirma++;
		
		return (cantValores == cantAfirma);
	}
	
	
	
	/**
	 * Retorna TRUE si todo el cuadro tiene tantas celdas afirmativas como posibles valores y no tiene celdas vacías
	 */
	public boolean resueltoCompleto() {
		int cantValores = dimensionXFila.getValores().size();
		int cantAfirma = 0;
		
		for (int i = 0; i < dimensionXFila.getValores().size(); i++)
			for (int j = 0; j < dimensionXColumna.getValores().size(); j++)
				if (cuadro[i][j].getValor() == IValoresCuadroDecision.AFIRMA)
					cantAfirma++;
				else if (cuadro[i][j].getValor() == IValoresCuadroDecision.VACIA)
					return false;
		
		return (cantValores == cantAfirma);
	}
	
	
	
	/**
	 * Pone todas las celdas del cuadro de decisión en VACIA
	 */
	public void vaciar() {
		short cantValores = (short) dimensionXFila.getValores().size();
		
		for (short i = 0; i < cantValores; i++)
			for (short j = 0; j < cantValores; j++)				
				cuadro[i][j].setValor(IValoresCuadroDecision.VACIA);
	}
	
	
	
	/**
	 * Dado dos cuadros de decisión con las mismas dimensiones por fila y columna y los mismos valores
	 * copia los valores del cuadro pasado como parámetro a este cuadro.
	 */
	public void copiar (CuadroDecision c) {
		short cantValores = (short) dimensionXFila.getValores().size();
		
		for (short i = 0; i < cantValores; i++)
			for (short j = 0; j < cantValores; j++)				
				cuadro[i][j].setValor(c.getValor(i, j));
	}
}
