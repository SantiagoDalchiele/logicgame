package uy.com.uma.logicgame.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;
import org.hibernate.cfg.Configuration;

import uy.com.uma.logicgame.api.conf.ConfiguracionLoadHelper;
import uy.com.uma.logicgame.api.persistencia.IManejadorJuego;
import uy.com.uma.logicgame.api.persistencia.PersistenciaFactory;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;

/**
 * Testea el uso de la clase ManejadorJuego
 *
 * @author Santiago Dalchiele
 */
public class TestManejadorJuego {

	static {
		System.setProperty(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "c:/santiago/git/logicgame/conf/log4j2.xml");
	}

	private static final Logger log = LogManager.getLogger(TestManejadorJuego.class.getName());
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {			
			log.info("Inicio de la aplicación");
			
			String hibernatePropsFilePath = "c:/santiago/git/logicgame/conf/hibernate.cfg.xml";
			File hibernatePropsFile = new File(hibernatePropsFilePath);
			Configuration configuration = new Configuration(); 
			configuration.configure(hibernatePropsFile);
			SessionFactoryUtil.getSessionFactory(configuration);
			
			/** @deprecated usar variable de ambiente LOGICGAME_HOME=c:/santiago/git/logicgame/conf */
			//System.setProperty(IConfiguracionConstantes.CONF_SYSTEM_VAR, "c:/santiago/git/logicgame/conf/logicgame-conf.xml");
			ConfiguracionLoadHelper.getInstancia();
			
			String pathOri = "c:/santiago/git/logicgame/recursos/juegos/06-museos.xml";
			String pathDest = "c:/temp/06-museos-bd.xml";
			int idJuego = 6;
			File file = new File(pathOri);
			JAXBContext jaxbContext = JAXBContext.newInstance(Juego.class);		 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Juego juego = (Juego) jaxbUnmarshaller.unmarshal(file);
			IManejadorJuego mj = PersistenciaFactory.getInstancia().getManejadorJuego();
			
			log.info((mj.existe(10) ? "SI" : "NO") + " existe un juego con identificador 10");
			log.info((mj.existe(3874) ? "SI" : "NO") + " existe un juego con identificador 3874");
			mj.borrar(idJuego);
			mj.persistir(juego);
			
			Juego nuevo = mj.obtener(idJuego);
			File dest = new File(pathDest);
			
			if (dest.exists())
				dest.delete();
			
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.marshal(nuevo, dest);
			
			log.info("Fin exitoso de la aplicación");
		} catch (Exception e) {
			log.error("Error general en la aplicación", e);
		} finally {
			SessionFactoryUtil.shutdown();
		}
	}
}
