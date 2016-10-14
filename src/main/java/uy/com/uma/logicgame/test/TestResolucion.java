package uy.com.uma.logicgame.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;
import uy.com.uma.logicgame.resolucion.Resolucion;

/**
 * Testea la clase ManejadorJuego
 *
 * @author Santiago Dalchiele
 */
public class TestResolucion {
	
	private static final Logger log = LogManager.getLogger(TestResolucion.class.getName());
	
	
	/**
	 * Punto de entrada de la aplicación
	 */
	public static void main(String[] args) {
		try {
			Collection<String> pathArchivos = new ArrayList<String>();
			/*pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/01-problemas-en-la-escuela.xml");  // 34
			pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/02-turismo-espana.xml");  // 26
			pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/03-vacaciones-islas.xml");  // 156 (usa el absurdo - para un humano no es necesario)
			pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/04-esoterico.xml");  // 105
			pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/05-ropa-prestada.xml");  // 70 (tiene al menos 2 soluciones posibles) 
			pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/06-museos.xml"); // 50
			pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/07-gustos-literarios.xml"); // 24 
			pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/08-mascotas.xml"); // 126
			pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/09-einstein.xml"); // 609 */
			//pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/10-simple.xml"); // 12
			//pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/13-hispanoamerica-recursos.xml"); // 88
			//pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/prueba-genera-6x5.xml");
			
			/* 
			pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/11-simple-sin-solucion.xml"); 
			pathArchivos.add("c:/santiago/lg/logicgame/recursos/juegos/12-simple-2-soluciones.xml");*/
			
			pathArchivos.add("c:/temp/nuevojuego10multi2.xml");
			
			for (String path : pathArchivos)
				resolverUnJuego(path);
		} catch (Exception e) {
			log.fatal("Error general en la aplicación", e);
		}
	}
	
	
	
	/**
	 * Resuelve el juego en la ruta especificada
	 */
	private static void resolverUnJuego (String path) throws JAXBException, ValidadorJuegoException, ConfiguracionException {
		File file = new File(path);
		
		if (!file.exists())
			log.error("No existe el archivo: " + file.getPath());
		else {
			JAXBContext jaxbContext = JAXBContext.newInstance(Juego.class);		 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Juego juego = (Juego) jaxbUnmarshaller.unmarshal(file);
			log.info("Resolviendo el juego: [" + path + "]");
			Resolucion r = Resolucion.resolver(juego);
			log.info("El juego " + (r.tieneSolucion() ? "" : "no ") + "tiene solución" + (r.tieneSolucion() ? (r.solucionUnica() ? " y " : " pero no ") + "es única" : ""));
			log.info("Costo total de intentar resolverlo completamente: " + r.getCosto());
			
			if (r.tieneSolucion() && r.solucionUnica()) {
				log.info("La solucion es:");
				log.info(r.getMatriz());
			}
		}
	}
}