package uy.com.uma.logicgame.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.api.persistencia.IManejadorJuego;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.api.persistencia.PersistenciaFactory;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;

/**
 * Prueba la logica de persistencia de un juego
 *
 * @author Santiago Dalchiele
 */
public class TestPersistirJuego {
	
	private static final Logger log = LogManager.getLogger(TestPersistirJuego.class.getName());
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String pathOri = "c:/temp/nuevojuego10multi2.xml";						
			JAXBContext jaxbContext = JAXBContext.newInstance(Juego.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			File ori = new File(pathOri);
			Juego juego = (Juego) jaxbUnmarshaller.unmarshal(ori);
			new ValidadorJuego().validarJuego(juego);
			PersistenciaFactory.getInstancia().getManejadorSesiones().reset(null, null);
			IManejadorJuego mj = PersistenciaFactory.getInstancia().getManejadorJuego();
			mj.persistir(juego);
			mj.actualizarRedundancias(juego.getId().intValue());
			log.info("Fin exitoso de la aplicación");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | ConfiguracionException | PersistenciaException | JAXBException | ValidadorJuegoException e) {
			e.printStackTrace();
		} finally {
			SessionFactoryUtil.shutdown();
		}
	}

}
