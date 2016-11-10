package uy.com.uma.logicgame.api.bean;

import javax.json.JsonObject;

/**
 * Objetos que son "transformables" al formato JSON
 * 
 * @author Santiago Dalchiele
 */
public interface IJSONObject {

	/** Retorna el dato en formato JSON */
	JsonObject toJSON();
}
