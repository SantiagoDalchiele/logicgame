package uy.com.uma.logicgame.ant;

import java.io.IOException;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

import uy.com.uma.logicgame.Messages;
import uy.com.uma.logicgame.api.conf.ConfiguracionException;
import uy.com.uma.logicgame.generacion.GeneradorJuegos;
import uy.com.uma.logicgame.generacion.ParametrosGeneracionJuego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.Juego;
import uy.com.uma.logicgame.nucleo.jaxb.juego.ValidadorJuegoException;

/**
 * Tarea ant que toma parametros de generación de un juego, lo intenta generar al azar y persiste el resultado en un archivo .xml
 *
 * @author Santiago Dalchiele
 */
public class GeneraLgTask extends LgAbstractTask {

	/** Identificador del juego */
	private int id;
	
	/** Cantidad de dimensiones */
	private int dims;
	
	/** Cantidad de valores por cada dimensión */
	private int values;
	
	/** Porcentaje de afirmaciones positivas 1-99% */
	private int percent;
	
	/** Costo mínimo que debe tener el juego */
	private int min;
	
	/** Costo máximo que debe tener el juego */
	private int max;
	
	/** Timeout, cantidad de segundos máximo que puede demorar la tarea en ejecutarse */
	private int timeout;
	
	
	
	/**
	 * Setters de propiedades
	 */	
	public void setId (int id) {
		this.id = id;
	}
	public void setDims (int value) {
		this.dims = value;
	}
	public void setValues (int values) {
		this.values = values;
	}
	public void setPercent (int percent) {
		this.percent = percent;
	}
	public void setMin (int min) {
		this.min = min;
	}
	public void setMax (int max) {
		this.max = max;
	}
	public void setTimeout (int timeout) {
		this.timeout = timeout;
	}
	
	
	
	/**
	 * Intenta generar un juego al azar
	 */
	public void execute() throws BuildException {
		validarPropiedades();
		configure();		
		
		try {
			Juego juego = new GeneradorJuegos(getParametros()).generar();
			
			if (!juego.getCosto().equals(BigInteger.valueOf(-1))) {
				juego.setId(BigInteger.valueOf(id));
				JAXBContext jaxbContext = JAXBContext.newInstance(Juego.class);		 
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				
				if ((file != null) && file.exists() && overwrite)
					if (!file.delete())
						log("Error al borrar el archivo " + file.getName(), Project.MSG_WARN);
				
				jaxbMarshaller.marshal(juego, file);
				log(Messages.getString("GeneraLgTask.msg_exito"), Project.MSG_INFO); //$NON-NLS-1$
				log(file.getCanonicalPath() + Messages.getString("GeneraLgTask.msg_proceso_ok"), Project.MSG_INFO); //$NON-NLS-1$
			} else
				log(Messages.getString("GeneraLgTask.err_timeout"), Project.MSG_ERR); //$NON-NLS-1$
		} catch (JAXBException | ConfiguracionException | ValidadorJuegoException | IOException e) {
			throw new BuildException(Messages.getString("GeneraLgTask.err_excepcion"), e); //$NON-NLS-1$
		}
	}
	
	
	
	/**
	 * Valida que todas las propiedades estén bien configuradas, de lo contrario tira excepción
	 */
	private void validarPropiedades() throws BuildException {
		if (file == null)
			throw new BuildException(Messages.getString("GeneraLgTask.err_prop_file")); //$NON-NLS-1$
		else if (file.exists() && (!overwrite))
			throw new BuildException(Messages.getString("GeneraLgTask.err_arch_existe_ini") + file.getAbsolutePath() + Messages.getString("GeneraLgTask.err_arch_existe_fin")); //$NON-NLS-1$ //$NON-NLS-2$
		else if (id == 0)
			throw new BuildException(Messages.getString("GeneraLgTask.err_prop_id")); //$NON-NLS-1$
		else if ((dims < 3) || (dims > 25))
			throw new BuildException(Messages.getString("GeneraLgTask.err_prop_dims")); //$NON-NLS-1$
		else if ((values < 3) || (values > 20))
			throw new BuildException(Messages.getString("GeneraLgTask.err_prop_values")); //$NON-NLS-1$
		else if ((percent < 1) || (percent > 99))
			throw new BuildException(Messages.getString("GeneraLgTask.err_prop_percent")); //$NON-NLS-1$
		else if (min < 0)
			throw new BuildException(Messages.getString("GeneraLgTask.err_prop_min")); //$NON-NLS-1$
		else if (max < 10)
			throw new BuildException(Messages.getString("GeneraLgTask.err_prop_max")); //$NON-NLS-1$
		else if (min >= max)
			throw new BuildException(Messages.getString("GeneraLgTask.err_prop_min_max")); //$NON-NLS-1$
		else if (timeout < 3)
			throw new BuildException(Messages.getString("GeneraLgTask.err_prop_timeout")); //$NON-NLS-1$
	}
	
	
	
	/**
	 * Construye los parámetros de generación en base a las propiedades de la tarea seteados
	 */
	private ParametrosGeneracionJuego getParametros() {
		ParametrosGeneracionJuego param = new ParametrosGeneracionJuego();
		param.setCantDimensiones(dims);
		param.setCantValores(values);
		param.setPorcAfirma(percent);
		param.setCostoMin(min);
		param.setCostoMax(max);
		param.setTimeout(timeout);
		return param;
	}
}
