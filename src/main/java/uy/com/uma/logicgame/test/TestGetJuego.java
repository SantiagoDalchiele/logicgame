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
			obtener(1, "recursos/juegosnew/01-problemas-en-la-escuela.xml");
			obtener(2, "recursos/juegosnew/02-turismo-espana.xml");
			obtener(3, "recursos/juegosnew/03-vacaciones-islas.xml");
			obtener(4, "recursos/juegosnew/04-esoterico.xml");
			obtener(6, "recursos/juegosnew/06-museos.xml");
			obtener(7, "recursos/juegosnew/07-gustos-literarios.xml");
			obtener(8, "recursos/juegosnew/08-mascotas.xml");
			obtener(9, "recursos/juegosnew/09-einstein.xml");
			obtener(10, "recursos/juegosnew/10-simple.xml");
			obtener(13, "recursos/juegosnew/13-hispanoamerica-recursos.xml");
			obtener(14, "recursos/juegosnew/14-RiosYBanderas.xml");
			obtener(15, "recursos/juegosnew/15-problemas-en-la-vivienda.xml");
			obtener(16, "recursos/juegosnew/16-juegos-infantiles.xml");
			obtener(17, "recursos/juegosnew/17-vacaciones-uruguay.xml");
			obtener(18, "recursos/juegosnew/18-comensales.xml");
			obtener(19, "recursos/juegosnew/19-mundial-de-rugby.xml");
			obtener(20, "recursos/juegosnew/20-casamientos.xml");
			obtener(21, "recursos/juegosnew/21-turistas-en-uruguay.xml");
			obtener(22, "recursos/juegosnew/22-video-juegos-tecnologia.xml");
			log.info("Fin exitoso de la aplicación");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | ConfiguracionException | PersistenciaException | JAXBException e) {
			e.printStackTrace();
		} finally {
			SessionFactoryUtil.shutdown();
		}
	}
	
	
	
	private static void obtener (int nroJuego, String pathDest) throws InstantiationException, IllegalAccessException, 
										ClassNotFoundException, ConfiguracionException, JAXBException, PersistenciaException {
		log.info("obteniendo el juego " + nroJuego + " al archivo " + pathDest);
		PersistenciaFactory.getInstancia().getManejadorSesiones().reset();
		IManejadorJuego mj = PersistenciaFactory.getInstancia().getManejadorJuego();
		JAXBContext jaxbContext = JAXBContext.newInstance(Juego.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		File dest = new File(pathDest);
		Juego j = mj.obtener(nroJuego);			
		
		if (dest.exists())
			dest.delete();			
		
		jaxbMarshaller.marshal(j, dest);
	}
}
