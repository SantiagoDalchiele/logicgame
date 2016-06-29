package uy.com.uma.logicgame.resolucion.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Implementa un árbol cuyo nodo tiene un valor (String) y tien n-hijos
 *
 * @author Santiago Dalchiele
 */
public class ArbolValores {

	/** Valor del nodo */
	private String valor;
	
	/** Hijos */
	private List<ArbolValores> hijos = new ArrayList<ArbolValores>();
	
	
	
	/**
	 * Constructor por defecto, es necesario el valor del nodo
	 */
	public ArbolValores (String valor) {
		this.valor = valor;
	}
	
	
	
	public List<ArbolValores> hijos() {
		return this.hijos;
	}
	public String getValor() {
		return this.valor;
	}
	
	
	
	/**
	 * Retorna la profundidad
	 */
	public int profundidad() {
		int max = 0;
		
		for (ArbolValores hijo : hijos) {
			int prof = hijo.profundidad();
			
			if (prof > max)
				max = prof;
		}
		
		return 1 + max;
	}

	
	
	/**
	 * Retorna la cantidad de nodos del árbol
	 */
	public int size() {
		int acum = 1;
		
		for (ArbolValores hijo : hijos)
			acum += hijo.size();
		
		return acum; 
	}
	
	
	
	/**
	 * Retorna TRUE si ningún nodo del árbol tiene más de un hijo
	 */
	public boolean esLista() {
		if (hijos.isEmpty())
			return true;
		else if (hijos.size() > 1)
			return false;
		else
			return hijos.get(0).esLista();
	}

	
	/**
	 * Retorna TRUE si cada rama del árbol tiene la misma cantidad de hijos que sus hermanos
	 */
	public boolean completo() {
		if (hijos.isEmpty())
			return true;
		else {
			int cantHijos = hijos.get(0).hijos().size();
			
			for (int i = 1; i < hijos.size(); i++)
				if (hijos.get(i).hijos().size() != cantHijos)
					return false;
			
			for (ArbolValores hijo : hijos)
				if (!hijo.completo())
					return false;
			
			return true;
		}
	}
	
	
	
	/**
	 * Retorna la lista de candidatos, cada rama es un candidato, los valores de cada nodo son la lista de candidato
	 */
	public List<List<String>> getCandidatos() {
		List<List<String>> candidatos = new ArrayList<List<String>>();
		
		if (completo()) {		
			if (hijos.isEmpty()) {
				List<String> item = new ArrayList<String>();
				item.add(getValor());
				candidatos.add(item);
			} else {
				for (ArbolValores hijo : hijos) {
					List<List<String>> candidatoHijo = hijo.getCandidatos();
					
					for (List<String> valsHijo : candidatoHijo) {
						List<String> item = new ArrayList<String>();
						item.add(getValor());
						item.addAll(valsHijo);
						candidatos.add(item);
					}
				}
			}
		}

		return candidatos;
	}
	
	
	
	/**
	 * Retorna todos los valores en determinado nivel del árbol, el nivel 0 es la raiz, y profundidad-1 son las hojas
	 */
	public Map<String,String> getValoresEnNivel (int nivel) {
		Map<String,String> ret = new HashMap<String,String>();
		
		if ((nivel == 0) && (!ret.containsKey(valor)))
			ret.put(valor, valor);
		else
			for (ArbolValores hijo : hijos)
				ret.putAll(hijo.getValoresEnNivel(nivel-1));
		
		return ret;
	}
	

	
	/**
	 * Retorna la visualización del objeto
	 */
	@Override
	public String toString() {		 
		String strHijos = "";
		
		for (Iterator<ArbolValores> it = hijos.iterator(); it.hasNext();)
			strHijos += it.next().toString() + (it.hasNext() ? ", " : "");
		
		return this.valor + (hijos.isEmpty() ? "" : " -> (" + strHijos + ")");
	}	
}
