package uy.com.uma.logicgame.persistencia.inter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import uy.com.uma.comun.util.UtilString;
import uy.com.uma.logicgame.api.bean.DatosIdioma;

/**
 * Representa la tabla idiomas
 *
 * @author Santiago Dalchiele
 */
@Entity
@Table(name = "idiomas")
public class Idioma {

	@Id
	@Column(columnDefinition = "bpchar(2)")
	private String id;
	
	@Column
	private String nombre;
	
	@Column
	private String icono;

	
	
	public Idioma() {
	}
	
	public Idioma(String id, String nombre, String icono) {
		this.id = id;
		this.nombre = nombre;
		this.icono = icono;
	}

	
	
	/**
	 * Retorna una clase "liviana" con los datos del idioma
	 */
	public static DatosIdioma getDatos (Idioma idioma) {
		DatosIdioma di = new DatosIdioma();
		di.setId(idioma.getId());
		di.setNombre(UtilString.quitarLetrasEspeciales(idioma.getNombre()));
		di.setIcono(idioma.getIcono());
		return di;
	}
	
	
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
