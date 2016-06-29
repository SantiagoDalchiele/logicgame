package uy.com.uma.logicgame.ant;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

import uy.com.uma.logicgame.Messages;
import uy.com.uma.logicgame.api.persistencia.IManejadorJuego;
import uy.com.uma.logicgame.api.persistencia.PersistenciaFactory;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;

/**
 * Tarea ant que toma un identificador de un juego y persiste en archivo .xml el juego registrado en la base de datos
 *
 * @author Santiago Dalchiele
 */
public class Lg2xmlTask extends LgAbstractTask {

	/** Identificador del juego */
	private int id;
	
	
	
	/**
	 * Setters de propiedades
	 */
	public void setId (int id) {
		this.id = id;
	}
	
	
	
	/**
	 * Persiste el juego en un archivo .xml y registrado previamente en la base de datos
	 */
	@Override
	public void execute() throws BuildException {		
		if (file == null)
			throw new BuildException(Messages.getString("Lg2xmlTask.error_prop_file"));		 //$NON-NLS-1$
		else if (file.exists() && (!overwrite))
			throw new BuildException(Messages.getString("Lg2xmlTask.error_arch_ini") + file.getAbsolutePath() + Messages.getString("Lg2xmlTask.error_arch_fin")); //$NON-NLS-1$ //$NON-NLS-2$
		else if (id == 0)
			throw new BuildException(Messages.getString("Lg2xmlTask.error_prop_id")); //$NON-NLS-1$
		else {
			configure();
			
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Juego.class);		 
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();				
				IManejadorJuego mj = PersistenciaFactory.getInstancia().getManejadorJuego();
				log(Messages.getString("Lg2xmlTask.msg_proceso_1") + id + Messages.getString("Lg2xmlTask.msg_proceso_2") + file.getCanonicalPath(), Project.MSG_INFO); //$NON-NLS-1$ //$NON-NLS-2$
				
				if (!mj.existe(id))
					throw new BuildException(Messages.getString("Lg2xmlTask.error_id_no_existe_ini") + id + Messages.getString("Lg2xmlTask.error_id_no_existe_fin")); //$NON-NLS-1$ //$NON-NLS-2$
				
				Juego juego = mj.obtener(id);
				
				if (file.exists() && overwrite)
					file.delete();				

				jaxbMarshaller.marshal(juego, file);
				log(file.getCanonicalPath() + Messages.getString("Lg2xmlTask.msg_proceso_ok"), Project.MSG_INFO); //$NON-NLS-1$
			} catch (Exception e) {
				throw new BuildException(Messages.getString("Lg2xmlTask.error_proceso_ini") + file.getPath() + Messages.getString("Lg2xmlTask.error_proceso_fin"), e); //$NON-NLS-1$ //$NON-NLS-2$
			} finally {
				SessionFactoryUtil.shutdown();
			}
		}		
	}
}
