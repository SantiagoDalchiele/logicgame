package uy.com.uma.logicgame.ant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

import uy.com.uma.logicgame.Messages;
import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.api.persistencia.IManejadorJuego;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.api.persistencia.PersistenciaFactory;

/**
 * Tarea ant que toma un identificador de un juego y reconstruye los atributos redundantes registrados en la base de datos para ese juego
 * Parámtros usados:
 * 	configuracion: confDir o (log4j2ConfFile, hibernateConfFile, lgConfFile)
 * 	id o <idset id="">
 *
 * @author Santiago Dalchiele
 */
public class LgUpdDbTask extends LgAbstractTask {

	/** Identificador del juego */
	private int id;
	
	/** Conjunto de identificadores */
	private Vector<IdSet> idsets = new Vector<IdSet>();
	
	/** Manejador de persistencia */
	private IManejadorJuego mj;
	
	/** Identificadores procesados */
	private Collection<Integer> ids = new ArrayList<Integer>();
	
	
	
	/**
	 * Setters de propiedades
	 */
	public void setId (int id) {
		this.id = id;
	}
	public IdSet createIdSet() {
        IdSet ids = new IdSet();
        idsets.add(ids);
        return ids;
    }	
	
	public class IdSet {        
        private int id;
        public IdSet() {}
        public void setId(int id) { this.id = id; }
        public int getId() { return id; }
    }
	
	
	
	/**
	 * Reconstruye los atributos redundantes registrados en la base de datos
	 */
	@Override
	public void execute() throws BuildException {
		if ((id == 0) && (idsets.isEmpty()))
			throw new BuildException(Messages.getString("LgUpdDbTask.error_props")); //$NON-NLS-1$
		else {
			configure();		
			
			try {
				mj = PersistenciaFactory.getInstancia().getManejadorJuego();
				
				if (id != 0)
					procesar(id);
				
				for (IdSet idJ : idsets)
					procesar (idJ.getId());
				
				for (Integer idJ : ids)
					log(idJ + Messages.getString("LgUpdDbTask.proceso_ok"), Project.MSG_INFO);				 //$NON-NLS-1$
			} catch (PersistenciaException | ConfiguracionException | InstantiationException | IllegalAccessException | 
					ClassNotFoundException e) {
				throw new BuildException(Messages.getString("LgUpdDbTask.excepcion"), e); //$NON-NLS-1$
			}			
		}
	}
	
	
	
	/**
	 * Procesa un identificador de juego en particular
	 */
	private void procesar (int idJuego) throws PersistenciaException {
		if (!mj.existe(idJuego))
			log(Messages.getString("LgUpdDbTask.no_existe_id_ini") + idJuego + Messages.getString("LgUpdDbTask.no_existe_id_fin"), Project.MSG_ERR); //$NON-NLS-1$ //$NON-NLS-2$
		else {
			mj.actualizarRedundancias(idJuego);
			ids.add(idJuego);
		}
	}
}
