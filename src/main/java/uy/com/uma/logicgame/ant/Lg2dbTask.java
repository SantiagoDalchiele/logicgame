package uy.com.uma.logicgame.ant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;

import uy.com.uma.logicgame.Messages;
import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.api.persistencia.IManejadorJuego;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.api.persistencia.PersistenciaFactory;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;

/**
 * Tarea ant que toma archivo(s) .xml con juegos y los persiste en la base de datos
 * 
 * @author Santiago Dalchiele
 */
public class Lg2dbTask extends LgAbstractTask {

	/** Clase utilitaria para el manejo de xml */
	private Unmarshaller jaxbUnmarshaller;
	
	/** Manejador de persistencia */
	private IManejadorJuego mj;
	
	/** Archivos procesados para mostrar al finalizar */
	private Collection<File> files = new ArrayList<File>();
	
	/** Validador de los juegos */
	private ValidadorJuego validador = new ValidadorJuego();
	
	

	/**
	 * Persiste en la base de datos los archivos .xml seteados
	 */
	@Override
	public void execute() throws BuildException {		
		if ((file == null) && (filesets.isEmpty()))
			throw new BuildException(Messages.getString("Lg2dbTask.error_prop_file_fileset")); //$NON-NLS-1$
		else if ((file != null) && (!file.exists()))
			throw new BuildException(Messages.getString("Lg2dbTask.error_archivo_ini") + file.getAbsolutePath() + Messages.getString("Lg2dbTask.error_archivo_fin")); //$NON-NLS-1$ //$NON-NLS-2$
		else {
			configure();
			
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Juego.class);		 
				jaxbUnmarshaller = jaxbContext.createUnmarshaller();				
				mj = PersistenciaFactory.getInstancia().getManejadorJuego();
				
				if (file != null)
					procesar(file);
				
				for (FileSet fs : filesets) {
					DirectoryScanner ds = fs.getDirectoryScanner(getProject());
					
					for (String fileName : ds.getIncludedFiles())
	                    procesar(new File(ds.getBasedir(), fileName));
				}
				
				for (File f : files)
					log(f.getCanonicalPath() + Messages.getString("Lg2dbTask.msg_proceso_ok"), Project.MSG_INFO); //$NON-NLS-1$
			} catch (JAXBException | ClassNotFoundException | IllegalAccessException | InstantiationException | 
					ConfiguracionException | ValidadorJuegoException | PersistenciaException | IOException e) {
				throw new BuildException(Messages.getString("Lg2dbTask.error_archivo_proc_ini") + file.getPath() + Messages.getString("Lg2dbTask.error_archivo_proc_fin"), e); //$NON-NLS-1$ //$NON-NLS-2$
			} finally {
				SessionFactoryUtil.shutdown();
			}
		}		
	}
	
	
	
	/**
	 * Procesa un archivo, lee el xml y graba en la base de datos
	 */
	private void procesar (File file) throws IOException, JAXBException, PersistenciaException, ValidadorJuegoException {
		log(Messages.getString("Lg2dbTask.msg_proceso") + file.getCanonicalPath(), Project.MSG_INFO); //$NON-NLS-1$
		
		if ((jaxbUnmarshaller != null) && (mj != null)) {
			Juego juego = (Juego) jaxbUnmarshaller.unmarshal(file);
			validador.validarJuego(juego);
			boolean existe = mj.existe(juego.getId().intValue()); 
			
			if (existe && (!overwrite))
				log(Messages.getString("Lg2dbTask.error_id_existente") + juego.getId(), Project.MSG_ERR); //$NON-NLS-1$
			else {
				mj.persistir(juego);
				mj.actualizarRedundancias(juego.getId().intValue());
				files.add(file);
			}
		}
	}
}
