package uy.com.uma.logicgame.api.bean;

/**
 * Clase liviana para trasegar datos con fe de los idiomas
 *
 * @author Santiago Dalchiele
 */
public class DatosIdioma {

	private String id;
	private String nombre;
	private String icono;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
}
