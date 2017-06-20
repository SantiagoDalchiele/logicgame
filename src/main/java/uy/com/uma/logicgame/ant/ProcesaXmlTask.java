package uy.com.uma.logicgame.ant;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

import uy.com.uma.logicgame.Messages;
import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;
import uy.com.uma.logicgame.resolucion.Resolucion;

/**
 * Tarea ant que toma un archivo .xml con un juego y verifica si tiene solución si esta es única y el costo de resolverlo.
 * Utiliza la propiedad file y al menos una de las propiedades lgConfFile o confDir. 
 *
 * @author Santiago Dalchiele
 */
public class ProcesaXmlTask extends LgAbstractTask {

	
	
	/**
	 * Procesa el archivo y muestra el resultado.
	 */
	@Override
	public void execute() throws BuildException {		
		if (file == null)
			throw new BuildException(Messages.getString("ProcesaXmlTask.error_prop_file"));		 //$NON-NLS-1$
		else if (!file.exists())
			throw new BuildException(Messages.getString("ProcesaXmlTask.error_arch_ini") + file.getAbsolutePath() + Messages.getString("ProcesaXmlTask.error_arch_fin")); //$NON-NLS-1$ //$NON-NLS-2$
		else {
			configure();
			
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Juego.class);		 
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				log(Messages.getString("ProcesaXmlTask.msg_proceso") + file.getCanonicalPath(), Project.MSG_INFO); //$NON-NLS-1$
				Juego juego = (Juego) jaxbUnmarshaller.unmarshal(file);
				new ValidadorJuego().validarJuego(juego);
				Resolucion r = Resolucion.resolver(juego);								
				log(Messages.getString("ProcesaXmlTask.msg_sol_1") + (r.tieneSolucion() ? Messages.getString("ProcesaXmlTask.msg_sol_2") : Messages.getString("ProcesaXmlTask.msg_sol_3")) + Messages.getString("ProcesaXmlTask.msg_sol_4") + (r.tieneSolucion() ? (r.solucionUnica() ? Messages.getString("ProcesaXmlTask.msg_sol_5") : Messages.getString("ProcesaXmlTask.msg_sol_6")) + Messages.getString("ProcesaXmlTask.msg_sol_7") : Messages.getString("ProcesaXmlTask.msg_sol_8")), Project.MSG_INFO);				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
				log(Messages.getString("ProcesaXmlTask.msg_costo") + r.getCosto(), Project.MSG_INFO); //$NON-NLS-1$
			} catch (JAXBException | IOException | ValidadorJuegoException | ConfiguracionException e) {
				throw new BuildException(Messages.getString("ProcesaXmlTask.error_proceso_ini") + file.getPath() + Messages.getString("ProcesaXmlTask.error_proceso_fin"), e); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}		
	}
}
