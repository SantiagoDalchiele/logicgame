package uy.com.uma.logicgame.api.persistencia;

import uy.com.uma.logicgame.api.bean.DatosConfiguracion;

/**
 * Manejador de la logica de configuracion
 *
 * @author Santiago Dalchiele
 */
public interface IManejadorConfiguracion {

	/** Resuelve en un único metodo la logica de los datos del usuario y los idiomas persistidos en la base de datos */
	DatosConfiguracion getDatosConfiguracion (String idUsuario) throws PersistenciaException;
}
