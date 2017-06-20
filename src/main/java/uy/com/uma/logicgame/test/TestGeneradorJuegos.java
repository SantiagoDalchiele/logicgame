package uy.com.uma.logicgame.test;

import java.io.File;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.generacion.GeneradorJuegos;
import uy.com.uma.logicgame.generacion.ParametrosGeneracionJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;

/**
 * Testea la generación aleatoria de un juego
 *
 * @author Santiago Dalchiele
 */
public class TestGeneradorJuegos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "c:/temp/prueba-genera-5x5.xml";
		ParametrosGeneracionJuego param = new ParametrosGeneracionJuego();
		param.setCantDimensiones(4);
		param.setCantValores(4);
		param.setPorcAfirma(25);
		param.setCostoMin(200);
		param.setCostoMax(99999);
		param.setTimeout(60);
		GeneradorJuegos gen = new GeneradorJuegos(param);
		
		try {
			Juego juego = gen.generar();
			
			if (!juego.getCosto().equals(BigInteger.valueOf(-1))) {
				juego.setId(BigInteger.valueOf(17));
				JAXBContext jaxbContext = JAXBContext.newInstance(Juego.class);		 
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				File file = new File(path);
				
				if (file.exists())
					if (!file.delete())
						System.out.println("Error al borrar el archivo " + file.getName());
				
				jaxbMarshaller.marshal(juego, file);
				System.out.println("Fin exitoso!");
			} else
				System.out.println("Fin por timeout, no se pudo generar un juego válido");
		} catch (JAXBException | ValidadorJuegoException | ConfiguracionException e) {
			e.printStackTrace();
		}
	}

}
