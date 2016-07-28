package uy.com.uma.logicgame.api.bean;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Encapsula la información de los datos del usuario y los idiomas persistidos en la base de datos
 * 
 * @author Santiago Dalchiele
 */
public class ConfiguracionDO {

	private UsuarioDO datosUsuario;
	private Collection<IdiomaDO> idiomas = new ArrayList<IdiomaDO>();
	
	
	
	public UsuarioDO getDatosUsuario() {
		return datosUsuario;
	}
	public void setDatosUsuario(UsuarioDO datosUsuario) {
		this.datosUsuario = datosUsuario;
	}
	public Collection<IdiomaDO> getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(Collection<IdiomaDO> idiomas) {
		this.idiomas = idiomas;
	}	
}
