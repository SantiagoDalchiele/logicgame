package uy.com.uma.logicgame.test;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.uma.logicgame.api.bean.UsuarioDO;
import uy.com.uma.logicgame.api.persistencia.IManejadorInternacionalizacion;
import uy.com.uma.logicgame.api.persistencia.IManejadorSeguridad;
import uy.com.uma.logicgame.api.persistencia.PersistenciaFactory;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;

/**
 * Testea el uso de la clase ManejadorInternacionalizacion
 *
 * @author Santiago Dalchiele
 */
public class TestManejadorInternacionalizacion {
	
	private static final Logger log = LogManager.getLogger(TestManejadorInternacionalizacion.class.getName());
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			log.info("Inicio de la aplicación");
			IManejadorInternacionalizacion mi = PersistenciaFactory.getInstancia().getManejadorInternacionalizacion();
			IManejadorSeguridad ms = PersistenciaFactory.getInstancia().getManejadorSeguridad();
			/*long id = mi.internacionalizar("es", "Nombre");
			log.info("El identificador obtenido es: " + id);*/
			log.info("El texto para 1 es: " + mi.getTexto("es", 1));
			log.info("El id del juego de Santiago es: " + ms.getDatosUsuario("Santiago").getIdJuego());
			//log.info("El id del juego de santiag0 es: " + ms.getIdJuego("santiag0"));			
			Collection<UsuarioDO> ranking = ms.getRanking("santiago", 15);
			
			for (UsuarioDO du : ranking)
				log.info(du.getNivel() + "-" + du.getAlias());
			
			log.info("Fin exitoso de la aplicación");
		} catch (Exception e) {
			log.error("Error general en la aplicación", e);
		} finally {
			SessionFactoryUtil.shutdown();
		}
	}
}
