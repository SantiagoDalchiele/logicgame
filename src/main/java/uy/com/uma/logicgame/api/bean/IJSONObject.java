package uy.com.uma.logicgame.api.bean;

/**
 * Objetos que son "transformables" al formato JSON
 * 
 * @author Santiago Dalchiele
 */
public interface IJSONObject {

	/** Retorna el dato en formato JSON */
	String toJSON();
}
