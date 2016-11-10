package uy.com.uma.logicgame.test;

import uy.com.uma.logicgame.api.persistencia.IManejadorJuego;
import uy.com.uma.logicgame.api.persistencia.PersistenciaFactory;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;

/**
 * Testea la generación de redundancias de un juego en la base de datos
 *
 * @author Santiago Dalchiele
 */
public class TestGeneradorRedundancias {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PersistenciaFactory.getInstancia().getManejadorSesiones().reset();
			IManejadorJuego manJuego = PersistenciaFactory.getInstancia().getManejadorJuego();
			manJuego.actualizarRedundancias(10);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SessionFactoryUtil.shutdown();
		}
	}

}
