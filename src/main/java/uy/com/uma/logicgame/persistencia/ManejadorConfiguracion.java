package uy.com.uma.logicgame.persistencia;

import java.util.Locale;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import uy.com.uma.logicgame.api.bean.ConfiguracionDO;
import uy.com.uma.logicgame.api.persistencia.IManejadorConfiguracion;
import uy.com.uma.logicgame.api.persistencia.PersistenciaException;
import uy.com.uma.logicgame.api.validacion.UtilValidacionParametros;
import uy.com.uma.logicgame.persistencia.inter.Idioma;
import uy.com.uma.logicgame.persistencia.seguridad.Usuario;

/**
 * Manejador de la logica de configuracion
 * 
 * @author Santiago Dalchiele
 */
public class ManejadorConfiguracion implements IManejadorConfiguracion {

	/** session factory */
	private SessionFactory sessions = SessionFactoryUtil.getSessionFactory();
	
	
	
	/**
	 * Resuelve en un único metodo la logica de los datos del usuario y los idiomas persistidos en la base de datos
	 */
	public ConfiguracionDO getDatosConfiguracion (String idUsuario) throws PersistenciaException {
		UtilValidacionParametros.validarIdUsuario(idUsuario);
		Session session = sessions.openSession();
		final Locale LOCALE = Locale.getDefault();
		
		try {
			ConfiguracionDO dc = new ConfiguracionDO();
			idUsuario = idUsuario.toLowerCase(LOCALE);
			Usuario u = (Usuario) session.get(Usuario.class, idUsuario);
				
			if (u == null)
				throw new PersistenciaException("No existe el usuario [" + idUsuario + "]");
			else
				dc.setDatosUsuario(Usuario.getDatosUsuario(u));
			
			Query query = session.createQuery("FROM " + Idioma.class.getName());
			
			for (Object o : query.list())
				dc.getIdiomas().add(Idioma.getDatos((Idioma) o));
			
			return dc;
		} finally {
			UtilHibernate.closeSession(session);
		}
	}
}
