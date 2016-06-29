package uy.com.uma.logicgame.ant;

import java.io.File;
import java.util.Vector;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.hibernate.cfg.Configuration;

import uy.com.uma.logicgame.api.conf.ConfiguracionLoadHelper;
import uy.com.uma.logicgame.persistencia.SessionFactoryUtil;

/**
 * Implementa métodos comunes a todas las tareas ant implementadas
 * Las posibles propiedades de toda tarea que extiende esta clase son: confDir, log4j2ConfFile, hibernateConfFile, lgConfFile, file e incluir filesets
 *
 * @author Santiago Dalchiele
 */
abstract class LgAbstractTask extends Task {

	/** Archivo xml con el contenido del juego */
	protected File file;
	
	/** Filesets de posibles archivos */
	protected Vector<FileSet> filesets = new Vector<FileSet>();
	
	/** Indica si sobre-escribir o no */
	protected boolean overwrite;

	

	/**
	 * Setters de propiedades
	 */
	public void setFile(File file) {
		this.file = file;
	}
	public void addFileset(FileSet fileset) {
        filesets.add(fileset);
    }
	public void setOverwrite(boolean value) {
		this.overwrite = value;
	}

	

	/**
	 * Configura la aplicación.  Configura el archivo hibernate.cfg.xml
	 */
	protected void configure() throws BuildException {		
		configureHibernate();
	}

	
	
	/**
	 * Configura hibernate
	 */
	private void configureHibernate() {
		Configuration configuration = ConfiguracionLoadHelper.loadHibernateConfiguration(); 
		SessionFactoryUtil.getSessionFactory(configuration);
	}
}
