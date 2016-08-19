package uy.com.uma.logicgame.resolucion.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import uy.com.uma.comun.util.UtilString;
import uy.com.uma.logicgame.api.IValoresCuadroDecision;
import uy.com.uma.logicgame.resolucion.MatrizDecisionBuilder;

/**
 * Matriz de decision de un juego de lógica
 * En las columnas se enumeran las dimensiones 2 en delante
 * En las filas: la primer fila es la dimensión 1, luego se enumeran desde la dimensión N hasta la dimensión 3
 *
 * @author Santiago Dalchiele
 * @see MatrizDecisionBuilder
 */
public class MatrizDecision implements IValoresCuadroDecision {

	private Map<String, Dimension> dimensionesXFila = new HashMap<String, Dimension>();
	private Map<String, Dimension> dimensionesXCols = new HashMap<String, Dimension>();
	private List<Dimension> dimsXFila = new ArrayList<Dimension>();
	private List<Dimension> dimsXCols = new ArrayList<Dimension>();
	private CuadroDecision [][] matriz;
	private int cantValores = 0;
	private Map<Short, Dimension> dimensionesXNro = new HashMap<Short, Dimension>();
	
	
	
	/**
	 * Construye la matriz a partir de las listas de dimensiones bien formadas
	 * @param dimsXFila dimensiones por fila
	 * @param dimsXCols dimensiones por columna
	 */
	public MatrizDecision (List<Dimension> dimsXFila, List<Dimension> dimsXCols) {
		for (Dimension d : dimsXFila) {
			dimensionesXFila.put(d.getId(), d);
			this.dimsXFila.add(d);
			this.dimensionesXNro.put(d.getNro(), d);
			
			if (this.cantValores == 0)
				this.cantValores = d.getValores().size();
		}
		
		for (Dimension d : dimsXCols) {
			dimensionesXCols.put(d.getId(), d);
			this.dimsXCols.add(d);
			
			if (! this.dimensionesXNro.containsKey(d.getNro()))
				this.dimensionesXNro.put(d.getNro(), d);
		}
	
		int cantCols = dimsXFila.size();
		matriz = new CuadroDecision [cantCols][cantCols];
		
		for (int i = 0; i < cantCols; i++) {
			Dimension dimXFila = dimsXFila.get(i);

			for (int j = 0; j < (cantCols-i); j++) 			
				matriz[i][j] = new CuadroDecision(dimsXCols.get(j), dimXFila);
		}
	}



	public Map<String, Dimension> getDimensionesXFila() {
		return dimensionesXFila;
	}
	public Map<String, Dimension> getDimensionesXCols() {
		return dimensionesXCols;
	}
	public Dimension getPrimerDimensionXFila() {
		return dimsXFila.get(0);
	}
	public Dimension getPrimerDimensionXCols() {
		return dimsXCols.get(0);
	}
	public Dimension getDimensionXNro (short nro) {
		return dimensionesXNro.get(nro);
	}

	
	public CuadroDecision getCuadro (Dimension fila, Dimension col) {
		return getCuadro(fila.getFila(), col.getCol());
	}
	public CuadroDecision getCuadro (short fila, short col) {
		return matriz[fila][col];
	}
	public CuadroDecision getPrimerCuadro() {
		return matriz[0][0];
	}

	
	public DatoCuadroDecision getDato (short filaM, short colM, short filaC, short colC) {
		return getCuadro(filaM, colM).getDato(filaC, colC);
	}
	public DatoCuadroDecision getDato (Dimension dimXFila, Dimension dimXCol, ValorDimension valorXFila, ValorDimension valorXCol) {
		return getCuadro(dimXFila, dimXFila).getDato(valorXFila, valorXCol);
	}
	
	public short getValor (short filaM, short colM, short filaC, short colC) {
		return getCuadro(filaM, colM).getValor(filaC, colC);
	}
	public short getValor (Dimension dimXFila, Dimension dimXCol, ValorDimension valorXFila, ValorDimension valorXCol) {
		return getCuadro(dimXFila, dimXFila).getValor(valorXFila, valorXCol);
	}
	
	public void setValor (short filaM, short colM, short filaC, short colC, short valor) {
		getCuadro(filaM, colM).setValor(filaC, colC, valor);
	}
	public void setValor (Dimension dimXFila, Dimension dimXCol, ValorDimension valorXFila, ValorDimension valorXCol, short valor) {
		getCuadro(dimXFila, dimXCol).setValor(valorXFila, valorXCol, valor);
	}
	
	public int getCantValores() {
		return this.cantValores;
	}	

	

	/**
	 * Retorna TRUE si los cuadros de la primer fila de la matriz no tienen celdas vacías o 
	 * si los cuadros de la primer columna de la matriz no tienen celdas vacías
	 */
	public boolean resuelto() {
		Dimension primerFila = dimsXFila.get(0);
		boolean retorno = true;
		
		for (Dimension d : dimensionesXCols.values())
			if (! getCuadro(primerFila, d).resuelto())
				retorno = false;
		
		if (retorno)
			return true;
		else {
			Dimension primerCol = dimsXCols.get(0);
			
			for (Dimension d : dimensionesXFila.values())
				if (! getCuadro(d, primerCol).resuelto())
					return false;
			
			return true;
		}
	}
	
	
	
	/**
	 * Retorna TRUE si todos los cuadros de la matriz no tienen celdas vacías
	 */
	public boolean resueltoCompleto() {
		for (Dimension fila : dimensionesXFila.values()) {			
			for (Dimension col : dimensionesXCols.values()) {
				CuadroDecision cuadro = getCuadro(fila, col);
				
				if ((cuadro != null) && (! cuadro.resueltoCompleto()))
					return false;
			}					
		}
		
		return true;
	}
	
	
	
	/**
	 * Pone todas las celdas de los cuadros de la matriz en valor VACIA
	 */
	public void vaciar() {
		int dimMatriz = dimensionesXCols.size();
		
		for (int i = 0; i < dimMatriz; i++)
			for (int j = 0; j < dimMatriz; j++)
				if (matriz[i][j] != null)
					matriz[i][j].vaciar();
	}
	
	
	
	/**
	 * Dadas dos matrices de decisión con las mismas dimensiónes y valores, copia en esta matriz los valores de cada cuadro
	 * de decisión de la matriz pasada como parámetro
	 */
	public void copiar (MatrizDecision m) {
		short dimMatriz = (short) dimensionesXCols.size();
		
		for (short i = 0; i < dimMatriz; i++)
			for (short j = 0; j < dimMatriz; j++)
				if (matriz[i][j] != null)
					matriz[i][j].copiar(m.getCuadro(i, j));
	}

	
	
	/**
	 * Retorna los valores de la matriz de forma amigable
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int largoMaxIdVals = 0;
		
		/** Calcula el largo máximo de los identificadores de los valores de las dimensiones incluidas en la matriz */
		for (Dimension fila : dimensionesXFila.values())
			for (ValorDimension vd : fila.getValores().values()) {
				int largo = vd.getId().length();
				
				if (largo > largoMaxIdVals)
					largoMaxIdVals = largo;			
			}
		
		for (Dimension col : dimensionesXCols.values())
			for (ValorDimension vd : col.getValores().values()) {
				int largo = vd.getId().length();
				
				if (largo > largoMaxIdVals)
					largoMaxIdVals = largo;			
			}
		
		/** Titulos de los valores de las dimensiones en las columnas de la matriz */
		sb.append(UtilString.getNVeces(" ", largoMaxIdVals+1));
		sb.append("|");
		
		for (Iterator<Dimension> it = dimsXCols.iterator(); it.hasNext();) {
			for (ValorDimension vd : it.next().getValoresXSec()) {
				sb.append(vd.getId());
				sb.append(UtilString.getNVeces(" ", largoMaxIdVals - vd.getId().length() + 1));
			}
			
			sb.append((it.hasNext() ? "|" : "\n"));
		}
		
		sb.append(UtilString.getNVeces("-", ((largoMaxIdVals + 1) * (dimsXCols.size() * this.cantValores + 1)) + (dimsXCols.size())));
		sb.append("\n");
		
		/** Fila a fila la matriz */
		short fila = 0;
		
		for (Iterator<Dimension> it = dimsXFila.iterator(); it.hasNext(); fila++) {			
			for (ValorDimension vd : it.next().getValoresXSec()) {
				sb.append(vd.getId());
				sb.append(UtilString.getNVeces(" ", largoMaxIdVals - vd.getId().length() + 1));
				sb.append("|");
				short col = 0;
				
				for (Iterator<Dimension> it3 = dimensionesXCols.values().iterator(); it3.hasNext(); col++) {
					Dimension dc = it3.next();
					
					if (matriz[fila][col] != null) {
						for (ValorDimension vds :  dc.getValoresXSec()) {
							short colC = vds.getSec();
							short v = getValor(fila, col, vd.getSec(), colC);
							String txtVal = (v == VACIA ? " " : (v == AFIRMA ? "S" : "N"));
							sb.append(txtVal);
							sb.append(UtilString.getNVeces(" ", largoMaxIdVals));
						}
					} else
						for (int h = 0; h < dc.getValores().size(); h++)
							sb.append(UtilString.getNVeces(" ", largoMaxIdVals+1));							
					
					sb.append(it3.hasNext() ? "|" : "\n");
				}
			}			
			
			if (it.hasNext()) {
				sb.append(UtilString.getNVeces("-", ((largoMaxIdVals + 1) * (dimsXCols.size() * this.cantValores + 1)) + (dimsXCols.size())));
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}	
}
