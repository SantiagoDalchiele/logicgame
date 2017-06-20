package uy.com.uma.logicgame.test;

import java.io.File;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.generacion.OptimizadorPistas;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;
import uy.com.uma.logicgame.resolucion.Resolucion;

/**
 * Teste la optimización de pistas de un juego
 *
 * @author Santiago Dalchiele
 */
public class TestOptimizadorPistas {
	
	private static final Logger log = LogManager.getLogger(TestOptimizadorPistas.class.getName());

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//String path = "c:/santiago/java/logicgame/recursos/juegos/10-simple.xml";
		//String path = "c:/santiago/java/logicgame/recursos/juegos/15-Problemas en la vivienda.xml";		
		//String pathDest = "c:/santiago/java/logicgame/recursos/juegos/15-Problemas en la vivienda-mod.xml";
		String path = "c:/santiago/java/logicgame/recursos/juegos/prueba-genera-6x5.xml";
		String pathDest = "c:/santiago/java/logicgame/recursos/juegos/prueba-genera-6x5-mod.xml";
		File file = new File(path);

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Juego.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Juego juego = (Juego) jaxbUnmarshaller.unmarshal(file);
			log.info("Resolviendo el juego: [" + path + "]");
			Resolucion r = Resolucion.resolver(juego);
			log.info("El juego " + (r.tieneSolucion() ? "" : "no ") + "tiene solución" + (r.tieneSolucion() ? (r.solucionUnica() ? " y " : " pero no ") + "es única" : ""));
			log.info("Costo total de intentar resolverlo completamente: " + r.getCosto());
			
			if (!new OptimizadorPistas().optimizar(juego).isEmpty()) {
				r = Resolucion.resolver(juego);
				log.info("El juego " + (r.tieneSolucion() ? "" : "no ") + "tiene solución" + (r.tieneSolucion() ? (r.solucionUnica() ? " y " : " pero no ") + "es única" : ""));
				log.info("Costo total de intentar resolverlo completamente: " + r.getCosto());
				juego.setCosto(BigInteger.valueOf(r.getCosto()));
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.marshal(juego, new File(pathDest));
			}
		} catch (JAXBException | ConfiguracionException | ValidadorJuegoException e) {
			e.printStackTrace();
		}
	}
}
