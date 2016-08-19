package uy.com.uma.logicgame.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.api.persistencia.IManejadorJuego;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.api.persistencia.PersistenciaFactory;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;

/**
 * Prueba la logica de persistencia de obtener un juego
 *
 * @author Santiago Dalchiele
 */
public class TestGetJuego {
	
	private static final Logger log = LogManager.getLogger(TestGetJuego.class.getName());
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		try {
			String pathDest = "c:/temp/nuevojuego10multi2.xml";
			PersistenciaFactory.getInstancia().getManejadorSesiones().reset(null, null);
			IManejadorJuego mj = PersistenciaFactory.getInstancia().getManejadorJuego();
			JAXBContext jaxbContext = JAXBContext.newInstance(Juego.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			File dest = new File(pathDest);
			Juego j = mj.obtener(10);			
			
			if (dest.exists())
				dest.delete();			
			
			jaxbMarshaller.marshal(j, dest);			
			log.info("Fin exitoso de la aplicación");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | ConfiguracionException | PersistenciaException | JAXBException e) {
			e.printStackTrace();
		} finally {
			SessionFactoryUtil.shutdown();
		}
	}
}
