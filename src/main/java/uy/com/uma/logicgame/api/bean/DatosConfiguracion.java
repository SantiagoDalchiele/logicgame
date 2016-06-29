package uy.com.uma.logicgame.api.bean;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Encapsula la información de los datos del usuario y los idiomas persistidos en la base de datos
 * 
 * @author Santiago Dalchiele
 */
public class DatosConfiguracion {

	private DatosUsuario datosUsuario;
	private Collection<DatosIdioma> idiomas = new ArrayList<DatosIdioma>();
	
	
	
	public DatosUsuario getDatosUsuario() {
		return datosUsuario;
	}
	public void setDatosUsuario(DatosUsuario datosUsuario) {
		this.datosUsuario = datosUsuario;
	}
	public Collection<DatosIdioma> getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(Collection<DatosIdioma> idiomas) {
		this.idiomas = idiomas;
	}	
}
