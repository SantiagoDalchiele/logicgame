package uy.com.uma.logicgame.persistencia;

import uy.com.uma.logicgame.api.persistencia.IManejadorSesiones;

/**
 * Implementa un stub para desacoplar de la interface web las referencias a hibernate
 *
 * @author Santiago Dalchiele
 */
public class ManejadorSesiones1Capa implements IManejadorSesiones {

	
	
	/** 
	 * Resetea la configuracion 
	 */
	@Override
	public void reset() {
		SessionFactoryUtil.reset();
	}

	
	/** 
	 * Retorna TRUE si se inicializó 
	 */
	@Override
	public boolean isInitialized() {
		return SessionFactoryUtil.isInitialized();
	}

	
	
	/** 
	 * Cierra la session 
	 */
	@Override
	public void shutdown() {
		SessionFactoryUtil.shutdown();
	}
}
