package uy.com.uma.logicgame.api.persistencia;

import uy.com.uma.logicgame.api.bean.ConfiguracionDO;

/**
 * Manejador de la logica de configuracion
 *
 * @author Santiago Dalchiele
 */
public interface IManejadorConfiguracion {

	/** Resuelve en un único metodo la logica de los datos del usuario y los idiomas persistidos en la base de datos */
	ConfiguracionDO getDatosConfiguracion (String idUsuario) throws PersistenciaException;
}
